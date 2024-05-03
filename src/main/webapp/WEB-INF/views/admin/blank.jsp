<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:import url="./adminHeader.jsp"></c:import>

	<c:import url="./adminSidebar.jsp"></c:import>

	<!-- 내용 추가 -->
	<div class="container-fluid">
		<!-- ============================================================== -->
		<!-- Start Page Content -->
		<!-- ============================================================== -->
		<!-- Row -->
		<div class="row">
			<!-- Column -->
			<div class="col-lg-4 col-xlg-3 col-md-5">
				<div class="card">
					<div class="card-body profile-card">
						<center class="mt-4">
							<img src="../assets/images/users/5.jpg" class="rounded-circle"
								width="150" />
							<h4 class="card-title mt-2">Hanna Gover</h4>
							<h6 class="card-subtitle">Accoubts Manager Amix corp</h6>
							<div class="row text-center justify-content-center">
								<div class="col-4">
									<a href="javascript:void(0)" class="link"> <i
										class="icon-people" aria-hidden="true"></i> <span
										class="value-digit">254</span>
									</a>
								</div>
								<div class="col-4">
									<a href="javascript:void(0)" class="link"> <i
										class="icon-picture" aria-hidden="true"></i> <span
										class="value-digit">54</span>
									</a>
								</div>
							</div>
						</center>
					</div>
				</div>
			</div>
			<!-- Column -->
			<!-- Column -->
			<div class="col-lg-8 col-xlg-9 col-md-7">
				<div class="card">
					<div class="card-body">
						<form class="form-horizontal form-material mx-2">
							<div class="form-group">
								<label class="col-md-12 mb-0">Full Name</label>
								<div class="col-md-12">
									<input type="text" placeholder="Johnathan Doe"
										class="form-control ps-0 form-control-line">
								</div>
							</div>
							<div class="form-group">
								<label for="example-email" class="col-md-12">Email</label>
								<div class="col-md-12">
									<input type="email" placeholder="johnathan@admin.com"
										class="form-control ps-0 form-control-line"
										name="example-email" id="example-email">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-12 mb-0">Password</label>
								<div class="col-md-12">
									<input type="password" value="password"
										class="form-control ps-0 form-control-line">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-12 mb-0">Phone No</label>
								<div class="col-md-12">
									<input type="text" placeholder="123 456 7890"
										class="form-control ps-0 form-control-line">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-12 mb-0">Message</label>
								<div class="col-md-12">
									<textarea rows="5" class="form-control ps-0 form-control-line"></textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-12">Select Country</label>
								<div class="col-sm-12 border-bottom">
									<select
										class="form-select shadow-none ps-0 border-0 form-control-line">
										<option>London</option>
										<option>India</option>
										<option>Usa</option>
										<option>Canada</option>
										<option>Thailand</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12 d-flex">
									<button class="btn btn-success mx-auto mx-md-0 text-white">Update
										Profile</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- Column -->
		</div>
		<!-- Row -->
		<!-- ============================================================== -->
		<!-- End PAge Content -->
		<!-- ============================================================== -->
		<!-- ============================================================== -->
		<!-- Right sidebar -->
		<!-- ============================================================== -->
		<!-- .right-sidebar -->
		<!-- ============================================================== -->
		<!-- End Right sidebar -->
		<!-- ============================================================== -->
	</div>




	<c:import url="./adminFooter.jsp"></c:import>
</body>
</html>