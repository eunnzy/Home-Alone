<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
<meta charset="UTF-8">
  <title>Animated Login Form</title>
  
  <link href="https://fonts.googleapis.com/css?family=Poppins:600&display=swap" rel="stylesheet">
  <script src="https://kit.fontawesome.com/a81368914c.js"></script>
 <link href="/css/lessorLogin.css" rel="stylesheet"></link>
 <link href="/css/bootstrap.min.css" rel="stylesheet"></link>
  <meta name="viewport" content="width=device-width, initial-scale=1">
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
		<div class="login-wrapper">
			<div class="img-wrap">
				<div class="img-div">
					<img class="wave" src="/img/adminhome.png">
				</div>
			</div>
			<div class="content-wrap">
				<div class="login-content">
					<form id="login_form" method="post">
						<img src="/img/homes.png">
						<h2 class="title">중개인 로그인</h2>
						<div class="input-div one">
							<div class="i">
								<i class="fas fa-user"></i>
							</div>
							<div class="div">
								<h5>ID</h5>
								<label id="id"></label> <input type="text" class="input"
									id="lessorId" name="lessorId" required />
							</div>
						</div>
						<div class="input-div pass">
							<div class="i">
								<i class="fas fa-lock"></i>
							</div>
							<div class="div">
								<h5>Password</h5>
								<input type="password" class="input" id="lessorPw"
									name="lessorPw" required />
							</div>
						</div>
						<div style="display: inline-block; float: left;">
							<a href="/member/findLessorId" id="findLessorId">아이디</a>
						</div>
						<div style="display: inline-block; float: left;">
							<p>&</p>
						</div>
						<div style="display: inline-block; float: left;">
							<a href="/member/findLessorPw" id="findLessorPw">비밀번호 찾기</a>
						</div>
						<div style="float: right;">
							<a href="/member/lessorJoin">회원가입</a>
						</div>

						<button type="submit" class="btn2" value="Login">Login</button>
					</form>
				</div>
			</div>
			
		</div>
	</div>

	<!--     <script type="text/javascript">
    const inputs = document.querySelectorAll(".input");


    function addcl(){
      let parent = this.parentNode.parentNode;
      parent.classList.add("focus");
    }

    function remcl(){
      let parent = this.parentNode.parentNode;
      if(this.value == ""){
        parent.classList.remove("focus");
      }
    }


    inputs.forEach(input => {
      input.addEventListener("focus", addcl);
      input.addEventListener("blur", remcl);
    });
    </script> -->


    
    <script src="/js/lessorLogin.js" ></script>
</body>
</html>