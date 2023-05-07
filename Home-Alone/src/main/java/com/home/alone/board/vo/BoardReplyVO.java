package com.home.alone.board.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BoardReplyVO {
	private Long rno;
	private Long bno;
	
	private String reply;
	private String replyer;
	private Date replyDate;
	private Date updateDate;

}
