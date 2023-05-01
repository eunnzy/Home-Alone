$(document).ready(function() {
	console.log(iqNum);
	
	$.ajax({
		url: "/inqury/answer/list",
		type: 'POST',
		data: {"iqNum" : $("#iqNum").val()},
		success: function(data) {
			console.log(data);
			
			let answerHtml = "";
			let answerList = $(".answer-list");
			
			for(let i=0; i<data.length; i++) {
				answerHtml += "<tr><td>"+ data[i].lessorId  +"</td></tr>";
				answerHtml += "<tr><td>"+ data[i].ansContent  +"</td></tr>";
			}
			
			answerList.append(answerHtml);
		}
		
	});
	
})
