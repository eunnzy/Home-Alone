package com.guardian.myhome.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data	// getter, setter, toString, hashcode 등을 자동으로 생성
@NoArgsConstructor
//@AllArgsConstructor
public class HomeVO {
	private int homeNum;		// 매물 번호	
	private String lessorId;	// 중개인 아이디 (작성자)
	private String homeType;	// 집 종류
	private String addr1;		// 우편번호
	private String addr2;		// 도로명	
	private String addr3;		// 상세 주소
	private double latitude;	// 위도
	private double longitude;	// 경도
	private int homeArea;	// 집 면적
	private String rentType; // 거래 종류 - 월세, 전세 등
	private int rentPeriods;	// 임대 기간
	private int roomCount;		// 방 개수
	private int parking;		// 주차 몇대?
	private String pet;			// 반려동물
	private String elevator;	// 엘리베이터
	private String balcony;	// 엘리베이터
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date moveDate;	// 입주가능일
	private int floor;			// 층
	private String homeTitle;	// 글 제목	
	private String homeDetail;	// 상세 글
	private int homeValid;		// 유효한 게시글 인지
	private Date homeRegDate;	// 글 등록 날짜
	
}
