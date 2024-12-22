package com.qa.ims.persistence.dao;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.DBUtils;

public class ItemDAOFailTest {
	
private final ItemsDAO DAO = new ItemsDAO(); 
	
	@Before 
	public void setup() {
		DBUtils.connect("fail"); 
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		
		final Items created = new Items(1L, "iPhone", 540.0);
		assertNull(DAO.create(created)); 
	}
	
	@Test
	public void testReadAll() {
		List<Items> expected = new ArrayList<>();
		expected.add(new Items(1L, "iPhone", 540.0));
		assertNotEquals(expected, DAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		assertNotEquals(new Items(1L, "iPhone", 540.0), DAO.readLatest());
	}
	
	
	@Test
	public void testRead() {
		final long ID = 1L;
		assertNotEquals(new Items(1L, "iPhone", 540.0), DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Items updated = new Items(1L, "iPhone", 540.0);
		assertNotEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertNotEquals(1, DAO.delete(1));
	}
	
}
