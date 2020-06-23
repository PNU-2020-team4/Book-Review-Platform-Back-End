package com.team4.bookreview.dao;

import java.util.List;

import com.team4.bookreview.vo.HistoryVO;

public interface HistoryDAO {
	public List<HistoryVO> selectAllbyUser(HistoryVO hv);
	public int insertHistory(HistoryVO hv);
	public int deleteHistory(HistoryVO hv);
}
