package com.home.alone.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.home.alone.vo.HomeDetailVO;
import com.home.alone.vo.HomeImgVO;
import com.home.alone.vo.HomeOptionVO;
import com.home.alone.vo.HomePreviewVO;
import com.home.alone.vo.HomePriceVO;
import com.home.alone.vo.HomeReportVO;
import com.home.alone.vo.HomeVO;

/*
	mybatis를 연결하기 위한 mapper
	매물 관련 mapper
*/
public interface HomeMapper {
	int insertHome(HomeVO homeVO);	// 매물 테이블에 삽입
	int insertHomeImgList(List<HomeImgVO> homeImgList);	// 매물 사진 테이블에 삽입
	int insertHomePrice(HomePriceVO homePriceVO);	// 매물 가격 테이블에 삽입
	int insertHomeOptionList(List<HomeOptionVO> homeOptionList);	// 매물 옵션 테이블에 삽입
	int insertHomeReport(HomeReportVO homeReportVO);	// 매물 신고 정보 삽입
	
	List<HomePreviewVO> selectHomeInBoundsList(Map<String, Object> mapBounds);	// 현재 지도 경계내 매물 리스트 반환(미리보기 리스트)
	HomeImgVO selectPreviewHomeImg(int homeNum);	// 매물 미리보기시 나올 사진
	HomeDetailVO selectHomeDetail(int homeNum);	// 매물 정보 반환
	List<HomeImgVO> selectHomeImgList(int homeNum); // 해당 매물 사진들 반환
	List<String> selectHomeOptionList(int homeNum); // 해당 매물 옵션 목록 반환
	
	//List<HomeReportVO> selectReportHomeList();	// 매물 신고리스트 조회
	
	int updateHome(HomeVO homeVO);	// 매물 테이블 수정
	int updateHomePrice(HomePriceVO HomePriceVO);	// 매물 가격 테이블 수정
	int deleteHomeOption(int homeNum);	// 매물 옵션 삭제
	int deleteHomeImg(int homeNum);	// 매물 사진 정보 삭제
	
	public List<HomePreviewVO> getListByLessorId(String lessorId);
	public void deleteHome(@Param("homeNum")int homeNum, @Param("lessorId")String lessorId);
	
} 
