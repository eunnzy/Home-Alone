////	$(document).ready(function(){
////		$(".join_button").click(function(){
////			$("#join_form").attr("action", "/member/imchaJoin");
////			$("#join_form").submit();
////		});
////	});
////	
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
//		var lessorId = $('.form-id').val();
//		var lessorPw = $('.form-password').val();
//		var pwck    = $('.form-passwordCheck').val();
//		var lessorNickName = $('.form-control').val();
//		var phone   = $('.form-control').val();
//		var imchaAddr3 = $('.form-control_3').val();
//	});
//	
//	if(lessorId == "") {
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
//	if(lessorNickName == "") {
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
//	if(lessorAddr3 = "") {
//		$('.final_imchaAddr3_ck').css('display','block');
//		addressCheck = false; 
//	} else {
//		$('.final_imchaAddr3_ck').css('display','none');
//		addressCheck = true;
//	}
//	
//	if(idCheck&idckCheck&pwCheck&pwckCheck&nicknameCheck&phoneCheck&addressCheck){
//		$("#validation-form").attr("action", "/member/lessorJoin");
//		$("#validation-form").submit();
//	}
//	
//	
//	
//});
	// 아이디 중복검사
	$('.form-id').on("propertychange change keyup paste input", function(){
		
//		console.log("keyup 테스트");
		var lessorId = $('.form-id').val();
		var data  = {lessorId : lessorId}
		
		$.ajax({
			type : "post",
			url : "/member/lessorIdChk",
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
			
			var lessorNickName = $('.form-nickname').val();
			var data  = {lessorNickName : lessorNickName}
			
			$.ajax({
				type : "post",
				url : "/member/lessorNickNameChk",
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
	 
	// 주소연동
	function execution_daum_address(){
	 
	    new daum.Postcode({
	        oncomplete: function(data) {
	        	// 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    addr += extraAddr;
                
                } else {
                   addr += ' ';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $(".form-control_1").val(data.zonecode);
                $(".form-control_2").val(addr);
                // 커서를 상세주소 필드로 이동한다.
                $(".form-control_3").attr("readonly",false);
                $(".form-control_3").focus();
            } 
	    }).open();    
	 
	}
	
	// 비밀번호 확인
	$('.form-passwordCheck').on("propertychange change keyup paste input", function(){
		
		var lessorPw = $('.form-password').val();
		var pwck    = $('.form-passwordCheck').val();
		$('.final_pwck_ck').css('display','none');
		
		if (lessorPw == pwck) {
			$('.pwck_input_re_1').css('display', 'block');
			$('.pwck_input_re_2').css('display', 'none');
			pwckcorCheck = true;
		}else {
			$('.pwck_input_re_1').css('display', 'none');
			$('.pwck_input_re_2').css('display', 'block');
			pwckcorCheck = false;
		}
	});
	
	// 이미지 업로드
	$("input[type='file']").on("change", function(e){
		//alert("동작");
		
		// 이미 존재할시 삭제
		if($(".imgDeleteBtn").length > 0) {
			deleteFile();
		}
		let formData = new FormData();
		let fileInput = $('input[name="uploadFile"]');
		let fileList = fileInput[0].files;
		let fileObj = fileList[0];
		
		if(!fileCheck(fileObj.name, fileObj.size)){
			return false;
		}
		
		alert("파일을 첨부하였습니다.");
		
		console.log("fileList :" + fileList);
		console.log("fileObj :" + fileObj);
		console.log("fileName :" + fileObj.name);
		console.log("fileSize :" + fileObj.size);
		console.log("fileType(MimeType) :" + fileObj.type);
		
		formData.append("uploadFile",fileObj);
		
		$.ajax({
			url : '/member/uploadAjaxAction',
			processData : false,
			contentType : false,
			data : formData,
			type : 'POST',
			dataType : 'json',
			success : function(result) {
				console.log(result);
				showUploadImage(result);
			},
			error : function(result){
				alert("이미지 파일이 아닙니다.");
			}
		});
	});
	
	// 파일 체크
	let regex = new RegExp("(.*?)\.(jpg|png)$");
	let maxSize = 1048576; // 1MB
	
	function fileCheck(fileName, fileSize) {
		
		if(fileSize >= maxSize){
			alert("파일 사이즈 초과");
			return false;
		}
		if(!regex.test(fileName)){
			alert("해당 종류의 파일은 업로드할 수 없습니다.");
			return false;
		}
		
		return true;
	}
	
	// 이미지 출력
	function showUploadImage(uploadResultArr) {
		
		// 전달받은 데이터 검증
		if(!uploadResultArr || uploadResultArr.length == 0) {
			return
		}
		let uploadResult = $("#uploadResult");
		
		let obj = uploadResultArr[0];
		
		let str = "";
		
		let fileCallPath = obj.uploadPath.replace(/\\/g, '/') + "/s_" + obj.uuid + "_" + obj.fileName;
		
		str += "<div id='result_card'>";
		str += "<img src='/member/display?fileName=" + fileCallPath +"'>";
		str += "<div class='imgDeleteBtn' data-file='" + fileCallPath + "'>x</div>";
		str += "<input type='hidden' name='lessorImg.fileName' value='" + obj.fileName + "'>";
		str += "<input type='hidden' name='lessorImg.uuid' value='" + obj.uuid + "'>";
		str += "<input type='hidden' name='lessorImg.uploadPath' value='" + obj.uploadPath + "'>";
		str += "</div>";
		
		uploadResult.append(str);
		
	}
	
	// 파일 삭제
	function deleteFile() {
		
		let targetFile = $(".imgDeleteBtn").data("file");
		
		let targetDiv = $("#result_card");
		
		$.ajax ({
			
			url : "/member/deleteFile",
			data : {fileName : targetFile},
			dataType : 'text',
			type : 'POST',
			success : function(result) {
				console.log(result);
				
				targetDiv.remove();
				$("input[type='file']").val("");
			},
			error : function(result) {
				console.log(result);
				
				alert("파일을 삭제하지 못했습니다.")
			}
		});
	}
	
	// 이미지 삭제 버튼 작동
	$("#uploadResult").on("click", ".imgDeleteBtn", function(e) {
		
		deleteFile();
	});
	
	
//	$(".btn btn-primary btn btn-block").click(function() {
//		
//		alert("로그인 버튼 작동");
//	});
