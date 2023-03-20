<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>매물 수정</title>
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link href="/css/bootstrap.min.css" rel="stylesheet"></link>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
	<link href="/css/registerHome.css"  type="text/css" rel="stylesheet" >
	<style>
	/*  
		photo 이미지 출처 : 
		https://www.flaticon.com/free-icon/picture_2659360?term=photo&page=1&position=8&origin=tag&related_id=2659360 
	*/		
    </style>
</head>
<body>
	<header>
		<jsp:include page="../../header.jsp"></jsp:include>
	</header>
	
	<div class="home-container col-md-9 mx-auto">
		<h1 class="mt-3 mb-3"> 매물 정보 수정 </h1>
		<div class="add-notice p-3 mx-auto mb-5" >
        	<ul>
        		<li>전/월세 매물만 등록할 수 있습니다.</li>
        		<li>매물 등록시 바로 거래가 가능합니다. </li>
        		<li>허위 매물 등록시 웹 사이트 이용에 제한이 있을 수 있습니다. 이 점을 주의하여 사실 정보만을 기입하길 바랍니다.</li>
        	</ul>
       	</div>
       	<div class="form-div">
       		<form id="homeForm" method="post">
       			<table class="table mb-5">
                    <thead>
                        <tr>
                            <th colspan="2" > <h3 class="text-center"> 매물 종류 </h3> </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td> 방 종류  </td>
                            <td class="radio-btn"> 
                            	<div class="form-check form-check-inline">
                                	<input type="radio" class="form-check-input" name="homeType" value="원룸"> <label> 원룸 </label> 
                                </div>
                                <div class="form-check form-check-inline">
                                	<input type="radio" class="form-check-input" name="homeType" value="투룸"> <label> 투룸 </label> 
                                </div>
                                <div class="form-check form-check-inline">
                                	<input type="radio" class="form-check-input" name="homeType" value="쓰리룸 이상"> <label> 쓰리룸 이상 </label> 
                                </div>
                                <div class="form-check form-check-inline">
                                	<input type="radio" class="form-check-input" name="homeType" value="오피스텔"> <label> 오피스텔 </label> 
                                </div>
                                <div class="form-check form-check-inline">
                                	<input type="radio" class="form-check-input" name="homeType" value="아파트"> <label> 아파트 </label> 
                                </div>
                                <div class="form-check form-check-inline">
                                	<input type="radio" class="form-check-input" name="homeType" value="쉐어하우스"> <label> 쉐어하우스 </label> 
                            	</div>
                            </td>
                        </tr>
                    </tbody>
               	</table>
               	
               	<table class="table mb-5">
                    <thead>
	                    <tr>
                            <th colspan="2"> <h3 class="text-center"> 위치 정보 </h3> </th>
                        </tr>
                   	</thead>
                   	<tbody>
                        <tr>
                            <td> 주소  </td>
                            <td> 
                            	 <div class="row mb-3">
								    <label for="addr1" class="col-sm-2">우편번호</label>
								    <div class="col-sm-5">
								   		<input type="text" class="form-control" name="addr1" id="addr1" value="${home.addr1 }">
							    	</div>
							    	<div class="col-sm-4">
							    		<button type="button" class="btn btn-primary" id="searchPost"> 주소 검색</button> 
							    	</div>
							    </div>
							    <div class="row mb-3">
								    <label for="addr2" class="col-sm-2">주소</label>
								    <div class="col-sm-10">
								   		<input type="text" class="form-control" name="addr2" id="addr2" value="${home.addr2 }">
							    	</div>
							    </div>
							     <div class="row mb-3">
								    <label for="addr3" class="col-sm-2">상세정보</label>
								    <div class="col-sm-10">
								   		<input type="text" class="form-control" name="addr3" id="addr3" value="${home.addr3 }">  
							    	</div>
							    </div>
							    <input type="hidden" name="latitude" id="latitude" value="${home.latitude }">
							    <input type="hidden" name="longitude" id="longitude" value="${home.longitude }">
                            </td>
                        </tr>
                  		</tbody>
               </table>
                    
               <table class="table mb-5">
            	<thead>	
                  		<tr>
                           <th colspan="2"> <h3 class="text-center"> 매물 정보 </h3> </th>
                       </tr>
                   </thead>
                   <tbody>
                       <tr>
                           <td> 크기  </td>
                           <td> 
                           	<div class="row">
								<div class="col-sm-auto">
									<label for="homeArea">전용 면적:</label>
								</div>                            	
							    <div class="col-sm-auto">
							   		<input class="form-control" type="text" id="area" value="${home.homeArea* 3.3 } ">
						    	</div>
						    	<div class="col-sm-auto">
							   		<label>m<sup>2</sup></label>
						    	</div>
						    	<div class="col-sm-auto">
							   		<input class="form-control" type="text" name="homeArea" id="homeArea" value="${home.homeArea }" readonly>
						    	</div>
						    	<div class="col-sm-auto">
							   		<label>평</label>
						    	</div>
						    </div>
                           </td>
                       </tr>
                       <tr>
                           <td> 거래 종류  </td>
                           <td>
                               <input type="radio" class="form-check-input" name="rentType" value="월세"> <label> 월세 </label> 
                               <input type="radio" class="form-check-input" name="rentType" value="전세"> <label> 전세 </label> 
                           </td>
                       </tr>
                       <tr>
                           <td> 보증금  </td>
                           <td>
                           	<div class="row mb-3">
							    <div class="col-auto">
							   		 <input class="form-control" type="text" name="deposit" value="${home.deposit }">
						    	</div>
						    	<div class="col-auto">
							   		<label>만원</label>
						    	</div>
						    </div>
                           </td>
                       </tr>
                       <tr>
                           <td> 월세  </td>
                           <td>
	                           	<div class="row mb-3">
								    <div class="col-auto">
								   		<input class="form-control" type="text" name="monthly" value="${home.monthly }">
							    	</div>
						    		<div class="col-auto">
							   			<label>만원</label>
							   		</div>
							   		<div class="col-auto">
							   			<label>* 전세인 경우 0으로 작성</label>
							   		</div>
						    		</div>
						   	 	</div>
                           </td>
                       </tr>
                       <tr>
                           <td> 임대 기간  </td>
                           <td>    
                           		<div class="row mb-3">
								    <div class="col-auto">
								   		<input class="form-control" type="text" name="rentPeriods" value="${home.rentPeriods }">
							    	</div>
						    		<div class="col-auto">
							   			<label>년</label>
							   		</div>
						   	 	</div>
                           </td>
                       </tr>
                       <tr>
                           <td> 방 개수  </td>
                           <td>    
                        		<div class="row mb-3">
								    <div class="col-auto">
								   		<input class="form-control" type="text" name="roomCount" value="${home.roomCount }">
							    	</div>
						    		<div class="col-auto">
							   			<label>개</label>
							   		</div>
							   	</div>
                           </td>
                       </tr>
                   </tbody>
               </table>
               
               <table class="table mb-5">
                   <thead>
                       <tr>
                           <th colspan="2"> <h3 class="text-center"> 추가 정보 </h3> </th>
                       </tr>
                   </thead>
                   <tbody>
                       <tr>
                           <td> 관리비  </td>
                           <td> 
                           		<div class="row mb-3">
								    <div class="col-auto">
								   		<input class="form-control" type="text" name="adminCost" value="${home.adminCost }">
							    	</div>
						    		<div class="col-auto">
							   			<label>만원</label>  
						    		</div>
						   	 	</div>
                           </td>
                       </tr>
                       <tr>
                           <td> 주차  </td>
                           <td>
                           		<div class="row mb-3">
								    <div class="col-auto">
								   		<input class="form-control" type="text" name="parking" value="${home.parking }">
							    	</div>
						    		<div class="col-auto">
							   			<label>대</label> 
						    		</div>
						   	 	</div>
                           </td>
                       </tr>
                       <tr>
                           <td> 반려동물 </td>
                           <td class="radio-btn">
                               <input class="form-check-input" type="radio" name="pet" value="가능"> <label> 가능 </label> 
                               <input class="form-check-input" type="radio" name="pet" value="불가능"> <label> 불가능 </label> 
                           </td>
                       </tr>
                       <tr>
                           <td> 엘리베이터 </td>
                           <td class="radio-btn">
                               <input class="form-check-input" type="radio" name="elevator" value="가능"> <label> 가능 </label> 
                               <input class="form-check-input" type="radio" name="elevator" value="불가능"> <label> 불가능 </label> 
                           </td>
                       </tr>
                       <tr>
                           <td> 베란다 / 발코니  </td>
                           <td class="radio-btn">    
                               <input class="form-check-input" type="radio" name="balcony" value="가능"> <label> 가능 </label> 
                               <input class="form-check-input" type="radio" name="balcony" value="불가능"> <label> 불가능 </label> 
                           </td>
                       </tr>
                       <tr>
                           <td> 입주 가능 일  </td>
                           <td>    
                           		<div class="col-3">
                               		<input class="form-control" type="date" name="moveDate" value="<fmt:formatDate  value="${home.moveDate}" pattern="yyyy-MM-dd" />"> 
                               </div>
                           </td>
                       </tr>
                       <tr>
                           <td> 층  </td>
                           <td>
                           		<div class="row mb-3">
									<div class="col-auto">
										<label> 해당 층: </label> 
									</div>                            	
							    <div class="col-auto">
							   		 <input class="form-control" type="text" name="floor" value="${home.floor }">
						    	</div>
						    	<div class="col-auto">
							   		<label> * 지하인 경우 ex) -1 </label>
						    	</div>
						    </div>
                              	
                           </td>
                       </tr>
                       <tr>
                           <td>옵션 </td>
                           <td>
                           		<div class="form-check form-check-inline">
                                	<input type="checkbox" class="form-check-input" autocomplete="off" name="optionList" value="세탁기">
								 	<label class="form-check-label">세탁기</label>
                                </div>
                                <div class="form-check form-check-inline">
                                	<input type="checkbox" class="form-check-input"  autocomplete="off" name="optionList" value="TV">
								 	<label class="form-check-label">TV</label>
                                </div>
                                <div class="form-check form-check-inline">
                                	<input type="checkbox" class="form-check-input" autocomplete="off" name="optionList" value="전자레인지">
								 	<label class="form-check-label" >전자레인지 </label>
                                </div>
                                <div class="form-check form-check-inline">
                                	<input type="checkbox" class="form-check-input" autocomplete="off" name="optionList" value="인덕션">
								 	<label class="form-check-label" >인덕션</label>
                                </div>
                                <div class="form-check form-check-inline">
                                	<input type="checkbox" class="form-check-input" autocomplete="off" name="optionList" value="책상">
								 	<label class="form-check-label" >책상 </label>
                                </div>
                                <div class="form-check form-check-inline">
                                	<input type="checkbox" class="form-check-input" autocomplete="off" name="optionList" value="침대">
								 	<label class="form-check-label" >침대 </label>
                                </div>
                                <div class="form-check form-check-inline">
                                	<input type="checkbox" class="form-check-input" autocomplete="off" name="optionList" value="에어컨">
								 	<label class="form-check-label">에어컨 </label>
                                </div>
							 	<div class="form-check form-check-inline">
                                	<input type="checkbox" class="form-check-input"autocomplete="off" name="optionList" value="냉장고">
								 	<label class="form-check-label">냉장고 </label>
                                </div>
                                <div class="form-check form-check-inline">
                                	<input type="checkbox" class="form-check-input" autocomplete="off" name="optionList" value="신발장">
								 	<label class="form-check-label">신발장 </label>
                                </div>
							 	<div class="form-check form-check-inline">
                                	<input type="checkbox" class="form-check-input" autocomplete="off" name="optionList" value="옷장">
								 	<label class="form-check-label">옷장 </label>
                                </div>
                                <div class="form-check form-check-inline">
                                	<input type="checkbox" class="form-check-input"autocomplete="off" name="optionList" value="도어락">
								 	<label class="form-check-label" >도어락 </label>
                                </div>
                                <div class="form-check form-check-inline">
                                	<input type="checkbox" class="form-check-input"  autocomplete="off" name="optionList" value="비데">
								 	<label  class="form-check-label">비데</label>
                                </div>
                           </td>
                       </tr>
                   </tbody>
               </table>
               
                <table class="table mb-5">
                   <thead>
                       <tr>
                           <th colspan="2" class="text-center"> <h3> 상세 정보 </h3> </th>
                       </tr>
                   </thead>
                   <tbody >
                       <tr>
                           <td> 제목  </td>
                           <td> 
                               <input class="form-control" type="text" name="homeTitle" value="${home.homeTitle }">           
                           </td>
                       </tr>
                       <tr>
                           <td> 상세 설명  </td>
                           <td><textarea class="form-control"  name="homeDetail" rows="10">${home.homeDetail }</textarea>
                           </td>
                       </tr>
                   </tbody>
               </table>
                <table class="table mb-5">
                   <thead>
                       <tr>
                           <th colspan="2"> <h3 class="text-center"> 사진 등록 </h3> </th>
                       </tr>
                   </thead>
                   <tbody>
	            	<tr>
		                <td>
		                	<div class="add-notice p-3 mx-auto mb-3">
					        	<ul>
					        		<li> 사진은 최소 2장 이상 등록해야 합니다.</li>
					        		<li> 최대 10장까지 올릴 수 있습니다.</li>
					        	</ul>
					       	</div>
				       		<input type="file" class="form-control" name="homeImg" id="homeImg" multiple="multiple">
					       	<div class="row resultImg">
					       		<%-- <c:forEach items="${home.homeImgList}" var="imgFile" >
					       			<div class="img-div col-sm-3" data-path="${imgFile.homeImgPath}" data-imgname="${imgFile.homeImgName}">
					       				<div class="imgDelete" data-file="${imgFile.homeImgPath}">
					       				<i class="bi bi-x-lg"></i></div>
					       				<img src="/home/manage/showHomeImg?homeImgName=/${imgFile.homeImgPath}/${imgFile.homeImgName}">
					       			</div>
					       		</c:forEach> --%>
					       	</div>
		                </td>
             		</tr>
            		</tbody>
				</table>
       		</div>
       		<div class="text-center mx-auto">
        		<button type="reset" class="btn btn-large mr-2" onclick="location.href='/home/manage/list'">취소</button>
 				<button type="submit" id="updateBtn" class="btn btn-large" >수정</button>
			</div>
    	</form>	
	</div>

	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> 
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a94d4863c9f7363e85ad81dac027db86&libraries=services,clusterer,drawing"></script>
	<script src="/js/modifyHome.js" ></script>
	<script>
	// 수정 페이지 시작 -> 기존에 사용자가 첨부한 사진 파일 목록 추가, 선택된 옵션 값들 불러오기.
	let homeNum = ${home.homeNum};
	
	
	$(document).ready(function() {	
		$("input:radio[name=homeType]").each(function () {
			if(this.value ==  "${home.homeType}")
				this.checked = true;
		});		
		
		$("input:radio[name=rentType]").each(function () {
			if(this.value ==  "${home.rentType}")
				this.checked = true;
		});	
		
		$("input:radio[name=balcony]").each(function () {
			if(this.value == "${home.balcony}")
				this.checked = true;
		});		
		
		$("input:radio[name=elevator]").each(function () {
			if(this.value ==  "${home.elevator}")
				this.checked = true;
		});		
		
		$("input:radio[name=pet]").each(function () {
			if(this.value ==  "${home.pet}")
				this.checked = true;
		});	
		
		// 정규식을 이용해 받아온 옵션값 [] 제거 후 배열로 변경
		let optionList = "${home.optionList}".replace(/[[\]]/g, "").split(", ");	
		console.log(optionList);
		
		for(let i=0; i<optionList.length; i++) {
			$("input:checkbox[name=optionList]").each(function(index) {
				console.log(optionList[i]);
				if(this.value == optionList[i])
					this.checked = true; 
			});
		
		}
		
		let homeImgDiv = $( ".resultImg");
		let imgStr = "";
		
		imgStr += "<c:forEach items='${home.homeImgList}' var='imgFile'>"
		imgStr += "<div class='img-div col-sm-3' data-path='${imgFile.homeImgPath}' data-imgname='${imgFile.homeImgName}'>";
		imgStr  += "<div class='imgDelete' data-file='${imgFile.homeImgPath}'><i class='bi bi-x-lg'></i></div>";
		imgStr  += "<img src='/home/manage/showHomeImg?homeImgName=${imgFile.homeImgPath}/t_${imgFile.homeImgName}'>";
		imgStr  += "</div></c:forEach>";
		
		homeImgDiv.append(imgStr);
		
	});
	
   </script>
   
</body>
</html>