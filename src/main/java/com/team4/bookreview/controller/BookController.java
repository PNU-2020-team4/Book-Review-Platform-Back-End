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
		String jsonValue = renderer.getInsertRes(data);
		logger.info(jsonValue);
		return jsonValue;
	}
	
	@RequestMapping(value="/book/get", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody
	public String reviewSelect() {
        logger.info("=========== [/book/get] request ==========");
        String jsonValue = renderer.getSelectRes("");
        logger.info(jsonValue);
        return jsonValue;
	}
	
	@RequestMapping(value="/book/getone", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String reviewSelect(@RequestParam String data)  {
        logger.info("=========== [/book/getone] request ==========");
		String jsonValue = renderer.getSelectRes(data);
		logger.info(jsonValue);
		return jsonValue;
	}
	
	@RequestMapping(value="/book/delete", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String reviewDelete(@RequestParam String data)  {
        logger.info("=========== [/book/delete] request ==========");
		String jsonValue = renderer.getDeleteRes(data);
		logger.info(jsonValue);
		return jsonValue;
		
	}

	@RequestMapping(value="/book/update", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String reviewUpdate(@RequestParam String data)  {
        logger.info("=========== [/book/update] request ==========");
		String jsonValue = renderer.getUpdateRes(data);
		logger.info(jsonValue);
		return jsonValue;
	}

	@RequestMapping(value="/book/search/author", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String searchByAuthor(@RequestParam String data)  {
        logger.info("=========== [/book/search/author] request ==========");
		String jsonValue = renderer.getSearchByAuthorRes(data);
		logger.info(jsonValue);
		return jsonValue;
	}
	
	@RequestMapping(value="/book/search/name", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String searchByBookName(@RequestParam String data)  {
        logger.info("=========== [/book/search/name] request ==========");
		String jsonValue = renderer.getSearchByNameRes(data);
		logger.info(jsonValue);
		return jsonValue;
	}
	

	
	@RequestMapping(value="/recommend/user/review", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String getUserReviewBasedRecommend(@RequestParam String data)  {
        logger.info("=========== [/recommend/user/review] request ==========");
		String jsonValue = renderer.getSearchByUserReview(data);
		logger.info(jsonValue);
		return jsonValue;
	}
	
	@RequestMapping(value="/recommend/user/history", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String getUserHistoryBasedRecommend(@RequestParam String data)  {
        logger.info("=========== [/recommend/user/history] request ==========");
		String jsonValue = renderer.getSearchByUserHistory(data);
		logger.info(jsonValue);
		return jsonValue;
	}
	
	@RequestMapping(value="/recommend/database", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String getDataBasedRecommend(@RequestParam String data) {
        logger.info("=========== [/recommend/database] request ==========");
		String jsonValue = renderer.getSearchByData(data);
		logger.info(jsonValue);
		return jsonValue;
	}
}
