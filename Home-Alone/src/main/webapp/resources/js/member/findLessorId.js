$(document).ready(function(){
	   
	   let formData = $("#findLessorId");
      $('#findId').click(function(){
         // alert("성공!");
         if($('#lessorNickName').val() == ''){
            alert("닉네임을 입력하세요.");
            return false;
         }
         
         if($('#phone').val() == ''){
            alert("휴대폰 번호를 입력하세요.");
            return false;
         }
         
         let lessor = {
        		 "lessorNickName" : $('#lessorNickName').val(),  "phone" : $('#phone').val()
        	}
         $.ajax({
        	data: lessor,
         	url: "/member/findLessorId",
         	type : 'POST',
			success : function(result) {
				if(result == 1) {
					const url = "/member/resultLessorId"
					window.open(url,"아이디찾기",'width=500px, height=700px, scrolbars=yes, resizeable=no');
				}
				else {
					alert("입력하신 정보가 올바르지 않습니다.");
				}
			},
         	error : function(result) {
				console.log(result);
				
				alert("전송 실패");
			}
         })
         
         
         formData.submit();
         
       
      });
      
      
      
   });