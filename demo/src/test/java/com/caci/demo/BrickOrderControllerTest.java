package com.caci.demo;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import com.caci.demo.payloaddto.NewOrder;

import static org.hamcrest.CoreMatchers.*;

class BrickOrderControllerTest {

	@Autowired
	RestTemplate restTemplate = new RestTemplate();
	
	
	
	
	@Test
	public void testAddOrder() {
		NewOrder nOrder = new NewOrder();
		nOrder.setCustomerId("modhaId");
		nOrder.setNoOfBricks(100);
		
		String resourceURL = "http://localhost:8080/buyBricks";
		
		//Create new Order
		ResponseEntity<String> response = restTemplate.postForEntity(resourceURL, nOrder, String.class);
		assertThat(response.getStatusCode(),is(HttpStatus.OK));
	}
	
	
	@Test
	public void testgetAllOrders() {
		
		String resourceURL = "http://localhost:8080/getAllOrders";
		ResponseEntity<String> response = restTemplate.getForEntity(resourceURL, String.class);
		assertNotNull(response);
		assertThat(response.getStatusCode(),is(HttpStatus.OK));
	}
	
	
	
	@Test
	public void inValidOrders() throws Exception{
		 
	    final String resourceURL = "http://localhost:8080/FullfillOrder/invalidCustomer/invalidOrder";
	    ResponseEntity<String> response = restTemplate.getForEntity(resourceURL,String.class);
	    System.out.println(response.getBody());
	    assertThat(response.getBody(),is("Failed"));
	}

}
