<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div.container {
		display: flex;
		left: 960ps;
	}
</style>
</head>
<body>
	<c:import url="./adminHeader.jsp"></c:import>

	<c:import url="./adminSidebar.jsp"></c:import>

	
	<div class = "container">
	<select>
		<option>강의게시판</option>
		<option>스터디게시판</option>
		<option>알고리즘게시판</option>
		<option>재직자리뷰게시판</option>
	</select>
	</div>





	<c:import url="./adminFooter.jsp"></c:import>
</body>
</html>