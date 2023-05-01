package com.home.alone.dao;

import java.util.List;

import com.home.alone.vo.HomeInqAnswerVO;
import com.home.alone.vo.HomeInquryDetailVO;
import com.home.alone.vo.HomeInquryVO;

public interface HomeInquryDAO {
	int insertHomeInqury(HomeInquryVO homeInquryVO); // 문의글 등록
	int insertHomeInqAnswer(HomeInqAnswerVO homeInqAnswerVO); // 답변 등록
	int selectInqAnswerIdCheck(String lessorId);
	
	List<HomeInquryVO> selectInquryListByImcha(String imchaId); // 회원아이디별 문의 목록
	List<HomeInquryVO> selectInquryListByLeessor(String lessorId); // 중개인아이디 문의 목록
	HomeInquryDetailVO selectHomeInquryDetail(int iqNum);	// 문의글 자세히 보기
	List<HomeInqAnswerVO> selectInqAnswer(int iqNum);	// 문의 답변 
	
	int deleteHomeInqAnswer(int iqNum); // 답변 삭제
	int deleteHomeInqury(HomeInquryVO homeInquryVO); // 문의글 삭제
	
	int updateHomeInquryStatus(int iqNum);	// 문의글 답변 등록 상태 변경
	int updateHomeInqAnswer(HomeInqAnswerVO homeInqAnswerVO);	// 문의 답변 수정
}
