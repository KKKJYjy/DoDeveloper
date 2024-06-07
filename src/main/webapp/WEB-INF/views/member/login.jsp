<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<title>Login - DoDeveloper</title>
<meta content="" name="description" />
<meta content="" name="keywords" />

<!-- Favicons -->
<link href="/resources/assets/img/favicon.png" rel="icon" />
<link href="/resources/assets/img/apple-touch-icon.png" rel="apple-touch-icon" />

<!-- Fonts -->
<link href="https://fonts.googleapis.com" rel="preconnect" />
<link href="https://fonts.gstatic.com" rel="preconnect" crossorigin />
<link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;1,300;1,400;1,500;1,600;1,700;1,800&family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap" rel="stylesheet" />

<!-- Vendor CSS Files -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="/resources/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet" />
<link href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" rel="stylesheet" />
<link href="/resources/assets/vendor/aos/aos.css" rel="stylesheet" />

<!-- Template Main CSS File -->
<link href="/resources/assets/css/main.css" rel="stylesheet" />
<link href="/resources/assets/css/member/login.css" rel="stylesheet" />

<!-- =======================================================
  * Template Name: Append
  * Template URL: https://bootstrapmade.com/append-bootstrap-website-template/
  * Updated: Mar 17 2024 with Bootstrap v5.3.3
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
function findId() {
	let email = $("#sb_findId_email").val();
	let resultMsg = sendIdToUser(email);
	alert(resultMsg);
	$("#sb_findId").modal("hide");
}

function findPwd() {
	let userId = $("#sb_findPwd_userId").val();
	let email = $("#sb_findPwd_email").val();
	let resultMsg = sendPwdResetLink(userId, email);
	alert(resultMsg);
	
	$("#sb_findPwd").modal("hide");
}

function findPwdView() {
	// 아이디 조회 모달 닫고 비밀번호 찾기 모달 열기
	$("#sb_findId_result").modal("hide");
	$("#sb_findPwd").modal("show");
}

function findIdLogin() {
	console.log("조회한 아이디로 로그인");
	$("#sb_findId_result").modal("hide");
	
	// 모달 창 닫으면서 유저 아이디칸에 조회한 아이디 넣어주기
	$("userId").val("");
	
	// 백엔드 처리
}

function findPwdLogin() {
	console.log("임시 비밀번호로 로그인");
	$("#sb_findPwd_result").modal("hide");
	
	// 백엔드 처리
}

function sendIdToUser(email) {
	let resultMsg = "";
	
	$.ajax({
        url: "./forgottenUserId",
        type: "post",
        dataType: "text",
        async: false,
        data: {
        	email: email,
        },
        success: function (data) {
        	resultMsg = data;
        },
        error: function () {
        	resultMsg = "에러가 발생했습니다. 잠시 후 다시 시도해주십시오.";
        }
     });
	return resultMsg;
}

function sendPwdResetLink(userId, email) {
	let resultMsg = "";
	
	$.ajax({
        url: "./pwdResetLink",
        type: "post",
        dataType: "text",
        async: false,
        data: {
        	userId : userId,
        	email: email,
        },
        success: function (data) {
        	resultMsg = data;
        },
        error: function () {
        	resultMsg = "에러가 발생했습니다. 잠시 후 다시 시도해주십시오.";
        }
     });
	return resultMsg;
}
</script>
</head>

