package com.team4.bookreview.util;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team4.bookreview.daoImpl.HistoryDAOImpl;
import com.team4.bookreview.model.Response;
import com.team4.bookreview.vo.HistoryVO;

@Service
public class HistoryQueryResRenderer implements DBQueryResRenderer {

	private ObjectMapper obj = new ObjectMapper();
	
	@Autowired
	private HistoryDAOImpl historyDaoImpl;
	
	@Override
	public String getInsertRes(String data) {
		// TODO Auto-generated method stub
		System.out.println("=======getInsertRes========");
		Response r = new Response();
		HistoryVO hv = (HistoryVO)r.readValue(data,  HistoryVO.class);
		hv.setDate(new Date());
		int result = 0;
		
		try {
			result = historyDaoImpl.insertHistory(hv);
		} catch(Exception e) {
			e.printStackTrace();
			r.setResultCode(200);
			r.setMessage("Data not satisfied");
			System.out.println("Return : " + r.toJsonString());
			return r.toJsonString();
		}
		
		switch(result) {
		case 0:
			r.setResultCode(400);
			r.setMessage("DB Insertion Error");
			break;
		
		case 1:
			r.setResultCode(100);
			r.setDataObject(hv);
			System.out.println("Success");
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
	public String getDeleteRes(String data) {
		// TODO Auto-generated method stub
		System.out.println("=======getDeleteRes========");
		
		Response r = new Response();
		HistoryVO hv= (HistoryVO)r.readValue(data,  HistoryVO.class);
		
		int res;
		
		try {
			res = historyDaoImpl.deleteHistory(hv);
			System.out.println(res);
		} catch(Exception e) {
			e.printStackTrace();
			r.setResultCode(200);
			r.setMessage("Data not satisfied");
			System.out.println("Return : " + r.toJsonString());
			return r.toJsonString();
		}
		
		switch(res) {
		case 0:
			r.setResultCode(400);
			r.setMessage("DB Deletion Error");
			break;
		
		case 1:
			r.setResultCode(100);
			r.setDataObject(hv);
			r.setMessage("Success");
			System.out.println("Success");
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
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getSelectAllByUserRes(String data) {
		System.out.println("=======getSelectAllByUserRes========");
		Response r = new Response();
		HistoryVO hv = (HistoryVO)r.readValue(data, HistoryVO.class);
		
		List<HistoryVO> result;
		try{
			result = historyDaoImpl.selectAllbyUser(hv);
			r.setResultCode(100);
			r.setDataList(result);
		} catch(Exception e){
			e.printStackTrace();
			r.setResultCode(200);
			r.setMessage("Data not satisfied");
			System.out.println("Return : " + r.toJsonString());
			return r.toJsonString();			
		}

		return r.toJsonString();
		}

	@Override
	public String getUpdateRes(String data) {
		// TODO Auto-generated method stub
		
		
		return null;
	}

}
