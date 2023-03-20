<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="report-modal">
	<div class="report-wrap">
		<div class="report-title">매물 신고 하기</div>
		<div class="report-close"><i class='bi bi-x-lg'></i></div>
		<div class="report-content">
			<table class="table">
				<tr>
					<td style="color: white;">신고 유형</td>
					<td style="color: white;">
						<div class="form-check">
							<input class="form-check-input" type="radio" name="reportType" value="1">
							<label class="form-check-label" >등록된 정보가 일치하지 않음</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="reportType" value="2">
							<label class="form-check-label" >이미 계약이 완료된 매물임</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio"name="reportType" value="3">
							<label class="form-check-label" >기타</label>
						</div>
					</td>
				</tr>
				<tr>
					<td style="color: white;"> 신고 내용 </td>
					<td> <textarea class="form-control"  name="reportContent" id="reportContent" style="height: 200px" placeholder="신고 유형이 기타인 경우 신고 이유를 작성해주세요"></textarea>
				</tr>
			</table>
		</div>
		<div class="mb-3">
			<input class="form-check-input" type="checkbox" id="agreeCheck"> 
			<label class="form-check-lable" style="color: white">위 내용에 동의 합니다</label>
		</div>
		<div class="d-grid gap-3 mx-auto">
			<button type="button" class="btn btn-md btn-success" id="reportBtn">신고하기</button>
		</div>
	</div>
</div>