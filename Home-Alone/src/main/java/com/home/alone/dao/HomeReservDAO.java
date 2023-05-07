package com.home.alone.dao;

import java.util.List;

import com.home.alone.vo.HomeReservPreviewVO;
import com.home.alone.vo.HomeReservVO;

public interface HomeReservDAO {
	int insertHomeReserv(HomeReservVO homeReservVO); // 매물 예약 정보 insert
	List<String> selectHomeReservInvalidTimeList(HomeReservVO homeReservVO);		// 이미 예약된 시간 리스트
	List<HomeReservPreviewVO> selectHomeReservListByImchaId(String imchaId);	// 일반회원 예약 목록
	List<HomeReservPreviewVO> selectHomeReservListByLessorId(String lessorId);	// 일반회원 예약 목록
	int selectHomeReservCountImcha(String imchaId);	// 예약 목록 개수
	int deleteHomeReserv(int reservNum);	// 예약 삭제
	int updateHomeReservReject(int revNum);	// 예약 거절
	int updateHomeReservCancel(int revNum);	// 예약 수락
	int updateHomeReservAccept(int revNum);	// 예약 수락
}
