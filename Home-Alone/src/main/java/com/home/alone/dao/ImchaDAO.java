package com.home.alone.dao;

import com.home.alone.vo.ImchaVO;

public interface ImchaDAO {

//	 ImchaVO findId(ImchaVO imcha) throws Exception;
	
	 // 아이디 찾기
	 String findId(ImchaVO imcha) throws Exception;
	 
	 // 비밀번호 찾기
	 ImchaVO findPw(ImchaVO imcha) throws Exception;
	 
	 // 비밀번호 변경
	 ImchaVO updatePw(ImchaVO imcha) throws Exception;
}
