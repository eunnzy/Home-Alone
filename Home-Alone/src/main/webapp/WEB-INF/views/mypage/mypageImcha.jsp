<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- <link href="/css/bootstrap.min.css" rel="stylesheet"></link> -->
<link href="/css/mypage.css" rel="stylesheet"></link>
<title>마이페이지</title>
</head>
<body>
	<header>
		<jsp:include page="../header.jsp"></jsp:include>
	</header>


<!-- body -->
  <form action="mypage">
    <h2 style="text-align: center; font-weight: bold;" class="mypage">My Page</h2>
    <div class="d-flex  mx-auto ">
    <table style="margin-left: auto; margin-right: auto;" class="my-5" >
      <tr>
      	<td>
          <button class="mx-5 imcha-button" type="button" onclick = "location.href = '/mypage/getMember'">
	          <div class="imcha-div"><img src="/img/man-user.png" alt="유저이미지"></div>
	          	회원정보수정
          </button>
        </td>
        <td>
          <button class="mx-5 imcha-button" type="button" onclick = "location.href = '/like/likeList?imchaId=${imcha.imchaId}'">
          	<div class="imcha-div"><img src="/img/heart.png" alt="하트이미지" ></div>
          		찜 목록
          </button>
        </td>
        <td>
          <button class="mx-5 imcha-button" type="button" onclick = "location.href = '/home/reservation/list?imchaId=${imcha.imchaId}'">
          	<div class="imcha-div"><img src="/img/booking.png" alt="예약이미지"></div>
          		예약 확인
          </button>
        </td>
        <td>
          <button class="mx-5 imcha-button" type="button" onclick = "location.href = '/qna/list?imchaId=${imcha.imchaId}'">
          	<div class="imcha-div"><img src="/img/question.png" alt="문의이미지"></div>
          		문의 하기
          </button>
        </td>
        
      </tr>
    </table>
  </div>
  </form>

</body>
</html>