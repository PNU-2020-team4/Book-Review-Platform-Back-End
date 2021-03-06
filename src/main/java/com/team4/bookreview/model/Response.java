package com.team4.bookreview.model;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team4.bookreview.util.ErrorMsg;
import com.team4.bookreview.vo.DataVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Response {
    private static final Logger logger = LoggerFactory.getLogger(Response.class);
    

    private static ObjectMapper obj = new ObjectMapper();
    int resultCode = 500;
    String message = "";
    DataVO dataObject = null;
    List<? extends DataVO> dataList = null;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataVO getDataObject() {
        return dataObject;
    }

    public void setDataObject(DataVO dataObject) {
        this.dataObject = dataObject;
    }    

    
    public String toJsonString() {
        try {
        	logger.info("MAKE RESULT INTO JSON...");
            return obj.writeValueAsString(this);
        } catch (Exception e) {
            logger.error(ErrorMsg.ERROR_STRING, e);
            return createDummy();
        }
    }

    private String createDummy() {
        try {
            Response r = new Response();
            r.resultCode = 400;
            r.message = ErrorMsg.ERROR_UNKNOWN;
            return obj.writeValueAsString(r);
        } catch (Exception e) {
            logger.error(ErrorMsg.ERROR_STRING, e);
            return "";
        }
    }

    public DataVO readValue(String data, Class<? extends DataVO> valueType) {
        try {
			return obj.readValue(data, valueType);
		} catch (Exception e) {
			logger.error(ErrorMsg.ERROR_STRING, e);
			return new DataVO();
		}
	}

    public List<? extends DataVO> getDataList() {
        return dataList;
    }

    public void setDataList(List<? extends DataVO> dataList) {
        this.dataList = dataList;
    }
}