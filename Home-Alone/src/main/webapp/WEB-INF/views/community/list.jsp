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
	<style>
		.login-show {
			display: none;
		}
	</style>
</head>
<body>
	<header>
		<jsp:include page="../header.jsp"></jsp:include>
	</header>
	
	<div class="container">
        <div class="bs-docs-section row">
        	<div class="col-lg-11 mx-auto ">
            
				<h1 id="tables" class="text-center mt-5 mb-3">Community</h1>
            	<div class="mx-auto community-info text-center mb-2">
             		혼자서 집 구하시기 힘드시지 않았나요? <br> 
             		커뮤니티를 활용하여 서로 정보를 공유하고, 질문도 해보세요
            	</div>
                
	            <div class="row mt-3 mb-3">
		            <div class="col">
			            <div class="category-div">
			            	<button class="btn cateBtn" id="전체" name="all">전체</button>
			            	<button class="btn cateBtn" id="질문" name="ask">질문</button>
			            	<button class="btn cateBtn" id="정보공유" name="info">정보공유</button>
			            </div>
		            </div>
		            <div class="col float-end">
			            <form id="searchForm" action="/community/list" method="get">
			            	<div class="row">
			                  	<div class="d-flex">
			                  	   <div class="col-sm">
			                  	   		<select class="form-select" id="condition" name="type" required>
							              	<option value="" <c:out value="${pageMaker.cri.type == null?'selected':''}" />> 선택 </option>
							                <option value="T" <c:out value="${pageMaker.cri.type eq 'T'?'selected':''}" />> 제목 </option>
							                <option value="C" <c:out value="${pageMaker.cri.type eq 'C'?'selected':''}" />> 내용 </option>
							                <option value="N" <c:out value="${pageMaker.cri.type eq 'N'?'selected':''}" />> 닉네임 </option>
							                <option value="TC" <c:out value="${pageMaker.cri.type eq 'TC'?'selected':''}" />> 제목 + 내용 </option>
							       		</select>
			                  	   </div>
			                       <div class="col">
			                           <input class="form-control me-sm-2" type="search" placeholder="Search" name="keyword"  required>
						               <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }" >
						               <input type="hidden" name="amount" value="${pageMaker.cri.amount }" >                 
						           </div>
			                       <div class="col-auto">  
			                       		<button class="btn btn-secondary" type="submit">검색</button>
			                       </div>
			                      </div>
			                  </div>
			            </form>
					</div>
				</div>	
            <!-- 게시판시작 -->
            <div class="bs-component">
              <table class="table table-hover table-border" style="text-align: center;">
                  <tr class="table-row">
                    <th scope="col" style="width: 60%;">제목</th>
                    <th scope="col" style="width: 15%;">닉네임</th>
                    <th scope="col" style="width: 15%;">작성일</th>
                    <th scope="col" style="width: 10%;">조회수</th>
                  </tr>
                  <!-- 공지 -->
                  <c:forEach var="list" items="${ablist}">
	                  <tr class="table-primary table-row">
	                    <td><a class="abmove" href="${list.ano}" style="text-decoration:none;">${list.title}</a></td>
	                    <td>운영자</td>
	                    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${list.regdate}" /></td>
	                    <td></td>
	                  </tr>
                  </c:forEach>  
                  
                  <!-- 목록리스트 -->
                  <c:forEach items="${list}" var="board">
	                  <tr class="table-secondary table-row">
	                    <td><a class="move" href="${board.bno}" style="text-decoration:none;"> [${board.category }] ${board.title}</a></td>
	                    <td>${board.nickname}</td>
	                    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}" /></td>
	                    <td>${board.views}</td>
	                  </tr>
                  </c:forEach>
              </table>
            </div>
        
        <!-- 페이징 -->
        <div class="page-div mx-auto">
          <ul class="pagination pagination-sm bg-light">
          
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
        <div class="login-show">
        <span><button type="button" class="btn btn-info" id="myBtn">내가 쓴 글</button></span>
        <span class="float-end"><button type="button" class="btn btn-info" id="regBtn">글쓰기</button></span>
        </div>
    </div>
    </div>
    
    <!-- 페이지이동 Form -->
	<form id = "actionForm" action="/community/list" method="get" >
    	<input type="hidden" name="pageNum" id="pageNum" value="${pageMaker.cri.pageNum }" >
    	<input type="hidden" name="amount" id="amount" value="${pageMaker.cri.amount }" >
    	<input type="hidden" name="keyword" id="keyword" value="${pageMaker.cri.keyword}"  >
    	<input type="hidden" name="type" id="type" value="${pageMaker.cri.type}">
    </form>
	
	<footer>
    	<jsp:include page="../footer.jsp"></jsp:include>
    </footer>
    
    <!-- 자바스크립트 -->
	<script type="text/javascript">
	var pageNum = $("#pageNum");
	var amount = $("#amount");
	var keyword = $("#keyword");
	var type=$("#type");
	
	var imchaId = "${imcha.imchaId}";
	var lessorId = "${lessor.lessorId}";
	
	console.log(imchaId);
	
	$(document).ready(function() {
		
		if(imchaId != '') {
			$(".login-show").show();
		}
		
		$("#myBtn").on("click", function(e) {
			self.location = "/community/mylist?imchaId=" + imchaId;
		});
		
		var actionForm = $("#actionForm");
		$(".move").on("click", function(e) {
			e.preventDefault();
			actionForm.append("<input type='hidden' name='bno' value='" + $(this).attr("href") + "'>");
 			actionForm.append("<input type='hidden' name='imchaId' value='" + imchaId + "'>");  
			actionForm.attr("action", "/community/get");
			actionForm.submit();
		});
		
		$(".abmove").on("click", function(e) {
			e.preventDefault();
			actionForm.append("<input type='hidden' name='ano' value='" + $(this).attr("href") + "'>");
			actionForm.attr("action", "/community/getAlarm");
			actionForm.submit();
		});
		
		
		$(".cate").on("click", function(e) {
			e.preventDefault();
			var key = $(this).attr("href");
			
			pageNum.val(1);
			
			if(key == "전체"){
				type.val('');
				keyword.val('');
			}else {
				type.val('G');
				keyword.val(key);
			}
			
			actionForm.submit();
		});
		
		
	});
	
	$("#regBtn").click(function() {
		self.location = "/community/register";
	})
	
	</script>
	
	<script src="/js/board/list.js" ></script>
</body>
</html>