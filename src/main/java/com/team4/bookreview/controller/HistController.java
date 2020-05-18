package com.team4.bookreview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team4.bookreview.util.HistoryQueryResRenderer;

@Controller
public class HistController {
	@Autowired
	private HistoryQueryResRenderer renderer;
	
	@RequestMapping(value="/hist/showAll", method=RequestMethod.POST)
	@ResponseBody
	public String ShowAllByUser(@RequestParam String data) {
		System.out.println("=============[/hist/showAll] request ===============");
		System.out.println("data : " + data);
		
		String JSONValue = renderer.getSelectAllByUserRes(data);
		System.out.println("Return : " + JSONValue);
		
		return JSONValue;
	}
	
	@RequestMapping(value="/hist/insert", method=RequestMethod.POST)
	@ResponseBody
	public String HistInsert(@RequestParam String data) {
		System.out.println("=============[/hist/HistInsert] request ===============");
		System.out.println("data : " + data);
		
		String JSONValue = renderer.getInsertRes(data);
		System.out.println("Return : " + JSONValue);
		
		return JSONValue;
	}
	
	@RequestMapping(value="/hist/delete", method=RequestMethod.POST)
	@ResponseBody
	public String HistDelete(@RequestParam String data) {
		System.out.println("=============[/hist/HistDelete] request ===============");
		System.out.println("data : " + data);
		
		String JSONValue = renderer.getDeleteRes(data);
		System.out.println("Return : " + JSONValue);
		
		return JSONValue;
	}
	
}
