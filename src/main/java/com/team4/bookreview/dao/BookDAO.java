package com.team4.bookreview.dao;

import java.util.List;

import com.team4.bookreview.vo.BookVO;

public interface BookDAO {
	public List<BookVO> selectAll();
	public BookVO select(int idx);
}
