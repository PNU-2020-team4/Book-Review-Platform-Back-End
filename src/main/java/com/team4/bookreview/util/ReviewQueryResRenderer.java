package com.team4.bookreview.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.team4.bookreview.daoImpl.ReviewDAOImpl;
import com.team4.bookreview.model.Response;
import com.team4.bookreview.vo.ReviewExpandVO;
import com.team4.bookreview.vo.ReviewVO;
@Service
public class ReviewQueryResRenderer implements DBQueryResRenderer {
	private static final Logger logger = LoggerFactory.getLogger(ReviewQueryResRenderer.class);

	private ObjectMapper obj = new ObjectMapper();

	@Autowired
	ReviewDAOImpl reviewDAOImpl;
	
	@Override
	public String getInsertRes(String data) {
		Response r = new Response();
		ReviewVO record = (ReviewVO) r.readValue(data, ReviewVO.class);
		int result;
		
		try {
			result = reviewDAOImpl.insert(record);
		} catch (Exception e) {
			e.printStackTrace();
			r.setResultCode(200);
			r.setMessage("Data not satisfied");
			logger.error("Data not satisfied");
			logger.error("Return : " + r.toJsonString());
			return r.toJsonString();
		}
		
		switch(result) {
		case 1:
			r.setResultCode(100);
			r.setDataObject(record);
			System.out.println("Success");
			break;
		case 0:
			r.setResultCode(400);
			r.setMessage("DB Insertion error");
			break;
		default:
			r.setResultCode(300);
			r.setMessage("Internal Error");
			logger.error("Return value is not 0 or 1");
		}

		logger.info("Return : " + r.toJsonString());
		return  r.toJsonString();
	}

	@Override
	public String getDeleteRes(String data) {
		Response r = new Response();
		int idx = -1;
		try {
			ObjectNode node = obj.readValue(data, ObjectNode.class);
			idx = node.get("idx").asInt();
		} catch (Exception e) {
			e.printStackTrace();
			idx = -1;
		} 
		logger.info("Received data : " + idx);

		int result;
		try {
			result = reviewDAOImpl.delete(idx);
		} catch (Exception e) {
			e.printStackTrace();
			r.setResultCode(200);
			r.setMessage("Data not satisfied");
			logger.error("Return : " + r.toJsonString());
			return r.toJsonString();
		}

		switch(result) {
			case 1:
				r.setResultCode(100);
				logger.info("Success");
				break;
			case 0:
				r.setResultCode(400);
				r.setMessage("DB Deletion error");
				break;
			default:
				r.setResultCode(300);
				r.setMessage("Internal Error");
				logger.error("Return value is not 0 or 1");
		}
		logger.info("Return : " + r.toJsonString());
		return r.toJsonString();
	}


	@Override
	public String getSelectRes(String data) {
		Response r = new Response();
		
		int writer = -1;
		try {
			ObjectNode nodes = obj.readValue(data, ObjectNode.class);
			writer = nodes.get("writer").asInt();
		} catch (Exception e) {
			e.printStackTrace();
			writer = -1;
		}

		logger.info("Received data : " + writer);
		List<ReviewExpandVO> result;
		
		try {
			if (writer == -1) {
				result = (reviewDAOImpl.selectAll());
			} else {
				result = (reviewDAOImpl.selectByWriter(writer));
			}
		} catch (Exception e) {
			e.printStackTrace();
			r.setResultCode(200);
			r.setMessage("Data not satisfied");
			logger.error("Return : " + r.toJsonString());
			return r.toJsonString();
		}

		if(result == null) {
			r.setResultCode(400);
			r.setMessage("DB Selection Failure");
		} else {
			logger.info("Queried data : " + result.size());
			r.setResultCode(100);
			r.setDataList(result);
			logger.info("Success");			
		}
		logger.info("Return : " + r.toJsonString());
		return r.toJsonString();
	}


	public String getReviewByBookRes(String data) {
		Response r = new Response();
		int bookID = -1;
		
		List<ReviewExpandVO> result;
		try {
			ObjectNode nodes = obj.readValue(data, ObjectNode.class);
			bookID = nodes.get("bookID").asInt();
			result = (reviewDAOImpl.selectByBook(bookID));

		} catch (Exception e) {
			e.printStackTrace();
			r.setResultCode(200);
			r.setMessage("Data not satisfied");
			logger.error("Return : " + r.toJsonString());
			return r.toJsonString();
		}

		if(result == null) {
			r.setResultCode(400);
			r.setMessage("DB Selection Failure");
		} else {
			logger.info("Queried data : " + result.size());
			r.setResultCode(100);
			r.setDataList(result);
			logger.info("Success");			
		}
		logger.info("Return : " + r.toJsonString());
		return r.toJsonString();
	}

	@Override
	public String getUpdateRes(String data) {
		// update content, date
		Response r = new Response();
		ReviewVO record = (ReviewVO) r.readValue(data, ReviewVO.class);
		
		int result;
		try {
			result = reviewDAOImpl.update(record);
		} catch (Exception e) {
			e.printStackTrace();
			r.setResultCode(200);
			r.setMessage("Data not satisfied");
			logger.error("Return : " + r.toJsonString());
			return r.toJsonString();
		}
				
		switch(result) {
		case 1:
			r.setResultCode(100);
			r.setDataObject(record);
			logger.info("Success");
			break;
		case 0:
			r.setResultCode(400);
			r.setMessage("DB Update error");
			logger.error("DB Update error");
			break;
		default:
			r.setResultCode(300);
			r.setMessage("Internal Error");
			logger.error("Return value is not 0 or 1");
		}
		logger.info("Return : " + r.toJsonString());
		return r.toJsonString();
	}

}
