package com.team4.bookreview.daoimpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.team4.bookreview.dao.UserDAO;
import com.team4.bookreview.vo.UserVO;


@Service
@Repository
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	private String namespace = "com.team4.bookreview.dao.UserDAO";
	
	public List<UserVO> selectAll() {
		return sqlSession.selectList(namespace+".selectAll");
	}
	
	public UserVO select(int id) {
		return sqlSession.selectOne(namespace+".select", id);
	}
	
	public String selectID(int id) {
		return sqlSession.selectOne(namespace+".selectID", id);
	}
	
	public int updateUser(UserVO uv) {
		return sqlSession.insert(namespace+".updateUser", uv);
	}
	
	public int updateNick(UserVO uv) {
		return sqlSession.update(namespace+".updateNick", uv);
	}

	public int updateWithdrawal(UserVO uv) {
		return sqlSession.update(namespace+".withdrawal", uv);
	}
}
