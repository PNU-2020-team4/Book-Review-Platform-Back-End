package com.team4.bookreview.book;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team4.bookreview.vo.BookVO;

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
public class bookQueryTest {

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
	public void bookSelectAllTest() throws Exception{
		 this.mockMvc.perform(get("/book/get"))
	                .andDo(print())
	                .andExpect(status().isOk());

	}
	
	@Test
	public void bookSelectOneTest() throws Exception{
		 final String content = "{\"idx\":1}";
		 
		 this.mockMvc.perform(post("/book/getone")
				 .param("data", content)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
	}
	
	@Test
	public void bookInsertTest() throws Exception{
		 final String content = "{\"idx\":12345678,"
		 		+ " \"name\":\"BOOKTEST\","
		 		+ " \"author\":\"AUTHORTEST\","
		 		+ " \"genre\":\"GENRETEST\"}";
		 
		 this.mockMvc.perform(post("/book/insert")
				 .param("data", content)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
	}
	
	@Test
	public void bookUpdateTest() throws Exception{
		 final String content = "{\"idx\":12345678,"
		 		+ " \"name\":\"BOOKTESTUPDATE\"}";
		 
		 this.mockMvc.perform(post("/book/update")
				 .param("data", content)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
	}
	
	@Test
	public void bookDeleteTest() throws Exception{
		 final String content = "{\"idx\":12345678}";
		 
		 this.mockMvc.perform(post("/book/delete")
				 .param("data", content)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
	}
	
	
	@Test
	public void bookAuthorSearchTest() throws Exception{
		 final String content = "{\"author\":\"편집부\"}";
		 
		 this.mockMvc.perform(post("/book/search/author")
				 .param("data", content)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
	}
	
	@Test
	public void bookNameSearchTest() throws Exception{
		 final String content = "{\"name\":\"빨강 머리 앤이 5년 후 나에게 : Q & A a day (Q & A a day)\"}";
		 
		 this.mockMvc.perform(post("/book/search/name")
				 .param("data", content)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
	}
	
	@Test
	public void bookGetUserReviewBasedRecommendTest() throws Exception{
		 final String content = "{\"writer\":98939011}";
		 this.mockMvc.perform(post("/recommend/user/review")
				 .param("data", content)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
	}
	
	@Test
	public void bookGetUserHistoryBasedRecommendTest() throws Exception{
		 final String content = "{\"writer\":98939011}";
		 this.mockMvc.perform(post("/recommend/user/history")
				 .param("data", content)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
	}
	
	@Test
	public void bookGetDataBasedRecommendTest() throws Exception{
		 final String content = "{\"writer\":98939011}";
		 this.mockMvc.perform(post("/recommend/database")
				 .param("data", content)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON_VALUE))
	                .andDo(print())
	                .andExpect(status().isOk());
	}
	
}
