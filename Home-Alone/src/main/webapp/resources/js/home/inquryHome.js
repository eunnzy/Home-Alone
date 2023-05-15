$("#inqModalBtn").click(function() {
	
	if(imchaId == '') {
		alert("로그인을 해주세요!");
		return false;
	}

	$(".inq-modal").css("display", "flex");
	
	$("#inqBtn").click(function() {
		let iqTitle = $("#iqTitle").val();
		let iqContent = $("#iqContent").val();
		
		console.log(imchaId);
		
		if(imchaId == '') {
			alert("로그인을 해주세요!");
			return false;
		}
		
		if(iqTitle == "") {
			alert("문의 제목을 입력해주세요!");
			return false;
		}
		
		if(iqContent == "") {
			alert("문의 내용을 입력해주세요!");
			return false;
		}
			
		let inqData = {
			"homeNum" : homeNum,
			"iqTitle" : iqTitle,
			"iqContent" : iqContent
		};
	
		$.ajax({
			url: '/inqury/register',
			data: inqData,
			type: 'POST',
			success: function(data){
				console.log(data);
				alert('문의가 등록되었습니다.');
				$(".inq-modal").css("display", "none");
			}
		});
	
	});
	
	
})

$(".inq-close").click(function() {
	$(".inq-modal").css("display", "none");
})