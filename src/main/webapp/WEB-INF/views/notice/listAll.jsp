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
<link href="/resources/assets/css/lecture/listAll.css" rel="stylesheet" />
<title>Insert title here</title>
<script>
	function writeBtn() {
		let user = '${sessionScope.loginMember.userId}'
		let admin = '${sessionScope.loginMember.isAdmin}'
		if (user === ''){
			alert('로그인 후 이용해주세요');
			window.location.href = '/member/login';
		} else if (admin === 'N'){
			alert('작성 권한이 없습니다');
		} else {
			window.location.href = '/admin/notice';
		}
	}
</script>
</head>
<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="../header.jsp"%>

	<main id="main">
		<section id="notice" class="basic">
			<div class="container">

				<table class="table table-light table-hover">



					<thead>
						<tr>
							<th><input id="allCheck" type="checkbox" name="allCheck" /></th>
							<th>글번호</th>
							<th>작성자</th>
							<th>제목</th>
							<th>작성일</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="board" items="${notcBoardList }">

							<tr id="table"
								onclick="location.href = '/adminView/noticViewDetail?boardNo=${board.boardNo}';">
								<td onclick="event.cancelBubble=true"><input
									type="checkbox" name="rowCheck" class="deleteCheckbox"
									id="myCheckbox" value="${board.boardNo }" /></td>
								<td>${board.boardNo }</td>
								<td>${board.writer}</td>
								<td>${board.title }</td>
								<td>${board.postDate }</td>

							</tr>


						</c:forEach>
					</tbody>
				</table>
				
				
				
				<ul class="pagination">
					<c:if test="${param.pageNo > 1 }">
						<li class="page-item"><a class="page-link"
							href="/admin/noticeBoard?pageNo=${param.pageNo -1 }&searchType=${param.searchType}&searchValue=${param.searchValue}">Previous</a></li>

					</c:if>
					<c:forEach var="i"
						begin="${pagingInfo.startNumOfCurrentPagingBlock }"
						end="${pagingInfo.endNumOfCurrentPagingBlock }" step="1">
						<li class="page-item" id="${i }"><a class="page-link"
							href="/admin/noticeBoard?pageNo=${i }&searchType=${param.searchType}&searchValue=${param.searchValue}">${i }</a></li>
					</c:forEach>

					<c:if test="${param.pageNo < pagingInfo.totalPageCnt }">
						<li class="page-item"><a class="page-link"
							href="/admin/noticeBoard?pageNo=${param.pageNo +1 }&searchType=${param.searchType}&searchValue=${param.searchValue}">Next</a></li>
					</c:if>
				</ul>
				
				<button type="button" id="openModalBtn" onclick="writeBtn();">글 쓰기</button>


			</div>
		</section>
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