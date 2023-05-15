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
    <link href="./bootstrap.min.css" rel="stylesheet"></link>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <title>매물 관리</title>
    <link href="/css/homeManage.css"  type="text/css" rel="stylesheet" >
	<style>
		.home-img img{
			width:100%;
			height: 13rem;
			object-fit:cover;
			cursor: pointer;
		}
    </style>
</head>
<body style="height: 100; width: 100;">
  <header>
		<jsp:include page="../header.jsp"></jsp:include>
  </header>

  <div class="container">
		<div class="mb-3 mt-5 mx-auto">
	  		<h2 class="text-center" class="my-5"> <b>매물 관리</b> </h2>
		  	<div class="row">
		  		<div class="col">
			  	<button type="button" class="btn btn-secondary float-end" onclick = "location.href = '/home/manage/register' ">매물 등록</button>
		  		</div>
			</div>
	  	</div>
    <div class="row">
	    <c:forEach items="${homeList}" var="homeList">
	      <div class="col-6">
	        <div class="home-card card bg-light mt-3" >
	          <h4 class="card-header text-truncate">
	          		 <c:choose>
							<c:when test="${ homeList.homeValid == 0}">
								<span class="badge bg-info">게시중단</span>
							</c:when>
							<c:when test="${ homeList.homeValid == 1}">
								<span class="badge bg-success"> 게시중 </span>
							</c:when>
							<c:otherwise>
								<span class="badge bg-danger">신고매물</span>
							</c:otherwise>
					</c:choose>
	          		${homeList.homeTitle}
	          
	          </h4>
	          <div class="card-body ">
		          <div class="row">
			          <div class="col-5 home-img">
			          		<img src="/home/getHomeImg?homeImgFile=${fn:replace(homeList.homeImg.homeImgPath, '\\', '//')}/${homeList.homeImg.homeImgUuid}_${homeList.homeImg.homeImgName}"  class="img-fluid" onclick="detailHome(${homeList.homeNum })">
			          </div>
			          <div class="col-7">
			          	 <h4>${homeList.rentType }</h4>
			          	 <c:choose>
							<c:when test="${homeList.rentType == '월세'}">
								<h5>보증금 : ${homeList.depositUnit } / ${homeList.monthlyUnit} </h5>
							</c:when>
							<c:otherwise>
								<h5>보증금 : ${homeList.depositUnit } </h5>
							</c:otherwise>
						</c:choose>
			            <h6>${homeList.homeType }</h6>
			           
						<p class="text-truncate">${homeList.addr2} ${homeList.addr3}</p>
						<br>
			      
			           <c:choose>
							<c:when test="${homeList.homeValid == 0}">	<!-- 게시중단 상태면 -->
								  <button class="btn btn-primary" onclick="btnClick('valid', ${homeList.homeNum})"> 게시하기 </button>
								  <button class="btn btn-primary" onclick="btnClick('modify', ${homeList.homeNum})">수정</button>
			          			  <button class="btn btn-primary" onclick="btnClick('delete', ${homeList.homeNum})">삭제</button>	
							</c:when>
							<c:when test="${homeList.homeValid == 1}">	<!-- 게시중 이라면 -->
								  <button class="btn btn-primary" onclick="btnClick('invalid', ${homeList.homeNum})"> 게시중단 </button>
								  <button class="btn btn-primary" onclick="btnClick('modify', ${homeList.homeNum})">수정</button>
			          			  <button class="btn btn-primary" onclick="btnClick('delete', ${homeList.homeNum})">삭제</button>
							</c:when>
							<c:otherwise>	<!-- 게시중단 상태라면 -->
			          			<button class="btn btn-primary" onclick="btnClick('delete', ${homeList.homeNum})">삭제</button>	
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
  
  <form id="form" action="/home/manage/changeStatus" >
  	<input type="hidden" name="change" id="change">
  	<input type="hidden" name="homeNum" id="homeNum">
  </form>
  

  <script type="text/javascript">

  function detailHome(homeNum) {
  		location.href = "/home/detail?homeNum=" + homeNum;
  	}
  	
  	function btnClick(change, homeNum) {
  		switch(change) {
  	
  		case "modify" :
  			location.href = "/home/manage/modify?homeNum=" + homeNum;
  			break;
  		case "delete" :
  			if(confirm("삭제하시겠습니까?")) {
  				formSubmit(change, homeNum);
  			}else {
  				return false;
  			}
  			break;
  		case "valid":
  			formSubmit(change, homeNum);
  			break;
  		case "invalid":
  			formSubmit(change, homeNum);
  			break;
  		}
  		
  	}
  	
  	function formSubmit(change, homeNum) {
  		let	e = window.event;
  		e.preventDefault();
  		
  		let formData = $("#form");
  		
  		$("#change").attr("value", change);
  		$("#homeNum").attr("value", homeNum);
  		
  		console.log($("#change").val());
  		console.log($("#homeNum").val());
  		formData.attr("action", "/home/manage/changeStatus");
  		formData.attr("method", "post");
  		
  		formData.submit();
  	}
  </script>
  
</body>
</html>