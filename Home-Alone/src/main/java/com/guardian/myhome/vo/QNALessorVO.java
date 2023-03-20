package com.guardian.myhome.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QNALessorVO {
	private int lqNum;				// 답변 번호
	private int iqNum;				// 문의 번호
	private String imchaId;			// 회원 아이디
	private String lessorId;		// 중개인 아이디
	private String lqDate;			// 답변 작성일
	private String lqAnswer;		// 답변 내용
	private int answerStatus;		// 답변 상태
}
