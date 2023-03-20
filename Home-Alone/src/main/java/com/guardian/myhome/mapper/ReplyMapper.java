package com.guardian.myhome.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.guardian.myhome.vo.Criteria;
import com.guardian.myhome.vo.ReplyVO;

public interface ReplyMapper {
	
	// 등록 -> 게시물이 있어야 등록 가능 (FK관계) 
	public int insert(ReplyVO vo);
	
	// 특정 댓글 조회 
	public ReplyVO read(Long rno);

	// 삭제 
	public int delete(Long rno);
	
	// 수정 
	public int update(ReplyVO reply);
	
	// 페이징 처리 
	public List<ReplyVO> getListWithPaging(
		   @Param("cri") Criteria cri, @Param("bno") Long bno);
}
