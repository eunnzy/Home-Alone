$("#inqModalBtn").click(function() {
	$(".inq-modal").css("display", "flex");
	
	$("#inqBtn").click(function() {
		let iqTitle = $("#iqTitle").val();
		let iqContent = $("#iqContent").val();
		
			
		let inqData = {
			"homeNum" : homeNum,
			"iqTitle" : iqTitle,
			"iqContent" : iqContent
		};
	
		$.ajax({
			url: '/home/inqury',
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