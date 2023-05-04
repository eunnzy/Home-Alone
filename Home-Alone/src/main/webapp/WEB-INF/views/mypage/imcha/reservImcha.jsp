<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 목록</title>
<style>
	.home-img a img{
		width:100%;
		height: 10rem;
		object-fit:cover;
		cursor: pointer;
	}
</style>
</head>
<body>
	<header>
		<jsp:include page="../../header.jsp"></jsp:include>
	</header>

	<h2 style="text-align: center; font-weight: bold;" class="my-5">예약 목록</h2>
	<div class="container">
		<div class="row">
			<c:forEach items="${reservList}" var="reservList">
				<div class="col-6">
					<div class="card bg-light">
						<div class="card-body">
							<div class="row">
								<div class="col-5 home-img">
									<a href="/home/detail?homeNum=${reservList.homeNum} ">
										<img src="/home/getHomeImg?homeImgFile=${fn:replace(reservList.homeImg.homeImgPath, '\\', '//')}/${reservList.homeImg.homeImgUuid}_${reservList.homeImg.homeImgName}"  class="img-fluid">
									</a>
								</div>
								<div class="col-7 py-2">
									<h5 >방문 날짜 :<fmt:formatDate value="${reservList.revDate}" pattern="yyyy-MM-dd" /></h5>
									<h5> 방문 시간 ${reservList.revTime }</h5>
									<c:choose>
						    			<c:when test="${reservList.revState ==  0}">
						    				<h6>예약 상태: 대기중 </h6>
										</c:when>
										<c:when test="${reservList.revState ==  1}">
												<h6>예약 상태: 예약 확정 </h6>
										</c:when>	   
										<c:otherwise>
												<h6>예약 상태: 예약 거절 </h6>
										</c:otherwise> 
						    		</c:choose>
						    		<button class="btn btn-secondary" type="button" onclick="location.href = '/home/reservation/reject?homeNum=${reservList.homeNum}">취소</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>


</body>
</html>