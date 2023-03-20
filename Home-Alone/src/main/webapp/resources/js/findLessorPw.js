	$(document).ready(function(){
		$('#findLessorPw').submit(function(){
			// alert("성공!");
			if($('#lessorId').val() == ''){
				alert("아이디를 입력하세요.");
				return false;
			}
			
			if($('#phone').val() == ''){
				alert("휴대폰 번호를 입력하세요.");
				return false;
			}
			
		});	
	});