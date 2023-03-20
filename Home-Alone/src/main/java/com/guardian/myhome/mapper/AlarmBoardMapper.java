package com.guardian.myhome.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guardian.myhome.vo.AlarmBoardVO;

public interface AlarmBoardMapper {
	// 목록 
	public List<AlarmBoardVO> alarmBoard();
		
	// 등록 
	public void insertalarmBoard(AlarmBoardVO board);
			
	// 조회
	public AlarmBoardVO alarmBoardread(@Param("ano") Long ano);
			
	// 삭제
	public int alarmBoarddelete(Long ano);
			
	// 수정
	public int alarmBoardupdate(AlarmBoardVO board);
}
