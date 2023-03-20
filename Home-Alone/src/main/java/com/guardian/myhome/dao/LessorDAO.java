package com.guardian.myhome.dao;

import java.util.List;

import com.guardian.myhome.vo.LessorVO;

public interface LessorDAO {

//	 LessorVO findLessorId(LessorVO lessor) throws Exception;
	 
	// 아이디 찾기
	 String findLessorId(LessorVO lessor) throws Exception;
	 
	 // 비밀번호 찾기 
	 LessorVO findLessorPw(LessorVO lessor) throws Exception;
	 
	 // 비밀번호 변경
	 LessorVO updateLessorPw(LessorVO lessor) throws Exception;
	 
	 // 중개인 리스트
	 List<LessorVO> lessorList();
	 
	 // 중개인 회원가입 승인
	 LessorVO successId(LessorVO lessor) throws Exception;
}
