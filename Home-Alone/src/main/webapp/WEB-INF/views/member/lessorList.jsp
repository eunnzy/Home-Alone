<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
      <link href="/css/bootstrap.min.css" rel="stylesheet"></link>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
        <div class="bs-docs-section row">
          <div class="col-lg-12">
            <br><br>
              <h1 id="tables" style="text-align: center;">중개인 목록</h1>
              <br><br>
              
              <div class="bs-component">
<table class="table table-hover" style="text-align: center;">
<tr>
	<th scope="col" style="width: 15%;">아이디</th>
	<th scope="col" style="width: 15%;">이름</th>
	<th scope="col" style="width: 15%;">닉네임</th>
	<th scope="col" style="width: 15%;">중개소이름</th>
	<th scope="col" style="width: 15%;">중개소 등록번호</th>
	<th scope="col" style="width: 15%;">회원가입일자</th>
	<th scope="col" style="width: 10%;">현재상태</th>
	</tr>
	<c:forEach var="lessor" items="${list }" >
	<tr>
		<td>${lessor.lessorId }</td>
		<td>${lessor.name }</td>
		<td>${lessor.lessorNickName }</td>
		<td><a href="#" onclick="displayImg('${lessor.lessorId}')">${lessor.jgsName }</a></td>
		<td>${lessor.jgsNum }</td>
		<td><fmt:formatDate  value="${lessor.enrollDate }" pattern="yyyy-MM-dd" /></td>
	 	 <c:choose>
			  	<c:when test="${lessor.status == 0}">
			  	<td class="admin_list">
	                <button id ="${lessor.lessorId}" type="button" value="승인" class="success" style="background-color: skyblue;">승인 </button>
                </td>
			  	</c:when> 
			  	<c:when test="${lessor.status == 1}">
			  	<td class="admin_list">
	                <p data-user-id ="${lessor.lessorId}" type="text" value="승인완료" class="">승인완료</p>
                </td>
			  	</c:when>
			  </c:choose> 
	</tr>
	</c:forEach>	
</table>
</div>
</div>
</div>
</div>
<!--<script type="text/javascript">
//회원가입 승인
 $(function(){
	$('.success').click(function () {
		console.log('회원가입승인!');
	var successId = $('#lessorId').val();
	var thisRow = $(this).closest('tr');
	var addr = thisRow.find('td:eq(0)').find('input').val();
	var txt = thisRow.find('td:eq(1)').find('input').val();
	
	alert(addr + ", " + txt + "을 클릭함");
	console.log(successId);
	// 0 가입대기 , 1.가입승인
	
	$.ajax({
		type : 'post',
		url : "/admin/successId",
		data : {
    		lessorId : successId,
    	},
    	success : function(data){
    	},error : function(status, error) {
			console.log('에러발생!!');
			console.log(status, error);
		}
	});
	});
});
</script> -->

<!-- <script>

function displayImg(lessorId) {
	console.log(lessorId);
	const url = "/member/lessorImg?lessorId="+lessorId;
	window.open(url,"아이디찾기",'width=500px, height=700px, scrolbars=yes, resizeable=no');

}

$(function(){
$('.success').on('click', function() { 
	  
	  //현재 row의 정보 가져오기 
	  var thisRow = $(this).closest('tr'); 
	  
	  //주소 input 값 가져오기
	  var successId = thisRow.find('td:eq(0)').text();
	  
	 
	  console.log(successId);
	  
$.ajax({
	type : 'post',
	url : "/admin/successId",
	data : {
		lessorId : successId,
	},
	success : function(data){
		if(data == 1) {
			console.log(data)
			alert(successId + "님, 회원가입을 승인하였습니다.");
			thisRow.find('td:eq(6)').html("승인완료")
		} else {
			alert("실패하였습니다.")
		}
	}
});
});
});
</script> -->
<script src="/js/lessorList.js" ></script>
</body>
</html>

