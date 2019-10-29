package com.caci.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.caci.demo.model.BrickOrder;
import com.caci.demo.model.Customer;

@Component
public class OrderService {

	private static List<Customer> customers = new ArrayList<>();
	
	static {
		// Initialize Customers
		Customer modha = new Customer("modhaid","modhaname");
		Customer john = new Customer("johnid","johnname");
		Customer mary = new Customer("maryid","maryname");
		
		customers.add(modha);
		customers.add(john);
		customers.add(mary);
		
	}
	
	public Customer retrieveCustomer(String customerId) {
		for (Customer customer : customers) {
			System.out.println("Printing customer name:"+customer.getId());
			if (customer.getId().equalsIgnoreCase(customerId)) {
				return customer;
			}
		}
		return null;
	}

	
	// Retrieving Order
	public BrickOrder retrieveOrder(String customerId, String orderId) {
		Customer customer = retrieveCustomer(customerId);
		System.out.println("In retrieveOrder method of service"+customerId+"-"+orderId);
		if (customer == null) {
			return null;
		}
		System.out.println("In retrieveOrder method of service: Found customer");
		for (BrickOrder bOrder : customer.getBrickOrders()) {
			System.out.println("In retrieveOrder method of service: OrderId"+bOrder.getOrderId());
			if (bOrder.getOrderId().equals(orderId)) {
				return bOrder;
			}
		}

		return null;
	}
	
	// Adding Order
	public String addOrder(String customerId, int noOfBricks) {
		System.out.println("In addOrder method buyBricks");
		Customer customer = retrieveCustomer(customerId);

		if (customer == null) {
			System.out.println("Could not find customer");
			return null;
		}
		System.out.println("Found customer");
		String OrderId = customer.getName()+"_"+customer.incrementOrderSeq();
		BrickOrder bOrder = new BrickOrder(noOfBricks,OrderId,"new");
		customer.addBrickOrder(bOrder);
		return OrderId;
		
	}
	
	
	// Return list of all customer orders
	public List<BrickOrder> getAllOrders(){
		
		List<BrickOrder> allOrders = new ArrayList<BrickOrder>();
		for (Customer customer : customers) {
			
			allOrders.addAll(customer.getBrickOrders());
		}
		
		return allOrders;
	}
	
	// Update Order ( as per spec creating new order // Order reference is unique to the submission
	public String updateOrder(String customerId, String orderId, int noOfBricks) {
		
		Customer customer = retrieveCustomer(customerId);
		if (customer != null) {
			customer.deleteBrickOrder(orderId);
			return addOrder(customerId,noOfBricks);
		}
		else {
			return "Failed";
		}
		
	}
	
	
// Fullfill order -- covers 2 cases
public String FullfillOrder(String customerId, String orderId) {
		
		BrickOrder bOrder = retrieveOrder(customerId, orderId);
		if (bOrder!= null) {
			if (!bOrder.getStatus().equalsIgnoreCase("Dispatched")) {
			
					bOrder.setStatus("Dispatched");
					return "Dispatched";
			
			}
		}
		return "Failed";
		
	}
	
	
}
