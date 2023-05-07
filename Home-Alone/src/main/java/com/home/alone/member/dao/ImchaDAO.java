package com.home.alone.member.dao;

import com.home.alone.member.vo.ImchaVO;

public interface ImchaDAO {

	String selectImchaId(ImchaVO imcha) throws Exception; // 아이디 찾기
	ImchaVO selectImchaPw(ImchaVO imcha) throws Exception; // 비밀번호 찾기
	ImchaVO updateImchaPw(ImchaVO imcha) throws Exception; // 비밀번호 변경
	int insertImcha(ImchaVO imcha); // 회원가입
	int selectImchaIdCheck(String imchaId); // 아이디 중복 검사
	int selectNicknameCheck(String nickname); // 닉네임 중복 검사
	ImchaVO selectImchaLogin(ImchaVO imcha); // 로그인
	int updateImcha(ImchaVO imcha); 	// 정보 수정
	ImchaVO selectImchaInfo(String imchaId); // 저장된 정보 가져오기
}
