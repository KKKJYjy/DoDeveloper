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
	function resetBtn() {
		window.location.href = "/admin/noticeBoard";
	}
	
	function sendBtn() {
		
		if (upd.title.value == "") {

			upd.title.focus();
			alert('제목을 입력해주세요');

			return false;
		}

		else if (upd.content.value == "") {

			upd.content.focus();
			alert('내용을 입력해주세요');

			return false;
		} else {
			alert('글이 수정되었습니다');

		}
		
	}
</script>

</head>
<body>
	<c:import url="./adminHeader.jsp"></c:import>


	<c:import url="./adminSidebar.jsp"></c:import>
	<div class="page-wrapper">

		<c:import url="./adminMiniHeader.jsp"></c:import>

		<div class="container-fluid">
			<form action="/admin/updatePost" method="post" name="upd">
				<input type="hidden" name="boardNo"
					value="${result.notcBoard.boardNo }">
				<div class="noticeBoard">

					<div class="mb-3 mt-3">
						<textarea class="form-control" id="notWriter" name="writer"
							readonly="readonly">${result.notcBoard.writer}</textarea>
					</div>

					<div class="mb-3 mt-3">
						<label>작성 일자</label>
						<textarea class="form-control" id="notpostDate" name="postDate"
							readonly="readonly">${result.notcBoard.postDate}</textarea>
					</div>

					<div class="mb-3 mt-3">
						<label>제목</label>
						<textarea class="form-control" id="notTitle" name="title">${result.notcBoard.title }</textarea>
					</div>


					<div class="mb-3 mt-3">
						<label>내용</label>
						<textarea class="form-control" id="noticeInput" name="content">${result.notcBoard.content }</textarea>
					</div>

					<div class="btns">

						<button type="submit" class="btn btn-primary sendNotice" onclick="return sendBtn()">글
							저장</button>
						<button type="reset" class="btn btn-primary resetBtn" onclick="resetBtn()">취소</button>


					</div>



				</div>
			</form>





		</div>




		<c:import url="./adminFooter.jsp"></c:import>
	</div>
</body>
</html>