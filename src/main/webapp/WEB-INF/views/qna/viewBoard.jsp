<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<title>Lecture List - DoDeveloper</title>
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
<link href="/resources/assets/css/lecture/listAll.css" rel="stylesheet" />

<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style>
.content {
	padding: 15px;
	border: 1px solid #595959;
}

.reply {
	display: flex;
	flex-direction: row;
	justify-content: space-around;
}

.replyer {
	margin-right: 10px;
}

.replies {
	font-size: 12px;
	color: #595959;
}
</style>
<script>
let replies = null;

$(function() {
	getAllReplies();
	
	// 댓글 저장 버튼을 누르면
	$(".saveReply").click(function() {
		let replyContent = $('#replyContent').val();
		if (replyContent == '') {
			alert('댓글을 남겨주세요');
			$('#replyContent').focus();
		} else {
			let replyer = preAuth();	
			if (replyer != '') {
				let bNo = ${qnaView.no};
				let newReply = {
					"bNo" : bNo,
					"replyContent" : replyContent,
					"replyer" : replyer
				};  
				
				console.log(JSON.stringify(newReply));
				
					 $.ajax({
						url : 'replyPost',
						type : "post",
						data : JSON.stringify(newReply),    
						headers : {  
							"content-type" : "application/json"
						},
						dataType : "text", 
						async : 'false',
						success : function(data) {
							console.log(data);
							if (data == 'success') {
								$('.replies').empty();
								$('#replyContent').val('');
								
								getAllReplies();
							}
					},
					error : function (data) {  
						console.log(data);
					}
				}); 
			}
		}
	})
	
});

// 댓글 처리 전 로그인 인증
function preAuth() {
	let writer = '${sessionScope.loginMember.userId}';
	if (writer == '') {  // 로그인 하지 않았다
		location.href='/member/login?redirectUrl=qnaReply&no=${qnaView.no}';
		// 로그인 하고 댓글 저장 할 수 있도록 다시 여기로 오게 해야 함.
		writer = '${sessionScope.loginMember.userId}';
	} 
	return writer;
}

function getAllReplies() {
	let boardNo = ${qnaView.no};
	
	$.ajax({
		url : "/qnaAll",
		type : "get",
		dataType : "json", 
		async : 'false',
		success : function(data) {
			console.log(data);
			replies = data;
			outputReplies(data);
			
		},
	});
}

function outputReplies(data) {
	let output = `<div class="list-group replies">`;
	$.each(data, function(i, reply) {
		output += `<ul class="list-group-item list-group-item-action">`;
		output += `<div class='reply' id='reply_\${reply.replyNo}'>`;
		
		output += `<div style='flex:1'>\${reply.replyContent}</div>`;
		output += `<div class='replyer'>\${reply.replyer}</div>`;
		
		// 댓글 작성 날짜 시간 처리
		let diff = processPostDate(reply.writtenDate);
		output += `<div>\${diff}</div>`;
		
		output += `<div class='replyBtns'><img src='/resources/images/gear.png' onclick='showModifyReply(\${reply.replyNo});'/>`;
		output += `<img src='/resources/images/trach.png' onclick='showRemoveReply(\${reply.replyNo});' /></div>`;
		
		output += `</div>`;
		output += `</ul>`;
	});
	
	$(".replies").html(output);
}

function showRemoveReply(replyNo) {
	let user = preAuth();  // 로그인한 유저
	let writer = null;
	$.each(replies, function(i, r) {
		if (replyNo == r.replyNo) {
			writer = r.replyer;  // 댓글 작성자
		}
	});
	
	
	 if (user == writer) { 
		if(window.confirm(replyNo + '번 글을 삭제 할까요?')) {
			 $.ajax({
					url : "/qnaDelete",
					type : "delete",
					headers : {
						"Content-Type" : "application/json",
						
						// PUT, DELETE, PATCH 등의 REST에서 사용되는 HTTP method가 동작하지 않는 과거의 웹 브라우저에서
						// POST 방식으로 동작되도록 한다.
						"X-HTTP-Method-Override" : "POST"
					},
					dataType : "text", // 수신받을 데이터의 타입
					async : 'false',
					success : function(data) {
						console.log(data);
			
						if (data == 'success') {
							getAllReplies();
						}
					},
				}); 
		}
	 }
}

