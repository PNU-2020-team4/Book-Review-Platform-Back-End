package com.team4.bookreview.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface DBQueryResRenderer {
	String getInsertRes(String data) throws JsonProcessingException, IOException;
	String getDeleteRes(String data) throws JsonProcessingException, IOException;
	String getSelectRes(String data) throws JsonParseException, JsonMappingException, IOException;
	String getUpdateRes(String data) throws JsonProcessingException, IOException;
}
