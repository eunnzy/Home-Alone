<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="/js/board_get_reply.js"></script>
<script type="text/javascript" src="/js/board_getBT.js"></script>
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
		<!-- 조회시작 : 제목, 닉네임, 최근수정날짜 -->
	    <div class="card mb-3" style="background-color: white;">
	      <h3 class="card-header"><c:out value="${board.title}" /></h3>
	      <div class="card-footer text-muted">
	        <span><c:out value="${board.nickname}" /></span>
	        <span class="float-end">최근 수정일 : <fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate}" /></span>
	      </div>
	      <!-- 파일 업로드 -->
	      <div id="uploadfile"><ul></ul></div>
	      <!-- 내용 -->
	      <div class="card-body">
	        <p class="card-text" style="color: black;"><c:out value="${board.content}" /></p>
	      </div><br>
	      <!-- 이미지 업로드 -->
	      <div id="uploadimg"></div>
	        <!-- hidden -->
	      	<input type="hidden" name="bno" value="<c:out value="${board.bno}" />" >
	      	
	      <!-- 조회수, 좋아요 -->
	      <div class="card-body"> 	
	      <span><c:out value="${board.views}" /> 조회</span>
	      <form><span class="float-end" id="like"></span></form>
	      </div>
	    </div>
	    
	    <!-- 댓글 -->
	    <div class="card card-body" style="background-color: white;">
	       <div class = "chat"></div>
	       <!-- 댓글 작성 창 -->
		   <div><br></div>
	       <div class="input-group mb-3 replyreg">
	         <input type="text" class="form-control" aria-label="Recipient's username" aria-describedby="button-addon2" style="80%" name="reply" id="reply" required>
	         <button class="btn btn-secondary" type="button" id="replyregBT">등록</button>
	       </div>
	    </div><div><br></div>

	    <!-- 하단 버튼 -->
        <span><button type="button" class="btn btn-info" id="BackBT">목록으로</button></span>
        <span class="float-end"><div class="btn-group" role="group" aria-label="Basic example">
        <button type="button" class="btn btn-secondary" id="modifyBT">수정</button>
	    <button type="button" class="btn btn-secondary" id="deleteBT">삭제</button>
	    </div></span>

 	    <!-- 페이지이동 Form -->
	    <form id = "operForm" action="/community/modify" method="get" >
	   	 <input type="hidden" id="bno" name="bno" value="<c:out value="${board.bno}" />" >
 	   	 <input type="hidden" name="pageNum" value="<c:out value="${cri.pageNum }" />" >
	   	 <input type="hidden" name="amount" value="<c:out value="${cri.amount }" />"  >
	   	 <input type="hidden" name="keyword" value="<c:out value="${cri.keyword }" />"  >
	   	 <input type="hidden" name="type" value="<c:out value="${cri.type }" />"  > 
	    </form> 
	    <div><br><br></div>
       </div>
	
	<footer>
    	<jsp:include page="../footer.jsp"></jsp:include>
    </footer>
    
    <!-- 자바스크립트 -->
    <script type="text/javascript">
    // 파일 업로드 
    $(document).ready(function() {
		(function() {
			var bno = '<c:out value="${board.bno}" />';
			$.getJSON("/community/getAttachList", {bno:bno}, function(arr) {
				console.log("arr" + arr);
				
				var imgstr = "";
				var filestr = "";
				
				$(arr).each(function(i, attach){
					if(attach.fileType){
						var fileCallPath = encodeURIComponent(attach.uploadPath + "/" + attach.uuid + "_" + attach.fileName);
						imgstr += "<div data-path='" + attach.uploadPath + "' data-uuid='" + attach.uuid + "' data-filename='" + attach.fileName + "' data-type='" + attach.fileType + "'>";
						imgstr += "<img src='/community/display?fileName=" + fileCallPath + "'>";
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
    <script type="text/javascript">
    // 좋아요 체크 
    $(document).ready(function() {
    	var likeval = ${like};
    	var bnoValue = '<c:out value="${board.bno}" />';
    	var member = '<c:out value="${imcha.imchaId}" />';
		var str = "";
		
		if(member == '' || member == null) {
			str += "<c:out value='${board.likes}' /> 좋아요";
			$("#like").html(str); 
		} else {
			if (likeval > 0) {
				str = "<img src='/icon/like.png' class='likeBT'>";
				$("#like").html(str); 
				$(".likeBT").on("click", function(e) {
					var result = confirm("좋아요를 취소하시겠습니까?");
					if(result){
						replyService.likeDown({bno:bnoValue, userid:member}, function(data) { 
							location.reload();
					});
					}else{
					    return;
					}
				});
			} else {
				str = "<img src='/icon/nonlike.png' class='likeBT'>";
				$("#like").html(str); 
				$(".likeBT").on("click", function(e) {
					replyService.likeUp({bno:bnoValue, userid:member}, function(result) {
						location.reload();
					});
				});
			}
		}
    });
    </script>
    <script type="text/javascript">
    $(document).ready(function() {
		var bnoValue = '<c:out value="${board.bno}" />';
		var replyUL = $(".chat");
		var member = '<c:out value="${imcha.nickname}" />';
		
			// 댓글 목록 보여주기  
			showList(1);
			function showList(page) { 
				replyService.getList({ bno:bnoValue, page: page||1 }, 
						function(list) { var str="";  if(list == null || list.length == 0) { replyUL.html(""); return; }
						for (var i = 0, len = list.length || 0; i < len; i++) {
							str += "<div class='replydiv'> <h6 class='card-subtitle mb-2 text-muted' data-rno='" + list[i].rno + "'>";
							str += "<span>" + list[i].replyer + "</span>";
							str += "<span class='float-end'>" + replyService.displayTime(list[i].updateDate) + "</span></h6>";
							str += "<p class='card-text'>" + list[i].reply + "</p>";
							if (list[i].replyer == member) {
								str += "<div class='float-end'>";
								str += "<button type='button' class='btn btn-primary btn-sm float-end delBT' data-no='" + list[i].rno + "'>삭제</button>"; 
								str += "</div><br><br></div>";
							}
						}
						replyUL.html(str);
						
						// 댓글 삭제 
						$(".delBT").on("click", function(e) {
							var result = confirm("댓글을 삭제하시겠습니까?");
							var no = $(this).data("no");
							if(result){
								replyService.remove(no, function(result) { 
									showList(1);
							});
							}else{
							    return;
							}
						}); 		
			});
			}
			// 댓글 입력 
			var regBt = $("#replyregBT");
			regBt.on("click", function(e) {
				if($("#reply").val() == null || $("#reply").val() == "") {
					alert("댓글 내용을 입력해주세요.");
				} else {
					var reply = {reply:$("#reply").val(), replyer:member, bno:bnoValue};
					replyService.add(reply, function(result) { 
						showList(1); 
						$("#reply").val('');
					});
				}
			});
		// 로그인 여부에 따른 버튼 
	 	var imchanick = '<c:out value="${imcha.nickname}" />';
	 	var boardnick = '<c:out value="${board.nickname}" />';
		if(imchanick != boardnick || imchanick == '' || imchanick == null) {
			$("#modifyBT").hide();
			$("#deleteBT").hide();
		} else {
			$("#modifyBT").show();
			$("#deleteBT").show();
		}
		if(imchanick == '' || imchanick == null) {
			$("#reply").attr("disabled", "").attr("value", "     로그인 후 댓글 입력이 가능합니다 ;(");
			$("#replyregBT").attr("disabled", "");
		}
	});
	</script>
</body>
</html>