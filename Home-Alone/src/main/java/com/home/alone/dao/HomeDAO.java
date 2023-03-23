
package com.home.alone.dao;

import java.util.List;
import java.util.Map;

import com.home.alone.vo.HomeAddInfoVO;
import com.home.alone.vo.HomeDetailVO;
import com.home.alone.vo.HomeImgVO;
import com.home.alone.vo.HomeOptionVO;
import com.home.alone.vo.HomePreviewVO;
import com.home.alone.vo.HomePriceVO;
import com.home.alone.vo.HomeReportVO;
import com.home.alone.vo.HomeVO;
import com.home.alone.vo.LessorVO;

public interface HomeDAO {
	int insertHome(HomeVO homeVO);	// 매물 테이블에 삽입
	int insertHomeImgList(List<HomeImgVO> homeImgList);	// 매물 사진 테이블에 삽입
	int insertHomePrice(HomePriceVO homePriceVO);	// 매물 가격 테이블에 삽입
	int insertHomeOption(HomeOptionVO homeOptionVO);
	int insertHomeAddInfo(HomeAddInfoVO homenAddInfoVO);
	int insertHomeReport(HomeReportVO homeReportVO);	// 매물 신고 정보 삽입
	
	List<HomePreviewVO> selectHomeInBoundsList(Map<String, Object> mapBounds);	// 현재 지도 경계내 매물 리스트 반환(미리보기 리스트)
	List<HomePreviewVO> selectHomeListByFilter(Map<String, Object> filterData);
	HomeImgVO selectPreviewHomeImg(int homeNum);	// 매물 미리보기시 나올 사진
	HomeDetailVO selectHomeDetail(int homeNum);	// 매물 정보 반환
	HomeAddInfoVO selectHomeAddInfo(int homeNum);
	HomeOptionVO selectHomeOption(int homeNum);
	
	List<HomeImgVO> selectHomeImgList(int homeNum); // 해당 매물 사진들 반환
	int updateHome(HomeVO homeVO);	// 매물 테이블 수정
	int updateHomeAddInfo(HomeAddInfoVO homeAddInfoVO);	// 매물 추가 정보 테이블 수정
	int updateHomeOption(HomeOptionVO homeOptionVO);	// 매물 옵션 정보 테이블 수정
	int updateHomePrice(HomePriceVO HomePriceVO);	// 매물 가격 테이블 수정
	int deleteHomeImg(int homeNum);	// 매물 사진 정보 삭제

	int deleteHome(int homeNum);	// 매물 삭제
	List<HomePreviewVO> getListByLessorId(LessorVO vo);
	List<HomeReportVO> selectReportHomeList();
}
