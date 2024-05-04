<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<title>Lecture List - DoDeveloper</title>
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
<link href="/resources/assets/css/lecture/viewBoard.css"
	rel="stylesheet" />

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
	function showInput() {
		var selectBox = document.getElementById("lecReviewSelect");
		var inputField = document.getElementById("lecReviewInput");

		if (selectBox.value === "option4") {
			inputField.style.display = "block";
		} else {
			inputField.style.display = "none";
		}
	}
</script>
</head>

<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="../header.jsp"%>

	<main id="main">
		<!-- Basic Section - Lecture Page -->
		<section id="lecture" class="basic">
			<div class="container">

				<section class="notice">
					<div class="page-title">
						<div class="container">
							<h3>강의 추천 게시글 작성 페이지</h3>
						</div>
					</div>



					<!-- 글 작성은 로그인 한 유저만 가능하니 유저 id가 바로 뜰수 있도록 -->
					<!-- 별점은 ☆로 5개로 하되 반개는 안되고 한개씩만 가능 -->
					<!-- 후기는 select박스로 결정하는 것도 있고 "내가 작성"을 누를 경우 input박스가 생기도록 -->
					<!-- 단, 유저가 select박스 중 다른 것을 눌렀을 경우 input박스는 사라져야 한다. -->
					<!-- 링크를 올렸을 때 바로 북마크가 생기도록 -->
					<div class="lecBoard">
						<form action="/board/writePOST" method="post">
							<div class="mb-3 mt-3">
								<label for="lecWriter" class="form-label"></label> <input
									type="text" class="form-control" id="lecWriter"
									name="lecWriter" value="${sessionScope.loginMember.userId}" />
									님께서 시청하신 강의 중 좋았던 강의 링크를 공유해주시고 후기를 남겨주세요.
							</div>

							<div class="mb-3 mt-3">
								<label for="lecTitle" class="form-label">제목</label> <input
									type="text" class="form-control" id="lecWriter"
									name="lecWriter" value="${result.lecBoard.lecTitle}" />
							</div>

							<div class="mb-3 mt-3">
								<label for="lecLink" class="form-label">강의 링크</label>
								<textarea id="lecLink" name="lecLink" rows="5" cols="500"
									placeholder="강의 링크를 공유해주세요." class="form-control"></textarea>
							
								<input
									type="text" class="form-control" id="lecWriter"
									name="lecWriter" value="${result.lecBoard.lecLink}" />
							
							</div>

							<div class="mb-3 mt-3">
								<label for="lecReview" class="form-label">강의 후기</label> 
								<select
									id="lecReviewSelect" name="lecReview" onchange="showInput()">
									<option value="australia">-- 강의 후기 선택 --</option>
									<option value="option1">초보자가 듣기 너무 좋아요.</option>
									<option value="option2">기초가 있으신 분들이 들으셔야 할 것 같아요.</option>
									<option value="option3">등등 생각 좀 해볼게요...</option>
									<option value="option4">강의 후기 직접 작성할게요.</option>
								</select>
								<textarea class="form-control" id="lecReviewInput"
									name="lecReview" placeholder="강의 후기 직접 작성해주세요..."
									style="display: none;"></textarea>
									
									
									<input
									type="text" class="form-control" id="lecWriter"
									name="lecWriter" value="${result.lecBoard.lecReview}" />
									
									
							</div>

							<div class="mb-3 mt-3">
								<label for="lecScore" class="form-label">별점</label>
								
								<input
									type="text" class="form-control" id="lecWriter"
									name="lecWriter" value="${result.lecBoard.lecScore}" />
								
								 <input
									type="text" class="form-control" id="lecScore" name="lecScore"
									placeholder="☆☆☆☆☆" />
							</div>

						</form>
					</div>

					<!-- 글 수정 & 글 삭제 로그인 한 유저만 가능 -->
					<div class="btns">
						<input type="submit" class="btn btn-success" value="글 저장" /> <input
							type="button" class="btn btn-danger" value="취소"
							onclick="resetWriteBoard();" />
						<div class="btn-group">
							<button type="button" class="btn"
								onclick="location.href='/lecture/listAll';">목록으로</button>
						</div>
					</div>




				</section>
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
</body>
</html>
