function displayImg(lessorId) {
	console.log(lessorId);
	const url = "/member/lessorImg?lessorId="+lessorId;
	window.open(url,"아이디찾기",'width=500px, height=700px, scrolbars=yes, resizeable=no');

}

$(function(){
$('.success').on('click', function() { 
	  
	  //현재 row의 정보 가져오기 
	  var thisRow = $(this).closest('tr'); 
	  
	  //주소 input 값 가져오기
	  var successId = thisRow.find('td:eq(0)').text();
	  
	 
	  console.log(successId);
	  
$.ajax({
	type : 'post',
	url : "/admin/successId",
	data : {
		lessorId : successId,
	},
	success : function(data){
		if(data == 1) {
			console.log(data)
			alert(successId + "님, 회원가입을 승인하였습니다.");
			thisRow.find('td:eq(6)').html("승인완료")
		} else {
			alert("실패하였습니다.")
		}
	}
});
});
});