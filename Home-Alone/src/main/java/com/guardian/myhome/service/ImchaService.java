package com.guardian.myhome.service;

import com.guardian.myhome.vo.ImchaVO;

public interface ImchaService {

	// 회원가입
	public void imchaJoin(ImchaVO imcha) throws Exception;
	
	// 아이디 중복검사
	public int idCheck(String imchaId) throws Exception;
	
	// 닉네임 중복검사
	public int nicknameCheck(String nickname) throws Exception;
	
	// 로그인
	public ImchaVO imchaLogin(ImchaVO imcha) throws Exception;
	
	// 아이디 찾기
//	public ImchaVO findId(ImchaVO imcha) throws Exception;
	
	// 아이디 찾기
	public String findId(ImchaVO imcha) throws Exception;
	
	// 비밀번호 찾기
	public ImchaVO findPw(ImchaVO imcha) throws Exception;
	
	// 비밀번호 변경
	public ImchaVO updatePw(ImchaVO imcha) throws Exception;
	
	// 회원정보수정
	public void updateMember(ImchaVO imcha) throws Exception;
	
	// 회원정보 저장
	public ImchaVO getMember(ImchaVO member) throws Exception;

	
}
