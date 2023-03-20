package com.guardian.myhome.service;


import com.guardian.myhome.vo.AdminVO;

public interface AdminService {

	// 회원가입
	public void adminJoin(AdminVO admin) throws Exception;
	
	// 아이디 중복검사
	public int idCheck(String adminId) throws Exception;
	
	// 로그인
	public AdminVO adminLogin(AdminVO admin) throws Exception;
	
	public int successNum(int homeNum) throws Exception;
	
}
