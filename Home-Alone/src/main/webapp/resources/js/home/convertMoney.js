function convertMoney(money) {	
	let convert = ""; 
	if(money == 0) {
		convert = "없음";
	}else if(money >= 10000) {
		convert += Math.floor(money/10000) + "억 ";
		if(money % 10000 != 0 ) {
			money = money % 10000;
			convert +=  money + "만";
		}
	}else {
		convert = money + "만";
	}
	
	return convert;
}