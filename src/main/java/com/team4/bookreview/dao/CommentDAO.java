package com.team4.bookreview.dao;

import java.util.List;

import com.team4.bookreview.vo.CommentVO;

public interface CommentDAO {
	public List<CommentVO> selectAll();
	public CommentVO select(CommentVO cmt);
	public int insertComment(CommentVO cmt);
	public int updateComment(CommentVO cmt);
	public int deleteComment(CommentVO cmt);
	}
