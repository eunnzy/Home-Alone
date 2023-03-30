package com.home.alone.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.home.alone.vo.HomePreviewVO;
import com.home.alone.vo.HomeReportVO;
import com.home.alone.vo.HomeReservVO;
import com.home.alone.vo.HomeVO;
import com.home.alone.vo.LessorVO;


public interface HomeService {
	int insertHome(Map<String, Object> insertMap);
	List<HomePreviewVO> homeInBoundsList(Map<String, Object> mapBounds);
	List<HomePreviewVO> homeListByFilter(Map<String, Object> filterData);
	List<HomeVO> selectAllHomeList();	// 모든 매물 리스트
	Map<String, Object> selectHomeDetail(int homeNum);	// 매물 상세 정보 반환 
	
	// List reservHomeListByLessor(LessorVO lessor);
	
	
	String convertMoneyUnit(long money); 
	int modifyHomeInfo(Map<String, Object> modifyHome);	// 매물 정보 수정
	int reportHome(HomeReportVO homeReportVO);	// 매물 신고
	List<HomeReportVO> selectReportHomeList();
	List<HomePreviewVO> getListByLessorId(LessorVO vo);	// 중개인이 등록한 매물 리스트
	int deleteHome(int homeNum);	
	int reservHome(HomeReservVO homeReservVO);	// 예약하기	
	List<String> getValidTimeList(HomeReservVO homeReservVO);	// 예약된 시간 리스트
	
}