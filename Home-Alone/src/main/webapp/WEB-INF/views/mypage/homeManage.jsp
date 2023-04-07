<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="./bootstrap.min.css" rel="stylesheet"></link>
    <title>매물 관리</title>
    <link href="/css/homeManage.css"  type="text/css" rel="stylesheet" >
	<style>
		.home-img img{
			height: 100%;
			object-fit:cover;
		}
		.home-card{
			cursor: pointer;
		}
    </style>
</head>
<body style="height: 100; width: 100;">
  <header>
		<jsp:include page="../header.jsp"></jsp:include>
  </header>

  <!-- body -->
	<div>
		<div style="bottom: 30px; width: 78rem; margin-left: auto; margin-right: auto;">
	  	
	  	<h2 style="text-align: center; font-weight: bold; padding-left:85px" class="my-5">매물 관리 
	  	<button type="button" class="btn btn-secondary mr-2 my-2" style="float: right;" onclick = "location.href = '/home/manage/register' ">매물 등록</button></h2>
	  </div>
	</div>
	
  <div class="container">
    <div class="row">
	    <c:forEach items="${homeList}" var="homeList">
	      <div class="col-6">
	        <div class="home-card card bg-light mt-5 mx-2 py-2" onclick="detailHome(${homeList.homeNum })">
	          <h4 class="card-header text-truncate">
	          		 <c:choose>
							<c:when test="${ homeList.homeValid == 1}">
								<span class="badge bg-success"> 게시중 </span>
							</c:when>
							<c:otherwise>
								<span class="badge bg-info"></span>
							</c:otherwise>
					</c:choose>
	          		${homeList.homeTitle}
	          
	          </h4>
	          <div class="card-body ">
		          <div class="row">
			          <div class="col-5 home-img">
			          		<img src="/home/getHomeImg?homeImgFile=${homeList.homeImg.homeImgPath}/${homeList.homeImg.homeImgName}" class="img-fluid">
			          </div>
			          <div class="col-7">
			          	 <h4>${homeList.rentType }</h4>
			          	 <c:choose>
							<c:when test="${homeList.rentType == '월세'}">
								<h5>보증금 : ${homeList.deposit } / 월세 : ${homeList.monthly} </h5>
							</c:when>
							<c:otherwise>
								<h5>보증금 : ${homeList.deposit } </h5>
							</c:otherwise>
						</c:choose>
			            <h6>${homeList.homeType }</h6>
			           
						<p class="text-truncate">${homeList.addr2} ${homeList.addr3}</p>
						<br>
			            <a href="/home/manage/modify?homeNum=${homeList.homeNum}" class="card-link">수정</a>
			            <a href="/home/manage/deleteHome?homeNum=${homeList.homeNum}&lessorId=${homeList.lessorId}" class="card-link">삭제</a>
			          </div>
		          </div>
	          </div>
	        </div>
	      </div>
		</c:forEach>
    </div>
  </div>

  <script type="text/javascript">
  	function detailHome(homeNum) {
  		location.href = "/home/detail?homeNum=" + homeNum;
  	}
  </script>
  
</body>
</html>