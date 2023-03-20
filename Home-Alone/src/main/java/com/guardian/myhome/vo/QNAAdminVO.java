package com.guardian.myhome.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QNAAdminVO {
	private int aqNum;				// 관리자 문의 번호
	private String lessorId;		// 중개인 아이디
	private String aqTitle;			// 문의 제목
	private String aqDate;			// 작성일
	private String aqContent;		// 문의 내용
}
