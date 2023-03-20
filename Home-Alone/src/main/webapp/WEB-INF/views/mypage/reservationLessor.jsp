<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 확인</title>
</head>
<body>
  <header>
		<jsp:include page="../header.jsp"></jsp:include>
  </header>

 <!-- body -->
  <h2 style="text-align: center; font-weight: bold;" class="my-5">예약 확인</h2>
  <div class="container">
    <div class="row">
    <c:forEach items="${lessorList}" var="lessorList">
      <div class="col-6" style="text-align:center;">
        <div class="card bg-light">
          <div class="card-body">
            <h4 class="card-title">방문 신청 일자 : ${lessorList.revDate}</h4>
            <h6 class="card-subtitle mb-2 text-muted">신청인 : ${lessorList.imchaId}</h6>
            <p><a href="/home/detail?homeNum=${lessorList.homeNum} " class="card-text">매물 번호 : ${lessorList.homeNum}</a></p>
            <p class="card-text">예약 상태 : ${lessorList.state}</p>
            <button class="btn btn-secondary mx-5" type="button" 
            onclick = "location.href = '/home/reservation/allow?homeNum=${lessorList.homeNum}&imchaId=${lessorList.imchaId}&revNum=${lessorList.revNum}'" >확정</button>
            <button class="btn btn-secondary mx-5" type="button" 
            onclick = "location.href = '/home/reservation/reject?homeNum=${lessorList.homeNum}&imchaId=${lessorList.imchaId}&revNum=${lessorList.revNum}'" >취소</button>
          </div>
        </div>
        <p class="mb-4"></p>
      </div>
	</c:forEach>
    </div>
  </div>
  
</body>
</html>