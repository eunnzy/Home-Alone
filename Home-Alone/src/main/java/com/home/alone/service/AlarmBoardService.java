package com.home.alone.service;

import java.util.List;

import com.home.alone.board.vo.AlarmBoardAttachVO;
import com.home.alone.board.vo.AlarmBoardVO;

public interface AlarmBoardService {
	// 목록 
	public List<AlarmBoardVO> getAlarmBoardList();
		
	// 등록 
	public void abregister(AlarmBoardVO board);
				
	// 조회
	public AlarmBoardVO abget(Long ano);
				
	// 삭제
	public boolean abremove(Long ano);
				
	// 수정
	public boolean abmodify(AlarmBoardVO board);
	
	// 파일 업로드 조회 
	public List<AlarmBoardAttachVO> getabAttachList(Long ano);

}
