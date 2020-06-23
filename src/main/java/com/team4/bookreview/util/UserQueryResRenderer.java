package com.team4.bookreview.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.team4.bookreview.daoimpl.UserDAOImpl;
import com.team4.bookreview.model.Response;
import com.team4.bookreview.vo.UserVO;


@Service
public class UserQueryResRenderer implements DBQueryResRenderer {
	private static final Logger logger = LoggerFactory.getLogger(UserQueryResRenderer.class);
	private static final String ERROR = "Error";
	
	@Autowired
	UserDAOImpl userDaoImpl;
	
	@Override
	public String getInsertRes(String data) {
		return null;
	}

	@Override
	public String getDeleteRes(String data) {
		return null;
	}

	@Override
	public String getSelectRes(String data) {
		Gson gson = new Gson();
		Response r = new Response();
		UserVO user = gson.fromJson(data,  UserVO.class);
		
		UserVO selected_user = null;
		
		try {
			selected_user = userDaoImpl.select(user.getId());
		} catch(Exception e) {
			logger.error(ERROR, e);
			r.setResultCode(500);
			r.setMessage(ErrorMsg.ERROR_DB_SELECTION);
			return r.toJsonString();
		}
		
		if(selected_user != null) {
			r.setResultCode(100);
			r.setDataObject(selected_user);
			r.setMessage(SuccessMsg.SUCCESS_STRING);
			logger.info(SuccessMsg.SUCCESS_STRING);
		} else {
			r.setResultCode(400);
			r.setMessage(ErrorMsg.ERROR_DB_SELECTION);
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
			logger.error(ERROR, e);
			r.setResultCode(500);
			r.setMessage(ErrorMsg.ERROR_DATA_NOT_SATISFIED);
		}
		
		switch(result) {
		case 0:
			r.setResultCode(400);
			r.setMessage(ErrorMsg.ERROR_DB_UPDATE);
			break;
		
		case 1:
			r.setResultCode(100);
			r.setMessage(SuccessMsg.SUCCESS_STRING);
			r.setDataObject(userDaoImpl.select(user.getId()));
			break;
		default:
			r.setResultCode(400);
			r.setMessage(ErrorMsg.ERROR_INTERNAL);
			logger.error(ErrorMsg.ERROR_RETURN_VALUE_NOT_0_1);
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
			logger.error(ERROR, e);
			r.setResultCode(500);
			r.setMessage(ErrorMsg.ERROR_DATA_NOT_SATISFIED);
			return r.toJsonString();
		}
		
		switch(result) {
		case 1:
			r.setResultCode(100);
			r.setDataObject(user);
			logger.info(SuccessMsg.SUCCESS_STRING);
			break;
		case 0:
			r.setResultCode(400);
			r.setMessage(ErrorMsg.ERROR_DB_INSERTION);
			break;
		default:
			r.setResultCode(400);
			r.setMessage(ErrorMsg.ERROR_INTERNAL);
			logger.error(ErrorMsg.ERROR_RETURN_VALUE_NOT_0_1);
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
			logger.error(ERROR, e);
			r.setResultCode(500);
			r.setMessage(ErrorMsg.ERROR_DATA_NOT_SATISFIED);
			return r.toJsonString();
		}
		
		switch(result) {
		case 1:
			r.setResultCode(100);
			r.setDataObject(user);
			logger.info(SuccessMsg.SUCCESS_STRING);
			break;
		case 0:
			r.setResultCode(400);
			r.setMessage(ErrorMsg.ERROR_DB_INSERTION);
			break;
		default:
			r.setResultCode(400);
			r.setMessage(ErrorMsg.ERROR_INTERNAL);
			logger.error(ErrorMsg.ERROR_RETURN_VALUE_NOT_0_1);
		}

		return r.toJsonString();
	}

}
