package com.guardian.myhome.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guardian.myhome.mapper.RevMapper;
import com.guardian.myhome.service.HomeService;
import com.guardian.myhome.service.ReservationService;
import com.guardian.myhome.vo.LessorVO;
import com.guardian.myhome.vo.ReservationVO;

@Controller
@RequestMapping("home/reservation")
public class ReservationController {
	
	@Autowired
	RevMapper revMapper;
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	HomeService homeService;

	// 회원방문예약 신청
	@RequestMapping(value="/enroll")
	@ResponseBody
	public String insertRev(ReservationVO vo, String revTime, int homeNum, HttpServletResponse response) throws IOException {
		Map<String, Object> home =  homeService.selectHomeDetail(homeNum);
		System.out.println(home);
		vo.setRevDate(vo.getRevDate()+' ' + revTime);
		revMapper.insertRev(vo);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
        out.println("<script>alert('예약이 완료되었습니다.');history.go(-1);</script>");
        out.flush();

		return "/home/detail";
	}
	
	// 회원이 보는 예약신청 목록
	@RequestMapping("/list")
	public String getRevByImchaId(String imchaId, Model model) {
		List<ReservationVO> revList = revMapper.getRevByImchaId(imchaId);
	
		for(int i=0; i<revList.size(); i++) {
			int tmp = revList.get(i).getRevState();
			if(tmp==0) {
				revList.get(i).setState("확인 중 입니다.");
			} else if (tmp==1) {
				revList.get(i).setState("예약 확정되었습니다.");
			} else {
				revList.get(i).setState("예약 거절되었습니다.");
			}
		
		}
		System.out.println("listError");
		
		model.addAttribute("revList",revList);
		return "mypage/reservationImcha";
	}
	
	// 회원이 예약 취소
	@RequestMapping("/cancel")
	public String delete(int revNum, String imchaId) {
		revMapper.delete(revNum, imchaId);
		return "redirect:/home/reservation/list?imchaId="+imchaId;
	}
	
	// 중개인이 보는 예약 목록
	@RequestMapping("/lessorList")
	public String getRevByLessor(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		LessorVO lessorVO = (LessorVO) session.getAttribute("lessor");	// 글 등록 아이디
		System.out.println(lessorVO.getLessorId());
		List<ReservationVO> lessorList = revMapper.getRevByLessor(lessorVO.getLessorId());
		for(int i=0; i<lessorList.size(); i++) {
			int tmp = lessorList.get(i).getRevState();
			if(tmp==0) {
				lessorList.get(i).setState("확인 중 입니다.");
			} else if (tmp==1) {
				lessorList.get(i).setState("예약 확정되었습니다.");
			} else {
				lessorList.get(i).setState("예약 거절되었습니다.");
			}
		
		}
		
		model.addAttribute("lessorList",lessorList);

		return "mypage/reservationLessor";
	}
	
	// 예약 거부
	@RequestMapping("/reject")
	public String reject(int revNum, String imchaId, int homeNum, HttpServletResponse response) throws IOException {
		revMapper.reject(revNum, imchaId, homeNum);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
        out.println("<script>alert('예약을 거절하였습니다.');history.go(-1);</script>");
        out.flush();
		return "redirect:/home/reservation/lessorList";
	}
	
	// 예약 확정
	@RequestMapping("/allow")
	public String changeRevState(int homeNum, String imchaId, int revNum, HttpServletResponse response) throws IOException {
		revMapper.changeRevState(homeNum, imchaId, revNum);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
        out.println("<script>alert('예약을 확정하였습니다.');history.go(-1);</script>");
        out.flush();
		return "redirect:/home/reservation/lessorList";
	}
	
	@RequestMapping("/invaildDate")
	@ResponseBody
	public List<ReservationVO> invaildDate(String revDate){
		System.out.println(revDate);
        List<ReservationVO>list = revMapper.invaildDate(revDate);
        System.out.println(list);
        return list;
	}
 }
