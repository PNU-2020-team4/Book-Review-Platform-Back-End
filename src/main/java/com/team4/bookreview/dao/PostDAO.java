package com.team4.bookreview.dao;

import java.util.List;

import com.team4.bookreview.vo.PostVO;

public interface PostDAO {
	public List<PostVO> selectAll();
	public PostVO select(int idx);
}
