//	$(document).ready(function(){
//		$(".join_button").click(function(){
//			$("#join_form").attr("action", "/member/imchaJoin");
//			$("#join_form").submit();
//		});
//	});
//	
 var code = "";
 
 var idCheck = false;
 var idckCheck = false;
 var pwCheck = false;
 var pwckCheck = false;
 var nicknameCheck = false;
 var phoneCheck = false;
 var addressCheck = false;
 
$(document).ready(function(){
	$("btn btn-primary btn btn-block").click(function(){
		
		var imchaId = $('.form-id').val();
		var imchaPw = $('.form-password').val();
		var pwck    = $('.form-passwordCheck').val();
		var nickname = $('.form-control').val();
		var phone   = $('.form-control').val();
		var imchaAddr3 = $('.form-control_3').val();
	});
	
	if(imchaId == "") {
		$('.final_id_ck').css('display', 'block');
		idCheck = false;
	} else {
		$('.final_id_ck').css('display', 'none');
		idCheck = true;
	}
	
	if(pwck == "") {
		$('.final_pwck_ck').css('display', 'block');
		pwckCheck = false;
	} else {
		$('.final_pwck_ck').css('display', 'none');
		pwckCheck = true;
	}
	
	if(nickname == "") {
		$('.final_name_ck').css('display','block');
		nicknameCheck = false;
	} else {
		$('.final_name_ck').css('display','none');
		nicknameCheck = true;
	}
	
	if(phone == "") {
		$('.final_phone_ck').css('display','block');
		phoneCheck = false;
	} else {
		$('.final_phone_ck').css('display','none');
		phoncheck = true;
	}
	
	if(imchaAddr3 = "") {
		$('.final_imchaAddr3_ck').css('display','block');
		addressCheck = false; 
	} else {
		$('.final_imchaAddr3_ck').css('display','none');
		addressCheck = true;
	}
	
	if(idCheck&idckCheck&pwCheck&pwckCheck&nicknameCheck&phoneCheck&addressCheck){
		$("#validation-form").attr("action", "/member/userJoin");
		$("#validation-form").submit();
	}
	
	
});
	// 아이디 중복검사
	$('.form-id').on("propertychange change keyup paste input", function(){
		
		var imchaId = $('.form-id').val();
		var data  = {imchaId : imchaId}
		
		$.ajax({
			type : "post",
			url : "/member/imchaIdChk",
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
	
	// 닉네임 중복검사
	$('.form-nickname').on("propertychange change keyup paste input", function(){
			
			var nickname = $('.form-nickname').val();
			var data  = {nickname : nickname}
			
			$.ajax({
				type : "post",
				url : "/member/nicknameChk",
				data : data,
				success : function(result) {
					if(result != 'fail') {
						$('.nickname_input_re_1').css("display","inline-block");
						$('.nickname_input_re_2').css("display","none");
					} else {
						$('.nickname_input_re_2').css("display","inline-block");
						$('.nickname_input_re_1').css("display","none");
					}
				}
			}); 
		});
	 
//	// 주소연동
//	function execution_daum_address(){
//	 
//	    new daum.Postcode({
//	        oncomplete: function(data) {
//	        	// 각 주소의 노출 규칙에 따라 주소를 조합한다.
//                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
//                var addr = ''; // 주소 변수
//                var extraAddr = ''; // 참고항목 변수
//
//                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
//                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
//                    addr = data.roadAddress;
//                } else { // 사용자가 지번 주소를 선택했을 경우(J)
//                    addr = data.jibunAddress;
//                }
//
//                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
//                if(data.userSelectedType === 'R'){
//                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
//                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
//                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
//                        extraAddr += data.bname;
//                    }
//                    // 건물명이 있고, 공동주택일 경우 추가한다.
//                    if(data.buildingName !== '' && data.apartment === 'Y'){
//                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
//                    }
//                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
//                    if(extraAddr !== ''){
//                        extraAddr = ' (' + extraAddr + ')';
//                    }
//                    addr += extraAddr;
//                
//                } else {
//                   addr += ' ';
//                }
//
//                // 우편번호와 주소 정보를 해당 필드에 넣는다.
//                $(".form-control_1").val(data.zonecode);
//                $(".form-control_2").val(addr);
//                // 커서를 상세주소 필드로 이동한다.
//                $(".form-control_3").attr("readonly",false);
//                $(".form-control_3").focus();
//            } 
//	    }).open();    
//	 
//	}
	// 주소
	
	$('document').ready(function() {
		 var area0 = ["시/도 선택","서울특별시","인천광역시","대전광역시","광주광역시","대구광역시","울산광역시","부산광역시","경기도","강원도","충청북도","충청남도","전라북도","전라남도","경상북도","경상남도","제주도"];
		  var area1 = ["강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구","용산구","은평구","종로구","중구","중랑구"];
		   var area2 = ["계양구","남구","남동구","동구","부평구","서구","연수구","중구","강화군","옹진군"];
		   var area3 = ["대덕구","동구","서구","유성구","중구"];
		   var area4 = ["광산구","남구","동구",     "북구","서구"];
		   var area5 = ["남구","달서구","동구","북구","서구","수성구","중구","달성군"];
		   var area6 = ["남구","동구","북구","중구","울주군"];
		   var area7 = ["강서구","금정구","남구","동구","동래구","부산진구","북구","사상구","사하구","서구","수영구","연제구","영도구","중구","해운대구","기장군"];
		   var area8 = ["고양시","과천시","광명시","광주시","구리시","군포시","김포시","남양주시","동두천시","부천시","성남시","수원시","시흥시","안산시","안성시","안양시","양주시","오산시","용인시","의왕시","의정부시","이천시","파주시","평택시","포천시","하남시","화성시","가평군","양평군","여주군","연천군"];
		   var area9 = ["강릉시","동해시","삼척시","속초시","원주시","춘천시","태백시","고성군","양구군","양양군","영월군","인제군","정선군","철원군","평창군","홍천군","화천군","횡성군"];
		   var area10 = ["제천시","청주시","충주시","괴산군","단양군","보은군","영동군","옥천군","음성군","증평군","진천군","청원군"];
		   var area11 = ["계룡시","공주시","논산시","보령시","서산시","아산시","천안시","금산군","당진군","부여군","서천군","연기군","예산군","청양군","태안군","홍성군"];
		   var area12 = ["군산시","김제시","남원시","익산시","전주시","정읍시","고창군","무주군","부안군","순창군","완주군","임실군","장수군","진안군"];
		   var area13 = ["광양시","나주시","목포시","순천시","여수시","강진군","고흥군","곡성군","구례군","담양군","무안군","보성군","신안군","영광군","영암군","완도군","장성군","장흥군","진도군","함평군","해남군","화순군"];
		   var area14 = ["경산시","경주시","구미시","김천시","문경시","상주시","안동시","영주시","영천시","포항시","고령군","군위군","봉화군","성주군","영덕군","영양군","예천군","울릉군","울진군","의성군","청도군","청송군","칠곡군"];
		   var area15 = ["거제시","김해시","마산시","밀양시","사천시","양산시","진주시","진해시","창원시","통영시","거창군","고성군","남해군","산청군","의령군","창녕군","하동군","함안군","함양군","합천군"];
		   var area16 = ["서귀포시","제주시","남제주군","북제주군"];

		 

		 // 시/도 선택 박스 초기화

		 $("select[name^=sido]").each(function() {
		  $selsido = $(this);
		  $.each(eval(area0), function() {
		   $selsido.append("<option value='"+this+"'>"+this+"</option>");
		  });
		  $selsido.next().append("<option value=''>구/군 선택</option>");
		 });

		 

		 // 시/도 선택시 구/군 설정

		 $("select[name^=sido]").change(function() {
		  var area = "area"+$("option",$(this)).index($("option:selected",$(this))); // 선택지역의 구군 Array
		  var $gugun = $(this).next(); // 선택영역 군구 객체
		  $("option",$gugun).remove(); // 구군 초기화

		  if(area == "area0")
		   $gugun.append("<option value=''>구/군 선택</option>");
		  else {
		   $.each(eval(area), function() {
		    $gugun.append("<option value='"+this+"'>"+this+"</option>");
		   });
		  }
		 });


		});
	
	// 비밀번호 확인
	$('.form-passwordCheck').on("propertychange change keyup paste input", function(){
		
		var imchaPw = $('.form-password').val();
		var pwck    = $('.form-passwordCheck').val();
		$('.final_pwck_ck').css('display','none');
		
		if (imchaPw == pwck) {
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
