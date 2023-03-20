$("#reservModalBtn").click(function() {
	console.log("버튼 클릭");
	$(".reserv-modal").css("display", "flex");
	
	// 여기다 작성
})

$("#qnaBtn").click(function() {
	console.log("버튼 클릭");
	$(".qna-modal").css("display", "flex");
	
	// 여기다 작성
})

$(".qna-close").click(function() {
	$(".qna-modal").css("display", "none");
})

$(".reserv-close").click(function() {
	$(".reserv-modal").css("display", "none");
})



$("#reservBtn").on("click", function(e){
	console.log($('#enrollDate').val());
	$.ajax({
				url: 'reservation/enroll', 
				data: { 
				lessorId: lessorId,
				imchaId : $('#enrollName').val(),
				homeNum : homeNum,
				revDate : enrollDate + ' ' + revTime
				}, 
				type: 'POST',
				success: function (param) { 
				alert('예약이 완료되었습니다.');
				},
				error: function () {
//				alert('네트워크 오류 발생');
				}
	});  
});

//
//$("#revDate").on("change", function(e){
//	console.log($("#revDate").val());
//	$.ajax({
//		url: 'home/reservation/invaildDate',
//		data: {"revDate": $("#revDate").val()},
//		type: 'POST',
//		success: function(data){
//			$(data).each(function(){
//				console.log(data)
//			});
//		}
//		
//	});
//});

$('#revDate').on("change", function (){               //버튼 id
	$("input:radio[name='revTime']").attr("disabled", false);
	
    $.ajax({
        url:"/home/reservation/invaildDate",    //본인 url
        type:"post",
        data : {revDate:$('#revDate').val()},    //input date value
        success:function(data){
            $(data).each(function(){
                var date =this.revDate.substr(11,5);

            $('input:radio[name="revTime"]').each(function() {

                       //checked 처리

                      if(this.value ===  date){//checked 처리된 항목의 값
                            this.disabled = true;      //checked가
                            console.log("같음");
//                            $(this).setAttribute("style", "background-color: #808080");

                      }

                 });
            });

        }
    });

});