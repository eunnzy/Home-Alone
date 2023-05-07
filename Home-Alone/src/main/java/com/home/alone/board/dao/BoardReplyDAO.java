package com.home.alone.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.home.alone.board.vo.BoardReplyVO;
import com.home.alone.util.Criteria;

public interface BoardReplyDAO {
		// 등록 -> 게시물이 있어야 등록 가능 (FK관계) 
		int insertBoardReply(BoardReplyVO vo);
		
		// 특정 댓글 조회 
		BoardReplyVO selectBoardReply(Long rno);

		// 삭제 
		int deleteBoardReply(Long rno);
		
		// 수정 
		int updateBoardReply(BoardReplyVO reply);
		
		// 댓글 리스트 조회
		List<BoardReplyVO> selectBoardReplyListWithPaging(Long bno);	// 특정 게시물에 대한 댓글 
}
