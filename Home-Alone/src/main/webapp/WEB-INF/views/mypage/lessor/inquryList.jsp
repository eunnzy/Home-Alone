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

	<h2 style="text-align: center; font-weight: bold;" class="my-5">1대1 문의</h2>
  	<table class="table table-hover" style="max-width: 78rem; margin-left: auto; margin-right: auto; text-align: center;">
	    <thead>
	      <tr>
	        <th scope="col">작성자</th>
	        <th scope="col" colspan="2">제목</th>
	        <th scope="col">작성 일자</th>
	        <th scope="col">답변 상태</th>
	      </tr>
	    </thead>
	    
		  <tbody>
		    <c:forEach items="${inqList }" var="inqList">
		    <%-- <a href="qna/getBpard?iqNum=${qnaList.iqNum}"> --%>
		      <tr class="table-secondary">
		        <th scope="row">${inqList.imchaId }</th>
		        <td colspan="2"><a href="/inqury/detail?iqNum=${inqList.iqNum}">${inqList.iqTitle }</a></td>
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
	
</body>
</html>