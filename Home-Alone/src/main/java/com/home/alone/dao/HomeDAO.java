
package com.home.alone.dao;

import java.util.List;
import java.util.Map;

import com.home.alone.member.vo.LessorVO;
import com.home.alone.vo.HomeAddInfoVO;
import com.home.alone.vo.HomeDetailVO;
import com.home.alone.vo.HomeImgVO;
import com.home.alone.vo.HomeLikeVO;
import com.home.alone.vo.HomeOptionVO;
import com.home.alone.vo.HomeOverviewVO;
import com.home.alone.vo.HomePriceVO;
import com.home.alone.vo.HomeReportVO;
import com.home.alone.vo.HomeReservOverviewVO;
import com.home.alone.vo.HomeReservVO;
import com.home.alone.vo.HomeVO;

public interface HomeDAO {
	// --- 매물 정보 삽입 ---
	int insertHome(HomeVO homeVO);	// 매물 기본 정보 테이블에 insert
	int insertHomeImgList(List<HomeImgVO> homeImgList);	// 매물 사진 insert
	int insertHomeOption(HomeOptionVO homeOptionVO);	// 매물 옵션 insert
	int insertHomeAddInfo(HomeAddInfoVO homenAddInfoVO); // 매물 추가 정보 insert
	int insertHomePrice(HomePriceVO homePriceVO);	// 매물 가격 insert
	int insertHomeReport(HomeReportVO homeReportVO);	// 매물 신고 정보 insert
	
	List<HomeOverviewVO> selectHomeInBoundsList(Map<String, Object> mapBounds);	// 현재 지도 경계내 매물 리스트 반환(미리보기 리스트)
	List<HomeOverviewVO> selectHomeListByFilter(Map<String, Object> filterData);
	HomeImgVO selectPreviewHomeImg(int homeNum);	// 매물 미리보기시 나올 사진
	HomeDetailVO selectHomeDetail(int homeNum);		// 매물 정보 
	HomeAddInfoVO selectHomeAddInfo(int homeNum);	// 매물 추가 정보
	HomeOptionVO selectHomeOption(int homeNum);		// 매물 옵션 정보
	List<HomeImgVO> selectHomeImgList(int homeNum); // 해당 매물 사진들 반환

	int updateHome(HomeVO homeVO);	// 매물 테이블 수정
	int updateHomeAddInfo(HomeAddInfoVO homeAddInfoVO);	// 매물 추가 정보 테이블 수정
	int updateHomeOption(HomeOptionVO homeOptionVO);	// 매물 옵션 정보 테이블 수정
	int updateHomePrice(HomePriceVO HomePriceVO);	// 매물 가격 테이블 수정
	int updateHomeInvalid(int homeNum);	// 게시 중단 상태로 변경
	int updateHomeValid(int homeNum);	// 게시중 상태로 변경
	int updateHomeValidStop(int homeNum);	// 매물신고 처리 됐을 때 
	
	int deleteHomeImg(int homeNum);	// 매물 사진 정보 삭제
	int deleteHome(int homeNum);	// 매물 삭제
	int deleteHomeReserv(int reservNum);	// 예약 삭제
	
	List<HomeOverviewVO> selectHomeListByLessorId(LessorVO vo);
	List<HomeReportVO> selectReportHomeList();	// 매물 신고 목록
	
}
