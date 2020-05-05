package com.team4.bookreview.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.team4.bookreview.util.UserQueryResRenderer;

@Controller
public class LoginController {

	@Autowired
	private UserQueryResRenderer renderer;
		
	private String apiResult = null;
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam String data) throws JsonParseException, JsonMappingException, IOException {
		
		System.out.println("=========== [/login] request ==========");
		System.out.println("data : " + data);

		String JSONValue = renderer.getUpdateRes(data);
		System.out.println("Return : " + JSONValue);
		return JSONValue;

	}
}