package com.home.alone.service;

import java.util.List;
import java.util.Map;

import com.home.alone.vo.HomeInqAnswerVO;
import com.home.alone.vo.HomeInquryDetailVO;
import com.home.alone.vo.HomeInquryVO;
import com.home.alone.vo.HomePreviewVO;
import com.home.alone.vo.HomeReportVO;
import com.home.alone.vo.HomeReservPreviewVO;
import com.home.alone.vo.HomeReservVO;
import com.home.alone.vo.LessorVO;


public interface HomeService {
	int registerHome(Map<String, Object> insertMap);
	List<HomePreviewVO> homeInBoundsList(Map<String, Object> mapBounds);
	List<HomePreviewVO> homeListByFilter(Map<String, Object> filterData);
	Map<String, Object> getHomeDetail(int homeNum);	// 매물 상세 정보 반환 
	// List reservHomeListByLessor(LessorVO lessor);
	String convertMoneyUnit(long money); 
	int modifyHomeInfo(Map<String, Object> modifyHome);	// 매물 정보 수정
	int reportHome(HomeReportVO homeReportVO);	// 매물 신고
	List<HomeReportVO> getReportHomeList();	
	List<HomePreviewVO> getListByLessorId(LessorVO vo);	// 중개인이 등록한 매물 리스트
	int deleteHome(int homeNum);	// 매물 삭제
	int inquryHome(HomeInquryVO homeInquryVO);	// 문의글 등록
	List<HomeInquryVO> getHomeInqListByImcha(String imchaId);	// 문의글 리스트(일반회원)
	List<HomeInquryVO> getHomeInqListByLessor(String lessorId);	// 문의글 리스트(일반회원)
	int reservHome(HomeReservVO homeReservVO);	// 예약하기	
	List<String> getValidTimeList(HomeReservVO homeReservVO);	// 예약된 시간 리스트
	// HomeInquryDetailVO getHomeInqDetail(int iqNum);	// 문의 작성글 자세히 보기.
	HomeInquryDetailVO getHomeInqDetail(int iqNum);
	List<HomeInqAnswerVO> getHomeInqAnswerList(int iqNum);	// 문의 답변 댓글 리스트
	int registerHomeInqAnswer(HomeInqAnswerVO homeInqAnswerVO);	// 답변 등록
	int homeAnsIdCheck(String lessorId);	// 답변 수정을 위한 아이디 체크
	int modifyHomeInqAnswer(HomeInqAnswerVO homeInqAnswerVO);	// 답변 수정
	List<HomeReservPreviewVO> getReservListByImcha(String imchaId);	// 일반회원 예약 목록
	List<HomeReservPreviewVO> getReservListByLessor(String lessorId);	// 중개인 예약 목록
}