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
	<c:set var="contextPath" value="<%=request.getContextPath()%>" />
	<c:import url="./adminHeader.jsp"></c:import>

	<c:import url="./adminSidebar.jsp"></c:import>
	
	
	<table class="table table-hover">
			<thead>
				<tr>
					<th>회원아이디</th>
					<th>회원이름</th>
					<th>이메일</th>
					<th>careers</th>
					<th>가입일</th>
					<th>회원상태</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${userList }">
					<c:choose>
							<tr>
								<td>${user.userId }</td>
								<td>${user.userName }</td>
								<td>${user.email }</td>
								<td class="postDate">${user.registerDate }</td>
								<td>${user.status }</td>
							
							</tr>
					</c:choose>

				</c:forEach>
			</tbody>
		</table>
	
	
	
	<c:import url="./adminFooter.jsp"></c:import>
	
</body>
</html>