<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<title>My joined Study List - DoDeveloper</title>
<meta content="" name="description" />
<meta content="" name="keywords" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<!-- 부트스트랩 아이콘 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">

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

<!-- 스터디 myApplyList css 파일 -->
<link href="/resources/assets/css/study/myApplyList.css" rel="stylesheet" />

<script>
	$(function() {
		
		
		//신청 수락, 거절 버튼 눌렀을 때 알럿창
		let url = new URL(window.location.href);
		let urlParams = url.searchParams;
		//console.log(urlParams);

		if (urlParams.get('applyModify') == 'success') {
			alert("참여신청을 수정했습니다.");
		} else if (urlParams.get('applyDelete') == 'success') {
			alert("참여신청을 취소했습니다.");
		}

	});

	//수정 팝업창에서 수정 버튼 눌렀을 때 유효성검사
	function isVaild(applyNo) {
		let result = false;

		console.log(applyNo);
		console.log($(`.reason_\${applyNo}`).val());
		console.log($(`.reason_\${applyNo}`).val().length);

		//공백수까지 세어서 유효성 검사에 그냥 통과하는 문제가 있다

		if (($(`.reason_\${applyNo}`).val() == null)
				|| ($(`.reason_\${applyNo}`).val() == '')) {
			alert("참여 신청 이유를 입력해주세요");
		} else if ($(`.reason_\${applyNo}`).val().length < 10) {
			alert("참여 신청 이유는 10자 이상 입력해주세요");
		} else {

			result = true;
		}

		return result;
	}
</script>
</head>
<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="../header.jsp"%>

	<main id="main">
		<!-- Basic Section - Study Page -->
		<section id="study" class="studyBasic">

			<div class="container" style="width: 70%">

				<div class="container">
					<h3 class="center text-center text-light pb-4 fw-medium">
						내가 참여중인 스터디 모임글</h3>
				</div>
				<%-- ${studyList } --%>

				<div class="row row-cols-md-2 mt-5">
					<c:forEach var="study" items="${studyList }">
						<div class="col-md mb-2 study">
							<div class="card mb-3">
								<div class="card-body">
									<!-- 스터디 모임글 내용 -->
									<div class="d-flex justify-content-between">
										<p class="card-subtitle mb-1">📍${study.stuLoc }</p>
										<p class="card-subtitle mb-1">
											스터디 시작일 ${study.endDate }
										</p>
									</div>

									
									<div class="mouseOver">
										<h5 class="card-title"
											onclick="location.href='/study/viewStudyBoard?stuNo=${study.stuNo}';">
											<b>${study.stuTitle }</b>
										</h5>
									</div>
									<c:forEach var="stack" items="${stuStackList }">
										<c:if test="${study.stuNo == stack.stuBoardNo }">
											<span class="badge text-bg-secondary mb-3">${stack.stackName }</span>
										</c:if>
									</c:forEach>

									<!-- 현재 스터디 참여자 -->
									<p
										class="card-text mb-2 border-top border-secondary border-opacity-25 pt-3">
										<b>👀 현재 스터디원</b>
									</p>
									<div class="d-flex mb-3 studyMember">
										<c:forEach var="apply" items="${stuApplyList }">
											<c:choose>
												<c:when
													test="${study.stuNo eq apply.stuNo and apply.status eq 'Y'}">
													<p class="card-text me-2 member">${apply.applyId }</p>
												</c:when>
											</c:choose>
										</c:forEach>

										<!-- <p class="card-text me-2 text-secondary">아직 스터디원이 없습니다.</p> -->

									</div>
									<p class="card-text mb-2 border-top border-secondary border-opacity-25 pt-3">
										<b>카카오 오픈채팅 링크</b>
									</p>
									<p class="card-text member">${study.contactLink }</p>
									<!-- 현재 스터디 신청자 R:새로운 신청 N:거절 Y:수락 -->


								</div>
							</div>
						</div>
					</c:forEach>
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

	<!-- Preloader --
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

</body>
</html>
