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
<link href="/resources/assets/css/lecture/viewBoard.css"
	rel="stylesheet" />

<!-- =======================================================
  * Template Name: Append
  * Template URL: https://bootstrapmade.com/append-bootstrap-website-template/
  * Updated: Mar 17 2024 with Bootstrap v5.3.3
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style>
.replyer {
	margin-right: 10px;
}

.reply {
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: 10px;
	border-bottom: 1px solid #ccc;
}

<!--
수정 & 삭제 버튼 -->.replyBtns {
	margin-left: inherit;
	margin-bottom: auto;
	float: right;
	float: inherit;
}

.replyEditBtns {
	top: -3%;
}

.replyEditBtns, .replyRemoveBtns {
	position: absolute;
	float: right;
	margin-left: inherit;
	margin-bottom: 200px;
}

.replyEditBtns img, .replyRemoveBtns img {
	width: 30px;
	height: 30px;
}

<!--
좋아요 버튼 -->.btn-group {
	position: relative;
}
</style>
<script>
let replies = null;
// ---------------------------------------------------------------------

// 댓글 작성시 저장하는 js
$(function() {
	// 댓글 목록 가져오기
	getAllReplies();
	
	// 댓글 저장 버튼 클릭 이벤트
	$('.saveReply').click(function() {
		let replyContent = $('#replyContent').val(); // 댓글 내용 가져오기
		if (replyContent === '') {
			// 댓글을 입력안하고 댓글 작성 버튼을 눌렀을 경우
			alert('댓글을 입력해주세요!');
			$('#replyContent').focus(); // 포커스를 입력창에 넣어주기
		} else {
			let replyer = preAuth(); // 댓글 작성자 가져오기
			if (replyer !== '') {
				let bNo = '${lecBoard.lecNo}'; // 게시글 번호 가져오기
				let bType = 1;
				let newReply = {
					"bNo" : bNo + "", // 문자열로 변환
					"bType" : bType,
					"replyContent" : replyContent,
					"replyer" : replyer
				}; // 댓글 객체 생성

				console.log(newReply + '게시글 번호 가져오기');

				// AJAX를 이용하여 댓글 추가 요청 보내기
				$.ajax({
					url : '/reply/' + bType + "/"+ bNo,
					type : 'post',
					data : JSON.stringify(newReply), // 서버에 넘겨주는 데이터
					headers : { // 서버에 보내지는 데이터의 형식이 json임을 알림
						"content-type" : "application/json"
					},
					dataType : "text", // 수신받을 데이터의 타입
					async : 'false',
					success : function(data) { // data(json)
						// 통신 성공하면 실행할 내용들....
						console.log(data);
						if (data == 'success') {
							$('.replies').empty();
							$('#replyContent').val('');

							getAllReplies(); // 댓글 작성 후 모든 댓글 조회
						}
					},
					error : function(data) { // HttpStatus Code가 200이 아닐때...
						console.log(data);
					}
				});
			}
		}
	});
});

// ---------------------------------------------------------------------

// 댓글 작성 & 수정 전 로그인 인증
function preAuth() {
	let writer = '${sessionScope.loginMember.userId}';
	if (writer === '') { // 로그인 하지 않았다면 로그인 페이지로 이동
		location.href = '/member/login?redirectUrl=view&lecNo=${lecBoard.lecNo}';
	}
	return writer;
}

// ---------------------------------------------------------------------

// 전체 댓글 가져오기
function getAllReplies() {
	let bNo = '${lecBoard.lecNo}';
	let bType = 1;
	
	// alert("bNo" + bNo + "bType" + bType);
	
	$.ajax({
		url : "/reply/list/" + bType + "/" + bNo,
		type : "get",
		dataType : "json", // 수신받을 데이터의 타입
		async : 'false',
		success : function(data) { // data(json)
			// 통신 성공하면 실행할 내용들....
			console.log(data);

			replies = data;

			outputReplies(data);
		}
	});
}

// ---------------------------------------------------------------------

// 댓글 data들을 모아서 자리만들기
function outputReplies(data) {
	let output = `<div class="list-group replies">`;
	$.each(data, function(i, reply) {
		output += `<ul class="list-group-item">`;
		output += `<div class='reply' id='reply_\${reply.replyNo}'>`
		output += `<div style='flex:1' >\${reply.replyContent}</div>`; // 댓글 내용
		output += `<div class='replyer'>\${reply.replyer}</div>`; // 작성자
		
		// 댓글 작성의 날짜 & 시간 처리
		let diff = processPostDate(reply.writtenDate);
		output += `<div>\${diff}</div>`; // 댓글 시간
		
		output += `<div class='replyBtns'>`;
		output += `<div class='replyEditBtns'>`;
		output += `<img src='/resources/images/reply/replyedit.png' onclick='showModifyReply(\${reply.replyNo});' />`;
		output += `</div>`;
		output += `<div class='replyRemoveBtns'>`;
		output += `<img src='/resources/images/reply/replytrash.png' onclick='showRemoveReply(\${reply.replyNo});' />`;
		output += `</div>`;
		output += `</div>`;
		
		output += `</div>`;
		output += `</ul>`;
		
	});
	output += `</div>`;
	console.log(output);
	$('.replies').html(output);
}

