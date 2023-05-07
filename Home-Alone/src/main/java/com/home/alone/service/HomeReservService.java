package com.home.alone.service;

import java.util.List;

import com.home.alone.vo.HomeReservPreviewVO;
import com.home.alone.vo.HomeReservVO;

public interface HomeReservService {
	int reservHome(HomeReservVO homeReservVO);	// 예약하기	
	List<String> getInvalidTimeList(HomeReservVO homeReservVO);	// 이미 예약된 시간 리스트
	List<HomeReservPreviewVO> getReservListByImcha(String imchaId);	// 일반회원 예약 목록
	List<HomeReservPreviewVO> getReservListByLessor(String lessorId);	// 중개인 예약 목록
	int getHomeReservCount(String imchaId); 	// 예약 개수
	int homeReservCancel(int revNum);	// 예약 거절
	int homeReservAccept(int revNum);	// 예약 거절
	int homeReservReject(int revNum);	// 예약 거절
	int deleteReserv(int homeNum);	// 예약 내역 삭제
}
