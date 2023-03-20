var geocoder = new kakao.maps.services.Geocoder();
// 작성한 주소의 위도, 경도 값 계산
var changeToLoc = function(data, status) {
	if(status  === kakao.maps.services.Status.OK) {
		$("#latitude").val(data[0].y);
		$("#longitude").val(data[0].x);
		console.log($("#latitude").val());
		console.log($("#longitude").val());
	}	
};

// 주소 검색
$("#searchPost").click(function() {
	new daum.Postcode({
	    oncomplete: function(data) {
		// 위치 검색 api
        	 var addr = '';
                var extraAddr = ''; 
 
                if (data.userSelectedType === 'R') { 
                    addr = data.roadAddress;
                } else { 
                    addr = data.jibunAddress;
                }
 
                if(data.userSelectedType === 'R'){
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                	addr += extraAddr;
                } else {
                	addr += ' ';
                }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            $("#addr1").val(data.zonecode);
            $("#addr2").val(addr);
            $("#addr3").focus();
            
            
            geocoder.addressSearch(addr, changeToLoc);
            
        }
    }).open();	 
});


// 전용 면적(m^2) -> '평' 으로 계산
$("#area").on("propertychange keyup paste input", function() { 	
	let area = $(this).val();
	let homeArea = $("#homeArea").val( Math.floor(area / 3.3) );
});




let index = 0;
let homeForm = $("#homeForm");
let homeImgList = [];    


