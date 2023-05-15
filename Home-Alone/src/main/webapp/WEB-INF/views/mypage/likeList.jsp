<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>찜목록</title>
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
		<jsp:include page="../header.jsp"></jsp:include>
	</header>

	<div class="container">
		<h2 class="text-center my-5"><b>찜 목록</b></h2>
		<div class="row">
			<c:forEach items="${likeList}" var="list">
				<div class="col-6 mb-3">
					<div class="card bg-light">
						<div class="card-body">
							 <div class="text-truncate"> 
							 	<h4 class="card-title"> 
							 		<c:choose>
										<c:when test="${list.rentType == '월세'}">
											<span class="badge bg-success">${list.rentType}</span>
										</c:when>
										<c:otherwise>
											<span class="badge bg-info">${list.rentType}</span>
										</c:otherwise>
									</c:choose>
							 	${list.homeTitle } </h4>
							 	<hr>
							 </div>
							<div class="row">
								<div class="col-5 home-img">
									<a href="/home/detail?homeNum=${list.homeNum} ">
										<img src="/home/getHomeImg?homeImgFile=${fn:replace(list.homeImg.homeImgPath, '\\', '//')}/${list.homeImg.homeImgUuid}_${list.homeImg.homeImgName}"  class="img-fluid">
									</a>
								</div>
								<div class="col-7 py-2">
									<h4>${list.homeType }</h4>
									<c:choose>
										<c:when test="${list.rentType == '월세'}">
											<h5> 보증금: ${list.depositUnit } / ${list.monthlyUnit }</h5>
										</c:when>
										<c:otherwise>
											<h5>보증금: ${list.depositUnit }</h5>
										</c:otherwise>
									</c:choose>
									<h6>위치 :  ${list.addr2 } ${list.addr3 }</h6>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	
	<script type="text/javascript">
	
	</script>
	
</body>
</html>