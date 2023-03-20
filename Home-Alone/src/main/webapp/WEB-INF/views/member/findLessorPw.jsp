<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      
<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/css/bootstrap.min.css" rel="stylesheet"></link>
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
  <script src="http://code.jquery.com/jquery-latest.js"></script>
<meta charset="UTF-8">

    <style>
      .input-form {
        max-width: 680px;
  
        margin-top: 80px;
        padding: 32px;
  
        background: #fff;
        -webkit-border-radius: 10px;
        -moz-border-radius: 10px;
        border-radius: 10px;
        -webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
        -moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
        box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
      }
    </style>
<title>비밀번호 찾기</title>	
</head>
<body>   
<!-- 네브바 -->
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="/index"><img src="/img/house.png"></a>
      <div class="collapse navbar-collapse" id="navbarColor03">
        
        <!-- <a href="/index" class="btn btn-secondary my-2 my-sm-0" type="submit">Login</a> -->
      </div>
    </div>
  </nav>
   <!-- 네브바 끝 -->
  <div class="container">
    <div class="input-form-backgroud row">
      <div class="input-form col-md-12 mx-auto">
        <h1 style="text-align:center;"  class="mb-3">Find My Account</h1>
          <div class="row">
            <div class="mb-3">	
						<form action="${pageContext.request.contextPath }/member/findLessorPw" id="findLessorPw" method="post" name="findLessorPw">
							<div class="row">
								<div class="col-12 mb-4">
									<label for="phone_number">아이디</label> 
									<input type="text" name="lessorId" class="form-control" id="lessorId" placeholder="아이디를 입력하세요. ">
								</div>
								<div class="col-12 mb-4">
									<label for="phone_number">전화번호</label> 
									<input type="text" name="phone" class="form-control" id="phone" placeholder=" 010-0000-0000 "><br>
								<br><br>
								</div>
								
								<button class="btn alazea-btn w-120" style="width:360pt;height:40pt;margin:auto;" type="submit" value="check">비밀번호 찾기</button>
								<br><br>
								</div>
						</form>
						
					</div>
				</div>
			</div>
		</div>
	</div><br><br><br>
	
	<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="/js/findLessorPw.js" ></script>
<!-- 	<script type="text/javascript">
	$(document).ready(function(){
		$('#findLessorPw').submit(function(){
			// alert("성공!");
			if($('#lessorId').val() == ''){
				alert("아이디를 입력하세요.");
				return false;
			}
			
			if($('#phone').val() == ''){
				alert("휴대폰 번호를 입력하세요.");
				return false;
			}
			
		});	
	});
	
	</script> -->
</body>
</html>	