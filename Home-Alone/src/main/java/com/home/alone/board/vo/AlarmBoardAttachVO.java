package com.home.alone.board.vo;

import lombok.Data;

@Data
public class AlarmBoardAttachVO {
	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;
	
	private Long ano;
}
