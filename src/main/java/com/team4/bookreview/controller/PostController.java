package com.team4.bookreview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team4.bookreview.util.PostQueryResRenderer;

@Controller
public class PostController {

    
	@Autowired
	private PostQueryResRenderer renderer;

    @RequestMapping(value="/post/get", method=RequestMethod.GET)
    @ResponseBody
    public String postsSelect() {
        System.out.println("=========== [/post/get] request ==========");
        String JSONValue = renderer.getSelectRes("");
        System.out.println("Return : " + JSONValue);
        return JSONValue;
    }

    @RequestMapping(value="/post/getone", method=RequestMethod.POST)
    @ResponseBody
    public String postSelect(@RequestParam String data) {
        System.out.println("=========== [/post/getOne] request ==========");
        String JSONValue = renderer.getSelectRes(data);
        System.out.println("Return : " + JSONValue);
        return JSONValue;
    }

    @RequestMapping(value="/post/insert", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String postInsert(@RequestParam String data) {
        System.out.println("=========== [/post/insert] request ==========");
        String JSONValue = renderer.getInsertRes(data);
        System.out.println("Return : " + JSONValue);
        return JSONValue;
    }

    @RequestMapping(value="/post/delete", method=RequestMethod.POST)
    @ResponseBody
    public String postDelete(@RequestParam String data) {
        System.out.println("=========== [/post/delete] request ==========");
        String JSONValue = renderer.getDeleteRes(data);
        System.out.println("Return : " + JSONValue);
        return JSONValue;
    }

    @RequestMapping(value="/post/update", method=RequestMethod.POST)
    @ResponseBody
    public String postUpdate(@RequestParam String data) {
        System.out.println("=========== [/post/update] request ==========");
        String JSONValue = renderer.getUpdateRes(data);
        System.out.println("Return : " + JSONValue);
        return JSONValue;
    }


}
