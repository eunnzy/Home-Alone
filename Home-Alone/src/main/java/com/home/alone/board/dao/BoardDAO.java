package com.home.alone.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.home.alone.board.vo.BoardAttachVO;
import com.home.alone.board.vo.BoardLikesVO;
import com.home.alone.board.vo.BoardVO;
import com.home.alone.util.Criteria;

public interface BoardDAO {
	List<BoardVO> selectBoardList(Criteria cri); // 커뮤니티 글 
	int selectBoardCount(Criteria cri);	// 커뮤니티 글 개수
	List<BoardVO> selectMyBoardList(Criteria cri); 	// 내가 쓴 글 목록 리스트  // Criteria cri, String imchaid
	int selectWriteCount(String imchaId);	// 내가 쓴 글 개수
	int selectLikeCheck(BoardLikesVO boardLikesVO);	// 게시글 좋아요 확인
	int insertBoard(BoardVO board); 	// 게시글 등록 
	int insertBoardAttach(BoardAttachVO board);	// 첨부파일 등록
	int insertBoardLike(BoardLikesVO boardLikesVO);	// 좋아요 추가
	BoardVO selectBoardDetail(Long bno); // 게시글 조회
	List<BoardAttachVO> selectBoardAttachfindByBno(Long bno); // 특정 게시물의 번호로 첨부파일을 찾는 작업 
	int updateBoard(BoardVO board); 	// 게시글 수정
	int updateLikesUp(Long bno); // 게시글 좋아요 증가
	int updateLikesDown(Long bno); // 게시글 좋아요 감소
	int updateViewsUp(Long bno); // 게시글 조회수 증가
	int updateReplysUp(Long bno); 	// 게시글 댓글수 증가
	int updateReplysDown(Long bno); // 게시글 댓글수 감소
	int deleteBoard(Long bno);  // 게시글 삭제
	int deleteBoardAttach(String uuid);	// 게시물 첨부파일 업로드 삭제
	int deleteBoardAttachAll(Long bno); // 게시물의 모든 첨부파일 삭제
	int deleteBoardLike(BoardLikesVO boardLikesVO);	// 좋아요 삭제
	List<BoardAttachVO> getOldFiles(); // 업로드 파일 확인

}
