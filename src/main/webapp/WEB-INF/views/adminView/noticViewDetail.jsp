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
	<h1>상세보기 페이지</h1>

	
	<div class="page-wrapper">

		

		<div class="container-fluid">

			<div class="noticeBoard">
				<form action="/admin/noticePOST" method="post" name="frm">
					<div class="mb-3 mt-3">
						<input type="text" class="form-control" id="notWriter"
							name="Writer" placeholder="작성자" />
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
							onclick="return checkCheck()" value="글 수정">
					</div>

				</form>

			</div>






		</div>




		
	</div>


</body>
</html>