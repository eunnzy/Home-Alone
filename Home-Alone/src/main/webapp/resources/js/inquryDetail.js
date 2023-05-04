$(document).ready(function() {
	
	/*$.ajax({
		url: "/inqury/answer/list",
		type: 'POST',
		data: {"iqNum" : $("#iqNum").val()},
		success: function(data) {
			console.log(data);
			
			let answerHtml = "";
			let answerList = $(".answer-list");
			
			for(let i=0; i<data.length; i++) {
				answerHtml += "<tr><td>"+ data[i].lessorId  +"</td></tr>";
				answerHtml += "<tr><td>"+ data[i].ansContent  +"</td></tr>";
			}
			
			answerList.append(answerHtml);
		}
		
	});*/
	
})

var answerService = (function() {
	// 댓글 등록 
	function add(answer, callback, error) {
		console.log("add reply.......");
		
		$.ajax({
			type : 'post', 
			url : '/inqury/answer/register', 
			data : JSON.stringify(answer), 
			contentType : "application/json; charset=utf-8", 
			success : function(result, status, xhr) { if(callback) { callback(result); }},
			error : function(xhr, status, er) { if(error) { error(er); }}
		})
	}
	
	// 답변 목록 
	function getList(param, callback, error) {
		var iqNum = param.iqNum;
		// var page = param.page || 1;
		$.getJSON("/inqury/answer/" + iqNum + ".json", function(data) { if(callback) {  callback(data); } })
		.fail(function(xhr, status, err) { if(error) { error(); }});
	}
	
	// 답변 삭제 
	function remove(ansNum, callback, error) {
		$.ajax ({
			type : 'delete',
			url : '/inqury/answer/delete/' + ansNum,
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
		
	
	return { add:add, getList:getList, remove:remove, update:update, get:get, displayTime:displayTime};
})();