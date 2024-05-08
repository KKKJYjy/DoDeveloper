<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	
	
</style>
</head>
<body>
	<c:import url="./adminHeader.jsp"></c:import>

	<c:import url="./adminSidebar.jsp"></c:import>





	<select>
		<option>강의게시판</option>
		<option>스터디게시판</option>
		<option>알고리즘게시판</option>
		<option>재직자리뷰게시판</option>
	</select>
	
	
	
	
	  <p class="text-center">Centered nav:</p>
  <ul class="nav justify-content-center box">
    <li class="nav-item">
      <a class="nav-link" href="#">Link</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Link</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">Link</a>
    </li>
    <li class="nav-item">
      <a class="nav-link disabled" href="#">Disabled</a>
    </li>
  </ul>


	

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