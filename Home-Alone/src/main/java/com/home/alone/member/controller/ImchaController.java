package com.home.alone.member.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.home.alone.mapper.ImchaMapper;
import com.home.alone.member.service.ImchaService;
import com.home.alone.member.vo.ImchaVO;

/*
	회원 관련 기능
	
 */

@Controller
@SessionAttributes("imchaId")
@RequestMapping("/member")
public class ImchaController {
	
	@Autowired
	private ImchaService imchaService;
	
	
	// 유저회원가입
	@RequestMapping(value = "/imchaJoin", method = RequestMethod.GET)
	public void joinGET() {
		
	}
	
	
	@RequestMapping(value = "/imchaJoin", method = RequestMethod.POST)
	public String joinPOST(ImchaVO imcha) throws Exception{
		
		imchaService.imchaJoin(imcha);
		
		return "redirect:/index";
	}
	
	// 아이디 중복체크
	@RequestMapping(value = "/imchaIdChk", method = RequestMethod.POST)
	@ResponseBody
	public String imchaIdChkPOST(String imchaId) throws Exception {
		
		int result = imchaService.imchaIdCheck(imchaId);
		
		if (result != 0) {
			return "fail";
		} else {
			return "success";
		}
	}
	
	// 닉네임 중복체크
		@RequestMapping(value = "/nicknameChk", method = RequestMethod.POST)
		@ResponseBody
		public String nicknameChkPOST(String nickname) throws Exception {
			
			int result = imchaService.nicknameCheck(nickname);
			
			if (result != 0) {
				return "fail";
			} else {
				return "success";
			}
		}
	
	@RequestMapping(value = "/imchaLogin", method = RequestMethod.GET)
	public String registerForm() {
		return "/member/imchaLogin";
	}
	
	// 로그인
	@RequestMapping(value = "/imchaLogin", method = RequestMethod.POST)
	public String loginPOST(HttpServletRequest request, ImchaVO imcha, RedirectAttributes rttr) throws Exception {
		HttpSession session = request.getSession();
		ImchaVO vo = imchaService.imchaLogin(imcha);
		
		if(vo == null) {
			int result = 0;
			rttr.addFlashAttribute("result", result);
			return "redirect:/member/imchaLogin";
		} else {
			session.setAttribute("imcha", vo);
			System.out.println(vo.getImchaId());
			return "redirect:/index";
		}
	}
	

	
	// 로그아웃
	@RequestMapping(value="/logout.do", method = RequestMethod.GET)
	public String logoutGET(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		return "redirect:/index";
	}
	
	// 아이디 찾기
	@RequestMapping(value="/findId", method=RequestMethod.GET)
	public String findIdGET() throws Exception {
		return "member/findId";
	}
	
	@RequestMapping(value="/findId", method=RequestMethod.POST)
	@ResponseBody
	public int findIdPOST(ImchaVO imcha, Model model) throws Exception {
		System.out.println(imcha);
		String findIdVo = imchaService.findImchaId(imcha);
		
		System.out.println(findIdVo);
		
		if (findIdVo == null) {
			return 0;
		}else {
			model.addAttribute("imchaId", findIdVo);
			return 1;
		}
	}
	
	// 아이디 결과
	@RequestMapping(value="/resultId", method=RequestMethod.GET)
	public String resultIdGET(HttpServletRequest request, Model model, @RequestParam(required=false,value="nickname")
	String phone,String nickname,ImchaVO searchVO) throws Exception{
		
		return "/member/resultId";
	}
	
	// 비밀번호 찾기
	@RequestMapping(value="/findPw", method=RequestMethod.GET)
	public String findPwGET() throws Exception {
		return "member/findPw";
	}
	
	@RequestMapping(value="/findPw", method=RequestMethod.POST)
	public String findPwPOST(ImchaVO imcha, HttpSession session, RedirectAttributes rttr) throws Exception{
		ImchaVO findPwVo = imchaService.findImchaPw(imcha);
		
		if(findPwVo == null) {
			rttr.addFlashAttribute("check", 1);
			return "/member/msg";
		}else {
			findPwVo.setImchaId(imcha.getImchaId());
			rttr.addFlashAttribute("check",0);
			rttr.addFlashAttribute("findPwVo", findPwVo);
			return "redirect:/member/updatePw";
		}
	}
	
	// 비밀번호 변경
	@RequestMapping(value="/updatePw", method=RequestMethod.GET)
	public void updatePwGET(@RequestParam(value="updatePw", defaultValue="", required=false) String imchaId, ImchaVO imcha) throws Exception{
		
	}
	
	@RequestMapping(value="/updatePw", method=RequestMethod.POST)
	public String updatePwPOST(@RequestParam(value="updatePw", defaultValue="", required=false) String imchaId, ImchaVO imcha) throws Exception{
		imchaService.modifyImchaPw(imcha);
		return "redirect:/member/imchaLogin";
	}
	
}

