package com.team4.bookreview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team4.bookreview.util.ReviewQueryResRenderer;

@Controller
public class reviewController {
	@Autowired
	private ReviewQueryResRenderer renderer;
	 	
	@RequestMapping(value="/review/insert", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String reviewInsert(@RequestParam String data) {
        System.out.println("=========== [/review/insert] request ==========");
		String JSONValue = renderer.getInsertRes(data);
		System.out.println(JSONValue);
		return JSONValue;
	}

	@RequestMapping(value="/review/get", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String reviewSelect(@RequestParam String data)  {
		System.out.println("=========== [/review/get] request ==========");
		String JSONValue = renderer.getSelectRes(data);
		System.out.println(JSONValue);
		return JSONValue;
	}
	@RequestMapping(value="/book/review/get", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String reviewSelectByBook(@RequestParam String data)  {
		System.out.println("=========== [/book/review/get] request ==========");
		String JSONValue = renderer.getReviewByBookRes(data);
		System.out.println(JSONValue);
		return JSONValue;
	}

	
	@RequestMapping(value="/review/delete", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String reviewDelete(@RequestParam String data)  {
        System.out.println("=========== [/review/delete] request ==========");
		String JSONValue = renderer.getDeleteRes(data);
		System.out.println(JSONValue);
		return JSONValue;
		
	}

	@RequestMapping(value="/review/update", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String reviewUpdate(@RequestParam String data)  {
        System.out.println("=========== [/review/update] request ==========");
		String JSONValue = renderer.getUpdateRes(data);
		System.out.println(JSONValue);
		return JSONValue;
		
	}
	
	// public Timestamp getTimestamp(String str){
	// 	return Timestamp.valueOf(str);
	// }
	
}
