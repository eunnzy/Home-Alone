<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 화면</title>
</head>
<body>
	 <header>
		<jsp:include page="header.jsp"></jsp:include>
 	 </header>
	
	<div style="background-color: white;">
	  <div>
<!-- 	    <img src="img/b.png" style="max-width: 100%; height:auto;">
	    <img src="img/c.png" style="max-width: 100%; height:auto;">
	    <img src="img/d.png" style="max-width: 100%; height:auto;"> -->
	    <img src="img/b.png">
	    <img src="img/c.png">
	    <img src="img/d.png">
	  </div>
  
	<div style="text-align: center;">
    	<br>
    	<a href="/member/login"><button type="button" class="btn btn-primary" style="display: inline-block; width: 150px;">시작하기</button></a>
    	<br><br><br>
  	</div>
	</div>

    <footer>
    	<jsp:include page="footer.jsp"></jsp:include>
    </footer>
</body>
</html>