<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="../header.jsp"%>
	
	
	<!--
	아래블로그 참고하셔요~^^
	
	https://velog.io/@yujinaa/spring-%ED%8C%80%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8-%EC%A0%9C%EC%9E%91-10.-%EA%B4%80%EB%A6%AC%EC%9E%90-%ED%8E%98%EC%9D%B4%EC%A7%80
	-->

	<main id="main">
		<!-- Basic Section - Login Page -->
		<section id="login" class="basic">
			<div class="container" data-aos="fade-up" data-aos-delay="200">
				<form role="form" action="/member/loginPost" method="post">
					<div class="form-login form-control p-4 position-absolute top-50 start-50 translate-middle" style="max-width: 330px;">
						<div class="form-panel">
							<div class="form-header text-center">
								<i class="bi bi-person-circle"></i>
								<div class="text-center mb-4">
									<span class="fw-light fs-3 text-secondary">계정 로그인</span>
								</div>
							</div>
							<div class="form-content">
								<div class="mb-4">
									<label for="userId" class="form-label">아이디</label>
									<div class="input-group flex-nowrap">
										<span class="input-group-text" id="addon-wrapping"><i class="bi bi-person-fill"></i></span> <input type="text" class="form-control" id="userId" name="userId" value="jiseong" placeholder="아이디를 입력해주세요" />
									</div>
								</div>
								<div class="mb-2">
									<label for="userPwd" class="form-label">비밀번호</label>
									<div class="input-group flex-nowrap">
										<span class="input-group-text" id="addon-wrapping"><i class="bi bi-key-fill"></i></span> <input type="password" class="form-control" id="userPwd" name="userPwd" value="1234" placeholder="비밀번호를 입력해주세요" />
									</div>
								</div>

								<div class="form-check d-flex justify-content-between mb-4">
									<div class="col">
										<input class="form-check-input" type="checkbox" id="remember" name="remember" ${sessionScope.loginDTO.remember ? 'checked' : ''}> <label class="form-check-label" for="remember">로그인 유지</label>
									</div>
								</div>
								<div class="d-grid gap-2 mx-auto mb-3">
									<button type="submit" class="btn btn-secondary">로그인</button>
									<button type="button" class="btn btn-register" onclick="location.href='/member/register'">회원가입</button>
								</div>
								<div class="text-center">
									<i class="bi bi-question-diamond-fill me-1"></i> <a href="#" class="link-secondary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover" data-bs-toggle="modal" data-bs-target="#sb_findId">아이디 찾기</a> / <a href="#" class="link-secondary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover" data-bs-toggle="modal" data-bs-target="#sb_findPwd">비밀번호 찾기</a>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</section>
		<!-- End Basic Section -->
	</main>
	
	<!-- 아이디 찾기 Modal -->
	<div class="modal fade" id="sb_findId" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="sb_findId_label" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="sb_findId_label">아이디 찾기</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="form-header text-center mb-4">
						<div class="text-center">
							<span class="fw-light fs-4 text-secondary">회원님의 이메일을 입력해주세요</span>
						</div>
					</div>

					<div class="form_chagePwd d-flex flex-column">
						<!-- 
						<div class="mb-3 mt-2">
							<div class="label-group">
								<label for="sb_findId_userName" class="form-label">이름</label>
							</div>
							<input type="text" class="form-control" id="sb_findId_userName" />
						</div>
						 -->
						<div class="mb-3 mt-2">
							<div class="label-group">
								<label for="sb_findId_email" class="form-label">이메일</label>
							</div>
							<input type="text" class="form-control" id="sb_findId_email" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger" onclick="findId();">아이디 찾기</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 비밀번호 찾기 Modal -->
	<div class="modal fade" id="sb_findPwd" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="sb_findPwd_label" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="sb_findPwd_label">비밀번호 찾기</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="form-header text-center mb-4">
						<div class="text-center">
							<span class="fw-light fs-4 text-secondary">회원님의 아이디, 이메일을 입력해주세요</span>
						</div>
					</div>

					<div class="form_chagePwd d-flex flex-column">
						<div class="mb-3 mt-2">
							<div class="label-group">
								<label for="sb_findPwd_userId" class="form-label">아이디</label>
							</div>
							<input type="text" class="form-control" id="sb_findPwd_userId" />
						</div>
						<div class="mb-3 mt-2">
							<div class="label-group">
								<label for="sb_findPwd_email" class="form-label">이메일</label>
							</div>
							<input type="text" class="form-control" id="sb_findPwd_email" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger" onclick="findPwd();">비밀번호 찾기</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 아이디 찾기 결과 Modal -->
	<div class="modal fade" id="sb_findId_result" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="sb_findId_result_label" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="sb_findId_result_label">아이디 찾기</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="form-header text-center mb-4">
						<div class="text-center">
							<span class="fw-light fs-4 text-secondary">아이디 찾기 결과</span>
						</div>
						<small class="text-muted">회원님의 아이디를 확인해주세요</small>
					</div>

					<div class="form_chagePwd d-flex flex-column">
						<div class="mb-3 mt-2">
							<div class="label-group">
								<label for="sb_findId_result_userId" class="form-label">아이디</label>
							</div>
							<input type="text" class="form-control" id="sb_findId_result_userId" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" onclick="findIdLogin();">로그인 하기</button>
					<button type="button" class="btn btn-danger" onclick="findPwdView();">비밀번호 찾기</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 비밀번호 찾기 결과 Modal -->
	<div class="modal fade" id="sb_findPwd_result" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="sb_findPwd_result_label" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="sb_findPwd_result_label">비밀번호 찾기</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="form-header text-center mb-4">
						<div class="text-center">
							<span class="fw-light fs-4 text-secondary">비밀번호 찾기 결과</span>
						</div>
						<small class="text-muted">회원님의 임시 비밀번호 입니다</small>
					</div>

					<div class="form_chagePwd d-flex flex-column">
						<div class="mb-3 mt-2">
							<div class="label-group">
								<label for="sb_findPwd_result_userId" class="form-label">비밀번호</label>
							</div>
							<input type="text" class="form-control" id="sb_findPwd_result_userId" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" onclick="findPwdLogin();">로그인 하기</button>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../footer.jsp"%>

	<!-- Scroll Top Button -->
	<a href="#" id="scroll-top" class="scroll-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

	<!-- Preloader -->
	<div id="preloader">
		<div></div>
		<div></div>
		<div></div>
		<div></div>
	</div>

	<!-- Vendor JS Files -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="/resources/assets/vendor/glightbox/js/glightbox.min.js"></script>
	<script src="/resources/assets/vendor/purecounter/purecounter_vanilla.js"></script>
	<script src="/resources/assets/vendor/imagesloaded/imagesloaded.pkgd.min.js"></script>
	<script src="/resources/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
	<script src="/resources/assets/vendor/aos/aos.js"></script>
	<script src="/resources/assets/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="/resources/assets/js/main.js"></script>
</body>
</html>
