package com.guardian.myhome.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.guardian.myhome.service.LessorService;
import com.guardian.myhome.service.ImchaService;
import com.guardian.myhome.vo.LessorVO;
import com.guardian.myhome.vo.ImchaVO;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
	
	@Autowired
	private ImchaService  memberservice;
	
	@RequestMapping(value="/mypageImcha", method = RequestMethod.GET)
	public String mypageImcha() {
		return "mypage/mypageImcha";
	}
	
	@RequestMapping(value="/getMember")
	public String getMember(ImchaVO imcha, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		ImchaVO vo = (ImchaVO) session.getAttribute("imcha");
		System.out.println(vo.getImchaId());
		imcha.setImchaId(vo.getImchaId());
		memberservice.getMember(imcha);
		System.out.println("닉네임" + vo.getNickname());
		System.out.println(imcha.getImchaId());
		session.setAttribute("imcha", vo);
		
		return "mypage/getMember";
	}
	
	
	// 회원 정보 수정 페이지
	@RequestMapping(value="/updateMember")
	public String updateMember(ImchaVO imcha, HttpServletRequest request) throws Exception {
		
//		 //1.session이 있고 + 2.session정보가 있으면 
//	      if(session != null && session.getAttribute("member") != null) { 
//	      //updateform에있는 정보를 받아와 수정한다.
//	         memberservice.updateMember(member);
//	         session.setAttribute("member", memberservice.getMember(member));
//	         return "redirect:/mypage/getMember";
//	      }
		HttpSession session = request.getSession();
		memberservice.updateMember(imcha);
		System.out.println(imcha.getImchaId());
		session.setAttribute("imcha", memberservice.getMember(imcha));
	    return "redirect:/mypage/getMember";
			
	}
	
	@Autowired
	LessorService lessorservice;
	
	@RequestMapping(value="/mypageLessor", method = RequestMethod.GET)
	public String mypageLessor() {
		return "mypage/mypageLessor";
	}
	
	@RequestMapping(value="/getLessor")
	public String getLessor(LessorVO lessor, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		LessorVO vo = (LessorVO) session.getAttribute("lessor");
		System.out.println(vo.getLessorId());
		lessor.setLessorId(vo.getLessorId());
		lessorservice.getLessor(lessor);
		System.out.println("닉네임" + lessor.getLessorId());
		System.out.println(lessor.getLessorId());
		session.setAttribute("lessor", lessorservice.getLessor(vo));
		
		return "mypage/getLessor";
	}
	
	// 회원 정보 수정 페이지
	@RequestMapping(value="/updateLessor")
	public String updateLessor(LessorVO lessor, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		LessorVO vo = (LessorVO) session.getAttribute("lessor");
		lessor.setLessorId(vo.getLessorId());
		System.out.println(vo.getLessorId());
		lessorservice.updateLessor(lessor);
		session.setAttribute("lessor", lessorservice.getLessor(lessor));
		
	    return "mypage/getLessor";
			
	}
}
