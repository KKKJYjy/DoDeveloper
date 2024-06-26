<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<title>CompanyInfo List - DoDeveloper</title>
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
	<%@ include file="../header.jsp"%>
	<main id="main">
		<!-- Basic Section - CompanyInfo Page -->
		<section id="companyInfo" class="basic">
			<div class="container">

				<h4>기업 리뷰 작성 페이지</h4>

				<form action="/companyInfo/writtenBoard" method="post">
					<div class="mb-3 mt-3">
						<label for="revWriter" class="form-label">작성자: </label> <input
							type="text" class="form-control" id="revWriter"
							placeholder="입력하신 모든 정보는 익명으로 처리되니 걱정마세요!" name="revWriter"
							value="${sessionScope.loginMember.userId}" />
					</div>

					<div class="mb-3 mt-3">
						<label for="revTitle" class="form-label">기업 한 줄 평 : </label> <input
							type="text" class="form-control" id="revTitle" 
							placeholder="(20자 이상) 기업에 대해 작성해 주세요" name="revTitle" />
					</div>

					<div class="mb-3 mt-3">
						<label for="revProfession" class="form-label">파트 : </label> <input
							type="text" class="form-control" id="revProfession"
							placeholder="업무 분야" name="revProfession" />
					</div>

					<div class="mb-3 mt-3">
						<label for="revContent" class="form-label">내용: </label>
						<textarea cols="600" rows="10" id="revContent" placeholder="(30자 이상) 기업에 관한 리뷰 글 작성해 주세요"
						name="revContent" class="form-control"></textarea>
					</div>

					<div class="mb-3 mt-3">
						<label for="revGood" class="form-label">장점: </label>
						<textarea cols="300" rows="10" id="revGood" name="revGood" placeholder="(30자 이상) 만족스러운 점을 작성해 주세요"
							class="form-control"></textarea>
					</div>

					<div class="mb-3 mt-3">
						<label for="revBed" class="form-label">단점: </label>
						<textarea cols="600" rows="10" id="revBed" name="revBed" placeholder="(30자 이상) 아쉬운 점을 작성해 주세요"
							class="form-control"></textarea>
					</div>
					
					<div>
						<input type="hidden" name="companyInfoNo" value="${param.companyInfoNo}">
					</div>
					
					<div>
						<input type="hidden" name="bType" value="3">
					</div>
					
					<input type="submit" class="btn btn-success" value="글 저장"
						onclick="location.href='/companyInfo/writtenBoard';" /> 
					<input type="reset" class="btn btn-danger" value="취소"
						onclick="location.href='/companyInfo/entire';" />
				</form>
			</div>
		</section>
		<!-- End Basic Section -->
	</main>

	<%@ include file="../footer.jsp"%>


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


