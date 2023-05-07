package com.home.alone.service;

import java.util.List;
import java.util.Map;

import com.home.alone.member.vo.LessorVO;
import com.home.alone.vo.HomeInqAnswerVO;
import com.home.alone.vo.HomeInquryDetailVO;
import com.home.alone.vo.HomeInquryVO;
import com.home.alone.vo.HomePreviewVO;
import com.home.alone.vo.HomeReportVO;
import com.home.alone.vo.HomeReservPreviewVO;
import com.home.alone.vo.HomeReservVO;


public interface HomeService {
	int registerHome(Map<String, Object> insertMap);	// 매물 등록
	List<HomePreviewVO> homeInBoundsList(Map<String, Object> mapBounds);	// 지도 경계 내 매물 리스트
	List<HomePreviewVO> homeListByFilter(Map<String, Object> filterData);	// 매물 필터 검색
	Map<String, Object> getHomeDetail(int homeNum);	// 매물 상세 정보 반환 
	String convertMoneyUnit(long money); 
	int modifyHomeInfo(Map<String, Object> modifyHome);	// 매물 정보 수정
	int reportHome(HomeReportVO homeReportVO);	// 매물 신고
	List<HomeReportVO> getReportHomeList();		// 매물 신고 리스트	
	List<HomePreviewVO> getListByLessorId(LessorVO vo);	// 중개인이 등록한 매물 리스트
	int changeHomeStatusStop(int homeNum);	// 매물 상태 - 게시중단으로 변경
	int changeHomeStatusPost(int homeNum);	// 매물 상태 - 게시중으로 변경
	int deleteHome(int homeNum);	// 매물 삭제
}