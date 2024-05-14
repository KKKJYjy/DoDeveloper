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

<div class="preloader">
      <div class="lds-ripple">
        <div class="lds-pos"></div>
        <div class="lds-pos"></div>
      </div>
    </div>
    <!-- ============================================================== -->
    <!-- Main wrapper - style you can find in pages.scss -->
    <!-- ============================================================== -->


<div class="page-wrapper">
	<c:import url="./adminHeader.jsp"></c:import>

	

		<div class="container-fluid">
			<!-- ============================================================== -->
			<!-- Start Page Content -->
			<!-- ============================================================== -->
			<div class="row">
				<!-- column -->
				<div class="col-sm-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">Basic Table</h4>
							<h6 class="card-subtitle">
								Add class
								<code>.table</code>
							</h6>
							<div class="table-responsive">
								<table class="table user-table">
									<thead>
										<tr>
											<th class="border-top-0">#</th>
											<th class="border-top-0">First Name</th>
											<th class="border-top-0">Last Name</th>
											<th class="border-top-0">Username</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>Deshmukh</td>
											<td>Prohaska</td>
											<td>@Genelia</td>
										</tr>
										<tr>
											<td>2</td>
											<td>Deshmukh</td>
											<td>Gaylord</td>
											<td>@Ritesh</td>
										</tr>
										<tr>
											<td>3</td>
											<td>Sanghani</td>
											<td>Gusikowski</td>
											<td>@Govinda</td>
										</tr>
										<tr>
											<td>4</td>
											<td>Roshan</td>
											<td>Rogahn</td>
											<td>@Hritik</td>
										</tr>
										<tr>
											<td>5</td>
											<td>Joshi</td>
											<td>Hickle</td>
											<td>@Maruti</td>
										</tr>
										<tr>
											<td>6</td>
											<td>Nigam</td>
											<td>Eichmann</td>
											<td>@Sonu</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
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
	</div>
	
	

	<c:import url="./adminFooter.jsp"></c:import>


</body>
</html>