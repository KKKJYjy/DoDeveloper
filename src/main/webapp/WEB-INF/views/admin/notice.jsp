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
</head>
<body>
	<c:import url="./adminHeader.jsp"></c:import>


	<c:import url="./adminSidebar.jsp"></c:import>
	<div class="page-wrapper">

		<c:import url="./adminMiniHeader.jsp"></c:import>

		<div class="container-fluid">
		
		<div class="lecBoard">
					
							<div class="mb-3 mt-3">
								<label for="lecTitle" class="form-label">제목</label> <input
									type="text" class="form-control" id="lecTitle" name="lecTitle"
									placeholder="제목을 입력하세요." />
							</div>


							<div class="mb-3 mt-3">
								<textarea class="form-control" id="lecReviewInput"
									name="lecReview" placeholder="내용을 입력하세요"
									></textarea>
							</div>

							<!-- 글 수정 & 글 삭제 로그인 한 유저만 가능 -->
							<div class="btns">
								<input type="submit" class="btn btn-success" value="글 저장" /> <input
									type="button" class="btn btn-danger" value="취소"
									onclick="cancelWriteBoard();" />
							</div>


						
					</div>
		
		
		
		</div>


		<c:import url="./adminFooter.jsp"></c:import>
	</div>
</body>
</html>