package com.qa.ims.persistence.domain;

import java.util.ArrayList;

public class Order {

	
	// Attributes for order
	private Long orderId;
	private Long customerId;

	// Attributes for orderline
	private Items item = null;
	private Long itemId;
	private Long quantity;
	private double cost;
	private ArrayList<Items> Items = new ArrayList<Items>();

	// Constructors for ORDER
	public Order(Long customerId) {
		this.customerId = customerId;
	}

	public Order(Long orderId, Long customerId) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
	}

	// Constructors for OrderLine

	public Order(Long orderId, Long itemId, Long quantity) {
		super();
		this.orderId = orderId;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public Order(Long orderId, Long customerId, Long itemId, Long quantity) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public Order(Long orderId, Long customerId, Items item, Long itemId, Long quantity, double d) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.item = item;
		this.itemId = itemId;
		this.quantity = quantity;
		this.cost = d;
	}

//	public Order(Long orderId, ArrayList<Items> Items, Long quantity) {
//		super();
//		this.orderId = orderId;
//		this.Items = Items;
//		this.quantity = quantity;
//	}
	

	// Getters and Setters



	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public void setItem(Items item) {
		this.item = item;
	}

	public Items getItem() {
		return item;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public ArrayList<Items> getItems() {
		return Items;
	}

	public void setItems(ArrayList<Items> Items) {
		this.Items = Items;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customerId=" + customerId + "]";
	}


	public String orderlinesString() {
		return "Order [orderId=" + orderId + ", itemId=" + itemId + ", quantity=" + quantity + "]";
	}
	
	public String allString() {
		return "Order [orderId=" + orderId + ", customerId=" + customerId + ", itemId=" + itemId + ", Items=" + Items
				+ ", quantity=" + quantity + ", cost=" + cost + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Items == null) ? 0 : Items.hashCode());
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (Items == null) {
			if (other.Items != null)
				return false;
		} else if (!Items.equals(other.Items))
			return false;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}

	
	

	

}
