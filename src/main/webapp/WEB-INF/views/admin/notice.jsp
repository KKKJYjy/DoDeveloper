<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	function checkCheck() {

		if (frm.title.value == "") {

			frm.title.focus();
			alert('제목을 입력해주세요');

			return false;
		}

		else if (frm.content.value == "") {

			frm.content.focus();
			alert('내용을 입력해주세요');

			return false;
		} else {
			alert('저장되었습니다');

		}

	}

	$(document).ready(function() {

		mobileSize();

		function mobileSize() {
			var realSize = window.innerWidth;
			$("noticeInput").css("width", realSize);
			console.log("디바이스 실사이즈:" + realSize);
			console.log("모바일 실사이즈:" + $("body").css("width"));
		}

	});
</script>
<style>
#noticeInput {
	width: 1200px;
	height: 500px;
	margin: 0 auto;
	padding: 10px;
	border: 0;

	/* height: 500px;
	width: 1300px;
	position: relative;
	margin: 0 auto;
	top: 9%;
	left: 9%;  */
}

#notTitle {
	width: 1200px;
	margin: 0 auto;
	padding: 10px;
	border: 0;

	/* width: 1300px;
	position: relative;
	top: 9%;
	left: 9%; */
}

#notWriter {
	width: 1200px;
	margin: 0 auto;
	padding: 10px;
	border: 0;

	/* width: 1300px;
	position: relative;
	top: 9%;
	left: 9%;  */
}

.sendNotice {
	position: relative;
	left: 83%;
	/* left: 1137%;

	/* position: relative;
	left: 1358px;  */
}
</style>
</head>
<body>
	<c:import url="./adminHeader.jsp"></c:import>


	<c:import url="./adminSidebar.jsp"></c:import>
	<div class="page-wrapper">

		<c:import url="./adminMiniHeader.jsp"></c:import>

		<div class="container-fluid">

			<div class="noticeBoard">
				<form action="/admin/noticePOST" method="post" name="frm">
					<div class="mb-3 mt-3">
						<textarea class="form-control" id="notWriter"
							name="writer" readonly="readonly"
							 >${sessionScope.loginMember.userId}</textarea>
					</div>

					<div class="mb-3 mt-3">
						<input type="text" class="form-control" id="notTitle" name="title"
							placeholder="제목을 입력하세요." />
					</div>


					<div class="mb-3 mt-3">
						<textarea class="form-control" id="noticeInput" name="content"
							placeholder="내용을 입력하세요"></textarea>
					</div>

					<div class="btns">

						<input type="submit" class="btn btn-primary sendNotice"
							onclick="return checkCheck()" value="글 저장">
					</div>

				</form>

			</div>






		</div>




		<c:import url="./adminFooter.jsp"></c:import>
	</div>
</body>
</html>