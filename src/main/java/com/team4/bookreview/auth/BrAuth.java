package com.team4.bookreview.auth;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team4.bookreview.daoImpl.UserDAOImpl;

@Service
public class BrAuth implements RequestAuth {
	@Autowired 
	private UserDAOImpl user;
	private int id;
	
	@Override
	public boolean authenticate() {
		// TODO Auto-generated method stub
		return (user.selectID(id) != null);
	}
	
	public String doService(int id) throws JsonProcessingException {
		this.id = id;
		
		HashMap<String, String> map = new HashMap<String, String>();
		ObjectMapper obj = new ObjectMapper();
		
		boolean result = authenticate();
		if(result) {
			System.out.println("Found ID : " + id);
			map.put("authenticated", "true");
		}
		else 
			map.put("authenticated", "false");
		return obj.writeValueAsString(map);
	}

}
