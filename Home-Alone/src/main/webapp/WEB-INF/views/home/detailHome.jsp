<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
	integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
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
								<img
									src="/home/getHomeImg?homeImgFile=${imgFile.homeImgPath}/${imgFile.homeImgName}"
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
						    	주소:  ${home.lessorAddr }</div>
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
						    	<img src="/icon/question.png" name="qnaBtn" id="qnaBtn"> <label for="qnaBtn">문의 남기기</label> 
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
								<td width="30%">${home.rentType}</td>
								<c:choose>
									<c:when test="${home.rentType == '월세'}">
										<td>${home.deposit} / ${home.monthly}</td>
									</c:when>
									<c:otherwise>
										<td>${home.deposit}원</td>
									</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<td>관리비</td>
								<td>${home.adminCost}원</td>
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
								<td><fmt:formatDate value="${home.moveDate}"
										pattern="yyyy-MM-dd" /></td>
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
								<td>${home.elevator }</td>
							</tr>
							<tr>
								<td>발코니 / 베란다</td>
								<td>${home.balcony }</td>
							</tr>
							<tr>
								<td>반려 동물</td>
								<td>${home.pet }</td>
							</tr>
							<tr>
								<td>층</td>
								<td><c:choose>
										<c:when test="${home.floor > 0 }">
											 ${home.floor}층
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


				<div class="location ms-2 mt-5 mb-2">
					<h3>
						<b>위치 정보</b>
					</h3>
					<div id="map" style="width: 100%; height: 350px"></div>
				</div>

			</div>
		</div>

		<!--  문의하기 모달 --> 
		<div class="qna-modal">
			<div class="qna-wrap">
			<form action="/qna/insert" method="post">
				<div class="qna-title">문의 하기</div>
				<div class="qna-close"><i class='bi bi-x-lg'></i></div>
				<div class="qna-content">
					<table class="table">
						<tr>
							<td style="color: white;"> 이름 </td>
							<td> <input type="text" class="form-control" name="imchaId" id="imchaId" value="${imcha.imchaId }" readonly></td>
						</tr>
						<tr>
							<td style="color: white;"> 문의 제목 </td>
							<td> <input type="text" class="form-control" name="iqTitle" id="iqTitle"></td>
						</tr>
						<tr>
							<td style="color: white;"> 문의 내용</td>
							<td> <textarea class="form-control" style="min-height:200px; resize: none;" id="iqContent" name="iqContent"></textarea></td>
						</tr>
					</table>
				</div>
				<div class="d-grid gap-3 mx-auto">
					
					<button type="submit" class="btn btn-md btn-success" id="qnaBtn">문의하기</button>
				</div>
			</form>
			</div>
		</div>
		
	</div>

	<jsp:include page="reportHome.jsp"></jsp:include>
	<!-- 신고하기 모달 -->

	<form action="reservation/enroll" method="POST">

		<div class="reserv-modal">
			<div class="reserv-wrap">
				<div class="reserv-title">집 방문 예약</div>
				<div class="reserv-close">
					<i class='bi bi-x-lg'></i>
				</div>
				<div class="reserv-content">

					<table class="table">
						<tr>
							<td style="color: white;">이름</td>
							<td><input type="text" class="form-control" id="imchaId"
								name="imchaId" value="${imcha.imchaId }" readonly></td>
						</tr>
						<tr>
							<td style="color: white;">방문 날짜</td>
							<td><input type="date" class="form-control" id="revDate"
								name="revDate"></td>
						</tr>

					</table>
					<table class="timeTable">
						<tr class="timeSelect">
							<td style="color: white; padding-right: 25px;">방문 시간</td>
							<td><input type="radio" id="revTime" name="revTime"
								value="10:00"><label for="revTime">10:00</label></td>
							<td><input type="radio" id="revTime2" name="revTime"
								value="11:00"><label for="revTime2">11:00</label></td>
							<td><input type="radio" id="revTime3" name="revTime"
								value="12:00"><label for="revTime3">12:00</label></td>
						</tr>
						<tr class="timeSelect">
							<td style="color: white;"></td>
							<td><input type="radio" id="revTime4" name="revTime"
								value="13:00"><label for="revTime4">13:00</label></td>
							<td><input type="radio" id="revTime5" name="revTime"
								value="14:00"><label for="revTime5">14:00</label></td>
							<td><input type="radio" id="revTime6" name="revTime"
								value="15:00"><label for="revTime6">15:00</label></td>
						</tr>
						<tr class="timeSelect">
							<td style="color: white;"></td>
							<td><input type="radio" id="revTime7" name="revTime"
								value="16:00"><label for="revTime7">16:00</label></td>
							<td><input type="radio" id="revTime8" name="revTime"
								value="17:00"><label for="revTime8">17:00</label></td>
							<td><input type="radio" id="revTime9" name="revTime"
								value="18:00"><label for="revTime9">18:00</label></td>
						</tr>
					</table>
				</div>
				<div class="d-grid gap-3 mx-auto">
					<input type="hidden" id="lessorId" name="lessorId"
						value="${home.lessorId}"> <input type="hidden"
						id="homeNum" name="homeNum" value="${home.homeNum}">
					<button type="submit" class="btn btn-md btn-success" id="reservBtn">예약하기</button>
				</div>

			</div>
		</div>
	</form>




	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a94d4863c9f7363e85ad81dac027db86"></script>
	<script>
		let latitude = ${home.latitude};
		let longitude = ${home.longitude};
		let homeNum = ${home.homeNum};
		let imchaId = $('#imchaId').val();
		let lessorId = "${home.jgsName}";
		let enrollName = $('#enrollName').val();
		let enrollDate = $('#enrollDate').val();
		let homeLike = ${homeLike};
		let revTime = $("input[name='revTime']:checked").val();
	</script>
	<script src="/js/reportHome.js"></script>
	<script src="/js/detailHome.js"></script>
	<script src="/js/reservHome.js"></script>
	<script>
		$(document).ready(function() {
			
			console.log(homeLike);
			if(homeLike == 1) {
				$("#likeBtn").prop("src", "/icon/heart-fill.png");
			}else{
				$("#likeBtn").prop("src", "/icon/heart.png");
				
			}
			
			let imchaId = $('#imchaId').val();
			let lessorId = "${home.jgsName}";
			let enrollName = $('#enrollName').val();
			let enrollDate = $('#enrollDate').val();
			let revTime = $("input[name='revTime']:checked").val();
			$("#likeBtn").on("click", function(e){ 
				$.ajax({
					type : 'get',
					url : '/like/clickLike?homeNum=' + homeNum,
					success : function (data) {
						if (data > 0) {
							$("#likeBtn").prop("src", "/icon/heart-fill.png");
						} else {
							$("#likeBtn").prop("src", "/icon/heart.png");
							
						}
							
					}
				});
			});
			 /* $("#reservBtn").on("click", function(e){
				console.log($('#enrollDate').val());
				$.ajax({
				                    url: 'reservation/enroll', 
				                    data: { 
				                    	lessorId: lessorId,
				                    	imchaId : $('#enrollName').val()
				                    	homeNum : homeNum,
				                    	revDate : enrollDate + ' ' + revTime
				                        }, 
				                    type: 'POST',
				                    success: function (param) { 
				                       alert('예약이 완료되었습니다.');
				                    },
				                    error: function () {
				                        alert('네트워크 오류 발생');
				                    }
				                });  //end of ajax 
			});  */
		});
	</script>
</body>
</html>