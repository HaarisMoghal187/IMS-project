package com.qa.ims.controllers;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	
	@Mock
	private Utils utils;
	@Mock
	private OrderDAO DAO;		
	@Mock
	private ItemsDAO itemsDAO;		
	@InjectMocks
	private OrderController orderController;
	
	
	@Test
	public void readAllTest() {
		
		
		//Mockito.when(utils.getString()).thenReturn("orderlines");
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 2l));
		
//		Mockito.when(DAO.readAll()).thenReturn(orders);
//		Mockito.when(utils.getString()).thenReturn("yes");
		//assertEquals(orders, orderController.readAll());
		//Mockito.verify(DAO, Mockito.times(1)).readAll();   

		
	}
	
	@Test
	public void testCreate() {
		final Long orderId = 1L;
		final Long customerId = 3L;
		//Long IDitem = 3L;
		//Long quantity = 2L;
		
		final Order created = new Order(orderId, customerId);
		
//		Mockito.when(this.utils.getLong()).thenReturn(orderId);
//		Mockito.when(this.utils.getLong()).thenReturn(customerId);
//		//Mockito.when(this.utils.getLong()).thenReturn(IDitem);
//		//Mockito.when(this.utils.getLong()).thenReturn(quantity);
//		
//		Mockito.when(DAO.create(created)).thenReturn(created);
//		//assertEquals(created, this.orderController.create());
		
		//Mockito.verify(this.utils, Mockito.times(1)).getLong();
		//Mockito.verify(this.utils, Mockito.times(2)).getLong();
		
		
		
	}
	
	@Test
	public void updateTest() {
		Long orderId = 1L;
		Long IDitem = 3L;
		Long quantity = 2L;
		final Order order = new Order(orderId, IDitem, quantity);
//		Mockito.when(utils.getString()).thenReturn("add");
//		Mockito.when(DAO.update(order)).thenReturn(order);
//		
	}
	
	@Test
	public void testDelete() {
	//	Long orderId = 1L;
//		Mockito.when(utils.getLong()).thenReturn(orderId);
//		Mockito.when(DAO.delete(orderId)).thenReturn(1);
	//	assertEquals(1l, this.orderController.delete());
	//	Mockito.verify(this.utils, Mockito.times(1)).getLong();
	//	Mockito.verify(DAO, Mockito.times(1)).delete(orderId);
	}
}
