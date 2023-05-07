package com.home.alone.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class HomeReservPreviewVO {
	private int revNum;		// 예약 번호	
	private String lessorId;	// 중개인 이름
	private String jgsName; 	// 중개사이름	
	private String imchaId;		// 예약자(일반회원) 아이디
	private String imchaName;	// 예약자 이름
	private int homeNum;	// 예약한 매물 번호
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date revDate;	// 예약 날짜
	private String revTime;	// 예약 시간대
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date regRevDate;		// 신청시간 
	private int revState;	// 예약 상태
	private HomeImgVO homeImg;	// 사진
}
