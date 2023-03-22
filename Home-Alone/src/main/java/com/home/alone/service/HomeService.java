package com.home.alone.service;

import java.util.List;
import java.util.Map;

import com.home.alone.vo.HomeImgVO;
import com.home.alone.vo.HomePreviewVO;
import com.home.alone.vo.HomeReportVO;
import com.home.alone.vo.HomeVO;
import com.home.alone.vo.LessorVO;


public interface HomeService {
	int insertHome(Map<String, Object> insertMap);
	List<HomePreviewVO> homeInBoundsList(Map<String, Object> mapBounds);
	List<HomeVO> selectAllHomeList();	// 모든 매물 리스트
	Map<String, Object> selectHomeDetail(int homeNum);	// 매물 상세 정보 반환 
	String convertMoneyUnit(long money); 
	HomeImgVO previewHomeImg(int homeNum);	// 매물 미리보기시 나올 사진
	int modifyHomeInfo(Map<String, Object> modifyHome);	// 매물 정보 수정
	int reportHome(HomeReportVO homeReportVO);	// 매물 신고
	
	List<HomeReportVO> selectReportHomeList();
	public List<HomePreviewVO> getListByLessorId(LessorVO vo);
	public int deleteHome(int homeNum);
}