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
<script>
	function selectCode(code) {
		// 알고리즘 분류 테이블의 code 항목을 받아서 select 태그로 code 를 선택
		console.log(code);

		console.log($('#classificationCode').val());

		$('#classificationCode').val(code);

	}
	
	function insertAlgClass() {
		newClassification = $('#algClass').val();
		console.log(newClassification);
		if(newClassification != ''){
			$.ajax({
				url : "/algorithm/newClassification",
				data : {
					
					"algClassification": newClassification,
				},
				type : "get",
				dataType : "text", // 수신받을 데이터의 타입
				success : function(data) {
				console.log(data);
				
				
				},
			});
			
			alert("등록되었습니다.")
		} else {
			alert("부적절한 입력값");
		}
	}
</script>
</head>

<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="../header.jsp"%>

	<main id="main">
		<!-- Basic Section - Algorithm Page -->
		<section id="algorithm" class="basic">
			<div class="container">

				<h1>글쓰기페이지</h1>

				<div>${algClassification}</div>

				<form action="/algorithm/writePOST" method="post">
					<div class="mb-3 mt-3">
						<label for="userId" class="form-label">글쓴이: </label> <input
							type="text" class="form-control" id="writer" name="writer"
							value="${sessionScope.loginMember.userId }" />
					</div>

					<div class="mb-3 mt-3">
						<label for="title" class="form-label">제목 : </label> <input
							type="text" class="form-control" id="title"
							placeholder="글 제목을 입력하세요..." name="title" />
					</div>



					<div class="container mt-3">

						<h2>Select Menu</h2>
						<p>To style a select menu in Bootstrap 5, add the .form-select
							class to the select element:</p>
						<label for="sel1" class="form-label">Select list (select
							one):</label> <select class="form-select" id="sel1" name="code"
							onchange="selectCode(this.value);">
							<option value="0">생성할 알고리즘을 어디에 분류할지 선택</option>
							<c:forEach items="${algClassification}" var="classification">

								<option value="${classification.code }">${classification.code }
									${classification.algClassification}</option>

							</c:forEach>
						</select> <br> <label for="title" class="form-label">code : </label> <input
							type="number" class="form-control" id="classificationCode"
							name="classificationCode" value="" readonly="readonly" />

						<!--  modal -->
						<div class="container mt-3">
							<h3>Modal Example</h3>
							<p>Click on the button to open the modal.</p>

							<button type="button" class="btn btn-primary"
								data-bs-toggle="modal" data-bs-target="#myModal">Open
								modal</button>
						</div>



					</div>

					<div class="mb-3 mt-3">
						<label for="mobile" class="form-label">글 내용: </label>
						<textarea cols="600" rows="10" id="comment" name="comment"
							class="form-control">
          					</textarea>
					</div>







					<input type="submit" class="btn btn-success" value="글 저장" /> <input
						type="button" class="btn btn-danger" value="취소" onclick="location.href='/algorithm/listAll'" />
				</form>










			</div>
		</section>
		<!-- End Basic Section -->
	</main>
	<!-- The Modal  algClassification 테이블 항목 만들기 -->
	<div class="modal" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">새 알고리즘 분류항목 개설</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">Modal body..</div>
				<div class="mb-3 mt-3">
					<label for="title" class="form-label">제목 : </label> <input
						type="text" class="form-control" id="algClass"
						placeholder="새 항목을 입력하세요..." name="algClass" />
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-warning"
						onclick="insertAlgClass()">등록</button>
					<button type="button" class="btn btn-danger"
						data-bs-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>

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
