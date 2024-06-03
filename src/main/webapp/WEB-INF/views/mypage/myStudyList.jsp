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

<script>
	$(function() {
		console.log($(".accordion").length);
		for (let i = 0; i < $(".accordion").length; i++) {
			console.log($(".accordion:eq(i)"));

			if ($(".accordion:eq(i)") == null) {
				$(".accordion:eq(i)")
						.html(
								`<p class="card-text me-2 text-secondary">아직 스터디원이 없습니다.</p>`);
			}
		}
		/* if($(".list-group").html("")){
			//$(".list-group").html(`<p class="card-text me-2 text-secondary">아직 스터디 신청이 없습니다.</p>`);
		} */

		/* $(".studyMember").html(`<p class="card-text me-2 text-secondary">아직 스터디원이 없습니다.</p>`); */

		//신청 수락, 거절 버튼 눌렀을 때 알럿창
		let url = new URL(window.location.href);
		let urlParams = url.searchParams;
		console.log(urlParams);

		if (urlParams.get('applyAccept') == 'success') {
			alert("참여신청을 수락했습니다.");
		}else if(urlParams.get('applyRefuse') == 'success'){
			alert("참여신청을 거절했습니다.");
		}

	});
</script>
</head>
<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="../header.jsp"%>

	<main id="main">
		<!-- Basic Section - Study Page -->
		<section id="study" class="studyBasic">

			<div class="container" style="width: 70%">

				<div class="container">
					<h3 class="center text-center text-light pb-4">
						${loginMember.userId }님이 작성한 스터디 모임글</h3>
				</div>
				<%-- ${studyList } --%>

				<div class="row row-cols-md-1 mt-5">
					<c:forEach var="study" items="${studyList }">
						<div class="col-md mb-2 study">
							<div class="card mb-3">
								<div class="card-body">
									<!-- 스터디 모임글 내용 -->
									<div class="d-flex justify-content-between">
										<p class="card-subtitle mb-1">📍${study.stuLoc }</p>
										<p class="card-text">
											<!-- 수정 버튼 -->
											<i class="bi bi-pencil fs-5 me-2" style="cursor: pointer"
												onclick="location.href='/study/modifyStudyBoard?stuNo=${study.stuNo}';"></i>
											<!-- 삭제 버튼 -->
											<i class="bi bi-trash3 fs-5 me-2" style="cursor: pointer"
												data-bs-toggle="modal"
												data-bs-target="#deleteModal_${study.stuNo}"></i>
										</p>
									</div>

									<!-- 모집글 삭제 확인용 모달창 -->
									<div class="modal fade" id="deleteModal_${study.stuNo}">
										<div class="modal-dialog">
											<div class="modal-content">

												<!-- Modal Header -->
												<div class="modal-header">
													<h4 class="modal-title">스터디 모집글 삭제</h4>
													<button type="button" class="btn-close"
														data-bs-dismiss="modal"></button>
												</div>

												<!-- Modal body -->
												<div class="modal-body">해당 글을 삭제하시겠습니까?</div>

												<!-- Modal footer -->
												<div class="modal-footer">
													<button type="button" class="btn btn-outline-danger"
														data-bs-dismiss="modal">취소</button>
													<button type="button" class="btn btn-danger"
														onclick="location.href='/study/deleteStudy?stuNo=${study.stuNo }';">삭제</button>
												</div>

											</div>
										</div>
									</div>
									<!-- 모집글 삭제 확인용 모달창 끝 -->

									<h5 class="card-title">
										<b>${study.stuTitle }</b>
									</h5>
									<c:forEach var="stack" items="${stuStackList }">
										<c:if test="${study.stuNo == stack.stuBoardNo }">
											<span class="badge text-bg-secondary mb-3">${stack.stackName }</span>
										</c:if>
									</c:forEach>

									<!-- 현재 스터디 참여자 -->
									<p
										class="card-text mb-2 border-top border-secondary border-opacity-25 pt-3">
										<b>👀 현재 스터디원</b>
									</p>
									<div class="d-flex mb-2 studyMember_${study.stuNo }">
										<c:forEach var="apply" items="${stuApplyList }">
											<c:choose>
												<c:when
													test="${study.stuNo eq apply.stuNo and apply.status eq 'Y'}">
													<p class="card-text me-2">${apply.applyId }</p>
												</c:when>
											</c:choose>

										</c:forEach>

										<!-- <p class="card-text me-2 text-secondary">아직 스터디원이 없습니다.</p> -->

									</div>
									<p
										class="card-text mb-2 border-top border-secondary border-opacity-25 pt-3">
										<b>✉️ 스터디 참여신청</b>
									</p>
									<!-- 현재 스터디 신청자 R:새로운 신청 N:거절 Y:수락 -->

									<div class="accordion accordion-flush"
										id="accordionFlushExample_${study.stuNo }">
										<c:forEach var="apply" items="${stuApplyList }">
											<c:choose>
												<c:when
													test="${study.stuNo == apply.stuNo and apply.status == 'R'}">
													<div class="accordion-item">
														<h2 class="accordion-header" style="border: none;">
															<button class="accordion-button collapsed" type="button"
																data-bs-toggle="collapse"
																data-bs-target="#flush-collapseOne_${apply.applyNo}"
																aria-expanded="false"
																aria-controls="flush-collapseOne_${apply.applyNo}">
																${apply.applyId }님의스터디참여 신청</button>
														</h2>
														<div id="flush-collapseOne_${apply.applyNo}"
															class="accordion-collapse collapse"
															data-bs-parent="#accordionFlushExample_${study.stuNo }">
															<div class="accordion-body">
																${apply.reason }
																<button type="button"
																	class="btn btn-outline-danger btn-sm"
																	onclick="location.href='/studyApply/refuseApply/${apply.applyNo}';">거절</button>
																<button type="button" class="btn btn-danger btn-sm"
																	onclick="location.href='/studyApply/acceptApply/${apply.applyNo}';">수락</button>
															</div>
														</div>
													</div>
												</c:when>
											</c:choose>
										</c:forEach>

										<!-- 		<p class="card-text me-2 text-secondary">아직 스터디신청이 없습니다.</p> -->

									</div>

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
