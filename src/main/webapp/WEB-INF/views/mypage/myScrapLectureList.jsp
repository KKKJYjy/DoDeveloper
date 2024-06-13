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
<!-- 강의 추천 게시판의 myLectureList css 파일 -->
<link href="/resources/assets/css/lecture/myLectureList.css"
	rel="stylesheet" />

<script>
	
</script>
</head>
<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="../header.jsp"%>

	<main id="main">
		<!-- Basic Section - Study Page -->
		<section id="lecture" class="lectureBasic">

			<div class="container" style="width: 70%">

				<div class="container">
					<h3 class="center text-center text-light pb-4 fw-medium">
						${sessionScope.loginMember.userId}님이 강의 추천 게시판에 좋아요를 누른 게시글</h3>
				</div>


				<c:forEach var="lectureScrap" items="${lectureScrapList}">
						<div class="col-md mb-2 lecture">
							<div class="card mb-3">
								<div class="card-body">
									<table class="table">
										<thead>
											<tr>
												<th>스크랩 한 게시글 내용</th>
												<th>스크랩 한 일자</th>
											</tr>
										</thead>
										<tbody>
											<tr onclick="location.href='/mypage/myScrapLectureList';">
												<td>${lectureScrap.scrapLecTitle}</td>
												<td>${lectureScrap.scrapDate}</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</c:forEach>


				<!-- 페이징 -->
				<!-- 여기서 사용한 쿼리스트링은 페이징 다음 페이지로 넘어갔을 때 조건을 유지하기 위해서 사용한 것 -->
				<ul class="pagination">
					<c:if test="${param.pageNo > 1}">
						<li class="page-item "><a class="page-link"
							href="/mypage/myScrapLectureList?pageNo=${param.pageNo -1 }">Previous</a></li>
					</c:if>
					<c:forEach var="i"
						begin="${pagingInfo.startNumOfCurrentPagingBlock }"
						end="${pagingInfo.endNumOfCurrentPagingBlock }" step="1">
						<li class="page-item " id="${i }"><a class="page-link"
							href="/mypage/myScrapLectureList?pageNo=${i }">${i }</a></li>
					</c:forEach>
					<c:if test="${param.pageNo < pagingInfo.totalPageCnt}">
						<li class="page-item "><a class="page-link"
							href="/mypage/myScrapLectureList?pageNo=${param.pageNo +1 }">Next</a></li>
					</c:if>
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
