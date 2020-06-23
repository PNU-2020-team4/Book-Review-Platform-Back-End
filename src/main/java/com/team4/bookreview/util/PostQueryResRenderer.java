package com.team4.bookreview.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.team4.bookreview.daoImpl.PostDAOImpl;
import com.team4.bookreview.model.Response;
import com.team4.bookreview.vo.PostVO;


@Service
public class PostQueryResRenderer implements DBQueryResRenderer {
	private static final Logger logger = LoggerFactory.getLogger(PostQueryResRenderer.class);

	private ObjectMapper obj = new ObjectMapper();
	@Autowired
	private PostDAOImpl postDAOImpl;

	@Override
	public String getInsertRes(String data) {
		logger.info("----- getInsertRes -----");
		Response r = new Response();
		PostVO post = (PostVO) r.readValue(data, PostVO.class);
		int result;
		try {
			// result would be idx of new row
			result = postDAOImpl.insert(post);
			logger.info("Idx of new Row : " + result);
			r.setResultCode(100);
			r.setDataObject(post);
		} catch (Exception e) {
			logger.error("Error", e);
			r.setResultCode(200);
			r.setMessage("Something's wrong");
		}

		return r.toJsonString();
	}

	@Override
	public String getDeleteRes(String data) {
		logger.info("----- getDeleteRes -----");
		Response r = new Response();
		PostVO post = (PostVO) r.readValue(data, PostVO.class);
		int result;
		try {
			result = postDAOImpl.delete(post);
			if (result == 1) {
				r.setResultCode(100);
			} else {
				r.setResultCode(300);
			}
		} catch (Exception e) {
			logger.error("Error", e);
			r.setResultCode(200);
			r.setMessage("Something's wrong");
		}

		return r.toJsonString();
	}

	@Override
	public String getSelectRes(String data) {
		logger.info("----- getSelectRes -----");
		Response r = new Response();
		int idx = 0;
		try {
			ObjectNode node = obj.readValue(data, ObjectNode.class);
			if(node.get("idx") != null) {
				idx = node.get("idx").asInt();
			}
		} catch (Exception e) {
			logger.error("Error", e);
			idx = 0;
		} 

		if(idx == 0) {
			logger.info("[SELECT ALL POST]");
			List<PostVO> result;
			try {
				result = postDAOImpl.selectAll();
				r.setResultCode(100);
				r.setDataList(result);
			} catch (Exception e) {
				logger.error("Error", e);
				r.setResultCode(200);
				r.setMessage("Something's wrong");
			} 
			return r.toJsonString();
		}

		logger.info("[SELECT ONE POST]");
		PostVO result = null;
		try {
			result = postDAOImpl.select(idx);
			if (result != null) {
				r.setResultCode(100);
				r.setDataObject(result);
			}
		} catch (Exception e) {
			logger.error("Error", e);
			r.setResultCode(200);
			r.setMessage("Something's wrong");
		}
		return r.toJsonString();
	}

	@Override
	public String getUpdateRes(String data) {
		logger.info("----- getUpdateRes -----");
		Response r = new Response();
		PostVO post = (PostVO) r.readValue(data, PostVO.class);
		int result;
		try {
			result = postDAOImpl.update(post.getIdx(), post);
			r.setResultCode(100);
		} catch (Exception e) {
			logger.error("Error", e);
			r.setResultCode(200);
			r.setMessage("Something's wrong");
		}
		return r.toJsonString();
	}

	public String getSearchByWriterRes(String data){
		logger.info("----- getSearchByWriterRes -----");
		Response r = new Response();	
		PostVO post = (PostVO) r.readValue(data, PostVO.class);
		List<PostVO> result;
		try {
			post.setWriter(Util.toSearchString(post.getWriter()));

			result = postDAOImpl.searchByWriter(post);
			r.setDataList(result);
			r.setResultCode(100);
		} catch (Exception e) {
			logger.error("Error", e);
			r.setResultCode(200);
			r.setMessage("Something's wrong");
		}

		return r.toJsonString();
	}

	public String getSearchByTitleRes(String data){
		logger.info("----- getSearchByTitleRes -----");
		Response r = new Response();	
		PostVO post = (PostVO) r.readValue(data, PostVO.class);
		List<PostVO> result;
		try {
			post.setTitle(Util.toSearchString(post.getTitle()));

			result = postDAOImpl.searchByTitle(post);
			r.setDataList(result);
			r.setResultCode(100);
		} catch (Exception e) {
			logger.error("Error", e);
			r.setResultCode(200);
			r.setMessage("Something's wrong");
		}

		return r.toJsonString();
	}
}
