package com.home.alone.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.home.alone.board.vo.BoardVO;
import com.home.alone.util.Criteria;

public interface BoardMapper {
	// 로그인 전 
	List<BoardVO> beforeBoard(Criteria cri);
	
	// 로그인 전 갯수
	int beforeBoardCount(Criteria cri);
		
	// 로그인 후 
	// Criteria cri, String sido1, String gugun1
	List<BoardVO> afterBoard(Criteria cri);
		
	// 로그인 후 갯수
	// Criteria cri, String sido1, String gugun1
	int afterBoardCount(Criteria cri);
	
	// 내가 쓴 글 목록 리스트 
	// Criteria cri, String imchaid
	List<BoardVO> getMyboard(Criteria cri);
		
	// 내가 쓴 글 갯수 
	int getMyboardCount(Criteria cri);
		
	// 등록 
	void insertSelectKey(BoardVO board);
			
	// 조회
	BoardVO read(@Param("bno") Long bno);
			
	// 삭제
	int delete(Long bno);
			
	// 수정
	int update(BoardVO board);
	
	// 좋아요 증가
	int likesUp(Long bno);
			
	// 좋아요 감소
	int likesDown(Long bno);
		
	// 조회수 증가
	int viewsUp(Long bno);
	
	// 댓글수 증가
	int replysUp(Long bno);
				
	// 댓글수 감소
	int replysDown(Long bno);
	
}
