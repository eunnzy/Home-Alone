<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>매물 검색</title>
	<link href="/css/searchHome.css?v2"  type="text/css" rel="stylesheet" >
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	<link rel="fa-solid fa-magnifying-glas" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
</head>
<body>
	 <header>
		<jsp:include page="../header.jsp"></jsp:include>
	</header>

	<div class="wrapper">
	<div class="map-container">
		<div class="map_wrap">
			<div id="map"></div>	<!-- 지도 -->
			<!-- <ul id="category" style="padding-left:0px;">
		        <li id="BK9" data-order="0"> 
		            <span class="category_bg bank"></span>
		            은행
		        </li>       
		        <li id="MT1" data-order="1"> 
		            <span class="category_bg mart"></span>
		            마트
		        </li>  
		        <li id="PM9" data-order="2"> 
		            <span class="category_bg pharmacy"></span>
		            약국
		        </li>  
		        <li id="OL7" data-order="3"> 
		            <span class="category_bg oil"></span>
		            주유소
		        </li>  
		        <li id="CE7" data-order="4"> 
		            <span class="category_bg cafe"></span>
		            카페
		        </li>  
		        <li id="CS2" data-order="5"> 
		            <span class="category_bg store"></span>
		            편의점
		        </li> 
		        <li id="CS2" data-order="5"> 
		            <span class="category_bg store"></span>
		            편의점
		        </li>      
		    </ul> -->
		</div>
		<div class="search-div">
			<div class="search-group-wrap">
				<div class="search-bar-wrap">		
					<div class="search-bar">
						<button type="button" class="filterBtn" id="filterBtn"><i class="fa-solid fa-sliders"></i></button>
						<input type="text" id="searchInput" placeholder="지역, 학교, 지하철역으로 검색" > 
						<button type="button" id="searchBtn"><i class="fa-solid fa-magnifying-glass"></i></button>
	       			</div>
       		   </div> 
       		   
       			<div class="filter-content">
       				<div>
       					<h4>매물 종류</h4>
       					<p>중복 선택 가능</p>
       				</div>
     				<div class="home-type mb-4">
     					<div class="form-check form-check-inline">
                           	<input type="checkbox" class="form-check-input" autocomplete="off" name="homeType" value="원룸">
							<label class="form-check-label">원룸</label>
						</div>
                        <div class="form-check form-check-inline">
                        	<input type="checkbox" class="form-check-input"  autocomplete="off" name="homeType" value="투룸">
 							<label class="form-check-label">투룸</label>
                        </div>
                        <div class="form-check form-check-inline">
                           	<input type="checkbox" class="form-check-input" autocomplete="off" name="homeType" value="쓰리룸이상">
			 				<label class="form-check-label" >쓰리룸이상 </label>
                        </div>
                        <div class="form-check form-check-inline">
                         	<input type="checkbox" class="form-check-input" autocomplete="off" name="homeType" value="오피스텔">
	 						<label class="form-check-label" >오피스텔</label>
                        </div>
                        <div class="form-check form-check-inline">
                             	<input type="checkbox" class="form-check-input" autocomplete="off" name="homeType" value="빌라">
					 			<label class="form-check-label" >빌라 </label>
                        </div>
                        <div class="form-check form-check-inline">
                             	<input type="checkbox" class="form-check-input" autocomplete="off" name="homeType" value="쉐어하우스">
					 			<label class="form-check-label" >쉐어하우스 </label>
                        </div>
       				</div>
       				
       				<hr>
       				
       				<div>
       					<h4>거래 유형</h4>
       					<p>중복 선택 가능</p>
       				</div>
     				<div class="rent-Type mb-4">
     					<div class="form-check form-check-inline">
                           	<input type="checkbox" class="form-check-input" autocomplete="off" name="rentType" value="월세">
							<label class="form-check-label">월세</label>
						</div>
                        <div class="form-check form-check-inline">
                        	<input type="checkbox" class="form-check-input"  autocomplete="off" name="rentType" value="전세">
 							<label class="form-check-label">전세</label>
                        </div>
       				</div>
       				<div class="range-div mt-3 mb-4">
				        <label for="customRange1" class="form-label d-grid">보증금</label>
				        <input type="range" id="deposit" min="0" max="3" value="3">
				        <ul class="deposit-range-ul">
				        	<li> 0 </li>
				        	<li> 5000만</li>
				        	<li> 1억 </li>
				        	<li> 최대 </li>
				        </ul>
				        <label for="customRange3" class="form-label d-grid">월세</label>
				        <input type="range"id="monthly" min="0" max="3" value="3"> 
				        <ul class="monthly-range-ul">
				        	<li> 0 </li>
				        	<li> 50만</li>
				        	<li> 100만 </li>
				        	<li> 최대 </li>
				        </ul>
					</div>
					
					<hr>
					
       				<div>
       					<h4>옵션 선택</h4>
       					<p>중복 선택 가능</p>
       				</div>
     				<div class="option-list mb-4">
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
                        <div class="form-check form-check-inline mb-3">
                          	<input type="checkbox" class="form-check-input"  autocomplete="off" name="optionList" value="비데">
						 	<label  class="form-check-label">비데</label>
                        </div>
       				</div>
       				
					<hr>
					
       				<div>
       					<h4>추가 정보</h4>
       					<p>중복 선택 가능</p>
       				</div>
     				<div class="addInfo-list mb-3">
     					<div class="form-check form-check-inline">
                           	<input type="checkbox" class="form-check-input" autocomplete="off" name="addInfoList" value="반려동물">
							<label class="form-check-label">반려동물</label>
						</div>
                        <div class="form-check form-check-inline">
                        	<input type="checkbox" class="form-check-input"  autocomplete="off" name="addInfoList" value="엘리베이터">
 							<label class="form-check-label">엘리베이터</label>
                        </div>
                        <div class="form-check form-check-inline">
                        	<input type="checkbox" class="form-check-input"  autocomplete="off" name="addInfoList" value="주차">
 							<label class="form-check-label">주차 가능</label>
                        </div>
                        <div class="form-check form-check-inline mb-3">
                        	<input type="checkbox" class="form-check-input"  autocomplete="off" name="addInfoList" value="발코니">
 							<label class="form-check-label">발코니/베란다</label>
                        </div>
       				</div>
       				
       				<div class="d-flex justify-content-center mt-4">
       					<button class="btn btn-outline-info m-2" type="button" id="cancelBtn">초기화</button>
  						<button class="btn btn-outline-info m-2" type="button" id="filterApplyBtn">적용하기</button>
       				</div>
				</div>
				
				<!-- 메믈 리스트 -->
				<div class="home-wrap">
                    <div class="home-list">
					</div>
				</div>
		</div>
	</div>
		
	<script>
		var search = encodeURIComponent("${searchKeyword}");
		/* var searchKeyword = "${searchKeyword}"; */
		console.log(searchKeyword);
	</script>
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a94d4863c9f7363e85ad81dac027db86&libraries=services,clusterer,drawing"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a94d4863c9f7363e85ad81dac027db86"></script>
	
	<script src="/js/searchHome.js" ></script>
	<script src="/js/searchFilter.js" ></script>
	
</script>
</body>
</html>