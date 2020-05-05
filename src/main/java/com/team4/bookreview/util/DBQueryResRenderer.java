package com.team4.bookreview.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface DBQueryResRenderer {
	String getInsertRes(String data);
	String getDeleteRes(String data);
	String getSelectRes(String data);
	String getUpdateRes(String data);
}
