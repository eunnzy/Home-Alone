<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<title>아이디 찾기</title>   
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
            <form  method="post" name="findLessorId">
               <div class="row">
                  <div class="col-12 mb-4">
                     <label for="lessor_name">닉네임 </label> 
                     <input type="text" name="lessorNickName" class="form-control" id="lessorNickName" value="" placeholder="닉네임을 입력하세요.">
                  </div>
               <div class="col-12 mb-4">
                     <label for="lessor_phone">전화번호 </label> 
                     <input type="text" name="phone" class="form-control" id="phone" value="" placeholder="010-0000-0000"><br>
                  <br>
               </div>
                        
            <button class="btn btn-primary btn btn-block" id="findId" style="width:360pt;height:40pt;margin:auto;" type="button" value="check">아이디 찾기</button>
               <br><br>
            </div>
            <br>
                        
                        
      </form>
   </div>
</div>
</div>
</div>
   </div><br><br><br>
   

<!-- <script type="text/javascript">
   $(document).ready(function(){
	   
	   let formData = $("#findLessorId");
      $('#findId').click(function(){
         // alert("성공!");
         if($('#lessorNickName').val() == ''){
            alert("닉네임을 입력하세요.");
            return false;
         }
         
         if($('#phone').val() == ''){
            alert("휴대폰 번호를 입력하세요.");
            return false;
         }
         
         let lessor = {
        		 "lessorNickName" : $('#lessorNickName').val(),  "phone" : $('#phone').val()
        	}
         $.ajax({
        	data: lessor,
         	url: "/member/findLessorId",
         	type : 'POST',
			success : function(result) {
				if(result == 1) {
					const url = "/member/resultLessorId"
					window.open(url,"아이디찾기",'width=500px, height=700px, scrolbars=yes, resizeable=no');
				}
				else {
					alert("입력하신 정보가 올바르지 않습니다.");
				}
			},
         	error : function(result) {
				console.log(result);
				
				alert("전송 실패");
			}
         })
         
         
         formData.submit();
         
       
      });
      
      
      
   });
   
  /*  $("#findId").on("click", function(e) {
	   const url = "/member/resultLessorId"
   	   window.open(url,"아이디찾기",'width=500px, height=700px, scrolbars=yes, resizeable=no');
   }) */

</script> -->


    <footer>
       <jsp:include page="../footer.jsp"></jsp:include>
    </footer>
    
  <script src="/js/findLessorId.js" ></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>


