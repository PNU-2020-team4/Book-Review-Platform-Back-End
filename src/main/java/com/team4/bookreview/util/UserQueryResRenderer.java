package com.team4.bookreview.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.bookreview.daoImpl.UserDAOImpl;
import com.team4.bookreview.model.Response;
import com.team4.bookreview.vo.UserVO;


@Service
public class UserQueryResRenderer implements DBQueryResRenderer {
	
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
		Response r = new Response();
		UserVO user = (UserVO) r.readValue(data, UserVO.class);
		
		System.out.println(user.toString());
	
		user.setHist_cnt(0);
		int result = 0;
		
		try {
			result = userDaoImpl.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			r.setResultCode(500);
			r.setMessage("Data not satisfied");
			return r.toJsonString();
		}
		
		switch(result) {
		case 1:
			r.setResultCode(100);
			r.setData(user);
			System.out.println("Success");
			break;
		case 0:
			r.setResultCode(400);
			r.setMessage("DB Insertion Error");
			break;
		default:
			r.setResultCode(400);
			r.setMessage("Internal Error");
			System.out.println("Return value is not 0 or 1");
		}

		return r.toJsonString();
	}



}