<%-- 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <!DOCTYPE html>
  <html lang="ko">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
	<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="../resources/css/admin_user.css">
    <title>어드민 회원관리</title>
    <script
	src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"
	integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css"
	integrity="sha512-aOG0c6nPNzGk+5zjwyJaoRUgCdOrfSDhmMID2u4+OIslr0GjpLKo7Xm0Ao3xmpM4T8AmIouRkqwj1nrdVsLKEQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

  </head>


  <body>

    <div class="member">
      <div class="member_title">
        <h2>회원관리</h2>
      </div>
      <br>
      <nav></nav>




      <div class="member_list" >
        <table class="admin_board_wrap" id="user-admin">
          <thead class="admin_boardList">
            <th class="admin_board_head">이름</th>
            <th class="admin_board_head">아이디</th>
            <th class="admin_board_head">가입일</th>
			<th class="admin_board_head">중개소이름</th>
			<th class="admin_board_head">중개소 등록번호</th>
			<th class="admin_board_head">가입상태</th>
            <th class="admin_board_head">가입승인</th>
          </thead>
          <tbody>
          <c:forEach var="lessor" items="${list}">
            <tr class="admin_board_content">
              <td class="admin_board_content_nm">${lessor.lessorId}</a> </td>
              <td class="admin_board_content_nm">${lessor.name}</td>
              <td class="admin_board_content_nm"><fmt:formatDate  value="${lessor.enrollDate }" pattern="yyyy-MM-dd" /></td>
              <td class="admin_board_content_nm">${lessor.jgsName}</td>
              <td class="admin_board_content_nm">${lessor.jgsNum}</td>
              <td class="admin_board_content_nm">${lessor.status }</td>
			  <c:choose>
			  	<c:when test="${lessor.status == 0}">
			  	<td class="admin_board_content_nm">
	                <button data-user-id ="${lessor.lessorId}" type="button" value="승인" class="appro">승인 </button>
	                <button data-user-id ="${lessor.lessorId}" type="button" value="거부" class="deni">거부</button>
                </td>
			  	</c:when>
			  	<c:when test="${lessor.status == 1}">
			  	<td class="admin_board_content_nm">
	                <button data-user-id ="${lessor.lessorId}" type="button" value="승인" class="userDrop">회원 추방</button>
                </td>
			  	</c:when>
			  	<c:when test="${lessor.status == 3}">
			  	 	<td>추방회원</td>
			  	</c:when>
			  	<c:when test="${lessor.status == 2 }">
			  		<td>승인거절회원</td>
			  	</c:when>
			  </c:choose>
            </tr>
            </c:forEach>
          </tbody>
        </table>


      </div>
    </div>


    <!-- 회원가입 승인 모달 -->
    <div class="modal_approve">
      <div class="po">
        <div class="check_listWrap">
          <div class="modal_appr">
            회원가입이 승인되었습니다.
            <div class="close_modal_btn approve-btn">
              <button class="modalAppro_end_btn">확인</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 회원가입 거부 모달 -->
    <div class="modal_deni">
      <div class="po">
        <div class="check_listWrap">
          <div class="modal_de">
             회원가입이 거부되었습니다.
            <div class="close_modal_btn">
              <button class="modal_deni_btn">확인</button>
            </div>
          </div>
        </div>
      </div>

    </div>

    <script>
    
    
      let str = '';
      // 모달 스크립트 
      $(function () {
    	  $("#user-admin").DataTable();
        // 게시판 모달
        $('.modal_boardList_admin').click(function () {
        	var board_id = $(this).data("userId");
        	console.log(board_id);
        	getList(true, board_id);
          $('.modal_list').fadeIn(500);
        }); // open modal end
        
        function getList(reset, board_id) {
        	
        	if(reset) {
        		str = '';
        	}
        	
        	$.ajax({
        		type : 'post',
        		url : '<c:url value ="/admin/getUserBoardList"/>',
                data : {
                	board_writer : board_id,
                	},
        		dataType : "json",
        		success : function(data) {
					for(var i = 1 in data){
						console.log(data[i].board_title);
						str += "<tr class='admin_boardM_content'>"
						str += "<td class='admin_boardM_nm'><a href='#'>"+data[i].board_no+"</a></td>"
						str += "<td class='admin_boardM_nm'><a href='#'>"+data[i].board_title+"</a></td>"
						str += "<td class='admin_boardM_nm'><a href='#'>"+timeStamp(data[i].board_regdate)+"</a></td>"
						str += "<td class='admin_boardM_nm'>"+data[i].board_hit+"</td>"
						str += "<td class='admin_boardM_nm'>"+data[i].board_like+"</td>"
						str += "</tr>"
					}
					$('#ListName').html(board_id + '님의 작성 게시물')
					$('#getBoardList').html(str); 
					$("#comment-admin").DataTable();
				},error : function(status, error) {
					console.log('에러발생!!');
					console.log(status, error);
				}
	          });//ajax 종료
		}
        
        
        $('.modal_list_end_btn').click(function () {
        	console.log('모달 닫기 이벤트 발생!');
          $('.modal_list').fadeOut();
        });
        
        
        // 댓글 모달
        $('.modal_reply_admin').click(function () {
          $('.modal_reply').fadeIn(500);
      		var comment_id = $(this).data("userId");
    		console.log(comment_id);
			getList2(true,comment_id);
        });
        
        function getList2(reset, comment_id) {
			if(reset){
				str ='';
			}
			
    		$.ajax({
				type :'post',
				url : '<c:url value ="/admin/getCommentList"/>',
				data : {
					com_writer : comment_id,
				},
				dataType :"json",
				success : function(data) {
					console.log('잘됨!')
					for(var i = 1 in data){
						console.log(data[i].com_no);
		                str += "<tr class='admin_reply_content'>"
		                str += "  <td class='admin_reply_con'><a href='#'>"+data[i].com_no+"</a></td>"
		                str += " <td class='admin_reply_con'><a href='#'>"+data[i].com_content+"</a></td>"
		                str += " <td class='admin_reply_con'>"+timeStamp(data[i].com_regdate)+"</td>"
		                str += " </tr>"
					}
					$('#commentList_box').html(str);
					$('#comment_name').html(comment_id +'님의 작성 댓글 ')
				
					
					
				},error : function(status, error) {
					console.log('에러발생!!');
					
					console.log(status, error);
				}
				
			});//ajax 종료
		}
        
   
        $('.modal_reply_end_btn').click(function () {
          $('.modal_reply').fadeOut();
        });
        // 마이페이지 모달
        $('.mypageModal').click(function () {
          $('.mypage_modal').fadeIn(500);
          var Id = $(this).attr('value');
          console.log(Id);
          // 아이디 값 불러옴 
          
          $.ajax({
        	type : 'post',
        	url: '<c:url value="/admin/getuserInfo" />',
        	data : {
        		id : Id,
        	},
        	dataType:"json",
        	success : function(data){
        		console.log(data.userName);
        		console.log(data.userId);
        		$("input[name='name']").val(data.userName);
        		$("input[name='id']").val(data.userId);
        		$("input[name='phone']").val(data.userPh);
        		$("input[name='addr1']").val(data.userAdr3);
        		$("input[name='addr2']").val(data.userAdr2);
        		$("input[name='addr3']").val(data.userAdr4);
        		$("input[name='addr_num']").val(data.userAdr1);
        		$(".memInfo").html(data.userName+"님의 회원 정보");
        	},error : function(status, error) {
				console.log('에러발생!!');
				console.log(status, error);
			}
        	
        	
          })//ajax 종료
        });
        $('.close').click(function () {
          $('.mypage_modal').fadeOut();
        });
        // 회원가입 승인
        $('.appro').click(function () {
        	const successId = $(this).data("userId");
        	console.log(successId);
        	// 0 가입대기 , 1.가입승인, 2. 가입거절
        	
        	$.ajax({
        		type : 'post',
        		url : '<c:url value="/admin/successId" />',
        		data : {
            		id : successId,
            	},
            	success : function(data){
            	},error : function(status, error) {
    				console.log('에러발생!!');
    				console.log(status, error);
    			}
            	
        	}); // 아작스 종료
          $('.modal_approve').fadeIn(500);
        });
        $('.modalAppro_end_btn').click(function () {
          $('.modal_approve').fadeOut();
        });
        
        
        // 회원가입 거부
        $('.deni').click(function () {
        	const failId = $(this).data("userId");
        	console.log(failId);
 
          
      	$.ajax({
    		type : 'post',
    		url : '<c:url value="/admin/failId" />',
    		data : {
        		id : failId,
        	},
        	success : function(data){
        	},error : function(status, error) {
				console.log('에러발생!!');
				console.log(status, error);
			}
    	});//아작스 끝
        $('.modal_deni').fadeIn(500);
        });
        $('.modal_deni_btn').click(function () {
          $('.modal_deni').fadeOut();
        });
      });
      //검색
      $('.search_onclick_submit').click(function () {
          /* alert('검색되니?')
          location.href = "" + "검색어 이름" + "" + "검색어 아이디";*/
          if($('.search-input').val()===''){
            alert('서비스 준비 중입니다..');
            return;
          }
        }
      );
      // 엔터키누를시 검색버튼 눌리기
      $('.search-input').keypress(function (e) {
        if (e.keyCode == 13) {
          //실행할 function작성
          alert("엔터 잘 실행되니?");
        }
      });
      // 가입승인 버튼 클릭시 회원가입승인
      $('.appro').click(function(){
      	const successId = $(this).data("userId");
    	console.log(successId);
    	// 0 가입대기 , 1.가입승인, 2. 가입거절
    	
    	$.ajax({
    		type : 'post',
    		url : '<c:url value="/admin/successId" />',
    		data : {
        		id : successId,
        	},
        	success : function(data){
        	},error : function(status, error) {
				console.log('에러발생!!');
				console.log(status, error);
			}
        	
    	}); // 아작스 종료
      $('.modal_approve').fadeIn(500);
      });
      // 가입거부 버튼 클릭시 요청 삭제
      $('.deni').click(function(){
        console.log('회원가입거부!');
        // 가입승인 거부됨 메일전송 서비스 만들면 좋을듯
      });
      
      $('.userDrop').click(function() {
		
    	  const dropId = $(this).data("userId");
        	console.log(dropId);
           	$.ajax({
        		type : 'post',
        		url : '<c:url value="/admin/dropId" />',
        		data : {
            		id : dropId,
            	},
            	success : function(data){
            	},error : function(status, error) {
      				console.log('에러발생!!');
      				
      				console.log(status, error);
      			}
  		}); //end ajax
	  });// end user Drop
	  
	  function timeStamp(millis) {
	      
	      const date = new Date(); //현재 날짜
	      //현재 날짜를 밀리초로 변환 - 등록일 밀리초 -> 시간차
	      const gap = date.getTime() - millis;
	      
	      let time; //리턴할 시간
	      if(gap < 60 * 60 * 24 * 1000) { //1일 미만인 경우
	         if(gap < 60 * 60 * 1000) { //1시간 미만일 경우
	            time = '방금 전';
	         } else { //1시간 이상일 경우
	            time = parseInt(gap / (1000 * 60 * 60)) + '시간 전';
	         }
	      } else { //1일 이상일 경우
	         const today = new Date(millis);
	         const year = today.getFullYear(); //년
	         const month = today.getMonth() + 1; //월
	         const day = today.getDate(); //일
	         const hour = today.getHours(); //시
	         const minute = today.getMinutes(); //분
	         
	         time = year + '년 ' + month + '월 ' + day + '일 ' + hour + '시 ' + minute + '분'; 
	         
	      }
	      return time;
	   }
    </script>
    </body>
    </html> --%>