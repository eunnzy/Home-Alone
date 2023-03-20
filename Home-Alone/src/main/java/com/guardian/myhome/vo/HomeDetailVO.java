package com.guardian.myhome.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HomeDetailVO {
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
	private int deposit; 	// 보증금
	private int monthly;		// 월세
	private int rentPeriods;	// 임대 기간
	private int roomCount;		// 방 개수
	private int adminCost;	// 관리비
	private int parking;		// 주차 몇대?
	private String pet;			// 반려동물
	private String elevator;	// 엘리베이터
	private String balcony;	// 발코니
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date moveDate;	// 입주가능일
	private int floor;			// 층
	private String homeTitle;	// 글 제목	
	private String homeDetail;	// 상세 글
	private int homeValid;		// 유효한 게시글 인지
	private Date homeRegDate;	// 글 등록 날짜
	private String jgsName;
	private String jgsNum;
	private String phone;
	private String name;
	private String lessorAddr1;
	private String lessorAddr2;
	private String lessorAddr3;
	private List<String> optionList;
	private List<HomeImgVO> homeImgList;
	
	
}
