package com.guardian.myhome.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.guardian.myhome.vo.BoardVO;
import com.guardian.myhome.vo.Criteria;

public interface BoardMapper {
	// 로그인 전 
	public List<BoardVO> beforeBoard(Criteria cri);
	
	// 로그인 전 갯수
	public int beforeBoardCount(Criteria cri);
		
	// 로그인 후 
	// Criteria cri, String sido1, String gugun1
	public List<BoardVO> afterBoard(Criteria cri);
		
	// 로그인 후 갯수
	// Criteria cri, String sido1, String gugun1
	public int afterBoardCount(Criteria cri);
	
	// 내가 쓴 글 목록 리스트 
	// Criteria cri, String imchaid
	public List<BoardVO> getMyboard(Criteria cri);
		
	// 내가 쓴 글 갯수 
	public int getMyboardCount(Criteria cri);
		
	// 등록 
	public void insertSelectKey(BoardVO board);
			
	// 조회
	public BoardVO read(@Param("bno") Long bno);
			
	// 삭제
	public int delete(Long bno);
			
	// 수정
	public int update(BoardVO board);
	
	// 좋아요 증가
	public int likesUp(Long bno);
			
	// 좋아요 감소
	public int likesDown(Long bno);
		
	// 조회수 증가
	public int viewsUp(Long bno);
	
	// 댓글수 증가
	public int replysUp(Long bno);
				
	// 댓글수 감소
	public int replysDown(Long bno);
	
}
