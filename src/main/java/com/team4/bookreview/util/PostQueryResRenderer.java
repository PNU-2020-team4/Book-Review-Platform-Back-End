package com.team4.bookreview.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.team4.bookreview.daoImpl.PostDAOImpl;
import com.team4.bookreview.model.Response;
import com.team4.bookreview.vo.PostVO;


@Service
public class PostQueryResRenderer implements DBQueryResRenderer {
	private ObjectMapper obj = new ObjectMapper();
	@Autowired
	private PostDAOImpl postDAOImpl;

	@Override
	public String getInsertRes(String data) {
		System.out.println("----- getInsertRes -----");
		Response r = new Response();
		PostVO post = (PostVO) r.readValue(data, PostVO.class);
		int result;
		try {
			// result would be idx of new row
			result = postDAOImpl.insert(post);
			System.out.println("Idx of new Row : " + result);
			r.setResultCode(100);
			r.setData(post);
		} catch (Exception e) {
			e.printStackTrace();
			r.setResultCode(200);
			r.setMessage("Something's wrong");
		}

		return r.toJsonString();
	}

	@Override
	public String getDeleteRes(String data) {
		System.out.println("----- getDeleteRes -----");
		Response r = new Response();
		PostVO post = (PostVO) r.readValue(data, PostVO.class);
		int result;
		try {
			result = postDAOImpl.delete(post.getIdx());
			if (result == 1) {
				r.setResultCode(100);
			} else {
				r.setResultCode(300);
			}
		} catch (Exception e) {
			e.printStackTrace();
			r.setResultCode(200);
			r.setMessage("Something's wrong");
		}

		return r.toJsonString();
	}

	@Override
	public String getSelectRes(String data) {
		System.out.println("----- getSelectRes -----");
		Response r = new Response();
		int idx = 0;
		try {
			ObjectNode node = obj.readValue(data, ObjectNode.class);
			System.out.println("---df0-df");
			idx = node.get("idx").asInt();
		} catch (Exception e) {
			e.printStackTrace();
			idx = 0;
		} 

		if(idx == 0) {
			List<PostVO> result;
			try {
				result = postDAOImpl.selectAll();
				r.setResultCode(100);
				r.setDataList(result);
			} catch (Exception e) {
				e.printStackTrace();
				r.setResultCode(200);
				r.setMessage("Something's wrong");
			} 
			return r.toJsonString();
		}

		PostVO result = null;
		try {
			result = postDAOImpl.select(idx);
			if (result != null) {
				r.setResultCode(100);
				r.setData(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			r.setResultCode(200);
			r.setMessage("Something's wrong");
		}
		return r.toJsonString();
	}

	@Override
	public String getUpdateRes(String data) {
		System.out.println("----- getUpdateRes -----");
		Response r = new Response();
		PostVO post = (PostVO) r.readValue(data, PostVO.class);
		int result;
		try {
			result = postDAOImpl.update(post.getIdx(), post);
			r.setResultCode(100);
		} catch (Exception e) {
			e.printStackTrace();
			r.setResultCode(200);
			r.setMessage("Something's wrong");
		}
		return r.toJsonString();
	}
}
