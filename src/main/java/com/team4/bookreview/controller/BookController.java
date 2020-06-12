package com.team4.bookreview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team4.bookreview.util.BookQueryResRenderer;

@Controller
public class BookController {
	
    @Autowired
	private BookQueryResRenderer renderer;
	 	
	@RequestMapping(value="/book/insert", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String bookInsert(@RequestParam String data)  {
        System.out.println("=========== [/book/insert] request ==========");
		String JSONValue = renderer.getInsertRes(data);
		System.out.println(JSONValue);
		return JSONValue;
	}
	
	@RequestMapping(value="/book/get", method = RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody
	public String reviewSelect() {
        System.out.println("=========== [/book/get] request ==========");
        String JSONValue = renderer.getSelectRes("");
        System.out.println("Return : " + JSONValue);
        return JSONValue;
	}
	
	@RequestMapping(value="/book/getone", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String reviewSelect(@RequestParam String data)  {
        System.out.println("=========== [/book/getone] request ==========");
		String JSONValue = renderer.getSelectRes(data);
		System.out.println(JSONValue);
		return JSONValue;
	}
	
	@RequestMapping(value="/book/delete", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String reviewDelete(@RequestParam String data)  {
        System.out.println("=========== [/book/delete] request ==========");
		String JSONValue = renderer.getDeleteRes(data);
		System.out.println(JSONValue);
		return JSONValue;
		
	}

	@RequestMapping(value="/book/update", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String reviewUpdate(@RequestParam String data)  {
        System.out.println("=========== [/book/update] request ==========");
		String JSONValue = renderer.getUpdateRes(data);
		System.out.println(JSONValue);
		return JSONValue;
	}

	@RequestMapping(value="/book/search/author", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String searchByAuthor(@RequestParam String data)  {
        System.out.println("=========== [/book/search/author] request ==========");
		String JSONValue = renderer.getSearchByAuthorRes(data);
		System.out.println(JSONValue);
		return JSONValue;
	}
	
	@RequestMapping(value="/book/search/name", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String searchByBookName(@RequestParam String data)  {
        System.out.println("=========== [/book/search/name] request ==========");
		String JSONValue = renderer.getSearchByNameRes(data);
		System.out.println(JSONValue);
		return JSONValue;
	}
	@RequestMapping(value="/recommend/user/review", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String getUserReviewBasedRecommend(@RequestParam String data)  {
        System.out.println("=========== [/recommend/user/review] request ==========");
		String JSONValue = renderer.getSearchByUserReview(data);
		System.out.println(JSONValue);
		return JSONValue;
	}
	
	@RequestMapping(value="/recommend/user/history", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String getUserHistoryBasedRecommend(@RequestParam String data)  {
        System.out.println("=========== [/recommend/user/history] request ==========");
		String JSONValue = renderer.getSearchByUserHistory(data);
		System.out.println(JSONValue);
		return JSONValue;
	}
	
	@RequestMapping(value="/recommend/database", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String getDataBasedRecommend(@RequestParam String data) {
        System.out.println("=========== [/recommend/database] request ==========");
		String JSONValue = renderer.getSearchByData(data);
		System.out.println(JSONValue);
		return JSONValue;
	}
}
