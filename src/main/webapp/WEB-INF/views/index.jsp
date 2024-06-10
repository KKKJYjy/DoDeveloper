<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<title>Home - DoDeveloper</title>
<meta content="" name="description" />
<meta content="" name="keywords" />

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
<link href="/resources/assets/css/home.css" rel="stylesheet" />

<!-- =======================================================
  * Template Name: Append
  * Template URL: https://bootstrapmade.com/append-bootstrap-website-template/
  * Updated: Mar 17 2024 with Bootstrap v5.3.3
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>

<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="./header.jsp"%>

	<main id="main">
		<!-- Basic Section - Index Page -->
		<section id="index" class="home">
			<div class="container" style="width: 70%">
			
				<div class="row pb-4">
					<div class="col-md-6">
						<h5 class="center text-center text-light pb-2">스터디 모임</h5>
						<div class="card">
							<ul class="list-group list-group-flush">
								<c:forEach var="study" items="${studyList }">
									<li class="list-group-item">
										<div class="d-flex">									
											<p class="card-text me-2 text-dark-emphasis mb-1">${study.stuWriter }</p>
											<p class="card-text text-dark-emphasis mb-1">
												<fmt:formatDate pattern="yyyy-MM-dd" value="${study.wrritenDate }" />
											</p>
											<div class="ms-auto">
												<i class="bi bi-eye me-2 text-dark-emphasis"> ${study.readCount }</i>
												<i class="bi bi-chat text-dark-emphasis"> 0</i>
											</div>										
										</div>
										<div class="mouseOver">
											<p class="card-title fw-semibold text-truncate" style="max-width: 100%;" 
											onclick="location.href='/study/viewStudyBoard?stuNo=${study.stuNo}';">
											${study.stuTitle }</p>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<div class="col-md-6">
						<h5 class="center text-center text-light pb-2">강의 추천</h5>
						<div class="card">
							<ul class="list-group list-group-flush">
								<c:forEach var="lecture" items="${lectureList }">
									<li class="list-group-item">
										<div class="d-flex">									
											<p class="card-text me-2 text-dark-emphasis mb-1">${lecture.lecWriter }</p>
											<p class="card-text text-dark-emphasis mb-1">
												<fmt:formatDate pattern="yyyy-MM-dd" value="${lecture.lecPostDate }" />
											</p>
											<div class="ms-auto">
												<i class="bi bi-eye me-2 text-dark-emphasis"> ${lecture.lecReadCount }</i>
												<i class="bi bi-suit-heart me-2 text-dark-emphasis"> ${lecture.lecLikeCount }</i>
												<i class="bi bi-chat text-dark-emphasis"> 0</i>
											</div>										
										</div>
										<div class="mouseOver">
											<p class="card-title fw-semibold text-truncate" style="max-width: 100%;"
												onclick="location.href='/lecture/viewBoard?lecNo=${lecture.lecNo }';">
											${lecture.lecTitle }</p>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6">
						<h5 class="center text-center text-light pb-2">기업 리뷰</h5>
						<div class="card">
							<ul class="list-group list-group-flush">
								<li class="list-group-item">
									<div class="d-flex">									
										<p class="card-text me-2 text-dark-emphasis mb-1">작성자</p>
										<p class="card-text text-dark-emphasis mb-1">작성일</p>
										<div class="ms-auto">
											<i class="bi bi-eye me-2 text-dark-emphasis"> 0</i>
											<i class="bi bi-chat text-dark-emphasis"> 0</i>
										</div>										
									</div>
									<div class="mouseOver">
										<p class="card-title fw-semibold text-truncate" style="max-width: 100%;">게시판 제목</p>
									</div>
								</li>
							</ul>
						</div>
					</div>
					<div class="col-md-6">
						<h5 class="center text-center text-light pb-2">알고리즘</h5>
						<div class="card">
							<ul class="list-group list-group-flush">
								<li class="list-group-item">
									<div class="d-flex">									
										<p class="card-text me-2 text-dark-emphasis mb-1">작성자</p>
										<p class="card-text text-dark-emphasis mb-1">작성일</p>
										<div class="ms-auto">
											<i class="bi bi-eye me-2 text-dark-emphasis"> 0</i>
											<i class="bi bi-chat text-dark-emphasis"> 0</i>
										</div>										
									</div>
									<div class="mouseOver">
										<p class="card-title fw-semibold text-truncate" style="max-width: 100%;">게시판 제목</p>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>


			</div>
		</section>
		<!-- End Basic Section -->
	</main>

	<%@ include file="./footer.jsp"%>

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
</body>
</html>
