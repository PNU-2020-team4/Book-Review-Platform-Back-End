package com.team4.bookreview.controller;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserQueryResRenderer renderer;
		
	@RequestMapping(value="/login", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String login(@RequestParam String data) throws JsonParseException, JsonMappingException, IOException {
		
		logger.info("=========== [/login] request ==========");
		logger.info(data);

		String jsonValue = renderer.getUpdateRes(data);
		logger.info(jsonValue);
		return jsonValue;
	}

	@RequestMapping(value="/user/withdrawal", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String withdrawal(@RequestParam String data) throws JsonParseException, JsonMappingException, IOException {
		logger.info("=========== [/user/withdrawal] request ==========");
		logger.info(data);

		String jsonValue = renderer.getWithdrawalRes(data);
		logger.info(jsonValue);
		return jsonValue;
	}

	
}