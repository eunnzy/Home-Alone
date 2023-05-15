package com.home.alone.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.alone.board.dao.BoardDAO;
import com.home.alone.board.vo.BoardAttachVO;
import com.home.alone.board.vo.BoardLikesVO;
import com.home.alone.board.vo.BoardVO;
import com.home.alone.mapper.BoardLikesMapper;
import com.home.alone.util.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDAO;
	
	
	@Setter(onMethod_ = @Autowired)
	private BoardLikesMapper likesMapper;
	

	@Override
	public List<BoardVO> boardList(Criteria cri) {	// 커뮤니티 글 리스트
		log.info("before Board" + cri);
		return boardDAO.selectBoardList(cri);
	}
	
	@Override
	public int boardCount(Criteria cri) {	// 커뮤니티 글 개수
		log.info("before Board Count");
		return boardDAO.selectBoardCount(cri);
	}	
				
	// 내가 쓴 글 목록 리스트 
	@Override
	public List<BoardVO> getMyboardList(Criteria cri) {
		log.info("get getMyboard......");
		return boardDAO.selectMyBoardList(cri);
	}
		
	// 내가 쓴 글 갯수
	@Override
	public int getMyboardCount(String imchaId) {
		log.info("getMyboardCount");
		log.info(imchaId);
		return boardDAO.selectWriteCount(imchaId);
	}
	
	// 등록 
	@Transactional
	@Override
	public void register(BoardVO board) {
		log.info("register........" + board);
		boardDAO.insertBoard(board);
		if(board.getAttachList() == null || board.getAttachList().size() <= 0) {
			return;
		}
		board.getAttachList().forEach(attach -> {
			attach.setBno(board.getBno());
			boardDAO.insertBoardAttach(attach);
		});
	}

	// 조회
	@Override
	public BoardVO getDetail(Long bno) {
		log.info("get........" + bno);
		return boardDAO.selectBoardDetail(bno);
	}
	
	// 파일 업로드 조회
	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {
		log.info("get Attach list by bno" + bno);
		return boardDAO.selectBoardAttachfindByBno(bno);
	}

	// 수정
	@Transactional
	@Override
	public boolean modify(BoardVO board) {
		log.info("modify........" + board);
		boardDAO.deleteBoardAttachAll(board.getBno());
		boolean modifyResult = boardDAO.updateBoard(board) == 1;
		if(modifyResult && board.getAttachList() != null && board.getAttachList().size() > 0) {
			board.getAttachList().forEach(attach -> {
				attach.setBno(board.getBno());
				boardDAO.insertBoardAttach(attach);
			});
		}
		return modifyResult;
	}
	
	// 삭제 
	@Transactional
	@Override
	public boolean remove(Long bno) {
		log.info("remove........" + bno);
		boardDAO.deleteBoardAttachAll(bno);
		return boardDAO.deleteBoard(bno) == 1;
	}
	
	// 좋아요 On
	@Override
	public int likesOn(BoardLikesVO boardLikesVO) {
		return boardDAO.insertBoardLike(boardLikesVO);
	}
		
	// 좋아요 Off
	@Override
	public int likesOff(BoardLikesVO boardLikesVO) {
	return boardDAO.deleteBoardLike(boardLikesVO);
	}
		
	// 좋아요 Up
	@Override
	public boolean likesUp(Long bno) {
		return boardDAO.updateLikesUp(bno) == 1;
	}
		
	// 좋아요 Down
	@Override
	public boolean likesDown(Long bno) {
		return boardDAO.updateLikesDown(bno) == 1;
	}
		
	// 좋아요 체크 
	@Override
	public int likeCheck(Long bno, String userid) {
		log.info("likeCheck........" + bno);
		return likesMapper.likeCheck(bno, userid);
	}
	
	// 조회수 
	@Override
	public boolean viewsUp(Long bno) {
		log.info("viewsUp........" + bno);
		return boardDAO.updateViewsUp(bno) == 1;
	}
	
	// 댓글수 증가
	@Override
	public void replysUp(Long bno) {
		boardDAO.updateReplysUp(bno);
	}
						
	// 댓글수 감소
	@Override
	public void replysDown(Long bno) {
		boardDAO.updateReplysDown(bno);
	}
}
