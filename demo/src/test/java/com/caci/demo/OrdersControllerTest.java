package com.caci.demo;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.caci.demo.controller.OrdersController;

@RunWith(SpringRunner.class)
@WebMvcTest(OrdersController.class)
public class OrdersControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getOrder_ShouldReturnOrder() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/bricks/order"))
		.andExpect(status().isOk());
		
		
	}

}
