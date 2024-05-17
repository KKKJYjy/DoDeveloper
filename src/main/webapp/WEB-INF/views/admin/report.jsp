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
		
		<button id="openModalBtn" onclick="checkCheckbox()">게시글삭제</button>
		
		<table class="table table-light table-hover">



					<thead>
						<tr>
							<th><input id="allCheck" type="checkbox" name="allCheck"/></th>
							<th>회원 아이디</th>
							<th>회원 이름</th>
							<th>이메일</th>
							<th>가입 일자</th>
							<th>상태</th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach var="board" items="${stuBoardList }">

							<tr id="table">
								<td><input type="checkbox" name="rowCheck"
									class="deleteCheckbox" id="myCheckbox" value="${board.userId}" /></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								
							</tr>


						</c:forEach>
					</tbody>
				</table>
		
		
		
		</div>
		
		
		<c:import url="./adminFooter.jsp"></c:import>
		</div>
</body>
</html>