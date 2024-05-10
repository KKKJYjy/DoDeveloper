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

	<p class="text-center">제직자리뷰게시판</p>
	<ul class="nav justify-content-center box">
		<li class="nav-item"><a class="nav-link"
			href="/admin/selectBoard">스터디게시판</a></li>
		<li class="nav-item"><a class="nav-link"
			href="/admin/lectureBoard">강의추천게시판</a></li>
		<li class="nav-item"><a class="nav-link"
			href="/admin/algorithmBoard">알고리즘게시판</a></li>
		<li class="nav-item"><a class="nav-link"
			href="/admin/reviewBoard">제직자리뷰게시판</a></li>
	</ul>


	<div class="container">
		<h4>리뷰게시글 전체 조회 페이지</h4>


		<c:import url="./search.jsp"></c:import>


		<div class="container mt-3">
			<table class="table table-hover">



				<thead>
					<tr>
						<th>글번호</th>
						<th>작성자</th>
						<th>제목</th>
						<th>부서</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${revBoardList }">
						<tr
							onclick="location.href = '/adminView/reviewDetails?revNo=${board.revNo}';">
							<td>${board.revNo }</td>
							<td>${board.revWriter }</td>
							<td>${board.revTitle }</td>
							<td>${board.revProfession }</td>
							<td>${board.revPostDate }</td>
						</tr>


					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>


	<c:import url="./adminFooter.jsp"></c:import>
</body>
</html>