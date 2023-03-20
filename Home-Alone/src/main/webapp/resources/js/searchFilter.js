let homeData;
let homeTypeCheck = [];
let rentTypeCheck = [];
let optionCheck = [];
let addInfoCheck = [];
let deposit = 0;
let monthly = 0;
let filterBtnStatus = false;

$(document).ready(function() {
	$("#filterBtn").click(function() {
		if($(".filter-content").css("display") == "none")	{
			$(".filter-content").css("display", "inline-block");
			$(".filter-content").scrollTop(0);
		}else {
			$(".filter-content").css("display", "none");
		}
	});
	
	$("#cancelBtn").click(function() {
		if($("input[type=checkbox]:checked")) 
		$("input[type=checkbox]").prop("checked", false);
	});
	
	$("#filterApplyBtn").click(function() {
		homeTypeCheck=[];
		rentTypeCheck=[];
		optionCheck=[];
		addInfoCheck=[];
		
		filterBtnStatus = true;
		console.log("filterApplyBtn func()");
		console.log("filterBtn" + filterBtnStatus);	
		
		$("input[name=homeType]:checked").each(function() {
			if(!homeTypeCheck.includes($(this).val())) 
				homeTypeCheck.push($(this).val());
		});

		$("input[name=rentType]:checked").each(function() {
			if(!rentTypeCheck.includes($(this).val())) 
				rentTypeCheck.push($(this).val());
		});
		
		$("input[name=optionList]:checked").each(function() {
			if(!optionCheck.includes($(this).val())) 
				optionCheck.push($(this).val());
		});
		
		$("input[name=addInfoList]:checked").each(function() {
			if(!addInfoCheck.includes($(this).val())) 
				addInfoCheck.push($(this).val());
		});
		
		
		deposit = $("#deposit").val();
		if(deposit == 1) {
			deposit = 5000;
		}else if(deposit == 2){
			deposit = 10000;
		}else {
			deposit = 99999999;
		}
		
		monthly = $("#monthly").val();
		if(monthly == 1) {
			monthly = 50;
		}else if(monthly == 2){
			monthly = 100;
		}else {
			monthly = 99999999;
		}
		
		getHomeInBounds();	
		
		for(let i=0; i<homeData.length; i++) {
			console.log(homeData[i]);
			let check = checkFilter(homeData[i]);
			console.log("check: " + check);
			if(check === false) {
			    continue;
			}
			else {
				displayHomeList(homeData[i], i);
		   }
		}
		
		$(".filter-content").css("display", "none");
	});
	
});




