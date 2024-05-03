<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<title>Study List - DoDeveloper</title>
<meta content="" name="description" />
<meta content="" name="keywords" />

<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>

<!-- 부트스트랩 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<!-- select2 css cdn -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.9/css/select2.min.css"
	rel="stylesheet" />

<!-- include summernote css/js -->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">

<!-- Favicons -->
<link href="/resources/assets/img/favicon.png" rel="icon" />
<link href="/resources/assets/img/apple-touch-icon.png"
	rel="apple-touch-icon" />

<!-- Fonts -->
<link href="https://fonts.googleapis.com" rel="preconnect" />
<link href="https://fonts.gstatic.com" rel="preconnect" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;1,300;1,400;1,500;1,600;1,700;1,800&family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap"
	rel="stylesheet" />

<!-- Vendor CSS Files -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="/resources/assets/vendor/glightbox/css/glightbox.min.css"
	rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"
	rel="stylesheet" />
<link href="/resources/assets/vendor/aos/aos.css" rel="stylesheet" />

<!-- Template Main CSS File -->
<link href="/resources/assets/css/main.css" rel="stylesheet" />

<!-- =======================================================
  * Template Name: Append
  * Template URL: https://bootstrapmade.com/append-bootstrap-website-template/
  * Updated: Mar 17 2024 with Bootstrap v5.3.3
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	$(function() {

		$('.studyLang').select2({
			maximumSelectionLength : 3,
			placeholder : '언어 선택 (최대 3개)'
		});

		$('.summernote').summernote({
			placeholder : '스터디 목표와 모임 주기, 스터디 방식 등 자유롭게 스터디에 대해 소개해주세요.',
			tabsize : 3,
			height : 300,

		});

		//지도 검색 버튼을 클릭했을 때
		$("#searchMapBtn").click(function() {
			// 지도 검색한 값 가져오기
			let searchMap = $("#searchMap").val();
			console.log(searchMap);

			// 검색한 값 키워드로 장소를 검색
			ps.keywordSearch(searchMap, placesSearchCB);
		})

		// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
		infowindow = new kakao.maps.InfoWindow({
			zIndex : 1
		});

		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
			level : 5
		// 지도의 확대 레벨
		};

		// 지도를 생성합니다    
		map = new kakao.maps.Map(mapContainer, mapOption);

		// 장소 검색 객체를 생성합니다
		var ps = new kakao.maps.services.Places();

	});

	// 키워드 검색 완료 시 호출되는 콜백함수 입니다
	function placesSearchCB(data, status, pagination) {

		if (status === kakao.maps.services.Status.OK) {

			// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
			// LatLngBounds 객체에 좌표를 추가합니다
			var bounds = new kakao.maps.LatLngBounds();

			for (var i = 0; i < data.length; i++) {
				displayMarker(data[i]);
				bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
			}

			// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
			map.setBounds(bounds);
		}
	}

	let mapX = '';
	let mapY = '';
	let mapName = '';

	// 지도에 마커를 표시하는 함수입니다
	function displayMarker(place) {

		// 마커를 생성하고 지도에 표시합니다
		var marker = new kakao.maps.Marker({
			map : map,
			position : new kakao.maps.LatLng(place.y, place.x)
		});

		// 마커에 클릭이벤트를 등록합니다
		kakao.maps.event.addListener(marker, 'click', function() {
			// 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
			infowindow.setContent('<div style="padding:5px;font-size:12px;">'
					+ place.place_name + '</div>');
			infowindow.open(map, marker);

			// 지도 검색바에 선택한 장소명 출력
			$("#searchMap").val(place.place_name);

			mapY = place.y;
			mapX = place.x;
			mapName = place.place_name;

			console.log(mapX, mapY, mapName);
		});
	}

	function getNewStudy() {
		let result = false;
		/* let stuWriter = '${loginMember.userId }';
		let stuTitle = $("#stuTitle").val();
		let stuContent = $("#stuContent").val();
		let stuLoc = mapName;
		let stuX = mapX;
		let stuY = mapY;
		let stuDate = $("#stuDate").val();
		let stuPers = $("#stuPers").val();
		let endDate = $("#endDate").val();
		let contactLink = $("#contactLink").val(); */
		
		let newStudyDTO = {
			"stuWriter" :  '${loginMember.userId }',
			"stuTitle" : $("#stuTitle").val(),
			"stuContent" : $("#stuContent").val(),
			"stuLoc" : mapName,
			"stuX" : mapY,
			"stuY" : mapX,
			"stuDate" : $("#stuDate").val(),
			"stuPers" : $("#stuPers").val(),
			"endDate" : $("#endDate").val(),
			"contactLink" : $("#contactLink").val()
		};
		
		console.log(newStudyDTO);

		$.ajax({
			url : '/study/insertStudy',
			type : 'post',
			data : JSON.stringify(newStudyDTO), //보내는 데이터
			dataType : 'text',
			async : 'false', //받아올 데이터가 있어야 파싱 가능.
			headers : { //서버에 보내지는 데이터의 형식
				"content-type" : "application/json"	
			},
			success : function(data) {
				console.log(data);
				
			}
		});
	}
</script>
<style>
.studyBasic { 
	--default-color: #212529; 
	--default-color-rgb: 255, 255, 255; 
	--background-color: #212529; 
	--background-color-rgb: 0, 0, 0;
	padding: 150px 0;
}

.note-editable {
	background-color: white;
	color: black;
}

.note-toolbar {
	background-color: #f8f9fa;
}
</style>
</head>

