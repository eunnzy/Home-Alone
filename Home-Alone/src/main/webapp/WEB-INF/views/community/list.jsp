<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="/js/board_listBT.js"></script>
<title>Community List</title>
</head>
<body>
	<header>
		<jsp:include page="../header.jsp"></jsp:include>
	</header>
	
	<div class="container">
        <div class="bs-docs-section row">
          <div class="col-lg-12"><br><br>
            <h1 id="tables">Community</h1>
            <p><c:out value="${imcha.sido1}" /> <c:out value="${imcha.gugun1}" /></p>
                
            <span class="float-end">
            <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">카테고리</a>
            	<div class="dropdown-menu">
                 <a class="dropdown-item cate" href="동네소식">동네소식</a>
                 <a class="dropdown-item cate" href="동네질문">동네질문</a>
                 <a class="dropdown-item cate" href="동네맛집">동네맛집</a>
                 <a class="dropdown-item cate" href="도와줘요">도와줘요</a>
                 <a class="dropdown-item cate" href="취미생활">전국취미생활</a>
                 <a class="dropdown-item cate" href="자취꿀팁">전국자취꿀팁</a>
                 <a class="dropdown-item cate" href="분실실종">전국분실실종</a>
                </div>
            </span><br><br>
            
            <!-- 검색창 -->
            <form class="d-flex" id="searchForm" action="/community/list" method="get">
              <select class="form-select" id="condition" style="width: 25%;" name="type" required>
              	<option value="" <c:out value="${pageMaker.cri.type == null?'selected':''}" />> Search List </option>
                <option value="T" <c:out value="${pageMaker.cri.type eq 'T'?'selected':''}" />> Title </option>
                <option value="C" <c:out value="${pageMaker.cri.type eq 'C'?'selected':''}" />> Content </option>
                <option value="N" <c:out value="${pageMaker.cri.type eq 'N'?'selected':''}" />> Nickname </option>
                <option value="TC" <c:out value="${pageMaker.cri.type eq 'TC'?'selected':''}" />> Title or Content </option>
                <option value="TN" <c:out value="${pageMaker.cri.type eq 'TN'?'selected':''}" />> Title or Nickname </option>
                <option value="TCN" <c:out value="${pageMaker.cri.type eq 'TCN'?'selected':''}" />> Title or Content or Nickname </option>
              </select>
              
              <input class="form-control me-sm-2" type="search" placeholder="Search" name="keyword"
               value='<c:out value="${pageMaker.cri.keyword}" />' required>
               <input type="hidden" name="pageNum" value="<c:out value="${pageMaker.cri.pageNum }" />" >
               <input type="hidden" name="amount" value="<c:out value="${pageMaker.cri.amount }" />" >
              <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
            </form><br>

            <!-- 게시판시작 -->
            <div class="bs-component">
              <table class="table table-hover" style="text-align: center;">
                  <tr>
                    <th scope="col" style="width: 10%;">Category</th>
                    <th scope="col" style="width: 40%;">Title</th>
                    <th scope="col" style="width: 15%;">Name</th>
                    <th scope="col" style="width: 15%;">Time</th>
                    <th scope="col" style="width: 10%;">Views</th>
                    <th scope="col" style="width: 10%;">Likes</th>
                  </tr>
                  <!-- 공지 -->
                  <c:forEach var="list" items="${ablist}">
                  <tr class="table-primary">
                    <td></td>
                    <td><a class="abmove" href="<c:out value="${list.ano}" />" style="text-decoration:none;"><c:out value="${list.title}" /></a></td>
                    <td></td>
                    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${list.regdate}" /></td>
                    <td></td>
                    <td></td>
                  </tr>
                  </c:forEach>  
                  
                  <!-- 목록리스트 -->
                  <c:forEach items="${list}" var="board">
                  <tr class="table-secondary">
                    <td><c:out value="${board.category}" /></td>
                    <td><a class="move" href="<c:out value="${board.bno}" />" style="text-decoration:none;"><c:out value="${board.title}" /></a></td>
                    <td><c:out value="${board.nickname}" /></td>
                    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}" /></td>
                    <td><c:out value="${board.views}" /></td>
                    <td><c:out value="${board.likes}" /></td>
                  </tr>
                  </c:forEach>
              </table>
            </div>
        </div>
        </div>
        
        <!-- 페이징 -->
        <div>
          <ul class="pagination pagination-sm">
          
          	<c:if test="${pageMaker.prev}">
          	  <li class="page-item">
                <a class="page-link" href="${pageMaker.startPage -1}">&laquo;</a>
              </li>
          	</c:if>
          	
          	<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
          		<li class="page-item ${pageMaker.cri.pageNum == num ? "active" : ""} " >
          		<a class="page-link" href="${num}">${num}</a>
          		</li>
          	</c:forEach>
          	
          	<c:if test="${pageMaker.next}">
          	  <li class="page-item">
                <a class="page-link" href="${pageMaker.endPage +1 }">&raquo;</a>
              </li>
          	</c:if>
          </ul>
        </div>

        <!-- 하단 버튼 -->
        <div class="loginshow">
        <span><button type="button" class="btn btn-info" id="myboardBT">내가 쓴 글</button></span>
        <span class="float-end"><button type="button" class="btn btn-info" id="regBT">글쓰기</button></span>
        <br><br></div>
    </div>
    
    <!-- 페이지이동 Form -->
    <form id = "actionForm" action="/community/list" method="get" >
    	<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }" >
    	<input type="hidden" name="amount" value="${pageMaker.cri.amount }" >
    	<input type="hidden" name="keyword" value="<c:out value="${pageMaker.cri.keyword}" />" >
    	<input type="hidden" name="type" value="<c:out value="${pageMaker.cri.type}" />" >
    </form>
	
	<footer>
    	<jsp:include page="../footer.jsp"></jsp:include>
    </footer>
    
    <!-- 자바스크립트 -->
	<script type="text/javascript">
	$(document).ready(function() {
		var imchaId = '<c:out value="${imcha.imchaId}" />';
		
		if(imchaId == null || imchaId == '') {
			$("#regBT").hide();
			$("#myboardBT").hide();
		} else {
			$("#regBT").show();
			$("#myboardBT").show();
		}
		
		$("#myboardBT").on("click", function(e) {
			self.location = "/community/mylist?imchaid=<c:out value='${imcha.imchaId}' />";
		});
		
		var actionForm = $("#actionForm");
		$(".move").on("click", function(e) {
			e.preventDefault();
			actionForm.append("<input type='hidden' name='bno' value='" + $(this).attr("href") + "'>");
 			actionForm.append("<input type='hidden' name='userid' value='" + imchaId + "'>");  
			actionForm.attr("action", "/community/get");
			actionForm.submit();
		});
		
		$(".abmove").on("click", function(e) {
			e.preventDefault();
			actionForm.append("<input type='hidden' name='ano' value='" + $(this).attr("href") + "'>");
			actionForm.attr("action", "/community/getAlarm");
			actionForm.submit();
		});
		
		var cateForm = $("#cateForm");
		$(".cate").on("click", function(e) {
			e.preventDefault();
			var key = $(this).attr("href");
			actionForm.find("input[name='pageNum']").val(1);
			actionForm.find("input[name='type']").val('G');
			actionForm.find("input[name='keyword']").val(key);
			actionForm.submit();
		});
	});
	</script>
</body>
</html>