function showModifyReply(replyNo) {
	let user = preAuth();  // 로그인한 유저
	let writer = null;
	$.each(replies, function(i, r) {
		if (replyNo == r.replyNo) {
			writer = r.replyer;  // 댓글 작성자
		}
	});
	
	
	 if (user == writer) { 
	let output = `<div class="modifyReply"><div class="mb-3 mt-3"><label for="modifyReplyContent" class="form-label">댓글 내용: </label>`;
	output += `<textarea cols="600" rows="5" id="modifyReplyContent" class="form-control"></textarea>`;
	// 백틱 이용 방법
	output += `<button type="button" class="btn btn-primary" onclick='modifyReply(\${replyNo});'>댓글수정</button></div></div>`;
	// ""이용방법
  // output += "<button type='button' class='btn btn-primary modifyReplyBtn' onclick='modifyReply(" + replyNo + ");'>댓글수정</button></div></div>";
	
	$(output).insertAfter($(`#reply_\${replyNo}`));
	 } else {
		 alert('본인 댓글만 수정 할 수 있습니다');
	 }
}

function modifyReply(replyNo) {
	let replyContent = $('#modifyReplyContent').val();
	
	let modifyReply = {
			"replyNo" : replyNo,
			"replyContent" : replyContent
	};
	
		  $.ajax({
				url : "/qnaModifyReply",
				type : "put",
				data : JSON.stringify(modifyReply),
				headers : {
					"Content-Type" : "application/json",
					
					// PUT, DELETE, PATCH 등의 REST에서 사용되는 HTTP method가 동작하지 않는 과거의 웹 브라우저에서
					// POST 방식으로 동작되도록 한다.
					"X-HTTP-Method-Override" : "POST"
				},
				dataType : "text", // 수신받을 데이터의 타입
				async : 'false',
				success : function(data) {
					console.log(data);
		
					if (data == 'success') {
						$('.modifyReply').remove();
						getAllReplies();
					}
				},
			}); 
	
}


function processPostDate(writtenDate) {
	let postDate = new Date(writtenDate);  // 댓글 작성일시
	let now = new Date();  // 현재 날짜시간
	
	let diff = (now - postDate) / 1000;   // 시간 차 (초 단위)
	
	let times = [
		{name: "일", time: 60 * 60 * 24},
		{name: "시간", time: 60 * 60},
		{name: "분", time: 60}
	];
	
	for (let val of times) {
		// 시간차를 기준시간(val.time)으로 나눠보자
		let betweenTime = Math.floor(diff / val.time);
		console.log(diff, betweenTime);
		
		if (betweenTime > 0) {
			return betweenTime + val.name + "전";
		}
	}
	
	return "방금전";
	
}
</script>
</head>
<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="../header.jsp"%>

	<main id="main">
		<section id="qna" class="basic">
			<div class="container">

				<div class="qnaBoard">

					<div class="mb-3 mt-3">
						<label>작성자</label>
						<textarea class="form-control" id="qnaWriter" name="Writer"
							readonly="readonly">${qnaView.qnaWriter }</textarea>
					</div>

					<div class="mb-3 mt-3">
						<label>제목</label>
						<textarea class="form-control" id="qnaTitle" name="title"
							readonly="readonly">${qnaView.qnaTitle }</textarea>
					</div>

					<div class="mb-3 mt-3">
						<label>내용</label>
						<textarea class="form-control" id="qnaContent" name="content"
							readonly="readonly">${qnaView.qnaContent }</textarea>
					</div>


					<div class="mb-3 mt-3">
						<label>작성 일자</label>
						<textarea class="form-control" id="postDate" name="postDate"
							readonly="readonly">${qnaView.postDate }</textarea>
					</div>





				</div>

				<div class="btns">

					<button type="button" id="openModalBtn" onclick="checkCheckbox()">댓글</button>
				</div>




				<div class="btns">

					<button type="button" class="btn btn-warning"
						onclick="location.href='/qna/listAll';">목록으로</button>
				</div>

				<div class="writeReply">
					<div class="mb-3 mt-3">
						<label for="replyContent" class="form-label">댓글 내용: </label>
						<textarea rows="cols=" 600" rows="5" id="replyContent"
							class="form-control"></textarea>
						<button type="button" class="btn btn-primary saveReply">댓글
							저장</button>
					</div>
				</div>

				<div class="replies"></div>
			</div>
		</section>
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