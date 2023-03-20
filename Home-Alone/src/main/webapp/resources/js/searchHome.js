var map;
let homeMarker = [];	// 매물 표시할 마커를 담을 배열
let categoryStatus=false;


$(document).ready(function() { 	// 처음 페이지 들어왔을 때 현재 위치 내 매물 리스트 보여주기 위해*/
	let mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
	    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	    level: 3 // 지도의 확대 레벨 
	}; 
	
	map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	let zoomControl = new kakao.maps.ZoomControl(); // 지도 줌 컨트롤러
	map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
	
	
	/*------------ searchHome 페이지 들어갈 때 사용자의 현재 위치 받아와서 그 위치에 있는 매물 리스트 보여줌 -------------*/
	if (navigator.geolocation) {	 // GeoLocation을 이용해서 접속 위치를 얻어옵니다
		navigator.geolocation.getCurrentPosition(function(position) {
			var lat = position.coords.latitude, // 위도
			        lon = position.coords.longitude; // 경도
			var locPosition = new kakao.maps.LatLng(lat, lon); // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
			         // 인포윈도우에 표시될 내용입니다
			map.setCenter(locPosition);	 
			
/*			if(searchKeyword !== "") {
				console.log("searchKeyword 실행")
				var ps = new kakao.maps.services.Places(); 	// 장소 검색 객체 
				ps.keywordSearch(searchKeyword, placesSearchCB);
				return;
			}
		*/
			
			getHomeInBounds();
			kakao.maps.event.addListener(map, 'idle', getHomeInBounds);
			
		});
	} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
	    var locPosition = new kakao.maps.LatLng(33.450701, 126.570667);
	}
	
	
});

var ps = new kakao.maps.services.Places(); 	// 장소 검색 객체 


// 지도 경계에서의 매물 정보 가져오기
function getHomeInBounds() {
	let homeList = $(".home-card");  
		homeList.remove();	// 기존의 리스트 목록 삭제
	    removeMarker();	// 기존의 마커 제거
	
	let bounds = map.getBounds();	// 지도 범위 가져오기
	
	// 북동쪽 위도 경도
	let neLat = bounds.getNorthEast().getLat();
	let neLng = bounds.getNorthEast().getLng();
	
	// 남서쪽 위도, 경도
	let swLat = bounds.getSouthWest().getLat();
	let swLng = bounds.getSouthWest().getLng();
	
	
	console.log("neLat : " + neLat);
	console.log("neLng : " + neLng);
	console.log("swLat : " + swLat);
	console.log("swLng : " + swLng);
	
	let mapBounds = {"neLat" : neLat,
    				"neLng" : neLng,
    			 	"swLat" : swLat,
    			 	"swLng" : swLng };
	
	let mapCenter = map.getCenter();
	// 지도 경계까지의 매물 정보들을 서버에 요청.
	$.ajax({
		url: '/home/homeInBounds',
    	data : mapBounds,
    	type : 'Post',
    	dataType : 'json',
    	success: function(data) {	
    		if(data != null) {
    			map.setCenter(mapCenter);	// 주어진 영역이 화면 안에 전부 나타날 수 있도록 지도의 중심 좌표와 확대 수준을 설정
				console.log(data.length);
				homeData = JSON.parse(JSON.stringify(data));	// 필터 검색을 위한 객체 저장
				console.log("data 값 : " + homeData);
				if(filterBtnStatus != true) {
					let homeList = $(".home-card");  
						homeList.remove();	// 기존의 리스트 목록 삭제
					    removeMarker();	// 기존의 마커 제거
								
					for(let i=0; i<data.length; i++) {
						displayHomeList(data[i], i);	// 매물 정보 리스트 출력
					}
				}else {
					filterBtnStatus = false;
				}
			}else {
					alert("해당하는 위치에 매물 정보가 없습니다.");
				}
	    	}
	});
}


// 필터 검색
function checkFilter(data) {
	let typeStatus = false;
	
	if(homeTypeCheck.length == 0)
		typeStatus = true;
	
	for(let i=0; i<homeTypeCheck.length; i++) {
		if(data.homeType == homeTypeCheck[i]) {
			typeStatus = true;
		}
	}
	
	if(typeStatus == false) return typeStatus;
		
	let rentStatus = false;
	
	if(rentTypeCheck.length == 0)
		rentStatus = true;
		
	for(let i=0; i<rentTypeCheck.length; i++) {
		if(data.rentType == rentTypeCheck[i])
			rentStatus = true;
	}
	
	if(rentStatus == false) return rentStatus;
	
	// 돈 범위 체크
	if((data.deposit/10000) > deposit)
		return false;
		
	if((data.monthly)/10000 > monthly)
		return false;	
	
	// 옵션 리스트 체크
	let optionCount = 0;
	for(let i=0; i<optionCheck.length; i++) {
		for(let j=0; j<data.optionList.length; j++) {
			if(data.optionList[j] == optionCheck[i]) {
				optionCount++;	
			}
		}	
	}
	
	if(optionCheck.length != optionCount) { // 하나라도 같지 않으면 false
		return false;
	}
	// 추가 정보
	let addInfoCount=0;
	for(let i=0; i<addInfoCheck.length; i++) {
		switch(addInfoCheck[i]) {
		case "반려동물":
			data.pet.includes("가능");
			addInfoCount++;
			break;
		case "엘리베이터":
			data.elevator.includes("가능");
			addInfoCount++;
			break;
		case "주차":
			if(data.parking >= 1) 
				addInfoCount++;
			break;
		case "발코니":
			data.balcony.includes("가능");
			addInfoCount++;
			break;
		default:
			break;
		}
	}
	
	if(addInfoCheck.length != addInfoCount) {
		return false;
	}
	
	return true;
}


