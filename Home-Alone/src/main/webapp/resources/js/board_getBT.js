$(document).ready(function() {
	var operForm = $("#operForm");
	
	$("#modifyBT").on("click", function(e) {
		e.preventDefault();
		operForm.attr("action", "/community/modify");
		operForm.submit();
	});
	
	$("#deleteBT").on("click", function(e) {
		e.preventDefault();
		var result = confirm("게시물 삭제 시, 기록된 댓글도 함께 삭제됩니다. 삭제 하시겠습니까?");
		if(result){
			operForm.attr("action", "/community/delete.do");
			operForm.submit();
		}else{
		    return;
		}
	});
	
	$("#BackBT").on("click", function(e) {
		e.preventDefault();
		operForm.find("#bno").remove();
		operForm.attr("action", "/community/list");
		operForm.submit();
	});
});