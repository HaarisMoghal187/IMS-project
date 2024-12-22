package com.qa.ims.persistence.domain;

public class Items {
	private Long id;
	private String item_name;
	private double price;

	
	
	public Items(String item_name, double price) {
		super();
		this.item_name = item_name;
		this.price = price;
	}

	public Items(Long id, String item_name, double price) {
		super();
		this.id = id;
		this.item_name = item_name;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price; 
	}

	@Override
	public String toString() {
		return "Items [id=" + id + ", item_name=" + item_name + ", price=" + price + "]";
	}
	
	
	public String toStringMeth() {
		return "Items [item_name=" + item_name + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((item_name == null) ? 0 : item_name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Items other = (Items) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (item_name == null) {
			if (other.item_name != null)
				return false;
		} else if (!item_name.equals(other.item_name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}

	
	
	
	
	
	
	
}
