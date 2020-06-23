package com.team4.bookreview.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team4.bookreview.util.CommentQueryResRenderer;

@Controller
public class CommentController {
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	@Autowired
	private CommentQueryResRenderer renderer;
	
	@RequestMapping(value="/comment/insert", method=RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String writeComment(@RequestParam String data) {
		logger.info("=============/comment/insert=============");
		logger.info("data" + data);
		String resJson = renderer.getInsertRes(data);
		logger.info(resJson);
		return resJson;
	}
	
	@RequestMapping(value="/comment/update", method=RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String updateComment(@RequestParam String data) {
		logger.info("=============/comment/update=============");
		logger.info("data : " + data);
		String resJson = renderer.getUpdateRes(data);
		logger.info(resJson);
		return resJson;
	}
	
	@RequestMapping(value="/comment", produces="text/plain; charset=UTF-8", method=RequestMethod.POST)
	@ResponseBody
	public String showComments() {

		logger.info("=============/comment=============");
		String resJson = renderer.getAllSelectRes();
		
		logger.info(resJson);

		return resJson;
		
	}
	
	@RequestMapping(value="/comment/delete", method=RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String deleteComment(@RequestParam String data) {
		logger.info("=============/comment/delete=============");
		logger.info("data : " + data);
		String resJson = renderer.getDeleteRes(data);
		logger.info(resJson);
		return resJson;
	}
	
	
}
