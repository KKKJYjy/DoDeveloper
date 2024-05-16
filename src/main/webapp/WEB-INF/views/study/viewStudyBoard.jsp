<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<title>View Study - DoDeveloper</title>
<meta content="" name="description" />
<meta content="" name="keywords" />

<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>

<!-- 부트스트랩 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">

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



<!-- 스터디 writeStudyBoard css 파일 -->
<link href="/resources/assets/css/study/writeStudyBoard.css"
	rel="stylesheet" />

<script>

	$(function() {
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new kakao.maps.LatLng(${studyList.stuX }, ${studyList.stuY }), // 지도의 중심좌표
			level : 3
		// 지도의 확대 레벨
		};

		var map = new kakao.maps.Map(mapContainer, mapOption);

		// 마커가 표시될 위치입니다 
		var markerPosition = new kakao.maps.LatLng(${studyList.stuX }, ${studyList.stuY });

		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
			position : markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);

		// 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
		var iwContent = `<div style="padding:10px; width:100%; "><p class="mb-1"><b>${studyList.stuLoc}</b></p>`;
		iwContent += `<p class="pb-2"><a href="https://map.kakao.com/link/map/${studyList.stuLoc},${studyList.stuX }, ${studyList.stuY }" target="_blank"><span class="badge text-bg-secondary me-2">큰지도보기</span></a>`;
		iwContent += `<a href="https://map.kakao.com/link/to/${studyList.stuLoc},${studyList.stuX }, ${studyList.stuY }" target="_blank"><span class="badge text-bg-secondary">길찾기</span></a></p>`;
		iwContent += `</div>`; 
		
		iwPosition = new kakao.maps.LatLng(${studyList.stuX }, ${studyList.stuY }); //인포윈도우 표시 위치입니다

		// 인포윈도우를 생성합니다
		var infowindow = new kakao.maps.InfoWindow({
			position : iwPosition,
			content : iwContent
		});

		// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
		infowindow.open(map, marker);
		
		// ============= 로드뷰 이미지 =============		
		
		var roadviewContainer = document.getElementById('roadview'); //로드뷰를 표시할 div
		var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
		var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

		var position = new kakao.maps.LatLng(${studyList.stuX }, ${studyList.stuY });

		// 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
		roadviewClient.getNearestPanoId(position, 50, function(panoId) {
		    roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
		});
		
		// =====================================
		
		//북마크 버튼을 눌렀을 때
		let book = 0;
        $('.bookMark').on('click',function(){
            if(book==0){
                $(this).attr('class','bi-bookmark-fill');
               	book++;
            }else if(book==1){
                $(this).attr('class','bi-bookmark');
                book--;
            }

        });
        
        
        //url 쿼리스트링 값 가져오기
        let url = new URL(window.location.href);
        let urlParams = url.searchParams;
        
        console.log(urlParams);
        
        if(urlParams.get('status') == 'success'){
        	alert("참여신청 완료했습니다.");	
        }
        
        
	});
	
	//참여신청팝업창에서 참여신청버튼을 눌렀을 때 유효성검사
	function isVaild(){
		let result = false;
		
		if($("#reason").val() == null || $("#reason").val() == ''){
			alert("참여 신청 이유를 입력해주세요");
		}else if($("#reason").val().length < 10){
			alert("참여 신청 이유는 10자 이상 입력해주세요");
		}else{
			result = true;			
		}
		
		return result;
	}
	
	
</script>
<style>
i {
	cursor: pointer;
}
</style>
</head>

