package com.guardian.myhome.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guardian.myhome.mapper.BoardAttachMapper;
import com.guardian.myhome.mapper.BoardLikesMapper;
import com.guardian.myhome.mapper.BoardMapper;
import com.guardian.myhome.vo.BoardAttachVO;
import com.guardian.myhome.vo.BoardLikesVO;
import com.guardian.myhome.vo.BoardVO;
import com.guardian.myhome.vo.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardAttachMapper attachMapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardLikesMapper likesMapper;
	

	// 로그인 전 
	@Override
	public List<BoardVO> beforeBoard(Criteria cri) {
		log.info("before Board" + cri);
		return mapper.beforeBoard(cri);
	}
	
	// 로그인 전 갯수
	@Override
	public int beforeBoardCount(Criteria cri) {
		log.info("before Board Count");
		return mapper.beforeBoardCount(cri);
	}	
				
	// 로그인 후 
	@Override
	public List<BoardVO> afterBoard(Criteria cri) {
		log.info("after Board");
		return mapper.afterBoard(cri);
	}
				
	// 로그인 후 갯수
	@Override
	public int afterBoardCount(Criteria cri) {
		log.info("after Board Count");
		return mapper.afterBoardCount(cri);
	}
	
	// 내가 쓴 글 목록 리스트 
	@Override
	public List<BoardVO> getMyboard(Criteria cri) {
		log.info("get getMyboard......");
		return mapper.getMyboard(cri);
	}
		
	// 내가 쓴 글 갯수
	@Override
	public int getMyboardCount(Criteria cri) {
		log.info("getMyboardCount");
		return mapper.getMyboardCount(cri);
	}
	
	// 등록 
	@Transactional
	@Override
	public void register(BoardVO board) {
		log.info("register........" + board);
		mapper.insertSelectKey(board);
		if(board.getAttachList() == null || board.getAttachList().size() <= 0) {
			return;
		}
		board.getAttachList().forEach(attach -> {
			attach.setBno(board.getBno());
			attachMapper.insert(attach);
		});
	}

	// 조회
	@Override
	public BoardVO get(Long bno) {
		log.info("get........" + bno);
		return mapper.read(bno);
	}
	
	// 파일 업로드 조회
	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {
		log.info("get Attach list by bno" + bno);
		return attachMapper.findByBno(bno);
	}

	// 수정
	@Transactional
	@Override
	public boolean modify(BoardVO board) {
		log.info("modify........" + board);
		attachMapper.deleteAll(board.getBno());
		boolean modifyResult = mapper.update(board) == 1;
		if(modifyResult && board.getAttachList() != null && board.getAttachList().size() > 0) {
			board.getAttachList().forEach(attach -> {
				attach.setBno(board.getBno());
				attachMapper.insert(attach);
			});
		}
		return modifyResult;
	}
	
	// 삭제 
	@Transactional
	@Override
	public boolean remove(Long bno) {
		log.info("remove........" + bno);
		attachMapper.deleteAll(bno);
		return mapper.delete(bno) == 1;
	}
	
	// 좋아요 On
	@Override
	public void likesOn(Long bno, String userid) {
		likesMapper.likesOn(bno, userid);
	}
		
	// 좋아요 Off
	@Override
	public void likesOff(Long bno, String userid) {
		likesMapper.likesOff(bno, userid);
	}
		
	// 좋아요 Up
	@Override
	public boolean likesUp(Long bno) {
		return mapper.likesUp(bno) == 1;
	}
		
	// 좋아요 Down
	@Override
	public boolean likesDown(Long bno) {
		return mapper.likesDown(bno) == 1;
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
		return mapper.viewsUp(bno) == 1;
	}
	
	// 댓글수 증가
	@Override
	public void replysUp(Long bno) {
		mapper.replysUp(bno);
	}
						
	// 댓글수 감소
	@Override
	public void replysDown(Long bno) {
		mapper.replysDown(bno);
	}
}
