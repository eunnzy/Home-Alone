package com.guardian.myhome.mapper;

import com.guardian.myhome.vo.LessorVO;
import com.guardian.myhome.vo.LessorImgVO;

public interface LessorMapper {

	// 중개인 회원가입
	public void lessorJoin(LessorVO lessor);
	
	// 아이디 중복 검사
	public int idCheck(String lessorId);
	
	// 닉네임 중복 검사
	public int lessorNickNameCheck(String lessorNickName);
	
	// 로그인 
	public LessorVO lessorLogin(LessorVO lessor);
	
	public LessorVO getLessor(LessorVO lessor);

	public void updateLessor(LessorVO lessor);
	
	// 회원가입 승인
	public int successId(String lessorId);
	
	// 거절
//	public int failed(String lessorId);
	
	// 이미지 등록
	public int imgEnroll(LessorImgVO lessorImg);
	
	// 이미지 뷰
	public LessorImgVO selectImg(String lessorId);
	


}
