package com.team4.bookreview;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/ConnectionTest")
public class ConnectionTest {
	private static final Logger logger = LoggerFactory.getLogger(ConnectionTest.class);
	
	@RequestMapping(value = "/ConnectionTest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String home(Locale locale, Model model) throws JsonProcessingException {
		logger.info("DB Connection Test! The client locale is {}.", locale);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("responseCode", "200");
		ObjectMapper obj = new ObjectMapper();
		
		return obj.writeValueAsString(map);
	}
	
}
