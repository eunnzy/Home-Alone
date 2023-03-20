package com.guardian.myhome.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlarmBoardAttachFileDTO {
	private String fileName;		// 원본 파일 이름 
	private String uploadPath;		// 업로드 경로 
	private String uuid;			// uuid
	private boolean image;			// 이미지 여부 
}
