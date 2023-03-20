package com.guardian.myhome.service;

import java.util.List;
import java.util.Map;

import com.guardian.myhome.vo.BoardAttachVO;
import com.guardian.myhome.vo.BoardLikesVO;
import com.guardian.myhome.vo.BoardVO;
import com.guardian.myhome.vo.Criteria;


public interface BoardService {
	// 로그인 전 
	public List<BoardVO> beforeBoard(Criteria cri);
	
	// 로그인 전 갯수
	public int beforeBoardCount(Criteria cri);
			
	// 로그인 후 
	public List<BoardVO> afterBoard(Criteria cri);
					
	// 로그인 후 갯수
	public int afterBoardCount(Criteria cri);
	
	// 내가 쓴 글 목록 리스트 
	public List<BoardVO> getMyboard(Criteria cri);
				
	// 내가 쓴 글 갯수 
	public int getMyboardCount(Criteria cri);
		
	// 등록 
	public void register(BoardVO board);
			
	// 조회
	public BoardVO get(Long bno);
	
	// 파일 업로드 조회 
	public List<BoardAttachVO> getAttachList(Long bno);
			
	// 수정 
	public boolean modify(BoardVO board);
			
	// 삭제
	public boolean remove(Long bno);
	
	// 좋아요 UP
	public boolean likesUp(Long bno);
		
	// 좋아요 DOWN
	public boolean likesDown(Long bno);
	
	// 좋아요 ON 
	public void likesOn(Long bno, String userid);
	
	// 좋아요 OFF 
	public void likesOff(Long bno, String userid);
	
	// 좋아요 체크 여부 
	public int likeCheck(Long bno, String userid);
	
	// 조회수 
	public boolean viewsUp(Long bno);
	
	// 댓글수 증가
	public void replysUp(Long bno);
					
	// 댓글수 감소
	public void replysDown(Long bno);
}
