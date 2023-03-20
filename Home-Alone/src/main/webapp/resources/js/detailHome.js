$(document).ready(function() {
		let mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = { 
		    center: new kakao.maps.LatLng(latitude, longitude), // 지도의 중심좌표
		    level: 4 // 지도의 확대 레벨 
		};
		
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		var markerPosition  = new kakao.maps.LatLng(latitude, longitude); 	// 마커 위치
			
		var marker = new kakao.maps.Marker({
		    position: markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);

});


