package com.home.alone.board.service;

import java.util.List;

import com.home.alone.board.vo.BoardReplyVO;
import com.home.alone.util.Criteria;

public interface BoardReplyService {
	
	// 등록
	int register(BoardReplyVO vo);
	
	// 조회
	BoardReplyVO get(Long rno);
	
	// 수정 
	int modify(BoardReplyVO vo);
	
	// 삭제 
	int remove(Long rno);
	
	// 게시물별 댓글
	List<BoardReplyVO> getList(Long bno);
//	List<BoardReplyVO> getList(Criteria cri, Long bno);
}
