package com.home.alone.dao;

import java.util.List;

import com.home.alone.vo.HomeLikeVO;
import com.home.alone.vo.HomeOverviewVO;

public interface HomeLikeDAO {
	int insertHomeLike(HomeLikeVO homeLikeVO);	// 매물 찜 insert
	List<HomeOverviewVO> selectHomeLikeList(String imchaId);	// 매물 찜 목록 리스트 
	int selectHomeLikeCheck(HomeLikeVO homeLikeVO);	 // 매물 찜 여부 확인
	int deleteHomeLike(HomeLikeVO homeLikeVO);	// 매물 찜 삭제

}
