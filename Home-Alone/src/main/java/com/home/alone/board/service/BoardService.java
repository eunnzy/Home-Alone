package com.home.alone.board.service;

import java.util.List;
import java.util.Map;

import com.home.alone.board.vo.BoardAttachVO;
import com.home.alone.board.vo.BoardLikesVO;
import com.home.alone.board.vo.BoardVO;
import com.home.alone.util.Criteria;


public interface  BoardService{
	List<BoardVO> boardList(Criteria cri);	// 커뮤니티 글 리스트
	int boardCount(Criteria cri);	// 커뮤니티 글 개수
	List<BoardVO> getMyboardList(Criteria cri); // 내가 쓴 글 목록 리스트 
	int getMyboardCount(String imchaId); // 내가 쓴 글 갯수 
	void register(BoardVO board); 	// 등록 
	BoardVO getDetail(Long bno); // 조회
	List<BoardAttachVO> getAttachList(Long bno);  	// 파일 업로드 조회 
	boolean modify(BoardVO board); 	// 수정 
	boolean remove(Long bno); // 삭제
	boolean likesUp(Long bno); 	// 좋아요 UP
	boolean likesDown(Long bno);	// 좋아요 DOWN
	void likesOn(Long bno, String userid); // 좋아요 ON 
	void likesOff(Long bno, String userid); // 좋아요 OFF 
	int likeCheck(Long bno, String userid); // 좋아요 체크 여부 
	boolean viewsUp(Long bno); // 조회수 
	void replysUp(Long bno); // 댓글수 증가
	void replysDown(Long bno); // 댓글수 감소
}
