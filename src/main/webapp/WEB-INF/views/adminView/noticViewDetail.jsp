<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	function checkCheck() {
		window.location.href = "/admin/updateNotice?boardNo=${notice.boardNo}"
	}
</script>
</head>
<body>
	<c:import url="../admin/adminHeader.jsp"></c:import>


	<c:import url="../admin/adminSidebar.jsp"></c:import>


	<div class="page-wrapper">
		<c:import url="../admin/adminMiniHeader.jsp"></c:import>


		<div class="container-fluid">

			<div class="noticeBoard">
				
					<div class="mb-3 mt-3">
						<label>작성자</label>
						<textarea class="form-control" id="notWriter" name="Writer"
							readonly="readonly">${notice.writer}</textarea>
					</div>
					
					<div class="mb-3 mt-3">
						<label>작성 일자</label>
						<textarea class="form-control" id="notpostDate" name="postDate"
							readonly="readonly">${notice.postDate}</textarea>
					</div>

					<div class="mb-3 mt-3">
						<label>제목</label>
						<textarea class="form-control" id="notTitle" name="title" readonly="readonly">${notice.title }</textarea>
					</div>


					<div class="mb-3 mt-3">
						<label>내용</label>
						<textarea class="form-control" id="noticeInput" name="content" readonly="readonly">${notice.content }</textarea>
					</div>

					<div class="btns">

						<input type="button" class="btn btn-primary sendNotice"
							onclick="return checkCheck()" value="글 수정">
					</div>


			</div>

		</div>



		<c:import url="../admin/adminFooter.jsp"></c:import>






	</div>


</body>
</html>