package com.guardian.myhome.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminVO {

	private String adminId;
	
	private String adminPw;
	
	private String adminName;
	
	private String adminTel;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enrollDate;


}
