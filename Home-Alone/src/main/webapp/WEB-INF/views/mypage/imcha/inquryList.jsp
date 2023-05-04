<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	pageContext.setAttribute("br", "<br/>");
	pageContext.setAttribute("cn", "\n");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/css/bootstrap.min.css" rel="stylesheet"></link>
<title>문의 목록</title>
</head>
<body>
	<header>
		<jsp:include page="../../header.jsp"></jsp:include>
	</header>

	<div class="cotainer">
  		<div class="col-lg-10 mx-auto">
			<h1 class="text-center mt-5 mb-4">1대1 문의</h1>
			<div class="bs-component mt-2">
		  		<table class="table table-hover table-border" style="max-width: 78rem; margin-left: auto; margin-right: auto; text-align: center;">
				    <thead>
				      <tr>
				       <!--  <th scope="col">작성자</th> -->
				        <th scope="col" style="width: 60%;" >제목</th>
				        <th scope="col" style="width: 20%;">작성 일자</th>
				        <th scope="col" style="width: 20%;">답변 상태</th>
				      </tr>
				    </thead>
					  <tbody>
					    <c:forEach items="${inqList }" var="inqList">
					    <%-- <a href="qna/getBpard?iqNum=${qnaList.iqNum}"> --%>
					      <tr class="table-secondary table-row">
					       <%--  <th scope="row">${inqList.imchaId }</th> --%>
					        <td><a href="/inqury/detail?iqNum=${inqList.iqNum}">${inqList.iqTitle }</a></td>
					        <td><fmt:formatDate value="${inqList.iqDate}" pattern="yyyy-MM-dd" /></td>
							<c:choose>
								<c:when test="${ inqList.ansStatus eq 0 }">
									<td>답변 대기중</td>
								</c:when>
								<c:otherwise>
									<td> 답변 완료 </td>
								</c:otherwise>
							</c:choose>
					      </tr>
					    </c:forEach>
				      </tbody>
			    </table>
	    	</div>
		     <div class="page-div mx-auto">
		          <ul class="pagination pagination-sm bg-light">
		          	<c:if test="${pageMaker.prev}">
		          	  <li class="page-item">
		                <a class="page-link" href="${pageMaker.startPage -1}">&laquo;</a>
		              </li>
		          	</c:if>
		          	
		          	<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
		          		<li class="page-item ${pageMaker.cri.pageNum == num ? "active" : ""} " >
		          		<a class="page-link" href="${num}">${num}</a>
		          		</li>
		          	</c:forEach>
		          	
		          	<c:if test="${pageMaker.next}">
		          	  <li class="page-item">
		                <a class="page-link" href="${pageMaker.endPage +1 }">&raquo;</a>
		              </li>
		          	</c:if>
		          </ul>
	       	</div>
		        
		</div>
	</div>
	
	
</body>
</html>