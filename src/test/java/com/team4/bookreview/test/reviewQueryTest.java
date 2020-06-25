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
public class reviewQueryTest {

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
	public void reviewSelectTest() throws Exception{
		 final String content = "{\"writer\":98939011}";
		 
		 this.mockMvc.perform(post("/review/get")
				 .param("data", content)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
	}
	
	@Test
	public void reviewSelectByBookTest() throws Exception{
		 final String content = "{\"bookID\":16310640}";
		 
		 this.mockMvc.perform(post("/book/review/get")
				 .param("data", content)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
	}
	
	@Test
	public void reviewInsertTest() throws Exception{
		 final String content = "{"
			 		+ " \"writer\":\"0\","
			 		+ " \"content\":\"CONTENTTEST\","
			 		+ " \"star\":\"5\","
			 		+ " \"book\":\"1\"}";
		 
		 this.mockMvc.perform(post("/review/insert")
				 .param("data", content)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
	}
	
	@Test
	public void reviewUpdateTest() throws Exception{
		 final String content = "{\"idx\":122,"
			 		+ " \"content\":\"CONTENTUPDATETEST\","
			 		+ " \"star\":\"3\"}";	
		 
		 this.mockMvc.perform(post("/review/update")
				 .param("data", content)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
	}

	@Test
	public void reviewDeleteTest() throws Exception{
		 final String content = "{\"idx\":122}";
		 
		 this.mockMvc.perform(post("/review/delete")
				 .param("data", content)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
	}
	

	
}
