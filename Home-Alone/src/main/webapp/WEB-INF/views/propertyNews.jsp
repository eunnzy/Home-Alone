<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<jsp:include page="header.jsp"></jsp:include>
	</header>
	
	<div class="container text-center">
		<table class="table">
			<tr>
				<td colspan="2"> <h1 class="text-center">실시간 부동산 소식</h1> </td>
			</tr>
			<c:forEach items="${newsList}" var="news">
				<tr>
					<td><a href="${news.url }"><img src="${news.imgUrl }"></a></td>
					<td class="text-center"><a href="${news.url }"><h5>${news.title }</h5></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
</body>
</html>