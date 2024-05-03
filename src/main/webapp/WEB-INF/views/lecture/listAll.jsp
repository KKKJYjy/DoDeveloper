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

<!-- =======================================================
  * Template Name: Append
  * Template URL: https://bootstrapmade.com/append-bootstrap-website-template/
  * Updated: Mar 17 2024 with Bootstrap v5.3.3
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style>
select option:hover {
    background-color: #444; /* 마우스 호버 시 배경 색상을 짙은 회색(#444)으로 변경 */
    color: #333; /* 마우스 호버 시 텍스트 색상을 짙은 회색(#333)으로 변경 */
}
</style>

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
							<h3>강의 추천 게시글 전체 조회 페이지</h3>
						</div>
					</div>

					<!-- board seach area -->
					<div id="board-search">
						<div class="container">
							<div class="search-window">
								<form action="">
									<div class="search-wrap searchbar">
										<!-- 검색 기능 -->
										<div class="input-group mt-3 mb-3">
											<select id="btn" name="searchType">
												<option value="-1">---- 검색 조건을 입력하세요 ----</option>
												<option value="title">제목</option>
												<option value="writer">작성자</option>
												<option value="content">본문</option>
											</select>
										</div>
										<div class="input-group mt-3 mb-3">
											<input id="search" type="text" name="searchValue"
												placeholder="검색어를 입력해주세요.">
											<button type="submit" class="btn btn-dark"
												onclick="return isValid();">검색</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>


					<div class="button-container">
						<button type="button" class="btn btn-dark dropdown-toggle filters"
							data-bs-toggle="dropdown">검색 필터</button>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="#">최신순</a></li>
							<li><a class="dropdown-item" href="#">추천순</a></li>
							<li><a class="dropdown-item" href="#">조회순</a></li>
						</ul>
						<button type="button" class="btn btn-dark writeren"
							onclick="location.href='';">글 작성</button>
					</div>



					<!-- board list area -->
					<div id="board-list">
						<div class="container">
							<table class="board-table">
								<thead>
									<tr>
										<th>글번호</th>
										<th>제목</th>
										<th>작성자</th>
										<th>작성 날짜</th>
										<th>조회수</th>
										<th>좋아요수</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="lectureBoard" items="${lectureBoardList }">
										<tr
											onclick="location.href='/lecture/view?lecNo=${lectureBoard.lecNo }';">
											<td>${lectureBoard.lecNo }</td>
											<td>${lectureBoard.lecTitle }</td>
											<td>${lectureBoard.lecWriter }</td>
											<td>${lectureBoard.lecPostDate }</td>
											<td>${lectureBoard.lecReadCount }</td>
											<td>${lectureBoard.lecLikeCount }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</section>


				<!-- 페이징 -->
				<ul class="pagination">
					<li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
					<li class="page-item active"><a class="page-link" href="#">${i }</a></li>
					<li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
				</ul>

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
