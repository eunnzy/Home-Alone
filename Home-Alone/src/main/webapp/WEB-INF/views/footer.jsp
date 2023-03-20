<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
* {
  box-sizing: border-box;
}

body {
  margin: 0;
}

/* Create three unequal columns that floats next to each other */
.column {
  float: left;
  padding: 10px;
}

/* Left and right column */
.column.side {
  width: 15%;
}

/* Middle column */
.column.middle {
  width: 20%;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}

/* Responsive layout - makes the three columns stack on top of each other instead of next to each other */
@media screen and (max-width: 600px) {
  .column.side, .column.middle {
    width: 100%;
  }
}

a{
text-decoration:none;
}

p{
font-size : 13px;
}
</style>

</head>
<body>
	<br><br><br><br><hr><br>
	<div class="row">
  <div class="column side">
    <h6>Reference</h6>
    <p>Illustration by <a href="https://icons8.com/illustrations/author/A7iGlOUD5Neq">dekob2</a> from <a href="https://icons8.com/illustrations">Ouch!</a></p>
    <p>Bootstrap by Morph from <a href="https://bootswatch.com/">bootswatch</a></p>
  </div>
  
  <div class="column middle">
    <h5>Team members</h5>
    <p>(스마트웹&콘텐츠개발)반응형 UI/UX 웹콘텐츠 개발자 양성과정A <br> 2022.07.22 ~ 2023.03.09 from <a href="https://www.iei.or.kr/main/main.kh">KH정보교육원</a></p>
    <p>
    <a href="#">백시현</a> &nbsp;&nbsp;|&nbsp;
    <a href="#">송혜빈</a> &nbsp;&nbsp;|&nbsp;
    <a href="#">이수호</a> &nbsp;&nbsp;|&nbsp;
    <a href="#">정은지</a>
    </p>
  </div>
  
  <div class="column side">
    <h6>GitHub</h6>
    <p><a href="https://github.com/eunnzy/Here-Is-My-Home">Here Is My Home</a></p>
  </div>
</div>
</body>
</html>