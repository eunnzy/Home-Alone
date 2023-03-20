package com.guardian.myhome.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationVO {
	
	private int revNum;
	private String lessorId;
	private String imchaId;
	private int homeNum;
	private String revDate;
	private Date regRevDate;		// 신청시간 작성일같은거
	private int revState;
	private String state;
	
}
