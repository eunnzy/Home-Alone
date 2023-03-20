package com.guardian.myhome.service;

import java.util.List;

import com.guardian.myhome.vo.Criteria;
import com.guardian.myhome.vo.ReplyVO;

public interface ReplyService {
	
	// 등록
	public int register(ReplyVO vo);
	
	// 조회
	public ReplyVO get(Long rno);
	
	// 수정 
	public int modify(ReplyVO vo);
	
	// 삭제 
	public int remove(Long rno);
	
	// 게시물별 댓글
	public List<ReplyVO> getList(Criteria cri, Long bno);
}
