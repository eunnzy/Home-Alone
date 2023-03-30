<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="qna-modal">
	<div class="qna-wrap">
		<div class="qna-title">문의 하기</div>
		<div class="qna-close"><i class='bi bi-x-lg'></i></div>
		<div class="qna-content">
			<table class="table">
				<tr>
					<td style="color: white;"> 이름 </td>
					<td> <input type="text" class="form-control" name="imchaId" id="imchaId" value="${imcha.imchaId }" readonly></td>
				</tr>
				<tr>
					<td style="color: white;"> 문의 제목 </td>
					<td> <input type="text" class="form-control" name="iqTitle" id="iqTitle"></td>
				</tr>
				<tr>
					<td style="color: white;"> 문의 내용</td>
					<td> <textarea class="form-control" style="min-height:200px; resize: none;" id="iqContent" name="iqContent"></textarea></td>
				</tr>
			</table>
		</div>
		<div class="d-grid gap-3 mx-auto">
			
			<button type="submit" class="btn btn-md btn-success" id="qnaBtn">문의하기</button>
		</div>
	</div>
</div>
