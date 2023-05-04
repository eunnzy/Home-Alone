package com.home.alone.service;

import java.util.List;
import java.util.Map;

import com.home.alone.util.Criteria;
import com.home.alone.vo.BoardAttachVO;
import com.home.alone.vo.BoardLikesVO;
import com.home.alone.vo.BoardVO;


public interface  BoardService{
	public List<BoardVO> boardList(Criteria cri);	// 커뮤니티 글 리스트
	public int boardCount(Criteria cri);	// 커뮤니티 글 개수
	public List<BoardVO> getMyboardList(Criteria cri); // 내가 쓴 글 목록 리스트 
	public int getMyboardCount(String imchaId); // 내가 쓴 글 갯수 
	public void register(BoardVO board); 	// 등록 
	public BoardVO getDetail(Long bno); // 조회
	public List<BoardAttachVO> getAttachList(Long bno);  	// 파일 업로드 조회 
	public boolean modify(BoardVO board); 	// 수정 
	public boolean remove(Long bno); // 삭제
	public boolean likesUp(Long bno); 	// 좋아요 UP
	public boolean likesDown(Long bno);	// 좋아요 DOWN
	public void likesOn(Long bno, String userid); // 좋아요 ON 
	public void likesOff(Long bno, String userid); // 좋아요 OFF 
	public int likeCheck(Long bno, String userid); // 좋아요 체크 여부 
	public boolean viewsUp(Long bno); // 조회수 
	public void replysUp(Long bno); // 댓글수 증가
	public void replysDown(Long bno); // 댓글수 감소
}
