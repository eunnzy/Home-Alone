package com.home.alone.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.alone.mapper.ImchaMapper;
import com.home.alone.member.dao.ImchaDAO;
import com.home.alone.member.vo.ImchaVO;

@Service
public class ImchaServiceImpl implements ImchaService {

	@Autowired
	ImchaMapper imchamapper;
	
	@Autowired
	ImchaDAO imchaDAO;
	
	// 회원가입
	@Override
	public int imchaJoin(ImchaVO imcha) throws Exception {
		return imchaDAO.insertImcha(imcha);
	}
	
	// 아이디 중복체크
	@Override
	public int imchaIdCheck(String imchaId) throws Exception {
		return imchaDAO.selectImchaIdCheck(imchaId);
	}
	
	// 닉네임 중복체크
	@Override
	public int nicknameCheck(String nickname) throws Exception {
		return imchaDAO.selectNicknameCheck(nickname);
	}

	// 로그인
	@Override
	public ImchaVO imchaLogin(ImchaVO imcha) throws Exception {
		return imchaDAO.selectImchaLogin(imcha);
	}
	
	// 아이디 찾기
	@Override
	public String findImchaId(ImchaVO imcha) throws Exception {
		return imchaDAO.selectImchaId(imcha);
	}
	
	// 비밀번호 찾기
	@Override
	public ImchaVO findImchaPw(ImchaVO imcha) throws Exception {
		return imchaDAO.selectImchaPw(imcha);
	}
	
	// 비밀번호 변경
	@Override
	public ImchaVO modifyImchaPw(ImchaVO imcha) throws Exception {
		return imchaDAO.updateImchaPw(imcha);
	}

	// 회원 정보 수정
	@Override
	public int modifyImchaInfo(ImchaVO imcha) throws Exception {
		return imchaDAO.updateImcha(imcha);
	}

	// 기존의 회원 정보 가져오기
	@Override
	public ImchaVO getImchaInfo(String imchaId) throws Exception {
		return imchaDAO.selectImchaInfo(imchaId);
	}


}
