package com.team4.bookreview.book;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




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
	public void test() throws Exception{
		 this.mockMvc.perform(get("/book/get"))
	                .andDo(print())
	                .andExpect(status().isOk());

	}
	
}
