package com.team4.bookreview.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.team4.bookreview.model.Response;
import com.team4.bookreview.util.BookQueryResRenderer;
import com.team4.bookreview.util.ErrorMsg;
import com.team4.bookreview.util.HistoryQueryResRenderer;
import com.team4.bookreview.vo.BookVO;
import com.team4.bookreview.vo.HistoryVO;

@Controller
public class HistController {
	private static final Logger logger = LoggerFactory.getLogger(HistController.class);
	
	@Autowired
	private HistoryQueryResRenderer renderer;
	@Autowired
	private BookQueryResRenderer brenderer;
	
	@RequestMapping(value="/hist/showAll", method=RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String showAllByUser(@RequestParam String data) {
		logger.info("=============[/hist/showAll] request ===============");
		logger.info(data);
		
		String jsonValue = renderer.getSelectAllByUserRes(data);
		logger.info(jsonValue);
		
		return jsonValue;
	}
	
	@RequestMapping(value="/hist/insert", method=RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String histInsert(@RequestParam String data) {
		logger.info("=============[/hist/insert] request ===============");
		logger.info(data);
		ObjectMapper obj = new ObjectMapper();
		String name;
		String author;
		int idx;
		int userId; 
		
		try {
			ObjectNode node = obj.readValue(data, ObjectNode.class);
			idx = node.get("idx").asInt();
			name = node.get("title").asText();
			author = node.get("author").asText();
			userId = node.get("id").asInt();
		} catch (Exception e) {
			logger.info("Request Failed");
			logger.error(ErrorMsg.ERROR_STRING, e);
			return new Response().toJsonString();
		} 
		
		logger.info("Book IDX : " + idx);
		logger.info("Book Name : " + name);
		logger.info("Book Author : " + author);
		logger.info("User ID : " + userId);
		
		
		BookVO bv = new BookVO();
		bv.setIdx(idx);
		bv.setAuthor(author);
		bv.setName(name);
		int bookInsertResult = brenderer.getInsertNoDup(bv); 
		
		logger.info("Book Insert Result : " + bookInsertResult);
		if(bookInsertResult == 0) 	logger.info(name + " " + author + " is Already Exist In br.book");
	
		HistoryVO hv= new HistoryVO();
	
		hv.setBook(idx);
		hv.setUser(userId);
		String jsonValue = renderer.getInsertResByRecord(hv); 
		logger.info(jsonValue);

		return jsonValue;
	}
	
	@RequestMapping(value="/hist/delete", method=RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String histDelete(@RequestParam String data) {
		logger.info("=============[/hist/delete] request ===============");
		logger.info(data);
		String jsonValue = renderer.getDeleteRes(data);
		logger.info(jsonValue);
		return jsonValue;
	}
	
}
