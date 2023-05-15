package com.home.alone.service;

import java.util.List;

import com.home.alone.vo.HomeDetailVO;
import com.home.alone.vo.HomeLikeVO;
import com.home.alone.vo.HomeOverviewVO;

public interface HomeLikeService {
	
	int insetLike(HomeLikeVO homeLikeVO); // 매물 찜 추가	
	
	List<HomeOverviewVO> getLikeByImchaId(String imchaId);	// 매물 찜 목록 리스트
	
	int remove(HomeLikeVO homeLikeVO);
	
	int checkLike(HomeLikeVO homeLikeVO);
	
	HomeDetailVO getLikeDetail(HomeLikeVO homeLikeVO);
	
//	public List<LikeVO> getByLikeNumWithImchaId(LikeVO vo);
}
