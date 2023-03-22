package com.home.alone.service;

import java.util.List;

import com.home.alone.vo.HomeDetailVO;
import com.home.alone.vo.HomeVO;
import com.home.alone.vo.LikeVO;

public interface LikeService {
	
	public void insetLike(LikeVO vo);
	
	public List<LikeVO> getLikeByImchaId(String imchaId);
	
	public void remove(int homeNum, String imchaId);
	
	public int checkLike(LikeVO vo);
	
	public HomeDetailVO getLikeDetail(LikeVO vo);
	
//	public List<LikeVO> getByLikeNumWithImchaId(LikeVO vo);
}
