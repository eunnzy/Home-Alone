package com.home.alone.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data	
@NoArgsConstructor
public class PropertyNewsVO {
	private String imgUrl;
	private String title;
	private String url;
	private String publication;	// 신문사
	private int page;
}
