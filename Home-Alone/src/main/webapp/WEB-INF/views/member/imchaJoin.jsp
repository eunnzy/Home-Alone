<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/css/bootstrap.min.css" rel="stylesheet"></link>
    <link href="/css/lessorJoin.css" rel="stylesheet"></link>

    <title>Document</title>
    <script src="http://code.jquery.com/jquery-3.4.1.js"
		integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
		crossorigin="anonymous"></script>
</head>
<body>
  <!-- 네브바 -->
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="/index"><img src="/img/house.png"></a>
    </div>
  </nav>
  <!-- 네브바 끝 -->
  
  <div class="container">
    <div class="input-form-backgroud row">
      <div class="input-form col-md-12 mx-auto">
        <h1 style="text-align:center;"  class="mb-3">   </h1>

  <form id="join_form" method="post">
  <h1 style="text-align:center;"  class="mb-3">회원가입</h4>
        <form class="validation-form" novalidate>
          <div class="row">
            <div class="mb-3">
              <label for="id_name">아이디</label>
              <div class="id_input_box">
              <input type="text" class="form-id" name="imchaId" id="imchaId" placeholder="영어와 숫자를 조합" value="" required>
              <div class="invalid-feedback">아이디를 입력해주세요.
               <span class="final_id_ck">아이디를 입력해주세요.</span>
              </div>
              </div>
              <span class="id_input_re_1">사용 가능한 아이디입니다.</span>
			  <span class="id_input_re_2">아이디가 이미 존재합니다.</span>
			 
            </div>

            <div class="mb-3">
              <label for="password">비밀번호</label>
              <input type="password" class="form-password" name="imchaPw" id="imchaPw" placeholder="****" required>
              <div class="invalid-feedback">
                비밀번호를 입력해주세요.
              </div>
              <span class="final_pw_ck">비밀번호를 입력해주세요.</span>
             </div>
            <div class="mb-3">
              <label for="pwck">비밀번호 확인</label>
              <input type="password" class="form-passwordCheck" name="pwck" id="pwck" placeholder="****" required>
              <div class="invalid-feedback">
                비밀번호를 입력해주세요.
                 <span class="final_pwck_ck">비밀번호 확인을 입력해주세요.</span>
               </div>
               <span class="pwck_input_re_1">비밀번호가 일치합니다.</span>
                <span class="pwck_input_re_2">비밀번호가 일치하지 않습니다.</span>
               </div>
               
            <div class="mb-3">
              <label for="nickname">닉네임</label>
              <input type="text" class="form-nickname" name="nickname" id="nickname" placeholder="" required>
              <div class="invalid-feedback">
                닉네임을 입력해주세요.
                 <span class="final_nickname_ck">닉네임을 입력해주세요.</span>
               </div>
               <span class="nickname_input_re_1">사용 가능한 닉네임입니다.</span>
                <span class="nickname_input_re_2">닉네임이 이미 존재합니다.</span>
               </div>
   


          <div class="mb-3">
            <label for="phone">전화번호</label>
            <input type="text" class="form-control" name="phone" id="phone" placeholder="010-1234-5678" required>
            <div class="invalid-feedback">
              전화번호를 입력해주세요.
            </div>
            <span class="final_phone_ck">휴대폰 번호를 입력해주세요.</span>
          </div>

          <div class=" mb-3">
              <label for="user_roll">회원종류</label>
              <input type="text" class="form-control" name="userRoll" id="userRoll" placeholder="" value="일반회원" readonly />
            </div>
   
            
		<div class=" mb-3">
		    <label for="exampleSelect1" class="form-label mt-4">주소</label>
		</div>
		    <select class="form-select" id="exampleSelect1" name="sido1" id="sido1" style="width: 40%;"></select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <select class="form-select" id="exampleSelect1" name="gugun1" id="gugun1" style="width: 40%;"></select>
		
            
          <hr class="mb-4">
          <div class="custom-control custom-checkbox">
            <input type="checkbox" class="custom-control-input" id="aggrement" required>
            <label class="custom-control-label" for="aggrement">개인정보 수집 및 이용에 동의합니다.</label>
          </div>

          <div class="col-md-6 mb-4"></div>
          <button class="btn btn-primary btn btn-block" type="submit">가입 완료</button> &nbsp;
          <!-- <a href="/member/lessorJoin" class="btn btn-primary btn btn-block" type="submit">중개인 회원가입</a>&nbsp;
          <button style=background-color:#FEE500;   class="btn btn-primary btn btn-block" type="submit" >카카오 회원가입</button> -->
</form>
</h1>
</form>
      </div>
    </div>
  </div>  


  	<footer>
       <jsp:include page="../footer.jsp"></jsp:include>
    </footer>
    
  <script src="/js/imchaJoin.js" ></script>
  <script type="text/javascript" src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>