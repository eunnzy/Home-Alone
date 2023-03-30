<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<link href="/css/bootstrap.min.css" rel="stylesheet"></link>
<title>안전지도<title>
</head>
<body>
	<header>
		<jsp:include page="header.jsp"></jsp:include>
	</header>
	
	<div id="map">
	</div>
	
	<script type="text/javascript" src="hhttp://api.vworld.kr/req/wms?key=05F1F959-3C7C-3BB9-9585-00D34F13529C&domain=http://localhost:8080"></script>
	<script src="http://www.safemap.go.kr/mapapi/js/2DAPI/c/2dapi_common.js"></script>
	<script src="http://www.safemap.go.kr/mapapi/js/2DAPI/c/2dapi_open.js"></script>
	<script src="http://www.safemap.go.kr/mapapi/js/2DAPI/c/2dapi_example.js"></script>
	
</body>
</html>

<script>
$(document).ready(function() {
	let map = $("#map");
	
	
/* 	
	$.ajax({
		method: "GET",
		url: "http://www.safemap.go.kr/openApiService/wms/getLayerData.do?apikey=GNT2EV0N-GNT2-GNT2-GNT2-GNT2EV0NXI
		data : {name:'범죄주의구간(성폭력)', layername:'A2SM_CRMNLHSPOT_TOT', styles:'A2SM_CrmnlHspot_Tot_Rape'}
		
		
	});
 */
	
 	addWmsLayer();
 
	function addWmsLayer(){
	  	param = {name:"범죄주의구간(성폭력)",
	 		serverUrl:"http://www.safemap.go.kr/openApiService/wms/getLayerData.do?apikey=GNT2EV0N-GNT2-GNT2-GNT2-GNT2EV0NXI",
			layername:"A2SM_TFCACDSTTUS_BIG,A2SM_TFCACDHSPOT_NEW",
	 		styles:"A2SM_TFCACDSTTUS_BIG,A2SM_TFCACDHSPOT_NEW"};
	   	var wmsLayer = new OpenLayers.Layer.WMS( param.name, param.serverUrl,
						      {   layers: ''+param.layername, 
	      						   styles:param.styles,
	      						   format: 'image/png', 
	      						   exceptions:'text/xml',
	      						   transparent: true
	               },
	    					  {isBaseLayer: false}
	);	
	map.addLayer(wmsLayer);

	
	
	
	
	}

});


</script>