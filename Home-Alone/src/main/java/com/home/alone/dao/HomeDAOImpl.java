package com.home.alone.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.home.alone.vo.HomeAddInfoVO;
import com.home.alone.vo.HomeDetailVO;
import com.home.alone.vo.HomeImgVO;
import com.home.alone.vo.HomeOptionVO;
import com.home.alone.vo.HomePreviewVO;
import com.home.alone.vo.HomePriceVO;
import com.home.alone.vo.HomeReportVO;
import com.home.alone.vo.HomeVO;
import com.home.alone.vo.LessorVO;

@Repository
public class HomeDAOImpl implements HomeDAO{
	@Autowired
	private SqlSessionTemplate sqlSession;
	private static final String HOMEMAPPER = "com.home.alone.mapper.HomeMapper.";
	
	@Override
	public int insertHome(HomeVO homeVO) {
		return sqlSession.insert(HOMEMAPPER + "insertHome", homeVO);
	}

	@Override
	public int insertHomeImgList(List<HomeImgVO> homeImgList) {
		return sqlSession.insert(HOMEMAPPER + "insertHomeImgList", homeImgList);
	}
	
	@Override
	public int insertHomeOption(HomeOptionVO homeOptionVO) {
		return sqlSession.insert(HOMEMAPPER + "insertHomeOption", homeOptionVO);
	}
	
	@Override
	public int insertHomeAddInfo(HomeAddInfoVO homenAddInfoVO) {
		return sqlSession.insert(HOMEMAPPER + "insertHomeAddInfo", homenAddInfoVO);
	}

	@Override
	public int insertHomePrice(HomePriceVO homePriceVO) {
		return sqlSession.insert(HOMEMAPPER + "insertHomePrice", homePriceVO);
	}

	@Override
	public int insertHomeReport(HomeReportVO homeReportVO) {
		return sqlSession.insert(HOMEMAPPER + "insertHomeReport", homeReportVO);
	}
	
	@Override
	public List<HomePreviewVO> selectHomeInBoundsList(Map<String, Object> mapBounds) {
		return sqlSession.selectList(HOMEMAPPER + "selectHomeInBoundsList", mapBounds);
	}

	@Override
	public List<HomePreviewVO> selectHomeListByFilter(Map<String, Object> filterData) {
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
	public List<HomePreviewVO> getListByLessorId(LessorVO vo) {
		System.out.println(vo.getLessorId());
		return sqlSession.selectList(HOMEMAPPER+ "selectListByLessorId", vo);
	}

	@Override
	public int deleteHome(int homeNum) {
		return  sqlSession.selectOne(HOMEMAPPER+ "deleteHome", homeNum);
	}

	@Override
	public HomeAddInfoVO selectHomeAddInfo(int homeNum) {
		return sqlSession.selectOne(HOMEMAPPER +"selectHomeAddInfo", homeNum);
	}

	@Override
	public HomeOptionVO selectHomeOption(int homeNum) {
		return sqlSession.selectOne(HOMEMAPPER +"selectHomeOption", homeNum);
	}

	

}