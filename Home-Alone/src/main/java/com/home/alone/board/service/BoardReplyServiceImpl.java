package com.home.alone.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.alone.board.dao.BoardReplyDAO;
import com.home.alone.board.vo.BoardReplyVO;
import com.home.alone.mapper.ReplyMapper;
import com.home.alone.util.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class BoardReplyServiceImpl implements BoardReplyService {
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Autowired
	private BoardReplyDAO boardReplyDAO;

	@Override
	public int register(BoardReplyVO vo) {
		log.info("register......" + vo);
		return boardReplyDAO.insertBoardReply(vo);
	}

	@Override
	public BoardReplyVO get(Long rno) {
		log.info("get......" + rno);
		return boardReplyDAO.selectBoardReply(rno);
	}

	@Override
	public int modify(BoardReplyVO vo) {
		log.info("modify........" + vo);
		return boardReplyDAO.updateBoardReply(vo);
	}

	@Override
	public int remove(Long rno) {
		log.info("remove........" + rno);
		return boardReplyDAO.deleteBoardReply(rno);
	}

	@Override
	public List<BoardReplyVO> getList(Long bno) {
		log.info("get List" + bno);
		return boardReplyDAO.selectBoardReplyListWithPaging(bno);
	}
	
	
//	public List<BoardReplyVO> getList(Criteria cri, Long bno) {
//		log.info("get List" + bno);
//		return mapper.getListWithPaging(cri, bno);
//	}

}
