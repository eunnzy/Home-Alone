<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/css/bootstrap.min.css" rel="stylesheet"></link>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    <title>Document</title>
</head>
<body>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="/admin/main"><img src="/img/house.png"></a>
      <div class="collapse navbar-collapse" id="navbarColor03">
        <ul class="navbar-nav me-auto" >
          <li class="nav-item">
             <a class="nav-link" href="/admin/reportList">신고목록</a>
          </li>
          <li class="nav-item">
             <a class="nav-link" href="/member/lessorList">중개인 가입 목록</a>
          </li>
          <li class="nav-item">
             <a class="nav-link" href="/admin/ablist">공지게시판</a>
          </li>

         
         </ul>
     
          <c:if test = "${admin != null}">
          <div><a class="btn btn-secondary my-2 my-sm-0" href="/member/logout.do">Log-out</a></div>
          </c:if>
          </div>
     </div>

  </nav>
</body>
</html>