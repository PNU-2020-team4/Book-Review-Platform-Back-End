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
public class postQueryTest {

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
	public void postSelectTest() throws Exception{
		 this.mockMvc.perform(get("/post/get"))
	                .andDo(print())
	                .andExpect(status().isOk());

	}
	
	@Test
	public void postSelectOneTest() throws Exception{
		 final String sc = "{\"idx\":8}";
		 
		 this.mockMvc.perform(post("/post/getone")
				 .param("data", sc)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
		 
		 final String fc = "{\"idx\":0}";
		 
		 this.mockMvc.perform(post("/post/getone")
				 .param("data", fc)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
	}
	
	
	@Test
	public void postInsertTest() throws Exception{
		 final String content = "{\"title\":\"TestTitle!\","
		 		+ " \"writer\":\"12345\","
		 		+ " \"content\" : \"TestContent\"}";
		 
		 this.mockMvc.perform(post("/post/insert")
				 .param("data", content)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
	}
	
	@Test
	public void postUpdateTest() throws Exception{
		 final String sc = "{\"title\":\"TestTitleUpdate\","
			 		+ " \"writer\":\"12345\","
			 		+ " \"idx\" : 9,"
			 		+ " \"content\" : \"TestContentUpdate\"}";
		 final String fc = "{\"title\":\"TestTitleUpdate\","
			 		+ " \"writer\":\"12345\","
			 		+ " \"idx\" : 1,"
			 		+ " \"content\" : \"TestContent\"}";;
		 
		 this.mockMvc.perform(post("/post/update")
				 .param("data", sc)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
		 this.mockMvc.perform(post("/post/update")
				 .param("data", fc)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
	}
	
	@Test
	public void postDeleteTest() throws Exception{
		 final String successContent = "{\"idx\":9,"
		 		+ " \"writer\": 12345}";
		 
		 this.mockMvc.perform(post("/post/delete")
				 .param("data", successContent)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
		 
		 
		 final String failContent = "{\"idx\":1,"
			 		+ " \"writer\": 12345}";
			 
			 this.mockMvc.perform(post("/post/delete")
					 .param("data", failContent)
					 .contentType(MediaType.APPLICATION_JSON)
					 .accept(MediaType.APPLICATION_JSON_VALUE))
		                .andDo(print())
		                .andExpect(status().isOk());
	}
	

	
	@Test
	public void postSearchByWriterTest() throws Exception{
		 final String successContent = "{\"writer\":1}";
		 final String failContent = "{\"writer\":0}";

		 this.mockMvc.perform(post("/post/search/writer")
				 .param("data", successContent)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
		 
		 this.mockMvc.perform(post("/post/search/writer")
				 .param("data", failContent)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
	}
	
		
	@Test
	public void postSearchByTitleTest() throws Exception{
		 final String successContent = "{\"title\":\"T1\"}";
		 final String failContent = "{\"title\":\"a\"}";
		 
		 this.mockMvc.perform(post("/post/search/title")
				 .param("data", successContent)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
		 
		 this.mockMvc.perform(post("/post/search/title")
				 .param("data", failContent)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
		 
	}
	

	
}
