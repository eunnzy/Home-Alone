$("#reservModalBtn").click(function() {
	
	$(".reserv-modal").css("display", "flex");
	
	let revDate = $("#revDate").val();	// 방문 날짜
	let revTime = $("input:radio[name=revTime]:checked").val();

	$("#revDate").on("change", function(e){	// 날짜 선택할 때
		revTimeInit();
		
		let revValid = { "revDate": $("#revDate").val(), "homeNum" : homeNum};
		console.log(revValid);
		
		$.ajax({
			url: '/home/reserv/validTimeCheck',
			data: revValid,
			type: 'POST',
			success: function(data){
				console.log(data);
				for(let i=0; i<data.length; i++) {
					console.log(data[i]);
					changeDisabled(data[i]);	// 이미 예약된 시간대는 예약할 수 없도록
				}
			}
		});
	});
	
	$("#reservBtn").on("click", function(e){	// 예약하기 버튼 클릭시
		let revData = {
			"revDate" : revDate,
			"revTime" : revTime,
			"lessorId" : lessorId,
			"homeNum" : homeNum
		};	// 서버로 넘길 데이터	
		
		console.log(revData);
		
		$.ajax({
			url: '/home/reserv/register', 
			data: revData,
			type: 'POST',
			success: function (data) { 
				console.log(data);
				alert('예약이 완료되었습니다.');
				changeDisabled(revTime);
			},
			error: function () {
			}
		});  
	});
	
});

function changeDisabled(revTime) {	// 라디오버튼 disabled로 변경
	$('input:radio[name="revTime"]').each(function() {
		if(this.value ===  revTime) {
			this.disabled = true;
		}
	});
}

// 사용자가 예약 날짜를 바꾸면 해당 날짜의 시간대를 초기화 해주어야 그 날짜의 예약된 시간 disabled 가능
function revTimeInit() {	// 시간대 disabled 초기화
	$('input:radio[name="revTime"]').each(function() {
	       this.disabled = false;   
	});
}

$(".reserv-close").click(function() {
	$(".reserv-modal").css("display", "none");
})