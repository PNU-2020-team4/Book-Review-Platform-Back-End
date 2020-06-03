package com.team4.bookreview.daoImpl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.team4.bookreview.dao.ReviewDAO;
//import com.team4.bookreview.vo.ReviewExpandVO;
import com.team4.bookreview.vo.ReviewVO;

@Service
@Repository
public class ReviewDAOImpl implements ReviewDAO {
	
	@Autowired
    protected SqlSessionTemplate sqlSession;
	
	@Override
	public List<ReviewVO> selectAll() {
		return sqlSession.selectList("com.team4.bookreview.reviewMapper.selectAll");
	}

	@Override
	public List<ReviewVO> selectByWriter(int writer) {
		return sqlSession.selectList("com.team4.bookreview.reviewMapper.selectByWriter", writer);
	}
	
	@Override
	public int insert(ReviewVO rv) {
		return sqlSession.insert("com.team4.bookreview.reviewMapper.insert", rv);
	}

	@Override
	public int delete(int idx) {
		return sqlSession.delete("com.team4.bookreview.reviewMapper.delete", idx);
	}

	@Override
	public int update(ReviewVO rv) {
		return sqlSession.update("com.team4.bookreview.reviewMapper.update", rv);
	}


	
	

}
