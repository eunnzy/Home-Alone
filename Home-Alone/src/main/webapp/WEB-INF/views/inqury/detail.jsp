<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<link href="/css/bootstrap.min.css" rel="stylesheet"></link>
<style>
	.answer-div {
		box-shadow: 5px white;
		width: 100%;
		height: auto;
	}
</style>
</head>
<body>
	<header>
		<jsp:include page="../header.jsp"></jsp:include>
	</header>
	
	<div class="container mt-5 mb-4">
		<div class="col-10 px-5 py-5 bg-light mx-auto">
				<h4 class="text-center"> 문의 내용 </h4>
				<table class="table">
					<input type="hidden" id="iqNum" value="${inq.iqNum }">
					<tr>
						<td> 제목 </td>
						<td>${inq.iqTitle }</td>
					</tr>
					<tr>
						<td> 작성자 </td>
						<td> ${inq.imchaId} </td>
					</tr>
					<tr>
						<td> 문의 매물 </td>
						<td> <a href="/home/detail?homeNum=${inq.homeNum}">매물보기</a></td>
					</tr>
					<tr>
						<td> 등록 날짜 </td>
						<td> <fmt:formatDate value="${inq.iqDate}" pattern="yyyy-MM-dd" /> </td>
					</tr>
					<tr>
						<td> 문의 내용 </td>
						<td > ${inq.iqContent }</td>
					</tr>					
				</table>
			<hr>
			
			<div class="answer-div mb-3">
				<div class="mb-4">답변</div>
				<table class="table answer-list mb-3">
				</table>
	       </div>

	       <c:if test="${lessor != null }">
		       <hr>
			    <div> 답변 작성 </div>
					<div class="input-group mb-3 replyreg">
					   <textarea type="text" class="form-control" rows="6" id="ansContent" > </textarea>
					   <button class="btn btn-secondary" type="button" id="ansBtn">등록</button>
					</div>
				</div>
			</c:if>
	</div>
	
	<script type="text/javascript">
			let iqNum = ${inq.iqNum};
			console.log(iqNum);
		
		
		$("#ansBtn").click(function() {
			
			let homeInqAnswer = { "iqNum" : $("#iqNum").val(), "ansContent" : $("#ansContent").val() };
			
			$.ajax({
				url: "/inqury/answer/register",
				type: "POST",
				data: homeInqAnswer,
				success: function(data) {
					console.log(data);
				}
			});
			
		});
		
	</script>
	<script src="/js/inquryDetail.js" ></script>
	
	
	
</body>
</html>