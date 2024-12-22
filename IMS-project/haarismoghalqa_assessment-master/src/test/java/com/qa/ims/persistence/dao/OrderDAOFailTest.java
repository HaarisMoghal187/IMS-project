package com.qa.ims.persistence.dao;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOFailTest {
	
	private final OrderDAO DAO = new OrderDAO();
	@Before 
	public void setup() {
		DBUtils.connect("fail");
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		
		final Order created = new Order(1L, 1L);
		assertNull(DAO.create(created)); 
	}
	
	
	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1L,1L));
		DAO.create(new Order(1L, 1L));
		assertNotEquals(expected, DAO.readAll());
	}
	
	@Test
	public void testReadAllOrderline() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1L, 1L, 3L));
		DAO.addItem(new Order(1L, 1L, 3L));
		assertNotEquals(expected, DAO.readAllOrderlines());
	}
	
	@Test
	public void testReadLatest() {
		assertNotEquals(new Order(1L, 1l), DAO.readLatest());
	}
	
	
	@Test
	public void testRead() {
		final long ID = 1L;
		assertNotEquals(new Order(1L, 1l), DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Order updated = new Order(1L, 1l);
		assertNotEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
//		DAO.create(new Order(1L));
		assertNotEquals(1, DAO.delete(1));		
	}
	
	@Test
	public void testDeleteItem() {
		//Items test = new Items (1l, "Iphone", 540.0);		
		//assertNotEquals(1l , DAO.deleteItem(1l, 1l));	 	
	}

}
