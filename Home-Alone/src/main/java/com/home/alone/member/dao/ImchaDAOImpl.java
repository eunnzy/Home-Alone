package com.home.alone.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.home.alone.member.vo.ImchaVO;

@Repository
public class ImchaDAOImpl implements ImchaDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	private static final String IMCHAMAPPER = "com.home.alone.mapper.ImchaMapper.";
	
	// 아이디 찾기
	@Override
	public String selectImchaId(ImchaVO imcha) throws Exception {
		return sqlSession.selectOne(IMCHAMAPPER + "selectImchaId", imcha);
	}
	
	// 비밀번호 찾기
	@Override
	public ImchaVO selectImchaPw(ImchaVO imcha) throws Exception {
		return sqlSession.selectOne(IMCHAMAPPER + "selectImchaPw", imcha);
	}
	
	// 비밀번호 변경
	@Override
	public ImchaVO updateImchaPw(ImchaVO imcha) throws Exception {
		return sqlSession.selectOne(IMCHAMAPPER + "updateImchaPw", imcha);
	}

	@Override
	public int insertImcha(ImchaVO imcha) {
		return sqlSession.insert(IMCHAMAPPER + "insertImcha", imcha);
	}

	@Override
	public int selectImchaIdCheck(String imchaId) {
		return sqlSession.selectOne(IMCHAMAPPER + "selectImchaIdCheck", imchaId);
	}

	@Override
	public int selectNicknameCheck(String nickname) {
		return sqlSession.selectOne(IMCHAMAPPER + "selectNicknameCheck", nickname);
	}

	@Override
	public ImchaVO selectImchaLogin(ImchaVO imcha) {
		return sqlSession.selectOne(IMCHAMAPPER + "selectImchaLogin", imcha);
	}

	@Override
	public int updateImcha(ImchaVO imcha) {
		return sqlSession.update(IMCHAMAPPER + "updateImcha", imcha);
	}

	@Override
	public ImchaVO selectImchaInfo(String imchaId) {
		return sqlSession.selectOne(IMCHAMAPPER + "selectImchaInfo", imchaId);
	}

}

