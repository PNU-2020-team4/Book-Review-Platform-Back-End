package com.team4.bookreview.daoimpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.team4.bookreview.dao.HistoryDAO;
import com.team4.bookreview.vo.HistoryVO;

@Service
@Repository
public class HistoryDAOImpl implements HistoryDAO {

	@Autowired
	private SqlSession sqlSession;
	private String namespace = "com.team4.bookreview.dao.HistoryDAO";
	
	@Override
	public List<HistoryVO> selectAllbyUser(HistoryVO hv) {
		return sqlSession.selectList(namespace+".selectAllbyUser", hv);
	}
	
	@Override
	public int insertHistory(HistoryVO hv) {
		return sqlSession.update(namespace+".insertHistory", hv);
	}

	@Override
	public int deleteHistory(HistoryVO hv) {
		return sqlSession.update(namespace+".deleteHistory", hv);
	}

}
