package com.team4.bookreview.login;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.team4.bookreview.api.NaverLoginBO;
import com.team4.bookreview.daoImpl.UserDAOImpl;
import com.team4.bookreview.vo.UserVO;

@Controller
public class LoginController {
	
	@Autowired
	private NaverLoginBO naverLoginBO;
	
	@Autowired
	private UserDAOImpl userDaoImpl;
	
	private String apiResult = null;
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(@RequestParam String data) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		UserVO user = objectMapper.readValue(data, UserVO.class);
		
		
	
		
		return "login";
	}
}