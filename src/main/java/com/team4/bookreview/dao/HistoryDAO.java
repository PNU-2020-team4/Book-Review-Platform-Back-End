package com.team4.bookreview.dao;

import java.util.List;

import com.team4.bookreview.vo.HistoryVO;

public interface HistoryDAO {
	public List<HistoryVO> selectAll();
	public HistoryVO select(int userID, int hist_no);
}
