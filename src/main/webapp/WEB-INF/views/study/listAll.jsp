<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<title>Study List - DoDeveloper</title>
<meta content="" name="description" />
<meta content="" name="keywords" />

<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>

<!-- 부트스트랩 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<!-- select2 css cdn -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.9/css/select2.min.css"
	rel="stylesheet" />

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

<!-- 스터디 listAll css 파일 -->
<link href="/resources/assets/css/study/listAll.css" rel="stylesheet" />

<script>
	$(function() {
		$('.studyLang').select2({
			placeholder : '스터디 언어'
		});
	});

	//검색 조건 유효성 체크
	function isValid() {
		let result = false;

		let searchType = $("#searchType").val();
		let searchContent = $("#searchContent").val();

		console.log(searchType, searchContent);

		//검색어에 있어서는 안되는 쿼리문 키워드 배열로 정의
		let keyWord = new Array("OR", "SELECT", "AND", "INSERT", "UPDATE",
				"DELETE", "DROP", "EXEC", "TRUNCATE", "CREATE", "ALTER");

		if (searchType != -1 && searchContent.length > 0) {
			//검색 방법과 검색 내용이 있을 때 쿼리문 키워드가 존재하는지 검사

			let regEx;
			for (let i = 0; i < keyWord.length; i++) {
				//keyWord 배열에 있는 문자열 패턴이 있는지 없는지 전역적으로 검사하는 객체 생성
				regEx = new RegExp(keyWord[i], "gi");

				if (regEx.test(searchContent)) {
					//유저가 입력한 검색어에 키워드가 존재하는지 검사
					alert('검색어가 올바르지 않습니다!');
					$('#searchContent').val('');
					$('#searchContent').focus();
					return false;
				}
			}

			result = true; //검색방법, 검색 내용 있을 때

		} else { //검색 방법과 검색 내용, 쿼리문 키워드 없을 때
			alert('검색 방법과 검색 내용을 입력하세요!');
		}

		return result;
	}
</script>

</head>

<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="../header.jsp"%>

	<main id="main">
		<!-- Basic Section - Study Page -->
		<section id="study" class="studyBasic">

			<div class="container" style="width: 80%">
				<div class="container">
					<h3 class="center text-center text-light">
						<b>🔥 개발 스터디 모집</b>
					</h3>
				</div>

				<!-- 공지사항 넣을 부분 -->
				<div class="container mt-3">공지사항</div>

				<!-- 상단 필터 & 검색부분 -->
				<div class="container mt-3">

					<!-- 스터디할 언어 선택해서 select -->
					<div class="row">
						<div class="col-md-2">
							<select class="studyLang form-control" multiple="multiple"
								style="width: 100%;">
								<option>React</option>
								<option>javascript</option>
								<option>Vue</option>
								<option>Nextjs</option>
								<option>Java</option>
								<option>Spring</option>
								<option>Kotlin</option>
								<option>Swift</option>
								<option>Flutter</option>
							</select>
						</div>

						<!-- 모집중 or 모집마감 -->
						<div class="col-md-2">
							<button class="btn btn-outline-secondary" style="width: 100%">모집중만
								보기</button>
						</div>

						<div class="col-md-3"></div>

						<!-- 검색바 -->

						<div class="col-md-5">
							<form>
								<div class="row">
									<div class="col-md-4">
										<select class="form-select" id="searchType" name="searchType">
											<option value="-1">검색 방법</option>
											<option value="title">제목</option>
											<option value="writer">작성자</option>
											<option value="content">내용</option>
										</select>
									</div>
									<div class="col-md-6">
										<input type="text" class="form-control mb-4"
											id="searchContent" name="searchContent"
											placeholder="검색할 내용 입력" />
									</div>
									<div class="col-md-2">
										<input type="button" class="btn btn-secondary" value="검색"
											style="width: 100%" onclick="return isValid();" />
									</div>
								</div>
							</form>
						</div>


					</div>
				</div>

				<!-- 스터디 모임글 리스트 -->
				<div class="container mt-3">

					<%-- 	${studyList }
				${stuStackList } --%>
					<div class="row row-cols-md-4 ">
						<!-- 모임글 추가하기 -->
						<div class="col">
							<div class="card">
								<div class="card-body p-4 text-center" style="height: 251px;">
									<h5 style="line-height: 200px; cursor: pointer;"
										onclick="location.href='/study/writeStudyBoard';">
										<b>나도 스터디 만들기</b>
									</h5>
								</div>
							</div>
						</div>


						<c:forEach var="study" items="${studyList }">

							<!-- 모임글 1개 -->
							<div class="col mb-4" style="cursor: pointer;"
								onclick="location.href='/study/viewStudyBoard?stuNo=${study.stuNo}';">
								<div class="card">
									<div class="card-body p-4" style="width: 100%; height: 251px;">
										<div class="">
											<p class="card-subtitle mb-2 text-body-secondary">📍${study.stuLoc }</p>
										</div>

										<!-- 제목 -->
										<div class="mt-4">
											<h5 class="card-title">
												<b>${study.stuTitle }</b>
											</h5>
										</div>

										<!-- 스터디 언어 stuStack테이블에서 가져올 예정 -->
										<div class="mt-4">
											<p class="card-text">
												<c:forEach var="stack" items="${stuStackList }">
													<c:if test="${study.stuNo == stack.stuBoardNo }">
														<span class="badge text-bg-secondary">${stack.stackName }</span>
													</c:if>
												</c:forEach>
											</p>
										</div>

										<div class="d-flex mt-4">
											<div class="me-auto">
												<p class="card-text">${study.stuWriter }</p>
											</div>
											<div class="me-2">
												<p class="card-text text-body-secondary">
													<i class="bi bi-eye"></i> ${study.readCount }
												</p>
											</div>
											<div class="">
												<p class="card-text text-body-secondary">
													<i class="bi bi-bookmark"></i> ${study.scrape }
												</p>
											</div>
										</div>
									</div>
								</div>
							</div>

						</c:forEach>
					</div>

				</div>
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

	<!-- select2 javascript cdn -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.9/js/select2.min.js"></script>
</body>
</html>
