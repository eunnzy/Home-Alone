<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 확인</title>
</head>
<body>
	<header>
		<jsp:include page="../../header.jsp"></jsp:include>
	</header>

	<!-- body -->
	<h2 class="text-center my-5"><b>예약 목록</b></h2>
	<div class="container">
		<div class="row">
			<c:forEach items="${reservList}" var="reservList">
				<div class="col-6">
					<div class="card bg-light">
						<div class="card-body">
							<div class="row">
								<div class="col-5 home-img">
									<a href="/home/detail?homeNum=${reservList.homeNum} ">
										<img src="/home/getHomeImg?homeImgFile=${reservList.homeImg.homeImgPath}/${reservList.homeImg.homeImgName}" class="img-fluid">
									</a>
								</div>
								<div class="col-7">
									<h5 >방문 날짜 :<fmt:formatDate value="${reservList.revDate}" pattern="yyyy-MM-dd" /></h5>
									<h5> 방문 시간 ${reservList.revTime }</h5>
									<h5 >신청인 : ${reservList.imchaId}</h5>
									<c:choose>
						    			<c:when test="${reservList.revState ==  0}">
						    				<h6>예약 상태: 대기중 </h6>
						    				<button class="btn btn-secondary mx-5" type="button" onclick="location.href = '/home/reservation/allow?homeNum=${reservList.homeNum}">확정</button>
											<button class="btn btn-secondary mx-5" type="button" onclick="location.href = '/home/reservation/reject?homeNum=${reservList.homeNum}">취소</button>
										</c:when>	   
										<c:otherwise>
											<c:when test="${reservList.revState ==  1}">
												<h6>예약 상태: 예약 확정 </h6>
												<button class="btn btn-secondary mx-5" type="button" onclick="location.href = '/home/reservation/reject?homeNum=${reservList.homeNum}">취소</button>
											</c:when>
										</c:otherwise> 
						    		</c:choose>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>