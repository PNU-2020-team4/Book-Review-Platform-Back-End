package com.team4.bookreview.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.team4.bookreview.daoImpl.ReviewDAOImpl;
import com.team4.bookreview.model.Response;
import com.team4.bookreview.vo.ReviewVO;
@Service
public class ReviewQueryResRenderer implements DBQueryResRenderer {
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
			System.out.println("Data not satisfied");
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
		System.out.println("Received data : " + idx);

		int result;
		try {
			result = reviewDAOImpl.delete(idx);
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
				r.setMessage("DB Insertion error");
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

		System.out.println("Received data : " + writer);
		List<ReviewVO> result;
		
		try {
			result = (reviewDAOImpl.selectByWriter(writer));
		} catch (Exception e) {
			e.printStackTrace();
			r.setResultCode(200);
			r.setMessage("Data not satisfied");
			System.out.println("Return : " + r.toJsonString());
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
		System.out.println("Return : " + r.toJsonString());
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
			r.setMessage("DB Update error");
			System.out.println("DB Update error");
			break;
		default:
			r.setResultCode(300);
			r.setMessage("Internal Error");
			System.out.println("Return value is not 0 or 1");
		}
		System.out.println("Return : " + r.toJsonString());
		return r.toJsonString();
	}

}
