package com.team4.bookreview.util;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.team4.bookreview.daoImpl.BookDAOImpl;
import com.team4.bookreview.model.Response;
import com.team4.bookreview.vo.BookVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BookQueryResRenderer implements DBQueryResRenderer {

	private ObjectMapper obj = new ObjectMapper();

	@Autowired
	BookDAOImpl bookDAOImpl;

	@Override
	public String getInsertRes(String data){
		System.out.println("----- getInsertRes -----");

		Response r = new Response();
		BookVO record = (BookVO)r.readValue(data, BookVO.class);
		int result;

		try{
			result = bookDAOImpl.insert(record);
		} catch(Exception e){
			e.printStackTrace();
			r.setResultCode(200);
			r.setMessage("Data not satisfied");
			System.out.println("Return : " + r.toJsonString());
			return r.toJsonString();			
		}

		switch(result) {
		case 1:
			r.setResultCode(100);
			r.setData(record);
			System.out.println("Success");
			break;
		case 0:
			r.setResultCode(400);
			r.setMessage("DB Insertion error");
			break;
		default:
			r.setResultCode(300);
			r.setMessage("Internal Error");
			System.out.println("Return value is not 0 or 1");
		}

		System.out.println("Return : " + r.toJsonString());
		return  r.toJsonString();
	}

	@Override
	public String getDeleteRes(String data){
		System.out.println("----- getDeleteRes -----");

		Response r = new Response();
		int idx = -1;
		try {
			ObjectNode node = obj.readValue(data, ObjectNode.class);
			idx = node.get("idx").asInt();
		} catch (Exception e) {
			e.printStackTrace();
			idx = -1;
		} 
		System.out.println("Received data : " + idx);

		int result;
		try {
			result = bookDAOImpl.delete(idx);
		} catch (Exception e) {
			e.printStackTrace();
			r.setResultCode(200);
			r.setMessage("Data not satisfied");
			System.out.println("Return : " + r.toJsonString());
			return r.toJsonString();
		}

		switch(result) {
			case 1:
				r.setResultCode(100);
				System.out.println("Success");
				break;
			case 0:
				r.setResultCode(400);
				r.setMessage("DB Deletion error");
				break;
			default:
				r.setResultCode(300);
				r.setMessage("Internal Error");
				System.out.println("Return value is not 0 or 1");
		}
		System.out.println("Return : " + r.toJsonString());
		return r.toJsonString();
	}

	@Override
	public String getSelectRes(String data){
		System.out.println("----- getSelectRes -----");

		Response r = new Response();
		int idx = 0;
		try {
			ObjectNode nodes = obj.readValue(data, ObjectNode.class);
			idx = nodes.get("idx").asInt();
		} catch (Exception e) {
			e.printStackTrace();
			idx = 0;
		}

		System.out.println("Received data : " + idx);
		
		if(idx == 0){
			System.out.println("[SELECT ALL BOOK]");
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
				System.out.println("Queried data : " + result.size());
				r.setResultCode(100);
				r.setDataList(result);
				System.out.println("Success");			
			}
		}
		else {
			System.out.println("[SELECT ONE BOOK]");
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
				System.out.println("Queried data");
				r.setResultCode(100);
				r.setData(result);
				System.out.println("Success");			
			}
			
		}
		return r.toJsonString();

	}

	@Override
	public String getUpdateRes(String data){
		System.out.println("----- getUpdateRes -----");

		// TODO Auto-generated method stub
		return null;
	}


}
