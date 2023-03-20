package com.guardian.myhome.vo;

import lombok.Data;

@Data
public class BoardLikesVO {
	private int likeno;			// 좋아요 번호(시퀀스) 
	private Long bno;			// 글 번호 
	private String userid;		// 현재 접속 아이디 
	private int like_check;		// 좋아요 상태 	
}
