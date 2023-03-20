<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/bootstrap.min.css" rel="stylesheet"></link>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>Alarm List</title>
</head>
<body>
	<header>
 		<jsp:include page="header.jsp"></jsp:include>
	</header>
	
	<div class="container">
        <div class="bs-docs-section row">
          <div class="col-lg-12"><br><br>
            <h1 id="tables">Alarm List</h1>

            <!-- 게시판시작 -->
            <div class="bs-component">
              <table class="table table-hover" style="text-align: center;">
                  <tr>
                    <th scope="col" style="width: 10%;"></th>
                    <th scope="col" style="width: 40%;">Title</th>
                    <th scope="col" style="width: 15%;"></th>
                    <th scope="col" style="width: 15%;">Time</th>
                    <th scope="col" style="width: 10%;"></th>
                    <th scope="col" style="width: 10%;"></th>
                  </tr>
                  <!-- 공지 -->
                  <c:forEach var="list" items="${ablist}">
                  <tr class="table-light">
                    <td></td>
                    <td><a class="abmove" href="<c:out value="${list.ano}" />" style="text-decoration:none;"><c:out value="${list.title}" /></a></td>
                    <td></td>
                    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${list.regdate}" /></td>
                    <td></td>
                    <td>
                    <div class="btn-group" role="group" aria-label="Basic example">
					  <a class="abmo" href="<c:out value="${list.ano}" />" style="text-decoration:none;"><button type="button" class="btn btn-secondary btn-sm">수정</button></a>
					  <a class="abde" href="<c:out value="${list.ano}" />" style="text-decoration:none;"><button type="button" class="btn btn-secondary btn-sm">삭제</button></a>
					</div>
                    </td>
                  </tr>
                  </c:forEach>  
              </table>
            </div>
        </div>
        </div>

        <!-- 하단 버튼 -->
        <div class="loginshow">
        <span class="float-end"><button type="button" class="btn btn-info" id="abregBT">공지 올리기</button></span>
        <br><br></div>
    </div>
    	<!-- 페이지이동 Form -->
    	<form id = "actionForm" action="/admin/getAB" method="get" >
    	</form>
    
	<footer>
     	<jsp:include page="../footer.jsp"></jsp:include> 
    </footer>
    <script type="text/javascript">
	$(document).ready(function() {
		$("#abregBT").on("click", function() {
			self.location = "/admin/abregister";
		});
		
		var actionForm = $("#actionForm");
		$(".abmove").on("click", function(e) {
			e.preventDefault();
			actionForm.append("<input type='hidden' name='ano' value='" + $(this).attr("href") + "'>");
			actionForm.submit();
		});
		
		$(".abde").on("click", function(e) {
			e.preventDefault();
			var result = confirm("삭제하시면 홈페이지의 공지사항에서도 삭제됩니다. 삭제하시겠습니까?");
			if(result){
				actionForm.attr("action", "/admin/deleteAlarm.do");
				actionForm.append("<input type='hidden' name='ano' value='" + $(this).attr("href") + "'>");
				actionForm.submit();
			}else{
			    return;
			}
		});
		
		$(".abmo").on("click", function(e) {
			e.preventDefault();
			actionForm.attr("action", "/admin/abmodify");
			actionForm.append("<input type='hidden' name='ano' value='" + $(this).attr("href") + "'>");
			actionForm.submit();
		});
		
	});
	</script>
</body>
</html>