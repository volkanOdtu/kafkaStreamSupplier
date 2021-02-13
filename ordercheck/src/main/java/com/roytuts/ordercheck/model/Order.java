package com.roytuts.ordercheck.model;



public class Order {
	String uuid ,name ,item ,status;
	
	public Order() {
		this.status = Status.PENDING.name();
	}

	public Order(String uuid ,String name ,String item) {
		this.uuid = uuid;
		this.name = name;
		this.item = item;
		this.status = Status.PENDING.name();
	}

	public String getUuid() { return uuid; }

	public void setUuid(String uuid) { this.uuid = uuid; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}