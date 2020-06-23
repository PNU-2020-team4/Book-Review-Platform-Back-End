package com.team4.bookreview.util;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.team4.bookreview.daoImpl.CommentDAOImpl;
import com.team4.bookreview.model.Response;
import com.team4.bookreview.vo.CommentVO;
@Service
public class CommentQueryResRenderer implements DBQueryResRenderer {
	private static final Logger logger = LoggerFactory.getLogger(CommentQueryResRenderer.class);

	@Autowired
	private CommentDAOImpl commentDaoImpl;
	
	@Override
	public String getInsertRes(String data) {
		// TODO Auto-generated method stub
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
			r.setMessage("Some Error occur while inserting comment");
			return r.toJsonString();
		}
		
		switch(result_cnt) {
		case 0:
			r.setResultCode(400);
			r.setMessage("DB Insert Error");
			break;
		case 1:
			r.setResultCode(100);
			r.setMessage("Success");
			r.setDataObject(to_Insert_Comment);
			break;
		default:
			r.setResultCode(400);
			r.setMessage("Internal Error");
		}
		
		return r.toJsonString();
	}

	@Override
	public String getDeleteRes(String data) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		Response r = new Response();
		CommentVO to_Delete_Comment = gson.fromJson(data, CommentVO.class);
		
		int result_cnt = 0;
		try {
			result_cnt = commentDaoImpl.deleteComment(to_Delete_Comment);
		} catch(Exception e) {
			r.setResultCode(500);
			r.setMessage("Some Error occur while deleting comment");
			return r.toJsonString();
		}
		logger.info(result_cnt + "");
		
		switch(result_cnt) {
		case 0:
			r.setResultCode(400);
			r.setMessage("DB Insert Error");
			break;
		case 1:
			r.setResultCode(100);
			r.setMessage("Success");
			r.setDataObject(to_Delete_Comment);
			break;
		default:
			r.setResultCode(400);
			r.setMessage("Internal Error");
		}
		
		return r.toJsonString();
	}

	@Override
	public String getSelectRes(String data) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getAllSelectRes() {
		Response r = new Response();
		
		List<CommentVO> list_Comment = null;
		try {
			list_Comment = commentDaoImpl.selectAll();
		} catch(Exception e) {
			r.setResultCode(500);
			r.setMessage("Some Error occur while deleting comment");
			return r.toJsonString();
		}
		
		if(list_Comment != null) {
			r.setResultCode(100);
			r.setMessage("Success");
			r.setDataList(list_Comment);
		} else {
			r.setResultCode(400);
			r.setMessage("DB Select Error");
		}
		
		return r.toJsonString();
	}

	@Override
	public String getUpdateRes(String data) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		Response r = new Response();
		CommentVO to_Update_Comment = gson.fromJson(data,  CommentVO.class);
		
		int result_cnt = 0;
		
		try {
			result_cnt = commentDaoImpl.updateComment(to_Update_Comment);
		} catch(Exception e) {
			logger.error("Error", e);
			r.setResultCode(500);
			r.setMessage("Some Error occur while updating comment");
			return r.toJsonString();
		}
		switch(result_cnt) {
		case 0:
			r.setResultCode(400);
			r.setMessage("DB Update Error");
			break;
		
		case 1:
			r.setResultCode(100);
			r.setMessage("Success");
			r.setDataObject(commentDaoImpl.select(to_Update_Comment));
			break;
		default:
			r.setResultCode(400);
			r.setMessage("Internal Error");
			logger.error("Return value is not 0 or 1");
		}
		
		return r.toJsonString();
	}



}
