package com.home.alone.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.home.alone.service.HomeService;
import com.home.alone.vo.HomeReservVO;
import com.home.alone.vo.ImchaVO;

@Controller
@RequestMapping("/home/reserv")
public class HomeReservController {
//	@Autowired
//	RevMapper revMapper;
//	
	
//	@Autowired
//	ReservationService reservationService;
//	
	@Autowired
	HomeService homeService;
	
	@PostMapping("/register")
	@ResponseBody
	public int reservHome(HomeReservVO revData, HttpServletRequest request) {
		ImchaVO imcha = (ImchaVO) request.getSession().getAttribute("imcha");	// 회원 아이디 
		revData.setImchaId(imcha.getImchaId());
		System.out.println("revData : " + revData);
		int result = homeService.reservHome(revData);
		System.out.println(result);
		return result;	// 예약 신청
	}
	
	
	@RequestMapping("/validTimeCheck")
	@ResponseBody
	public List<String> reservValidTimeCheck(HomeReservVO revValid) {	// 유효한 예약 시간대만 예약할 수 있도록 확인해주기
		System.out.println("homeReservVO = " + revValid);
		
		List<String> timeList = new ArrayList<>();
		timeList = homeService.getValidTimeList(revValid);	
		System.out.println(timeList);
		
		return timeList;
		
	}
	
	
	@RequestMapping("/list")	// 일반회원 리스트
	public String reservList() {
		
		return "mypage/reservationImcha";
	}


//	// 회원이 보는 예약신청 목록
//	@RequestMapping("/list")
//	public String getRevByImchaId(String imchaId, Model model) {
//		List<HomeReservVO> revList = revMapper.getRevByImchaId(imchaId);
//	
//		for(int i=0; i<revList.size(); i++) {
//			int tmp = revList.get(i).getRevState();
//			if(tmp==0) {
//				revList.get(i).setState("확인 중 입니다.");
//			} else if (tmp==1) {
//				revList.get(i).setState("예약 확정되었습니다.");
//			} else {
//				revList.get(i).setState("예약 거절되었습니다.");
//			}
//		
//		}
//		System.out.println("listError");
//		
//		model.addAttribute("revList",revList);
//		return "mypage/reservationImcha";
//	}
//	
//	// 회원이 예약 취소
//	@RequestMapping("/cancel")
//	public String delete(int revNum, String imchaId) {
//		revMapper.delete(revNum, imchaId);
//		return "redirect:/home/reservation/list?imchaId="+imchaId;
//	}
//	
//	// 중개인이 보는 예약 목록
//	@RequestMapping("/lessorList")
//	public String getRevByLessor(Model model, HttpServletRequest request){
//		HttpSession session = request.getSession();
//		LessorVO lessorVO = (LessorVO) session.getAttribute("lessor");	// 글 등록 아이디
//		System.out.println(lessorVO.getLessorId());
//		List<HomeReservVO> lessorList = revMapper.getRevByLessor(lessorVO.getLessorId());
//		for(int i=0; i<lessorList.size(); i++) {
//			int tmp = lessorList.get(i).getRevState();
//			if(tmp==0) {
//				lessorList.get(i).setState("확인 중 입니다.");
//			} else if (tmp==1) {
//				lessorList.get(i).setState("예약 확정되었습니다.");
//			} else {
//				lessorList.get(i).setState("예약 거절되었습니다.");
//			}
//		
//		}
//		
//		model.addAttribute("lessorList",lessorList);
//
//		return "mypage/reservationLessor";
//	}
//	
//	// 예약 거부
//	@RequestMapping("/reject")
//	public String reject(int revNum, String imchaId, int homeNum, HttpServletResponse response) throws IOException {
//		revMapper.reject(revNum, imchaId, homeNum);
//		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = response.getWriter();
//        out.println("<script>alert('예약을 거절하였습니다.');history.go(-1);</script>");
//        out.flush();
//		return "redirect:/home/reservation/lessorList";
//	}
}