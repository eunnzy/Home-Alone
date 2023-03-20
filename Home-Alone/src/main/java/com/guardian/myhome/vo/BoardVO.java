package com.guardian.myhome.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;


@Data
public class BoardVO {
	private Long bno;			//글번호
	private String imchaid;		//작성자
	private String title;		//제목
	private String category;	//카테고리
	private String content;		//내용
	private Date regdate;		//등록시간
	private Date updateDate;	//수정시간
	private int views;			//조회수
	private int likes;			//좋아요
	private int replys;			//댓글 
	
	private String nickname;	// 닉네임
	private String sido1;		// 주소1
	private String gugun1;		// 주소2 
	
	private List<BoardAttachVO> attachList;	// 파일업로드 
	
}
