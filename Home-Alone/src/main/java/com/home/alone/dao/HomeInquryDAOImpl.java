package com.home.alone.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.home.alone.vo.HomeInqAnswerVO;
import com.home.alone.vo.HomeInquryDetailVO;
import com.home.alone.vo.HomeInquryVO;


@Repository
public class HomeInquryDAOImpl implements HomeInquryDAO{
	@Autowired
	private SqlSessionTemplate sqlSession;
	private static final String HOMEINQURYMAPPER = "com.home.alone.mapper.HomeInquryMapper.";
	
	
	@Override
	public int insertHomeInqury(HomeInquryVO homeInquryVO) {
		return sqlSession.insert(HOMEINQURYMAPPER + "insertHomeInqury", homeInquryVO);
	}

	@Override
	public int insertHomeInqAnswer(HomeInqAnswerVO homeInqAnswerVO) {
		return sqlSession.insert(HOMEINQURYMAPPER + "insertHomeInqAnswer", homeInqAnswerVO);
	}
	
	@Override
	public int selectInqAnswerIdCheck(String lessorId) {
		return sqlSession.selectOne(HOMEINQURYMAPPER + "selectInqAnswerIdCheck", lessorId);
	}

	@Override
	public List<HomeInquryVO> selectInquryListByImcha(String imchaId) {
		return sqlSession.selectList(HOMEINQURYMAPPER + "selectInquryListByImcha", imchaId);
	}

	@Override
	public List<HomeInquryVO> selectInquryListByLeessor(String lessorId) {
		return sqlSession.selectList(HOMEINQURYMAPPER + "selectInquryListByLeessor", lessorId);
	}

	@Override
	public HomeInquryDetailVO selectHomeInquryDetail(int iqNum) {
		return sqlSession.selectOne(HOMEINQURYMAPPER + "selectHomeInquryDetail", iqNum);
	}
	
	@Override
	public List<HomeInqAnswerVO> selectInqAnswer(int iqNum) {
		return sqlSession.selectList(HOMEINQURYMAPPER + "selectInqAnswer", iqNum);
	}

	@Override
	public int deleteHomeInqAnswer(int iqNum) {
		return 0;
	}

	@Override
	public int deleteHomeInqury(HomeInquryVO homeInquryVO) {
		return 0;
	}
	
	@Override
	public int updateHomeInquryStatus(int iqNum) {
		return sqlSession.update(HOMEINQURYMAPPER + "updateHomeInquryStatus", iqNum);
	}

	@Override
	public int updateHomeInqAnswer(HomeInqAnswerVO homeInqAnswerVO) {
		return sqlSession.update(HOMEINQURYMAPPER + "updateHomeInqAnswer", homeInqAnswerVO);
	}

	


	
}
