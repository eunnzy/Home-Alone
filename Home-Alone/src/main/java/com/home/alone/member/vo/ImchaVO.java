package com.home.alone.member.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImchaVO {

	private String imchaId;
	private String imchaPw;
	private String name;
	private String nickname;
	private String phone;
	private String userRoll;
	private String sido;
	private String gugun;
	private int valid;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enrollDate;

	
}
