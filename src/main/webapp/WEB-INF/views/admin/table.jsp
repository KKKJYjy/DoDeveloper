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
	
	
	
	<div class="container">
		<h4>게시글 전체 조회 페이지</h4>

		<table class="table table-hover">
			<thead>
				<tr>
					<th>글번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>내용</th>
					<th>장소</th>
					<th>기간</th>
					<th>모집상태</th>
					<th>종료일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${studyBoardList }">
					<tr>
						<td>${board.stuNo }</td>
						<td>${board.stuWriter }</td>
						<td>${board.stuTitle }</td>
						<td>${board.stuContent }</td>
						<td>${board.stuLoc }</td>
						<td>${board.stuDate }</td>
						<td>${board.status }</td>
						<td>${board.endDate }</td>
					</tr>
					
					
				</c:forEach>
			</tbody>
		</table>
		</div>
	
	
	
	
	<c:import url="./adminFooter.jsp"></c:import>
	
	
	
	
	
	
	

</body>
</html>