<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="../header.jsp"%>

	<main id="main">
		<%-- ${studyList } --%>
		<!-- Basic Section - Study Page -->
		<section id="study" class="studyBasic">
			<div class="container" style="width: 80%">
				<div class="container">

					<div class="d-flex">
						<div class="me-auto">
							<h3 class="text-light">
								<b>${studyList.stuTitle }</b>
							</h3>
						</div>
						<!-- 로그인한 유저와 작성자가 같을 때에만 수정 삭제 버튼이 보이도록 처리 -->
						<c:if test="${loginMember.userId == studyList.stuWriter }">
							<!-- 수정 버튼 -->

							<div class="icon-link icon-link-hover"
								style="-bs-icon-link-transform: translate3d(0, -.125rem, 0);"
								onclick="location.href='/study/modifyStudyBoard?stuNo=${studyList.stuNo}';">
								<i class="bi bi-pencil fs-5 me-2" style="color: #ffffff;"></i>
							</div>
							<!-- 삭제 버튼 -->
							<div class="studyBoardDelete icon-link icon-link-hover"
								style="-bs-icon-link-transform: translate3d(0, -.125rem, 0);"
								data-bs-toggle="modal" data-bs-target="#deleteModal">
								<i class="bi bi-trash3 fs-5 me-2" style="color: #ffffff;"></i>
							</div>
						</c:if>
						<div class="icon-link icon-link-hover"
							style="-bs-icon-link-transform: translate3d(0, -.125rem, 0);">
							<i class="bi bi-share fs-5 me-2" style="color: #ffffff;"></i>
						</div>
					</div>

					<p class="text-light mt-3">
						<b>${studyList.stuWriter }</b>
					</p>
				</div>


				<!-- 삭제 확인용 모달창 -->
				<div class="modal fade" id="deleteModal">
					<div class="modal-dialog">
						<div class="modal-content">

							<!-- Modal Header -->
							<div class="modal-header">
								<h4 class="modal-title">스터디 모집글 삭제</h4>
								<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
							</div>

							<!-- Modal body -->
							<div class="modal-body">해당 글을 삭제하시겠습니까?</div>

							<!-- Modal footer -->
							<div class="modal-footer">
								<button type="button" class="btn btn-outline-danger"
									data-bs-dismiss="modal">취소</button>
								<button type="button" class="btn btn-danger"
									onclick="location.href='/study/deleteStudy?stuNo=${studyList.stuNo }';">삭제</button>
							</div>

						</div>
					</div>
				</div>



				<div class="container pt-4">


					<div class="card">
						<div class="card-body">

							<div class="row mb-2">
								<!-- 모집인원 -->
								<div class="col-md-6 ">
									<div class="mb-1 ">
										<b>모집 인원</b> <span class="">${studyList.stuPers }명</span>
									</div>

								</div>

								<!-- 마감일 -->
								<div class="col-md-6">
									<div class="mb-1 ">
										<b>모집 마감일</b> <span class="">${studyList.endDate }</span>
									</div>
								</div>
							</div>


							<div class="row mb-2">
								<!-- 연락 방법 -->
								<div class="col-md-6">
									<div class="mb-1 ">
										<b>연락 방법</b> <span class=""><a
											href="${studyList.contactLink }">${studyList.contactLink }</a></span>
									</div>
								</div>

								<!-- 진행 기간 -->
								<div class="col-md-6">
									<div class="mb-1">
										<b>진행 기간</b> <span class="">${studyList.stuDate }</span>
									</div>
								</div>
							</div>


							<!-- 스터디 언어 선택 -->
							<div class="row">
								<div class="col-md-12">
									<div class="">
										<b>스터디 언어</b>
										<c:forEach var="stack" items="${stuStackList }">
											<span class="badge text-bg-secondary">${stack.stackName }</span>
										</c:forEach>
									</div>
								</div>
							</div>

						</div>
					</div>


					<!-- 내용 -->
					<div class="card mt-3">
						<div class="card-body">
							<div class="mb-2">
								<b>스터디 소개</b>
							</div>
							<div class="">${studyList.stuContent }</div>
						</div>
					</div>


					<!-- 카카오 지도 입력 부분 -->
					<div class="card mt-3">
						<div class="card-body">
							<div class="mb-2">
								<b>스터디 예정 장소</b>
							</div>
							<div class="row">
								<!-- 카카오 지도 출력 부분 -->
								<div class="col-md-6">
									<div id="map" style="width: 100%; height: 400px;"></div>
								</div>
								<!-- 카카오 로드뷰 부분 -->
								<div class="col-md-6">
									<div id="roadview" style="width: 100%; height: 400px;"></div>
								</div>
							</div>
						</div>
					</div>

					
					<!-- 로그인한 유저와 작성자가 다를 때에만 참여신청 버튼이 보이도록 처리 -->
					<!-- 북마크, 참여신청 버튼 -->
					<c:if test="${loginMember.userId != studyList.stuWriter }">
					<div class="row mt-4">
						<div class="col-md-1">
							<button type="button" class="btn btn-danger bookBtn"
								style="width: 100%">
								<i class="bi bi-bookmark bookMark"></i>
							</button>
						</div>
						<div class="col-md-11">
							<input type="button" class="btn btn-secondary" value="참여신청"
								style="width: 100%" data-bs-toggle="modal" data-bs-target="#exampleModal" />
						</div>
					</div>
					</c:if>


					<!-- 참여신청 버튼 눌렀을 때 뜨는 모달창 -->
					<div class="modal fade" id="exampleModal" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog  modal-dialog-centered">
							<div class="modal-content">
								<div class="modal-header">
									<h1 class="modal-title fs-5" id="exampleModalLabel">${studyList.stuWriter }님의 스터디에 참여 신청</h1>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<form action="/studyApply/insertApply" method="post">
									<div class="modal-body">
										<input type="text" id="applyId" name="applyId" value="${loginMember.userId }" hidden="true" />
										<input type="text" id="stuNo" name="stuNo" value="${studyList.stuNo}" hidden="true" />
										<div class="mb-3">
											<label 
											for="reason" class="col-form-label">참여 신청하는 이유를 간단하게 입력해주세요.</label>
											<textarea class="form-control" id="reason" name="reason" placeholder="10자 이상 입력하세요"></textarea>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">취소</button>
										<input type="submit" class="btn btn-danger" onclick="return isVaild();" value="참여신청" />
									</div>
								</form>
							</div>
						</div>
					</div>


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
