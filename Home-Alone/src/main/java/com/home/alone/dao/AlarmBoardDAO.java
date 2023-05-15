package com.home.alone.dao;

import java.util.List;

import com.home.alone.board.vo.AlarmBoardAttachVO;
import com.home.alone.board.vo.AlarmBoardVO;

public interface AlarmBoardDAO {
	List<AlarmBoardVO> selectAlarmBoardList();	// 공지사항 목록
	AlarmBoardVO selectAlarmBoardDetail(long ano);	// 공지사항 조회
	int insertAlarmBoard(AlarmBoardVO alarmBoardVO);	// 공지사항 등록
	int deleteAlarmBoard(long ano);	// 공지사항 삭제
	int updateAlarmBoard(AlarmBoardVO alarmBoardVO);	// 공지사항 수정
	int insertAlarmAttach(AlarmBoardAttachVO attachVO);
	int deleteAlarmAttach(String uuid);
	List<AlarmBoardAttachVO> selectAlarmAttachList(long ano);
	int deleteAlarmAttachAll(long ano);
	AlarmBoardAttachVO selectOldFileCheck();
	
}
