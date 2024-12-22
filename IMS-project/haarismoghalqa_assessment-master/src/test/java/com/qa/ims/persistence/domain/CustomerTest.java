package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerTest {

	private Customer customer;
	private Customer other;
	
	@Before
	public void setUp() {
		customer = new Customer(1L, "Chris", "Perrins");
		other = new Customer("Chris", "Perrins");
		
	}
	
	@Test
	public void settersTest() {
		//Constructor with 3 attributes
		assertNotNull(customer.getId());
		assertNotNull(customer.getFirstName());
		assertNotNull(customer.getSurname());
		//Constructor with 2 attributes
		assertNotNull(other.getFirstName());
		assertNotNull(other.getSurname()); 
		
		//Constructor with 3 attributes
		customer.setId(null);
		assertNull(customer.getId());
		customer.setFirstName(null);
		assertNull(customer.getFirstName());
		customer.setSurname(null);
		assertNull(customer.getSurname());
		
		//Constructor with 2 attributes
		other.setFirstName(null);
		assertNull(other.getFirstName());
		other.setSurname(null);
		assertNull(other.getSurname());
		
	}
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}
	@Test
	public void testtoString() {
		String actual= "id:" + customer.getId() + " first name:" + customer.getFirstName() + " surname:" + customer.getSurname();
		assertEquals(actual, customer.toString());
		
	}
	@Test
	public void testtoStringOther() {
		String actual= "id:" + other.getId() + " first name:" + other.getFirstName() + " surname:" + other.getSurname();
		assertEquals(actual, other.toString());
		
	} 
	
}
