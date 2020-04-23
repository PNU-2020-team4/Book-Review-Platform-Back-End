package com.team4.bookreview.dao;

import java.util.List;

import com.team4.bookreview.vo.CommentVO;

public interface CommentDAO {
	public List<CommentVO> selectAll();
	public CommentVO select(int postID, int cmt_no);	
}
