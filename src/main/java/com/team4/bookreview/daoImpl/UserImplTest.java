package com.team4.bookreview.daoImpl;

import java.sql.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.team4.bookreview.vo.UserVO;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"classpath:root-context.xml"})
public class UserImplTest {

	@Autowired
	private UserDAOImpl userDaoImpl;
	
	@Test
	public void test() {
		UserVO uv = new UserVO();
		
		uv.setAge("11");
//		uv.setBirth("dfdf");
		uv.setEmail("test@naver.com");
		uv.setGender("M");
		uv.setHist_cnt(0);
		uv.setId(335);
		uv.setName("����");
		uv.setNick("jjang");
		
//		userDaoImpl.insert(uv);
		
		List<UserVO> lu = userDaoImpl.selectAll();
		for(UserVO user : lu) System.out.println(user);
	}

}
