package com.team4.bookreview.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team4.bookreview.util.PostQueryResRenderer;

@Controller
public class PostController {

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    
	@Autowired
	private PostQueryResRenderer renderer;

    @RequestMapping(value="/post/get", method=RequestMethod.GET, produces = "application/json; charset=utf8")
    @ResponseBody
    public String postsSelect() {
        logger.info("=========== [/post/get] request ==========");
        String JSONValue = renderer.getSelectRes("");
        logger.info("Return : " + JSONValue);
        return JSONValue;
    }

    @RequestMapping(value="/post/getone", method=RequestMethod.POST, produces = "application/json; charset=utf8")
    @ResponseBody
    public String postSelect(@RequestParam String data) {
        logger.info("=========== [/post/getOne] request ==========");
        String JSONValue = renderer.getSelectRes(data);
        logger.info("Return : " + JSONValue);
        return JSONValue;
    }

    @RequestMapping(value="/post/insert", method=RequestMethod.POST, produces = "application/json; charset=utf8")
    @ResponseBody
    public String postInsert(@RequestParam String data) {
        logger.info("=========== [/post/insert] request ==========");
        String JSONValue = renderer.getInsertRes(data);
        logger.info("Return : " + JSONValue);
        return JSONValue;
    }

    @RequestMapping(value="/post/delete", method=RequestMethod.POST, produces = "application/json; charset=utf8")
    @ResponseBody
    public String postDelete(@RequestParam String data) {
        logger.info("=========== [/post/delete] request ==========");
        String JSONValue = renderer.getDeleteRes(data);
        logger.info("Return : " + JSONValue);
        return JSONValue;
    }

    @RequestMapping(value="/post/update", method=RequestMethod.POST, produces = "application/json; charset=utf8")
    @ResponseBody
    public String postUpdate(@RequestParam String data) {
        logger.info("=========== [/post/update] request ==========");
        String JSONValue = renderer.getUpdateRes(data);
        logger.info("Return : " + JSONValue);
        return JSONValue;
    }

    @RequestMapping(value="/post/search/writer", method=RequestMethod.POST, produces = "application/json; charset=utf8")
    @ResponseBody
    public String postSearchByWriter(@RequestParam String data) {
        logger.info("=========== [/post/search/writer] request ==========");
        String JSONValue = renderer.getSearchByWriterRes(data);
        logger.info("Return : " + JSONValue);
        return JSONValue;
    }

    @RequestMapping(value="/post/search/title", method=RequestMethod.POST, produces = "application/json; charset=utf8")
    @ResponseBody
    public String postSearchByTitle(@RequestParam String data) {
        logger.info("=========== [/post/search/title] request ==========");
        String JSONValue = renderer.getSearchByTitleRes(data);
        logger.info("Return : " + JSONValue);
        return JSONValue;
    }



}
