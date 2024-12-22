package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ItemTest {

	private Items Item;
	private Items other;

	@Before
	public void setUp() {
		Item = new Items(1L, "iPhone", 540.0);
		other = new Items("iPhone", 540.0);
	}

	@Test
	public void settersTest() {
		//Constructor with 3 attributes
		assertNotNull(Item.getId());
		assertNotNull(Item.getItem_name()); 
		assertNotNull(Item.getPrice());		
//		//Constructor with 2 attributes
//		assertNotNull(other.getItem_name());
//		assertNotNull(other.getPrice());
		
		//Constructor with 3 attributes
		Item.setId(null);
		assertNull(Item.getId());
		Item.setItem_name(null);
		assertNull(Item.getItem_name());
//		Item.setPrice((Double) null);
//		assertNull(Item.getPrice());
		
//		//Constructor with 2 attributes
//		other.setItem_name(null);
//		assertNull(other.getItem_name());
//		other.setPrice(null);
//		assertNull(other.getPrice());
	}
	
	@Test
	public void testtoString() {
		String actual = "Items [id=" + Item.getId() + ", item_name=" + Item.getItem_name() + ", price=" + Item.getPrice() + "]";
		assertEquals(actual, Item.toString());
	}
	
	@Test
	public void testtoStringOther() {
		String actual = "Items [item_name=" + other.getItem_name() + ", price=" + other.getPrice() + "]";
		assertEquals(actual, Item.toStringMeth());
	}

	

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Items.class).verify();
	}
}
