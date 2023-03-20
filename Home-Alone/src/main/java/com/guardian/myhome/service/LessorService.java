package com.guardian.myhome.service;

import java.util.List;

import com.guardian.myhome.vo.LessorImgVO;
import com.guardian.myhome.vo.LessorVO;

public interface LessorService {

	// 중개인 회원가입
	public void lessorJoin(LessorVO lessor) throws Exception;
	
	// 아이디 중복 검사
	public int idCheck(String lessorId) throws Exception;
	
	// 닉네임 중복검사
	public int lessorNickNameCheck(String lessorNickName) throws Exception;
	
	// 로그인
	public LessorVO lessorLogin(LessorVO lessor) throws Exception;
		
//	// 아이디 찾기
//	public LessorVO findLessorId(LessorVO lessor) throws Exception;
	
	// 아이디 찾기
	public String findLessorId(LessorVO lessor) throws Exception;
		
	// 비밀번호 찾기
	public LessorVO findLessorPw(LessorVO lessor) throws Exception;
		
	// 비밀번호 변경
	public LessorVO updateLessorPw(LessorVO lessor) throws Exception;
	
	// 회원정보 저장
	public LessorVO getLessor(LessorVO lessor) throws Exception;
	
	// 회원정보 수정
	public void updateLessor(LessorVO lessor) throws Exception;
	
	// 중개인 목록
	public List<LessorVO> lessorList();
	
	// 회원가입 승인
	public int successId(String lessorId) throws Exception;
	
	// 회원가입 거절
//	public int failed(String lessorId) throws Exception;
	
	// 이미지 업로드
	public int imgEnroll(LessorImgVO lessorImg) throws Exception;

	// 이미지 보기
	public LessorImgVO selectImg(String lessorId) throws Exception;
	

}
