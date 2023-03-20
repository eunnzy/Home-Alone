<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>MyBoard List</title>
</head>
<body>
	<header>
		<jsp:include page="../header.jsp"></jsp:include>
	</header>
	
	<div class="container">
        <div class="bs-docs-section row">
          <div class="col-lg-12">
            <br><br>
              <h1 id="tables">MyBoard</h1>
              <br><br>
              
            <!-- 게시판시작 -->
            <div class="bs-component">
              <table class="table table-hover" style="text-align: center;">
                  <tr>
                    <th scope="col" style="width: 10%;">Category</th>
                    <th scope="col" style="width: 40%;">Title</th>
                    <th scope="col" style="width: 15%;">Time</th>
                    <th scope="col" style="width: 10%;">Views</th>
                    <th scope="col" style="width: 10%;">Likes</th>
                    <th scope="col" style="width: 10%;">reply</th>
                  </tr>
                  <!-- 목록리스트 -->
                  <c:forEach items="${mylist}" var="board">
                  <tr class="table-light">
                    <td><c:out value="${board.category}" /></td>
                    <td>
                    	<a href="/community/get?bno=<c:out value='${board.bno}' />&userid=<c:out value="${imcha.imchaId}" />" style="text-decoration:none;">
                    		<c:out value="${board.title}" />
                    	</a>
                    </td>
                    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}" /></td>
                    <td><c:out value="${board.views}" /></td>
                    <td><c:out value="${board.likes}" /></td>
                    <td><c:out value="${board.replys}" /></td>
                  </tr>
                  </c:forEach>
              </table>
            </div>
        </div>
        </div>
        
        <!-- 페이징 -->
        <div>
          <ul class="pagination pagination-sm">
          
          	<c:if test="${mypageMaker.prev}">
          	  <li class="page-item">
                <a class="page-link" href="${mypageMaker.startPage -1}">&laquo;</a>
              </li>
          	</c:if>
          	
          	<c:forEach var="num" begin="${mypageMaker.startPage}" end="${mypageMaker.endPage}">
          		<li class="page-item ${mypageMaker.cri.pageNum == num ? "active" : ""} " >
          		<a class="page-link" href="${num}">${num}</a>
          		</li>
          	</c:forEach>
          	
          	<c:if test="${mypageMaker.next}">
          	  <li class="page-item">
                <a class="page-link" href="${mypageMaker.endPage +1 }">&raquo;</a>
              </li>
          	</c:if>
          </ul>
        </div>

        <!-- 하단 버튼 -->
        <div class="loginshow">
        <span><button type="button" class="btn btn-info" id="listBT">돌아가기</button></span>
        <span class="float-end"><button type="button" class="btn btn-info" id="regBT">글쓰기</button></span>
        <br><br></div>
    	</div>
    	
    	<!-- 페이지이동 Form -->
   		<form id = "myBoardForm" action="/community/mylist" method="get" >
    	<input type="hidden" name="pageNum" value="${mypageMaker.cri.pageNum }" >
    	<input type="hidden" name="amount" value="${mypageMaker.cri.amount }" >
    	</form>
	
	<footer>
    	<jsp:include page="../footer.jsp"></jsp:include>
    </footer>
    
    <!-- 자바스크립트 -->
    <script type="text/javascript">
	$(document).ready(function() {
		history.replaceState({},null,null);
		
		$("#listBT").on("click", function() {
			self.location = "/community/list";
		});
		
		var myBoardForm = $("#myBoardForm");
		$(".page-item a").on("click", function(e) {
			e.preventDefault();
			myBoardForm.find("input[name='pageNum']").val($(this).attr("href"));
			myBoardForm.submit();
		});
		
		$("#regBT").on("click", function() {
			self.location = "/community/register";
		});
	});
	</script>
</body>
</html>