package com.team4.bookreview.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.web.context.WebApplicationContext;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;




@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations= {
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
"file:src/main/webapp/WEB-INF/spring/root-context.xml",
}
)
public class loginQueryTest {

	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;	
	

	@Before
	public void setup() {
		this.mockMvc 
		= MockMvcBuilders
		.webAppContextSetup(this.ctx).build();
	}

	
	
	
	
	@Test
	public void loginTest() throws Exception{
		 final String content = "{\"id\":0,"
			 		+ " \"email\":\"test@naver.com\","
			 		+ " \"name\":\"TESTUSER\","
			 		+ " \"nickname\":\"TESTNICK\","
			 		+ " \"age\":\"20-29\","
			 		+ " \"gender\" : \"M\","
			 		+ " \"birthday\" : \"01-01\","
			 		+ " \"profile_image\": \"https://\"}";
		 
		 this.mockMvc.perform(post("/login")
				 .param("data", content)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());

	}
	
	@Test
	public void withdrawalTest() throws Exception{
		 final String content = "{\"id\":0}";
		 
		 this.mockMvc.perform(post("/user/withdrawal")
				 .param("data", content)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
	}
	
	
}
