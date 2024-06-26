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
<script>
/* 검색 조건 */
function isValid() {
	let searchType = $('#searchType').val(); // 검색 조건의 type
	let searchValue = $('#searchValue').val(); // 검색 조건의 값
	let filterType = $('#filterType').val(); // 검색 필터의 type

	if (searchType === "-1") {
		alert("검색 조건을 선택해주세요.");
		return false;
	}

	if (searchValue.trim() === "") {
		alert("검색어를 입력해주세요.");
		return false;
	}

	if (filterType === "-1") {
		return false;
	}

	// SQL 쿼리문 키워드 검사
	let sqlKeywords = [ "OR", "SELECT", "AND", "INSERT", "UPDATE",
			"DELETE", "DROP", "EXEC", "TRUNCATE", "CREATE", "ALTER" ];
	for (let i = 0; i < sqlKeywords.length; i++) {
		if (searchValue.toUpperCase().includes(sqlKeywords[i])) {
			alert("검색어에 유효하지 않은 키워드가 포함되어 있습니다.");
			return false;
		}
	}

	return true;
}

/* 페이징 */
$(function() {
	let pageNo = '${param.pageNo}';
	// alert(pageNo);
});

/* 로그인 후 게시글 작성 가능 */
function writen() {
    let writer = '${sessionScope.loginMember.userId}'; // 로그인 한 유저

    if (writer === '') {
        alert('로그인 후 이용부탁드립니다.');
        // 로그인 페이지로 이동, 로그인 성공 후 글 작성 페이지로
        window.location.href = '/lecture/writeBoard';
        return false;
    } else {
        // 로그인되어 있으면 글 작성 페이지로 이동
        window.location.href = '/lecture/writeBoard';
    }
}
</script>
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
							<h3>강의 추천 게시판</h3>
						</div>
					</div>

					<!-- board seach area -->
					<form action="/lecture/listAll" method="GET">
						<div id="board-search" class="container">
							<div class="search-window">

								<div class="search-wrap searchbar">
									<!-- 검색 기능 -->
									<div class="input-group mt-3 mb-3">
										<select id="searchType" name="searchType">
											<option value="-1">---- 검색 조건을 입력하세요 ----</option>
											<!-- "${param.searchType == 'lecTitle'?'selected':''}"을 사용해서 선택을 했을 때 값이 유지되도록 -->
											<!-- param에서 searchType을 가져와서 select를 했을 경우 -->
											<option value="lecTitle"
												<c:out value="${param.searchType == 'lecTitle'?'selected':''}"/>>제목</option>
											<option value="lecWriter"
												<c:out value="${param.searchType == 'lecWriter'?'selected':''}"/>>작성자</option>
										</select>
									</div>
									<div class="input-group mt-3 mb-3">
										<input id="searchValue" type="text" name="searchValue"
											placeholder="검색어를 입력해주세요.">
										<button type="submit" class="btn btn-dark"
											onclick="return isValid();">검색</button>
									</div>
								</div>
							</div>
						</div>

						<div class="button-container">
							<button type="button"
								class="btn btn-dark dropdown-toggle filters"
								data-bs-toggle="dropdown">검색 필터</button>
							<ul class="dropdown-menu" id="filterType" name="filterType">
								<li><a class="dropdown-item"
									href="?pageNo=${pagingInfo.pageNo }&filterType=latest&searchType=${param.searchType}&searchValue=${param.searchValue}">최신순</a></li>
								<li><a class="dropdown-item"
									href="?pageNo=${pagingInfo.pageNo }&filterType=popular&searchType=${param.searchType}&searchValue=${param.searchValue}">인기순</a></li>
								<li><a class="dropdown-item"
									href="?pageNo=${pagingInfo.pageNo }&filterType=view&searchType=${param.searchType}&searchValue=${param.searchValue}">조회순</a></li>
								<li><a class="dropdown-item"
									href="?pageNo=${pagingInfo.pageNo }&filterType=starScore&searchType=${param.searchType}&searchValue=${param.searchValue}">별점순</a></li>
							</ul>

							<button type="button" class="btn btn-dark writeren" id="applyFilterBtn"
								onclick="return writen();">글 작성</button>
						</div>
					</form>


					<!-- board list area -->
					<div id="board-list">
						<div class="container">
							<table class="board-table">
								<thead>
									<tr>
										<!-- <th>글번호</th> -->
										<th>제목</th>
										<th>작성자</th>
										<th>작성 날짜</th>
										<th>별점</th>
										<th>조회수</th>
										<th>좋아요수</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="lectureBoard" items="${lectureBoardList }">
										<!-- /lecture/viewBoard?lecNo가 /lecture/viewBoard 경로 -->
										<tr
											onclick="location.href='/lecture/viewBoard?lecNo=${lectureBoard.lecNo }';">
											<!-- <td>${lectureBoard.lecNo }</td> -->
											<td>${lectureBoard.lecTitle }</td>
											<td>${lectureBoard.lecWriter }</td>
											<td>${lectureBoard.lecPostDate }</td>
											<td>${lectureBoard.lecScore }</td>
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
				<!-- 여기서 사용한 쿼리스트링은 페이징 다음 페이지로 넘어갔을 때 조건을 유지하기 위해서 사용한 것 -->
				<ul class="pagination">
					<c:if test="${param.pageNo > 1}">
						<li class="page-item "><a class="page-link"
							href="/lecture/listAll?pageNo=${param.pageNo -1 }&filterType=${param.filterType}&searchType=${param.searchType}&searchValue=${param.searchValue}">Previous</a></li>
					</c:if>
					<c:forEach var="i"
						begin="${pagingInfo.startNumOfCurrentPagingBlock }"
						end="${pagingInfo.endNumOfCurrentPagingBlock }" step="1">
						<li class="page-item " id="${i }"><a class="page-link"
							href="/lecture/listAll?pageNo=${i }&filterType=${param.filterType}&searchType=${param.searchType}&searchValue=${param.searchValue}">${i }</a></li>
					</c:forEach>
					<c:if test="${param.pageNo < pagingInfo.totalPageCnt}">
						<li class="page-item "><a class="page-link"
							href="/lecture/listAll?pageNo=${param.pageNo +1 }&filterType=${param.filterType}&searchType=${param.searchType}&searchValue=${param.searchValue}">Next</a></li>
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
