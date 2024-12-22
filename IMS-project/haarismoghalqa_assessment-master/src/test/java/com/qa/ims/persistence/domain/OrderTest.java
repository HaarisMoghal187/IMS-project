package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderTest {

	//This is to test all the constructors
	private Order order;
	private Order order2;
	private Order order3;
	private Order order4;
	private Order order5;

	@Before
	public void setUp() {
		order = new Order(1L);
		order2 = new Order(1L, 1L);
		order3 = new Order(1L, 1L, 2L);
		order4 = new Order(1L, 1L, 1L, 2L);
		order5 = new Order(1L, 1L, null, 1L, 3l, 2.0); 
	}

	@Test
	public void settersTest() {

		// Constructor with 2 attributes

		assertNotNull(order2.getOrderId());
		assertNotNull(order2.getCustomerId());

		// Constructor with 3 attributes
		assertNotNull(order3.getOrderId());
		assertNotNull(order3.getItemId());
		assertNotNull(order3.getQuantity());

		// Constructor with 2 attributes
		order2.setOrderId(null);
		assertNull(order2.getOrderId());
		order2.setCustomerId(null);
		assertNull(order2.getCustomerId());

		// Constructor with 3 attributes
		order3.setOrderId(null);
		assertNull(order3.getOrderId());
		order3.setItemId(null);
		assertNull(order3.getItemId());
		order3.setQuantity(null);
		assertNull(order3.getQuantity());

		order5.setItem(null);
		assertNull(order5.getItem());
//		order5.setCost((Double) null);
//		assertNull(order5.getCost());

	}

	@Test
	public void testToString() {
		String actual = "Order [orderId=" + order2.getOrderId() + ", customerId=" + order2.getCustomerId() + "]";
		assertEquals(actual, order2.toString());
	}

	@Test
	public void testToStringOrderLine() {
		String actual = "Order [orderId=" + order3.getOrderId() + ", itemId=" + order3.getItemId() + ", quantity="
				+ order3.getQuantity() + "]";
		assertEquals(actual, order3.orderlinesString());
	}

	@Test
	public void testToStringAllString() {
		String actual = "Order [orderId=" + order5.getOrderId() + ", customerId=" + order5.getCustomerId() + ", itemId="+  order5.getItemId()+ ", Items=" +order5.getItems()+ ", quantity=" + order5.getQuantity() + ", cost=" + order5.getCost() + "]";
		assertEquals(actual, order5.allString());
	}

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Order.class).verify();
	}

}
