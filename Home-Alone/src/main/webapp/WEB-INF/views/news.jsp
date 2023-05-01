<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<header>
		<jsp:include page="header.jsp"></jsp:include>
	</header>
	
	<div class="container">
		<div class="col-lg-9 mx-auto">
			<div class="mt-4 mb-3">
				<h2>오늘의 부동산 소식</h2>
			</div>
			<table class="table table-hover table-border">
				<c:if test="${not empty newsList }">
					<c:forEach items="${newsList}" var="news" varStatus="index">
						<tr class="table-row">
							<td><a href="${news.url }"><img src="${news.imgUrl }"></a></td>
							<td class="text-center">
								<a href="${news.url }"><h5>${news.title }</h5></a>
								<p> ${news.content } </p> 
							</td>
						</tr>
					</c:forEach>
			</table>
			<div class="page-div mx-auto mt-2 mb-4">
	          <ul class="pagination pagination-sm bg-light">
	          	<c:if test="${currPage > 1}">
	          		 <li class="page-item">
	                	<a class="page-link bg-light" href="/news?currPage=${currPage - 1}">&laquo;</a>
	              	</li>
				</c:if>
	          	<c:forEach var="page" begin="${startPage }" end="${endPage}">
	          		<c:choose>
						<c:when test="${page == currPage}">
						<li class="page-item active">
							<span class="page-link  bg-light ">${page}</span>
						</li>
						</c:when>
						<c:otherwise>
						<li class="page-item">
							<a class="page-link bg-light" href="/news?currPage=${page}">${page}</a>
						</li>
						</c:otherwise>
					</c:choose>
	          	</c:forEach>
	          	
	          	<c:if test="${currPage < totalPage}">
		          	<li class="page-item">
						<a class="page-link  bg-light" href="/news?currPage=${currPage + 1}">&raquo;</a>
					</li>
				</c:if>
	          </ul>
	        </div>
			
			
<%-- 		<div>
          <ul class="pagination pagination-sm">
          	<c:if test="${currPage > 1}">
          		 <li class="page-item">
                	<a class="page-link" href="/news?currPage=${currPage - 1}">&laquo;</a>
              	</li>
			</c:if>
          	<c:forEach var="page" begin="1" end="${totalPage}">
          		<c:choose>
					<c:when test="${page == currPage}">
						<span>${page}</span>
					</c:when>
					<c:otherwise>
					<li class="page-item">
						<a class="page-link" href="/news?currPage=${page}">${page}</a>
					</li>
					</c:otherwise>
				</c:choose>
          	</c:forEach>
          	
          	<c:if test="${currPage < totalPage}">
	          	<li class="page-item">
					<a class="page-link" href="/news?currPage=${currPage + 1}">&raquo;</a>
				</li>
			</c:if>
          </ul>
        </div>
 --%>		
		</c:if>
		
	<%-- 	<div>
          <ul class="pagination pagination-sm">
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
        </div> --%>
			</div>
	</div>
	
	 <footer>
		<jsp:include page="footer.jsp"></jsp:include>
	</footer>
	
	
	
</body>
</html>