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
		homeType=[];
		rentType=[];
		optionList=[];
		addInfo=[];
		
		filterBtnStatus = true;
		console.log("filterApplyBtn func()");
		console.log("filterBtn" + filterBtnStatus);	
		
		$("input[name=homeType]:checked").each(function() {
			if(!homeType.includes($(this).val())) 
				homeType.push($(this).val());
		});

		$("input[name=rentType]:checked").each(function() {
			if(!rentType.includes($(this).val())) 
				rentType.push($(this).val());
		});
		
		$("input[name=optionList]:checked").each(function() {
			if(!optionList.includes($(this).val())) 
				optionList.push($(this).val());
		});
		
		$("input[name=addInfoList]:checked").each(function() {
			if(!addInfo.includes($(this).val())) 
				addInfo.push($(this).val());
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
		
		/*$.ajax({
			url: '/home/homeFilter',
	    	data : JSON.stringify(filterData),
	    	type : 'GET',
	    	dataType : 'json',
	    	success: function(data) {
	    	}
    	});*/
		
		// console.log(filterData);
		
		$(".filter-content").css("display", "none");
	});
	
});




