package com.home.alone.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.home.alone.service.HomeService;
import com.home.alone.vo.HomeInqAnswerVO;
import com.home.alone.vo.HomeInquryDetailVO;
import com.home.alone.vo.HomeInquryVO;
import com.home.alone.vo.ImchaVO;
import com.home.alone.vo.LessorVO;

@Controller
@RequestMapping("/inqury")
public class HomeInquryController {
	
	@Autowired
	HomeService homeService;
	
	
	// 매물 문의 등록
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody	
	public int inquryHome(HomeInquryVO inqData, HttpServletRequest request) {
		ImchaVO imcha = (ImchaVO) request.getSession().getAttribute("imcha");
		
		if(imcha == null) {
			return 0;
		}
		
		System.out.println(inqData);

		inqData.setImchaId(imcha.getImchaId());
		System.out.println(inqData);

		
		return homeService.inquryHome(inqData);
	}
	
	
	@RequestMapping("/detail")	// 문의 상세 페이지
	public String detail(@RequestParam("iqNum") int iqNum, Model model) {
		HomeInquryDetailVO homeInqDetail = homeService.getHomeInqDetail(iqNum);
		System.out.println(homeInqDetail);
		model.addAttribute("inq", homeInqDetail);
	
		return "inqury/detail";
	}
	
	
	
	@RequestMapping("/answer/list")	// 답변 목록
	@ResponseBody
	public List<HomeInqAnswerVO> answerList(int iqNum, HttpServletRequest request) {
		List<HomeInqAnswerVO> answerList = homeService.getHomeInqAnswerList(iqNum);
		return answerList;
	}
	
	@RequestMapping("/answer/register")	// 답변 등록
	@ResponseBody
	public int answer(HomeInqAnswerVO homeInqAnswer, HttpServletRequest request) {
		LessorVO lessor = (LessorVO) request.getSession().getAttribute("lessor");
		homeInqAnswer.setLessorId(lessor.getLessorId());
			
		System.out.println(homeInqAnswer);
		return homeService.registerHomeInqAnswer(homeInqAnswer);
	}
	
	

	@RequestMapping("/lessorIdCheck")	// 중개인 아이디 확인
	@ResponseBody
	public int lessorIdCheck(HttpServletRequest request) {
		LessorVO lessor = (LessorVO) request.getSession().getAttribute("lessor");
		return homeService.homeAnsIdCheck(lessor.getLessorId());	// 
	}
	
	
	@RequestMapping("/answer/modify")	// 답변 수정
	@ResponseBody
	public int answerModify(HomeInqAnswerVO homeInqAnswer, HttpServletRequest request) {
		LessorVO lessor = (LessorVO) request.getSession().getAttribute("lessor");
		homeInqAnswer.setLessorId(lessor.getLessorId());
		System.out.println(homeInqAnswer);
		return homeService.modifyHomeInqAnswer(homeInqAnswer);
	}
	
}
