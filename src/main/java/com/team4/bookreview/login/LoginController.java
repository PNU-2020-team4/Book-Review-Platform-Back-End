package com.team4.bookreview.login;

import java.io.IOException;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team4.bookreview.daoImpl.UserDAOImpl;
import com.team4.bookreview.vo.UserVO;

@Controller
public class LoginController {
	
	
	@Autowired
	private UserDAOImpl userDaoImpl;
	
	private String apiResult = null;
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(@RequestParam String data) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("=========== [/login] request ==========");
		System.out.println("data : " + data);
		ObjectMapper objectMapper = new ObjectMapper();
		UserVO user = objectMapper.readValue(data, UserVO.class);
		
		HashMap<String, String> map = new HashMap<String, String>();
		ObjectMapper obj = new ObjectMapper();
		
		user.setHist_cnt(0);
		int result = 0;
		
		try {
			result = userDaoImpl.updateUser(user);
		} catch (Exception e) {
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
		
		System.out.println("Return : " + obj.writeValueAsString(map));
		return obj.writeValueAsString(map);
	}
}