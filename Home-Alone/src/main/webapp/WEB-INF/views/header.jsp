<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/css/bootstrap.min.css" rel="stylesheet"></link>
<link rel="stylesheet"
   href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
   integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
   crossorigin="anonymous" referrerpolicy="no-referrer" />
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
   integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
   crossorigin="anonymous"></script>
<title>Document</title>
</head>
<body>
   <nav class="navbar navbar-expand-sm navbar-primary bg-light">
      <div class="container-fluid">
         <a class="navbar-brand" href="/index"><img src="/img/house.png"></a>
         <div class="collapse navbar-collapse" id="navbarColor03">
            <ul class="navbar-nav me-auto">
               <li class="nav-item"><a class="nav-link" href="/home/searchHome">집찾기</a></li>
               <%-- <li class="nav-item"><c:if
                     test="${member.userRoll == '일반회원' }">
                     <a class="nav-link" href="../mypage/mypageImcha">MyPage</a>
                  </c:if> <c:if test="${lessor.userRoll == '중개인' }">
                     <a class="nav-link" href="../mypage/mypageLessor">MyPage</a>
                  </c:if></li> --%>
               <li class="nav-item"><a class="nav-link" href="/community/list">커뮤니티</a></li>
            </ul>

            
            <!-- 로그인 안헀을 때 -->
            <c:if test="${imcha == null && lessor == null}">   
               <a class="nav-link dropdown-toggle" href="#" id="memberDropdown"
                  role="button" data-bs-toggle="dropdown" aria-expanded="false">
                  <span class="fa-solid fa-user" style="font-size: 27px;"></span>
               </a>
               <div class="dropdown-menu dropdown-menu-end" aria-labelledby="memberDropdown">
                  <a class="dropdown-item" href="/member/imchaLogin">일반회원 로그인</a> <a
                     class="dropdown-item" href="/member/lessorLogin">중개인 로그인</a> <a
                     class="dropdown-item" href="/member/selectMemberType">회원가입</a>
                  </ul>
               </div>
            </c:if>
               
            <c:if test="${imcha != null}">   <!-- 일반회원 로그인시  -->
               <a class="nav-link dropdown-toggle" href="#" id="lessorDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                  <span><img src="/icon/imcha.png"></span>
               </a>
               <div class="dropdown-menu dropdown-menu-end" aria-labelledby="lessorDropdown">
                  <li class="dropdown-item"> ${imcha.nickname}(${imcha.userRoll})님 </li>
                  <hr style="margin: auto">
                  <li><a class="dropdown-item" href="../mypage/mypageImcha">마이페이지</a></li>
                  <li><a class="dropdown-item" href="/member/logout.do">로그아웃</a></li>
               </div>
            </c:if>
            
            <c:if test="${lessor != null}">   <!-- 중개인 로그인시 -->
               <a class="nav-link dropdown-toggle" href="#" id="lessorDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                  <span><img src="/icon/lessor.png"></span>
               </a>
               <div class="dropdown-menu dropdown-menu-end" aria-labelledby="lessorDropdown">
                  <li class="dropdown-item"> ${lessor.name}(${lessor.userRoll})님 </li>
                  <hr style="margin: auto">
                  <li><a class="dropdown-item" href="../mypage/mypageLessor">마이페이지</a></li>
                  <li><a class="dropdown-item" href="/member/logout.do">로그아웃</a></li>
               </div>
            </c:if>
            
         </div>
      </div>
      </div>

   </nav>
</body>
</html>