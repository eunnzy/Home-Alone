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
			}
		});
	
	});
	
	
})

$(".inq-close").click(function() {
	$(".inq-modal").css("display", "none");
})