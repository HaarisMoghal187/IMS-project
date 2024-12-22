package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.dao.OrderDAO;

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private ItemsDAO itemsDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils, ItemsDAO itemsDAO) {
		super();
		this.orderDAO = orderDAO;
		this.itemsDAO = itemsDAO;
		this.utils = utils; 
	}

	@Override
	public List<Order> readAll() {
		LOGGER.info("would you like to read from order table or oderlines? (ORDER/ORDERLINES");
		String input = utils.getString().toLowerCase();

		if (input.equals("order")) {
			List<Order> orders = orderDAO.readAll();
			for (Order Order : orders) {
				LOGGER.info(Order.toString());
			}
			// return orders;
		} else if (input.equals("orderlines")) {
			List<Order> orders = orderDAO.readAllOrderlines();
			for (Order Order : orders) {
				LOGGER.info(Order.orderlinesString()); 
			}
			// return orders;
		} else {
			LOGGER.info("Please try again and enter either ORDER or ORDERLINES!");
		}
		LOGGER.info("Would you like to see the total cost of an order? (YES/NO)");
		String ans = utils.getString().toLowerCase();

		if (ans.equals("yes")) {
			LOGGER.info("please enter an orderID");
			long orderId = utils.getLong();
			long cost = 0;
			Order order = orderDAO.cost(orderId);
		}

		return null;

	}

	@Override
	public Order create() {
		// creating an order for a customer
		LOGGER.info("Please enter your customer ID.");
		Long customerId = utils.getLong();
		Order order = orderDAO.create(new Order(customerId));
		Long orderId = order.getOrderId();
		LOGGER.info("Order created, your order ID is: " + order.getOrderId());

		// Asking the customer whether they want to add some items to there order
		LOGGER.info("Would you like to add some items to your order?");
		String input = utils.getString(); // gets the users input

		List<Items> Items = itemsDAO.readAll(); // retrieving all the items
		Items items = null; // Will store the customers item.
		Boolean Check = false;

		// Checks to see if the user wants to add an item to there order.
		while (input.equals("yes")) {
			while (!Check) {
				LOGGER.info("Enter the items ID you wish to add to the order");
				Long itemId = utils.getLong();
				LOGGER.info("Enter the quantity you would like");
				Long quantity = utils.getLong();
				orderDAO.addItem(new Order(order.getOrderId(), itemId, quantity));
				for (Items i : Items) { // Find the item by ID depending on the users input (ItemsID).
					if (i.getId() == itemId) {
						items = i;
						LOGGER.info("you have added item:" + i + "with the quantity of " + quantity);
						break;
					}
				}
				order.setOrderId(orderId);
				order.setItem(items);// Set the item based on the users input
				order.setItemId(items.getId());
				Long price = (long) (quantity * items.getPrice()); // get the price of the items and times it by the
																	// quantity
				order.setQuantity(quantity);// sets the quantity based on the users input
				order.setCost(price);

				LOGGER.info("The price for those items is: £" + price);
				LOGGER.info("Would you like to add another item to the order or return?");
				LOGGER.info("(ADD/RETURN)");
				String exit = utils.getString().toLowerCase();
				if (exit.contentEquals("return")) {
					Check = true;
				}
			}
			input = "no";
		}

		LOGGER.info("Order Created");
		return order;

	}

	@Override
	public Order update() {
		LOGGER.info(
				"Would you like to ADD an item to an exisiting order or to UPDATE the order to assign another customer?");
		String answer = utils.getString().toLowerCase();
		List<Items> Items = itemsDAO.readAll(); // retrieving all the items
		switch (answer) {
		case "add":
			Items items = null; // Will store the customers item.
			LOGGER.info("You have choosen to add and item to an existing order!");
			LOGGER.info("Please enter the id of the order you would like to update");
			Long orderId = utils.getLong();
			LOGGER.info("Please enter the id of the item you wish to change in the order update");
			Long itemId = utils.getLong();
			LOGGER.info("Enter the new quantity");
			Long quantity = utils.getLong();
			for (Items i : Items) { // Find the item by ID depending on the users input (ItemsID).
				if (i.getId() == itemId) {
					items = i;
					LOGGER.info("you have added item:" + i + "with the quantity of " + quantity);
					break;
				}
			}
			// add more items to the same orderid
			Order order = orderDAO.addItem(new Order(orderId, itemId, quantity));
			order.setOrderId(orderId);
			order.setItem(items);// Set the item based on the users input
			order.setItemId(items.getId());
			long price = (long) (quantity * items.getPrice());
			order.setQuantity(quantity);// sets the quantity based on the users input
			order.setCost((long) price);
			LOGGER.info("Your order has been updated");
			return order;

		case "update":
			LOGGER.info("Please enter the id of the order you would like to update");
			Long orderID = utils.getLong();
			LOGGER.info("Please enter a CustomerID");
			Long CustomerID = utils.getLong();
			Order updateOrder = orderDAO.update(new Order(orderID, CustomerID));
			LOGGER.info("The customer id has been changed of the order");
			return updateOrder;
		default:
			LOGGER.info("Re-enter your input either add or update in lowercase please!");
			break;
		}
		LOGGER.info("Order Updated");
		return null;
	}

	@Override
	public int delete() {

		LOGGER.info("Would you like to delete the whole order or just and item from an order");
		LOGGER.info("(ORDER/ITEM)");
		String answer = utils.getString();
		switch (answer) {
		case "order":
			LOGGER.info("Please enter the OrderID:");
			Long orderId = utils.getLong();
			orderDAO.delete(orderId);
			LOGGER.info("The whole order has been deleted. Thank you!");
			break;
		case "item":
			LOGGER.info("Please enter the Order ID:");
			List<Order> orders = orderDAO.readAll();
			Long OrderId = utils.getLong();
			Order order = null; // finds the orderID and stores it
			for (Order i : orders) {
				while (i.getOrderId() == OrderId) {
					order = i;
					System.out.println(i);
					break;
				}
				LOGGER.info("Enter the item you wish to delete from the order");
				Long itemId = utils.getLong();
				LOGGER.info("The item has now been deleted from the order");
				orderDAO.deleteItem(OrderId, itemId);
				break;
			}
		}
		return 0;
	}

	public Order cost() {
		LOGGER.info("Enter the orderId you wish to find the total cost:");
		Long id = utils.getLong();
		Order order = orderDAO.cost(id); // does the calculation aswell as joining tables together to output the data.
		return null;

	}
}
