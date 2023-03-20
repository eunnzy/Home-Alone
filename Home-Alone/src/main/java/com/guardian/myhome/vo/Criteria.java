package com.guardian.myhome.vo;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class Criteria {
	private int pageNum;		// 페이지 번호 
	private int amount;			// 한 페이지당 몇 개의 데이터를 보여줄 것인지 결정 
	
	private String type;
	private String keyword;
	
	private String imchaid;		// 닉네임
	private String sido1;		// 주소1
	private String gugun1;		// 주소2 
	
	public Criteria() {
		this(1, 10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {
		return type == null? new String[] {} : type.split("");
	}
	
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("").queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.getAmount()).queryParam("type", this.getType()).queryParam("keyword", this.getKeyword());
		return builder.toUriString();
	}
}
