package com.home.alone.service;

import com.home.alone.vo.ImchaVO;

public interface ImchaService {
	int imchaJoin(ImchaVO imcha) throws Exception; // 회원가입
	int imchaIdCheck(String imchaId) throws Exception; // 아이디 중복검사 
	int nicknameCheck(String nickname) throws Exception; // 닉네임 중복검사
	ImchaVO imchaLogin(ImchaVO imcha) throws Exception; // 로그인
	String findImchaId(ImchaVO imcha) throws Exception; // 아이디 찾기
	ImchaVO findImchaPw(ImchaVO imcha) throws Exception; // 비밀번호 찾기
	ImchaVO modifyImchaPw(ImchaVO imcha) throws Exception; // 비밀번호 변경
	int modifyImchaInfo(ImchaVO imcha) throws Exception; // 회원정보수정
	ImchaVO getImchaInfo(String imchaId) throws Exception; // 회원정보 저장
}
