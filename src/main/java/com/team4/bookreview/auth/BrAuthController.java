package com.team4.bookreview.auth;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.team4.bookreview.daoImpl.UserDAOImpl;
import com.team4.bookreview.vo.UserVO;

@Controller 
public class BrAuthController {
	@Autowired
	UserDAOImpl user;
	@Autowired
	BrAuth auth;
	
	@RequestMapping(value="/BrAuth", method = RequestMethod.GET)
	public String authenticate(Model model) {
		
		List<UserVO> res = user.selectAll();
		
		for(UserVO vo : res) {
			System.out.println(vo.getId());
		}
		return "auth";
	}	
	
	
	@RequestMapping(value="/BrAuth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String authenticate(HttpServletRequest req, Model model) throws JsonProcessingException {

		int id = Integer.parseInt(req.getParameter("id"));
		System.out.println(auth.doService(id));	
		model.addAttribute("id", id);
		return "auth";
	}
	
	
}
