package com.team4.bookreview.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.team4.bookreview.daoImpl.PostDAOImpl;
import com.team4.bookreview.vo.PostVO;

import org.springframework.beans.factory.annotation.Autowired;

public class PostQueryResRenderer implements DBQueryResRenderer {
	@Autowired
	private ObjectMapper obj = new ObjectMapper();
	@Autowired
	private PostDAOImpl postDAOImpl;

	@Override
	public String getInsertRes(String data) throws JsonProcessingException, IOException {
		System.out.println("----- getInsertRes -----");
		PostVO post = obj.readValue(data, PostVO.class);
		HashMap<String, String> map = new HashMap<String, String>();
		int resultCode = 500;
		int result;
		try {
			// result would be idx of new row
			result = postDAOImpl.insert(post);
			System.out.println("Idx of new Row : " + result);
			resultCode = 100;
			map.put("post", obj.writeValueAsString(post));
		} catch (Exception e) {
			e.printStackTrace();
			resultCode = 200;
			map.put("message", "Something's wrong");
		}
		
		map.put("resultCode", String.valueOf(resultCode));
		System.out.println("return : " + obj.writeValueAsString(map));

		return obj.writeValueAsString(map);
	}

	@Override
	public String getDeleteRes(String data) throws JsonProcessingException, IOException {
		System.out.println("----- getDeleteRes -----");
		HashMap<String, String> map = new HashMap<String, String>();
		ObjectNode node = obj.readValue(data, ObjectNode.class);
		int idx = node.get("idx").asInt();
		int resultCode = 500;
		int result;
		try {
			result = postDAOImpl.delete(idx);
			if (result == 1) {
				resultCode = 100;
			} else {
				resultCode = 300;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultCode = 200;
			map.put("message", "Something's wrong");
		}
		map.put("resultCode", String.valueOf(resultCode));
		System.out.println("return : " + obj.writeValueAsString(map));

		return obj.writeValueAsString(map);
	}

	@Override
	public String getSelectRes(String data) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("----- getSelectRes -----");
		HashMap<String, String> map = new HashMap<String, String>();

		int resultCode = 500;

		ObjectNode node = obj.readValue(data, ObjectNode.class);
		if(node.get("idx") == null) {
			List<PostVO> result;
			try {
				result = postDAOImpl.selectAll();
				resultCode = 100;
				map.put("posts", obj.writeValueAsString(result));
			} catch (Exception e) {
				e.printStackTrace();
				resultCode = 200;
				map.put("message", "Something's wrong");
			}
			map.put("resultCode", String.valueOf(resultCode));
			System.out.println("return : " + obj.writeValueAsString(map));
	
			return obj.writeValueAsString(map);
		}

		int idx = node.get("idx").asInt();
		PostVO result = null;
		try {
			result = postDAOImpl.select(idx);
			if (result != null) {
				resultCode = 100;
				map.put("post", obj.writeValueAsString(result));
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultCode = 200;
			map.put("message", "Something's wrong");
		}

		map.put("resultCode", String.valueOf(resultCode));
		System.out.println("return : " + obj.writeValueAsString(map));

		return obj.writeValueAsString(map);
	}

	@Override
	public String getUpdateRes(String data) throws JsonProcessingException, IOException {
		System.out.println("----- getUpdateRes -----");

		PostVO post = obj.readValue(data, PostVO.class);
		HashMap<String, String> map = new HashMap<String, String>();
		int resultCode = 500;
		int result;
		try {
			result = postDAOImpl.update(post.getIdx(), post);
			resultCode = 100;
		} catch (Exception e) {
			e.printStackTrace();
			resultCode = 200;
			map.put("message", "Something's wrong");
		}
		
		map.put("resultCode", String.valueOf(resultCode));
		System.out.println("return : " + obj.writeValueAsString(map));

		return obj.writeValueAsString(map);
	}
}
