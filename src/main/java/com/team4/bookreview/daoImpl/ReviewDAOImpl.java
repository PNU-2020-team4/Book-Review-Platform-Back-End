package com.team4.bookreview.daoImpl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team4.bookreview.dao.ReviewDAO;
import com.team4.bookreview.vo.ReviewVO;

@Repository
public class ReviewDAOImpl implements ReviewDAO {
	@Autowired
    protected SqlSessionTemplate sqlSession;
	
	@Override
	public List<ReviewVO> selectAll() {
		return sqlSession.selectList("com.team4.bookreview.reviewMapper.selectAll");
	}

	@Override
	public ReviewVO select(int idx) {
		return sqlSession.selectOne("com.team4.bookreview.reviewMapper.select");
	}

}
