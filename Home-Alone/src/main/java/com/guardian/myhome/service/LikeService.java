package com.guardian.myhome.service;

import java.util.List;

import com.guardian.myhome.vo.HomeDetailVO;
import com.guardian.myhome.vo.HomeVO;
import com.guardian.myhome.vo.LikeVO;

public interface LikeService {
	
	public void insetLike(LikeVO vo);
	
	public List<LikeVO> getLikeByImchaId(String imchaId);
	
	public void remove(int homeNum, String imchaId);
	
	public int checkLike(LikeVO vo);
	
	public HomeDetailVO getLikeDetail(LikeVO vo);
	
//	public List<LikeVO> getByLikeNumWithImchaId(LikeVO vo);
}
