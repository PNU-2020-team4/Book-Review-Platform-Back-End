package com.team4.bookreview.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team4.bookreview.daoImpl.ReviewDAOImpl;
import com.team4.bookreview.vo.ReviewVO;

@Controller
public class reviewController {
	@Autowired
	ReviewDAOImpl r;

	ObjectMapper obj = new ObjectMapper();
	
	@RequestMapping(value="/reviewInsert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String reviewInsert(HttpServletRequest req) throws JsonProcessingException {
		
		HashMap<String, String> map = new HashMap<String, String>();
		ReviewVO record = renderVO(req);
		int result = r.insert(record);
		if(result != 0)
			map.put("result", "true");
		else map.put("result", "false");
		
		String JSONValue =  obj.writeValueAsString(map);
		System.out.println(JSONValue);
		return JSONValue;
	}

//	@RequestMapping(value="/reviewInsertTest", method = RequestMethod.GET)
//	public void reviewInsertTest() {
//		
//		ReviewVO record = new ReviewVO(1, "³»¿ë", 1, 3, "2020-04-24");
//		int result = r.insert(record);
//		if(result != 0)
//			System.out.println("ok");
//		else 		System.out.println("x");
//
//
//	}
	
	@RequestMapping(value="/reviewSelect", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String reviewSelect(HttpServletRequest req) throws JsonProcessingException {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String, Object>> recordList = new ArrayList<HashMap<String, Object>>();
		
		int writer = Integer.parseInt(req.getParameter("writer"));
		System.out.println("Received data : " + writer);
		List<ReviewVO> result = (r.selectByWriter(writer));
		
		
		if(result == null) map.put("result", "false");
		else {
			System.out.println("Queried data : " + result.size());

			for(ReviewVO record : result) {
				System.out.println(record);

				HashMap<String, Object> rec = new HashMap<String, Object>();
				rec.put("idx", record.getIdx());
				rec.put("writer", record.getWriter());
				rec.put("content", record.getContent());
				rec.put("star", record.getStar());
				rec.put("book", record.getBook());
				rec.put("date", record.getDate());
				recordList.add(rec);
			}
			map.put("result", "true");
			map.put("record list", recordList);
			
		}
		
		String JSONValue =  obj.writeValueAsString(map);
		System.out.println(JSONValue);
		return JSONValue;
	}
	
//	@RequestMapping(value="/reviewDelete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public String reviewDelete() {
//	
//		
//	}
//
//	@RequestMapping(value="/reviewUp)date", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public String reviewUpdate() {
//	
//		
//	}
	
	private ReviewVO renderVO(HttpServletRequest req) {
		return new ReviewVO(Integer.parseInt(req.getParameter("writer")), req.getParameter("content"),
							Integer.parseInt(req.getParameter("book")), Integer.parseInt(req.getParameter("star")),
							getTimestamp(req.getParameter("date")));
	}
	
	public Timestamp getTimestamp(String str){
		return Timestamp.valueOf(str);
	}
	
}
