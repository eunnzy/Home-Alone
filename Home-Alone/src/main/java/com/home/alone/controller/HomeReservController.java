package com.home.alone.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.home.alone.member.vo.ImchaVO;
import com.home.alone.member.vo.LessorVO;
import com.home.alone.service.HomeReservService;
import com.home.alone.vo.HomeReservPreviewVO;
import com.home.alone.vo.HomeReservVO;

@Controller
@RequestMapping("/home/reserv")
public class HomeReservController {
	
	@Autowired
	HomeReservService homeReservService;
	
	@PostMapping("/register")
	@ResponseBody
	public int reservHome(HomeReservVO revData, HttpServletRequest request) {
		ImchaVO imcha = (ImchaVO) request.getSession().getAttribute("imcha");	// 회원 아이디 
		revData.setImchaId(imcha.getImchaId());
		System.out.println("revData : " + revData);

//		if(homeReservService.getHomeReservCount(imcha.getImchaId()) > 10) {
//			return -1;
//		}
		
		int result = homeReservService.reservHome(revData);	// 예약 신청
		System.out.println(result);
		return result;	// 예약 신청
	}
	
	@RequestMapping("/invalidTimeCheck")
	@ResponseBody
	public List<String> reservValidTimeCheck(int homeNum, Date revDate) {	// 유효한 예약 시간대만 예약할 수 있도록 확인해주기
		System.out.println(homeNum);
		System.out.println(revDate);
	//	System.out.println("homeReservVO = " + revInvalid);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		HomeReservVO revInvalid = new HomeReservVO();
		
		revInvalid.setHomeNum(homeNum);
		revInvalid.setRevDate(revDate);
		
		List<String> timeList = new ArrayList<>();
		
		
		timeList = homeReservService.getInvalidTimeList(revInvalid);	// 이미 예약이 되어서 예약이 불가능한 시간 리스트
		System.out.println(timeList);
		
		return timeList;
		
	}
	
	
//	@RequestMapping("/invalidTimeCheck")
//	@ResponseBody
//	public List<String> reservValidTimeCheck(HomeReservVO revInvalid) {	// 유효한 예약 시간대만 예약할 수 있도록 확인해주기
//		System.out.println("homeReservVO = " + revInvalid);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		
//		List<String> timeList = new ArrayList<>();
//		
//		
//		timeList = homeReservService.getInvalidTimeList(revInvalid);	// 이미 예약이 되어서 예약이 불가능한 시간 리스트
//		System.out.println(timeList);
//		
//		return timeList;
//		
//	}
	
	@RequestMapping("/imcha/list")	// 일반회원이 예약한 리스트
	public String reservListByImcha(Model model, HttpServletRequest request) {
		ImchaVO imcha = (ImchaVO) request.getSession().getAttribute("imcha");	// 회원 아이디 
		List<HomeReservPreviewVO> reservList = homeReservService.getReservListByImcha(imcha.getImchaId());
		model.addAttribute("reservList", reservList);
		return "mypage/imcha/reservImcha";
	}
	
	@RequestMapping("/lessor/list")	// 중개인예약 리스트
	public String reservListByLessor(String lessorId, Model model, HttpServletRequest request) {
		LessorVO lessor = (LessorVO) request.getSession().getAttribute("lessor");	// 회원 아이디 
		List<HomeReservPreviewVO> reservList = homeReservService.getReservListByLessor(lessor.getLessorId());
		model.addAttribute("reservList", reservList);
		return "mypage/lessor/reservLessor";
	}

	// 예약 상태변경
	@RequestMapping("/changeStatus")
	public String cancel(String change, int revNum, HttpServletRequest request) throws IOException {
		System.out.println(request.getRequestURL());
		
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

//	// 예약 거부
	@RequestMapping("/reject")
	public String reject(int revNum, HttpServletResponse response) throws IOException {
		homeReservService.homeReservReject(revNum);
		return "redirect:/home/reserv/lessorList";
	}
}
