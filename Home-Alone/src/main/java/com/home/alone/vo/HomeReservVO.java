package com.home.alone.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class HomeReservVO {
	
	private int revNum;	
	private String lessorId;
	private String imchaId;
	private int homeNum;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date revDate;	// 예약 날짜
	private String revTime;	// 예약 시간대
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date regRevDate;		// 신청시간 
	private int revState;	// 예약 상태
	
	private String jgsName; 	// 중개사이름
	private String imchaName;
	
}
