package com.guardian.myhome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guardian.myhome.dao.ImchaDAO;
import com.guardian.myhome.mapper.ImchaMapper;
import com.guardian.myhome.vo.ImchaVO;

@Service
public class ImchaServiceImpl implements ImchaService {

	@Autowired
	ImchaMapper imchamapper;
	
	@Autowired
	ImchaDAO dao;
	
	// 회원가입
	@Override
	public void imchaJoin(ImchaVO imcha) throws Exception {

		imchamapper.imchaJoin(imcha);
	}
	
	// 아이디 중복체크
	@Override
	public int idCheck(String imchaId) throws Exception {
		return imchamapper.idCheck(imchaId);
	}
	
	// 닉네임 중복체크
	@Override
	public int nicknameCheck(String nickname) throws Exception {
		return imchamapper.nicknameCheck(nickname);
	}

	// 로그인
	@Override
	public ImchaVO imchaLogin(ImchaVO imcha) throws Exception {
		
		return imchamapper.imchaLogin(imcha);
	}
	
	// 아이디찾기
//	@Override
//	public ImchaVO findId(ImchaVO imcha) throws Exception {
//		
//		return dao.findId(imcha);
//	}
	
	// 아이디 찾기
	@Override
	public String findId(ImchaVO imcha) throws Exception {
		
		return dao.findId(imcha);
	}
	
	// 비밀번호 찾기
	@Override
	public ImchaVO findPw(ImchaVO imcha) throws Exception {
		
		return dao.findPw(imcha);
	}
	
	// 비밀번호 변경
	@Override
	public ImchaVO updatePw(ImchaVO imcha) throws Exception {
		
		return dao.updatePw(imcha);
	}

	@Override
	public void updateMember(ImchaVO imcha) throws Exception {
		imchamapper.updateMember(imcha);
		
	}

	@Override
	public ImchaVO getMember(ImchaVO imcha) throws Exception {
		return imchamapper.getMember(imcha);
	}


}
