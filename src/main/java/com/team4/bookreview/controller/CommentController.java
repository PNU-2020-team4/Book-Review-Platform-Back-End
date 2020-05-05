package com.team4.bookreview.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.team4.bookreview.daoImpl.CommentDAOImpl;
import com.team4.bookreview.vo.CommentVO;

@Controller
public class CommentController {
	@Autowired
	private CommentDAOImpl commentDAOImpl;
	
	@RequestMapping(value="/comment/insert")
	public void writeComment(@RequestParam String data) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		CommentVO commentVO = objectMapper.readValue(data,  CommentVO.class);

		commentDAOImpl.insertComment(commentVO);
	}
	
	@RequestMapping(value="/comment/update")
	@ResponseBody
	public String updateComment(@RequestParam String content, @RequestParam int id, @RequestParam int cmt_no, @RequestParam int post) throws JsonProcessingException {
		HashMap<String, Object> updateMap = new HashMap<String, Object>();
		updateMap.put("content", content);
		updateMap.put("writer", id);
		updateMap.put("cmt_no", cmt_no);
		updateMap.put("post", post);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		int updated_col = 0;
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		
		try {
			updated_col = commentDAOImpl.updateComment(updateMap);
		} catch (Exception e) {
			System.out.println("Error occured");
		}
		
				
		switch(updated_col) {
		case 1:
			resMap.put("resultCode", "200");
			resMap.put("message", "DB Update Complete");
			System.out.println("Success");
			break;
		case 0:
			resMap.put("resultCode", "400");
			resMap.put("message", "DB Update Error");
			break;
		default:
			resMap.put("resultCode", "300");
			resMap.put("message", "Internal Error");
			System.out.println("Return value is not 0 or 1");
		}
		
		return objectMapper.writeValueAsString(resMap);
	}
	
	@RequestMapping(value="/comment", produces="text/plain; charset=UTF-8")
	@ResponseBody
	public String showComments() {
		List<CommentVO> listComment = commentDAOImpl.selectAll();
		
		for(CommentVO cmt : listComment) {
			if(cmt.getdelFlag()) cmt.setContent("삭제된 댓글입니다.");
		}
		
		Gson gson = new Gson();
		
		return gson.toJson(listComment);
		
	}
	
	@RequestMapping(value="/comment/delete")
	@ResponseBody
	public void deleteComment(@RequestParam int id, @RequestParam int post, @RequestParam int cmt_no) throws JsonProcessingException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("writer",  id);
		map.put("post", post);
		map.put("cmt_no", cmt_no);
		map.put("delFlag", true);
		
		ObjectMapper obj = new ObjectMapper();
		
		try {
			commentDAOImpl.deleteComment(map);
		} catch (Exception e) {
			System.out.println("Some Error occured");
		}
		
	}
	
	
}
