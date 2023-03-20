package com.guardian.myhome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guardian.myhome.mapper.QNAMapper;
import com.guardian.myhome.vo.QNAImchaVO;
import com.guardian.myhome.vo.QNALessorVO;

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
