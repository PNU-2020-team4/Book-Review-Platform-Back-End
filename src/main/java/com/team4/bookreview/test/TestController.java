package com.team4.bookreview.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team4.bookreview.daoImpl.ReviewDAOImpl;
import com.team4.bookreview.vo.ReviewVO;

@Controller
public class TestController {
	@Autowired
	private TestService service;
	
	
	
	@RequestMapping(value="/selTest")
	public void selTest() {
		String result = service.selectNow();
		System.out.println(result);
	}
	
	@RequestMapping(value="/showTable")
	public void showTable() {
		String[] result = service.showTable();
		for(String table : result) System.out.println(table);
	}
	
}
