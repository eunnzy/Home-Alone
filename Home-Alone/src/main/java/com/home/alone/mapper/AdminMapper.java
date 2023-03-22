package com.home.alone.mapper;

import java.util.List;

import com.home.alone.vo.AdminVO;
import com.home.alone.vo.ImchaVO;


public interface AdminMapper {

	// 회원가입
	public void adminJoin(AdminVO admin);
	
	// 아이디 중복 검사
	public int idCheck(String adminId);
	
	// 로그인
	public AdminVO adminLogin(AdminVO admin);
	
	public List<ImchaVO> getList();
	
	public int successNum(int homeNum);
}
