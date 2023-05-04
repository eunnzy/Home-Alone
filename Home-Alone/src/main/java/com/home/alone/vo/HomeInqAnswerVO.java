package com.home.alone.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HomeInqAnswerVO {
	private int ansNum;	// 답변 번호
	private int iqNum;	// 문의 번호
	private String lessorId;	// 답변 등록 id(중개인 아이디)
	private String jgsName;
	private String ansContent;	// 답변 내용
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ansDate;	// 답변 등록 날짜 
}
