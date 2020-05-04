package com.team4.bookreview.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.team4.bookreview.daoImpl.ReviewDAOImpl;
import com.team4.bookreview.vo.ReviewVO;

public class ReviewQueryResRenderer implements DBQueryResRenderer {

	@Autowired
	private ObjectMapper obj = new ObjectMapper();
	@Autowired
	ReviewDAOImpl r;
	
	@Override
	public String getInsertRes(String data) throws IOException {
		HashMap<String, String> map = new HashMap<String, String>();
		ReviewVO record;
		record = obj.readValue(data, ReviewVO.class);
		int result;
		
		try {
			result = r.insert(record);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", "500");
			map.put("message", "Data not satisfied");
			System.out.println("Data not satisfied");
			System.out.println("Return : " + obj.writeValueAsString(map));
			return obj.writeValueAsString(map);
		}
		
		switch(result) {
		case 1:
			map.put("resultCode", "200");
			map.put("review", obj.writeValueAsString(record));
			System.out.println("Success");
			break;
		case 0:
			map.put("resultCode", "400");
			map.put("message", "DB Insertion error");
			System.out.println("DB Insertion error");
			break;
		default:
			map.put("resultCode", "300");
			map.put("message", "Internal Error");
			System.out.println("Return value is not 0 or 1");
		}

		return  obj.writeValueAsString(map);
	}

	@Override
	public String getDeleteRes(String data) throws IOException {
		HashMap<String, String> map = new HashMap<String, String>();
		
		ObjectNode nodes = obj.readValue(data, ObjectNode.class);
		int idx = nodes.get("idx").asInt();
		System.out.println("Received data : " + idx);
		
		int result;
		
		try {
			result = r.delete(idx);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", "500");
			map.put("message", "Data not satisfied");
			System.out.println("Data not satisfied");
			System.out.println("Return : " + obj.writeValueAsString(map));
			return obj.writeValueAsString(map);
		}

		switch(result) {
		case 1:
			map.put("resultCode", "200");
			System.out.println("Success");
			break;
		case 0:
			map.put("resultCode", "400");
			map.put("message", "DB Deletion error");
			System.out.println("DB Deletion error");
			break;
		default:
			map.put("resultCode", "300");
			map.put("message", "Internal Error");
			System.out.println("Return value is not 0 or 1");
		}
		
		String JSONValue =  obj.writeValueAsString(map);
		return JSONValue;
	}


	@Override
	public String getSelectRes(String data) throws JsonParseException, JsonMappingException, IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<HashMap<String, Object>> recordList = new ArrayList<HashMap<String, Object>>();
		ObjectNode nodes = obj.readValue(data, ObjectNode.class);
		int writer = nodes.get("writer").asInt();
		System.out.println("Received data : " + writer);
		List<ReviewVO> result;
		
		try {
			result = (r.selectByWriter(writer));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", "500");
			map.put("message", "Data not satisfied");
			System.out.println("Data not satisfied");
			System.out.println("Return : " + obj.writeValueAsString(map));
			return obj.writeValueAsString(map);
		}

		if(result == null) {
			map.put("resultCode", "400");
			map.put("message", "DB Selection Failure");
		}
		
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
			map.put("resultCode", "200");
			map.put("review list", recordList);
			System.out.println("Success");			
		}
		
		String JSONValue =  obj.writeValueAsString(map);
		return JSONValue;
	}


	@Override
	public String getUpdateRes(String data) throws IOException {
		// update content, date
		HashMap<String, String> map  = new HashMap<String, String>();
		ReviewVO record = obj.readValue(data, ReviewVO.class);
		
		int result;
		
		try {
			result = (r.update(record));
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resultCode", "500");
			map.put("message", "Data not satisfied");
			System.out.println("Data not satisfied");
			System.out.println("Return : " + obj.writeValueAsString(map));
			return obj.writeValueAsString(map);
		}
				
		switch(result) {
		case 1:
			map.put("resultCode", "200");
			map.put("review", obj.writeValueAsString(record));
			System.out.println("Success");
			break;
		case 0:
			map.put("resultCode", "400");
			map.put("message", "DB Update error");
			System.out.println("DB Update error");
			break;
		default:
			map.put("resultCode", "300");
			map.put("message", "Internal Error");
			System.out.println("Return value is not 0 or 1");
		}
		
		String JSONValue =  obj.writeValueAsString(map);
		System.out.println(JSONValue);
		return JSONValue;
	}


}
