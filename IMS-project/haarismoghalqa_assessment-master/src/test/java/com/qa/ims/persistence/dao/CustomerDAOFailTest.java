package com.qa.ims.persistence.dao;


import static org.junit.Assert.assertNotEquals;

import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DBUtils;

public class CustomerDAOFailTest {

	private final CustomerDAO DAO = new CustomerDAO();

	@Before
	public void setup() {
		DBUtils.connect("fail"); 
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Customer created = new Customer(2L, "chris", "perrins");
		assertNull(DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Customer> expected = new ArrayList<>();
		expected.add(new Customer(1L, "jordan", "harrison"));
		assertNotEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertNotEquals(new Customer(1L, "jordan", "harrison"), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertNotEquals(new Customer(ID, "jordan", "harrison"), DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Customer updated = new Customer(1L, "chris", "perrins");
		assertNull(DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertNotEquals(1, DAO.delete(1));
	}

}
