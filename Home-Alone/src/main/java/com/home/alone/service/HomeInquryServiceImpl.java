package com.home.alone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.alone.dao.HomeDAO;
import com.home.alone.dao.HomeInquryDAO;
import com.home.alone.vo.HomeInqAnswerVO;
import com.home.alone.vo.HomeInquryDetailVO;
import com.home.alone.vo.HomeInquryVO;

@Service
public class HomeInquryServiceImpl implements HomeInquryService {
	@Autowired
	HomeDAO homeDAO;
	
	@Autowired
	HomeInquryDAO homeInquryDAO;
	
	@Override	
	public int inquryHome(HomeInquryVO homeInquryVO) {	// 문의 등록
		return homeInquryDAO.insertHomeInqury(homeInquryVO);
	}

	@Override
	public List<HomeInquryVO> getHomeInqListByImcha(String imchaId) {	// 매물 문의 목록(일반회원)
		return homeInquryDAO.selectInquryListByImcha(imchaId);
	}

	@Override
	public List<HomeInquryVO> getHomeInqListByLessor(String lessorId) {	// 매물 문의 목록(중개인)
		return homeInquryDAO.selectInquryListByLeessor(lessorId);
	}

	@Override
	public HomeInquryDetailVO getHomeInqDetail(int iqNum) {	// 문의 상세보기
		HomeInquryDetailVO homeInqDetail = homeInquryDAO.selectHomeInquryDetail(iqNum);
		homeInqDetail.setHomeImg(homeDAO.selectPreviewHomeImg(homeInqDetail.getHomeNum()));
		return homeInqDetail;
	}

	@Override
	public List<HomeInqAnswerVO> getHomeInqAnswerList(int iqNum) {		// 문의 답변 목록
		return homeInquryDAO.selectInqAnswerList(iqNum);
	}

	@Override
	public HomeInqAnswerVO getHomeInqAnswer(int ansNum) {	// 문의 답변 내용
		return homeInquryDAO.selectHomeInqAnswer(ansNum);
	}

	@Override
	public int registerHomeInqAnswer(HomeInqAnswerVO homeInqAnswerVO) {	// 문의 답변 등록
		int result = homeInquryDAO.insertHomeInqAnswer(homeInqAnswerVO);
		System.out.println(result);
		
		if(result == 1) {	// 답변작성 성공시
			return homeInquryDAO.updateHomeInqAnsCom(homeInqAnswerVO.getIqNum());	// 답변 상태 변경
		}
		return 0;
	}

	@Override
	public int homeAnsIdCheck(String lessorId) {	// 문의 답변 상태
		return homeInquryDAO.selectInqAnswerIdCheck(lessorId);
	}

	@Override
	public int modifyHomeInqAnswer(HomeInqAnswerVO homeInqAnswerVO) {
		return homeInquryDAO.updateHomeInqAnswer(homeInqAnswerVO);
	}

	@Override	
	public int deleteHomeInqAnswer(int ansNum) {	// 답변 삭제
		HomeInqAnswerVO homeInqAnsVO = homeInquryDAO.selectHomeInqAnswer(ansNum);	// 문의 답변 번호에 해당하는 정보 가져오기
		homeInquryDAO.updateHomeInqAnsWait(homeInqAnsVO.getIqNum());	// 답변 상태를 대기중으로 수정하고
		return homeInquryDAO.deleteHomeInqAnswer(ansNum); // 답변 삭제
	}

	@Override
	public int modifyHomeInqAnsStatusCom(int iqNum) {
		return homeInquryDAO.updateHomeInqAnsCom(iqNum);
	}

	@Override
	public int modifyHomeInqAnsStatusWait(int iqNum) {
		return homeInquryDAO.updateHomeInqAnsWait(iqNum);
	}

}
