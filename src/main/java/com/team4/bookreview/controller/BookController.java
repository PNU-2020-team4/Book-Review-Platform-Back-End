package com.team4.bookreview.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team4.bookreview.util.BookQueryResRenderer;

@Controller
public class BookController {
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);
	
    @Autowired
	private BookQueryResRenderer renderer;
	 	
	@RequestMapping(value="/book/insert", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String bookInsert(@RequestParam String data)  {
        logger.info("=========== [/book/insert] request ==========");
		String JSONValue = renderer.getInsertRes(data);
		logger.info(JSONValue);
		return JSONValue;
	}
	
	@RequestMapping(value="/book/get", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody
	public String reviewSelect() {
        logger.info("=========== [/book/get] request ==========");
        String JSONValue = renderer.getSelectRes("");
        logger.info("Return : " + JSONValue);
        return JSONValue;
	}
	
	@RequestMapping(value="/book/getone", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String reviewSelect(@RequestParam String data)  {
        logger.info("=========== [/book/getone] request ==========");
		String JSONValue = renderer.getSelectRes(data);
		logger.info(JSONValue);
		return JSONValue;
	}
	
	@RequestMapping(value="/book/delete", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String reviewDelete(@RequestParam String data)  {
        logger.info("=========== [/book/delete] request ==========");
		String JSONValue = renderer.getDeleteRes(data);
		logger.info(JSONValue);
		return JSONValue;
		
	}

	@RequestMapping(value="/book/update", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String reviewUpdate(@RequestParam String data)  {
        logger.info("=========== [/book/update] request ==========");
		String JSONValue = renderer.getUpdateRes(data);
		logger.info(JSONValue);
		return JSONValue;
	}

	@RequestMapping(value="/book/search/author", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String searchByAuthor(@RequestParam String data)  {
        logger.info("=========== [/book/search/author] request ==========");
		String JSONValue = renderer.getSearchByAuthorRes(data);
		logger.info(JSONValue);
		return JSONValue;
	}
	
	@RequestMapping(value="/book/search/name", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String searchByBookName(@RequestParam String data)  {
        logger.info("=========== [/book/search/name] request ==========");
		String JSONValue = renderer.getSearchByNameRes(data);
		logger.info(JSONValue);
		return JSONValue;
	}
	@RequestMapping(value="/recommend/user/review", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String getUserReviewBasedRecommend(@RequestParam String data)  {
        logger.info("=========== [/recommend/user/review] request ==========");
		String JSONValue = renderer.getSearchByUserReview(data);
		logger.info(JSONValue);
		return JSONValue;
	}
	
	@RequestMapping(value="/recommend/user/history", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String getUserHistoryBasedRecommend(@RequestParam String data)  {
        logger.info("=========== [/recommend/user/history] request ==========");
		String JSONValue = renderer.getSearchByUserHistory(data);
		logger.info(JSONValue);
		return JSONValue;
	}
	
	@RequestMapping(value="/recommend/database", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String getDataBasedRecommend(@RequestParam String data) {
        logger.info("=========== [/recommend/database] request ==========");
		String JSONValue = renderer.getSearchByData(data);
		logger.info(JSONValue);
		return JSONValue;
	}
}
