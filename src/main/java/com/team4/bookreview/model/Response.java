package com.team4.bookreview.model;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team4.bookreview.vo.DataVO;

public class Response {
    private static ObjectMapper obj = new ObjectMapper();
    int resultCode = 500;
    String message = "";
    DataVO data = null;
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

    public DataVO getData() {
        return data;
    }

    public void setData(DataVO data) {
        this.data = data;
    }    

    
    
    public String toJsonString() {
        try {
            return obj.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return createDummy();
        }
    }

    private String createDummy() {
        try {
            Response r = new Response();
            r.resultCode = 400;
            r.message = "Unknown Error";
            return obj.writeValueAsString(r);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public DataVO readValue(String data, Class<? extends DataVO> valueType) {
        try {
			return obj.readValue(data, valueType);
		} catch (Exception e) {
			e.printStackTrace();
			return new DataVO();
		}
	}

    public static ObjectMapper getObj() {
        return obj;
    }

    public static void setObj(ObjectMapper obj) {
        Response.obj = obj;
    }

    public List<? extends DataVO> getDataList() {
        return dataList;
    }

    public void setDataList(List<? extends DataVO> dataList) {
        this.dataList = dataList;
    }
}