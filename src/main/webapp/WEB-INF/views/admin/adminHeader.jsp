<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="keywords"
	content="wrappixel, admin dashboard, html css dashboard, web dashboard, bootstrap 5 admin, bootstrap 5, css3 dashboard, bootstrap 5 dashboard, materialpro admin bootstrap 5 dashboard, frontend, responsive bootstrap 5 admin template, materialpro admin lite design, materialpro admin lite dashboard bootstrap 5 dashboard template" />
<meta name="description"
	content="Material Pro Lite is powerful and clean admin dashboard template, inpired from Bootstrap Framework" />
<meta name="robots" content="noindex,nofollow" />
<title>Material Pro Lite Template by WrapPixel</title>
<link rel="canonical"
	href="https://www.wrappixel.com/templates/materialpro-lite/" />
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="/resources/admin/assets/images/favicon.png" />
<!-- chartist CSS -->
<link
	href="/resources/admin/assets/plugins/chartist-js/dist/chartist.min.css"
	rel="stylesheet" />
<link
	href="/resources/admin/assets/plugins/chartist-js/dist/chartist-init.css"
	rel="stylesheet" />
<link
	href="/resources/admin/assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.css"
	rel="stylesheet" />
<!--This page css - Morris CSS -->
<link href="/resources/admin/assets/plugins/c3-master/c3.min.css"
	rel="stylesheet" />
<!-- Custom CSS -->
<link href="/resources/admin/css/style.min.css" rel="stylesheet" />
<style>
.logOut {
	color: black;
	margin-top: 25px;
}

.login {
	color: black;
}
</style>
</head>
<body>
	<div class="preloader">
		<div class="lds-ripple">
			<div class="lds-pos"></div>
			<div class="lds-pos"></div>
		</div>
	</div>

	<div id="main-wrapper" data-layout="vertical" data-navbarbg="skin6"
		data-sidebartype="full" data-sidebar-position="absolute"
		data-header-position="absolute" data-boxed-layout="full">
		<!-- ============================================================== -->
		<!-- Main wrapper - style you can find in pages.scss -->
		<!-- ============================================================== -->
		<div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5"
			data-sidebartype="full" data-sidebar-position="absolute"
			data-header-position="absolute" data-boxed-layout="full">
			<!-- ============================================================== -->
			<!-- Topbar header - style you can find in pages.scss -->
			<!-- ============================================================== -->
			<header class="topbar" data-navbarbg="skin6">
				<nav class="navbar top-navbar navbar-expand-md navbar-dark">
					<div class="navbar-header" data-logobg="skin6">
						<!-- ============================================================== -->
						<!-- Logo -->
						<!-- ============================================================== -->
  
					    <a class="navbar-brand ms-4" href="../"> <!-- Logo icon --> </b> <!--End Logo icon -->
							<!-- Logo text --> <span class="logo-text"> <!-- dark Logo text -->
								<img src="/resources/admin/assets/images/logo.png"
								alt="homepage" class="dark-logo" />
						</span>
						</a>    
						
						          
						
						<!-- ============================================================== -->
						<!-- End Logo -->
						<!-- ============================================================== -->
						<!-- ============================================================== -->
						<!-- toggle and nav items -->
						<!-- ============================================================== -->
						<a
							class="nav-toggler waves-effect waves-light text-white d-block d-md-none"
							href="javascript:void(0)"><i class="ti-menu ti-close"></i></a>
					</div>
					<!-- ============================================================== -->
					<!-- End Logo -->
					<!-- ============================================================== -->
					<div class="navbar-collapse collapse" id="navbarSupportedContent"
						data-navbarbg="skin5">
						<ul class="navbar-nav d-lg-none d-md-block">
							<li class="nav-item"><a
								class="nav-toggler nav-link waves-effect waves-light text-white"
								href="javascript:void(0)"><i class="ti-menu ti-close"></i></a></li>
						</ul>
						<!-- ============================================================== -->
						<!-- toggle and nav items -->
						<!-- ============================================================== -->
						<ul class="navbar-nav me-auto mt-md-0">
							<!-- ============================================================== -->
							<!-- Search -->
							<!-- ============================================================== -->

							<!-- <li class="nav-item search-box"><a
							class="nav-link text-muted" href="javascript:void(0)"><i
								class="ti-search"></i></a>
							<form class="app-search" style="display: none">
								<input type="text" class="form-control"
									placeholder="Search &amp; enter" /> <a
									class="srh-btn"><i class="ti-close"></i></a>
							</form></li>
							 -->
						</ul>

						<!-- ============================================================== -->
						<!-- Right side toggle and nav items -->
						<!-- ============================================================== -->
						<ul class="navbar-nav">
							<!-- ============================================================== -->
							<!-- User profile and search -->
							<!-- ============================================================== -->
							<c:choose>
								<c:when test="${sessionScope.loginMember != null}">
									<li class="nav-item dropdown"><a
										class="nav-link dropdown-toggle text-muted"
										href="/mypage/myProfile" id="navbarDropdown" role="button"
										aria-expanded="false">
											${sessionScope.loginMember.userName}님이 로그인중입니다.</a></li>

										
										
										<a href="/member/logout" class="logOut">로그아웃</a>
									
								</c:when>
								<c:otherwise>
									
										
										<a href="/member/login" class="login">로그인</a>
									
								</c:otherwise>
							</c:choose>
						</ul>
						
					</div>
				</nav>
			</header>


			<!-- ============================================================== -->
			<!-- Left Sidebar - style you can find in sidebar.scss  -->
			<!-- ============================================================== -->
			<!-- Left Sidebar - style you can find in sidebar.scss  -->
			<!-- ============================================================== -->

			<!-- ============================================================== -->
			<!-- End Left Sidebar - style you can find in sidebar.scss  -->
			<!-- ============================================================== -->
			<!-- ============================================================== -->
			<!-- Page wrapper  -->
			<!-- ============================================================== -->


		</div>
		<!-- ============================================================== -->
		<!-- End Topbar header -->
		<!-- ============================================================== -->




		<!-- ============================================================== -->
		<!-- End Page wrapper  -->
		<!-- ============================================================== -->

		<!-- ============================================================== -->
		<!-- End Wrapper -->
		<!-- ============================================================== -->
		<!-- ============================================================== -->
		<!-- All Jquery -->
		<!-- ============================================================== -->
		<script
			src="/resources/admin/assets/plugins/jquery/dist/jquery.min.js"></script>
		<!-- Bootstrap tether Core JavaScript -->
		<script
			src="/resources/admin/assets/plugins/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
		<script src="/resources/admin/js/app-style-switcher.js"></script>
		<!--Wave Effects -->
		<script src="/resources/admin/js/waves.js"></script>
		<!--Menu sidebar -->
		<script src="/resources/admin/js/sidebarmenu.js"></script>
		<!-- ============================================================== -->
		<!-- This page plugins -->
		<!-- ============================================================== -->
		<!-- chartist chart -->
		<script
			src="/resources/admin/assets/plugins/chartist-js/dist/chartist.min.js"></script>
		<script
			src="/resources/admin/assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.min.js"></script>
		<!--c3 JavaScript -->
		<script src="/resources/admin/assets/plugins/d3/d3.min.js"></script>
		<script src="/resources/admin/assets/plugins/c3-master/c3.min.js"></script>
		<!--Custom JavaScript -->
		<script src="/resources/admin/js/pages/dashboards/dashboard1.js"></script>
		<script src="/resources/admin/js/custom.js"></script>
</body>
</html>