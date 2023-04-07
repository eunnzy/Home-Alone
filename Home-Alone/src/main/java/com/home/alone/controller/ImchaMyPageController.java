package com.home.alone.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.home.alone.service.HomeService;
import com.home.alone.service.ImchaService;
import com.home.alone.vo.HomeInquryVO;
import com.home.alone.vo.ImchaVO;

@Controller
@RequestMapping("/mypage/imcha")
public class ImchaMyPageController {
	
	@Autowired
	private ImchaService  imchaService;
	
	@Autowired
	private HomeService homeService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public String mypageImcha() {
		return "mypage/mypageImcha";
	}
	
	// 회원 정보 수정 페이지
	@RequestMapping(value="/info")
	public String getMember(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		ImchaVO imchaVO = (ImchaVO) session.getAttribute("imcha");
		System.out.println(imchaVO.getImchaId());
		
		ImchaVO imchaInfo =  imchaService.getImchaInfo(imchaVO.getImchaId());
		
		System.out.println("닉네임" + imchaInfo.getNickname());
		System.out.println(imchaInfo.getImchaId());
		session.setAttribute("imcha", imchaInfo);
		
		return "mypage/imcha/modifyImcha";
	}
	
	
	@RequestMapping(value="/modify")	// 수정
	public String modifyImcha(ImchaVO imcha, HttpServletRequest request) throws Exception {
		System.out.println(imcha);
		HttpSession session = request.getSession();
		imchaService.modifyImchaInfo(imcha);
		System.out.println(imcha.getImchaId());
		session.setAttribute("imcha", imchaService.getImchaInfo(imcha.getImchaId()));
	    return "redirect: /mypage/imcha/info";
	}
	
	@RequestMapping(value="/inqury")
	public String homeInqList(Model model, HttpServletRequest request) {	// 문의 목록
		ImchaVO imcha = (ImchaVO) request.getSession().getAttribute("imcha");
		List<HomeInquryVO> inqList = homeService.getHomeInqListByImcha(imcha.getImchaId());
		model.addAttribute("inqList", inqList);
		
		return "/mypage/imcha/inquryList";
	}
}
