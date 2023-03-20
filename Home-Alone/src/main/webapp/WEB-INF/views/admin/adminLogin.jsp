<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="keywords" content="SignUp, Login, Register">
<meta name="keywords" content="Sign up, Sign in">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale-1.0">
 <title>Login</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
  <link type="text/css" rel="stylesheet" href="/css/adminLogin.css" />

</head>
<body>
<section class="login_section">
	<div class="container outer_container accounts_container">
		<div class="row h-100">
			<div class="col col-sm-12 col-md-12 col-lg-8 m-0 p-0 w-100 h-100 accounts_col">
			<div class="accounts_image w-100 h-100">
				<img src="/img/adminhome.png"
					alt="accounts_imge" class="img-fluid w-100 h-100" />
<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
</div>
</div>
<div class="col col-sm-12 col-md-12 col-lg-4 m-0 p-0 accounts_col">
<div class="accounts_forms w-100 h-100" id="login">
				<div class="title mt-4 p-4 w-100">
		<h1>여기가 내집</h1>
		<p class="mt-3">관리자 로그인 페이지.</p>
		</div>
		<form id="login_form" method="post" class="form w-100 p-4">
            <div class="form-group">
                <label for="adminId"></label>
                <input type="text" name="adminId" class="form-control" id="amdinId" placeholder="ID" onfocus="labelUp(this)" onblur="labelDown(this)" required />
              </div>
           <div class="form-group">
                <label for="login_password"></label>
                <i class="fa fa-eye-slash" id="eye_icon_login"></i>
                <input type="password" name="adminPw" class="form-control" id="adminPw" placeholder="PASSWORD"onfocus="labelUp(this)" onblur="labelDown(this)" required />
              </div>
			<div class="form-group mb-0">
				<button type="submit" class="btn btn-primary register_btn w-100">LOGIN</button>
			</div>
			<a href="/admin/adminJoin">관리자 추가</a>
			</form>
			</div>
			</div>
			</div>
			</div>
	</section>
	
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <script type="text/javascript" src="/js/adminLogin.js"></script>

	
</body>
</html>