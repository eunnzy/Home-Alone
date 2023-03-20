package com.guardian.myhome.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeVO {
	
	private int likeNum;
	private String imchaId;
	private int homeNum;
	private int likeStatus;
	
}
