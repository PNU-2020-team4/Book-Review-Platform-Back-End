package com.team4.bookreview.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.team4.bookreview.dao.BookDAO;
import com.team4.bookreview.vo.BookVO;
import com.team4.bookreview.vo.BookwithstarVO;
@Service
@Repository
public class BookDAOImpl implements BookDAO {
	
	
	@Autowired
    protected SqlSessionTemplate sqlSession;
	private String namespace = "com.team4.bookreview.bookMapper";

	@Override
	public List<BookVO> selectAll() {
		return sqlSession.selectList(namespace + ".selectAll");
	}

	@Override
	public BookVO select(int idx) {
		return sqlSession.selectOne(namespace + ".select", idx);
	}

	@Override
	public int insert(BookVO book){
		return sqlSession.insert(namespace + ".insert", book);
	}
	
	public int insertNoDup(BookVO book){
		logger.info("BOOK INFO");
		
		logger.info(book.getAuthor());
		logger.info(book.getName());
		
		int result = 0;
		if(sqlSession.selectOne(namespace + ".insertNoDup", book)==null) result =-1;
		logger.info(result);
		return result;
	}
	
	@Override
	public int delete(int idx){
		return sqlSession.delete(namespace + ".delete", idx);
	}

	public List<BookVO> searchByAuthor(BookVO book) {
		return sqlSession.selectList(namespace + ".searchAuthor", book); 
	}

	public List<BookVO> searchByName(BookVO book) {
		return sqlSession.selectList(namespace + ".searchName", book); 
	}
	
	public List<BookwithstarVO> getRecommendBasedUserReview(int writer){
		return sqlSession.selectList(namespace + ".getUserReviewBasedRecommend", writer);
	}
	public List<BookwithstarVO> getRecommendBasedUserHistory(int writer){
		return sqlSession.selectList(namespace + ".getUserHistoryBasedRecommend", writer);
	}
	public List<BookwithstarVO> getDataBasedUserHistory(int writer){
		return sqlSession.selectList(namespace + ".getDataBasedRecommend", writer);
	}
	public int getIndexByAuthorAndName(String author, String name) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("author", author);
		param.put("name", name);
		return sqlSession.selectOne(namespace + ".getIndexByAuthorAndName", param);
				
	}

} 