let regex = new RegExp("(.*?)\.(jpg|png|gif|jpeg)$");	// 파일 확장자 -> jpg / pn / gif / jpeg만 가능
let maxSize = 1048576;	
function imgExtentionCheck(fileName, fileSize){	// 파일 확장자 및 크기 체크 

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
	
// 첨부한 이미지 출력
function showImage(resultArr) {
	if(!resultArr || resultArr.length == 0)	// 데이터 받지 못한경우
		return;
		
	let uploadResult = $(".resultImg");
	let str = "";
	
	$(resultArr).each(function(i, obj){
		// encodeURIComponent : 사진 이름이 한글이면 변환 해서 
		let imgPath = encodeURIComponent(obj.homeImgPath + "/t_" + obj.homeImgName);
		console.log("obj: " + obj.homeImgPath);
		
		str += "<div class='img-div col-sm-3' data-path='"+ obj.homeImgPath +"' data-imgname='"+obj.homeImgName +"'>";
		str += "<div class='imgDelete' data-file='" + imgPath + "'><i class='bi bi-x-lg'></i></div>";
   		str += "<img src='/home/manage/showHomeImg?homeImgName="+ imgPath +"'>";
		str += "</div>";
		
	});
	uploadResult.append(str);
	
}

	
// 이미지 추가 버튼 클릭시 발생
$(document).on("change", "input[type=file]", function(e){
	e. preventDefault();
	
	let formData = new FormData();
	let fileInput = $("input[name=homeImg]");
	let fileList = fileInput[0].files;
	
	if(fileList.length > 10) {
		alert("사진은 최대 10장까지 첨부가능합니다.");
		return false;
	}
	
	console.log("fileList : " + fileList);
	
	for(var i=0; i<fileList.length; i++) {
		if(!imgExtentionCheck(fileList[i].name, fileList[i].size)){	// 파일 확장자 및 크기 검사
			return false;
		}
		formData.append("homeImg", fileList[i]);
	}
		
	
	// 서버에 사진 전송
	$.ajax({
		url: '/home/manage/homeImgUpload',
    	processData : false,
    	contentType : false,
    	data : formData,
    	type : 'POST',
    	dataType : 'json',
    	success: function(result) {
    		console.log(result);
    		showImage(result);	// 사진 업로드 결과
    	}
	});
});
	
	
// 이미지 삭제
$(".resultImg").on("click", ".imgDelete", function(e){
	
	let targetImg = $(this).data("file");
	let targetDiv = $(this).parent();
	
	console.log(targetImg);
	console.log(targetDiv);
	
	$.ajax({
		url: "/home/manage/removeHomeImg",
		data: {homeImgName : targetImg},
		dataType: "text",
		type: "POST",
		success: function(result) {
			console.log(result);
			targetDiv.remove();
		},
		error: function(result) {
			console.log(result);
			console.log("삭제 못함");
		}
	});
	
	
	
});
	
	
	
// 등록하기 버튼 클릭시 submit() 전송	
$("#updateBtn").on("click",function(e){	
	e.preventDefault();
	
	let optionList = [];	// 옵션 체크 한 것 배열로 넘기기.
	$("input[name=optionList]:checked").each(function() {
		optionList.push($(this).val());
	});

	let str="";
	$(".resultImg .img-div").each(function(i, obj) {
			console.log("resultImg.each() 함수 실행 ");
			
			str += "<input type='hidden' name='homeImgList[" + i + "].homeImgName' value='"+ $(obj).data("imgname") +"'>";
			str += "<input type='hidden' name='homeImgList[" + i + "].homeImgPath' value='"+ $(obj).data("path") +"'>";	
	
			let homeImgName = $(obj).data("imgname");
			let homeImgPath = $(obj).data("path");
			
			// homeImgData = { homeImgPath : homeImgPath, homeImgName: homeImgName };
			
			let homeImgData = homeImgPath + " " + homeImgName;
			homeImgList.push(homeImgData);
		}); 
	
	homeForm.append(str);
	
	// 유효성 확인.
	let homeType = $("input[name=homeType]:checked").val();
	let addr1 =  $("input[name=addr1]").val();
	let addr2 =  $("input[name=addr2]").val();
	let addr3 =  $("input[name=addr3]").val();
	let latitude =  $("input[name=latitude]").val();
	let longitude = $("input[name=longitude]").val();
	let homeArea = $("input[name=homeArea]").val();
	let rentType = $("input[name=rentType]:checked").val();
	let deposit = $("input[name=deposit]").val();
	let monthly = $("input[name=monthly]").val();
	let rentPeriods =  $("input[name=rentPeriods]").val();
	let roomCount = $("input[name=roomCount]").val();
	let adminCost = $("input[name=adminCost]").val();
	let parking = $("input[name=parking]").val();
	let pet = $("input[name=pet]:checked").val();
	let elevator = $("input[name=elevator]:checked").val();
	let balcony = $("input[name=balcony]:checked").val();
	let moveDate = $("input[name=moveDate]").val();
	let floor = $("input[name=floor]").val();
	let homeTitle = $("input[name=homeTitle]").val();
	let homeDetail = $("textarea[name=homeDetail]").val();
	
	
	// 유효성 검사
	if(homeType == "") {
		alert("방 종류를 선택해주세요.");
		return false;
	}
	
	if(addr1 == "" || addr2 == "" || addr3 == "") {
		alert("주소 정보를 확인해주세요.");
		return false; 		
	}
	
	if($("#area").val() == "") {
		alert("집 면적을 입력해주세요.");
		return;
	}
	
	if(rentType == "") {
		alert("거래 종류를 선택해주세요.");
		return false;
	}
	
	
	if(deposit == "") {
		alert("보증금을 입력해주세요.");
		return false;
	}
	
	if(monthly == "") {
		alert("월세를 작성해주세요.");
		return false;
	}
	
	if(rentPeriods == "") {
		alert("임대 기간을 입력해주세요.");
		return false;
	}
	
	if(roomCount == "") {
		alert("방 개수를 입력해주세요.");
		return false;
	}
	
	if(adminCost == "") {
		alert("관리비를 입력해주세요.(없는 경우 0으로 작성)");
		return false;
	}
	
	if(parking == "") {
		alert("주차 가능 대수를 입력해주세요.");
		return false;
	}
	
	if(pet == "") {
		alert("반려동물 가능 여부를 선택해주세요.");
		return false;
	}
	
	if(elevator == "") {
		alert("엘리베이터 가능 여부를 선택해주세요.");
		return false;
	}
	
	if(balcony == "") {
		alert("발코니 및 베란다 가능 여부를 선택해주세요.");
		return false;
	}
	
	if(moveDate == "") {
		alert("입주 가능일을 입력해주세요.");
		return false;
	}
	
	if(floor == "") {
		alert("건물의 해당 층수를 입력해주세요.");
		return false;
	}
	
	if(homeImgList.length < 2) {
		e.preventDefault();
		alert("사진은 최소 2장 첨부해야 합니다.");
		return false;
	}
	
	if(homeTitle == "") {
		alert("게시글 제목을 입력해주세요");
		return false;
	}
	
	if(homeDetail == ""){
		alert("게시글 상세정보를 입력해주세요");
		return false;
	}
	
	
	let homeData= {
				"homeNum" : homeNum, 
	    		"homeType" : homeType,
	    		"addr1" : addr1,
	    		"addr2" : addr2,
	    		"addr3" : addr3,
	    		"latitude" : latitude,
	    		"longitude": longitude,
	    		"homeArea" : homeArea,
	    		"rentType" : rentType,
	    		"deposit" : deposit * 10000,
	    		"monthly" : monthly * 10000,
	    		"rentPeriods" : rentPeriods,
	    		"roomCount" : roomCount,
	    		"adminCost" : adminCost  * 10000,
	    		"parking" : parking,
	    		"pet" : pet,
	    		"elevator" : elevator,
	    		"balcony" : balcony,
	    		"moveDate" : moveDate,
	    		"floor" : floor,
	    		"optionList" : optionList,
	    		"homeImgList": homeImgList,
	    		"homeTitle" : homeTitle,
	    		"homeDetail" : homeDetail
		}
	
	
	console.log(homeData);
	$.ajax({
			type:'post',
			url: '/home/manage/modify',
			data: JSON.stringify(homeData),
            contentType: "application/json",
			success:function(data, status, xhr){
				var msg = (data==1) ? "글 수정이 완료되었습니다." : "글 수정을 실패하였습니다.";
				alert(msg);
				location.href="/home/manage/list";
	 		},
	 		error: function(xhr, status, error){console.log(xhr.status, status)}
		}); 
	
	
});
		
