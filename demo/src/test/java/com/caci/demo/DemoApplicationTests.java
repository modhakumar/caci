package com.caci.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.caci.demo.model.BrickOrder;
import com.caci.demo.payloaddto.NewOrder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class DemoApplicationTests {

	public DemoApplicationTests() {
		
	}
	
	@Test
	void contextLoads() {
	}
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	
	@Test
	public void addOrder() throws Exception {
		NewOrder nOrder = new NewOrder();
		nOrder.setCustomerId("modhaId");
		nOrder.setNoOfBricks(100);
		
		String resourceURL = "http://localhost:8080/buyBricks";
		
		//Create new Order
		ResponseEntity<String> response = restTemplate.postForEntity(resourceURL, nOrder, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
	}
	
	
	@Test
	public void inValidOrders() throws Exception{
		 
	    final String resourceURL = "http://localhost:8080/FullfillOrder/invalidCustomer/invalidOrder";
	    ResponseEntity<String> response2 = restTemplate.getForEntity(resourceURL,String.class);
	    assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	
	
}
