package com.guardian.myhome.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class AlarmBoardVO {
	private Long ano;			//글번호
	private String title;		//제목
	private String content;		//내용
	private Date regdate;		//등록시간
	private Date updateDate;	//수정시간
	
	private List<AlarmBoardAttachVO> attachList;	// 파일업로드 

}
