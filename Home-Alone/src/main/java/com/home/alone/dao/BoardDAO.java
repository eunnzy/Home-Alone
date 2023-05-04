package com.home.alone.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.home.alone.util.Criteria;
import com.home.alone.vo.BoardVO;

public interface BoardDAO {
		List<BoardVO> selectBoardList(Criteria cri); // 커뮤니티 글 
		int selectBoardCount(Criteria cri);	// 커뮤니티 글 개수
		List<BoardVO> selectMyBoardList(Criteria cri); 	// 내가 쓴 글 목록 리스트  // Criteria cri, String imchaid
		int selectWriteCount(String imchaId);	// 내가 쓴 글 개수
		int insertBoard(BoardVO board); 	// 게시글 등록 
		BoardVO selectBoardDetail(@Param("bno") Long bno); // 게시글 조회
		int updateBoard(BoardVO board); 	// 게시글 수정
		int updateLikesUp(Long bno); // 게시글 좋아요 증가
		int updateLikesDown(Long bno); // 게시글 좋아요 감소
		int updateViewsUp(Long bno); // 게시글 조회수 증가
		int updateReplysUp(Long bno); 	// 게시글 댓글수 증가
		int updateReplysDown(Long bno); // 게시글 댓글수 감소
		int deleteBoard(Long bno);  // 게시글 삭제
}
