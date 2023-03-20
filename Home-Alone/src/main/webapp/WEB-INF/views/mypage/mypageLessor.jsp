<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- <link href="./bootstrap.min.css" rel="stylesheet"></link> -->
    <link href="/css/mypage.css" rel="stylesheet"></link>
    <title>마이페이지</title>
</head>
<body>
	<header>
		<jsp:include page="../header.jsp"></jsp:include>
	</header>

<!-- body -->

  <form>
    <h2 style="text-align: center; font-weight: bold;" class="mypage">My Page</h2>
    <div class="d-flex  mx-auto ">
    <table style="margin-left: auto; margin-right: auto;" class="my-5" >
      <tr>
      	<td>
          <button class="btn-lg mx-5 lessor-button" type="button" onclick = "location.href = '/mypage/getLessor'">
          	<div class="lessor-div"><img src="/img/businessman.png" alt="중개인이미지"></div>
          		회원 정보 수정
          </button>
        </td>
        <td>
          <button class="mx-5 lessor-button" type="button" onclick = "location.href = '/home/manage/list'">
	          <div class="lessor-div"><img src="/img/mypage-house.png" alt="집이미지"></div>
	          	매물 관리
          </button>
        </td>
        <td>
          <button class="mx-5 lessor-button" type="button" onclick = "location.href = '/home/reservation/lessorList'">
	          <div class="lessor-div"><img src="/img/booking.png" alt="달력이미지"></div>
	          	예약 확인
          </button>
        </td>
        <td>
          <button class="mx-5 lessor-button" type="button" onclick = "location.href = '/qna/lessorList'">
         	 <div class="lessor-div"><img src="/img/question.png" alt="문의이미지"></div>
          		1대1 문의
          </button>
        </td>
        <td>
          <button class="btn-secondary btn-lg mx-5 lessor-button" type="button" onclick = "location.href = 'QNA_LESSOR.html'">
          	<div class="lessor-div"><img src="/img/administrator.png" alt="관리자이미지"></div>
          		관리자 문의
          	</button>
        </td>
      </tr>
    </table>
  </div>
  </form>
  
</body>
</html>