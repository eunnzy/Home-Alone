<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 확인</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
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

	<!-- body -->
	<h2 class="text-center my-5"><b>예약 목록</b></h2>
	<div class="container">
		<div class="row">
			<c:forEach items="${reservList}" var="reservList">
				<div class="col-6 mb-3">
					<div class="card bg-light">
						<div class="card-body">
							<div class="row">
								<div class="col-5 home-img py-1">
									<a href="/home/detail?homeNum=${reservList.homeNum} ">
										<img src="/home/getHomeImg?homeImgFile=${fn:replace(reservList.homeImg.homeImgPath, '\\', '//')}/${reservList.homeImg.homeImgUuid}_${reservList.homeImg.homeImgName}"  class="img-fluid">
									</a>
								</div>
								<div class="col-7 py-1">
									<h5 >방문 날짜 :<fmt:formatDate value="${reservList.revDate}" pattern="yyyy-MM-dd" /></h5>
									<h5> 방문 시간 ${reservList.revTime }</h5>
									<h5 >신청인 : ${reservList.imchaId}</h5>
									<c:choose>
						    			<c:when test="${reservList.revState eq  0}">
						    				<h6>예약 상태: 대기중 </h6>
						    				<button class="btn btn-secondary" type="button" onclick="changeReservStatus('accept', ${reservList.revNum})">확정</button>
											<button class="btn btn-secondary" type="button" onclick="changeReservStatus('reject', ${reservList.revNum})">거절</button>
										</c:when>	   
										<c:when test="${reservList.revState eq  1}">
											<h6>예약 상태: 예약 확정 </h6>
											<button class="btn btn-secondary" type="button" onclick="changeReservStatus('cancel', ${reservList.revNum})">취소</button>
										</c:when>
										<c:otherwise>
											<h6>예약 상태: 예약 취소 </h6>
											<button class="btn btn-secondary" type="button" onclick="changeReservStatus('delete', ${reservList.revNum})">삭제</button>
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
	
	<form id="form">
	  	<input type="hidden" name="change" id="change">
	  	<input type="hidden" name="revNum" id="revNum">
 	 </form>
	
	<script type="text/javascript">
	function changeReservStatus(change, revNum) {
		let	e = window.event;
  		e.preventDefault();
  		
  		let formData = $("#form");
  		
  		$("#change").attr("value", change);
  		$("#revNum").attr("value", revNum);
  		
  		console.log($("#change").val());
  		console.log($("#revNum").val());
  		formData.attr("action", "/home/reserv/lessor/changeStatus");
  		formData.attr("method", "post");
  		
  		formData.submit();
	}
	
	</script>
	
	
</body>
</html>