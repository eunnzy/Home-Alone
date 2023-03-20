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
            	<h5 style="text-align:center;"  class="mb-3">Change My Password</h5>
                   <br><br>
                     <form action="${pageContext.request.contextPath }/member/updateLessorPw" id="updateLessorPw" method="post" name="updateLessorPw">
                         <div class="row">
                             <div class="col-12 mb-4">
                                 <label for="lessor_id">ID</label>
                                 <input type="text" name="lessorId" class="form-control" id="lessorId" value="${findLessorPwVo.lessorId }" readonly>
                             </div>
                             <br>
                         <div class="col-12 mb-4">
                                <label for="phone_number">New Password</label>
                                <input type="password" name="lessorPw" class="password form-control" id="lessorPw" min="0" value="" placeholder="새로운 비밀번호를 입력하세요.">
                         <div class="passdiv"></div>
                         </div>
                         <div class="col-12 mb-4">
                               	<label for="phone_number">Password Check</label>
                                <input type="password" name="lessorPw1" class="password2 form-control" id="lessorPw1" min="0" value="" placeholder="비밀번호를 재입력하세요.">
                         <div class="pass2div"></div>
                         <br><br><br><br>
                         </div>
				         <div class="col-12 mb-4">
				                <button class="btn alazea-btn w-100">비밀번호 변경</button>
				         </div>
                       </div>
                      </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
 <script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script type="text/javascript">
		$(document).ready(function(){
			$('#updateLessorPw').submit(function(){
				// alert("성공!");
				if($('#lessorPw').val() == ''){
					alert("새로운 비밀번호를 입력하세요.");
					return false;
				}
				
				if ($('#lessorPw1').val() == '') {
					alert("비밀번호 체크를 입력하세요.");
					return false;
				}
				
				if ($('.password2').val() != $('.password').val()) {
					$('.pass2div').html("입력하신 비밀번호가 일치하지 않습니다.");
					$('.pass2div').focus();
					return false;
				}
				if ($('.password2').val() == $('.password').val()) {
					alert("비밀번호가 변경되었습니다.");
				}
				
			});
		
		
		});
	
	
</script>
</body>
</html>	   