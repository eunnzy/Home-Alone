package com.guardian.myhome.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessorVO {

	private String lessorId;
	
	private String lessorPw;
	
	private String lessorNickName;
	
	private String phone;
	
	private String name;
	
	private String birthDate;
	
	private String jgsName;
	
	private String jgsNum;
	
	private int status;
	
	private String userRoll;
	
	private String lessorAddr1;
	
	private String lessorAddr2;
	
	private String lessorAddr3;
	
	private int valid;
	
	// 이미지 정보
	private LessorImgVO lessorImg;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enrollDate;


}
