<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/css/bootstrap.min.css" rel="stylesheet"></link>
<title>Insert title here</title>
</head>
<body>
	<header>
		<jsp:include page="../header.jsp"></jsp:include>
	</header>
	
	<h2 style="text-align: center; font-weight: bold;" class="my-5">문의 답변</h2>
  <div class="container">
    <div class="row">
      <div class="col-12">
        <div class="card bg-light" style="height: 30rem;">
          <div class="card-body">
            <h4 class="card-title">제목 : ${board.iqTitle }</h4>
            <h6 class="card-subtitle mb-2 text-muted">
              <span>작성자 : ${board.imchaId}</span> 
              <span style="float: right;">작성일자 : ${board.iqDate}</span>
            </h6>
            <p class="card-text">내용</p>
            <p>${board.iqContent}</p>
          </div>
        </div>
      </div>


      <form>
        <fieldset>
          <div class="form-group">
            <label for="exampleTextarea" class="form-label mt-4">답변</label>
            <textarea class="form-control" style="height: 15rem;" id="exampleTextarea" rows="3" value="${answer.lqAnswer }"></textarea>
          </div>
    
          <div class="form-group row">
            <label for="staticId" class="col-sm-2 col-form-label">작성자</label>
            <div class="col-sm-10">
              <input type="text" readonly="" class="form-control-plaintext" id="staticId" value="${answer.lessorId }">
            </div>
          </div>
          <div class="form-group row">
            <label for="staticDate" class="col-sm-2 col-form-label">작성일시</label>
            <div class="col-sm-10">
              <input type="date" readonly="" class="form-control-plaintext" id="staticDate" value="${answer.lqDate }">
            </div>
          </div>
          <fieldset class="form-group">


          <button type="submit" class="btn btn-primary my-5" style="float: right;">답변 등록</button>
        </fieldset>
      </form>
	
</body>
</html>