// 매물 리스트 출력하기
function displayHomeList(data, i) {
	console.log("displayHomeList " + data);
	let homeDiv = $(".home-list");
	
	let placePosition = new kakao.maps.LatLng(data.latitude, data.longitude);
    let overlayContent = getOverlay(data);
	let marker = addMarker(placePosition, i, overlayContent);
    let homeItem = getHomeItem(data);
	homeDiv.append(homeItem);	
}


// 매물 정보 추가 
function getHomeItem(data) {
	homeImgFile = data.homeImg.homeImgPath + "/" + data.homeImg.homeImgName;	// 사진경로
	
	let monthly = convertMoney(data.monthly);
	let deposit = convertMoney(data.deposit);
	let adminCost = convertMoney(data.adminCost);
	
	let homeStr = "";
	homeStr += "<div class='home-card' onclick='detailHome("+ data.homeNum + ")'> <div class='home-img-wrap'>"
	homeStr += "<img src='/home/getHomeImg?homeImgFile=" + homeImgFile + "'> </div>"
	homeStr += "<div class='home-content-wrap text-truncate'> <h4>" + data.rentType + " " ;
	if(data.rentType == "월세")
		homeStr  += deposit +  "/" + monthly + "</h4>";
	else 			
		homeStr  += deposit +  "</h4>";
	homeStr += "<p>" + data.homeType +" | " + data.homeArea + "평 | " + data.floor +"층" + "</p>";
	homeStr += "<p>"+ data.addr2  +"</p>"	
	homeStr += "<p>"+ data.homeTitle  +"</p> </div> </div>"	
	
	return homeStr;
}


// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, overlay) {
    var imageSrc = '/icon/homeMarker.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(40,40),  // 마커 이미지의 크기
        imgOptions =  {
            // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
            marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage 
        });

    marker.setMap(map); // 지도 위에 마커를 표출합니다
    homeMarker.push(marker);  // 배열에 생성된 마커를 추가합니다

	 var overlay = new kakao.maps.CustomOverlay({
	    content: overlay,
	    map: map,
	    position: marker.getPosition()       
	});
	
	overlay.setMap(null);

	kakao.maps.event.addListener(marker, 'mouseover', function() {
		overlay.setMap(map);
    });

    kakao.maps.event.addListener(marker, 'mouseout', function() {
         overlay.setMap(null);
    });

    return marker;
}

// 검색 키워드 입력 후 검색 버튼 클릭시 
$("#searchBtn").click(function() {
	let searchInput = $("#searchInput").val();	// 검색 키워드 값 
	
	if (!searchInput.replace(/^\s+|\s+$/g, " ")) {	// 어떠한 검색 키워드 값도 입력 안 됐을 때
		alert("검색어를 입력해주세요!");
		return false;
	}
	
	ps.keywordSearch(searchInput, placesSearchCB);
});

// 검색 이벤트
$("#searchInput").on("keyup",function(key){
    if(key.keyCode==13) {
		ps.keywordSearch($("#searchInput").val(), placesSearchCB);
	}
});


// 장소검색이 완료됐을 때 호출되는 콜백함수
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {
		console.log(categoryStatus);	
		if(categoryStatus == true) {
			categoryDisplay(data);
		}else {
	       	displayPlaces(data);	// 검색한 장소 찾기
		}
			
    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        alert('검색 결과가 존재하지 않습니다.');
        return;
    } else if (status === kakao.maps.services.Status.ERROR) {
        alert('검색 결과 중 오류가 발생했습니다.');
        return;
    }
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {
	console.log("displayPlace : " + places);		
	
 	var bounds = new kakao.maps.LatLngBounds(); 	// 지도 범위 재 설정
    var placePosition = new kakao.maps.LatLng(places[0].y, places[0].x);
    bounds.extend(placePosition);
 	
    // 검색된 장소 위치를 기준으로 지도 범위를 재설정 & 매물 정보가져오기.
  	map.setBounds(bounds);
  	map.setLevel(3);
  	getHomeInBounds();	
}


// 마커 오버레이 생성
function getOverlay(data) {
	let homeImg =  data.homeImg.homeImgPath + "/t_" + data.homeImg.homeImgName;	// 사진경로
	console.log(homeImg);
	
	
	var content = '<div class="overlay-wrap">' + 
            '    <div class="overlay-info">' + 
            '        <div class="title">' +  data.homeTitle +
            '        </div>' + 
            '        <div class="overlay-body">' + 
            '            <div class="img-wrap">' +
            '                <img src="/home/getHomeImg?homeImgFile=' + homeImg + '" width="73" height="71">' +
            '           </div>' + 
            '            <div class="desc text-truncate">' + 
            '                <div>'+ data.rentType + ' | '  + data.homeType + ' | ' +  data.homeArea + '평' +'</div>' + 
            '                <div>'+ data.addr2 + '</div>' + 
            '            </div>' + 
            '        </div>' + 
            '    </div>' +    
            '</div>';

	return content;
}


// 매물 마커 삭제
function removeMarker() {
    for (var i = 0; i < homeMarker.length; i++ ) {
        homeMarker[i].setMap(null);
    }   
    homeMarker = [];
}

// 돈(관리비, 월세, 보증금 등) 단위 변환
function convertMoney(money) {	
	let convert = ""; 
	money = money / 10000;
	if(money == 0) {
		convert = "없음";
	}else if(money >= 10000) {
		convert += Math.floor(money/10000) + "억";
		if(money % 10000 != 0 ) {
			money = money % 10000;
			convert +=  money + "만";
		}
	}else {
		convert = money + "만";
	}
	
	return convert;
}

// 상세보기 페이지 이동
function detailHome(homeNum) {
	location.href = "/home/detail?homeNum=" + homeNum;
}


