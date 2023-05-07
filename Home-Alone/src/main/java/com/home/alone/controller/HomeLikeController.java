package com.home.alone.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.home.alone.mapper.LikeMapper;
import com.home.alone.member.vo.ImchaVO;
import com.home.alone.service.HomeLikeService;
import com.home.alone.vo.HomeLikeVO;
import com.home.alone.vo.HomePreviewVO;

@Controller
@RequestMapping(value = "/home/like")
public class HomeLikeController {
	
	@Autowired
	HomeLikeService homeLikeService;
	
	
	@RequestMapping("/click")
	@ResponseBody
	public int clickLike(@RequestParam("homeNum")int homeNum, HttpServletRequest request) {
		System.out.println("insertLike controller");
		ImchaVO imchaVO = (ImchaVO)request.getSession().getAttribute("imcha");
		String imchaId =  imchaVO.getImchaId();
		System.out.println(imchaId);
		
		HomeLikeVO homeLikeVO = new HomeLikeVO();
		homeLikeVO.setImchaId(imchaId);
		homeLikeVO.setHomeNum(homeNum);
		
		System.out.println(homeLikeVO);
		int checkLike = homeLikeService.checkLike(homeLikeVO);
		
		if(checkLike == 0) {	 // 좋아요가 안 되어 있는 경우 
			homeLikeService.insetLike(homeLikeVO);
			return 1;
		} else { // 좋아요 되어 있는 경우 삭제
			homeLikeService.remove(homeLikeVO);
			return 0;
		}
		
	}
	
	@RequestMapping("/list")	// 찜목록 리스트
	public String getLikeByImchaId(HttpServletRequest request, Model model) {
		ImchaVO imchaVO = (ImchaVO)request.getSession().getAttribute("imcha");
		String imchaId =  imchaVO.getImchaId();

		List<HomePreviewVO> likeList = homeLikeService.getLikeByImchaId(imchaId);	// 일반회원 찜 목록 
		
		model.addAttribute("likeList",likeList);
		return "mypage/likeList";
	}

	public boolean remove(int likeNum, String imchaId) {
		// TODO Auto-generated method stub
		return false;
	}

}
