<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/css/bootstrap.min.css" rel="stylesheet"></link>
    <link href="/css/memberJoin.css" rel="stylesheet"></link>
    <title>Document</title>
    <script src="http://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
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
  	<h1 class="text-center mb-3" >중개인 회원가입</h1>
          <div class="row">
            <div class="mb-3">
              <label for="id_name">아이디</label>
              <div class="id_input_box">
              	<input type="text" class="form-control" name="lessorId" id="lessorId" placeholder="영어와 숫자를 조합" value="" required>
               	<span class="id_ck">아이디를 입력해주세요.</span>
              </div>
              <span class="id_input_re_1">사용 가능한 아이디입니다.</span>
			  <span class="id_input_re_2">아이디가 이미 존재합니다.</span>
            </div>

            <div class="mb-3">
              <label for="password">비밀번호</label>
              <input type="password" class="form-control" name="lessorPw" id="lessorPw" placeholder="****" required>
              <div class="invalid-feedback">
                비밀번호를 입력해주세요.
              </div>
              <span class="pw_ck">비밀번호를 입력해주세요.</span>
             </div>
            <div class="mb-3">
              <label for="pwck">비밀번호 확인</label>
              <input type="password" class="form-control" name="pwck" id="pwck" placeholder="****" required>
                 <span class="pwck_ck">비밀번호 확인을 입력해주세요.</span>
               <span class="pwck_input_re_1">비밀번호가 일치합니다.</span>
                <span class="pwck_input_re_2">비밀번호가 일치하지 않습니다.</span>
               </div>
               
            <div class="mb-3">
              <label for="lessor_nickname">닉네임</label>
              <div class="id_input_box">
              <input type="text" class="form-control" name="lessorNickName" id="lessorNickName" placeholder="" value="" required>
               <span class="nickname_ck">닉네임을 입력해주세요.</span>
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
            <span class="phone_ck">휴대폰 번호를 입력해주세요.</span>
          </div>

		<div class=" mb-3">
              <label for="name">이름</label>
              <input type="text" class="form-control" name="name" id="name" placeholder="" value="" required>
              <div class="invalid-feedback">
                이름을 입력해주세요.
              </div>
              <span class="nickname_ck">이름을 입력해주세요.</span>
            </div>
            
            <div class="mb-3">
            <label for="">생년월일</label>
            <input type="text" class="form-control" name="birthDate" id="birthDate" placeholder="1900-01-01" required>
            <div class="invalid-feedback">
              생년월일을 입력해주세요.
            </div>
            <span class="phone_ck">생년월일을 입력해주세요.</span>
          </div>
          
          <div class=" mb-3">
              <label for="jgsname">공인중개소이름</label>
              <input type="text" class="form-control" name="jgsName" id="jgsName" placeholder="" value="" required>
              <div class="invalid-feedback">
                공인중개소이름을 입력해주세요.
              </div>
              <span class="nickname_ck">공인중개소이름을 입력해주세요.</span>
            </div>
            
            <div class=" mb-3">
              <label for="jgsnum">공인중개사 등록번호</label>
              <input type="text" class="form-control" name="jgsNum" id="jgsNum" placeholder="" value="" required>
              <span class="nickname_ck">공인중개사 등록번호를 입력해주세요.</span>
            </div>
            <div class=" mb-3">
            	<div class="form_section_title">
            	<label for="jgsImg">공인중개사 인증서 등록</label>
            	</div>
            	<div class="form_section_content">
            		<input type="file" id="jgsImg" class="form-control" name='uploadFile'>
            		<div id="uploadResult">
        			<!--  <div id="result_card">
            				 <div class="imgDeleteBtn">x</div>
            				<img src="/member/display?fileName=house.png">
            			</div>  -->
            	</div>
            	</div>
            </div>
          <div class=" mb-3">
              <label for="user_roll">회원종류</label>
              <input type="text" class="form-control" name="userRoll" id="userRoll" placeholder="" value="중개인" readonly />
            </div>
            

            <div class="mb-3">
              <label for="address">주소</label>
              <div class="row mb-3">
              	<div class="col-8">
              	  <input type="text" class="form-control" name="lessorAddr1" id="lessorAddr1" placeholder="" readonly="readonly" >
              	</div>
              	<div class="col-4">
              		<div class="address_button" >
						<button class="btn btn-md btn-primary" onclick="execution_daum_address()">주소 찾기</span>
					</div>
              	</div>
              </div>
				
			<div class="mb-3">
              <input type="text" class="form-control" name="lessorAddr2" id="lessorAddr2" placeholder=""  readonly="readonly">
              <div class="invalid-feedback"> 
              </div>
              <br>
              
              <div class="mb-3">
              <input type="text" class="form-control" name="lessorAddr3" id="lessorAddr3" placeholder=""  readonly="readonly">
              <div class="invalid-feedback"> 
              </div>
              <span class="adress_ck">상세주소를 입력해주세요.</span>
            </div>


          <hr class="mb-4">
          <div class="custom-control custom-checkbox">
            <input type="checkbox" class="custom-control-input" id="aggrement" required>
            <label class="custom-control-label" for="aggrement">개인정보 수집 및 이용에 동의합니다.</label>
            <br><p>(※중개인의 경우 관리자가 회원가입 승인을 하여야 이용이 가능합니다.※)</p>
          </div>

          <div class="d-grid gap-2">
          	<button class="btn btn-primary" type="submit">회원 가입</button> &nbsp;
          </div>
          </div>
		</form>
      </div>
    </div>
  </div>  
</div>
 

 <script src="/js/member/lessorJoin.js" ></script>
 <script type="text/javascript" src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
 
</body>
</html>