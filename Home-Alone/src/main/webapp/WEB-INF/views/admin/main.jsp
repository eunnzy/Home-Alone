<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 화면</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
</head>
<body>
	 <header>
		<jsp:include page="header.jsp"></jsp:include>
 	 </header>
 	 
	<br><br>
	<div class="container">
		<div class="row">
			<div class="complain col-6 mr-4">
				<div class="card text-center bg-white">
			    	<div class="card-body">
				    		<img src="/img/complain.png" class="rounded mx-auto d-block mt-2 mb-3" width="180" height="180"  alt="...">	
			   			<h5 class="card-title mt-3 mb-2"><b>신고 목록</b></h5>
						<p class="card-text">허위매물 신고 목록 관리하기</p>
				    </div>
				</div>
			</div>
			<div class="idcheck col-6">
				<div class="card text-center bg-white">
					<div class="card-body">
						<img src="/img/idcheck.png" class="rounded mx-auto d-block mt-2 mb-3" width="180" height="180" alt="...">	
						<h5 class="card-title mb-2"><b>중개인 가입 목록</b></h5>
						<p class="card-text">공인중개사 자격증 확인하기</p>
		 			</div>
		 		</div>
		 	</div>
		</div>
		<br><br>
		<div class="row">
			<div class="alarmboard col-6 mr-4">
				<div class="card text-center bg-white">
			    	<div class="card-body">
				    		<img src="/img/alarmboard.png" class="rounded mx-auto d-block mt-2 mb-3" width="180" height="180"  alt="...">	
			   			<h5 class="card-title mt-3 mb-2"><b>공지게시판</b></h5>
						<p class="card-text">커뮤니티 공지사항 관리하기</p>
				    </div>
				</div>
			</div>
			<div class="homepage col-6">
				<div class="card text-center bg-white">
					<div class="card-body">
						<img src="/img/homepage.png" class="rounded mx-auto d-block mt-2 mb-3" width="180" height="180" alt="...">	
						<h5 class="card-title mb-2"><b>Here is My Home</b></h5>
						<p class="card-text">이용자 홈페이지로 이동하기</p>
		 			</div>
		 		</div>
		 	</div>
		</div>
	</div>
    <footer>
    	<jsp:include page="footer.jsp"></jsp:include>
    </footer>
    
    <script>
		$(".complain").click(function() {
			location.href = "/admin/reportList";
		});
		
		$(".idcheck").click(function() {
			location.href = "/member/lessorList";
		});
		
		$(".alarmboard").click(function() {
			location.href = "/admin/ablist";
		});
		
		$(".homepage").click(function() {
			location.href = "/index";
		});
	</script>
</body>
</html>