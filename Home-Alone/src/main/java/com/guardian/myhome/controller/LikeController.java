package com.guardian.myhome.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guardian.myhome.mapper.HomeMapper;
import com.guardian.myhome.mapper.LikeMapper;
import com.guardian.myhome.service.LikeService;
import com.guardian.myhome.vo.HomeDetailVO;
import com.guardian.myhome.vo.ImchaVO;
import com.guardian.myhome.vo.LikeVO;

@Controller
@RequestMapping(value = "/like")
public class LikeController {
	
	@Autowired
	LikeService likeService;
	
	@Autowired
	LikeMapper likeMapper;
	
	@Autowired
	HomeMapper homeMapper;
	
	@RequestMapping("/clickLike")
	@ResponseBody
	public int clickLike(@RequestParam("homeNum")int homeNum, HttpServletRequest request) {
		System.out.println("insertLike controller");
		ImchaVO imchaVO = (ImchaVO)request.getSession().getAttribute("imcha");
		String imchaId =  imchaVO.getImchaId();
		System.out.println(imchaId);
		
		LikeVO likeVO = new LikeVO();
		likeVO.setImchaId(imchaId);
		likeVO.setHomeNum(homeNum);
		
		System.out.println(likeVO);
		int checkLike = likeService.checkLike(likeVO);
		
		
		if(checkLike == 0) {	 // 좋아요가 안 되어 있는 경우 
			likeService.insetLike(likeVO);
			return 1;
		} else { // 좋아요 되어 있는 경우 삭
			likeService.remove(homeNum, imchaId);
			return 0;
		}
		
	}
	
	@RequestMapping("/likeList")
	public String getLikeByImchaId(String imchaId,Model model) {
		// 1. 사용자가 찜한 목록을 찾는다
		List<LikeVO> likeList = likeMapper.getLikeByImchaId(imchaId);
		// 2. 찜한 게시몰의 정보를 담을 그릇을 만든다
		List<HomeDetailVO> homeList = new ArrayList<HomeDetailVO>();
		System.out.println("error1");
		for(int i=0; i<likeList.size(); i++) {
			LikeVO vo = likeList.get(i);
			System.out.println("error2");
			homeList.add(likeService.getLikeDetail(vo));
		}
		System.out.println("error2");
		model.addAttribute("likeList",homeList);
		return "mypage/likeList";
	}

	public boolean remove(int likeNum, String imchaId) {
		// TODO Auto-generated method stub
		return false;
	}

//	public LikeVO getByLikeNumWithImchaId(LikeVO vo) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