// ---------------------------------------------------------------------

// 유저가 작성한 댓글 시간 계산
function processPostDate(writtenDate) {
	let postDate = new Date(writtenDate); // 댓글 작성 일시
	let now = new Date(); // 현재 날짜 시간
	
	let diff = (now - postDate) / 1000; // 시간차 (초 단위)
	
	let times = [
		{name : "일", time : 60 * 60 * 24},
		{name : "시간", time : 60 * 60},
		{name : "분", time : 60}
	];
	
	for (let val of times) {
		// 시간 차를 기준 시간(val.time)으로 나누기
		let betweenTime = Math.floor(diff / val.time);
		console.log(diff, betweenTime);
		
		if (betweenTime > 0) {
			return betweenTime + val.name + "전";
		}
	}
	return "방금 전";
}

// ---------------------------------------------------------------------

// 댓글 수정 전 로그인 한 유저만 가능하도록
function showModifyReply(replyNo) {
	// alert(replyNo);
	let user = preAuth(); // 로그인 한 유저
	
	let writer = null; // 작성자를 null로 두고
	
	$.each(replies, function(i, r) {
		if (replyNo == r.replyNo) {
			writer = r.replyer; // 댓글 작성자
		}
	});
	
	if (user == writer) { // 로그인 한 유저의 글일 경우에만 수정 가능
		let output = `<div class="modifyReply">`;
		output += `<div class="mb-3 mt-3">`;
		output += `<label for="modifyReplyContent" class="form-label">수정할 댓글 내용 : </label>`;
		output += `<textarea id="modifyReplyContent" class="form-control" cols="100" rows="1">`;
		output += `</textarea>`;
		
		output += `<button type="button" class="btn btn-secondary saveReply modifyReply" onclick="modifyReply(\${replyNo});">수정 완료`;
		output += `</button></div></div>`;
		
		$(output).insertAfter($(`#reply_\${replyNo}`));
	} else {
		// 댓글 작성자가 아닌 경우
		alert("작성자 댓글이 아닙니다.");
	}
}

// ---------------------------------------------------------------------

// 수정 완료 버튼을 눌렀을 경우 수정 완료
function modifyReply(replyNo) {
	// replyContent -> 작성된 댓글 / modifyReplyContent -> 수정될 댓글
	let replyContent = $('#modifyReplyContent').val();
	
	let modifyReply = {
			"replyNo" : replyNo, // 댓글 번호
			"replyContent" : replyContent // 댓글 내용
	}

	// alert(replyNo);
	
	if (replyContent === '') {
		// 댓글을 입력안하고 댓글 작성 버튼을 눌렀을 경우
		alert('수정할 댓글을 입력해주세요!');
		$('#modifyReplyContent').focus(); // 포커스를 입력창에 넣어주기
		return;
	}
	
	$.ajax({
		url : "/reply/" + replyNo,
		type : "put", // 데이터가 수정되었으니 넣고
		contentType : "application/json", // 서버에 전송되는 데이터 타입
		dataType : "text", // 수신받을 데이터타입은 text형식
		data : JSON.stringify(modifyReply), // 데이터를 JSON 문자열로 변환하여 전송
		async : 'false', // 비동기 요청을 동기적으로 처리하도록 설정하는 옵션
		success : function(data) {
			console.log(data);
			
			if (data == 'success') {
				$('.modifyReply').remove();
				getAllReplies(); // 전체 댓글 가져오기
			}
		}
	});
}

//---------------------------------------------------------------------

// 댓글 삭제 시 로그인 유저만 가능하도록
function showRemoveReply(replyNo) {
	// alert(replyNo);
	let user = preAuth(); // 로그인 한 유저
	
	let writer = null;
	
	$.each(replies, function(i, r) {
		if (replyNo == r.replyNo) {
			
			writer = r.replyer; // 댓글 작성자
		}
	});
	
	if (user == writer) {
		if (window.confirm("댓글을 삭제하겠습니까?")) {
			$.ajax({
				url : "/reply/" + replyNo,
				type : "delete", // 데이터가 삭제니 delete
				data : JSON.stringify(modifyReply), // 데이터를 JSON 문자열로 변환하여 전송
				dataType : "text", // text 타입으로 수신받고
				async : 'false', // 비동기 요청을 동기적으로 처리하도록 설정하는 옵션
				success : function(data) {
					console.log(data);
					
					if (data == 'success') {
						getAllReplies(); // 전체 댓글 가져오기
					}
				}
			});
		}
	}
}

//---------------------------------------------------------------------

// 댓글을 작성하다가 취소 눌렀을 때
function cancelWriteReply() {
	alert("댓글 작성 안하시겠습니까?");
	
	$.ajax({
		url : '/reply/cancelReply',
		type : 'post',
		dataType : 'text', // 수신받을 데이터의 타입
		success : function(data) { // data(json)
			// 통신 성공하면 실행할 내용들....
			console.log(data);
			if (data == 'success') {
				location.href = '/lecture/viewBoard?lecNo=${lecBoard.lecNo}';
				getAllReplies(); // 전체 댓글 가져오기
			}
		}
	});
}

