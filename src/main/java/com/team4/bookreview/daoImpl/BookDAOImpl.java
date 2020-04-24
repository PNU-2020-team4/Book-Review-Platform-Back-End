package com.team4.bookreview.daoImpl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.team4.bookreview.dao.BookDAO;
import com.team4.bookreview.vo.BookVO;
@Service
@Repository
public class BookDAOImpl implements BookDAO {
	@Autowired
    protected SqlSessionTemplate sqlSession;
	
	@Override
	public List<BookVO> selectAll() {
		return sqlSession.selectList("com.team4.bookreview.bookMapper.selectAll");
	}

	@Override
	public BookVO select(int idx) {
		return sqlSession.selectOne("com.team4.bookreview.bookMapper.select");
	}
}
