package com.team4.bookreview.dao;

import java.util.List;

import com.team4.bookreview.vo.ReviewExpandVO;
import com.team4.bookreview.vo.ReviewVO;

public interface ReviewDAO {
	public List<ReviewExpandVO> selectAll();
	public List<ReviewExpandVO> selectByWriter(int writer);
	public List<ReviewExpandVO> selectByBook(int book);
	public int insert(ReviewVO rv);
	public int delete(int idx);
	public int update(ReviewVO rv);
}