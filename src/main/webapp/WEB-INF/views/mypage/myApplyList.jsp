<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<title>My Apply List - DoDeveloper</title>
<meta content="" name="description" />
<meta content="" name="keywords" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

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

<!-- 스터디 myApplyList css 파일 -->
<link href="/resources/assets/css/study/myApplyList.css" rel="stylesheet" />

<script>
	$(function() {
		
		
		//신청 수락, 거절 버튼 눌렀을 때 알럿창
		let url = new URL(window.location.href);
		let urlParams = url.searchParams;
		//console.log(urlParams);

		if (urlParams.get('applyModify') == 'success') {
			alert("참여신청을 수정했습니다.");
		} else if (urlParams.get('applyDelete') == 'success') {
			alert("참여신청을 취소했습니다.");
		}

	});

	//수정 팝업창에서 수정 버튼 눌렀을 때 유효성검사
	function isVaild(applyNo) {
		let result = false;

		console.log(applyNo);
		console.log($(`.reason_\${applyNo}`).val());
		console.log($(`.reason_\${applyNo}`).val().length);

		//공백수까지 세어서 유효성 검사에 그냥 통과하는 문제가 있다

		if (($(`.reason_\${applyNo}`).val() == null)
				|| ($(`.reason_\${applyNo}`).val() == '')) {
			alert("참여 신청 이유를 입력해주세요");
		} else if ($(`.reason_\${applyNo}`).val().length < 10) {
			alert("참여 신청 이유는 10자 이상 입력해주세요");
		} else {

			result = true;
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
			<div class="container" style="width: 70%">

				<div class="container">
					<h3 class="center text-center text-light pb-4 fw-medium">
						내가 참여 신청한 스터디</h3>
				</div>

				<div class="row row-cols-md-2 mt-5">
					<c:forEach var="study" items="${studyList }">
						<div class="col-md mb-2">
							<div class="card mb-3">
								<div class="card-body">
									<h5 class="card-title mb-3 fw-semibold">🔥 내가 참여 신청한 스터디</h5>
									<!-- 스터디 모임글 내용 -->
									<div class="mouseOver">
										<p class="card-text mb-2 text-dark-emphasis">📍${study.stuLoc }</p>
										<p class="card-text mb-2 text-dark-emphasis"
											onclick="location.href='/study/viewStudyBoard?stuNo=${study.stuNo}';">
											${study.stuTitle }</p>
									</div>

									<c:forEach var="stack" items="${stuStackList }">
										<c:if test="${study.stuNo == stack.stuBoardNo }">
											<span class="badge text-bg-secondary mb-3">${stack.stackName }</span>
										</c:if>
									</c:forEach>

									<h5 class="card-title mb-2 border-top border-secondary border-opacity-25 pt-3 fw-semibold">
										✉️ 내 신청 내용
										<span class="applyStatus"></span>
									</h5>
									
									<!-- 현재 스터디 신청자 R:안읽은 신청 N:거절된 신청 Y:수락된 신청 -->
									<c:forEach var="apply" items="${stuApplyList }">
										<c:if test="${study.stuNo == apply.stuNo}">
											<p class="card-text text-dark-emphasis">${apply.reason }
												<c:choose>
													<c:when test="${apply.status == 'R' }">
														<span class="badge text-bg-light">읽지 않음</span>
													</c:when>
													<c:when test="${apply.status == 'Y' }">
														<span class="badge text-bg-light">신청 수락됨</span>
													</c:when>
													<c:when test="${apply.status == 'N' }">
														<span class="badge text-bg-light">신청 거절됨</span>
													</c:when>
												</c:choose>
											</p>
											<c:if test="${apply.status == 'R' }">											
												<button type="button" class="btn btn-outline-danger btn-sm"
													data-bs-toggle="modal"
													data-bs-target="#deleteModal_${study.stuNo}">신청 취소</button>
												<button type="button" class="btn btn-outline-danger btn-sm"
													data-bs-toggle="modal"
													data-bs-target="#modifyModal_${study.stuNo}">신청 수정</button>
											</c:if>

											<!-- 참여 신청 수정 모달창 -->
											<div class="modal fade" id="modifyModal_${study.stuNo}">
												<div class="modal-dialog">
													<div class="modal-content">
														<!-- Modal Header -->
														<div class="modal-header">
															<h4 class="modal-title">스터디 참여 신청 수정하기</h4>
															<button type="button" class="btn-close"
																data-bs-dismiss="modal"></button>
														</div>
														<!-- Modal body -->
														<form action="/studyApply/modifyApply" method="post">
															<div class="modal-body">
																<input type="text" id="applyNo" name="applyNo"
																	value="${apply.applyNo}" hidden="true" /> <input
																	type="text" id="applyId" name="applyId"
																	value="${apply.applyId}" hidden="true" /> <input
																	type="text" id="stuNo" name="stuNo"
																	value="${apply.stuNo}" hidden="true" />
																<div class="mb-3">
																	<label for="reason_${apply.applyNo}"
																		class="col-form-label">참여 신청하는 이유를 간단하게
																		입력해주세요.</label>
																	<textarea class="form-control reason_${apply.applyNo}"
																		id="reason" name="reason">
																	${apply.reason }
																</textarea>
																</div>
															</div>
															<!-- Modal footer -->
															<div class="modal-footer">
																<button type="button" class="btn btn-secondary"
																	data-bs-dismiss="modal">취소</button>
																<input type="submit" class="btn btn-danger"
																	onclick="return isVaild('${apply.applyNo}');"
																	value="수정" />
															</div>
														</form>
													</div>
												</div>
											</div>

											<!-- 참여 신청 취소 확인용 모달창 -->
											<div class="modal fade" id="deleteModal_${study.stuNo}">
												<div class="modal-dialog">
													<div class="modal-content">
														<!-- Modal Header -->
														<div class="modal-header">
															<h4 class="modal-title">스터디 참여 신청 취소하기</h4>
															<button type="button" class="btn-close"
																data-bs-dismiss="modal"></button>
														</div>
														<!-- Modal body -->
														<div class="modal-body">
															<b>${study.stuTitle }</b> 스터디 모집글의 참여 신청 내역을 취소하시겠습니까?
														</div>
														<!-- Modal footer -->
														<div class="modal-footer">
															<button type="button" class="btn btn-outline-danger"
																data-bs-dismiss="modal">아니오</button>
															<button type="button" class="btn btn-danger"
																onclick="location.href='/studyApply/deleteApply/${apply.applyNo}';">네</button>
														</div>
													</div>
												</div>
											</div>
											<!-- 참여 신청 취소 확인용 모달창 끝 -->
										</c:if>

									</c:forEach>

								</div>
							</div>
						</div>
					</c:forEach>
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

	<!-- Preloader --
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
