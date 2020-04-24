package com.team4.bookreview.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.team4.bookreview.daoImpl.UserDAOImpl;
import com.team4.bookreview.vo.UserVO;

@Controller
public class MyPageController {
	
	@Autowired
	private UserDAOImpl userDaoImpl;
	
	@RequestMapping(value="/mypage", method=RequestMethod.POST)
	public void seeMyPage(@RequestParam int id) {
		UserVO user = userDaoImpl.select(id);
		
		System.out.println(user);
	}
}
