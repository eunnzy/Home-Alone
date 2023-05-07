package com.home.alone.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.home.alone.member.service.LessorService;
import com.home.alone.member.vo.LessorVO;
import com.home.alone.service.HomeInquryService;
import com.home.alone.service.HomeService;
import com.home.alone.vo.HomeInquryVO;

@Controller
@RequestMapping("/mypage/lessor")
public class LessorMyPageController {
	
	@Autowired
	private LessorService lessorservice;
	
	@Autowired
	private HomeInquryService homeInquryService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public String mypageLessor() {
		return "mypage/mypageLessor";
	}
	
	@RequestMapping(value="/info")
	public String getLessor(LessorVO lessor, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		LessorVO vo = (LessorVO) session.getAttribute("lessor");
		System.out.println(vo.getLessorId());
		lessor.setLessorId(vo.getLessorId());
		lessorservice.getLessor(lessor);
		System.out.println("닉네임" + lessor.getLessorId());
		System.out.println(lessor.getLessorId());
		session.setAttribute("lessor", lessorservice.getLessor(vo));
		
		return "mypage/lessor/modifyLessor";
	}
	
	// 회원 정보 수정 페이지
	@RequestMapping(value="/modify")
	public String updateLessor(LessorVO lessor, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		LessorVO vo = (LessorVO) session.getAttribute("lessor");
		lessor.setLessorId(vo.getLessorId());
		System.out.println(vo.getLessorId());
		lessorservice.updateLessor(lessor);
		session.setAttribute("lessor", lessorservice.getLessor(lessor));
		
	    return "redirect: /mypage/lessor/info";
			
	}
	
	@RequestMapping(value="/inqury")
	public String homeInqList(Model model, HttpServletRequest request) {	// 문의 목록
		LessorVO lessor = (LessorVO) request.getSession().getAttribute("lessor");
		System.out.println(lessor);
		
		List<HomeInquryVO> inqList = homeInquryService.getHomeInqListByLessor(lessor.getLessorId());
		System.out.println(inqList);
		
		model.addAttribute("inqList", inqList);
		return "/mypage/lessor/inquryList";
	}
}
