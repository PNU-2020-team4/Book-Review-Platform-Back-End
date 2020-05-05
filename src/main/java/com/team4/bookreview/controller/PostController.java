package com.team4.bookreview.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.team4.bookreview.util.DBQueryResRenderer;
import com.team4.bookreview.util.QueryResRendererGetter;
import com.team4.bookreview.util.tableIDs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @Autowired
	DBQueryResRenderer renderer = QueryResRendererGetter.getQueryResRenderer(tableIDs.POST);

    @RequestMapping(value="/post/get", method=RequestMethod.GET)
    @ResponseBody
    public String postsSelect() {
        System.out.println("=========== [/post/get] request ==========");
        try {
    		String JSONValue = renderer.getSelectRes("");
    		System.out.println("Return : " + JSONValue);
            return JSONValue;
        } catch(JsonParseException e) {
            return "";
        } catch(JsonMappingException e) {
            return "";
        } catch(Exception e) {
            return "";
        }
    }

    @RequestMapping(value="/post/getone", method=RequestMethod.POST)
    @ResponseBody
    public String postSelect(@RequestParam String data) {
        System.out.println("=========== [/post/getOne] request ==========");
        try {
    		String JSONValue = renderer.getSelectRes(data);
    		System.out.println("Return : " + JSONValue);
            return JSONValue;
        } catch(JsonParseException e) {
            return "";
        } catch(JsonMappingException e) {
            return "";
        } catch(Exception e) {
            return "";
        }
    }

    @RequestMapping(value="/post/insert", method=RequestMethod.POST)
    @ResponseBody
    public String postInsert(@RequestParam String data) {
        System.out.println("=========== [/post/insert] request ==========");
        try {
    		String JSONValue = renderer.getInsertRes(data);
    		System.out.println("Return : " + JSONValue);
            return JSONValue;
        } catch(JsonParseException e) {
            return "";
        } catch(JsonMappingException e) {
            return "";
        } catch(Exception e) {
            return "";
        }
    }

    @RequestMapping(value="/post/delete", method=RequestMethod.POST)
    @ResponseBody
    public String postDelete(@RequestParam String data) {
        System.out.println("=========== [/post/delete] request ==========");
        try {
    		String JSONValue = renderer.getDeleteRes(data);
    		System.out.println("Return : " + JSONValue);
            return JSONValue;
        } catch(JsonParseException e) {
            return "";
        } catch(JsonMappingException e) {
            return "";
        } catch(Exception e) {
            return "";
        }
    }

    @RequestMapping(value="/post/update", method=RequestMethod.POST)
    @ResponseBody
    public String postUpdate(@RequestParam String data) {
        System.out.println("=========== [/post/update] request ==========");
        try {
    		String JSONValue = renderer.getUpdateRes(data);
    		System.out.println("Return : " + JSONValue);
            return JSONValue;
        } catch(JsonParseException e) {
            return "";
        } catch(JsonMappingException e) {
            return "";
        } catch(Exception e) {
            return "";
        }
    }


}
