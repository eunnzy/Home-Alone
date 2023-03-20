<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
	img{
/* 		width: 95%;
	    height: auto; */
	    display: flex;
	    margin: 15px auto 15px auto;
	}
</style>
<title>View</title>
</head>
<body>
	<header>
		<jsp:include page="../header.jsp"></jsp:include>
	</header>
	
		<!-- 본문 -->
		<div class="container"><br>
		<!-- 조회시작 : 제목, 최근수정날짜 -->
	    <div class="card mb-3" style="background-color: white;">
	      <h3 class="card-header"><c:out value="${Aboard.title}" /></h3>
	      <div class="card-footer text-muted">
	        <span class="float-end">최근 수정일 : <fmt:formatDate pattern="yyyy-MM-dd" value="${Aboard.updateDate}" /></span>
	      </div>
	      <!-- 파일 업로드 -->
	      <div id="uploadfile"><ul></ul></div>
	      <!-- 내용 -->
	      <div class="card-body">
	        <p class="card-text" style="color: black;"><c:out value="${Aboard.content}" /></p>
	      </div><br>
	      <!-- 이미지 업로드 -->
	      <div id="uploadimg"></div>
	        <!-- hidden -->
	      	<input type="hidden" name="ano" value="<c:out value="${Aboard.ano}" />" >
	    </div>
	    
	    <!-- 하단 버튼 -->
        <span><button type="button" class="btn btn-info" id="BackBT">목록으로</button></span>

 	    <!-- 페이지이동 Form -->
<%-- 	    <form id = "operForm" action="/community/modify" method="get" >
	   	 <input type="hidden" id="bno" name="bno" value="<c:out value="${board.bno}" />" >
 	   	 <input type="hidden" name="pageNum" value="<c:out value="${cri.pageNum }" />" >
	   	 <input type="hidden" name="amount" value="<c:out value="${cri.amount }" />"  >
	   	 <input type="hidden" name="keyword" value="<c:out value="${cri.keyword }" />"  >
	   	 <input type="hidden" name="type" value="<c:out value="${cri.type }" />"  > 
	    </form>  --%>
	    <div><br><br></div>
       </div>
	
	<footer>
    	<jsp:include page="../footer.jsp"></jsp:include>
    </footer>
    
    <!-- 자바스크립트 -->
    <script type="text/javascript">
    // 파일 업로드 
    $(document).ready(function() {
    	$("#BackBT").on("click", function() {
			self.location = "/community/list";
		});
    	
		(function() {
			var ano = '<c:out value="${Aboard.ano}" />';
			$.getJSON("/admin/getAttachList", {ano:ano}, function(arr) {
				console.log("arr" + arr);
				
				var imgstr = "";
				var filestr = "";
				
				$(arr).each(function(i, attach){
					if(attach.fileType){
						var fileCallPath = encodeURIComponent(attach.uploadPath + "/" + attach.uuid + "_" + attach.fileName);
						imgstr += "<div data-path='" + attach.uploadPath + "' data-uuid='" + attach.uuid + "' data-filename='" + attach.fileName + "' data-type='" + attach.fileType + "'>";
						imgstr += "<img src='/admin/display?fileName=" + fileCallPath + "'>";
						imgstr + "</div>";
					} else {
						var downloadPath = attach.uploadPath + "/" + attach.uuid + "_" + attach.fileName;
						filestr += "<li data-path='" + attach.uploadPath + "' data-uuid='" + attach.uuid + "' data-filename='" + attach.fileName + "' data-type='" + attach.fileType + "'>";
						filestr += "<a href='/community/download?fileName="+ downloadPath +"'>첨부파일 : " + attach.fileName + " </a>";
						filestr += "</li>";
					}
				});
				$("#uploadfile ul").html(filestr);
				$("#uploadimg").html(imgstr); 
			});
			})();
    });
    </script>
</body>
</html>