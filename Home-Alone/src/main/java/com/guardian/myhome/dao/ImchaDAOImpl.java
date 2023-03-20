package com.guardian.myhome.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.guardian.myhome.vo.ImchaVO;

@Repository
public class ImchaDAOImpl implements ImchaDAO {

	private static final Logger log = LoggerFactory.getLogger(ImchaDAOImpl.class);
	
	private static final String NAMESPACE = "com.guardian.myhome.mapper.ImchaMapper";
	
	private final SqlSession sqlSession;
	
	@Inject
	public ImchaDAOImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// 아이디 찾기
//	@Override
//	public ImchaVO findId(ImchaVO imcha) throws Exception {
//		
//		return sqlSession.selectOne(NAMESPACE+".findId", imcha);
//	}
	
	// 아이디 찾기
	@Override
	public String findId(ImchaVO imcha) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE+".findId", imcha);
	}
	
	// 비밀번호 찾기
	@Override
	public ImchaVO findPw(ImchaVO imcha) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE+".findPw", imcha);
	}
	
	// 비밀번호 변경
	@Override
	public ImchaVO updatePw(ImchaVO imcha) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE+".updatePw", imcha);
	}
	

}

