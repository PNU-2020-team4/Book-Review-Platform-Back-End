package com.team4.bookreview.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team4.bookreview.daoImpl.UserDAOImpl;
import com.team4.bookreview.vo.UserVO;

@Controller
public class MyPageController {
	
	@Autowired
	private UserDAOImpl userDaoImpl;
	
	@RequestMapping(value="/mypage", method=RequestMethod.POST)
	@ResponseBody
	public String seeMyPage(@RequestParam int id) throws JsonProcessingException {
		UserVO user = userDaoImpl.select(id);
		
		ObjectMapper objMapper = new ObjectMapper();
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("user",objMapper.writeValueAsString(user));
		
		System.out.println(user);
		
		return objMapper.writeValueAsString(map);
	}
	
	@RequestMapping(value="/mypage/changeNick", method=RequestMethod.POST)
	@ResponseBody
	public void changeNick(@RequestParam int id, @RequestParam String nick)
	{
		UserVO user = new UserVO();
		user.setId(id);
		user.setNick(nick);		
		
		int res = userDaoImpl.updateNick(user);
		if(res == 1) System.out.println("complete");
	}
}
