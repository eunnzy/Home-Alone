	// 아이디 중복검사
	$('.form-id').on("propertychange change keyup paste input", function(){
		
		var adminId = $('.form-id').val();
		var data  = {adminId : adminId}
		
		$.ajax({
			type : "post",
			url : "/admin/adminIdChk",
			data : data,
			success : function(result) {
				if(result != 'fail') {
					$('.id_input_re_1').css("display","inline-block");
					$('.id_input_re_2').css("display","none");
				} else {
					$('.id_input_re_2').css("display","inline-block");
					$('.id_input_re_1').css("display","none");
				}
			}
		}); 
	});

	
	// 비밀번호 확인
	$('.form-passwordCheck').on("propertychange change keyup paste input", function(){
		
		var adminPw = $('.form-password').val();
		var pwck    = $('.form-passwordCheck').val();
		$('.final_pwck_ck').css('display','none');
		
		if (adminPw == pwck) {
			$('.pwck_input_re_1').css('display', 'block');
			$('.pwck_input_re_2').css('display', 'none');
			pwckcorCheck = true;
		}else {
			$('.pwck_input_re_1').css('display', 'none');
			$('.pwck_input_re_2').css('display', 'block');
			pwckcorCheck = false;
		}
	});
	
//	$(".btn btn-primary btn btn-block").click(function() {
//		
//		alert("로그인 버튼 작동");
//	});
