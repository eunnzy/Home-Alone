package com.home.alone.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.home.alone.vo.HomeReservOverviewVO;
import com.home.alone.vo.HomeReservVO;

@Repository
public class HomeReservDAOImpl implements HomeReservDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	private static final String HOMERESERVMAPPER = "com.home.alone.mapper.HomeReservMapper.";
	
	@Override
	public int insertHomeReserv(HomeReservVO homeReservVO) {
		return sqlSession.insert(HOMERESERVMAPPER + "insertHomeReserv", homeReservVO);
	}

	@Override
	public List<String> selectHomeReservInvalidTimeList(HomeReservVO homeReservVO) {
		return sqlSession.selectList(HOMERESERVMAPPER + "selectHomeReservInvalidTimeList", homeReservVO);
	}
	
	@Override
	public List<HomeReservOverviewVO> selectHomeReservListByImchaId(String imchaId) {
		return sqlSession.selectList(HOMERESERVMAPPER + "selectHomeReservListByImchaId", imchaId);
	}

	@Override
	public List<HomeReservOverviewVO> selectHomeReservListByLessorId(String lessorId) {
		return sqlSession.selectList(HOMERESERVMAPPER + "selectHomeReservListByLessorId", lessorId);
	}
	
	@Override
	public int selectHomeReservCountImcha(String imchaId) {
		return sqlSession.selectOne(HOMERESERVMAPPER + "selectHomeReservCountImcha" , imchaId);
	}


	@Override
	public int deleteHomeReserv(int reservNum) {
		return sqlSession.delete(HOMERESERVMAPPER + "deleteHomeReserv", reservNum);
	}

	@Override
	public int updateHomeReservReject(int revNum) {
		return sqlSession.update(HOMERESERVMAPPER + "updateHomeReservReject", revNum);
	}


	@Override
	public int updateHomeReservCancel(int revNum) {
		return sqlSession.update(HOMERESERVMAPPER + "updateHomeReservCancel", revNum);
	}
	
	@Override
	public int updateHomeReservAccept(int revNum) {
		return sqlSession.update(HOMERESERVMAPPER + "updateHomeReservAccept", revNum);
	}



}
