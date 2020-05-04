package com.team4.bookreview.review;

import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.team4.bookreview.util.DBQueryResRenderer;
import com.team4.bookreview.util.QueryResRendererGetter;
import com.team4.bookreview.util.tableIDs;

@Controller
public class reviewController {
	@Autowired
	DBQueryResRenderer renderer = QueryResRendererGetter.getQueryResRenderer(tableIDs.REVIEW);
	 	
	@RequestMapping(value="/reviewInsert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String reviewInsert(@RequestParam String data) throws JsonParseException, JsonMappingException, IOException {

		String JSONValue = renderer.getInsertRes(data);
		System.out.println(JSONValue);
		return JSONValue;
	}


	
	@RequestMapping(value="/reviewSelect", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String reviewSelect(@RequestParam String data) throws JsonParseException, JsonMappingException, IOException {
		
		String JSONValue = renderer.getSelectRes(data);
		System.out.println(JSONValue);
		return JSONValue;
	}
	
	@RequestMapping(value="/reviewDelete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String reviewDelete(@RequestParam String data) throws JsonParseException, JsonMappingException, IOException {

		String JSONValue = renderer.getDeleteRes(data);
		System.out.println(JSONValue);
		return JSONValue;
		
	}

	@RequestMapping(value="/reviewUpdate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String reviewUpdate(@RequestParam String data) throws JsonParseException, JsonMappingException, IOException {

		String JSONValue = renderer.getUpdateRes(data);
		System.out.println(JSONValue);
		return JSONValue;
		
	}
	
/*	private ReviewVO renderVO(HttpServletRequest req) {

		if(req.getParameter("idx")=="0")
			return new ReviewVO(Integer.parseInt(req.getParameter("writer")), req.getParameter("content"),
					Integer.parseInt(req.getParameter("star")),Integer.parseInt(req.getParameter("book")),
					getTimestamp(req.getParameter("date")));
		else return new ReviewVO(Integer.parseInt(req.getParameter("idx")), Integer.parseInt(req.getParameter("writer")), req.getParameter("content"),
				Integer.parseInt(req.getParameter("star")),Integer.parseInt(req.getParameter("book")),
				getTimestamp(req.getParameter("date")));

	}	*/
	
	
	
	public Timestamp getTimestamp(String str){
		return Timestamp.valueOf(str);
	}
	
}
