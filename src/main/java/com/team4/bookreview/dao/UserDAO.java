package com.team4.bookreview.dao;

import java.util.List;

import com.team4.bookreview.vo.UserVO;

public interface UserDAO {
	public List<UserVO> selectAll();
	public UserVO select(int id);
}