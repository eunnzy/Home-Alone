package com.home.alone.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.alone.mapper.AdminMapper;
import com.home.alone.vo.AdminVO;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminMapper adminmapper;
	
	// 회원가입
	@Override
	public void adminJoin(AdminVO admin) throws Exception {

		adminmapper.adminJoin(admin);
	}
	
	// 아이디 중복체크
	@Override
	public int idCheck(String adminId) throws Exception {
		return adminmapper.idCheck(adminId);
	}

	// 로그인
	@Override
	public AdminVO adminLogin(AdminVO admin) throws Exception {
		
		return adminmapper.adminLogin(admin);
	}
	
	// 신고목록
	@Override
	public int report(int homeNum) throws Exception {
		return adminmapper.successNum(homeNum);
		
	}
	
}
