package com.guardian.myhome.service;

import java.util.List;
import java.util.Map;

import com.guardian.myhome.vo.HomeDetailVO;
import com.guardian.myhome.vo.HomeImgVO;
import com.guardian.myhome.vo.HomeOptionVO;
import com.guardian.myhome.vo.HomePreviewVO;
import com.guardian.myhome.vo.HomePriceVO;
import com.guardian.myhome.vo.HomeVO;

public interface HomeDAO {
	int insertHome(HomeVO homeVO);	// 매물 테이블에 삽입
	int insertHomeImgList(List<HomeImgVO> homeImgList);	// 매물 사진 테이블에 삽입
	int insertHomePrice(HomePriceVO homePriceVO);	// 매물 가격 테이블에 삽입
	int insertHomeOptionList(List<HomeOptionVO> homeOptionList);	// 매물 옵션 테이블에 삽입
	List<HomePreviewVO> selectHomeInBoundsList(Map<String, Object> mapBounds);	// 현재 지도 경계내 매물 리스트 반환(미리보기 리스트)
	HomeImgVO selectPreviewHomeImg(int homeNum);	// 매물 미리보기시 나올 사진
	HomeDetailVO selectHomeDetail(int homeNum);	// 매물 정보 반환
	List<HomeImgVO> selectHomeImgList(int homeNum); // 해당 매물 사진들 반환
	List<String> selectHomeOptionList(int homeNum); // 해당 매물 옵션 목록 반환
	int updateHome(HomeVO homeVO);	// 매물 테이블 수정
	int updateHomePrice(HomePriceVO HomePriceVO);	// 매물 가격 테이블 수정
	int deleteHomeOption(int homeNum);	// 매물 옵션 삭제
	int deleteHomeImg(int homeNum);	// 매물 사진 정보 삭제
}
