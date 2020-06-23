package com.team4.bookreview.util;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.team4.bookreview.daoImpl.BookDAOImpl;
import com.team4.bookreview.model.Response;
import com.team4.bookreview.vo.BookVO;
import com.team4.bookreview.vo.BookwithstarVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BookQueryResRenderer implements DBQueryResRenderer {
	private static final Logger logger = LoggerFactory.getLogger(BookQueryResRenderer.class);

	private ObjectMapper obj = new ObjectMapper();

	@Autowired
	BookDAOImpl bookDAOImpl;

	@Override
	public String getInsertRes(String data){
		logger.info("----- getInsertRes -----");

		Response r = new Response();
		BookVO record = (BookVO)r.readValue(data, BookVO.class);
		int result;
		
		try{
			result = bookDAOImpl.insert(record);
		} catch(Exception e){
			e.printStackTrace();
			r.setResultCode(200);
			r.setMessage("Data not satisfied");
			logger.info(r.toJsonString());
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
			r.setMessage("DB Insertion error");
			break;
		default:
			r.setResultCode(300);
			r.setMessage("Internal Error");
			logger.error("Return value is not 0 or 1");
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
			e.printStackTrace();		
		}
 
		logger.info(result + "");
		return result;
	}
	
	@Override
	public String getDeleteRes(String data){
		logger.info("----- getDeleteRes -----");

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
			result = bookDAOImpl.delete(idx);
		} catch (Exception e) {
			e.printStackTrace();
			r.setResultCode(200);
			r.setMessage("Data not satisfied");
			logger.error(r.toJsonString());
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
		logger.error(r.toJsonString());
		return r.toJsonString();
	}

	@Override
	public String getSelectRes(String data){
		logger.info("----- getSelectRes -----");

		Response r = new Response();
		int idx = 0;
		try {
			ObjectNode nodes = obj.readValue(data, ObjectNode.class);
			idx = nodes.get("idx").asInt();
		} catch (Exception e) {
			e.printStackTrace();
			idx = 0;
		}

		logger.info("Received data : " + idx);
		
		if(idx == 0){
			logger.info("[SELECT ALL BOOK]");
			List<BookVO> result;
			try {
				result = bookDAOImpl.selectAll();
			} catch (Exception e) {
				e.printStackTrace();
				r.setResultCode(200);
				r.setMessage("Something's wrong");
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
		}
		else {
			logger.info("[SELECT ONE BOOK]");
			BookVO result = null;
			try {
				result = bookDAOImpl.select(idx);
			} catch (Exception e) {
				e.printStackTrace();
				r.setResultCode(200);
				r.setMessage("Something's wrong");
				return r.toJsonString();
			}
			if(result == null) {
				r.setResultCode(400);
				r.setMessage("DB Selection Failure");
			} else {
				logger.info("Queried data");
				r.setResultCode(100);
				r.setDataObject(result);
				logger.info("Success");			
			}
			
		}
		return r.toJsonString();

	}

	@Override
	public String getUpdateRes(String data){
		logger.info("----- getUpdateRes -----");

		// TODO Auto-generated method stub
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
			e.printStackTrace();
			r.setResultCode(200);
			r.setMessage("Data not satisfied");
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
			e.printStackTrace();
			r.setResultCode(200);
			r.setMessage("Data not satisfied");
			logger.error(r.toJsonString());
			return r.toJsonString();			
		}

		return r.toJsonString();
	}

	public int getIndexByNameAndAuthorRes(String name, String author) {
		logger.info("----- getIndexByNameAndAuthorRes -----");
		
		int index = -1;
		try{
			index = bookDAOImpl.getIndexByAuthorAndName(author, name);
		} catch(Exception e){
			logger.error("Search Error With Author And Name.");
			e.printStackTrace();
		}

		return index;
	}
	
	public String getSearchByUserReview(String data) {
		logger.info("----- getSearchByUserReview -----");

		Response r = new Response();
		int writer = 0;
		try {
			ObjectNode nodes = obj.readValue(data, ObjectNode.class);
			writer = nodes.get("writer").asInt();
		} catch (Exception e) {
			e.printStackTrace();
			writer = 0;
		}
		
		if(writer != 0){
			logger.info("[GET RECOMMENDATION BASED ON USER REVIEW]");
			List<BookwithstarVO> result;
			try {
				result = bookDAOImpl.getRecommendBasedUserReview(writer);
			} catch (Exception e) {
				e.printStackTrace();
				r.setResultCode(200);
				r.setMessage("Something's wrong");
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
		}
		else {
			r.setResultCode(210);
			r.setMessage("Wrong Request");
		}

		return r.toJsonString();
	}
	
	
	public String getSearchByUserHistory(String data) {
		logger.info("----- getSearchByUserHistory -----");

		Response r = new Response();
		int writer = 0;
		try {
			ObjectNode nodes = obj.readValue(data, ObjectNode.class);
			writer = nodes.get("writer").asInt();
		} catch (Exception e) {
			e.printStackTrace();
			writer = 0;
		}
		
		if(writer != 0){
			logger.info("[GET RECOMMENDATION BASED ON USER HISTORY]");
			List<BookwithstarVO> result;
			try {
				result = bookDAOImpl.getRecommendBasedUserHistory(writer);
			} catch (Exception e) {
				e.printStackTrace();
				r.setResultCode(200);
				r.setMessage("Something's wrong");
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
		}
		else {
			r.setResultCode(210);
			r.setMessage("Wrong Request");
		}

		return r.toJsonString();
	}


	public String getSearchByData(String data) {
		// TODO Auto-generated method stub
		logger.info("----- getSearchByData -----");

		Response r = new Response();
		int writer = 0;
		try {
			ObjectNode nodes = obj.readValue(data, ObjectNode.class);
			writer = nodes.get("writer").asInt();
		} catch (Exception e) {
			e.printStackTrace();
			writer = 0;
		}
		
		if(writer != 0){
			logger.info("[GET RECOMMENDATION BASED ON DATA]");
			List<BookwithstarVO> result;

			try {
				result = bookDAOImpl.getDataBasedUserHistory(writer);
			} catch (Exception e) {
				e.printStackTrace();
				r.setResultCode(200);
				r.setMessage("Something's wrong");
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
		}
		else {
			r.setResultCode(210);
			r.setMessage("Wrong Request");
		}

		return r.toJsonString();
	}
}
