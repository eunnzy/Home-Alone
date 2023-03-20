$(document).ready(function() {
	history.replaceState({},null,null);
	
	$("#regBT").on("click", function() {
		self.location = "/community/register";
	});
	
	var actionForm = $("#actionForm");
	$(".page-item a").on("click", function(e) {
		e.preventDefault();
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.submit();
	});
	
	var searchForm = $("#searchForm");
	$("#searchForm button").on("click", function(e) {
		searchForm.find("input[name='pageNum']").val(1);
		e.preventDefault();
		searchForm.submit();
	});
});