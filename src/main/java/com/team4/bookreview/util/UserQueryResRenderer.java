package com.team4.bookreview.util;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team4.bookreview.daoImpl.ReviewDAOImpl;
import com.team4.bookreview.daoImpl.UserDAOImpl;
import com.team4.bookreview.vo.UserVO;

public class UserQueryResRenderer implements DBQueryResRenderer {

	@Autowired
	private ObjectMapper obj = new ObjectMapper();
	@Autowired
	UserDAOImpl userDaoImpl;
	
	@Override
	public String getInsertRes(String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeleteRes(String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSelectRes(String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUpdateRes(String data) {
		UserVO user = obj.readValue(data, UserVO.class);
		
		System.out.println(user.toString());
	
		HashMap<String, String> map = new HashMap<String, String>();
		ObjectMapper obj = new ObjectMapper();
		
		user.setHist_cnt(0);
		int result = 0;
		
		try {
			result = userDaoImpl.updateUser(user);
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
			map.put("user", obj.writeValueAsString(user));
			System.out.println("Success");
			break;
		case 0:
			map.put("resultCode", "400");
			System.out.println("DB Insertion error");
			break;
		default:
			map.put("resultCode", "300");
			map.put("message", "Internal Error");
			System.out.println("Return value is not 0 or 1");
		}

		String JSONValue =  obj.writeValueAsString(map);
		return JSONValue;
		
	}



}
