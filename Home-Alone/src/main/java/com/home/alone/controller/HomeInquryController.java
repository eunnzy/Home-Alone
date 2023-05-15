package com.home.alone.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.home.alone.member.vo.ImchaVO;
import com.home.alone.member.vo.LessorVO;
import com.home.alone.service.HomeInquryService;
import com.home.alone.service.HomeService;
import com.home.alone.vo.HomeInqAnswerVO;
import com.home.alone.vo.HomeInquryDetailVO;
import com.home.alone.vo.HomeInquryVO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/inqury")
public class HomeInquryController {
	
	@Autowired
	HomeInquryService homeInquryService;
	
	// 매물 문의 등록
	@RequestMapping(value="/register", method=RequestMethod.POST)
	@ResponseBody	
	public int inquryHome(HomeInquryVO inqData, HttpServletRequest request) {
		ImchaVO imcha = (ImchaVO) request.getSession().getAttribute("imcha");
		if(imcha == null) {
			return 0;
		}
		
		inqData.setImchaId(imcha.getImchaId());
		
		System.out.println(inqData);
		
		return homeInquryService.inquryHome(inqData);
	}
	
	
	@RequestMapping("/detail")	// 문의 상세 페이지
	public String detail(@RequestParam("iqNum") int iqNum, Model model) {
		HomeInquryDetailVO homeInqDetail = homeInquryService.getHomeInqDetail(iqNum);
		System.out.println(homeInqDetail);
		model.addAttribute("inq", homeInqDetail);
	
		return "inqury/detail";
	}
	
	// 답변 목록
	@GetMapping(value = "/answer/{iqNum}", produces= {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<HomeInqAnswerVO>> getList(@PathVariable("iqNum") int iqNum) {
		log.info("get List.........");
		return new ResponseEntity<>(homeInquryService.getHomeInqAnswerList(iqNum), HttpStatus.OK);
	}
	
	// 답변 삭제 
	@DeleteMapping(value="/answer/delete/{ansNum}", produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("ansNum") int ansNum) {
		log.info("remove : " + ansNum);
		return homeInquryService.deleteHomeInqAnswer(ansNum) == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
		
	
	// 답변 등록 
	@PostMapping(value = "/answer/register", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody HomeInqAnswerVO homeInqAnsVO) {
		log.info("HomeInqAnswerVO : " + homeInqAnsVO);
		int insertCount = homeInquryService.registerHomeInqAnswer(homeInqAnsVO);
		return insertCount == 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	

	@RequestMapping("/lessorIdCheck")	// 중개인 아이디 확인
	@ResponseBody
	public int lessorIdCheck(HttpServletRequest request) {
		LessorVO lessor = (LessorVO) request.getSession().getAttribute("lessor");
		return homeInquryService.homeAnsIdCheck(lessor.getLessorId());	// 
	}
	
	
	@RequestMapping("/answer/modify")	// 답변 수정
	@ResponseBody
	public int answerModify(HomeInqAnswerVO homeInqAnswer, HttpServletRequest request) {
		LessorVO lessor = (LessorVO) request.getSession().getAttribute("lessor");
		homeInqAnswer.setLessorId(lessor.getLessorId());
		System.out.println(homeInqAnswer);
		return homeInquryService.modifyHomeInqAnswer(homeInqAnswer);
	}
	
}
