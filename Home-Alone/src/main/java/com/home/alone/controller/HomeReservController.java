package com.home.alone.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.home.alone.member.vo.ImchaVO;
import com.home.alone.member.vo.LessorVO;
import com.home.alone.service.HomeReservService;
import com.home.alone.vo.HomeReservOverviewVO;
import com.home.alone.vo.HomeReservVO;

@Controller
@RequestMapping("/home/reserv")
public class HomeReservController {
	
	@Autowired
	HomeReservService homeReservService;
	
	@RequestMapping("/register")
	@ResponseBody
	public int reservHome(HomeReservVO revData, HttpServletRequest request) {
		ImchaVO imcha = (ImchaVO) request.getSession().getAttribute("imcha");	// 회원 아이디 
		revData.setImchaId(imcha.getImchaId());
		System.out.println("revData : " + revData);
		
		int result = homeReservService.reservHome(revData);	// 예약 신청
		System.out.println(result);
		return result;	// 예약 신청
	}
	
	@RequestMapping("/invalidTimeCheck")
	@ResponseBody
	public List<String> reservValidTimeCheck(HomeReservVO revInvalid) {	// 유효한 예약 시간대만 예약할 수 있도록 확인해주기
//		System.out.println(homeNum);
//		System.out.println(revDate);
		System.out.println("homeReservVO = " + revInvalid);

//		HomeReservVO revInvalid = new HomeReservVO();

//		revInvalid.setHomeNum(homeNum);
//		revInvalid.setRevDate(revDate);

//		String dateToString = revInvalid.getRevDate().toString();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date revDate;
//		try {
//			revDate = sdf.parse(dateToString);
//			System.out.println(revDate);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		List<String> timeList = new ArrayList<>();
		
		timeList = homeReservService.getInvalidTimeList(revInvalid);	// 이미 예약이 되어서 예약이 불가능한 시간 리스트
		System.out.println(timeList);
	
		
		return timeList;
		
	}
	
	
	@RequestMapping("/imcha/list")	// 일반회원이 예약한 리스트
	public String reservListByImcha(Model model, HttpServletRequest request) {
		ImchaVO imcha = (ImchaVO) request.getSession().getAttribute("imcha");	// 회원 아이디 
		List<HomeReservOverviewVO> reservList = homeReservService.getReservListByImcha(imcha.getImchaId());
		model.addAttribute("reservList", reservList);
		return "mypage/imcha/reservImcha";
	}
	
	@RequestMapping("/lessor/list")	// 중개인예약 리스트
	public String reservListByLessor(String lessorId, Model model, HttpServletRequest request) {
		LessorVO lessor = (LessorVO) request.getSession().getAttribute("lessor");	// 회원 아이디 
		List<HomeReservOverviewVO> reservList = homeReservService.getReservListByLessor(lessor.getLessorId());
		model.addAttribute("reservList", reservList);
		return "mypage/lessor/reservLessor";
	}

	// 예약 상태변경(일반회원)
	@RequestMapping("/imcha/changeStatus")
	public String cancelImcha(String change, int revNum, HttpServletRequest request) throws IOException {
		switch(change) {
		case "cancel":	// 취소면
			homeReservService.homeReservCancel(revNum);
			break;
		case "accept":
			homeReservService.homeReservAccept(revNum);
			break;
		case "reject":
			homeReservService.homeReservReject(revNum);
			break;
		case "delete":
			homeReservService.deleteReserv(revNum);
			break;
		}
		
		return "redirect: /home/reserv/imcha/list";
	}
	
	// 예약 상태변경(중개인)
	@RequestMapping("/lessor/changeStatus")
	public String changeReservLessor(String change, int revNum, HttpServletRequest request) throws IOException {
		switch(change) {
		case "cancel":	// 취소면
			homeReservService.homeReservCancel(revNum);
			break;
		case "accept":
			homeReservService.homeReservAccept(revNum);
			break;
		case "reject":
			homeReservService.homeReservReject(revNum);
			break;
		case "delete":
			homeReservService.deleteReserv(revNum);
			break;
		}
		
		return "redirect: /home/reserv/lessor/list";
	}

}
