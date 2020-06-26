package com.team4.bookreview.util;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.team4.bookreview.daoimpl.BookDAOImpl;
import com.team4.bookreview.model.Response;
import com.team4.bookreview.vo.BookVO;
import com.team4.bookreview.vo.BookwithstarVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BookQueryResRenderer {
	private static final Logger logger = LoggerFactory.getLogger(BookQueryResRenderer.class);
	private static final String WRITER  = "writer";

	private ObjectMapper obj = new ObjectMapper();

	@Autowired
	BookDAOImpl bookDAOImpl;

	
	public String getInsertRes(String data){
		logger.info("----- getInsertRes -----");

		Response r = new Response();
		BookVO record = (BookVO)r.readValue(data, BookVO.class);
		int result;
		
		try{
			result = bookDAOImpl.insert(record);
		} catch(Exception e){
			logger.error(ErrorMsg.ERROR_STRING, e);
			r.setResultCode(200);
			r.setMessage(ErrorMsg.ERROR_DATA_NOT_SATISFIED);
			logger.info(r.toJsonString());
			return r.toJsonString();			
		}

		switch(result) {
		case 1:
			r.setResultCode(100);
			r.setDataObject(record);
			logger.info(SuccessMsg.SUCCESS_STRING);
			break;
		case 0:
			r.setResultCode(400);
			r.setMessage(ErrorMsg.ERROR_DB_INSERTION);
			break;
		default:
			r.setResultCode(300);
			r.setMessage(ErrorMsg.ERROR_INTERNAL);
			logger.error(ErrorMsg.ERROR_RETURN_VALUE_NOT_0_1);
		}

		logger.error(r.toJsonString());
		return  r.toJsonString();
	}


	public int getInsertNoDup(BookVO record){
		logger.info("----- getInsertNoDupResByRecord -----");
		logger.info("----- BOOK INFO-----");
		logger.info(record.getAuthor());
		logger.info(record.getName());
		
		int result = 0; 
		try{
			result = bookDAOImpl.insertNoDup(record);
		
		} catch(Exception e){
			logger.error(ErrorMsg.ERROR_STRING, e);		
		}
 
		logger.info(result + "");
		return result;
	}
	
	
	public String getDeleteRes(String data){
		logger.info("----- getDeleteRes -----");

		Response r = new Response();
		int idx = -1;
		try {
			ObjectNode node = obj.readValue(data, ObjectNode.class);
			idx = node.get("idx").asInt();
		} catch (Exception e) {
			logger.error(ErrorMsg.ERROR_STRING, e);
			idx = -1;
		} 
		logger.info("Received data : " + idx);

		int result;
		try {
			result = bookDAOImpl.delete(idx);
		} catch (Exception e) {
			logger.error(ErrorMsg.ERROR_STRING, e);
			r.setResultCode(200);
			r.setMessage(ErrorMsg.ERROR_DATA_NOT_SATISFIED);
			logger.error(r.toJsonString());
			return r.toJsonString();
		}

		switch(result) {
			case 1:
				r.setResultCode(100);
				logger.info(SuccessMsg.SUCCESS_STRING);
				break;
			case 0:
				r.setResultCode(400);
				r.setMessage(ErrorMsg.ERROR_DB_DELETION);
				break;
			default:
				r.setResultCode(300);
				r.setMessage(ErrorMsg.ERROR_INTERNAL);
				logger.error(ErrorMsg.ERROR_RETURN_VALUE_NOT_0_1);
		}
		logger.error(r.toJsonString());
		return r.toJsonString();
	}

	
	public String getSelectRes(String data){
		logger.info("----- getSelectRes -----");

		Response r = new Response();
		int idx = 0;
		try {
			ObjectNode nodes = obj.readValue(data, ObjectNode.class);
			idx = nodes.get("idx").asInt();
		} catch (Exception e) {
			logger.error(ErrorMsg.ERROR_STRING, e);
			idx = 0;
		}

		logger.info("Received data : " + idx);
		
		if(idx == 0){
			logger.info("[SELECT ALL BOOK]");
			List<BookVO> result;
			try {
				result = bookDAOImpl.selectAll();
			} catch (Exception e) {
				logger.error(ErrorMsg.ERROR_STRING, e);
				r.setResultCode(200);
				r.setMessage(ErrorMsg.ERROR_UNKNOWN);
				return r.toJsonString();
			} 
			
			if(result == null) {
				r.setResultCode(400);
				r.setMessage(ErrorMsg.ERROR_DB_SELECTION);
			} else {
				logger.info(result.size() + "");
				r.setResultCode(100);
				r.setDataList(result);
				logger.info(SuccessMsg.SUCCESS_STRING);			
			}
		}
		else {
			logger.info("[SELECT ONE BOOK]");
			BookVO result = null;
			try {
				result = bookDAOImpl.select(idx);
			} catch (Exception e) {
				logger.error(ErrorMsg.ERROR_STRING, e);
				r.setResultCode(200);
				r.setMessage(ErrorMsg.ERROR_UNKNOWN);
				return r.toJsonString();
			}
			if(result == null) {
				r.setResultCode(400);
				r.setMessage(ErrorMsg.ERROR_DB_SELECTION);
			} else {
				logger.info("Queried data");
				r.setResultCode(100);
				r.setDataObject(result);
				logger.info(SuccessMsg.SUCCESS_STRING);			
			}
			
		}
		return r.toJsonString();

	}

	
	public String getUpdateRes(String data){
		logger.info("----- getUpdateRes -----");

		return null;
	}

	public String getSearchByAuthorRes(String data){
		logger.info("----- getSearchByAuthorRes -----");
		Response r = new Response();
		BookVO record = (BookVO)r.readValue(data, BookVO.class);
		
		List<BookVO> result;
		try{
			record.setAuthor(Util.toSearchString(record.getAuthor()));

			result = bookDAOImpl.searchByAuthor(record);
			r.setResultCode(100);
			r.setDataList(result);
		} catch(Exception e){
			logger.error(ErrorMsg.ERROR_STRING, e);
			r.setResultCode(200);
			r.setMessage(ErrorMsg.ERROR_DATA_NOT_SATISFIED);
			logger.error(r.toJsonString());
			return r.toJsonString();			
		}

		return r.toJsonString();
	}

	public String getSearchByNameRes(String data){
		logger.info("----- getSearchByAuthorRes -----");

		Response r = new Response();
		BookVO record = (BookVO)r.readValue(data, BookVO.class);
		
		List<BookVO> result;
		try{
			record.setName(Util.toSearchString(record.getName()));
			result = bookDAOImpl.searchByName(record);
			r.setResultCode(100);
			r.setDataList(result);
		} catch(Exception e){
			logger.error(ErrorMsg.ERROR_STRING, e);
			r.setResultCode(200);
			r.setMessage(ErrorMsg.ERROR_DATA_NOT_SATISFIED);
			logger.error(r.toJsonString());
			return r.toJsonString();			
		}

		return r.toJsonString();
	}


	
	public String getSearchByUserReview(String data) {
		logger.info("----- getSearchByUserReview -----");

		Response r = new Response();
		int writer = 0;
		try {
			ObjectNode nodes = obj.readValue(data, ObjectNode.class);
			writer = nodes.get(WRITER).asInt();
		} catch (Exception e) {
			logger.error(ErrorMsg.ERROR_STRING, e);
			writer = 0;
		}
		
		if(writer != 0){
			logger.info("[GET RECOMMENDATION BASED ON USER REVIEW]");
			List<BookwithstarVO> result;
			try {
				result = bookDAOImpl.getRecommendBasedUserReview(writer);
			} catch (Exception e) {
				logger.error(ErrorMsg.ERROR_STRING, e);
				r.setResultCode(200);
				r.setMessage(ErrorMsg.ERROR_UNKNOWN);
				return r.toJsonString();
			} 
			
			if(result == null) {
				r.setResultCode(400);
				r.setMessage(ErrorMsg.ERROR_DB_SELECTION);
			} else {
				logger.info(result.size() + "");
				r.setResultCode(100);
				r.setDataList(result);
				logger.info(SuccessMsg.SUCCESS_STRING);			
			}
		}
		else {
			r.setResultCode(210);
			r.setMessage(ErrorMsg.ERROR_DATA_NOT_SATISFIED);
		}

		return r.toJsonString();
	}
	
	
	public String getSearchByUserHistory(String data) {
		logger.info("----- getSearchByUserHistory -----");

		Response r = new Response();
		int writer = 0;
		try {
			ObjectNode nodes = obj.readValue(data, ObjectNode.class);
			writer = nodes.get(WRITER).asInt();
		} catch (Exception e) {
			logger.error(ErrorMsg.ERROR_STRING, e);
			writer = 0;
		}
		
		if(writer != 0){
			logger.info("[GET RECOMMENDATION BASED ON USER HISTORY]");
			List<BookwithstarVO> result;
			try {
				result = bookDAOImpl.getRecommendBasedUserHistory(writer);
			} catch (Exception e) {
				logger.error(ErrorMsg.ERROR_STRING, e);
				r.setResultCode(200);
				r.setMessage(ErrorMsg.ERROR_UNKNOWN);
				return r.toJsonString();
			} 
			
			if(result == null) {
				r.setResultCode(400);
				r.setMessage(ErrorMsg.ERROR_DB_SELECTION);
			} else {
				logger.info(result.size() + "");
				r.setResultCode(100);
				r.setDataList(result);
				logger.info(SuccessMsg.SUCCESS_STRING);			
			}
		}
		else {
			r.setResultCode(210);
			r.setMessage(ErrorMsg.ERROR_DATA_NOT_SATISFIED);
		}

		return r.toJsonString();
	}


	public String getSearchByData(String data) {
		logger.info("----- getSearchByData -----");

		Response r = new Response();
		int writer = 0;
		try {
			ObjectNode nodes = obj.readValue(data, ObjectNode.class);
			writer = nodes.get(WRITER).asInt();
		} catch (Exception e) {
			logger.error(ErrorMsg.ERROR_STRING, e);
			writer = 0;
		}
		
		if(writer != 0){
			logger.info("[GET RECOMMENDATION BASED ON DATA]");
			List<BookwithstarVO> result;

			try {
				result = bookDAOImpl.getDataBasedUserHistory(writer);
			} catch (Exception e) {
				logger.error(ErrorMsg.ERROR_STRING, e);
				r.setResultCode(200);
				r.setMessage(ErrorMsg.ERROR_UNKNOWN);
				return r.toJsonString();
			} 
			
			if(result == null) {
				r.setResultCode(400);
				r.setMessage(ErrorMsg.ERROR_DB_SELECTION);
			} else {
				logger.info(result.size() + "");
				r.setResultCode(100);
				r.setDataList(result);
				logger.info(SuccessMsg.SUCCESS_STRING);			
			}
		}
		else {
			r.setResultCode(210);
			r.setMessage(ErrorMsg.ERROR_DATA_NOT_SATISFIED);
		}

		return r.toJsonString();
	}
}
