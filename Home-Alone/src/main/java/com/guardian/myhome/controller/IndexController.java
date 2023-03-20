package com.guardian.myhome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/")
public class IndexController {
	
	@RequestMapping("index")	// 메인 페이지
	public String home() {
		return "index";
	}
	
	@RequestMapping("member/selectMemberType")	// 회원 유형 선택
	public String memberJoin() {
		
		return "/member/selectMemberType";
	}
	
}
