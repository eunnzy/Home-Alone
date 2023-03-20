<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style type="text/css">
.uploadResult ul {
    display: flex;
    flex-flow: row;
    justify-content: center;
    align-items: center;
}

.uploadResult ul li {
    list-style: none;
    padding: 10px;
    align-content: center;
    text-align: center;
    display: flex;
}

.uploadResult ul li img {
    width: 100px;
}
</style>
<title>Alarm Write</title>
</head>
<body>
	<header>
		<jsp:include page="header.jsp"></jsp:include>
	</header>
	
   <div class="container">
    <div class="bs-docs-section row">
     <div class="col-lg-12"><br><br>
       <h1 id="tables">공지사항 등록</h1>
       
     <form action="insertAlarmBoard.do" method="post" enctype="multipart/form-data" id="regForm">
	   <div class="bs-component">
        <table class="table table-hover">
         <tr>
          <th scope="col" class="col col-lg-1">제목</th>
           <td><input type="text" class="form-control" id="title" name="title" required></td>
          </tr>
          <tr>
           <th scope="col" class="col col-lg-1">첨부파일</th>
           <td><input class="form-control" type="file" id="file" name="uploadFile" multiple></td>
          </tr>
          <tr>
           <th scope="col" class="col col-lg-1"></th>
            <td class="uploadResult">
             <ul>
             </ul>
            </td> 
          </tr>
          <tr>
           <th scope="col" class="col col-lg-1">내용</th>
           <td><textarea class="form-control" id="content" rows="10" name="content" required></textarea></td>
          </tr>
        </table>
        </div>
        
	     <!-- 하단 버튼 -->
	     <button type="button" class="btn btn-info" id="backBT">취소</button>
	     <span class="float-end">
	     	<button type="submit" class="btn btn-info" id="regBT">등록</button>
	     </span>
       </form>
     
      </div>
     </div>
	</div>

	
	<footer>
    	<jsp:include page="../footer.jsp"></jsp:include>
    </footer>
    
    <!-- 자바스크립트 -->
    <script type="text/javascript" src="/js/Alarmboard_reg_Attach.js"></script>
</body>
</html>