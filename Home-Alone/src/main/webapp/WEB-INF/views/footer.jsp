<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
body {
 overflow-x: hidden;
}

/*  {
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
/* .column.side {
  width: 15%;
}


.column.middle {
  width: 20%;
} */

/* Clear floats after the columns */
/* .row:after {
  content: "";
  display: table;
  clear: both;
}
 */
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
	<hr class="mb-5">
	<div class="row ms-3">
  <div class="col text-center">
    <h6>Reference</h6>
    <p>Illustration by <a href="https://icons8.com/illustrations/author/A7iGlOUD5Neq">dekob2</a> from <a href="https://icons8.com/illustrations">Ouch!</a></p>
    <p>Bootstrap by Morph from <a href="https://bootswatch.com/">bootswatch</a></p>
  </div>
  
  <div class="col text-center">
    <h5>Member</h5>
    <p>
    <a href="#">정은지</a>
    </p>
  </div>
  
  <div class="col text-center">
    <h6 >GitHub</h6>
    <p><a href="https://github.com/eunnzy/Home-Alone">HomeAlone</a></p>
  </div>
</div>
</body>
</html>