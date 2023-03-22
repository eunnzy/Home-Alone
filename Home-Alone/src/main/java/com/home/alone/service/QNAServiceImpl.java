package com.home.alone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.alone.mapper.QNAMapper;
import com.home.alone.vo.QNAImchaVO;
import com.home.alone.vo.QNALessorVO;

@Service
public class QNAServiceImpl implements QNAService {

	@Autowired
	QNAMapper qnaMapper;
	
	@Override
	public void insertQNAImcha(QNAImchaVO qnaImcha) {
		qnaMapper.insertQNAImcha(qnaImcha);
	}

//	@Override
//	public void updateQNAImcha(QNAImchaVO qnaImcha) {
//		qnaMapper.updateQNAImcha(qnaImcha);
//	}
	
	@Override
	public void deleteQNAImcha(String imchaId, int iqNum) {
		qnaMapper.deleteQNAImcha(imchaId, iqNum);
	}

	@Override
	public List<QNAImchaVO> imchaList(String imchaId) {
		return qnaMapper.imchaList(imchaId);
	}
	
	@Override
	public QNAImchaVO getBoard(int iqNum) {
		return qnaMapper.getBoard(iqNum);
	}
	
	@Override
	public QNALessorVO getAnswer(int iqNum) {
		return qnaMapper.getAnswer(iqNum);
	}

	@Override
	public List<QNALessorVO> lessorList(String lessorId) {
		return qnaMapper.lessorList(lessorId);
	}

	@Override
	public void insertQNALessor(QNALessorVO qnaLessor) {
		qnaMapper.insertQNALessor(qnaLessor);
	}

	@Override
	public void deleteQNALessor(String lessorId, int lqNum) {
		qnaMapper.deleteQNALessor(lessorId, lqNum);
	}

	

}
