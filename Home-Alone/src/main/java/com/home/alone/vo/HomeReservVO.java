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
	private Date revDate;
	private String revTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date regRevDate;		// 신청시간 작성일같은거
	private int revState;
}
