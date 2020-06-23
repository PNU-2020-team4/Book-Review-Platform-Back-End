package com.team4.bookreview.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team4.bookreview.util.ReviewQueryResRenderer;

@Controller
public class reviewController {
	private static final Logger logger = LoggerFactory.getLogger(reviewController.class);

	@Autowired
	private ReviewQueryResRenderer renderer;
	 	
	@RequestMapping(value="/review/insert", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String reviewInsert(@RequestParam String data) {
        logger.info("=========== [/review/insert] request ==========");
		String JSONValue = renderer.getInsertRes(data);
		logger.info(JSONValue);
		return JSONValue;
	}

	
	@RequestMapping(value="/review/get", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String reviewSelect(@RequestParam String data)  {
		logger.info("=========== [/review/get] request ==========");
		String JSONValue = renderer.getSelectRes(data);
		logger.info(JSONValue);
		return JSONValue;
	}
	@RequestMapping(value="/book/review/get", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String reviewSelectByBook(@RequestParam String data)  {
		logger.info("=========== [/book/review/get] request ==========");
		String JSONValue = renderer.getReviewByBookRes(data);
		logger.info(JSONValue);
		return JSONValue;
	}

	
	@RequestMapping(value="/review/delete", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String reviewDelete(@RequestParam String data)  {
        logger.info("=========== [/review/delete] request ==========");
		String JSONValue = renderer.getDeleteRes(data);
		logger.info(JSONValue);
		return JSONValue;
		
	}

	@RequestMapping(value="/review/update", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String reviewUpdate(@RequestParam String data)  {
        logger.info("=========== [/review/update] request ==========");
		String JSONValue = renderer.getUpdateRes(data);
		logger.info(JSONValue);
		return JSONValue;
		
	}
	
	// public Timestamp getTimestamp(String str){
	// 	return Timestamp.valueOf(str);
	// }
	
}
