package com.team4.bookreview.util;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.team4.bookreview.daoimpl.CommentDAOImpl;
import com.team4.bookreview.model.Response;
import com.team4.bookreview.vo.CommentVO;
@Service
public class CommentQueryResRenderer implements DBQueryResRenderer {
	private static final Logger logger = LoggerFactory.getLogger(CommentQueryResRenderer.class);
	private static final String ERROR = "Error";

	@Autowired
	private CommentDAOImpl commentDaoImpl;
	
	@Override
	public String getInsertRes(String data) {
		Gson gson = new Gson();
		Response r = new Response();
		CommentVO to_Insert_Comment = gson.fromJson(data,  CommentVO.class);
		to_Insert_Comment.setDate(new Date());
		logger.info(to_Insert_Comment.toString());
		int result_cnt = 0;
		
		try {
			result_cnt = commentDaoImpl.insertComment(to_Insert_Comment);
		} catch(Exception e) {
			r.setResultCode(500);
			r.setMessage(ErrorMsg.ERROR_DB_INSERTION);
			return r.toJsonString();
		}
		
		switch(result_cnt) {
		case 0:
			r.setResultCode(400);
			r.setMessage(ErrorMsg.ERROR_DB_INSERTION);
			break;
		case 1:
			r.setResultCode(100);
			r.setMessage(SuccessMsg.SUCCESS_STRING);
			r.setDataObject(to_Insert_Comment);
			break;
		default:
			r.setResultCode(400);
			r.setMessage(ErrorMsg.ERROR_INTERNAL);
		}
		
		return r.toJsonString();
	}

	@Override
	public String getDeleteRes(String data) {
		Gson gson = new Gson();
		Response r = new Response();
		CommentVO to_Delete_Comment = gson.fromJson(data, CommentVO.class);
		
		int result_cnt = 0;
		try {
			result_cnt = commentDaoImpl.deleteComment(to_Delete_Comment);
		} catch(Exception e) {
			r.setResultCode(500);
			r.setMessage(ErrorMsg.ERROR_DB_DELETION);
			return r.toJsonString();
		}
		logger.info(result_cnt + "");
		
		switch(result_cnt) {
		case 0:
			r.setResultCode(400);
			r.setMessage(ErrorMsg.ERROR_DB_INSERTION);
			break;
		case 1:
			r.setResultCode(100);
			r.setMessage(SuccessMsg.SUCCESS_STRING);
			r.setDataObject(to_Delete_Comment);
			break;
		default:
			r.setResultCode(400);
			r.setMessage(ErrorMsg.ERROR_INTERNAL);
		}
		
		return r.toJsonString();
	}

	@Override
	public String getSelectRes(String data) {
		return null;
	}
	
	public String getAllSelectRes() {
		Response r = new Response();
		
		List<CommentVO> list_Comment = null;
		try {
			list_Comment = commentDaoImpl.selectAll();
		} catch(Exception e) {
			r.setResultCode(500);
			r.setMessage(ErrorMsg.ERROR_DB_DELETION);
			return r.toJsonString();
		}
		
		if(list_Comment != null) {
			r.setResultCode(100);
			r.setMessage(SuccessMsg.SUCCESS_STRING);
			r.setDataList(list_Comment);
		} else {
			r.setResultCode(400);
			r.setMessage(ErrorMsg.ERROR_DB_SELECTION);
		}
		
		return r.toJsonString();
	}

	@Override
	public String getUpdateRes(String data) {
		Gson gson = new Gson();
		Response r = new Response();
		CommentVO to_Update_Comment = gson.fromJson(data,  CommentVO.class);
		
		int result_cnt = 0;
		
		try {
			result_cnt = commentDaoImpl.updateComment(to_Update_Comment);
		} catch(Exception e) {
			logger.error(ERROR, e);
			r.setResultCode(500);
			r.setMessage(ErrorMsg.ERROR_DB_UPDATE);
			return r.toJsonString();
		}
		switch(result_cnt) {
		case 0:
			r.setResultCode(400);
			r.setMessage(ErrorMsg.ERROR_DB_UPDATE);
			break;
		
		case 1:
			r.setResultCode(100);
			r.setMessage(SuccessMsg.SUCCESS_STRING);
			r.setDataObject(commentDaoImpl.select(to_Update_Comment));
			break;
		default:
			r.setResultCode(400);
			r.setMessage(ErrorMsg.ERROR_INTERNAL);
			logger.error(ErrorMsg.ERROR_RETURN_VALUE_NOT_0_1);
		}
		
		return r.toJsonString();
	}



}
