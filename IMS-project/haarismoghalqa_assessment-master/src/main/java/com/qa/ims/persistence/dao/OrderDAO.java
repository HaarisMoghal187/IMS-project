package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	// For order
	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long OrderID = resultSet.getLong("orderId");
		Long CustomerID = resultSet.getLong("customerId");
		return new Order(OrderID, CustomerID);  
	} 

	// For Orderlines
	public Order OrderLineFromResultSet(ResultSet resultSet) throws SQLException {
		Long OrderId = resultSet.getLong("orderId");
		Long itemId = resultSet.getLong("itemId");
		long Quantity = resultSet.getLong("quantity");
		return new Order(OrderId, itemId, Quantity); 
	}

	// For items
	public Items modelFromResultSetItems(ResultSet resultset) throws SQLException {
		String name = resultset.getString("item_name");
		long price = resultset.getLong("price");
		return new Items(name, price);

	}
	// used to calculate the cost
	public double items(Long itemID) throws SQLException {
		double cost = 0;
		Connection connection = DBUtils.getInstance().getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSetItem = statement
				.executeQuery("SELECT * FROM item WHERE id ='" + itemID + "'");		
		while (resultSetItem.next()) {
			Items item = modelFromResultSetItems(resultSetItem);
			double price = item.getPrice();
			cost = price;
		}
		return cost;
	}

	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from orders ORDER by orderID ASC");) {
			ArrayList<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public List<Order> readAllOrderlines() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from orderlines ORDER by orderID ASC");) {
			ArrayList<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(OrderLineFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * from orders ORDER BY orderId DESC LIMIT 1;");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	// Add into orders
	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT into orders(customerId) values('" + order.getCustomerId() + "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	// Add into orderlines
	public Order addItem(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("insert into orderlines(orderId, itemId, quantity) values(?, ?, ?)");) {
			statement.setLong(1, order.getOrderId());
			statement.setLong(2, order.getItemId());
			statement.setLong(3, order.getQuantity());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM orders  WHERE orderId = ? ORDER BY orderId ASC");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order readOrderlines(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM orderlines  WHERE orderId = ? ORDER BY orderId ASC");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return OrderLineFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	// update into orders
	@Override
	public Order update(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update orders set OrderID ='" + order.getOrderId() + "', CustomerID ='"
					+ +order.getCustomerId() + "' where OrderID =" + order.getOrderId());
			return readOrder(order.getOrderId());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	// This reads the new order after the update has happened
	public Order readOrder(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from orders where OrderID = " + id);) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	// update into orderlines
	public Order updateOrderLines(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orderlines SET itemId = ?, quantity = ? WHERE orderId = ?");) {
			statement.setLong(1, order.getItemId());
			statement.setDouble(2, order.getQuantity());
			statement.setLong(3, order.getOrderId());
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	// delete into orders

	@Override
	public int delete(long orderid) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from orderlines where orderId = " + orderid);
			statement.executeUpdate("delete from orders where orderId = " + orderid);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	public void deleteItem(Long orderid, Long itemid) {
		// TODO Auto-generated method stub
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate(
					"delete from orderlines where orderId = '" + orderid + "' AND itemId = '" + itemid + "'");
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

	public Order cost(Long orderid) {
		// TODO Auto-generated method stub
		double cost = 0;
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT orders.orderId, orders.customerId, orderlines.itemId, item.item_name,	item.price, orderlines.quantity FROM orderlines JOIN orders on orderlines.orderId=orders.orderId JOIN item on item.id=orderlines.itemId WHERE orderlines.orderId ='" + orderid + "'");) {			
			while (resultSet.next()) {
				Order order = modelFromResultSet(resultSet);
				Order orderlines = OrderLineFromResultSet(resultSet);
				Items item = modelFromResultSetItems(resultSet);
				LOGGER.info("Order " + "[orderId="+ orderlines.getOrderId()+ ", customerId=" + order.getCustomerId() + ", itemId=" + orderlines.getItemId() +", itemName="+ item.getItem_name() +", Price=£"+ item.getPrice()+", Quantity="+ orderlines.getQuantity() + "]");
				Long itemId = orderlines.getItemId();
				Long quantity = orderlines.getQuantity();
				double total = items(itemId); // gets the price of the item
				// LOGGER.info(total); this is to test to see if the price of item is stored in total.
				cost = cost + total * quantity;				
			}
			LOGGER.info("");
			LOGGER.info("The total cost: £" + cost);
			LOGGER.info("");
			return null;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	

}
