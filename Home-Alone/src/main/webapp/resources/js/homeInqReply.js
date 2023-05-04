console.log("Reply Module....");

var replyService = (function() {
	// 댓글 등록 
	function add(reply, callback, error) {
		console.log("add reply.......");
		
		$.ajax({
			type : 'post', 
			url : '/replies/new', 
			data : JSON.stringify(reply), 
			contentType : "application/json; charset=utf-8", 
			success : function(result, status, xhr) { if(callback) { callback(result); }},
			error : function(xhr, status, er) { if(error) { error(er); }}
		})
	}
	
	// 댓글 목록 
	function getList(param, callback, error) {
		var bno = param.bno;
		var page = param.page || 1;
		$.getJSON("/replies/pages/" + bno + "/" + page + ".json", function(data) { if(callback) { callback(data); } })
		.fail(function(xhr, status, err) { if(error) { error(); }});
	}
	
	// 댓글 삭제 
	function remove(rno, callback, error) {
		$.ajax ({
			type : 'delete',
			url : '/replies/' + rno,
			success : function(deleteResult, status, xhr) { if(callback) { callback(deleteResult); }},
			error : function(xhr, status, er) { if(error) { error(er); } }
		});
	}
	
	// 댓글 수정 
	function update(reply, callback, error) {
		console.log("RNO : " + reply.rno);
		$.ajax ({
			type : 'put',
			url : '/replies/' + reply.rno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) { if(callback) { callback(result); }},
			error : function(xhr, status, er) { if(error) { error(er); }}
		});
	}
	
	// 댓글 조회
	function get(rno, callback, error) {
		$.get("/replies/" + rno + ".json", function(result) { if(callback) { callback(result); } })
		.fail(function(xhr, status, err) { if(error) { error(); }});
	}
	
	// 시간 처리 
	function displayTime(timeValue) {
		var today = new Date();
		var gap = today.getTime() - timeValue;
		var dateObj = new Date(timeValue);
		var str= "";
		
		if(gap < (1000 * 60 * 60 * 24)) {
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();
			
			return [(hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi, ':', (ss>9? '':'0') + ss].join('');
		} else {
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1; //getMonth는 zero-based
			var dd = dateObj.getDate();
			return [yy, '/', (mm>9 ? '': '0') + mm, '/', (dd>9? '':'0') + dd].join('');
		}
	};
		
	// 좋아요 
	function likeUp(mylike, callback, error) {
		console.log(mylike.bno + "번 게시물 좋아요 ");
		$.ajax ({
			type : 'post', 
			url : '/replies/likeUp',  
			data : JSON.stringify(mylike),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) { if(callback) { callback(result); }},
			error : function(xhr, status, er) { if(error) { error(er); }}
		});
	}
	
	// 좋아요 취소 
	function likeDown(mylike, callback, error) {
		console.log(mylike.bno + "번 게시물 좋아요 취소 ");
		$.ajax ({
			type : 'post', 
			url : '/replies/likeDown',  
			data : JSON.stringify(mylike),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) { if(callback) { callback(result); }},
			error : function(xhr, status, er) { if(error) { error(er); }}
		});
	}
	
	return { add:add, getList:getList, remove:remove, update:update, get:get, displayTime:displayTime, likeDown:likeDown, likeUp:likeUp };
})();

