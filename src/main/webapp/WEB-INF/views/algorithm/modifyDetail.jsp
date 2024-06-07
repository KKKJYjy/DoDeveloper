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
	function chageSelect() {

		// 셀렉트 태그로 AlgDetail 중 하나를 선택하면  항목에 해당하는 text 형태의 algDetail.toString 을 select란 변수로 받아옴.
		// 이후 select 을 분리해서 각각의 값을 게시글 번호, 게시글 제목, 코드 내용, 코드 실행결과 등의 항목에 디폴트 값으로 넣고 
		// 이 값들을 수정할 수 있도록 함 (처음부터 다시 써서 수정하는게 힘드니까)

		select = $("#selectAlgDetailTitle").val();
		console.log(select); // 셀렉트 태그에서 선택 시  
		selectTitle = $("select[name=selectAlgDetailTitle] option:selected")
				.text();
		console.log($("select[name=selectAlgDetailTitle] option:selected")
				.text()); //text값 가져오기 (algDetailTitle) 가져옴

		algDetailNo = select.split('algDetailNo=')[1].split(',')[0]

		algDetailContent = select.split('algDetailContent=')[1]
				.split(', algDetailResult')[0]

		algDetailResult = select.split('algDetailResult=')[1]
				.split(', algDetailTitle')[0]

		algDetailComment = select.split('algDetailComment=')[1]
				.split(', boardType')[0]

		$("#algDetailNo").val(algDetailNo);
		$("#algDetailTitle").val(selectTitle);
		$("#algDetailContent").val(algDetailContent);
		$("#algDetailResult").val(algDetailResult);
		$("#algDetailComment").val(algDetailComment);

		var text = document.querySelector('textarea');
		var enter = text.value.replace(/(\n|\r\n)/g, '<br>');
		var tab = text.value.replace(/(\t|\r\t)/g, '&emsp;');
		console.log(enter);
		console.log(tab);
		
	}


		
		
	$(function() {
		
		let a = 1;
		let b = 2;
		console.log(a+b);
		
		
	});
</script>
<style>
textarea {
	width: 100%;
	height: 16.25em;
	border: none;
	resize: none;
}
</style>
</head>

<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="../header.jsp"%>

	<main id="main">
		<!-- Basic Section - Algorithm Page -->
		<section id="algorithm" class="basic">
			<div class="container">

				<h1>${algDetailList[0].algDetailTitle}</h1>

				<h1>alg</h1>


				<div>${algDetail}</div>



				<form action="modifyAlgDetail" method="post">

					<label for="sel1" class="form-label">Select list (선택하면 제목과
						게시글 번호 자동으로 입력 됨): </label> <select class="form-select"
						id="selectAlgDetailTitle" name="selectAlgDetailTitle"
						onchange="chageSelect()">
						<option value="0">알고리즘을 어디 수정할 지 선택</option>
						<c:forEach items="${algDetail}" var="algDetail">

							<option value="${algDetail}" id="detailTitle">${algDetail.algDetailTitle }</option>

						</c:forEach>
					</select>





					<div class="mb-3 mt-3">
						<label for="title" class="form-label">제목 : </label> <input
							type="text" value="${algDetail[0].algDetailTitle }"
							onfocus="this.value='';" class="form-control" id="algDetailTitle"
							placeholder="글 제목을 입력하세요..." name="algDetailTitle" />
					</div>

					<div class="mb-3 mt-3">
						<label for="title" class="form-label">게시글 번호 : </label> <input
							type="number" class="form-control"
							value="${algDetail[0].algDetailNo }" id="algDetailNo"
							placeholder="입력하세요..." name="algDetailNo" />
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

						<label for="title" class="form-label">실행결과 : </label> <input
							type="text" class="form-control"
							value="${algDetail[0].algDetailResult }" id="algDetailResult"
							placeholder="입력하세요..." name="algDetailResult" />
					</div>

					<div class="mb-3 mt-3">

						<label for="title" class="form-label">comment : </label> <input
							type="text" class="form-control"
							value="${algDetail[0].algDetailComment }" id="algDetailComment"
							placeholder="입력하세요..." name="algDetailComment" />
					</div>


					<div class="mb-3 mt-3">

						<label for="title" class="form-label">comment : </label> <input
							type="text" class="form-control" value="dooly" id="writer"
							placeholder="입력하세요..." name="writer" />
					</div>

					<button type="submit">수정</button>
					<button type="button" onclick="location.href='/algorithm/algDetail?boardNo=${algDetail[0].algBoardNo}'">취소</button>

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
