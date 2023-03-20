//	$(document).ready(function(){
//		$(".join_button").click(function(){
//			$("#join_form").attr("action", "/member/imchaJoin");
//			$("#join_form").submit();
//		});
//	});
//	
// var code = "";
// 
// var idCheck = false;
// var idckCheck = false;
// var pwCheck = false;
// var pwckCheck = false;
// var nicknameCheck = false;
// var phoneCheck = false;
// var addressCheck = false;
// 
//$(document).ready(function(){
//	$("btn btn-primary btn btn-block").click(function(){
//		
//		var imchaId = $('.form-id').val();
//		var imchaPw = $('.form-password').val();
//		var pwck    = $('.form-passwordCheck').val();
//		var nickname = $('.form-control').val();
//		var phone   = $('.form-control').val();
//		var imchaAddr3 = $('.form-control_3').val();
//	});
//	
//	if(imchaId == "") {
//		$('.final_id_ck').css('display', 'block');
//		idCheck = false;
//	} else {
//		$('.final_id_ck').css('display', 'none');
//		idCheck = true;
//	}
//	
//	if(pwck == "") {
//		$('.final_pwck_ck').css('display', 'block');
//		pwckCheck = false;
//	} else {
//		$('.final_pwck_ck').css('display', 'none');
//		pwckCheck = true;
//	}
//	
//	if(nickname == "") {
//		$('.final_name_ck').css('display','block');
//		nicknameCheck = false;
//	} else {
//		$('.final_name_ck').css('display','none');
//		nicknameCheck = true;
//	}
//	
//	if(phone == "") {
//		$('.final_phone_ck').css('display','block');
//		phoneCheck = false;
//	} else {
//		$('.final_phone_ck').css('display','none');
//		phoncheck = true;
//	}
//	
//	if(imchaAddr3 = "") {
//		$('.final_imchaAddr3_ck').css('display','block');
//		addressCheck = false; 
//	} else {
//		$('.final_imchaAddr3_ck').css('display','none');
//		addressCheck = true;
//	}
//	
//	if(idCheck&idckCheck&pwCheck&pwckCheck&nicknameCheck&phoneCheck&addressCheck){
//		$("#validation-form").attr("action", "/member/userJoin");
//		$("#validation-form").submit();
//	}
//	
//	
//});
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
