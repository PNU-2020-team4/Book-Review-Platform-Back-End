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
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping(value="/login")
	public String login(Model model, HttpSession session) {
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		
		System.out.println("네이버 : " + naverAuthUrl);
		
		model.addAttribute("url", naverAuthUrl);
		
		return "login";
	}
	
	@RequestMapping(value="/callback", produces="application/json; charset=UTF-8")
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException, ParseException {
		System.out.println("callback");
		OAuth2AccessToken oauthToken;
		
		oauthToken = naverLoginBO.getAccessToken(session,  code,  state);
		
		apiResult = naverLoginBO.getUserProfile(oauthToken);
		
		System.out.println(apiResult);
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
		
		
		UserVO uv = new UserVO();
		setUserVO(response_obj, uv);
		
//		if(userDaoImpl.selectID(uv.getId())==null) 
//			userDaoImpl.insert(uv);
		
		userDaoImpl.updateUser(uv);
		
		
		System.out.println(uv.getName());
		
		session.setAttribute("sessionId",  uv.getNick());
		
		model.addAttribute("result", apiResult);
		
		return "login";
		
	}
	
	@RequestMapping(value="/showUser")
	public void showUser() {
		List<UserVO> lu = userDaoImpl.selectAll();
		
		for(UserVO user : lu) System.out.println(user);
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session)throws IOException {
		System.out.println("여기는 logout");
		session.invalidate();
		return "redirect:index.jsp";
	}
	
	private void setUserVO(JSONObject response_obj, UserVO uv) {
		String nickname = (String)response_obj.get("nickname");
		int id = Integer.parseInt((String)response_obj.get("id"));
		String age = (String)response_obj.get("age");
		char gender = ((String)response_obj.get("gender")).charAt(0);
		String email = (String)response_obj.get("email");
		String birth = (String)response_obj.get("birthday");
		int hist_cnt = 0;
		String name = (String)response_obj.get("name");
		
		uv.setId(id);
		uv.setBirthday(birth);
		uv.setEmail(email);
		uv.setHist_cnt(hist_cnt);
		uv.setName(name);
		uv.setAge(age);
		uv.setGender(gender);
		uv.setNick(nickname);
	}

	
	
}