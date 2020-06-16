package com.team4.bookreview.daoImpl;

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
		List<UserVO> lu = sqlSession.selectList(namespace+".selectAll");
		return lu;
	}
	
	public UserVO select(int id) {
		UserVO user = sqlSession.selectOne(namespace+".select", id);
		return user;
	}
	
	public String selectID(int id) {
		String res = sqlSession.selectOne(namespace+".selectID", id);
		return res;
	}
	
	public int updateUser(UserVO uv) {
		int res = sqlSession.insert(namespace+".updateUser", uv);
		return res;
	}
	
	public int updateNick(UserVO uv) {
		int res = sqlSession.update(namespace+".updateNick", uv);
		return res;
	}

	public int updateWithdrawal(UserVO uv) {
		int res = sqlSession.update(namespace+".withdrawal", uv);
		return res;
	}
}
