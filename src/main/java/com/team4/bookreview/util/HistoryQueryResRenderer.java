package com.team4.bookreview.util;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.team4.bookreview.daoimpl.HistoryDAOImpl;
import com.team4.bookreview.model.Response;
import com.team4.bookreview.vo.HistoryVO;

@Service
public class HistoryQueryResRenderer implements DBQueryResRenderer {
	private static final Logger logger = LoggerFactory.getLogger(HistoryQueryResRenderer.class);
	private static final String ERROR = "Error";

	@Autowired
	private HistoryDAOImpl historyDaoImpl;
	
	@Override
	public String getInsertRes(String data) {
		logger.info("=======getInsertRes========");
		Response r = new Response();
		HistoryVO hv = (HistoryVO)r.readValue(data,  HistoryVO.class);
		hv.setDate(new Date());
		int result = 0;
		
		try {
			result = historyDaoImpl.insertHistory(hv);
		} catch(Exception e) {
			logger.error(ERROR, e);
			r.setResultCode(200);
			r.setMessage(ErrorMsg.ERROR_DATA_NOT_SATISFIED);
			logger.error(r.toJsonString());
			return r.toJsonString();
		}
		
		switch(result) {
		case 0:
			r.setResultCode(400);
			r.setMessage(ErrorMsg.ERROR_DB_INSERTION);
			break;
		
		case 1:
			r.setResultCode(100);
			r.setDataObject(hv);
			logger.info("Success");
			break;
			
		default:
			r.setResultCode(300);
			r.setMessage(ErrorMsg.ERROR_INTERNAL);
			logger.error(ErrorMsg.ERROR_RETURN_VALUE_NOT_0_1);
		}
		
		logger.info(r.toJsonString());
		return r.toJsonString();
	}

	public String getInsertResByRecord(HistoryVO hv) {
		logger.info("=======getInsertRes========");
		Response r = new Response();
		hv.setDate(new Date());
		int result = 0;
		
		try {
			result = historyDaoImpl.insertHistory(hv);
		} catch(Exception e) {
			logger.error(ERROR, e);
			r.setResultCode(200);
			r.setMessage(ErrorMsg.ERROR_DATA_NOT_SATISFIED);
			logger.error(r.toJsonString());
			return r.toJsonString();
		}
		
		switch(result) {
		case 0:
			r.setResultCode(400);
			r.setMessage(ErrorMsg.ERROR_DB_INSERTION);
			break;
		
		case 1:
			r.setResultCode(100);
			r.setDataObject(hv);
			logger.info("Inserted");
			break;
			
		default:
			r.setResultCode(300);
			r.setMessage("Updated");
			logger.info("Update Date Of History Item");
		}
		
		logger.info(r.toJsonString());
		return r.toJsonString();
	}
	
	@Override
	public String getDeleteRes(String data) {
		logger.info("=======getDeleteRes========");
		
		Response r = new Response();
		HistoryVO hv= (HistoryVO)r.readValue(data,  HistoryVO.class);
		
		int res;
		
		try {
			res = historyDaoImpl.deleteHistory(hv);
			logger.info(res + "");
		} catch(Exception e) {
			logger.error(ERROR, e);
			r.setResultCode(200);
			r.setMessage(ErrorMsg.ERROR_DATA_NOT_SATISFIED);
			logger.error(r.toJsonString());
			return r.toJsonString();
		}
		
		switch(res) {
		case 0:
			r.setResultCode(400);
			r.setMessage(ErrorMsg.ERROR_DB_DELETION);
			break;
		
		case 1:
			r.setResultCode(100);
			r.setDataObject(hv);
			r.setMessage("Success");
			logger.info("Success");
			break;
			
		default:
			r.setResultCode(300);
			r.setMessage(ErrorMsg.ERROR_INTERNAL);
			logger.error(ErrorMsg.ERROR_RETURN_VALUE_NOT_0_1);
		}
		
		logger.info(r.toJsonString());
		
		return r.toJsonString();
	}

	@Override
	public String getSelectRes(String data) {
		return null;
	}
	
	public String getSelectAllByUserRes(String data) {
		logger.info("=======getSelectAllByUserRes========");
		Response r = new Response();
		HistoryVO hv = (HistoryVO)r.readValue(data, HistoryVO.class);
		
		List<HistoryVO> result;
		try{
			result = historyDaoImpl.selectAllbyUser(hv);
			r.setResultCode(100);
			r.setDataList(result);
		} catch(Exception e){
			logger.error(ERROR, e);
			r.setResultCode(200);
			r.setMessage(ErrorMsg.ERROR_DATA_NOT_SATISFIED);
			logger.error(r.toJsonString());
			return r.toJsonString();			
		}

		return r.toJsonString();
		}

	@Override
	public String getUpdateRes(String data) {
		return null;
	}

}
