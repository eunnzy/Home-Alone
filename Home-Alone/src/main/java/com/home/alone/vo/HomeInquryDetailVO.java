package com.home.alone.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HomeInquryDetailVO {
	private int iqNum; 	// 문의글 번호
	private String imchaId;		// 문의 등록 아이디(일반 회원 id)
	private String lessorId;	// 문의 남길 아이디
	private int homeNum;	// 매물 번호
	private String iqTitle;	// 문의 제목
	private String iqContent;	// 문의 내용
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date iqDate;	// 문의 등록 날짜
	
	List<HomeInqAnswerVO> inqAnswer;
//	private int ansStatus;	// 답변 등록 상태
//	private int ansNum;	// 답변 번호
//	private String ansContent;	// 답변 내용
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private Date ansDate;	// 답변 등록 날짜 
}
