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
	
	
	<p class="text-center">강의추천게시판</p>
	<ul class="nav justify-content-center box">
		<li class="nav-item"><a class="nav-link" href="/admin/selectBoard">스터디게시판</a></li>
		<li class="nav-item"><a class="nav-link" href="/admin/lectureBoard">강의추천게시판</a></li>
		<li class="nav-item"><a class="nav-link" href="/admin/algorithmBoard">알고리즘게시판</a></li>
		<li class="nav-item"><a class="nav-link" href="/admin/reviewBoard">제직자리뷰게시판</a></li>
	</ul>
	
	<div class="container">
		<h4>강의추천게시글 전체 조회 페이지</h4>



		<div class="container mt-3">
			<table class="table table-hover">



				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
						<th>좋아요</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${lecBoardList }">
						<tr onclick="location.href = '/adminView/lectureDetails?lecNo=${board.lecNo}';">
							<td>${board.lecNo }</td>
							<td>${board.lecTitle }</td>
							<td>${board.lecWriter }</td>
							<td>${board.lecPostDate }</td>
							<td>${board.lecReadCount }</td>
							<td>${board.lecLikeCount }</td>
						</tr>


					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	
	
	<c:import url="./adminFooter.jsp"></c:import>
</body>
</html>