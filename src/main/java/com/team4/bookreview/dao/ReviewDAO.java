package com.team4.bookreview.dao;

import java.util.List;

import com.team4.bookreview.vo.ReviewVO;

public interface ReviewDAO {
	public List<ReviewVO> selectAll();
	public List<ReviewVO> select(int idx);
	public int insert(ReviewVO rv);
	public int delete(int idx);
	public int update(ReviewVO rv);
}
