package com.team4.bookreview.daoImpl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.team4.bookreview.dao.CommentDAO;
import com.team4.bookreview.vo.CommentVO;

@Service
@Repository
public class CommentDAOImpl implements CommentDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	private String namespace = "com.team4.bookreview.dao.CommentDAO";
	
	public List<CommentVO> selectAll() {
		List<CommentVO> listComment = sqlSession.selectList(namespace+".selectAll");
		return listComment;
	}

	@Override
	public CommentVO select(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertComment(CommentVO cmt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int updateComment(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		int res = sqlSession.update(namespace+".updateComment", map);
		System.out.println(res);
		return res;
	}

	@Override
	public int deleteComment(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		int res = sqlSession.update(namespace+".deleteComment", map);
		System.out.println(res);
		return 0;
	}
}
