package com.team4.bookreview.daoImpl;

import java.util.List;

import com.team4.bookreview.dao.PostDAO;
import com.team4.bookreview.vo.PostVO;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class PostDAOImpl implements PostDAO {
	@Autowired
	protected SqlSessionTemplate sqlSession;

	private String mapper = "com.team4.bookreview.postMapper";

	@Override
	public List<PostVO> selectAll() {
		return sqlSession.selectList(mapper + ".selectAll");
	}

	@Override
	public PostVO select(int idx) {
		return sqlSession.selectOne(mapper + ".select", idx);
	}

	@Override
	public int update(int idx, PostVO post) {
		return sqlSession.update(mapper + ".update", post);
	}

	@Override
	public int delete(PostVO post) {
		return sqlSession.delete(mapper + ".delete", post);
	}

	// return idx
	@Override
	public int insert(PostVO post) {
		System.out.println(post + "");
		return sqlSession.insert(mapper + ".insert", post);
	}
	
	
}