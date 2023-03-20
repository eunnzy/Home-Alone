<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" 
    rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link href="./bootstrap.min.css" rel="stylesheet"></link>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>찜목록</title>
</head>
<body>
	<header>
		<jsp:include page="../header.jsp"></jsp:include>
	</header>


<!-- body -->
  <h2 style="text-align: center; font-weight: bold;" class="my-5">찜 목록</h2>
  <div class="container">
    <div class="row">
    	<c:forEach items = "${likeList }" var="list">
	        <div class="col-6 ">
	          <div class="card list-group bg-light mt-5 mb-3 mx-3" style="min-width: 40rem; min-height: 15rem;">
	          <a href="/home/detail?homeNum=${list.homeNum}" class="list-group-item list-group-item-action flex-column align-items-start active">
	            <div class="card-header">매물번호 : ${list.homeNum }</div>
	            <div class="card-body">
	              <h5 class="card-title">위치 : ${list.addr1 } ${list.addr2 } ${list.addr3 }</h5>
	                <div class="d-flex w-100 justify-content-between">
	                  <h5 class="mb-1">${list.rentType} ${list.deposit }/ ${list.monthly }</h5>
	                </div>
	                <p class="mb-1"></p>
	                <small class="text-muted">계약기간 : ${list.rentPeriods } </small>
	             </div>
	          </a>
	         </div>
	        </div>
	     </c:forEach>
	  </div>
	</div>
  
  
</body>
</html>