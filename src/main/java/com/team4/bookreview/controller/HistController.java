package com.team4.bookreview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.team4.bookreview.daoImpl.UserDAOImpl;
import com.team4.bookreview.model.Response;
import com.team4.bookreview.util.BookQueryResRenderer;
import com.team4.bookreview.util.HistoryQueryResRenderer;
import com.team4.bookreview.vo.BookVO;
import com.team4.bookreview.vo.HistoryVO;

@Controller
public class HistController {
	@Autowired
	private HistoryQueryResRenderer renderer;
	@Autowired
	private BookQueryResRenderer brenderer;
	
	@RequestMapping(value="/hist/showAll", method=RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String ShowAllByUser(@RequestParam String data) {
		System.out.println("=============[/hist/showAll] request ===============");
		System.out.println("data : " + data);
		
		String JSONValue = renderer.getSelectAllByUserRes(data);
		System.out.println("Return : " + JSONValue);
		
		return JSONValue;
	}
	
	@RequestMapping(value="/hist/insert", method=RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String HistInsert(@RequestParam String data) {
		System.out.println("=============[/hist/HistInsert] request ===============");
		System.out.println("data : " + data);
		String JSONValue;
		ObjectMapper obj = new ObjectMapper();
		String name;
		String author;
		int userId; 
		
		try {
			ObjectNode node = obj.readValue(data, ObjectNode.class);
			name = node.get("name").asText();
			author = node.get("author").asText();
			userId = node.get("id").asInt();
		} catch (Exception e) {
			System.out.println("Request Failed");
			e.printStackTrace();
			return new Response().toJsonString();
		} 
		
		System.out.println("Book Name : " + name);
		System.out.println("Book Author : " + author);
		System.out.println("User ID : " + userId);
		
		
		BookVO bv = new BookVO();
		bv.setAuthor(author);
		bv.setName(name);
		int bookInsertResult = brenderer.getInsertNoDup(bv); 
		
		System.out.println("Book Insert Result : " + bookInsertResult);
		if(bookInsertResult == 0) 	System.out.println(name + " " + author + " is Already Exist In br.book");
	
		int bookId = brenderer.getIndexByNameAndAuthorRes(name, author);
		System.out.println("Book Indx : " + bookId);

		//data : userid, bookidx, date
		if(bookId == -1) {
			JSONValue = new Response().toJsonString(); // ERROR
		}
		else{
			HistoryVO hv= new HistoryVO();
		
			hv.setBook(bookId);
			hv.setUser(userId);
			JSONValue = renderer.getInsertResByRecord(hv); // if(new book & user):insert , else : update Date
			System.out.println("Return : " + JSONValue);
		}
		return JSONValue;
	}
	
	@RequestMapping(value="/hist/delete", method=RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public String HistDelete(@RequestParam String data) {
		System.out.println("=============[/hist/HistDelete] request ===============");
		System.out.println("data : " + data);
		
		String JSONValue = renderer.getDeleteRes(data);
		System.out.println("Return : " + JSONValue);
		
		return JSONValue;
	}
	
}
