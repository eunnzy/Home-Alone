$(document).ready(function(){
	   
	   let formData = $("#findId");
      $('#findId').click(function(){
         // alert("성공!");
         if($('#nickname').val() == ''){
            alert("닉네임을 입력하세요.");
            return false;
         }
         
         if($('#phone').val() == ''){
            alert("휴대폰 번호를 입력하세요.");
            return false;
         }
         
         let imcha = {
        		 "nickname" : $('#nickname').val(),  "phone" : $('#phone').val()
        	}
         $.ajax({
        	data: imcha,
         	url: "/member/findId",
         	type : 'POST',
			success : function(result) {
				if(result == 1) {
					const url = "/member/resultId"
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