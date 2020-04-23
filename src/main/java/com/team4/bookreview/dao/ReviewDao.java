package com.team4.bookreview.dao;

import java.util.List;

import com.team4.bookreview.vo.ReviewVO;

public interface ReviewDAO {
	public List<ReviewVO> selectAll();
	public ReviewVO select(int idx);
}