<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="../header.jsp"%>

	<main id="main">
		<!-- Basic Section - Study Page -->
		<section id="study" class="studyBasic">
			<div class="container" style="width: 80%">
				<div class="container">
					<h3 class="center text-center text-light">
						<b>🔥 개발 스터디 만들기</b>
					</h3>
				</div>

				<div class="container pt-5">


					<form action="/study/insertStack" method="post">
						<!-- 스터디 언어 선택 -->
						<div class="row mb-4">
							<div class="col-md-12">
								<div class="mb-2 text-light">
									<b>스터디 언어</b>
								</div>
								<select class="studyLang form-control" multiple="multiple"
									style="width: 100%" id="chooseStack" name="chooseStack[]">
									<!-- ajax로 stack테이블에 있는 애들 대려오기 -->
									<option value="1">React</option>
									<option value="2">javascript</option>
									<option value="3">Vue</option>
									<option value="4">Nextjs</option>
									<option value="5">Java</option>
									<option value="6">Spring</option>
									<option value="7">Kotlin</option>
									<option value="8">Swift</option>
									<option value="9">Flutter</option>
								</select>
							</div>
						</div>
					
					
					
					<input type="text" class="form-control" id="stuWriter" 
					value="${loginMember.userId }" hidden="true" />
					
					<input type="text" class="form-control" id="stuNo" name="stuNo" 
					value="${loginMember.userId }" hidden="true" />
					
					
						<div class="row mb-4">

							<!-- 모집인원 -->
							<div class="col-md-6 ">
								<div class="mb-2 text-light">
									<b>모집 인원</b>
								</div>
								<select id="stuPers" class="form-select">
									<option value="-1">인원 미정 ~ 10명 이상</option>
									<option value="인원 미정">인원 미정</option>
									<option value="1명">1명</option>
									<option value="2명">2명</option>
									<option value="3명">3명</option>
									<option value="4명">4명</option>
									<option value="5명">5명</option>
									<option value="6명">6명</option>
									<option value="7명">7명</option>
									<option value="8명">8명</option>
									<option value="9명">9명</option>
									<option value="10명">10명</option>
								</select>
							</div>

							<!-- 마감일 -->
							<div class="col-md-6">
								<div class="mb-2 text-light">
									<b>모집 마감일</b>
								</div>
								<input type="date" class="form-control" id="endDate" />
							</div>
						</div>

						<div class="row mb-4">

							<!-- 연락 방법 -->
							<div class="col-md-6">
								<div class="mb-2 text-light">
									<b>연락 방법</b>
								</div>
								<input id="contactLink" type="text" class="form-control" placeholder="오픈톡 링크" />
							</div>

							<!-- 진행 기간 -->
							<div class="col-md-6">
								<div class="mb-2 text-light">
									<b>진행 기간</b>
								</div>
								<select id="stuDate" class="form-select">
									<option value="-1">기간 미정 ~ 6개월 이상</option>
									<option value="기간 미정">기간 미정</option>
									<option value="1개월">1개월</option>
									<option value="2개월">2개월</option>
									<option value="3개월">3개월</option>
									<option value="4개월">4개월</option>
									<option value="5개월">5개월</option>
									<option value="6개월">6개월</option>
								</select>
							</div>
						</div>


						<!-- 제목 -->
						<div class="mb-2 text-light">
							<b>모집글 제목</b>
						</div>
						<input id="stuTitle" type="text" class="form-control mb-4" placeholder="제목 입력" />

						<!-- 내용 -->
						<textarea id="stuContent" class="note-editable summernote" style="background-color: white"></textarea>

					<!-- 카카오 지도 입력 부분 -->
					<div class="row mt-4">
						<div class="mb-2 text-light">
							<b>스터디 예정 장소</b>
						</div>
						<div class="col-md-10">
							<input type="text" class="form-control mb-4" id="searchMap"
								placeholder="스터디 예정 장소 입력" />
						</div>
						<div class="col-md-2">
							<input id="searchMapBtn" type="button" class="btn btn-secondary"
								value="검색" style="width: 100%" />
						</div>
					</div>

					<!-- 카카오 지도 출력 부분 -->
					<div id="map" style="width: 100%; height: 500px;"></div>

					<!-- 취소 글쓰기 버튼 -->
					<div class="row mt-4">
						<div class="col-md-6">
							<input type="reset" class="btn btn-outline-secondary" value="취소"
								style="width: 100%" />
						</div>
						<div class="col-md-6">
							<input type="submit" class="btn btn-secondary" value="글쓰기"
								style="width: 100%" onclick="return getNewStudy();" />
						</div>
					</div>
					</form>

				</div>
			</div>
		</section>
		<!-- End Basic Section -->
	</main>

	<%@ include file="../footer.jsp"%>

	<!-- Scroll Top Button -->
	<a href="#" id="scroll-top"
		class="scroll-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Preloader -->
	<div id="preloader">
		<div></div>
		<div></div>
		<div></div>
		<div></div>
	</div>

	<!-- Vendor JS Files -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="/resources/assets/vendor/glightbox/js/glightbox.min.js"></script>
	<script
		src="/resources/assets/vendor/purecounter/purecounter_vanilla.js"></script>
	<script
		src="/resources/assets/vendor/imagesloaded/imagesloaded.pkgd.min.js"></script>
	<script
		src="/resources/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
	<script src="/resources/assets/vendor/aos/aos.js"></script>
	<script src="/resources/assets/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="/resources/assets/js/main.js"></script>

	<!-- select2 javascript cdn -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.9/js/select2.min.js"></script>
	<!-- summernote -->
	<script
		src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	<!-- kakao map api -->
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=60e5e9d6a47834eb03c485008a9e14a3&libraries=services"></script>
</body>
</html>
