package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {
	
private final OrderDAO DAO = new OrderDAO();
	
	@Before 
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
		new CustomerDAO().create(new Customer(1L, "Haaris", "Moghal"));
		new ItemsDAO().create(new Items(1L, "iPhone", 540.0));
		new OrderDAO().create(new Order(1L, 1L));
		new OrderDAO().addItem(new Order(1L, 1L, 3L));
	}
	
	@Test
	public void testCreate() {
		
		final Order created = new Order(3L, 2L);
		assertEquals(created, DAO.create(created)); 
	}
	
	
	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1L,1L));
		DAO.create(new Order(1L, 1L));
		assertEquals(expected.get(0), DAO.readAll().get(0));
	}
	
	@Test
	public void testReadAllOrderline() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1L, 1L, 3L));
		assertEquals(expected, DAO.readAllOrderlines());
	}
	
	@Test
	public void testReadLatest() {
		assertEquals(new Order(2L, 1l), DAO.readLatest());
	}
	
	
	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Order(1L, 1l), DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Order updated = new Order(1L, 1l);
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(0, DAO.delete(1l));		
	}
	
	@Test
	public void testDeleteItem() {
		final Items test = new Items (1l, "Iphone", 540.0);		
		//assertEquals(0, DAO.deleteItem(1l, 1l));		
	}
	

}
