<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<title>Message - DoDeveloper</title>
<meta content="" name="description" />
<meta content="" name="keywords" />

<!-- 제이쿼리 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

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
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
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

<script>
	let receivedMessageDisplayStartPoint = 0;
	let receivedMessageDisplayAmount = 30;

	let sentMessageDisplayStartPoint = 0;
	let sentMessageDisplayAmount = 30;

	$(window).on(
			'load',
			function() {
				showSentMessage(sentMessageDisplayStartPoint,
						sentMessageDisplayAmount);
				showReceivedMessage(
						receivedMessageDisplayStartPoint,
						receivedMessageDisplayAmount);

				$("#receivedMessageBtn").click(
						function() {
							showReceivedMessage(
									receivedMessageDisplayStartPoint,
									receivedMessageDisplayAmount);
							switchWindow($("#receivedMessage"));
						});

				$("#sentMessageBtn").click(
						function() {
							showSentMessage(sentMessageDisplayStartPoint,
									sentMessageDisplayAmount);
							switchWindow($("#sentMessage"));
						});

				$("#writeMessageBtn").click(function() {
					switchWindow($("#writeMessage"));
				});
			});

	function getReceivedMessage() {
		let result;

		$.ajax({
			url : "http://localhost:8081/message/dooly/received/0",
			method : "get",
			dataType : "json",
			async : false,
			success : function(data) {
				result = data;
			},
			error : function() {
				console.log("실패");
			}
		})

		return result;
	}

	function showReceivedMessage(startPoint, amountToShow) {
		let data = getReceivedMessage();
		let messageCnt = data.messageCnt;
		let messages = data.messages;

		let output = "";
		for(let message of messages){
			output += `
				<tr>
					<td>\${message.writer}</td>
					<td>\${message.title}</td>
					<td>\${message.writeDate}</td>
				</tr> `;
		}

		$("#receivedMessageTableBody").html(output);

	}

	function getSentMessage() {
		let result;

		$.ajax({
			url : "http://localhost:8081/message/dooly/sent/0",
			method : "get",
			dataType : "json",
			async : false,
			success : function(data) {
				result = data;
			},
			error : function() {
				console.log("실패");
			}
		})

		return result;
	}

	function showSentMessage(startPoint, amountToShow) {
		let data = getSentMessage();
		let messageCnt = data.messageCnt;
		let messages = data.messages;

		let output = "";
		for(let message of messages){
			output += `
				<tr>
					<td>\${message.writer}</td>
					<td>\${message.title}</td>
					<td>\${message.writeDate}</td>
				</tr> `;
		}

		$("#sentMessageTableBody").html(output);
	}

	function switchWindow(windowToShow) {
		$("#receivedMessage").hide();
		$("#sentMessage").hide();
		$("#writeMessage").hide();
		windowToShow.show();
	}
</script>
<style type="text/css">
body {
	font-family: 'D2Coding'
}

.message-menu {
	width: 33%;
}

.message-menu-btn {
	width: 33%;
	font-weight: lighter;
	font-size: large;
}
</style>
</head>

<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="../header.jsp"%>

	<main id="main">
		<!-- Basic Section - Message Page -->
		<section id="message" class="basic">
			<div class="container" style="height: 100vh;">

				<div style="display: flex; justify-content: space-between;">

					<button id="receivedMessageBtn" type="button"
						class="btn btn-light message-menu-btn">받은 메세지 출력</button>

					<button id="sentMessageBtn" type="button"
						class="btn btn-light message-menu-btn">보낸 메세지 출력</button>

					<button id="writeMessageBtn" type="button"
						class="btn btn-light message-menu-btn">메세지 작성</button>
				</div>

				<div id="receivedMessage"
					style="height: 100%; overflow-y: auto; overflow-x: auto;">
					<div
						style="background-color: white; height: 100%; border-radius: 10px;">
						
						<div style="width: 90%; margin: 0 auto;">
							<table class="table">
								<thead>
									<tr>
										<th class="col-1">작성자</th>
										<th class="col-3">제목</th>
										<th class="col-2">작성일</th>
									</tr>
								</thead>
								<tbody id="receivedMessageTableBody">

								</tbody>
							</table>

							<ul class="pagination">
								<li class="page-item"><a class="page-link" href="#">&lt&lt</a></li>
								<li class="page-item"><a class="page-link" href="#">1</a></li>
								<li class="page-item"><a class="page-link" href="#">2</a></li>
								<li class="page-item"><a class="page-link" href="#">3</a></li>
								<li class="page-item"><a class="page-link" href="#">&gt&gt</a></li>
							</ul>

						</div>
					</div>
				</div>

				<div id="sentMessage"
					style="height: 100%; overflow-y: auto; overflow-x: auto; display: none;">
					<div
						style="background-color: white; height: 100%; border-radius: 10px;">
						<div style="width: 90%; margin: 0 auto;">
							<table class="table">
								<thead>
									<tr>
										<th class="col-1">작성자</th>
										<th class="col-3">제목</th>
										<th class="col-2">작성일</th>
									</tr>
								</thead>
								<tbody id="sentMessageTableBody">

								</tbody>
							</table>

							<ul class="pagination">
								<li class="page-item"><a class="page-link" href="#">&lt&lt</a></li>
								<li class="page-item"><a class="page-link" href="#">1</a></li>
								<li class="page-item"><a class="page-link" href="#">2</a></li>
								<li class="page-item"><a class="page-link" href="#">3</a></li>
								<li class="page-item"><a class="page-link" href="#">&gt&gt</a></li>
							</ul>

						</div>
					</div>
				</div>

				<div id="writeMessage"
					style="height: 100%; overflow-y: auto; overflow-x: auto; display: none;">
					<h1>메세지를 출력 합니다.</h1>

				</div>

			</div>
		</section>
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
