package com.guardian.myhome.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guardian.myhome.vo.HomeDetailVO;
import com.guardian.myhome.vo.HomeImgVO;
import com.guardian.myhome.vo.HomeOptionVO;
import com.guardian.myhome.vo.HomePreviewVO;
import com.guardian.myhome.vo.HomePriceVO;
import com.guardian.myhome.vo.HomeReportVO;
import com.guardian.myhome.vo.HomeVO;
import com.guardian.myhome.vo.LessorVO;

@Repository
public class HomeDAOImpl implements HomeDAO{
	@Autowired
	private SqlSessionTemplate sqlSession;
	private static final String HOMEMAPPER = "com.guardian.myhome.mapper.HomeMapper.";
	
	@Override
	public int insertHome(HomeVO homeVO) {
		return sqlSession.insert(HOMEMAPPER + "insertHome", homeVO);
	}

	@Override
	public int insertHomeImgList(List<HomeImgVO> homeImgList) {
		return sqlSession.insert(HOMEMAPPER + "insertHomeImgList", homeImgList);
	}
	

	@Override
	public int insertHomePrice(HomePriceVO homePriceVO) {
		return sqlSession.insert(HOMEMAPPER + "insertHomePrice", homePriceVO);
	}

	@Override
	public int insertHomeOptionList(List<HomeOptionVO> homeOptionList) {
		return sqlSession.insert(HOMEMAPPER + "insertHomeOptionList", homeOptionList);
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
	public List<String> selectHomeOptionList(int homeNum) {
		return sqlSession.selectList(HOMEMAPPER + "selectHomeOptionList", homeNum);
	}

	@Override
	public int updateHome(HomeVO homeVO) {
		return sqlSession.update(HOMEMAPPER + "updateHome", homeVO);
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

}