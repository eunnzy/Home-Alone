package com.guardian.myhome.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.guardian.myhome.vo.LessorVO;


@Repository
public class LessorDAOImpl implements LessorDAO {

	private static final Logger log = LoggerFactory.getLogger(LessorDAOImpl.class);
	
	private static final String NAMESPACE = "com.guardian.myhome.mapper.LessorMapper";
	
	private final SqlSession sqlSession;
	
	@Inject
	public LessorDAOImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// 아이디 찾기
//	@Override
//	public LessorVO findLessorId(LessorVO lessor) throws Exception {
//		
//		return sqlSession.selectOne(NAMESPACE+".findLessorId", lessor);
//	}
	
	// 아이디 찾기
	@Override
	public String findLessorId(LessorVO lessor) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE+".findLessorId", lessor);
	}
	
	// 비밀번호 찾기
	@Override
	public LessorVO findLessorPw(LessorVO lessor) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE+".findLessorPw", lessor);
	}
	
	// 비밀번호 변경
	@Override
	public LessorVO updateLessorPw(LessorVO lessor) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE+".updateLessorPw", lessor);
	}
	
	// 중개인 목록
	@Override
	public List<LessorVO> lessorList() {
		return sqlSession.selectList(NAMESPACE+".lessorList");
	}
	
	// 회원가입 승인
	@Override
	public LessorVO successId(LessorVO lessor) throws Exception {
			
		return sqlSession.selectOne(NAMESPACE+".successId", lessor);
	}
}
