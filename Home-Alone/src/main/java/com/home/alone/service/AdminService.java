package com.home.alone.service;


import com.home.alone.vo.AdminVO;

public interface AdminService {

	// 회원가입
	void adminJoin(AdminVO admin) throws Exception;
	
	// 아이디 중복검사
	int idCheck(String adminId) throws Exception;
	
	// 로그인
	AdminVO adminLogin(AdminVO admin) throws Exception;
	
	int report(int homeNum) throws Exception;	// 신고처리
	
}
