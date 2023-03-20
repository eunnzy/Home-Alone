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
<title>Modify</title>
</head>
<body>
	<header>
		<jsp:include page="../header.jsp"></jsp:include>
	</header>
	
   <div class="container">
    <div class="bs-docs-section row">
     <div class="col-lg-12"><br><br>
       <h1 id="tables">수정하기</h1>
       
     <form action="/community/updateBoard.do" method="post" enctype="multipart/form-data" id="modifyForm">
       <input type="hidden" name="bno" value="<c:out value="${board.bno}" />" >
	   <div class="bs-component">
	    
	    <!-- 카테고리, 제목, 첨부파일 -->
        <table class="table table-hover">
         <tr>
          <th scope="col" class="col col-lg-1">카테고리</th>
          <td>
           <select class="form-select" id="condition" name="category">
            <option value="<c:out value="${board.category}" />"><c:out value="${board.category}" /></option>
            <option value="동네소식">동네소식</option>
            <option value="동네질문">동네질문</option>
            <option value="동네맛집">동네맛집</option>
            <option value="도와줘요">도와줘요</option>
            <option value="취미생활">전국취미생활</option>
            <option value="자취꿀팁">전국자취꿀팁</option>
            <option value="분실실종">전국분실실종</option>
           </select>
          </td>
         </tr>
         <tr>
          <th scope="col" class="col col-lg-1">제목</th>
          <td><input type="text" class="form-control" id="title" name="title" value="<c:out value="${board.title}" />" required></td>
         </tr>
         <tr>
          <th scope="col" class="col col-lg-1">첨부파일</th>
          <td><input class="form-control" type="file" name="uploadFile" id="file"></td>
         </tr>
         <!-- 첨부파일 섬네일 -->
         <tr>
           <th scope="col" class="col col-lg-1"></th>
            <td class="uploadResult">
             <ul>
             	<!-- <li><div>
             		<img src='/community/display?fileName=" + fileCallPath + "'>";
					<button type='button' data-file=/'"+fileCallPath+"/' data-type='image' class='btn btn-primary btn-sm'>x</button><br>";
				</div></li> -->
             </ul>
            </td> 
          </tr>
         <!-- 내용 -->
         <tr>
          <th scope="col" class="col col-lg-1">내용</th>
          <td><textarea class="form-control" id="content" rows="10" name="content" required><c:out value="${board.content}" /></textarea></td>
         </tr>
        </table>
        </div>
        
	     <!-- 하단 버튼 -->
	     <button type="submit" class="btn btn-info" id="listBT">취소하고 목록으로</button>
	     <button type="reset" class="btn btn-info">수정취소</button>
	     <span class="float-end"><button type="submit" class="btn btn-info" id="subBT">수정</button></span>
	     
	     <input type="hidden" name="pageNum" value="<c:out value="${cri.pageNum}" />" >
    	 <input type="hidden" name="amount" value="<c:out value="${cri.amount}" />" >
    	 <input type="hidden" name="keyword" value="<c:out value="${cri.keyword}" />" >
    	 <input type="hidden" name="type" value="<c:out value="${cri.type}" />" >
     </form>
     
     <form id="actionForm" action="/community/list" method="get">
     	 <input type="hidden" name="pageNum" value="<c:out value="${cri.pageNum}" />" >
    	 <input type="hidden" name="amount" value="<c:out value="${cri.amount}" />" >
    	 <input type="hidden" name="keyword" value="<c:out value="${cri.keyword}" />" >
    	 <input type="hidden" name="type" value="<c:out value="${cri.type}" />" >
     </form>
      </div>
     </div>
	</div>
	
	<footer>
    	<jsp:include page="../footer.jsp"></jsp:include>
    </footer>
    
    <!-- 자바스크립트 -->
	<script type="text/javascript">
    $(document).ready(function() {
		(function() {
			// 기존 등록 파일 보여주기 
			var bno = '<c:out value="${board.bno}" />';
			$.getJSON("/community/getAttachList", {bno:bno}, function(arr) {
				console.log("arr" + arr);
				
				var str = "";
				
				$(arr).each(function(i, attach){
					if(attach.fileType){
						var fileCallPath = encodeURIComponent(attach.uploadPath + "/s_" + attach.uuid + "_" + attach.fileName);
						str+= "<li data-path='" + attach.uploadPath + "' data-uuid='" + attach.uuid + "' data-filename='" + attach.fileName + "' data-type='" + attach.fileType + "'><div>";
						str+= "<img src='/community/display?fileName=" + fileCallPath + "'>";
						str+= "<button type='button' data-file=/'"+fileCallPath+"/' data-type='image' class='btn btn-primary btn-sm'>x</button><br>";
						str+= "</div>";
						str+ "</li>";
					} else {
						str+= "<li data-path='" + attach.uploadPath + "' data-uuid='" + attach.uuid + "' data-filename='" + attach.fileName + "' data-type='" + attach.fileType + "'><div>";
						str+= "<img src='/img/attach.png'>";
						str+= "<button type='button' data-file=/'"+fileCallPath +"/' data-type='file' class='btn btn-primary btn-sm'>x</button><br>";
						str+= "</div>";
						str+ "</li>";
					}
				});
				$(".uploadResult ul").html(str);
			});
			})();
	});
	</script>
	<script type="text/javascript" src="/js/board_modify_Attach.js"></script>
</body>
</html>