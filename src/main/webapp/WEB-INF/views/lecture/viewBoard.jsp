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
.content {
	padding: 15px;
	border: 1px solid #595959;
}

.replyer {
	margin-right: 10px;
}

.replyBtns img {
	width: 25px;
	margin-right: 5px;
}
</style>
<script>
	//---------------------------------------------------------------------

	//댓글 조회에 대한 js
	$(document).ready(function() {
		// 댓글 목록 가져오기
		var bNo = `${lecBoard.lecNo }`;
		refreshReplyList(bNo);
	});

	//---------------------------------------------------------------------

	// 댓글 전체 조회
	function getAllReplies() {
		let lecNo = ${lecBoard.lecNo};

		$.ajax({
			url : "/reply/list/" + bNo,
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

	//---------------------------------------------------------------------

	//댓글 목록을 가져와서 화면에 표시하는 함수 -> refreshReplyList
	function refreshReplyList(bNo) {
		$.ajax({
			type : "GET",
			url : "/reply/list/" + bNo,
			success : function(replies) {
				var html = "";
				for (var i = 0; i < replies.length; i++) {
					html += "<div class='reply'>" + replies[i].replyNo + " | "
							+ replies[i].replyContent + " | "
							+ replies[i].replyer + "</div>";
				}
				$(".replies").html(html); // 댓글 목록을 출력할 요소에 HTML 추가
			},
			// 댓글 목록을 불러오다가 오류가 났을 경우 alert창을 띄운다.
			// xhr - XMLHttpRequest 객체
			// status - 오류 상태
			// error - 오류에 대한 정보
			error : function(xhr, status, error) {
				alert("댓글 목록을 불러오는 중 오류가 발생했습니다.");
				console.error(error);
			}
		});
	}

	// ---------------------------------------------------------------------

	// 댓글 처리 전 로그인 인증
	function preAuth() {
		let writer = '${sessionScope.loginMember.userId}';
		if (writer === '') { // 로그인 하지 않았다면 로그인 페이지로 이동
			location.href = '/member/login?redirectUrl=view&lecNo=${lecBoard.lecNo}';
		}
		return writer;
	}

	//---------------------------------------------------------------------

	// 댓글 출력 방식
	function outputReplies(data) {
		let output = `<div class="list-group replies">`;
		$.each(data, function(i, reply) {
			output += `<ul class="list-group-item">`;
			output += `<div class='reply' id='reply_${reply.replyNo}'>`;
			output += `<div class='replyNo'>${reply.replyNo}</div>`;
			output += `<div style='flex:1'>${reply.replyContent}</div>`;
			output += `<div class='replyer'>${reply.replyer}</div>`;
			let diff = processPostDate(reply.writtenDate);
			output += `<div>${diff}</div>`;
			output += `<div class='replyBtns'><img src='/resources/images/reply/replyedit.png' onclick='showModifyReply(${reply.replyNo});'/>`;
			output += `<img src='/resources/images/reply/replytrash.png' onclick='' /></div>`;
			output += `</div>`;
			output += `</ul>`;
		});
		output += `</div>`;
		$('.replies').html(output);
	}

	//---------------------------------------------------------------------

	// 댓글 작성시 저장하는 js
	$(function() {

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
					let bNo = $
					{
						lecBoard.lecNo
					}
					; // 게시글 번호 가져오기
					let newReply = {
						"bNo" : bNo + "", // 문자열로 변환
						"replyContent" : replyContent,
						"replyer" : replyer
					}; // 댓글 객체 생성

					console.log('게시글 번호 가져오기');

					// AJAX를 이용하여 댓글 추가 요청 보내기
					$.ajax({
						url : '/reply/' + bNo,
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
	
	
// 댓글 수정 버튼에 대한 js
function showModifyReply(replyNo) {
    let user = preAuth(); // 로그인 한 유저를 user 변수로
    let writer = null;

    $.each(replies, function(i, r) {
        if (replyNo == r.replyNo) {
            writer = r.replyer; // 댓글 작성자
        }
    });

    // 로그인 한 유저는 본인 댓글 일 때만 수정이 가능하도록
    if (user == writer) {
        // 숨겨진 수정창을 보여줌
        $('#modifyReplyContainer').show();

        // 기존의 수정창을 비움
        $('#modifyReplyContent').val('');

        // 수정 버튼에 replyNo 정보 추가
        $('#modifyReplyContent').data('replyNo', replyNo);
    } else {
        alert('작성자 본인만 수정이 가능합니다.');
    }
}

// 댓글 수정 요청
function modifyReply() {
    let replyNo = $('#modifyReplyContent').data('replyNo');
    let replyContent = $('#modifyReplyContent').val();
    let modifyReply = {
        "replyNo": replyNo,
        "replyContent": replyContent
    };

    // 댓글 내용이 비어있는지 확인
    if (replyContent.trim() === '') {
        alert('댓글 내용을 입력하세요.');
        return;
    }

    // 서버로 수정된 내용 전송
    $.ajax({
        url: `/modify/${replyNo}`,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(modifyReply),
        success: function(response) {
            if (response === '댓글 수정 완료') {
                alert('댓글이 수정되었습니다.');
                // 수정된 댓글 새로고침
                refreshReplyList();
                // 수정창 숨김
                $('#modifyReplyContainer').hide();
            } else {
                alert('댓글 수정에 실패했습니다.');
            }
        },
        error: function(xhr, status, error) {
            console.error(xhr.responseText);
            alert('댓글 수정 중 오류가 발생했습니다.');
        }
    });
}

// 취소 버튼 클릭 시 수정창 숨김
function cancelModify() {
    $('#modifyReplyContainer').hide();
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
						<div class="mb-3 mt-3">
							<label for="lecNo" class="form-label">글 번호</label>
							<div class="content">${lecBoard.lecNo }</div>
						</div>

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

						<div class="btns">
							<button type="button" class="btn btn-dark"
								onclick="location.href='/board/listAll';">좋아요</button>
							<button type="button" class="btn btn-dark"
								onclick="location.href='/board/listAll';">신고</button>
						</div>
					</div>

					<!-- 글 수정 & 글 삭제 로그인 한 유저만 가능 -->
					<div class="btns">
						<button type="button" class="btn"
							onclick="location.href='/lecture/modifyLectureBoard?lecNo=${lecBoard.lecNo}';">글수정</button>
						<button type="button" class="btn"
							onclick="location.href='/lecture/removeLectureBoard?lecNo=${lecBoard.lecNo}';">글삭제</button>
						<div class="btn-group">
							<button type="button" class="btn"
								onclick="location.href='/lecture/listAll';">목록으로</button>
						</div>
					</div>

					<!-- 댓글 작성 로그인 한 유저만 가능 -->
					<div class="writeReply">
						<div class="mb-3 mt-3">
							<label for="replyContent" class="form-label">댓글 내용 : </label>
							<textarea id="replyContent" class="form-control" rows="1"></textarea>
							<button type="button" class="btn btn-info saveReply">댓글
								저장</button>
						</div>
					</div>

					<div class="replies">
						<!-- 댓글 목록이 여기에 표시됩니다 -->
						<label for="modifyReplyContent" class="form-label">수정할 댓글
							내용 : </label>
						<textarea id="modifyReplyContent" class="form-control" rows="1"></textarea>
						<button type="button" class="btn btn-info" onclick="modifyReply()">수정
							완료</button>
						<button type="button" class="btn btn-secondary"
							onclick="cancelModify()">취소</button>
					</div>

					<!-- 댓글 수정 입력창 -->
					<div id="modifyReplyContainer" style="display: none;"></div>

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
