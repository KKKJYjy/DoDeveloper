<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DoDeveloper</title>
<meta content="" name="description" />
<meta content="" name="keywords" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	$(function() {

		$('.status').change(function() {

			let newStatus = $(this).val();
			let userId = $(this).closest('tr').find('.userId').text(); // 해당 행의 userId 값 가져오기

			$.ajax({
				url : "/admin/status",
				type : "post",
				data : {
					"newStatus" : newStatus,
					"userId" : userId
				}, // 보내는 데이터
				success : function(data) {
					// data(json)
					// 통신 성공하면 실행할 내용들....
					console.log(data);

				},
			});

		})
	})
</script>
<style>
.penalty {
	color: red;
}
</style>

</head>
<body>

	<c:set var="contextPath" value="<%=request.getContextPath()%>" />
	<c:import url="./adminHeader.jsp"></c:import>


	<c:import url="./adminSidebar.jsp"></c:import>



	<div class="page-wrapper">

		<c:import url="./adminMiniHeader.jsp"></c:import>


		<div class="container-fluid">

			<!-- 검색 박스 -->
			<div class="nav-item search-box">
				<a class="nav-link text-muted" href="javascript:void(0)"><i
					class="ti-search"></i></a>
				<form class="app-search" style="display: none">
					<input type="text" class="form-control"
						placeholder="Search &amp; enter" /> <a class="srh-btn"><i
						class="ti-close"></i></a>
				</form>
			</div>
			<!-- ============================================================== -->
			<!-- Start Page Content -->
			<!-- ============================================================== -->
			<div class="row">
				<!-- column -->
				<div class="col-sm-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">회원관리</h4>
							<h6 class="card-subtitle">회원조회</h6>
							<div class="table-responsive">
								<table class="table user-table">
									<thead>
										<tr>
											<th class="border-top-0">회원 아이디</th>
											<th class="border-top-0">회원 이름</th>
											<th class="border-top-0">이메일</th>
											<th class="border-top-0">가입 일자</th>
											<th class="border-top-0">누적 경고</th>
											<th class="border-top-0">상태</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="user" items="${userList}">
											<tr id="${user.userId}">
												<td class="userId">${user.userId}</td>
												<td>${user.userName}</td>
												<td>${user.email}</td>
												<td><fmt:formatDate value="${user.registerDate}"
														pattern="yyyy-MM-dd" /></td>
												<c:choose>
													<c:when test="${user.penaltyCnt == null }">
														<td>0</td>
													</c:when>
													<c:otherwise>
														<td class="penalty">${user.penaltyCnt }</td>
													</c:otherwise>
												</c:choose>

												<td><c:choose>
														<c:when test="${sessionScope.loginMember.isAdmin == 'Y'}">
															<c:choose>
																<c:when test="${user.status == '정상회원'}">
																	<select class="form-select form-select-sm status">
																		<option>${user.status}</option>
																		<option>정지회원</option>
																		<option>탈퇴회원</option>
																	</select>
																</c:when>
																<c:when test="${user.status == '정지회원'}">
																	<select class="form-select form-select-sm status">
																		<option>${user.status}</option>
																		<option>정상회원</option>
																		<option>탈퇴회원</option>
																	</select>
																</c:when>
																<c:when test="${user.status == '탈퇴회원'}">
																	<select class="form-select form-select-sm status">
																		<option>${user.status}</option>
																		<option>정상회원</option>
																		<option>정지회원</option>
																	</select>
																</c:when>
															</c:choose>
														</c:when>
														<c:otherwise>
															${user.status}
        												</c:otherwise>
													</c:choose></td>
											</tr>
										</c:forEach>


									</tbody>
								</table>
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
		</div>

		<c:import url="./adminFooter.jsp"></c:import>
	</div>



</body>
</html>