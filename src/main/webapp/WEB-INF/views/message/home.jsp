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
	const messageType = {
			RECEIVED_MESSAGE : "received",
			SENT_MESSAGE : "sent"
	}

	let receivedMessageDisplayStartPoint = 0;
	let receivedMessageDisplayAmount = 30;
	
	let sentMessageDisplayStartPoint = 0;
	let sentMessageDisplayAmount = 30;
	
	let uid = "dooly";
	
	$(window).on(
			'load',
			function() {
				refreshMessage(messageType.RECEIVED_MESSAGE,
						receivedMessageDisplayStartPoint,
						receivedMessageDisplayAmount);
				
				switchWindow("#receivedMessage");
				changeMessageOptionBtnColor("#receivedMessageBtn");
				
				closeDetailWindow(messageType.RECEIVED_MESSAGE);
				closeDetailWindow(messageType.SENT_MESSAGE);
				
				$("#receivedMessageBtn").click(
						function() {
							refreshMessage(messageType.RECEIVED_MESSAGE,
									receivedMessageDisplayStartPoint,
									receivedMessageDisplayAmount);
							switchWindow("#receivedMessage");
							changeMessageOptionBtnColor(this);
							
						});
				
				$("#closeReceivedDetailBtn").click(function(){
					closeDetailWindow(messageType.RECEIVED_MESSAGE);
				});
				
				
				$("#sentMessageBtn").click(
						function() {
							refreshMessage(messageType.SENT_MESSAGE,
									sentMessageDisplayStartPoint,
									sentMessageDisplayAmount);
							switchWindow("#sentMessage");
							changeMessageOptionBtnColor(this);
						});

				$("#closeSentDetailBtn").click(function(){
					closeDetailWindow(messageType.SENT_MESSAGE);
				});
				
				
				$("#writeMessageBtn").click(function() {
					switchWindow("#writeMessage");
					changeMessageOptionBtnColor(this);
				});
				
			});

	function getReceivedMessage(startPoint) {
		let result;

		$.ajax({
			url : "http://localhost:8081/message/"+ uid +"/received/" + startPoint,
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

	function getSentMessage(startPoint) {
		let result;

		$.ajax({
			url : "http://localhost:8081/message/" + uid + "/sent/" + startPoint,
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
	
	function getMessage(messageNo) {
		let result;

		$.ajax({
			url : "http://localhost:8081/message/" + uid + "/" + messageNo,
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
	
	function refreshMessage(outputMessageType, startPoint, amountToShow) {
		let data;
		
		switch(outputMessageType){
			case messageType.RECEIVED_MESSAGE:
				data = getReceivedMessage(startPoint);
				break;
			case messageType.SENT_MESSAGE:
				data = getSentMessage(startPoint);
				break;
			default:
				console.log('error');
		}
		
		let messageCnt = data.messageCnt;
		let messages = data.messages;

		let output = makeMessagesTable(messages, outputMessageType);
		
		switch(outputMessageType){
			case messageType.RECEIVED_MESSAGE:
				$("#receivedMessageTableBody").html(output);
				break;
			case messageType.SENT_MESSAGE:
				$("#sentMessageTableBody").html(output);
				break;
		}
		
		
		bindMessageRowClickEvent();
	}
	
	function makeMessagesTable(messages, outputMessageType){
		let output = "";
		let cnt = 0;
		for(let message of messages){
			output += `
				<tr class='messageRow' data-messageType='\${outputMessageType}' data-messageNo='\${message.messageNo}' style='cursor: pointer;'>
					<td>\${message.writer}</td>
					<td style="overflow: hidden;">\${message.title}</td>
					<td>\${message.writeDate}</td>
				</tr> `;
			cnt++;
		}
		return output;
	}
	
	function bindMessageRowClickEvent(){
		$(".messageRow").on("click",function(){
			let clickedMessageType = $(this).attr('data-messageType');
			let messageNo = $(this).attr('data-messageNo');
			
			openDetailWindow(clickedMessageType);
			
			let data = getMessage(messageNo);
			let output = makeMessageDetailDiv(data);
			
			console.log(output);
			let selectedDiv = getSelectedDiv(clickedMessageType);
			$(selectedDiv).children(".detail").children(".detail-display").html(output);
		})
	}
	
	function getSelectedDiv(clickedMessageType){
		let selectedDiv = "";
		
		switch(clickedMessageType){
			case messageType.RECEIVED_MESSAGE:
				selectedDiv = "#receivedMessage";
				break;
			case messageType.SENT_MESSAGE:
				selectedDiv = "#sentMessage";
				break;
			default:
				console.log("error");
		}
		
		return selectedDiv;
	}
	
	function openDetailWindow(clickedMessageType){
		let selectedDiv = getSelectedDiv(clickedMessageType);
			
		$(selectedDiv).children(".list").hide();
		$(selectedDiv).children(".detail").show();
	}

	function closeDetailWindow(clickedMessageType){
		let selectedDiv = getSelectedDiv(clickedMessageType);

		$(selectedDiv).children(".list").show();
		$(selectedDiv).children(".detail").hide();
	}
	
	function makeMessageDetailDiv(data){
		let output = "";
		
		output += `
		<h1 style="color: black; height: 10%; width: 100%; word-break: break-all; overflow: auto; padding : 0px; margin: 0px;">\${data.message.title}</h1>
		
		<div style="color: gray;">
			<div style="height: 5%;">글쓴이 : \${data.message.writer}</div>
			<div style="height: 5%;">작성일 : \${data.message.writeDate}</div>
		</div>
		
		<hr style="height: 3%; padding : 0px; margin: 0px;" />
		
		<div style="height: 55%; width: 100%; word-break: break-all; overflow: auto; font-size:20px">\${data.message.content}</div>
		
		<div style="height: 15%; width: 100%; word-break: break-all; overflow: auto; font-size:20px">
			<div style="color: gray; font-size: 15px;">첨부파일:</div>`;

		for(let file of data.messageFiles){
			output += `
				<div>\${file.uploadName}</div>`;
		}
				
		output += `</div>`;
		
		return output;
	}
	
	function switchWindow(windowToShow) {
		$("#receivedMessage").hide();
		$("#sentMessage").hide();
		$("#writeMessage").hide();
		
		$(windowToShow).show();
	}
	
	function changeMessageOptionBtnColor(button){
		$("#receivedMessageBtn").css("color","#D3D3D3");
		$("#sentMessageBtn").css("color","#D3D3D3");
		$("#writeMessageBtn").css("color","#D3D3D3");
		
		$(button).css("color","black");
	}
	
	
	
	
	
	
	
	
	
//	function
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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

.detail {
	color: black;
}

.list {
	height: 100%;
	overflow-y: auto;
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

				<div style="height: 100%; overflow-y: auto; overflow-x: auto;">
					<div
						style="background-color: white; height: 100%; border-radius: 10px;">

						<div id="receivedMessage"
							style="height: 100%; width: 90%; margin: 0 auto;">

							<div class="list">
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

							<div class="detail" style="height: 100%; width: 100%">

								<div class="container" style="height: 30px;">
									<img id="closeReceivedDetailBtn"
										src="/resources/images/message/angles-left-solid.svg"
										style="height: 30px; width: 30px; cursor: pointer;" />
								</div>

								<div class="detail-display"
									style="height: calc(100% - 30px); width: 100%"></div>
							</div>

						</div>

						<div id="sentMessage"
							style="height: 100%; width: 90%; margin: 0 auto;">

							<div class="list">
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

							<div class="detail" style="height: 100%; width: 100%">
								<div class="container" style="height: 30px;">
									<img id="closeSentDetailBtn"
										src="/resources/images/message/angles-left-solid.svg"
										style="height: 30px; width: 30px; cursor: pointer;" />
								</div>

								<div class="detail-display"
									style="height: calc(100% - 30px); width: 100%"></div>
							</div>

						</div>


						<div id="writeMessage" style="width: 90%; margin: 0 auto;">
							<div id="writeMessageInterface"
								style="width: 90%; margin: 0 auto;">

								<div style="color: black;">받는 이</div>
								<div class="write-receiver-div" style="width: 100%">
									<input type="text" class="form-control"
										class="newmessage-receiver" placeholder="아이디를 입력하세요"
										name="receiver" style="width: 80%; float: left;">
									<button type="button" class="btn btn-success"
										style="float: left;">+</button>
									<button type="button" class="btn btn-danger">-</button>
								</div>

								<div style="color: black;">메세지 내용</div>
								<textarea class="form-control" rows="15" id="written-content"
									name="content"></textarea>
								<div
									style="background-color: blue; width: 100%; height: 100px; text-align: center; display: flex; justify-content: center; align-content: center; flex-direction: column;">
									파일 첨부</div>
							</div>

						</div>

					</div>
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
