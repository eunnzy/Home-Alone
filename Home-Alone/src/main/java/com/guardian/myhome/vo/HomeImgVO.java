package com.guardian.myhome.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 매물 사진 

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeImgVO {
	private int homeNum;		// 매물 번호
	private String homeImgName;		// 사진 이름
	private String homeImgPath;	// 사진 경로
	
}
