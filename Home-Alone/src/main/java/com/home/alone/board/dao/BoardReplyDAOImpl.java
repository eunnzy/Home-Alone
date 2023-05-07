package com.home.alone.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.home.alone.board.vo.BoardReplyVO;
import com.home.alone.util.Criteria;

@Repository
public class BoardReplyDAOImpl implements BoardReplyDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	private static final String BOARDREPLYMAPPER = "com.home.alone.mapper.BoardReplyMapper.";
	
	
	@Override
	public int insertBoardReply(BoardReplyVO vo) {
		return sqlSession.insert(BOARDREPLYMAPPER + "insertBoardReply", vo);
	}

	@Override
	public BoardReplyVO selectBoardReply(Long rno) {
		return sqlSession.selectOne(BOARDREPLYMAPPER + "selectBoardReply", rno);
	}

	@Override
	public int deleteBoardReply(Long rno) {
		return sqlSession.delete(BOARDREPLYMAPPER + "deleteBoardReply", rno);
	}

	@Override
	public int updateBoardReply(BoardReplyVO reply) {
		return sqlSession.update(BOARDREPLYMAPPER + "updateBoardReply", reply);
	}

	@Override
	public List<BoardReplyVO> selectBoardReplyListWithPaging(Long bno) {
		return sqlSession.selectList(BOARDREPLYMAPPER + "selectBoardReplyListWithPaging", bno);
	}

}
