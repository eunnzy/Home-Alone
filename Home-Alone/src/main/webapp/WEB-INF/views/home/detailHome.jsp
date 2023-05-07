<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%
	pageContext.setAttribute("br", "<br/>");
	pageContext.setAttribute("cn", "\n");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매물 상세 보기</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<link href="/css/bootstrap.min.css" rel="stylesheet"></link>
<link href="/css/detailHome.css" rel="stylesheet"></link>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<style>
</style>
</head>
<body>
	<header>
		<jsp:include page="../header.jsp"></jsp:include>
	</header>

	<div class="container mt-5">
		<div class="col-sm-10 mx-auto">
			<div class="row">
				<div class="col-sm-8 ">
					<div id="carouselExampleControlsNoTouching" class="carousel slide"
						data-bs-touch="false">
						<div class="carousel-inner homeImg-div">
							<c:forEach items="${home.homeImgList}" var="imgFile"
								varStatus="status">
								<c:choose>
									<c:when test="${status.first}">
										<div class="carousel-item active">
									</c:when>
									<c:otherwise>
										<div class="carousel-item">
									</c:otherwise>
								</c:choose>
								<img src="/home/getHomeImg?homeImgFile=${fn:replace(imgFile.homeImgPath, '\\', '//')}/${imgFile.homeImgUuid}_${imgFile.homeImgName}"
									class="d-block w-100" height="450" alt="...">
						</div>
						</c:forEach>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselExampleControlsNoTouching"
						data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselExampleControlsNoTouching"
						data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
			</div>
			<div class="col-sm-4 side-content">
					<div class="card bg-light">
						<div class="card-body">
					    	<h4 class="card-title">
					    		<c:choose>
					    			<c:when test="${home.rentType == '월세'}">
					    				<span class="badge bg-success">${home.rentType}</span>
										${home.deposit} / ${home.monthly}		
									</c:when>	   
									<c:otherwise>
										<span class="badge bg-info">${home.rentType}</span>
										${home.deposit}
									</c:otherwise> 
					    		</c:choose>
					    	</h4>
					    	<h6 class="card-subtitle mb-2 text-muted">${home.addr2 } ${home.addr3 } </h6>
					    	<hr>
					    	<div class="user-info mt-3">
				    		<div class="text-center">
					    		 ${home.jgsName} <br> 
					    		중개사 등록번호:  ${home.jgsNum} <br>
						    	 대표명 : ${home.jgsName }  <br>
						    	전화번호: ${home.phone }  <br>
						    	주소:  ${home.lessorAddr}</div>
				    		</div>
				    		<hr>
						   	<div class="col mb-3">
							    <!-- <a href="https://www.flaticon.com/kr/free-icons/" title=" 아이콘"> 아이콘  제작자: Freepik - Flaticon</a> -->
						    	<img src="/icon/siren.png" data-bs-toggle="modal" data-bs-target="#exampleModalLabel" name="sirenBtn" id="sirenBtn"> <label for="sirenBtn">신고하기</label> 
						   	</div>
						    <div class="like-div col mb-3">
							   	<!-- <a href="https://www.flaticon.com/kr/free-icons/" title="심장 아이콘">심장 아이콘  제작자: Freepik - Flaticon</a> -->
						    	<img src="/icon/heart.png" name="likeBtn" id="likeBtn"> <label for="likeBtn">찜하기</label>
						    </div>
							    <input type="hidden" name="homeNum" id="homeNum" value="${home.homeNum}">
							    <input type="hidden" name="imchaId" id="imchaId" value="${member.imchaId}">
								<div class="col mb-3">
							    <!-- <a href="https://www.flaticon.com/kr/free-icons/" title=" 아이콘"> 아이콘  제작자: Freepik - Flaticon</a>  -->
						    	<img src="/icon/question.png" id="inqModalBtn"> <label for="inqModalBtn">문의하기</label> 
							</div>
							
							<div class="d-grid gap-2 mx-auto mt-3">
								<button type="button" class="btn btn-md btn-success" id="reservModalBtn"> 예약하기 </button>
							</div>
							</div>
						</div>
					</div>
			<div class="col-sm-8 mt-4">
				<div class="basic-info">
					<table class="table">
						<thead>
							<tr>
								<th colspan="2">
									<h3>
										<b>기본 정보</b>
									</h3>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td> 거래 유형</td>
								<td> ${home.rentType }</td>
							</tr>
							<tr>
								<c:choose>
									<c:when test="${home.rentType == '월세'}">
										<td width="30%">보증금 / 월세  </td>
										<td>${home.deposit} / ${home.monthly}</td>
									</c:when>
									<c:otherwise>
										<td width="30%">보증금 </td>
										<td>${home.deposit}</td>
									</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<td> 임대 기간</td>
								<td> ${home.rentPeriods }${home.rentUnit }</td>
							</tr>
							<tr>
								<td>관리비</td>
								<td>${home.adminCost}</td>
							</tr>
							<tr>
								<td>주차</td>
								<c:choose>
									<c:when test="${home.parking > 0 }">
										<td>${home.parking}대 가능</td>
									</c:when>
									<c:otherwise>
										<td>이용 불가</td>
									</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<td>입주 가능일</td>
								<td><fmt:formatDate value="${home.moveDate}" pattern="yyyy-MM-dd" /></td>
							</tr>
						</tbody>
					</table>
				</div>

				<!-- 상세 정보  -->
				<div class="detail-info mt-3">
					<table class="table">
						<thead>
							<tr>
								<th colspan="2">
									<h3>
										<b>상세 정보</b>
									</h3>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td width="30%">방 종류</td>
								<td>${home.homeType }</td>
							</tr>
							<tr>
								<td>집 면적</td>
								<td>${home.homeArea } 평</td>
							</tr>
							<tr>
								<td>엘리베이터</td>
								<c:choose>
									<c:when test="${home.elevator == 'Y' }">
										<td>가능</td>
									</c:when>
									<c:otherwise>
										<td>불가능</td>
									</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<td>발코니 / 베란다</td>
								<c:choose>
									<c:when test="${home.balcony == 'Y' }">
										<td>가능</td>
									</c:when>
									<c:otherwise>
										<td>불가능</td>
									</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<td>반려 동물</td>
								<c:choose>
									<c:when test="${home.pet == 'Y' }">
										<td>가능</td>
									</c:when>
									<c:otherwise>
										<td>불가능</td>
									</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<td>층</td>
								<td><c:choose>
										<c:when test="${home.floor > 0 }">
											${home.totalFloor }층  중 ${home.floor}층
										</c:when>
										<c:otherwise>
											 지하 
										</c:otherwise>
									</c:choose></td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="home-detail mt-3 mb-4">
					<div class="home-title">${home.homeTitle}</div>

					<div class="home-content mt-3">${fn:replace(home.homeDetail, cn, br)}
					</div>
				</div>

				<div class="option ms-2 mt-3 mb-3">
					<h3>
						<b>기본 옵션</b>
					</h3>
					<div class="row option-list mt-3">
						<c:forEach items="${home.optionList}" var="option">
							<div class="ms-2 col-sm-2 option-icon-wrapper mb-2">
								<div class="option-icon">
									<img src="/icon/home/${option}.png">
								</div>
								<label> ${option} </label>
							</div>

						</c:forEach>
					</div>
				</div>


				<div class="location ms-2 mt-5 mb-4">
					<h3>
						<b>위치 정보</b>
					</h3>
					<div id="map" style="width: 100%; height: 350px"></div>
				</div>

			</div>
		</div>

		
	 <footer>
			<jsp:include page="../footer.jsp"></jsp:include>
 		</footer>	

		<jsp:include page="reportHome.jsp"></jsp:include>  <!-- 신고하기 모달 -->
	
	<jsp:include page="inquryHome.jsp"></jsp:include> <!--  문의하기 모달 --> 
	
	<jsp:include page="reservHome.jsp"></jsp:include><!-- 예약하기 모달 -->

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a94d4863c9f7363e85ad81dac027db86"></script>
	<script>
		var latitude = ${home.latitude};
		var longitude = ${home.longitude};
		var homeNum = ${home.homeNum};
		var imchaId = "${imcha.imchaId}";
		var enrollName = $('#enrollName').val();
		var enrollDate = $('#enrollDate').val();
		var lessorId = "${home.lessorId}";
		var homeLike = ${homeLike};
	</script>
	<script src="/js/home/reportHome.js"></script>
	<script src="/js/home/detailHome.js"></script>
	<script src="/js/home/reservHome.js"></script>
	<script src="/js/home/inquryHome.js"></script>

	
	<script>
		$(document).ready(function() {
			
			console.log(homeLike);
			if(homeLike == 1) {
				$("#likeBtn").prop("src", "/icon/heart-fill.png");
			}else{
				$("#likeBtn").prop("src", "/icon/heart.png");
				
			}
			let enrollName = $('#enrollName').val();
			let enrollDate = $('#enrollDate').val();
			let revTime = $("input[name='revTime']:checked").val();
			
			$("#likeBtn").on("click", function(e){ 
				if(imchaId == '') {
					alert("로그인을 해주세요!");
					return false;
				}
				
				$.ajax({
					type : 'get',
					url : '/home/like/click?homeNum=' + homeNum,
					success : function (data) {
						if (data > 0) {
							$("#likeBtn").prop("src", "/icon/heart-fill.png");
						} else {
							$("#likeBtn").prop("src", "/icon/heart.png");
							
						}
							
					}
				});
			});
		});
	</script>
</body>
</html>