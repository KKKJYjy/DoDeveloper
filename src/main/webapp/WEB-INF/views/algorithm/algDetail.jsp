<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@page import="java.io.Console"%>
<%@page import="com.dodeveloper.algorithm.vodto.AlgDetailDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<title>Algorithm List - DoDeveloper</title>
<meta content="" name="description" />
<meta content="" name="keywords" />

<!-- Favicons -->
<link href="/resources/assets/img/favicon.png" rel="icon" />
<link href="/resources/assets/img/apple-touch-icon.png"
	rel="apple-touch-icon" />

<!-- Fonts -->
<link href="https://fonts.googleapis.com" rel="preconnect" />
<link href="https://fonts.gstatic.com" rel="preconnect" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;1,300;1,400;1,500;1,600;1,700;1,800&family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap"
	rel="stylesheet" />

<!-- Vendor CSS Files -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />
<link href="/resources/assets/vendor/glightbox/css/glightbox.min.css"
	rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"
	rel="stylesheet" />
<link href="/resources/assets/vendor/aos/aos.css" rel="stylesheet" />

<!-- Template Main CSS File -->
<link href="/resources/assets/css/main.css" rel="stylesheet" />

<!-- =======================================================
  * Template Name: Append
  * Template URL: https://bootstrapmade.com/append-bootstrap-website-template/
  * Updated: Mar 17 2024 with Bootstrap v5.3.3
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!--  pyscript -->
<link rel="stylesheet" href="https://pyscript.net/alpha/pyscript.css" />
<script defer src="https://pyscript.net/alpha/pyscript.js"></script>
<!--  리스트 길이 구하는 함수 포함 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<script>
	$(function () {
		
		
		$.ajax({
			url : "",
			data : {
				
				"": ,
			},
			type : "post",
			dataType : "text", // 수신받을 데이터의 타입
			success : function(data) {
			console.log(data);
			
			
			},
		});
		
		
		
	});
	
	
</script>

</head>


<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="../header.jsp"%>
	<%
	String boardNo = request.getParameter("boardNo");
	session.setAttribute("boardNo", boardNo);
	%>

	<main id="main">
		<!-- Basic Section - Algorithm Page -->
		<section id="algorithm" class="basic">
			<div class="container">
				<h1>${algDetailList[0].algDetailTitle}</h1>
				<h1>${algDetailList[0].algDetailContent}</h1>
				<h1>${algDetailList[1].algDetailContent}</h1>

				<h2>${fn:length(algDetailList)}</h2>

				<h1>alg</h1>
				
				


				<py-script> def bubbleSort(arr): n = len(arr) for i in
				range(n-1): swapped = False for j in range(0,n-i-1): if
				arr[j]>arr[j+1]: swapped = True arr[j], arr[j+1] = arr[j+1],arr[j]

				if not swapped: return arr = [64,34,25,12,22,11,90] bubbleSort(arr)

				print("Sorted array is:") for i in range(len(arr)): print("% d" %
				arr[i], end=" ") </py-script>
				<py-script> ${algDetailList[0].algDetailContent} </py-script>
				<py-script> ${algDetailList[1].algDetailContent} </py-script>





				<div id='test' type="text">???</div>

				<div>${algDetailList}</div>

				<div>${algDetailList[0].algBoardNo}</div>





				<c:forEach var="algDetail" items="${algDetailList}" begin="0"
					end="${fn:length(algDetailList)}">

					<div class="container mt-3">
						<h2>${algDetail.algDetailTitle}</h2>
						<div>${algDetail.algDetailNo}</div>
						<div class="mt-4 p-5 bg-primary text-white rounded">
							<h1>code</h1>
							<p>${algDetail.algDetailContent}</p>
							<h1>result</h1>
							<p><py-script> ${algDetail.algDetailContent} </py-script></p>
						</div>
					</div>
					<div></div>
				</c:forEach>




			</div>
		</section>

		<form method="get" action="/algorithm/writeDetailPOST">
			<div class="btns">
				<button type="submit" class="btn btn-info">글쓰기</button>
				<button type="button" class="btn btn-info"
					onclick="location.href='/algorithm/modifyAlgDetail';">글수정</button>
			</div>
		</form>
		<button type="button" class="btn btn-danger"
			onclick="location.href='/algorithm/listAll';">알고리즘목록</button>

		<!-- End Basic Section -->
	</main>

	<%@ include file="../footer.jsp"%>

	<!-- Scroll Top Button -->
	<a href="#" id="scroll-top"
		class="scroll-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Preloader -->
	<div id="preloader">
		<div></div>
		<div></div>
		<div></div>
		<div></div>
	</div>

	<!-- Vendor JS Files -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="/resources/assets/vendor/glightbox/js/glightbox.min.js"></script>
	<script
		src="/resources/assets/vendor/purecounter/purecounter_vanilla.js"></script>
	<script
		src="/resources/assets/vendor/imagesloaded/imagesloaded.pkgd.min.js"></script>
	<script
		src="/resources/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
	<script src="/resources/assets/vendor/aos/aos.js"></script>
	<script src="/resources/assets/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="/resources/assets/js/main.js"></script>
</body>
</html>
