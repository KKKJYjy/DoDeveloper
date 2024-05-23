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
	
	const searchType = {
			TITLE : "title",
			CONTENT : "content",
			WRITER : "writer",
			RECEIVER : "receiver",
			NONE : "none"
	}

	let receivedMessageDisplayStartPoint = 0;
	let receivedMessageDisplayAmount = 10;
	let receivedMessageSearchType = searchType.NONE;
	let receivedMessageSearchWord = '';
	
	let sentMessageDisplayStartPoint = 0;
	let sentMessageDisplayAmount = 10;
	let sentMessageSearchType = searchType.NONE;
	let sentMessageSearchWord = '';
	
	let fileCodeArray = new Array();
	
	let uid = "${loginUser}";
	
	$(window).on(
			'load',
			function() {
				
				refreshMessage(messageType.RECEIVED_MESSAGE,
						receivedMessageDisplayStartPoint,
						receivedMessageDisplayAmount);
				
				refreshMessage(messageType.SENT_MESSAGE,
						sentMessageDisplayStartPoint,
						sentMessageDisplayAmount);
				
				switchWindow("#receivedMessage");
				changeMessageOptionBtnColor("#receivedMessageBtn");
				
				closeDetailWindow(messageType.RECEIVED_MESSAGE);
				closeDetailWindow(messageType.SENT_MESSAGE);

				newMessageReceiverBtnEventBinder();
				
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
				
				$(".dragAndDropDiv").on("dragenter",function(e){
					e.preventDefault();
					e.stopPropagation();
				});
				$(".dragAndDropDiv").on("dragover",function(e){
					e.preventDefault();
					e.stopPropagation();
					$(this).css("background-color", "blue");
				});
				$(".dragAndDropDiv").on("dragleave",function(e){
					e.preventDefault();
					e.stopPropagation();
					$(this).css("background-color", "DodgerBlue");
				});
				$(".dragAndDropDiv").on("drop",function(e){
					e.preventDefault();
					e.stopPropagation();
					$(this).css("background-color", "DodgerBlue");
					
					let files = e.originalEvent.dataTransfer.files;
					
					for(let file of files){
						let formData = new FormData();
						formData.append('file',file);
						uploadName = postFile(formData);
						
						if(uploadName == false){
							alert("파일 명은 50글자 이내여야 합니다!");
							break;
						}
						
						let fileInfo = new Object();
						fileInfo.uploadName = uploadName;

						fileCodeArray.push(fileInfo);
						output = `<div class="uploaded-file">\${fileInfo.uploadName}</div>`;
						$("#uploaded-file-list").append(output);
					}
					
				});
				
				$("#messageSendBtn").click(function(){
					let sendMessage = new Object();
					
					let message = new Object();
					message.writer = uid;
					let title = $('.newmessage-title').val();
					message.title = title;
					let content = $('.newmessage-content').val();
					message.content = content;
					
					let receiverIdList = new Array();
					let receiverInputs = $(".newmessage-receiver");
					for(receiverInput of receiverInputs){
						receiverIdList.push($(receiverInput).val());
					}

					let fileList = fileCodeArray;
					fileCodeArray = new Array();
					
					
					sendMessage.message = message;
					sendMessage.receiverIdList = receiverIdList;
					sendMessage.fileList = fileList;

					console.log(JSON.stringify(sendMessage));
					
					if(postMessage(sendMessage)){
						cleanInputTag();
						refreshMessage(messageType.SENT_MESSAGE, sentMessageDisplayStartPoint, sentMessageDisplayAmount);
					}
				});
				
						
				$("#sent-message-search-Btn").on("click",function(){
					messageSearchAndDisplay(messageType.SENT_MESSAGE);
				});
				
				$("#sent-message-search-word").keypress(function(e){
					if(e.which == 13){
						messageSearchAndDisplay(messageType.SENT_MESSAGE);
					}
				});

				
				$("#received-message-search-Btn").on("click",function(){
					messageSearchAndDisplay(messageType.RECEIVED_MESSAGE);
				});
				
				$("#received-message-search-word").keypress(function(e){
					if(e.which == 13){
						messageSearchAndDisplay(messageType.RECEIVED_MESSAGE);
					}
				});
				
				$("#received-message-display-amount-select").on("change", function(){
					receivedMessageDisplayAmount = $(this).val();
					refreshMessage(messageType.RECEIVED_MESSAGE, receivedMessageDisplayStartPoint, receivedMessageDisplayAmount);
				});
				
				$("#sent-message-display-amount-select").on("change", function(){
					sentMessageDisplayAmount = $(this).val();
					refreshMessage(messageType.SENT_MESSAGE, sentMessageDisplayStartPoint, sentMessageDisplayAmount);
				});
				
			});

	function getReceivedMessage(startPoint, amountToShow) {
		let result;

		$.ajax({
			url : "http://localhost:8081/message/"+ uid +"/received/" + startPoint + "/" + amountToShow,
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

	function getSentMessage(startPoint, amountToShow) {
		let result;

		$.ajax({
			url : "http://localhost:8081/message/" + uid + "/sent/" + startPoint + "/" + amountToShow,
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
		let msgSearchType = '';
		let msgSearchWord = '';
		
		switch(outputMessageType){
		case messageType.RECEIVED_MESSAGE:
			msgSearchType = receivedMessageSearchType;
			msgSearchWord = receivedMessageSearchWord;
			break;
		case messageType.SENT_MESSAGE:
			msgSearchType = sentMessageSearchType;
			msgSearchWord = sentMessageSearchWord;
			break;
		default:
			console.log('error');
		}

		data = getMessages(outputMessageType, startPoint, amountToShow, msgSearchType, msgSearchWord);
		
		let messageCnt = data.messageCnt;
		let messages = data.messages;

		let tableOutput = makeMessagesTable(messages, outputMessageType);
		let pagingOutput = makeMessagePaging(messageCnt, outputMessageType);
		
		switch(outputMessageType){
			case messageType.RECEIVED_MESSAGE:
				$("#receivedMessageTableBody").html(tableOutput);
				$("#received-message-pagination").html(pagingOutput);
				break;
			case messageType.SENT_MESSAGE:
				$("#sentMessageTableBody").html(tableOutput);
				$("#sent-message-pagination").html(pagingOutput);
				break;
		}
		
		bindMessageRowClickEvent();
		bindPagingClickEvent();
	}
	
	function getMessages(outputMessageType, startPoint, amountToShow, messageSearchType, messageSearchWord){
		let result;
		let urlString = "http://localhost:8081/message/"+ uid +"/" + outputMessageType + "/" + startPoint + "/" + amountToShow;
		
		if(messageSearchType != searchType.NONE && messageSearchWord != ''){
			urlString += "/" + messageSearchType + "/" + messageSearchWord;
		}
		
		console.log(urlString);
		
		$.ajax({
			url : urlString,
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
	
	function makeMessagePaging(messageCnt, outputMessageType){
		let startPoint = 0;
		let amountToShow = 0;
		
		switch(outputMessageType){
			case messageType.RECEIVED_MESSAGE:
				startPoint = receivedMessageDisplayStartPoint;
				amountToShow = receivedMessageDisplayAmount;
				break;
			case messageType.SENT_MESSAGE:
				startPoint = sentMessageDisplayStartPoint;
				amountToShow = sentMessageDisplayAmount;
				break;
		}
		
		let passedMessageCnt = startPoint;
		let aheadMessageCnt = messageCnt - startPoint;

		let passedPageCnt = Math.ceil(passedMessageCnt / amountToShow);

		let currentPageNumber = passedPageCnt + 1;
		let minPageNumber = 1;
		let maxPageNumber = Math.ceil(messageCnt/amountToShow);
		if(maxPageNumber <= 0){
			maxPageNumber = 1;
		}
		
		let output = ``;
		output += `<ul class="pagination">`;
		output += `		<li class="page-item"><a class="page-link \${outputMessageType}page" data-movepoint="\${minPageNumber}">&lt&lt</a></li>`;
		
		for(let i = 2; i > 0; i--){
			if(currentPageNumber - i < minPageNumber){
				continue;
			}
			output += `<li class="page-item"><a class="page-link \${outputMessageType}page" data-movepoint="\${currentPageNumber - i}">\${currentPageNumber - i}</a></li>`;
		}
		
		output += `<li class="page-item"><a class="page-link active \${outputMessageType}page" data-movepoint="\${currentPageNumber}">\${currentPageNumber}</a></li>`;
		
		for(let i = 1; i <= 2; i++){
			if(currentPageNumber + i > maxPageNumber){
				break;
			}
			output += `<li class="page-item"><a class="page-link \${outputMessageType}page" data-movepoint="\${currentPageNumber + i}">\${currentPageNumber + i}</a></li>`;
		}
				
		
		output += `		<li class="page-item"><a class="page-link \${outputMessageType}page" data-movepoint="\${maxPageNumber}">&gt&gt</a></li>`;
		output += `</ul>`;
		
		return output;
	}
	
	function bindMessageRowClickEvent(){
		$(".messageRow").on("click",function(){
			let clickedMessageType = $(this).attr('data-messageType');
			let messageNo = $(this).attr('data-messageNo');
			
			openDetailWindow(clickedMessageType);
			
			let data = getMessage(messageNo);
			let output = makeMessageDetailDiv(data);
			
			let selectedDiv = getSelectedDiv(clickedMessageType);
			$(selectedDiv).children(".detail").children(".detail-display").html(output);

			$(".attatched-file").on("click", function(){
				downloadFile($(this).html());
			})
		})
	}
	
	function bindPagingClickEvent(){
		$("." + messageType.RECEIVED_MESSAGE + "page").on("click", function(){
			receivedMessageDisplayStartPoint = ($(this).attr('data-movepoint') - 1) * receivedMessageDisplayAmount;
			refreshMessage(messageType.RECEIVED_MESSAGE, receivedMessageDisplayStartPoint, receivedMessageDisplayAmount);
		});
		
		$("." + messageType.SENT_MESSAGE + "page").on("click", function(){
			sentMessageDisplayStartPoint = ($(this).attr('data-movepoint') - 1) * sentMessageDisplayAmount;
			refreshMessage(messageType.SENT_MESSAGE, sentMessageDisplayStartPoint, sentMessageDisplayAmount);
		});
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
				<div style="height: 5%; width: 100%; overflow-x: auto;">수령인 : `;
			
		for(let receiver of data.receivers){
			output +=`\${receiver}, `;
		}
		output = output.substr(0, output.lastIndexOf(','));
		
		output += `
				</div>
			</div>`;
		
		
		output += `
		
			<hr style="height: 3%; padding : 0px; margin: 0px;" />
			
			<div style="height: 50%; width: 100%; word-break: break-all; overflow: auto; font-size:20px">\${data.message.content}</div>
			
			<div style="height: 15%; width: 100%; word-break: break-all; overflow: auto; font-size:20px">
				<div style="color: gray; font-size: 15px;">첨부파일:</div>`;

		for(let file of data.messageFiles){
			output += `
				<div class="attatched-file">\${file.uploadName}</div>`;
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
	
	
	
	
	
	
	function newMessageReceiverBtnEventBinder(){
		$(".addNewReceiver").on("click",function(){
			addNewMessageReceiver();
		});
		
		$(".removeNewReceiver").on("click",function(){
			removeNewMessageReceiver($(this).parent());
		});
	}
	
	let receiverInputCnt = 0;
	function addNewMessageReceiver(){
		let output = `
			<div id="write-receiver-div-\${receiverInputCnt}" class="write-receiver-div" style="width: 100%">
				<input type="text" class="form-control newmessage-receiver" placeholder="아이디를 입력하세요"
					name="receiver" style="width: 80%; float: left;">
				<button type="button" class="btn btn-success addNewReceiver"
					style="float: left;">+</button>
				<button type="button" class="btn btn-danger removeNewReceiver">-</button>
			</div>`;
		$("#newmessage-receiver-id-list").append(output);
		
		$("#write-receiver-div-" + receiverInputCnt).children(".addNewReceiver").on("click",function(){
			addNewMessageReceiver();
		});
		
		$("#write-receiver-div-" + receiverInputCnt).children(".removeNewReceiver").on("click",function(){
			removeNewMessageReceiver($(this).parent());
		});+
		
		receiverInputCnt++;
	}
	
	function removeNewMessageReceiver(tagToRemove){
		if( $('.write-receiver-div').length <= 1 ){
			return;
		}
		tagToRemove.remove();
	}
	
	function messageSearchAndDisplay(messageTypeInput){

		switch(messageTypeInput){
			case messageType.RECEIVED_MESSAGE:
				receivedMessageSearchType = $("#received-message-search-type").val();
				receivedMessageSearchWord = $("#received-message-search-word").val();
				receivedMessageDisplayStartPoint = 0;
				refreshMessage(messageType.RECEIVED_MESSAGE, receivedMessageDisplayStartPoint, receivedMessageDisplayAmount);
				break;
			case messageType.SENT_MESSAGE:
				sentMessageSearchType = $("#sent-message-search-type").val();
				sentMessageSearchWord = $("#sent-message-search-word").val();
				sentMessageDisplayStartPoint = 0;
				refreshMessage(messageType.SENT_MESSAGE, sentMessageDisplayStartPoint, sentMessageDisplayAmount);
				break;
			default:
				console.log("messageSearchAndDisplayError");		
		}
		
	}
	
	
	
	
	
	function postMessage(sendData){
		let result = false;
		$.ajax({
			url : "http://localhost:8081/message",
			method : "post",
			dataType : "text",
			async : false,
			contentType : 'application/json',
			data: JSON.stringify(sendData),
			success : function() {
				alert("성공적으로 메세지를 송신했습니다.");
				result = true;
			},
			error : function() {
				alert("메세지 전송이 실패했습니다.");
			}
		})
		
		return result;
	}
	
	function cleanInputTag(){
		$(".newmessage-receiver").val('');
		$(".newmessage-title").val('');
		$(".newmessage-content").val('');
		$('#uploaded-file-list').empty();
	}
	
	function postFile(formData){
		let result;

		$.ajax({
			url : "http://localhost:8081/message/file",
			method : "post",
			data: formData,
			dataType : "text",
			async : false,
            contentType: false,
            processData: false,
			success : function(data) {
				result = data;
			},
			error : function() {
				console.log("실패");
				result = false;
			}
		})

		return result;
	
	}
	
	function downloadFile(fileName){
		window.location="http://localhost:8081/message/file/" + fileName;
	}
	
	
</script>
<style type="text/css">
body {
	font-family: 'D2Coding'
}

.inpage-interact-window {
	overflow: auto;
}

.message-menu {
	width: 33%;
}

.message-menu-btn {
	width: 33%;
	font-weight: lighter;
	font-size: large;
}

.message-pagination {
	display: flex;
	justify-content: center;
}

.detail {
	color: black;
}

.list {
	height: 100%;
	overflow-y: auto;
}

.pagination {
	cursor: pointer;
}

.uploaded-file {
	color: black;
}

.attatched-file {
	cursor: pointer;
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
					<div class="inpage-interact-window"
						style="background-color: white; height: 100%; border-radius: 10px;">

						<div id="receivedMessage"
							style="height: 100%; width: 90%; margin: 0 auto;">

							<div class="list">
								<div id="received-display-amount-div">
									<select id="received-message-display-amount-select">
										<option value="10">10</option>
										<option value="15">15</option>
										<option value="20">20</option>
									</select>
								</div>
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

								<div id="received-message-pagination" class="message-pagination">

								</div>

								<select id="received-message-search-type">
									<option value="writer">작성자</option>
									<option value="title">제목</option>
									<option value="content">내용</option>
								</select> <input id="received-message-search-word" type="text">
								<button id="received-message-search-Btn">검색</button>

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
								<div id="sent-display-amount-div">
									<select id="sent-message-display-amount-select">
										<option value="10">10</option>
										<option value="15">15</option>
										<option value="20">20</option>
									</select>
								</div>
								<table class="table">
									<thead>
										<tr>
											<th class="col-1">작성자</th>
											<th class="col-3">제목</th>
											<th class="col-2">작성일</th>
										</tr>
									</thead>
									<tbody id="sentMessageTableBody" class="message-tbody">

									</tbody>
								</table>

								<div id="sent-message-pagination" class="message-pagination">

								</div>

								<select id="sent-message-search-type">
									<option value="receiver">수령인</option>
									<option value="title">제목</option>
									<option value="content">내용</option>
								</select> <input id="sent-message-search-word" type="text">
								<button id="sent-message-search-Btn">검색</button>

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


						<div id="writeMessage"
							style="width: 90%; height: 100%; overflow: auto; margin: 0 auto;">

							<div style="color: black;">받는 이</div>
							<div id="newmessage-receiver-id-list">
								<div class="write-receiver-div" style="width: 100%">
									<input type="text" class="form-control newmessage-receiver"
										placeholder="아이디를 입력하세요" name="receiver"
										style="width: 80%; float: left;">
									<button type="button" class="btn btn-success addNewReceiver"
										style="float: left;">+</button>
									<button type="button" class="btn btn-danger removeNewReceiver">-</button>
								</div>
							</div>
							<div style="color: black;">메세지 제목</div>
							<input type="text" class="form-control newmessage-title"
								placeholder="제목을 입력하세요" name="title">
							<div style="color: black;">메세지 내용</div>
							<textarea class="form-control newmessage-content" rows="15"
								id="written-content" name="content"></textarea>
							<div class="dragAndDropDiv"
								style="background-color: DodgerBlue; width: 100%; height: 100px; text-align: center; display: flex; justify-content: center; align-content: center; flex-direction: column;">
								첨부 파일 드래그드롭</div>
							<div id="uploaded-file-list"></div>
							<button id="messageSendBtn" class="btn btn-primary">보내기</button>

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
