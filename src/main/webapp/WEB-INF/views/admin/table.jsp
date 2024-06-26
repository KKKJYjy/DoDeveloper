<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function goToPage() {
		// 선택된 옵션의 값 읽기
		var selectedOption = document.getElementById("selectBoard").value;

		// 선택된 페이지로 이동
		window.location.href = selectedOption;
	}
</script>
</head>
<body>
	<c:import url="./adminHeader.jsp"></c:import>

	<c:import url="./adminSidebar.jsp"></c:import>

	<div class="page-wrapper">

		<c:import url="./adminMiniHeader.jsp"></c:import>


		<div class="container-fluid">

			<div class="container mt-3">
				<p class="text-center">게시판 조회</p>
				<ul class="nav nav-tabs nav-justified">
					<li class="nav-item"><a class="nav-link"
						href="/admin/selectBoard">스터디 모임</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/admin/lectureBoard">강의추천</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/admin/algorithmBoard">알고리즘</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/admin/reviewBoard">기업리뷰</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/admin/noticeBoard">공지사항</a></li>
				</ul>
			</div>

		</div>





		<!-- column -->















		<c:import url="./adminFooter.jsp"></c:import>
	</div>
</body>
</html>