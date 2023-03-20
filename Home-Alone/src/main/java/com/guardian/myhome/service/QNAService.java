package com.guardian.myhome.service;

import java.util.List;

import com.guardian.myhome.vo.QNAImchaVO;
import com.guardian.myhome.vo.QNALessorVO;

public interface QNAService {
	
	// 문의하기
	public void insertQNAImcha(QNAImchaVO qnaImcha);
	
	// 문의 수정
//	public void updateQNAImcha(QNAImchaVO qnaImcha);
	
	// 문의 삭제
	public void deleteQNAImcha(String imchaId, int iqNum);
	
	// 회원아이디별 문의 목록
	public List<QNAImchaVO> imchaList(String imchaId);
	
	// 문의내용 가져오기
	public QNAImchaVO getBoard(int iqNum);
	
	// 문의 답변 가져오기
	public QNALessorVO getAnswer(int iqNum);
	
	// 중개인아이디 문의 목록
	public List<QNALessorVO> lessorList(String lessorId);
	
	// 답변 등록
	public void insertQNALessor(QNALessorVO qnaLessor);
	
	// 답변 삭제
	public void deleteQNALessor(String lessorId, int lqNum);
	
	
}
