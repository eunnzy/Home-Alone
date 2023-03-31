$("#sirenBtn").click(function(){
	$(".report-modal").css("display", "flex");
	
	$("#reportBtn").click(function() {
		let reportType = $("input:radio[name=reportType]:checked").val();
		let reportContent = $("#reportContent").val();
		
		
		// 유효성 검사
		if(reportType == undefined) {
			alert("신고 유형을 선택해주세요.");
			return;
		}

		if(reportContent != "" && (reportType == 1 || reportType == 2)) {
			alert("신고유형이 기타인 경우에만 신고 내용을 작성할 수 있습니다.");
			return;	
		}	
		
		if(reportType == 3 && reportContent == "") {
			alert("신고 유형이 기타인 경우에는 신고 내용을 작성해주세요!");
			return;
		}
		
		
		if($("#agreeCheck").is(":checked") == false) {
			alert("약관에 동의해주세요.");
			return;
		}
		
		$.ajax({
			data: { "homeNum": homeNum, "reportType": reportType, "reportContent": reportContent},
			type : 'post',
			url: '/home/report',
			success:function(data) {
				if(data == 0) {
					alert("회원만 가능합니다.")
				}else {
					alert("신고가 정상적으로 접수되었습니다.");
				}
			},
			error: function(xhr, status, error){console.log(xhr.status, status)}
			
		});
	
	});
	
});

$(".report-close").click(function() {
	$(".report-modal").css("display", "none");
})



