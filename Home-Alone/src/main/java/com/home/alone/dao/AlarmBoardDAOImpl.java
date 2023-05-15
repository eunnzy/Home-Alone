package com.home.alone.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.home.alone.board.vo.AlarmBoardAttachVO;
import com.home.alone.board.vo.AlarmBoardVO;

@Repository
public class AlarmBoardDAOImpl implements AlarmBoardDAO{
	@Autowired
	private SqlSessionTemplate sqlSession;
	private static final String ABMAPPER = "com.home.alone.mapper.AlarmBoardMapper.";
	
	
	@Override
	public List<AlarmBoardVO> selectAlarmBoardList() {
		return sqlSession.selectList(ABMAPPER + "selectAlarmBoardList");
	}

	@Override
	public AlarmBoardVO selectAlarmBoardDetail(long ano) {
		return sqlSession.selectOne(ABMAPPER + "selectAlarmBoardDetail", ano);
	}

	@Override
	public int insertAlarmBoard(AlarmBoardVO alarmBoardVO) {
		return sqlSession.insert(ABMAPPER+"insertAlarmBoard", alarmBoardVO);
	}

	@Override
	public int deleteAlarmBoard(long ano) {
		return sqlSession.delete(ABMAPPER + "deleteAlarmBoard", ano);
	}

	@Override
	public int updateAlarmBoard(AlarmBoardVO alarmBoardVO) {
		return sqlSession.update(ABMAPPER + "updateAlarmBoard" , alarmBoardVO);
	}

	@Override
	public int insertAlarmAttach(AlarmBoardAttachVO attachVO) {
		return sqlSession.insert(ABMAPPER + "insertAlarmAttach", attachVO);
		
	}

	@Override
	public int deleteAlarmAttach(String uuid) {
		return sqlSession.delete(ABMAPPER + "deleteAlarmAttach", uuid);
	}

	@Override
	public List<AlarmBoardAttachVO> selectAlarmAttachList(long ano) {
		return sqlSession.selectList(ABMAPPER + "selectAlarmAttachList", ano);
	}

	@Override
	public int deleteAlarmAttachAll(long ano) {
		return sqlSession.delete(ABMAPPER + "deleteAlarmAttachAll", ano);
	}

	@Override
	public AlarmBoardAttachVO selectOldFileCheck() {
		return null;
	}
	
	
	
}
