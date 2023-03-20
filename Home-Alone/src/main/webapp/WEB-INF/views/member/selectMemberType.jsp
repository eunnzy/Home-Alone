<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 유형 선택</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<style>
	.imchaJoin {
		cursor: pointer;
		color: black;
	}
	.lessorJoin {
		cursor: pointer;
		color: black;
	}
</style>
</head>
<body>
	<header>
		<jsp:include page="../header.jsp"></jsp:include>
	</header>
	
	<section>
	<div>
		<img src="/img/check.png" class="rounded mx-auto d-block mt-5 mb-5" width="100" height="100" alt="...">	
	</div>
	<div class="text-center" style="font-family: 'Noto Sans KR';">
		<h5 class="mb-5">가입하실 회원 유형을 선택해주세요</h5>
	</div>
	<div class="container">
		<div class="row">
			<div class="imchaJoin col-6 mr-4">
				<div class="card text-center bg-white">
			    	<div class="card-body">
				    		<img src="/img/person.png" class="rounded mx-auto d-block mt-2 mb-3" width="180" height="180"  alt="...">	
			   			<h5 class="card-title mt-3 mb-2"><b>일반회원(임차인)</b></h5>
						<p class="card-text">매물을 찾기를 원하시는 분</p>
				    </div>
				</div>
			</div>
			<div class="lessorJoin col-6">
				<div class="lessorJoin card text-center bg-white">
					<div class="card-body">
						<img src="/img/building.png" class="rounded mx-auto d-block mt-2 mb-3" width="180" height="180" alt="...">	
						<h5 class="card-title mb-2"><b>중개인(임대인)</b></h5>
						<p class="card-text">매물 등록을 원하시는 분</p>
		 			</div>
		 		</div>
		 	</div>
		</div>
	</div>
	</section>
	
	<script>
		$(".imchaJoin").click(function() {
			location.href = "/member/imchaJoin";
		});
		
		$(".lessorJoin").click(function() {
			location.href = "/member/lessorJoin";
		});
	</script>
	
</body>
</html>