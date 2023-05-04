
$(".cateBtn").click(function(e) {
	e.preventDefault();
	
	let cateName = this.id;
	console.log(cateName);
	
	pageNum.val(1);
	
	
	if(cateName== "전체"){
				type.val('');
				keyword.val('');
				
			/* 	actionForm.find("input[name='type']").val('');
				actionForm.find("input[name='keyword']").val('');
			 */	
			}else {
				type.val('G');
				keyword.val(cateName);
			/* 	actionForm.find("input[name='type']").val('G');
				actionForm.find("input[name='keyword']").val(key); */
			}
			
					actionForm.submit();
	
});
