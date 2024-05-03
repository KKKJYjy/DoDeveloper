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

	<c:import url="./adminSidebar.jsp"></c:import>

	<!-- 내용 추가 -->

	<main id="dashboard">
      <!-- Basic Section - Index Page -->
      <section id="dashboard" class="basic">
        <div class="container">
        	<h1>홈</h1>
        </div>
      </section>
      <!-- End Basic Section -->
    </main>



	<c:import url="./adminFooter.jsp"></c:import>
</body>
</html>