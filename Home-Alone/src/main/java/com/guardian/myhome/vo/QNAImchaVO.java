package com.guardian.myhome.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QNAImchaVO {
	private int iqNum;				// 문의 번호
	private String imchaId;			// 회원 아이디
	private int homeNum;			// 매물 번호
	private String iqTitle;			// 문의 제목
	private String iqDate;			// 작성일
	private String iqContent;		// 내용
}
