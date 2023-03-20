<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
  <link href="/css/bootstrap.min.css" rel="stylesheet"></link>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="bs-docs-section row">
			<div class="col-lg-12">
				<br><br>
				<h1 id="tables" style="text-align: center;">신고매물 목록</h1>
				<br><br>
				
				<div class="bs-component">
					<table class="table table-hover" style="text-align: center">
					<tr>
						<th scope="col" style="width: 5%;">신고 번호</th> 
						<th scope="col" style="width: 5%;">매물 번호</th> 
						<th scope="col" style="width: 10%;">신고자</th>
						<th scope="col" style="width: 25%;">신고 유형</th> 
						<th scope="col" style="width: 30%;">신고 내용</th> 
						<th scope="col" style="width: 10%;">신고 날짜</th>
						<th scope="col" style="width: 15%;">신고처리</th>
						</tr>
						<c:forEach var="list" items="${list }">
						<tr>
					 		<td>${list.reportNum }</td>
							<td> <a href="/home/detail?homeNum=${list.homeNum }">${list.homeNum }</a></td>
							<td>${list.imchaId }</td>
							<c:choose>
								<c:when test="${ list.reportType == 1}">
									<td>등록된정보가 일치하지 않음 </td>
								</c:when>
								<c:when test="${ list.reportType == 2}">
									<td>이미 계약이 완료된 매물임 </td>
								</c:when>
								<c:otherwise>
									<td>기타</td>
								</c:otherwise>
							</c:choose>
							<td>${list.reportContent }</td>
							<td><fmt:formatDate  value="${list.reportRegDate }" pattern="yyyy-MM-dd" /></td> 
							<c:choose>
								<c:when test="${list.homeValid == 1 }">
									<td>
										<button id="${list.homeNum }" type="button" value="신고처리" class="success" style="background-color: #ff4500;">신고처리</button>
									</td>
								</c:when>
								<c:when test="${list.homeValid == 0 }">
									<td>
										신고처리완료
									</td>
								</c:when>
							</c:choose>
						</c:forEach>
				</table>
				</div>
			</div>
		</div>
	</div>
	<script>
	$(function(){
		$('.success').on('click', function() { 
			  
			  //현재 row의 정보 가져오기 
			  var thisRow = $(this).closest('tr'); 
			  
			  //주소 input 값 가져오기
			  var successNum = thisRow.find('td:eq(1)').text();
			  
			 
			  console.log(successNum);
			  
		$.ajax({
			type : 'post',
			url : "/admin/successNum",
			data : {
				homeNum : successNum,
			},
			success : function(data){
				if(data == 1) {
					console.log(data)
					alert(successNum + "번 매물, 신고처리를 하였습니다.");
					thisRow.find('td:eq(6)').html("신고처리 완료")
				} else {
					alert("실패하였습니다.")
				}
			}
		});
		});
		});
	</script>
</body>
</html>