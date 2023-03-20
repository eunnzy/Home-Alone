package com.guardian.myhome.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImchaVO {

	private String imchaId;
	
	private String imchaPw;
	
	private String nickname;
	
	private String phone;
	
	private String userRoll;
	
	private String sido1;
	
	private String gugun1;
	
	
	private int valid;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enrollDate;

	
}
