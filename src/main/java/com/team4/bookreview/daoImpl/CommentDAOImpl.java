package com.team4.bookreview.daoimpl;

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
		return sqlSession.selectList(namespace+".selectAll");
	}

	@Override
	public CommentVO select(CommentVO cmt) {
		return null;
	}

	@Override
	public int insertComment(CommentVO cmt) {
		return sqlSession.insert(namespace+".insertComment", cmt);
	}

	@Override
	public int updateComment(CommentVO cmt) {
		return sqlSession.update(namespace+".updateComment", cmt);
	}

	@Override
	public int deleteComment(CommentVO cmt) {
		return sqlSession.update(namespace+".deleteComment", cmt);
	}
}
