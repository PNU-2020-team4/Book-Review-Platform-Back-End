package com.team4.bookreview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team4.bookreview.util.CommentQueryResRenderer;

@Controller
public class CommentController {
	@Autowired
	private CommentQueryResRenderer renderer;
	
	@RequestMapping(value="/comment/insert", method=RequestMethod.POST)
	@ResponseBody
	public String writeComment(@RequestParam String data) {

		System.out.println("=============/comment/insert=============");
		System.out.println("data" + data);
		String resJson = renderer.getInsertRes(data);

		System.out.println("result : " + resJson);
		return resJson;
	}
	
	@RequestMapping(value="/comment/update", method=RequestMethod.POST)
	@ResponseBody
	public String updateComment(@RequestParam String data) {

		System.out.println("=============/comment/update=============");
		System.out.println("data : " + data);

		String resJson = renderer.getUpdateRes(data);
		
		System.out.println("result : " + resJson);
		return resJson;
	}
	
	@RequestMapping(value="/comment", produces="text/plain; charset=UTF-8", method=RequestMethod.POST)
	@ResponseBody
	public String showComments() {

		System.out.println("=============/comment=============");
		String resJson = renderer.getAllSelectRes();
		
		System.out.println("result : " + resJson);

		return resJson;
		
	}
	
	@RequestMapping(value="/comment/delete", method=RequestMethod.POST)
	@ResponseBody
	public String deleteComment(@RequestParam String data) {

		System.out.println("=============/comment/delete=============");
		System.out.println("data : " + data);
		String resJson = renderer.getDeleteRes(data);
		
		System.out.println("result : " + resJson);
		return resJson;
	}
	
	
}
