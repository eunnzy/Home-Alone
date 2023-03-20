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
    <title>회원정보수정</title>
    <script src="http://code.jquery.com/jquery-3.4.1.js"
		integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
		crossorigin="anonymous"></script>
</head>
<body>
  <header>
		<jsp:include page="../header.jsp"></jsp:include>
	</header>
  
  <div class="container">
    <div class="input-form-backgroud row">
      <div class="input-form col-md-12 mx-auto">
        <h1 style="text-align:center;"  class="mb-3">   </h1>

  <h1 style="text-align:center;"  class="mb-3">회원 정보 수정</h1>
        <form id="getLessor_form" method="post" class="validation-form" action="updateLessor" novalidate>
          <div class="row">
            <div class="mb-3">
              <label for="id_name">아이디</label>
              <div class="id_input_box">
			      <input type="text" class="form-id" name="lessorId" id="lessorId"  value="${lessor.lessorId}" required>
			      <div class="invalid-feedback">아이디를 입력해주세요.
               		<span class="final_id_ck">아이디를 입력해주세요.</span>
              	  </div>
              </div>
            </div>
            <span class="id_input_re_1">사용 가능한 아이디입니다.</span>
			<span class="id_input_re_2">아이디가 이미 존재합니다.</span>
          </div>
            

            <div class="mb-3">
              <label for="password">비밀번호</label>
              <input type="password" class="form-password" name="lessorPw" id="lessorPw" value="${lessor.lessorPw}" required>
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
              <label for="lessor_nickname">닉네임</label>
              <div class="id_input_box">
              <input type="text" class="form-nickname" name="lessorNickName" id="lessorNickName" placeholder="" value="${lessor.lessorNickName}" required>
              <div class="invalid-feedback">닉네임을 입력해주세요.
              	 <span class="final_nickname_ck">닉네임을 입력해주세요.</span>
              </div>
              <span class="nickname_input_re_1">사용 가능한 닉네임입니다.</span>
			  <span class="nickname_input_re_2">닉네임이 이미 존재합니다.</span>
              </div>
           <!--    <span class="id_input_re_1">사용 닉네임입니다.</span>
			  <span class="id_input_re_2">닉네임이 이미 존재합니다.</span> -->
            </div>


          <div class="mb-3">
            <label for="phone">전화번호</label>
            <input type="text" class="form-control" name="phone" id="phone" placeholder="010-1234-5678" value="${lessor.phone}" required>
            <div class="invalid-feedback">
              전화번호를 입력해주세요.
            </div>
            <span class="final_phone_ck">휴대폰 번호를 입력해주세요.</span>
          </div>

			<div class="mb-3">
              <label for="name">이름</label>
              <input type="text" class="form-control" name="name" id="name" placeholder="" value="${lessor.name}" required>
              <div class="invalid-feedback">
                이름을 입력해주세요.
              </div>
              <span class="final_nickname_ck">이름을 입력해주세요.</span>
            </div>
            
            <div class="mb-3">
	            <label for="">생년월일</label>
	            <input type="text" class="form-control" name="birthDate" id="birthDate" placeholder="1900-01-01" value="${lessor.birthDate}" required>
	            <div class="invalid-feedback">
	              생년월일을 입력해주세요.
	            </div>
	            <span class="final_phone_ck">생년월일을 입력해주세요.</span>
          	</div>
          
          	<div class=" mb-3">
              <label for="jgsname">공인중개소이름</label>
              <input type="text" class="form-control" name="jgsName" id="jgsName" placeholder="" value="${lessor.jgsName}" required>
              <div class="invalid-feedback">
                공인중개소이름을 입력해주세요.
              </div>
              <span class="final_nickname_ck">공인중개소이름을 입력해주세요.</span>
            </div>
            
            <div class=" mb-3">
              <label for="jgsnum">공인중개사 등록번호</label>
              <input type="text" class="form-control" name="jgsNum" id="jgsNum" placeholder="" value="${lessor.jgsNum}" required>
              <div class="invalid-feedback">
                공인중개사 등록번호를 입력해주세요.
              </div>
              <span class="final_nickname_ck">공인중개사 등록번호를 입력해주세요.</span>
            </div>
          <div class=" mb-3">
              <label for="user_roll">회원종류</label>
              <input type="text" class="form-control" name="userRoll" id="userRoll" placeholder="" value="중개인" readonly />
            </div>
            
            
   
       

            <div class="mb-3">
              <p> </p>
              <label for="address">주소</label>
              <input type="text" class="form-control_1" name="lessorAddr1" id="lessorAddr1" placeholder="" readonly="readonly" value="${lessor.lessorAddr1}">
              <div class="invalid-feedback"> 
              </div>
              <div class="address_button" onclick="execution_daum_address()">
					<span>주소 찾기</span>
				</div>
			</div>
				
			<div class="mb-3">
              <input type="text" class="form-control_2" name="lessorAddr2" id="lessorAddr2" placeholder=""  readonly="readonly" value="${lessor.lessorAddr2}">
              <div class="invalid-feedback"> 
              </div>
              <br>
              
              <div class="mb-3">
              <input type="text" class="form-control_3" name="lessorAddr3" id="lessorAddr3" placeholder=""  readonly="readonly" value="${lessor.lessorAddr3}">
              <div class="invalid-feedback"> 
              </div>
              	<span class="final_adress_ck">상세주소를 입력해주세요.</span>
              </div>
             </div>


          
          <hr class="mb-4">
          <div class="custom-control custom-checkbox">
            <input type="checkbox" class="custom-control-input" id="aggrement" required>
            <label class="custom-control-label" for="aggrement">개인정보 수집 및 이용에 동의합니다.</label>
          </div>

          <div class="col-md-6 mb-4"></div>
          <button class="btn btn-primary btn btn-block" type="submit">수정 완료</button> &nbsp;
	</form>
      </div>
    </div>
    </div>
  
    <footer class="my-3 text-center text-small">
      <p>  &nbsp; </p>
    </footer>




  <!-- footer -->
  <div style="background-color: #dbe2f0; text-align: center;">
    <br><br>
    <div>
        <a href="#">이용약관</a>   
        <a href="#">개인정보처리방침</a>
        <a href="#">프로젝트소개</a><hr>
        <a href="https://icons8.com/illustrations/author/zD2oqC8lLBBA">Icons 8</a> from <a href="https://icons8.com/illustrations">Ouch!</a>
    </div>
    
    <br><br><br><br>
  </div>
 

 <script src="/js/lessorJoin.js" ></script>
 <script type="text/javascript" src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
 
</body>
</html>