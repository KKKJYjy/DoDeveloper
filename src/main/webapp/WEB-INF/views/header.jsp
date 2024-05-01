<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageName" value="${requestScope['javax.servlet.forward.request_uri']}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<title></title>
<script>
	let requestUri = '${pageContext.request.requestURI}';
	requestUri = requestUri.split("/views")[1];
	requestUri = requestUri.split(".")[0];
	//console.log("requestUri", requestUri);

	$(document).ready(function() {
		$(".navmenu li a").each(function() {
			let href = $(this).attr("href");
			if (href == requestUri) {
				$(this).addClass("active");
			}
		});
	});
</script>
</head>
<body>
	<!-- ======= Header ======= -->
	<header id="header" class="header fixed-top d-flex align-items-center">
		<div class="container-fluid d-flex align-items-center justify-content-between">
			<a href="/" class="logo d-flex align-items-center me-auto me-xl-0"> <!-- Uncomment the line below if you also wish to use an image logo --> <!-- <img src="/resources/assets/img/logo.png" alt=""> --> 띠브 <span>DDev</span>
			</a>

			<!-- Nav Menu -->
			<nav id="navmenu" class="navmenu">
				<ul>
					<li><a href="/index">홈</a></li>
					<li><a href="/study/listAll">스터디 모임</a></li>
					<li><a href="/lecture/listAll">강의추천</a></li>
					<li><a href="/worker/listAll">재직자리뷰</a></li>
					<li><a href="/algorithm/listAll">알고리즘</a></li>
					<li><a href="/message">쪽지함</a></li>
					<li><a href="/member/login">로그인</a></li>
					<li><a href="/member/register">회원가입</a></li>
				</ul>
				<i class="mobile-nav-toggle d-xl-none bi bi-list"></i>
			</nav>
			<!-- End Nav Menu -->
			<div class="d-flex align-items-center">
				<c:choose>
					<c:when test="${sessionScope.loginMember != null}">
						<li class="loginUser me-3"><a href="#"> <span>${sessionScope.loginMember.userName} 님!<span></a></li>
						<a class="btn-logout" href="/member/logout">로그아웃</a>
					</c:when>
					<c:otherwise>
						<a class="btn-login me-2" href="/member/login">로그인</a>
						<a class="btn-register" href="/member/register">회원가입</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</header>
	<!-- End Header -->
</body>
</html>