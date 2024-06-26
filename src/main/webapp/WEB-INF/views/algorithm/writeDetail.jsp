<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<title>Algorithm List - DoDeveloper</title>
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
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />
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

<script>
	$(function name() {
		
		let user = '${sessionScope.loginMember.userId}';
		
		console.log(user);
		
		$('#writer').val(user);
		
		
		
		
	});

</script>

<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="../header.jsp"%>

	<main id="main">
		<!-- Basic Section - Algorithm Page -->
		<section id="algorithm" class="basic">
			<div class="container">

				<h1>${algDetailList[0].algDetailTitle}</h1>

				<h1>alg</h1>


				<div>${algDetailList}</div>

				<div>${boardNo}</div>



				<form action="/algorithm/writeDetailPOST" method="post">


					<div class="mb-3 mt-3">
						<label for="title" class="form-label">글 번호 : </label> <input
							type="number" class="form-control" id="algBoardNo"
							name="algBoardNo" value="${boardNo }" readonly="readonly" />
					</div>
					
					
					


					<div class="mb-3 mt-3">
						<label for="title" class="form-label">글쓴이 : </label> <input
							type="text" class="form-control" id="writer"
							placeholder=" 입력하세요..." name="writer" readonly="readonly" />
					</div>
					
					
					
					<div class="mb-3 mt-3">
						<label for="title" class="form-label">제목 : </label> <input
							type="text" class="form-control" id="algDetailTitle"
							placeholder=" 입력하세요..." name="algDetailTitle" />
					</div>
					


					<!--  event.keyCode == 9 (tab 키를 누르면 텍스트 박스에서 tab 이 적욕되도록 함 -->
					<div class="mb-3 mt-3">

						<label for="title" class="form-label">게시글 내용 : </label>
						<textarea
							onkeydown="if(event.keyCode===9){var v=this.value,s=this.selectionStart,e=this.selectionEnd;this.value=v.substring(0, s)+'\t'+v.substring(e);this.selectionStart=this.selectionEnd=s+1;return false;}"
							class="form-control" value="${algDetail[0].algDetailContent }"
							id="algDetailContent" placeholder="입력하세요..."
							name="algDetailContent"></</textarea>
					</div>
					
					<div class="mb-3 mt-3">
						<label for="title" class="form-label">result : </label> <input
							type="text" class="form-control" id="algDetailResult"
							placeholder=" 입력하세요..." name="algDetailResult" />
					</div>


					<div class="mb-3 mt-3">
						<label for="title" class="form-label">comment : </label> <input
							type="text" class="form-control" id="algDetailComment"
							placeholder=" 입력하세요..." name="algDetailComment" />
					</div>
					
					

					<div class="btns">

						<button type="submit" class="btn btn-info">글쓰기</button>
						<button type="button" class="btn btn-info"
							onclick="location.href='/';">글수정</button>
					
						<button type="button" class="btn btn-info"
							onclick="location.href='/algorithm/algDetail?boardNo=${boardNo}';">목록으로</button>
					</div>







				</form>






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
