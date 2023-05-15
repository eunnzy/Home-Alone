<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<link href="/css/detailHome.css" rel="stylesheet"></link>
</head>
<div class="reserv-modal">
	<div class="reserv-wrap">
		<div class="reserv-title">집 방문 예약</div>
		<div class="reserv-close">
			<i class='bi bi-x-lg'></i>
		</div>
		<div class="reserv-content">
			<table class="table">
						<tr>
							<td style="color: white;">이름</td>
							<td><input type="text" class="form-control" name="nickname" value="${imcha.nickname }" readonly></td>
						</tr>
						<tr>
							<td style="color: white;">방문 날짜</td>
							<td><input type="date" class="form-control" id="revDate" name="revDate"></td>
						</tr>
					</table>
					<table class="timeTable">
						<tr class="timeSelect">
							<td style="color: white; padding-right: 25px;">방문 시간</td>
							<td><input type="radio" id="revTime" name="revTime" value="10:00"><label for="revTime">10:00</label></td>
							<td><input type="radio" id="revTime2" name="revTime" value="11:00"><label for="revTime2">11:00</label></td>
							<td><input type="radio" id="revTime3" name="revTime" value="12:00"><label for="revTime3">12:00</label></td>
						</tr>
						<tr class="timeSelect">
							<td style="color: white;"></td>
							<td><input type="radio" id="revTime4" name="revTime" value="13:00"><label for="revTime4">13:00</label></td>
							<td><input type="radio" id="revTime5" name="revTime" value="14:00"><label for="revTime5">14:00</label></td>
							<td><input type="radio" id="revTime6" name="revTime" value="15:00"><label for="revTime6">15:00</label></td>
						</tr>
						<tr class="timeSelect">
							<td style="color: white;"></td>
							<td><input type="radio" id="revTime7" name="revTime" value="16:00"><label for="revTime7">16:00</label></td>
							<td><input type="radio" id="revTime8" name="revTime" value="17:00"><label for="revTime8">17:00</label></td>
							<td><input type="radio" id="revTime9" name="revTime" value="18:00"><label for="revTime9">18:00</label></td>
						</tr>
					</table>
		</div>
		<div class="d-grid gap-3 mx-auto">
			<input type="hidden" id="homeNum" name="homeNum" value="${home.homeNum}">
			<button type="button" class="btn btn-md btn-success" id="reservBtn">예약하기</button>
		</div>

	</div>
</div>