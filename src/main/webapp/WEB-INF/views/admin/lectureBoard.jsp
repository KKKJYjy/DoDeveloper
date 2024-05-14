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
	$(function() {

		let pageNo = '${param.pageNo}';
		if (pageNo == '') {
			pageNo = 1;
		}

		$(`#\${pageNo}`).addClass('active')

	})
</script>
</head>
<body>
	<c:import url="./adminHeader.jsp"></c:import>

	<div class="container mt-3">
		<p class="text-center">강의추천게시판</p>
		<ul class="nav nav-tabs nav-justified">
			<li class="nav-item"><a class="nav-link"
				href="/admin/selectBoard">스터디게시판</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/admin/lectureBoard">강의추천게시판</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/admin/algorithmBoard">알고리즘게시판</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/admin/reviewBoard">제직자리뷰게시판</a></li>
		</ul>
	</div>

	<div class="container">
		

		<c:import url="./search.jsp"></c:import>

		<div class="container mt-3">
			<table class="table table-hover">



				<thead>
					<tr>
						<th>글번호</th>
						<th>작성자</th>
						<th>제목</th>
						<th>작성일</th>
						<th>조회수</th>
						<th>좋아요</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${lecBoardList }">
						<tr
							onclick="location.href = '/lecture/viewBoard?lecNo=${board.lecNo}';">
							<td>${board.lecNo }</td>
							<td>${board.lecWriter }</td>
							<td>${board.lecTitle }</td>
							<td>${board.lecPostDate }</td>
							<td>${board.lecReadCount }</td>
							<td>${board.lecLikeCount }</td>
						</tr>


					</c:forEach>
				</tbody>
			</table>
		</div>

		<ul class="pagination">
			<c:if test="${param.pageNo > 1 }">
				<li class="page-item"><a class="page-link"
					href="/admin/lectureBoard?pageNo=${param.pageNo -1 }">Previous</a></li>

			</c:if>
			<c:forEach var="i"
				begin="${pagingInfo.startNumOfCurrentPagingBlock }"
				end="${pagingInfo.endNumOfCurrentPagingBlock }" step="1">
				<li class="page-item" id="${i }"><a class="page-link"
					href="/admin/lectureBoard?pageNo=${i }">${i }</a></li>
			</c:forEach>

			<c:if test="${param.pageNo < pagingInfo.totalPageCnt }">
				<li class="page-item"><a class="page-link"
					href="/admin/lectureBoard?pageNo=${param.pageNo +1 }">Next</a></li>
			</c:if>
		</ul>
	</div>



	<c:import url="./adminFooter.jsp"></c:import>
</body>
</html>