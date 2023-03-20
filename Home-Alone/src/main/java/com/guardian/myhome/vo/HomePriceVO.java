package com.guardian.myhome.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HomePriceVO {
	int homeNum;	// 매물 번호
	int deposit;	 // 보증금
	int monthly;	// 월세
	int adminCost;	// 관리비
}
