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

</head>
<body>

	<c:import url="./adminSidebar.jsp"></c:import>

	<div class="preloader">
		<div class="lds-ripple">
			<div class="lds-pos"></div>
			<div class="lds-pos"></div>
		</div>
	</div>
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
					<a class="navbar-brand ms-4" href="index.html"> <!-- Logo icon -->
						<b class="logo-icon"> <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
							<!-- Dark Logo icon --> <img
							src="/resources/admin/assets/images/logo-light-icon.png"
							alt="homepage" class="dark-logo" />
					</b> <!--End Logo icon --> <!-- Logo text --> <span class="logo-text">
							<!-- dark Logo text --> <img
							src="/resources/admin/assets/images/logo-light-text.png"
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

						<li class="nav-item search-box"><a
							class="nav-link text-muted" href="javascript:void(0)"><i
								class="ti-search"></i></a>
							<form class="app-search" style="display: none">
								<input type="text" class="form-control"
									placeholder="Search &amp; enter" /> <a class="srh-btn"><i
									class="ti-close"></i></a>
							</form></li>
					</ul>

					<!-- ============================================================== -->
					<!-- Right side toggle and nav items -->
					<!-- ============================================================== -->
					<ul class="navbar-nav">
						<!-- ============================================================== -->
						<!-- User profile and search -->
						<!-- ============================================================== -->
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle text-muted waves-effect waves-dark"
							href="#" id="navbarDropdown" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> <img
								src="/resources/admin/assets/images/users/1.jpg" alt="user"
								class="profile-pic me-2" />Markarn Doe
						</a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown"></ul>
						</li>
					</ul>
				</div>
			</nav>
		</header>
		<!-- ============================================================== -->
		<!-- End Topbar header -->
		<!-- ============================================================== -->
		<!-- ============================================================== -->
		<!-- Left Sidebar - style you can find in sidebar.scss  -->
		<!-- ============================================================== -->
	<aside class="left-sidebar" data-sidebarbg="skin6">
			<!-- Sidebar scroll-->
			<div class="scroll-sidebar">
				<!-- Sidebar navigation-->
				<nav class="sidebar-nav">
					<ul id="sidebarnav">
						<!-- User Profile-->
						<li class="sidebar-item"><a
							class="sidebar-link waves-effect waves-dark sidebar-link"
							href="/admin/dashboard" aria-expanded="false"><i
								class="mdi me-2 mdi-gauge"></i><span class="hide-menu">Dashboard</span></a>
						</li>
						<li class="sidebar-item"><a
							class="sidebar-link waves-effect waves-dark sidebar-link"
							href="pages-profile.html" aria-expanded="false">
								<i class="mdi me-2 mdi-account-check"></i><span
								class="hide-menu">Profile</span>
						</a></li>
						<li class="sidebar-item"><a
							class="sidebar-link waves-effect waves-dark sidebar-link"
							href="/admin/table" aria-expanded="false"><i
								class="mdi me-2 mdi-table"></i><span class="hide-menu">Table</span></a>
						</li>
						<li class="sidebar-item"><a
							class="sidebar-link waves-effect waves-dark sidebar-link"
							href="icon-material.html" aria-expanded="false"><i
								class="mdi me-2 mdi-emoticon"></i><span class="hide-menu">Icon</span></a>
						</li>
						<li class="sidebar-item"><a
							class="sidebar-link waves-effect waves-dark sidebar-link"
							href="map-google.html" aria-expanded="false"><i
								class="mdi me-2 mdi-earth"></i><span class="hide-menu">Google
									Map</span></a></li>
						<li class="sidebar-item"><a
							class="sidebar-link waves-effect waves-dark sidebar-link"
							href="/admin/blank" aria-expanded="false"><i
								class="mdi me-2 mdi-book-open-variant"></i><span
								class="hide-menu">Blank</span></a></li>
						<li class="sidebar-item"><a
							class="sidebar-link waves-effect waves-dark sidebar-link"
							href="/admin/inquiry" aria-expanded="false"><i
								class="mdi me-2 mdi-help-circle"></i><span class="hide-menu">문의
									</span></a></li>
						
					</ul>
				</nav>
				<!-- End Sidebar navigation -->
			</div>
			<!-- End Sidebar scroll-->
			<div class="sidebar-footer">
				<div class="row">
					<div class="col-4 link-wrap">
						<!-- item-->
						<a href="" class="link" data-toggle="tooltip" title=""
							data-original-title="Settings"><i
							class="ti-settings"></i></a>
					</div>
					<div class="col-4 link-wrap">
						<!-- item-->
						<a href="" class="link" data-toggle="tooltip" title=""
							data-original-title="Email"><i
							class="mdi mdi-gmail"></i></a>
					</div>
					<div class="col-4 link-wrap">
						<!-- item-->
						<a href="" class="link" data-toggle="tooltip" title=""
							data-original-title="Logout"><i
							class="mdi mdi-power"></i></a>
					</div>
				</div>
			</div>
		</aside>
	
		<!-- ============================================================== -->
		<!-- End Left Sidebar - style you can find in sidebar.scss  -->
		<!-- ============================================================== -->
		<!-- ============================================================== -->
		<!-- Page wrapper  -->
		<!-- ============================================================== -->
		<div class="page-wrapper">
			<!-- ============================================================== -->
			<!-- Bread crumb and right sidebar toggle -->
			<!-- ============================================================== -->
			<div class="page-breadcrumb">
				<div class="row align-items-center">
					<div class="col-md-6 col-8 align-self-center">
						<h3 class="page-title mb-0 p-0">Table</h3>
						<div class="d-flex align-items-center">
							<nav aria-label="breadcrumb">
								<ol class="breadcrumb">
									<li class="breadcrumb-item"><a href="#">Home</a></li>
									<li class="breadcrumb-item active" aria-current="page">
										Table</li>
								</ol>
							</nav>
						</div>
					</div>
					<div class="col-md-6 col-4 align-self-center">
						<div class="text-end upgrade-btn">
							<a href="https://www.wrappixel.com/templates/materialpro/"
								class="btn btn-danger d-none d-md-inline-block text-white"
								target="_blank">Upgrade to Pro</a>
						</div>
					</div>
				</div>
			</div>
			<!-- ============================================================== -->
			<!-- End Bread crumb and right sidebar toggle -->
			<!-- ============================================================== -->
			<!-- ============================================================== -->
			<!-- Container fluid  -->
			<!-- ============================================================== -->
			<div class="container-fluid">
				<!-- ============================================================== -->
				<!-- Start Page Content -->
				<!-- ============================================================== -->
				<div class="row">
					<!-- column -->

			



				</div>
			
			</div>
			<!-- ============================================================== -->
			<!-- End Container fluid  -->
			<!-- ============================================================== -->
			<!-- ============================================================== -->
			<!-- footer -->
			<!-- ============================================================== -->
			<footer class="footer">
				© 2021 Material Pro Admin by <a href="https://www.wrappixel.com/">wrappixel.com
				</a>
			</footer>
		</div>
	</div>



	<script src="/resources/admin/assets/plugins/jquery/dist/jquery.min.js"></script>
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