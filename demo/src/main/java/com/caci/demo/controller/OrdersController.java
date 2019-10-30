package com.caci.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.caci.demo.model.BrickOrder;
import com.caci.demo.payloaddto.NewOrder;
import com.caci.demo.service.OrderService;

@RestController
public class OrdersController {
	
	@Autowired
	private OrderService orderService;
	
	
	
	@PostMapping(value="/buyBricks", consumes = "application/json")
	public String buyBricks( @RequestBody NewOrder bOrder ) {

		return orderService.addOrder(bOrder.getCustomerId(), bOrder.getNoOfBricks());
		 
		
	}
	
	@RequestMapping(value="/retrieveOrder/{customerId}/{orderId}", produces = "application/json")
	public BrickOrder retrieveOrder(@PathVariable String customerId, @PathVariable String orderId) {
		return orderService.retrieveOrder(customerId, orderId);
	}
	
	
	@RequestMapping(value="/getAllOrders", produces = "application/json")
	public List<BrickOrder> getAllOrders() {
		return orderService.getAllOrders();
	}
	
	@RequestMapping(value="/updateOrder/{customerId}/{orderId}/{noOfBricks}")
	public String updateOrder(@PathVariable String customerId, @PathVariable String orderId, @PathVariable int noOfBricks) {
		String updateStatus = orderService.updateOrder(customerId, orderId,noOfBricks);
		if (updateStatus.equalsIgnoreCase("Failed")) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Failed Fullfil");
		}
		return updateStatus;
	}
	
	@RequestMapping(value="/FullfillOrder/{customerId}/{orderId}")
	public String FullfillOrder(@PathVariable String customerId, @PathVariable String orderId){
		String dispatchStatus = orderService.FullfillOrder(customerId, orderId);
		if (dispatchStatus.equalsIgnoreCase("Failed")) {
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Failed Fullfil");
		}
		return dispatchStatus;
	}
}
