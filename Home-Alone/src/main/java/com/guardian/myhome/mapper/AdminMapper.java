package com.guardian.myhome.mapper;

import java.util.List;

import com.guardian.myhome.vo.AdminVO;
import com.guardian.myhome.vo.ImchaVO;


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
