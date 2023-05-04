<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<style>
body {
overflow-x:hidden;
}
.masthead {
  position: relative;
  background: url("/img/houseIndex.jpg") no-repeat right center;
  background-size: cover;
  padding-top: 8rem;
  padding-bottom: 8rem;
  color: black;
}
.masthead:before {
  content: "";
  position: absolute;
  background-color: #d9e3f1;
  height: 100%;
  width: 100%;
  top: 0;
  left: 0;
  opacity: 0.7;
}
.masthead h1, .masthead .h1 {
  font-size: 2rem;
}
@media (min-width: 768px) {
 .masthead {
    padding-top: 12rem;
    padding-bottom: 12rem;
  }
  .masthead h1, .masthead .h1 {
    font-size: 3rem;
  }
}
.input-wrap {
	display: flex;
}
.search-btn {
	margin-left: 3px;
	padding: 0.7rem 1rem;
	background-color: #b9cfef;
	border-radius: 5px;
	border: none;
}
.index-title{
	font-weight: 800;
}

.features-icons {
  padding-top: 7rem;
  padding-bottom: 7rem;
}

.features-icons .features-icons-item .features-icons-icon {
  height: 7rem;
}
.features-icons .features-icons-item .features-icons-icon i {
  font-size: 1.5rem;
}
.lead {
  
  font-weight: 300;
}
</style>
</head>
<body>
	 <header>
		<jsp:include page="header.jsp"></jsp:include>
 	 </header>
	
	 <section class="masthead">
            <div class="container position-relative">
                <div class="row justify-content-center">
                    <div class="col-xl-6 mt-5">
                        <div class="text-center text-white">
                            <h1 class="index-title mb-5">어떤 집을 찾으시나요?</h1>
                            <form id="searchForm" action="/home/searchHome" method="get">
                                <div class="row">
                                	<div class="input-wrap">
	                                    <div class="col">
	                                        <input class="form-control form-control-lg" id="search" type="text"  name="searchKeyword" placeholder="지역, 학교, 지하철역으로 검색" />
	                                    </div>
	                                    <div class="col-auto"><button class="search-btn" id="submitButton" type="검색">검색</button></div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
         
        <section class="features-icons">
           <div class="container">
                <div class="row">
                    <div class="col-6 text-center">
                            <img src="/img/mapImg.jpg " class="rounded mx-auto" width="300" height="300" >
                     </div>
                     <div class="col-6 mt-5">
                     	 <h2 class="mt-5">나에게 딱 맞는 매물찾기</h2>
                        <div class="lead mb-0">지도로 원하는 매물을 한 눈에 찾아보세요.</div>
                   		<div class="lead mb-0">혼자이기 때문에 더 고민되는 안전과 주변 환경까지</div>
                   		<div class="lead mb-0">모두 "나홀로집에"서 확인하세요.</div>
                   	</div>
                </div>
            </div>
        </section>
        
       <!--  <section class="features-icons text-center bg-light">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4">
                        <div class="features-icons-item mx-auto">
                            <div class="features-icons-icon d-flex"><img src></i></div>
                            <h3>Fully Responsive</h3>
                            <p class="lead mb-0">This theme will look great on any device, no matter the size!</p>
                        </div>
                    </div>
                </div>
            </div>
        </section> -->
        
        
       <footer>
			<jsp:include page="footer.jsp"></jsp:include>
 		</footer>
</body>
</html>