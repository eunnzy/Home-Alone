package com.home.alone.service;

import java.util.List;

import com.home.alone.vo.HomeInqAnswerVO;
import com.home.alone.vo.HomeInquryDetailVO;
import com.home.alone.vo.HomeInquryVO;

public interface HomeInquryService {
	int inquryHome(HomeInquryVO homeInquryVO);	// 문의글 등록
	List<HomeInquryVO> getHomeInqListByImcha(String imchaId);	// 문의글 리스트(일반회원)
	List<HomeInquryVO> getHomeInqListByLessor(String lessorId);	// 문의글 리스트(일반회원)
	HomeInquryDetailVO getHomeInqDetail(int iqNum);	// 문의 상세보기
	List<HomeInqAnswerVO> getHomeInqAnswerList(int iqNum);	// 문의 답변 댓글 리스트
	HomeInqAnswerVO getHomeInqAnswer(int ansNum);	// 문의 답변 내용
	int registerHomeInqAnswer(HomeInqAnswerVO homeInqAnswerVO);	// 답변 등록
	int homeAnsIdCheck(String lessorId);	// 답변 수정을 위한 아이디 체크
	int modifyHomeInqAnswer(HomeInqAnswerVO homeInqAnswerVO);	// 답변 수정
	int deleteHomeInqAnswer(int ansNum);	// 답변 삭제
	int modifyHomeInqAnsStatusCom(int iqNum);	// 답변 상태: 완료 변경
	int modifyHomeInqAnsStatusWait(int iqNum);	// 답변 상태: 대기중 변경
}
