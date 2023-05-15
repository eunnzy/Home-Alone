package com.home.alone.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.home.alone.vo.HomeLikeVO;
import com.home.alone.vo.HomeOverviewVO;

@Repository
public class HomeLikeDAOImpl implements HomeLikeDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	private static final String HOMELIKEMAPPER = "com.home.alone.mapper.HomeLikeMapper.";

	
	@Override
	public int insertHomeLike(HomeLikeVO homeLikeVO) {
		return  sqlSession.insert(HOMELIKEMAPPER + "insertHomeLike", homeLikeVO);
	}

	@Override
	public List<HomeOverviewVO> selectHomeLikeList(String imchaId) {
		return sqlSession.selectList(HOMELIKEMAPPER + "selectHomeLikeList", imchaId);
	}

	@Override
	public int selectHomeLikeCheck(HomeLikeVO homeLikeVO) {
		return sqlSession.selectOne(HOMELIKEMAPPER + "selectHomeLikeCheck", homeLikeVO);
	}

	@Override
	public int deleteHomeLike(HomeLikeVO homeLikeVO) {
		return sqlSession.delete(HOMELIKEMAPPER + "deleteHomeLike", homeLikeVO);
	}

}
