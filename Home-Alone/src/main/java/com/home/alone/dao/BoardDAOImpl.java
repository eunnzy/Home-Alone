package com.home.alone.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.home.alone.util.Criteria;
import com.home.alone.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	@Autowired
	private SqlSessionTemplate sqlSession;
	private static final String BOARDMAPPER = "com.home.alone.mapper.BoardMapper.";
	
	@Override
	public List<BoardVO> selectBoardList(Criteria cri) {	// 커뮤니티 글 리스트로
		return sqlSession.selectList(BOARDMAPPER + "selectBoardList", cri);
	}

	@Override
	public int selectBoardCount(Criteria cri) {	// 커뮤니티 글 개수
		return sqlSession.selectOne(BOARDMAPPER + "selectBoardCount", cri);
	}


	@Override
	public List<BoardVO> selectMyBoardList(Criteria cri) {
		return sqlSession.selectList(BOARDMAPPER + "selectMyBoardList", cri);
	}

	@Override
	public int selectWriteCount(String imchaId) {
		return sqlSession.selectOne(BOARDMAPPER + "selectWriteCount" , imchaId);
	}
	
	@Override
	public int insertBoard(BoardVO board) {
		return sqlSession.insert(BOARDMAPPER + "insertBoard", board);
	}

	@Override
	public BoardVO selectBoardDetail(Long bno) {
		return sqlSession.selectOne(BOARDMAPPER + "selectBoardDetail", bno);
	}

	@Override
	public int updateBoard(BoardVO board) {
		return sqlSession.update(BOARDMAPPER + "updateBoard", board);
	}

	@Override
	public int updateLikesUp(Long bno) {
		return sqlSession.update(BOARDMAPPER + "updateLikesUp", bno);
	}

	@Override
	public int updateLikesDown(Long bno) {
		return sqlSession.update(BOARDMAPPER + "updateLikesDown", bno);
	}

	@Override
	public int updateViewsUp(Long bno) {
		return sqlSession.update(BOARDMAPPER + "updateViewsUp", bno);
	}

	@Override
	public int updateReplysUp(Long bno) {
		return sqlSession.update(BOARDMAPPER + "updateReplysUp", bno);
	}

	@Override
	public int updateReplysDown(Long bno) {
		return sqlSession.update(BOARDMAPPER + "updateReplysDown", bno);
	}

	@Override
	public int deleteBoard(Long bno) {
		return sqlSession.delete(BOARDMAPPER + "deleteBoard", bno);
	}


}
