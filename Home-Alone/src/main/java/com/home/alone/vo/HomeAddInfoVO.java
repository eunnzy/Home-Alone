package com.home.alone.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HomeAddInfoVO {	// 추가 정보
	private int homeNum;	// 매물 번호
	private int parking;		// 주차 몇대
	private char pet;			// 반려동물
	private char elevator;	// 엘리베이터
	private char balcony;	// 발코니
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date moveDate;	// 입주가능일
}
