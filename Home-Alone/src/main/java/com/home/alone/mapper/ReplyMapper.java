package com.home.alone.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.home.alone.board.vo.BoardReplyVO;
import com.home.alone.util.Criteria;

public interface ReplyMapper {
	
	// 등록 -> 게시물이 있어야 등록 가능 (FK관계) 
	public int insert(BoardReplyVO vo);
	
	// 특정 댓글 조회 
	public BoardReplyVO read(Long rno);

	// 삭제 
	public int delete(Long rno);
	
	// 수정 
	public int update(BoardReplyVO reply);
	
	// 페이징 처리 
	public List<BoardReplyVO> getListWithPaging(
		   @Param("cri") Criteria cri, @Param("bno") Long bno);
}
