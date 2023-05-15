package com.home.alone.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.home.alone.member.vo.LessorVO;
import com.home.alone.vo.HomeAddInfoVO;
import com.home.alone.vo.HomeDetailVO;
import com.home.alone.vo.HomeImgVO;
import com.home.alone.vo.HomeOptionVO;
import com.home.alone.vo.HomeOverviewVO;
import com.home.alone.vo.HomePriceVO;
import com.home.alone.vo.HomeReportVO;
import com.home.alone.vo.HomeReservOverviewVO;
import com.home.alone.vo.HomeReservVO;
import com.home.alone.vo.HomeVO;

@Repository
public class HomeDAOImpl implements HomeDAO{
	@Autowired
	private SqlSessionTemplate sqlSession;
	private static final String HOMEMAPPER = "com.home.alone.mapper.HomeMapper.";
	
	@Override
	public int insertHome(HomeVO homeVO) {  // 매물 기본 정보 테이블에 insert
		return sqlSession.insert(HOMEMAPPER + "insertHome", homeVO);
	}

	@Override
	public int insertHomeImgList(List<HomeImgVO> homeImgList) { // 매물 사진 경로 insert
		return sqlSession.insert(HOMEMAPPER + "insertHomeImgList", homeImgList);
	}
	
	@Override
	public int insertHomeOption(HomeOptionVO homeOptionVO) { // 매물 옵션 정보 insert
		return sqlSession.insert(HOMEMAPPER + "insertHomeOption", homeOptionVO);
	}
	
	@Override
	public int insertHomeAddInfo(HomeAddInfoVO homenAddInfoVO) { // 매물 추가 정보 insert
		return sqlSession.insert(HOMEMAPPER + "insertHomeAddInfo", homenAddInfoVO);
	}

	@Override
	public int insertHomePrice(HomePriceVO homePriceVO) { // 매물 가격 정보 insert
		return sqlSession.insert(HOMEMAPPER + "insertHomePrice", homePriceVO);
	}

	@Override
	public int insertHomeReport(HomeReportVO homeReportVO) {	// 매물 신고 정보 insert
		return sqlSession.insert(HOMEMAPPER + "insertHomeReport", homeReportVO);
	}
	
	
	@Override
	public List<HomeOverviewVO> selectHomeInBoundsList(Map<String, Object> mapBounds) {
		return sqlSession.selectList(HOMEMAPPER + "selectHomeInBoundsList", mapBounds);
	}

	@Override
	public List<HomeOverviewVO> selectHomeListByFilter(Map<String, Object> filterData) {
		return sqlSession.selectList(HOMEMAPPER + "selectHomeListByFilter", filterData);
	}
	
	@Override
	public HomeImgVO selectPreviewHomeImg(int homeNum) {
		return sqlSession.selectOne(HOMEMAPPER +"selectPreviewHomeImg", homeNum);
	}
	
	public HomeDetailVO selectHomeDetail(int homeNum) {
		return sqlSession.selectOne(HOMEMAPPER +"selectHomeDetail", homeNum);
	}

	@Override
	public List<HomeImgVO> selectHomeImgList(int homeNum) {
		return sqlSession.selectList(HOMEMAPPER + "selectHomeImgList", homeNum);
	}

	@Override
	public int updateHome(HomeVO homeVO) {
		return sqlSession.update(HOMEMAPPER + "updateHome", homeVO);
	}
	
	@Override
	public int updateHomeAddInfo(HomeAddInfoVO homeAddInfoVO) {
		// TODO Auto-generated method stub
		return sqlSession.update(HOMEMAPPER + "updateHomeAddInfo", homeAddInfoVO);
	}

	@Override
	public int updateHomeOption(HomeOptionVO homeOptionVO) {
		return sqlSession.update(HOMEMAPPER + "updateHomeOption", homeOptionVO);
	}

	
	@Override
	public int updateHomePrice(HomePriceVO homePriceVO) {
		return sqlSession.update(HOMEMAPPER + "updateHomePrice", homePriceVO);
	}
	
	

	@Override
	public int deleteHomeImg(int homeNum) {
		return sqlSession.delete(HOMEMAPPER + "deleteHomeImg", homeNum);
	}
	
	public int deleteHomeOption(int homeNum) {
		return sqlSession.delete(HOMEMAPPER + "deleteHomeOption", homeNum);
	}

	@Override
	public List<HomeReportVO> selectReportHomeList() {
		return sqlSession.selectList(HOMEMAPPER + "selectReportHomeList");
	}
	
	@Override
	public List<HomeOverviewVO> selectHomeListByLessorId(LessorVO vo) {
		System.out.println(vo.getLessorId());
		return sqlSession.selectList(HOMEMAPPER+ "selectHomeListByLessorId", vo);
	}

	@Override
	public int deleteHome(int homeNum) {
		return  sqlSession.delete(HOMEMAPPER+ "deleteHome", homeNum);
	}
	
	@Override
	public int deleteHomeReserv(int reservNum) {
		return sqlSession.selectOne(HOMEMAPPER +"deleteHomeReserv", reservNum);
	}


	@Override
	public HomeAddInfoVO selectHomeAddInfo(int homeNum) {
		return sqlSession.selectOne(HOMEMAPPER +"selectHomeAddInfo", homeNum);
	}

	@Override
	public HomeOptionVO selectHomeOption(int homeNum) {
		return sqlSession.selectOne(HOMEMAPPER +"selectHomeOption", homeNum);
	}

	@Override
	public int updateHomeInvalid(int homeNum) {
		return sqlSession.update(HOMEMAPPER + "updateHomeInvalid", homeNum);
	}

	@Override
	public int updateHomeValid(int homeNum) {
		return sqlSession.update(HOMEMAPPER + "updateHomeValid", homeNum);
	}

	@Override
	public int updateHomeValidStop(int homeNum) {
		return sqlSession.update(HOMEMAPPER + "updateHomeValidStop", homeNum);
	}



}