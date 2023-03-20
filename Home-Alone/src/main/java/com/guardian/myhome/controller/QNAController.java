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

import com.guardian.myhome.service.QNAService;
import com.guardian.myhome.vo.QNAImchaVO;
import com.guardian.myhome.vo.QNALessorVO;

@Controller
@RequestMapping("/qna")
public class QNAController {
	
	@Autowired
	QNAService qnaService;
	
	@RequestMapping("/insert")
	public void insertQNAImcha(QNAImchaVO qnaImcha, HttpServletResponse response, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		Map<String, Object> home = (Map<String, Object>) session.getAttribute("home"); 
		qnaImcha.setHomeNum((int)home.get("homeNum"));
		System.out.println(qnaImcha.getHomeNum());
		qnaService.insertQNAImcha(qnaImcha);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
        out.println("<script>alert('등록이 완료되었습니다.');history.go(-1);</script>");
        out.flush();
	}
	
//	@RequestMapping("/update")
//	public void updateQNAImcha(QNAImchaVO qnaImcha, HttpServletRequest request, HttpServletResponse response) throws IOException {
//		HttpSession session = request.getSession();
//		qnaService.updateQNAImcha(qnaImcha);
//		session.setAttribute("qnaImcha", qnaService.getBoard(qnaImcha.getIqNum()));
//		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = response.getWriter();
//        out.println("<script>alert('문의를 수정하였습니다.');history.go(-1);</script>");
//        out.flush();
//	}
	
	@RequestMapping("/list")
	public String imchaList(String imchaId, Model model) {
		List<QNAImchaVO> qnaImcha = qnaService.imchaList(imchaId);
		model.addAttribute("qnaImcha", qnaImcha);
		System.out.println(qnaImcha.get(0).getImchaId());
		return "mypage/qnaImcha";
	}
	
	@RequestMapping("/delete")
	public void deleteQNAImcha(String imchaId, int iqNum, HttpServletResponse response) throws IOException {
		qnaService.deleteQNAImcha(imchaId, iqNum);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
        out.println("<script>alert('문의를 삭제하였습니다.');history.go(-1);</script>");
        out.flush();
//		return "redirect:/qna/list?imchaId="+imchaId;
	}
	
	
	@RequestMapping("/getBoard")
	public String getBoard(int iqNum, Model model) {
		QNAImchaVO board = qnaService.getBoard(iqNum);
		System.out.println(iqNum);
		System.out.println(board.getImchaId());
		model.addAttribute("board", board);
		
		QNALessorVO answer = qnaService.getAnswer(iqNum);
		System.out.println(iqNum);
		model.addAttribute("answer", answer);
		return "mypage/qnaAnswer";
	}
	
	@RequestMapping("/lessorList")
	public String lessorList(String lessorId, Model model) {
		List<QNALessorVO> qnaLessor = qnaService.lessorList(lessorId);
		model.addAttribute("qnaLessor", qnaLessor);
		System.out.println(qnaLessor.get(0).getLessorId());
		return "mypage/qnaLessor";
	}
	
	
}