//---------------------------------------------------------------------

// 좋아요 버튼을 눌렀을 경우 insert
function likePost() {
    let lecNo = '${lecBoard.lecNo}'; // 게시글 번호
    let user = preAuth(); // 로그인 한 유저만 좋아요 가능하도록

    // 1) 게시글 번호, 좋아요 누를 유저를 likePost 객체에 담고
    let likePost = {
        "lecNo" : lecNo,
        "user" : user
    };

    $.ajax({
        url : '/lecture/like',
        type : 'post',
     	// 2) ajax를 이용해서 데이터(likePost)를 문자열로 변환하여 넘겨준다.
        data : JSON.stringify(likePost),
        headers : {
            "content-type" : "application/json"
        }, // 전송하는 데이터의 형식을 json으로 지정
        success : function(data) {
            console.log(data);
        },
        error : function(data) {
            console.log(data);
        }
    });
}


</script>
</head>

<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="../header.jsp"%>

	<main id="main">
		<!-- Basic Section - Lecture Page -->
		<section id="lecture" class="basic">
			<div class="container">

				<section class="notice">
					<div class="page-title">
						<div class="container">
							<h3>강의 추천 게시글 상세 페이지</h3>
						</div>
					</div>



					<!-- 로그인을 하지않아도 유저는 볼수 있지만 강의 링크는 로그인을 해야 볼수 있다. -->
					<!-- 별점은 이 글을 작성한 유저가 작성한 것이므로 다른 유저들이 누를 수 없다. -->
					<!-- 또한 좋아요 & 스크랩 & 신고 로그인 한 유저만 가능하다. -->
					<div class="lecBoard">
						<!-- <div class="mb-3 mt-3">
							<label for="lecNo" class="form-label">글 번호</label>
							<div class="content">${lecBoard.lecNo }</div>
						</div> -->

						<div class="mb-3 mt-3">
							<label for="lecWriter" class="form-label">작성자Id</label>
							<div class="content">${lecBoard.lecWriter }</div>
						</div>

						<div class="mb-3 mt-3">
							<label for="lecPostDate" class="form-label">작성일자</label>
							<div class="content">${lecBoard.lecPostDate }</div>
						</div>

						<div class="mb-3 mt-3">
							<label for="lecReadCount" class="form-label">조회수</label>
							<div class="content">${lecBoard.lecReadCount }</div>
						</div>

						<div class="mb-3 mt-3">
							<label for="scrap" class="form-label">스크랩</label>
							<div class="content">${lecBoard.scrap }</div>
						</div>

						<div class="mb-3 mt-3">
							<label for="lecTitle" class="form-label">제목</label>
							<div class="content">${lecBoard.lecTitle }</div>
						</div>

						<div class="mb-3 mt-3">
							<label for="lecLink" class="form-label">강의 북마크(링크)</label> <input
								type="text" class="form-control" id="title"
								value="${lecBoard.lecLink }" readonly="readonly" />
						</div>

						<div class="mb-3 mt-3">
							<label for="lecReview" class="form-label">후기</label>
							<div class="content">${lecBoard.lecReview }</div>
						</div>

						<div class="mb-3 mt-3">
							<label for="lecScore" class="form-label">별점</label>
							<div class="content">${lecBoard.lecScore }</div>
						</div>
					</div>


					<!-- 좋아요 - likeInfo / 신고 - reportInfo -->
					<div class="btn-group">
						<div class="btns">
							<!-- 하트 이미지 -->
							<img id="heartIcon" src="/resources/images/lecture/pinkHeart.png"
								alt="하트 이미지" style="width: 50px; height: 50px; cursor: pointer;"
								onclick="likePost()">
						</div>

						<!-- <div class="reportInfo"></div> -->
					</div>

					<!-- 글 수정 & 글 삭제 로그인 한 유저만 가능 -->
					<div class="btns">
						<a href="/lecture/modifyLectureBoard?lecNo=${lecBoard.lecNo}"
							class="btn">글수정</a> <a
							href="/lecture/removeLectureBoard?lecNo=${lecBoard.lecNo}"
							class="btn">글삭제</a>
					</div>


					<div class="btns">
						<a href="/lecture/listAll" class="btn">목록으로</a>
					</div>



					<!-- 댓글 작성 로그인 한 유저만 가능 -->
					<div class="writeReply">
						<div class="mb-3 mt-3">
							<label for="replyContent" class="form-label">댓글 내용 : </label>
							<textarea id="replyContent" class="form-control" cols="100"
								rows="1"></textarea>
							<button type="button" class="btn btn-secondary saveReply">댓글
								저장</button>
							<button type="button" class="btn btn-secondary"
								onclick="cancelWriteReply();">취소</button>
						</div>
					</div>

					<!-- 댓글 수정 로그인 한 유저만 가능 -->
					<div class="replies">
						<!-- 댓글 목록이 여기에 표시됩니다 -->
					</div>



				</section>



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
