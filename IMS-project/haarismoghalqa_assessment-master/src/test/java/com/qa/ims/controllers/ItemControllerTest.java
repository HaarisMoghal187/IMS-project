package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.ItemsController;

import com.qa.ims.persistence.dao.ItemsDAO;

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

	@Mock
	private Utils utils;
	@Mock
	private ItemsDAO DAO;
	@InjectMocks
	private ItemsController itemController;

	@Test
	public void readAllTest() {
		ItemsController itemController = new ItemsController(DAO, utils);
		List<Items> items = new ArrayList<>();
		items.add(new Items(1l, "Nintendo switch", 399.0));
		items.add(new Items(2l, "iMac", 1200.0));
		items.add(new Items(3l, "TV", 2100.0));
		Mockito.when(DAO.readAll()).thenReturn(items);
		assertEquals(items, itemController.readAll());
		Mockito.verify(DAO, Mockito.times(1)).readAll();
	}

//	@Test
//	public void testCreate() {
//		String ItemName = "Iphone";
//		Double Price = 1100.0;
//
//		final Items created = new Items(1l, ItemName, Price);
//
//		Mockito.when(utils.getString()).thenReturn(ItemName);
//		Mockito.when(utils.getDouble()).thenReturn(Price); 
//		Mockito.when(DAO.create(created)).thenReturn(created);

//		assertEquals(created, itemController.create());
//
//		Mockito.verify(utils, Mockito.times(1)).getString();
//		Mockito.verify(utils, Mockito.times(1)).getDouble();
//		Mockito.verify(DAO, Mockito.times(1)).create(created);

//	}

//	@Test
//	public void updateTest() {
//		Long id = 1L;
//		String Item_Name = "ps5";
//		Double Price = 400.0;
//
//		final Items updated = new Items(id, Item_Name, Price);
//		Mockito.when(utils.getLong()).thenReturn(id);
//		Mockito.when(utils.getString()).thenReturn(Item_Name);
//		Mockito.when(utils.getDouble()).thenReturn(Price);
//
//		Items item = new Items(1L, Item_Name, Price);
//		Mockito.when(DAO.update(item)).thenReturn(item);
//
////		assertEquals(updated, this.itemController.update());
////
////		Mockito.verify(utils, Mockito.times(1)).getLong();
////		Mockito.verify(utils, Mockito.times(1)).getString();
////		Mockito.verify(utils, Mockito.times(1)).getDouble();
////		Mockito.verify(DAO, Mockito.times(1)).update(updated);
//
//	}

	@Test
	public void deleteTest() {
		final long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(DAO.delete(ID)).thenReturn(1);

		assertEquals(1L, this.itemController.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(DAO, Mockito.times(1)).delete(ID);

	}

}