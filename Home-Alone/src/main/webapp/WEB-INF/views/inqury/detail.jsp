<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	pageContext.setAttribute("br", "<br/>");
	pageContext.setAttribute("cn", "\n");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<link href="/css/bootstrap.min.css" rel="stylesheet"></link>
<style>
	.answer-div {
		box-shadow: 5px white;
		width: 100%;
		height: auto;
	}
	.home-div {
		border: 1px solid rgb(204, 204, 204, 0.5);
	}
</style>
</head>
<body>
	<header>
		<jsp:include page="../header.jsp"></jsp:include>
	</header>
	
	<div class="container mt-3 mb-4">
		<div class="col-10 px-5 py-5 mx-auto">
			<div class="home-div text-center px-3 py-3 mb-4">
				<h5 class="mb-2" >문의 매물</h5>
				<div class="col-4 mx-auto">
	   				<a href="/home/detail?homeNum=${inq.homeNum}"> 
	   					<img src="/home/getHomeImg?homeImgFile=${fn:replace(inq.homeImg.homeImgPath, '\\', '//')}/${inq.homeImg.homeImgUuid}_${inq.homeImg.homeImgName}" class="img-fluid">
	   				</a>
   				</div>
			</div>	
			<div class="card mb-3" style="background-color: white;">
				<h3 class="card-header"> ${inq.iqTitle } </h3>
				<div class="card-footer text-muted">
					<p class="float-end"> 작성자: ${inq.imchaId } </p>
				</div>
				<!-- 내용 -->
				<div class="card-body">
					<p class="card-text" style="color: black;">${fn:replace(inq.iqContent, cn, br)}</p>
					<span class="float-end">등록 날짜: <fmt:formatDate value="${inq.iqDate}" pattern="yyyy-MM-dd" /></span>
				</div>
			</div>
				
			
			<!-- 댓글 -->
			<div class="card card-body" style="background-color: white;">
				<h5> 답변 </h5>
				<div class="answer mt-3"></div>
				<!-- 댓글 작성 창 -->
				<div>
					<br>
				</div>
				<div class="input-group mb-3 replyreg">
					<input type="text" class="form-control"
						aria-label="Recipient's username" aria-describedby="button-addon2"
						style="" name="ansContent" id="ansContent" required>
					<button class="btn btn-secondary" type="button" id="ansBtn">등록</button>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			let iqNum = ${inq.iqNum};
			let answerUL = $(".answer");
			let lessorId = "${lessor.lessorId}";
			let jgsName = "${lessor.jgsName}";
			
			
			// 댓글 목록 보여주기  
			showList(1);
			function showList(page) { 
				answerService.getList({ iqNum:iqNum }, 
						function(list) { var str="";  if(list == null || list.length == 0) { answerUL.html(""); return; }
						console.log(list);	
						console.log(jgsName);
						for (var i = 0, len = list.length || 0; i < len; i++) {
								str += "<div class='answerdiv'> <h6 class='card-subtitle mb-2 text-muted' data-rno='" + list[i].ansNum + "'>";
								str += "<span>" + list[i].jgsName + "</span>";
								str += "<span class='float-end'>" + answerService.displayTime(list[i].ansDate) + "</span></h6>";
								str += "<p class='card-text'>" + list[i].ansContent + "</p>";
								if (list[i].lessorId == lessorId) {
									str += "<div class='float-end'>";
									str += "<button type='button' class='btn btn-primary btn-sm float-end delBT' data-no='" + list[i].ansNum + "'>삭제</button>"; 
									str += "</div><br><br></div>";
								}
							}
							answerUL.html(str);
							
							// 답변 삭제 
							$(".delBT").on("click", function(e) {
								var result = confirm("답변을 삭제하시겠습니까?");
								var ansNum = $(this).data("no");
								if(result){
									answerService.remove(ansNum, function(result) { 
										showList(1);
								});
								}else{
								    return;
								}
							}); 		
				});
				
			}
			
			let ansBtn = $("#ansBtn");
			ansBtn.on("click", function(e) {
				if($("#ansContent").val() == null || $("#ansContent").val() == "") {
					alert("답변 내용을 입력해주세요.");
				} else {
					var answer = {ansContent:$("#ansContent").val(), lessorId:lessorId, iqNum : iqNum };
					answerService.add(answer, function(result) { 
						showList(1); 
						$("#ansContent").val('');
					});
				}
			});
			
			if(lessorId == "" || lessorId == null ) {
				$("#ansContent").attr("disabled", "").attr("value", " 중개인만 답변 작성이 가능합니다 :(");
				$("#ansBtn").attr("disabled", "");
			}
			
		});
		
		
		
		
		/* $("#ansBtn").click(function() {
			
			let homeInqAnswer = { "iqNum" : iqNum, "ansContent" : $("#ansContent").val() };
			
			$.ajax({
				url: "/inqury/answer/register",
				type: "POST",
				data: homeInqAnswer,
				success: function(data) {
					console.log(data);
				}
			});
			
		});
		 */
	</script>
	<script src="/js/inquryDetail.js" ></script>
	
	
	
</body>
</html>