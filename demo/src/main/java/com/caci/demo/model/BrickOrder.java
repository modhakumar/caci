package com.caci.demo.model;

public class BrickOrder {

	public int noOfBricksOrdered;
	public String orderId;
	public String Status;
	
	
	public BrickOrder(int noOfBricksOrdered, String orderId, String status) {
		this.noOfBricksOrdered = noOfBricksOrdered;
		this.orderId = orderId;
		this.Status = status;
	}
	
	
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public int getNoOfBricksOrdered() {
		return noOfBricksOrdered;
	}
	public void setNoOfBricksOrdered(int noOfBricksOrdered) {
		this.noOfBricksOrdered = noOfBricksOrdered;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
	@Override
    public boolean equals(Object obj) {
        return (this.orderId.equals(((BrickOrder) obj).orderId) );
    }
}
