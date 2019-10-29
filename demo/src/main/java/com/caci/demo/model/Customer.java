package com.caci.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	
	private String id;
	private String name;
	private int orderSeq = 0; 
	private List<BrickOrder> brickOrders;
	
	public int incrementOrderSeq() {
		return orderSeq++;
	}

	public void setBrickOrders(List<BrickOrder> brickOrders) {
		this.brickOrders = brickOrders;
	}

	
	
	public Customer(String id, String name) {
		this.id = id;
		this.name = name;
		brickOrders = new ArrayList<BrickOrder>();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void addBrickOrder(BrickOrder bOrder) {
		brickOrders.add(bOrder);
	}
	
	public List<BrickOrder> getBrickOrders() {
		return brickOrders;
	}
	
	public void deleteBrickOrder(String orderId) {
		this.brickOrders.remove(new BrickOrder(0,orderId,"tempForDelete"));
	}
	
}
