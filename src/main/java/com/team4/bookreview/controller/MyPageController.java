package com.team4.bookreview.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.team4.bookreview.util.UserQueryResRenderer;

@Controller
public class MyPageController {
	private static final Logger logger = LoggerFactory.getLogger(MyPageController.class);
	
	@Autowired
	private UserQueryResRenderer renderer;
	
	@RequestMapping(value="/mypage", produces="text/plain; charset=UTF-8", method=RequestMethod.POST)
	@ResponseBody
	public String seeMyPage(@RequestParam String data) throws JsonProcessingException {

		logger.info("=============/mypage=============");
		logger.info("data : " + data);

		String resJson = renderer.getSelectRes(data);
		
		logger.info("result : " + resJson);

		return resJson;
	}
	
	@RequestMapping(value="/mypage/changeNick", method=RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String changeNick(@RequestParam String data)
	{
		logger.info("=============/mypage/changeNick=============");
		logger.info("data : " + data);

		String resJson = renderer.getUpdateNickRes(data);
		
		logger.info("result : " + resJson);

		return resJson;
	}
}
