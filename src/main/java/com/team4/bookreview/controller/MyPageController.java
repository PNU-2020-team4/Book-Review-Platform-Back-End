package com.team4.bookreview.controller;

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
	
	@Autowired
	private UserQueryResRenderer renderer;
	
	@RequestMapping(value="/mypage", produces="text/plain; charset=UTF-8", method=RequestMethod.POST)
	@ResponseBody
	public String seeMyPage(@RequestParam String data) throws JsonProcessingException {

		System.out.println("=============/mypage=============");
		System.out.println("data : " + data);

		String resJson = renderer.getSelectRes(data);
		
		System.out.println("result : " + resJson);

		return resJson;
	}
	
	@RequestMapping(value="/mypage/changeNick", method=RequestMethod.POST)
	@ResponseBody
	public String changeNick(@RequestParam String data)
	{
		System.out.println("=============/mypage/changeNick=============");
		System.out.println("data : " + data);

		String resJson = renderer.getUpdateNickRes(data);
		
		System.out.println("result : " + resJson);

		return resJson;
	}
}
