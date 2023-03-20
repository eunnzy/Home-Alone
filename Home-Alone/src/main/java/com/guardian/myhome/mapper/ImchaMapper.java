package com.guardian.myhome.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.guardian.myhome.vo.ImchaVO;

public interface ImchaMapper {

	// 회원가입
	public void imchaJoin(ImchaVO imcha);
	
	// 아이디 중복 검사
	public int idCheck(String imchaId);
	
	// 닉네임 중복 검사
	public int nicknameCheck(String nickname);
	
	// 로그인
	public ImchaVO imchaLogin(ImchaVO imcha);
	
	
	// 정보 수정
	public void updateMember(ImchaVO imcha);
	
	// 정보 저장
	public ImchaVO getMember(ImchaVO imcha);
	
	
}
