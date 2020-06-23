package com.team4.bookreview.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.team4.bookreview.daoImpl.UserDAOImpl;
import com.team4.bookreview.model.Response;
import com.team4.bookreview.vo.UserVO;


@Service
public class UserQueryResRenderer implements DBQueryResRenderer {
	private static final Logger logger = LoggerFactory.getLogger(UserQueryResRenderer.class);
	
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
		Gson gson = new Gson();
		Response r = new Response();
		UserVO user = gson.fromJson(data,  UserVO.class);
		
		UserVO selected_user = null;
		
		try {
			selected_user = userDaoImpl.select(user.getId());
		} catch(Exception e) {
			e.printStackTrace();
			r.setResultCode(500);
			r.setMessage("Can not select Data");
			return r.toJsonString();
		}
		
		if(selected_user != null) {
			r.setResultCode(100);
			r.setDataObject(selected_user);
			r.setMessage("Success");
			logger.info("Success");
		} else {
			r.setResultCode(400);
			r.setMessage("Some Error occur while selecting");
		}
		return r.toJsonString();
	}
	
	public String getUpdateNickRes(String data) {
		Gson gson = new Gson();
		Response r = new Response();
		UserVO user = gson.fromJson(data,  UserVO.class);
		
		logger.info(user.toString());
		int result = 0;
		
		try {
			result = userDaoImpl.updateNick(user);
		} catch (Exception e) {
			e.printStackTrace();
			r.setResultCode(500);
			r.setMessage("Data not satisfied");
		}
		
		switch(result) {
		case 0:
			r.setResultCode(400);
			r.setMessage("DB Update Error");
			break;
		
		case 1:
			r.setResultCode(100);
			r.setMessage("Success");
			r.setDataObject(userDaoImpl.select(user.getId()));
			break;
		default:
			r.setResultCode(400);
			r.setMessage("Internal Error");
			logger.error("Return value is not 0 or 1");
		}
		
		return r.toJsonString();
	}

	@Override
	public String getUpdateRes(String data) {
		Response r = new Response();
		UserVO user = (UserVO) r.readValue(data, UserVO.class);
		logger.info(user.toString());
		
		logger.info(user.toString());
		user.setWithdrawal(false);
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
			r.setDataObject(user);
			logger.info("Success");
			break;
		case 0:
			r.setResultCode(400);
			r.setMessage("DB Insertion Error");
			break;
		default:
			r.setResultCode(400);
			r.setMessage("Internal Error");
			logger.error("Return value is not 0 or 1");
		}

		return r.toJsonString();
	}

	public String getWithdrawalRes(String data) { 
		Response r = new Response();
		UserVO user = (UserVO) r.readValue(data, UserVO.class);

		logger.info(user.toString());
		
		logger.info(user.toString());
		user.setWithdrawal(true);

		int result = 0;
		try {
			result = userDaoImpl.updateWithdrawal(user);
		} catch (Exception e) {
			e.printStackTrace();
			r.setResultCode(500);
			r.setMessage("Data not satisfied");
			return r.toJsonString();
		}
		
		switch(result) {
		case 1:
			r.setResultCode(100);
			r.setDataObject(user);
			logger.info("Success");
			break;
		case 0:
			r.setResultCode(400);
			r.setMessage("DB Insertion Error");
			break;
		default:
			r.setResultCode(400);
			r.setMessage("Internal Error");
			logger.error("Return value is not 0 or 1");
		}

		return r.toJsonString();
	}

}
