package com.team4.bookreview.dao;

import java.util.HashMap;
import java.util.List;

import com.team4.bookreview.vo.CommentVO;

public interface CommentDAO {
	public List<CommentVO> selectAll();
	public CommentVO select(HashMap<String, Object> map);
	public void insertComment(CommentVO cmt);
	public int updateComment(HashMap<String, Object> map);
	public int deleteComment(HashMap<String, Object> map);

}
