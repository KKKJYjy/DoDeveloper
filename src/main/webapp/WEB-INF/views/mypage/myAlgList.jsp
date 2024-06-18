<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<title>My Study List - DoDeveloper</title>
<meta content="" name="description" />
<meta content="" name="keywords" />

<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>

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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- 스터디 myStudyList css 파일 -->
<link href="/resources/assets/css/study/myStudyList.css"
	rel="stylesheet" />
<!--  pyscript -->
<link rel="stylesheet" href="https://pyscript.net/alpha/pyscript.css" />
<script defer src="https://pyscript.net/alpha/pyscript.js"></script>
<script>
$(function () {
	
	$('.py').hide();
	
	
	for (i=0; i<${fn:length(algDetailList)}; i++){
		let text = document. querySelectorAll('.content')[i].textContent;
		console.log(text);
		var enter = text.replace(/(\n|\r\n)/g, '<br>');
		console.log(enter);
		var tab = enter.replaceAll('    ', '&emsp;');
		console.log(tab);
		
		const html = document.getElementsByClassName('content')[i];
		html.innerHTML = tab;
	}
	
	
	
	
});

function button1_click(no) {
	var boardNo = no;
		
	$('.'+boardNo).show()
}

function button2_click(no) {
	var boardNo = no;
	
	$('.'+boardNo).hide()
}
</script>
<style>
#button {
	color: black;
	font-size: 12px;
	border: 1px solid black;
	padding: 5px;
	border-radius: 6px;
}
</style>
</head>
<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="../header.jsp"%>

	<main id="main">
		<!-- Basic Section - Study Page -->
		<section id="study" class="studyBasic">

			<div class="container" style="width: 70%">

				<div class="container">
					<h3 class="center text-center text-light pb-4 fw-medium">
						내가 작성한 algorithm</h3>
				</div>
				
				
				
				<c:forEach var="algDetail" items="${algDetailList}" begin="0"
					end="${fn:length(algDetailList)}">

					<div class="container mt-3">
						<a
							href="/algorithm/codeDetail?algDetailNo=${algDetail.algDetailNo}">
							<h2 style="color: white;">${algDetail.algDetailTitle}</h2>
						</a>
						<div class="mt-4 p-5 bg-primary text-white rounded">
							<h1>code</h1>
							<div class='content' id='content'>${algDetail.algDetailContent}</div>
							<h1>result</h1>
							<p>
								<py-script class="py ${algDetail.algDetailNo}">
								${algDetail.algDetailContent} </py-script>
							</p>
							<input type="button" id="button"
								onclick="button1_click(${algDetail.algDetailNo})" value="RUN" />
							<input type="button" id="button"
								onclick="button2_click(${algDetail.algDetailNo})" value="HIDE" />
						</div>
					</div>
					<div></div>



				</c:forEach>

				
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
