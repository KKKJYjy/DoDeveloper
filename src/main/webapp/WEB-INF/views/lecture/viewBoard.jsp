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

/* 수정 & 삭제 버튼 */
.replyBtns {
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

/* 좋아요 버튼 */
.btn-group {
	position: relative;
	margin-left: auto;
}

/* 링크(북마크) 스타일 */
.bookmark-list {
	list-style: none; /* ul의 · 제거 */
	padding: 0; /* 앞에 공간 제거 */
}

.bookmark-list li {
	margin: 5px 0; /* 북마크 위아래 공백 추가 */
}

.bookmark-list a {
	text-decoration: none; /* 텍스트에 밑줄을 제거 */
	color: white; /* 링크 색상을 설정합니다. */
	border: 1px solid #666; /* 링크 테두리를 색과 함께 설정 */
	border-radius: 4px; /* 링크 모서리를 둥글게 */
	padding: 5px 10px; /* 북마크 안의 여백을 지정 */
}

.bookmark-list a:hover {
	background-color: #666; /* 배경색을 변경합니다. */
	color: black; /* 글자색을 변경합니다. */
}

/* 별점 */
.starRating .star {
	width: 25px;
	height: 25px;
	margin-right: 10px;
	display: inline-block;
	/* 별점 이미지의 경로 */
	background-image: url('/resources/images/lecture/star.png');
	background-size: 100%;
}

.starRating .star.on {
	/* 채워진 별점 이미지의 경로 */
	background-image: url('/resources/images/lecture/fullStar.png');
}

.basic img {
	position: relative; /* 별점과 목록 사이의 간격 */
	display: inline; /* 댓글 수정 & 댓글 삭제 위 간격 */
}

/* 좋아요 버튼 & 스크랩 버튼 양옆으로 */
.btn-group {
	display: flex;
	justify-content: space-between; /* 양 끝으로 정렬 */
	align-items: center; /* 수직 가운데 정렬 */
	width: 100%; /* 버튼 그룹의 너비를 100%로 설정 */
}

#fullScrapIcon {
	width: 50px;
	height: 50px;
}

.scrap {
	margin-left: 20px;
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
			} else {
				// 로그인 안한 유저일 경우 로그인 후 댓글 작성 가능
				alert('로그인 후 댓글 작성 가능합니다!');
			}
		}
	});
});

// ---------------------------------------------------------------------

// 댓글 작성 & 수정 전 로그인 인증
function preAuth() {
	let writer = '${sessionScope.loginMember.userId}';
	if (writer === '') { // 로그인 하지 않았다면 로그인 페이지로 이동
		location.href = '/member/login?redirectUrl=viewBoard&lecNo=${lecBoard.lecNo }';
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
	let writer = '${sessionScope.loginMember.userId}'; // 로그인 한 유저
	
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
		if (writer === reply.replyer) {
			output += `<div class='replyEditBtns'>`;
			output += `<img src='/resources/images/reply/replyedit.png' onclick='showModifyReply(\${reply.replyNo});' />`;
			output += `</div>`;
			output += `<div class='replyRemoveBtns'>`;
			output += `<img src='/resources/images/reply/replytrash.png' onclick='showRemoveReply(\${reply.replyNo});' />`;
			output += `</div>`;
		}
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

// 댓글 수정 전 댓글 작성자만 가능하도록
function showModifyReply(replyNo) {
	
	let writer = null; // 작성자를 null로 두고
	
	if (writer === reply.replyer) { // 로그인 한 유저의 글일 경우에만 수정 가능
		let output = `<div class="modifyReply">`;
		output += `<div class="mb-3 mt-3">`;
		output += `<label for="modifyReplyContent" class="form-label">수정할 댓글 내용 : </label>`;
		output += `<textarea id="modifyReplyContent" class="form-control" cols="100" rows="1">`;
		output += `</textarea>`;
		
		output += `<button type="button" class="btn btn-secondary saveReply modifyReply" onclick="modifyReply(\${replyNo});">수정 완료`;
		output += `</button></div></div>`;
		
		$(output).insertAfter($(`#reply_\${replyNo}`));
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

// 댓글 삭제 시 댓글 작성자만 가능하도록
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
//---------------------------------------------------------------------

// 좋아요 버튼에 대한 설정

// 좋아요 변수 초기값 설정
let liked = false;

// 좋아요 상태를 확인하기 위해 페이지 로드 시 호출
$(function() {
    getLikeStatus(); // 초기 좋아요 상태 확인
});

// 좋아요 상태를 확인하는 함수
function getLikeStatus() {
    let lecNo = '${lecBoard.lecNo}'; // 게시글 번호
    let user = '${sessionScope.loginMember.userId}'; // 로그인 한 유저의 정보
    let lecLikeTitle = '${lecBoard.lecTitle}'; // 게시글 제목

    $.ajax({
        url: '/lecture/likeStatus', // 좋아요 상태 확인 요청을 보낼 URL
        type: 'get', // HTTP GET 메서드 사용
        data: {
            lecNo: lecNo, // 게시글 번호 전송
            user: user, // 유저 정보 전송
            lecLikeTitle: lecLikeTitle // 게시글 제목 전송
        },
        success: function(data) {
            // 빈하트(좋아요 누르기 전)와 꽉찬하트(좋아요 누른 후)의 HTML 요소 가져오기
            let heartIcon = document.getElementById("heartIcon");
            let fullHeartIcon = document.getElementById("fullHeartIcon");

            // 받아온 데이터가 "success"인 경우 꽉찬하트 표시, 아니면 빈하트 표시
            if (data === "success") {
                heartIcon.style.display = "none"; // 빈하트 숨김
                fullHeartIcon.style.display = "inline"; // 꽉찬하트 표시
                liked = true; // 좋아요를 누른 상태로 설정
            } else {
                heartIcon.style.display = "inline"; // 빈하트 표시
                fullHeartIcon.style.display = "none"; // 꽉찬하트 숨김
                liked = false; // 좋아요를 누르지 않은 상태로 설정
            }
        },
        error: function() {
            console.log('좋아요 상태를 불러오는데 실패했습니다.');
        }
    });   
}

// 하트 아이콘 클릭 시 호출되는 함수(clickHeart)
function clickHeart() {
    let lecNo = '${lecBoard.lecNo}'; // 게시글 번호
    let user = preAuth(); // 로그인 한 유저의 정보
    let lecLikeTitle = '${lecBoard.lecTitle}'; // 게시글 제목
    
    // 로그인 후 좋아요 클릭 가능하도록
    if (user === '') {
    	alert('로그인 후 좋아요가 가능합니다!');
    }

    // 좋아요 또는 좋아요 취소 요청을 보낼 URL 설정 - 삼항 연산자 사용
    let url = liked ? '/lecture/unLike' : '/lecture/like';

    // 좋아요 또는 좋아요 취소 데이터 객체 설정
    let likePost = {
        "lecNo": lecNo,
        "user": user,
        "lecLikeTitle": lecLikeTitle
    };

    console.log(likePost);
    
    // AJAX 요청
    $.ajax({
        url: url, // 요청 보낼 URL
        type: 'post', // HTTP POST 메서드 사용
        data: JSON.stringify(likePost), // 좋아요 데이터를 JSON 문자열로 변환하여 전송
        contentType: 'application/json', // 전송하는 데이터의 형식을 JSON으로 설정
        success: function(data) {
            console.log('success:', data);

            // 빈하트(좋아요 누르기 전)와 꽉찬하트(좋아요 누른 후)의 HTML 요소 가져오기
            let heartIcon = document.getElementById("heartIcon");
            let fullHeartIcon = document.getElementById("fullHeartIcon");

            if (!liked) {
                // 좋아요를 누른 경우
                heartIcon.style.display = "none"; // 빈하트 숨김
                fullHeartIcon.style.display = "inline"; // 꽉찬하트 표시
                console.log("좋아요 성공");
            } else {
                // 좋아요를 취소한 경우
                heartIcon.style.display = "inline"; // 빈하트 표시
                fullHeartIcon.style.display = "none"; // 꽉찬하트 숨김
                console.log("좋아요 취소 성공");
            }
            liked = !liked; // 좋아요 상태(좋아요 <=> 좋아요 취소) 변경
        },
        error: function(data) {
            console.log('error:', data);
        }
    });
}
//---------------------------------------------------------------------

// 링크에 대한 함수
function linkBookmarks() {
    // 1) 강의 링크를 가져와서 linkText 변수로 만들고
    let linkText = '${lecBoard.lecLink}';
    
    // 2) 링크 목록을 표시할 ul id 가져와서 bookmarkList 변수로 만들고
    let bookmarkList = document.getElementById('bookmarkList');

 	// 3) 주어진 링크에 대한 줄바꿈을 기준으로 분리(여러개의 링크가 있을 경우를 대비)
    linkText.split('\n').forEach(link => {
        // 4) ul태그 안에 li를 만든다.
        let listItem = document.createElement('li');
        
     	// 4-1) 위 li태그를 2번에서 만든 변수 bookmarkList 안에 appendChild를 사용하여 위 listItem변수에서 만든 것을 넣어준다.
        bookmarkList.appendChild(listItem);

     	// 5) 4번에서 만든 listItem변수에 appendChild를 사용해서 a태그를 만들어준다.(하이퍼링크를 설정하기 위해서)
        listItem.appendChild(document.createElement('a'));
                
        // 6) 5번에서 만든 a태그에 하이퍼링크 설정을 해줌으로 url을 불러온다.
        // lastChild : 마지막 자식 요소 / href : 하이퍼링크의 주소(url)을 의미
        listItem.lastChild.href = link.trim(); // 즉, trim()함수를 사용해서 마지막 자식요소인 하이퍼링크 url을 변경(앞뒤공백제거)
        // target : 하이퍼링크가 열릴 경로를 지정 / '_blank' : 새 창(새 탭)에서 열리도록 설정 / '_self' : 현재 창에서 열리도록 설정
        listItem.lastChild.target = '_blank'; // 링크를 새 탭에서 열도록 설정합니다.
        // textContent : 모든 자식을 주어진 문자열로 이루어진 하나의 텍스트 노드로 대치(즉, 나누어진 a태그들을 하나로)
        listItem.lastChild.textContent = link.trim(); // 마지막 자식 요소인 하이퍼링크 텍스트를 하나의 텍스트로 변경, 앞뒤 공백을 제거
    });
}

// 링크 함수 호출
$(function() {
	linkBookmarks();
});

//---------------------------------------------------------------------

// 유저가 글쓰기할 때 누른 별점 불러오기
document.addEventListener('DOMContentLoaded', function() {
	// lecScore 를 가져와서 starScore 변수에 넣기
	let starScore = ${lecBoard.lecScore};
	
	// alert(starScore);
	
	// 글쓴 유저가 누른 별점 보여주는 함수 showStarRating
	function showStarRating(starScore) {
		// 별점 요소들을 선택해서 stars 변수에 넣어준다.
		let stars = document.querySelectorAll('.starRating .star');
		stars.forEach(function (star ,i) {
			// index(i)가 star보다 작으면 'on' 클래스를 추가
			// 그렇지 않으면 삭제
			if (i < starScore) {
				star.classList.add('on');
			} else {
				star.classList.remove('on');
			}
		});
	}
	// 별점 표시를 하기위해서 위 함수 호출
    showStarRating(starScore);
});

//---------------------------------------------------------------------

// 스크랩
$(document).ready(function() {
    getScrapStatus(); // 스크랩 상태를 확인하는 함수 호출
});

// 스크랩 상태를 나타내는 변수 초기값 설정
let scraped = false;

function getScrapStatus() {
    let lecNo = '${lecBoard.lecNo}';
    let user = '${sessionScope.loginMember.userId}';
    let scrapLecTitle = '${lecBoard.lecTitle}';

    $.ajax({
        url: '/lecture/scrapStatus', // 요청을 보낼 URL
        type: 'get', // HTTP 요청 메서드 (GET)
        data: {
            lecNo: lecNo,
            user: user,
            scrapLecTitle: scrapLecTitle
        },
        success: function(data) {
            // 스크랩 누르기 전과 스크랩 누른 후의 HTML 요소를 가져옴
            let scrapIcon = document.getElementById("scrapIcon");
            let fullScrapIcon = document.getElementById("fullScrapIcon");

            // 받아온 데이터가 "success"이면 꽉찬하트를 표시, 아니면 빈하트를 표시
            if (data === "success") {
                scrapIcon.style.display = "none"; // 빈 스크랩 아이콘 숨김
                fullScrapIcon.style.display = "inline"; // 꽉찬 스크랩 아이콘 표시
                scraped = true; // 스크랩을 누른 상태
            } else {
                scrapIcon.style.display = "inline"; // 빈 스크랩 아이콘 표시
                fullScrapIcon.style.display = "none"; // 꽉찬 스크랩 아이콘 숨김
                scraped = false; // 스크랩을 누르지 않은 상태
            }
        },
        error: function() {
            console.log('스크랩 상태를 불러오는데 실패했습니다.');
        }
    });
}

function clickScrap() {
    let lecNo = '${lecBoard.lecNo}'; // 게시글 번호
    let user = preAuth(); // 로그인 한 유저만 스크랩 / 스크랩 취소 가능하도록
    let scrapLecTitle = '${lecBoard.lecTitle}';
    
    // 로그인 후 스크랩 클릭 가능하도록
    if (user === '') {
    	alert('로그인 후 스크랩이 가능합니다!');
    }

    // 스크랩 또는 스크랩 취소 요청을 보낼 URL 설정 - 삼항 연산자 사용
    let url = scraped ? '/lecture/unScrap' : '/lecture/scrap';
    
 	// 1) 게시글 번호와 스크랩 누를 유저를 scrapPost 객체에 담고
    let scrapPost = {
        "lecNo": lecNo,
        "user": user,
        "scrapLecTitle": scrapLecTitle
    };

    console.log(scrapPost);

    $.ajax({
        url: url,
        type: 'post',
        // 2) ajax를 이용해서 데이터(scrapPost)를 문자열로 변환하여 넘겨준다.
        data: JSON.stringify(scrapPost),
        contentType: 'application/json', // 전송하는 데이터의 형식을 JSON으로 지정
        success: function(data) {
            console.log('success:', data);

            // 스크랩 누르기 전과 스크랩을 누른 후의 id 값을 가져와서 변수로 지정
            let scrapIcon = document.getElementById("scrapIcon");
            let fullScrapIcon = document.getElementById("fullScrapIcon");

            if (!scraped) {
                // 스크랩을 누르는 경우
                scrapIcon.style.display = "none"; // 빈 스크랩 아이콘 숨김
                fullScrapIcon.style.display = "inline"; // 꽉찬 스크랩 아이콘 표시
                console.log("스크랩 성공");
            } else {
                // 스크랩을 눌렀던 경우 -> 스크랩 취소
                scrapIcon.style.display = "inline"; // 빈 스크랩 아이콘 표시
                fullScrapIcon.style.display = "none"; // 꽉찬 스크랩 아이콘 숨김
                console.log("스크랩 취소 성공");
            }
            scraped = !scraped; // 스크랩 상태(스크랩 <=> 스크랩 취소) 변경
        },
        error: function(data) {
            console.log('error:', data);
        }
    });
}

//---------------------------------------------------------------------

// 신고 버튼 (등록)
function clickReport() {

    let list = `${lecBoard}`;
    let user = preAuth(); // 로그인을 한 유저만 신고 가능
    console.log(list);
    
    // 위 lecBoard에서 문자열을 가져와 필요한 정보를 list에 담은 것으로 게시글 작성자와 게시글 번호를 추출
    let writer = list.split('lecWriter=')[1].split(', lecPostDate=')[0]; // 게시글 작성자 추출
    let boardNo = list.split('lecNo=')[1].split(', bType=')[0]; // 게시글 번호 추출

    console.log("게시글 작성자 : " + writer + ", 신고자 : " + user + ", 게시글 번호 : " + boardNo);

    // 게시글 작성자명과 신고자명 게시글 번호를 모달의 입력창에 값을 불러와서 설정
    $('#writer').val(writer);
    $('#reporter').val(user);
    $('#boardNo').val(boardNo);
    
    // 게시글 신고되기 전 - 하얀 신고 아이콘을 표시 빨간 신고 아이콘 숨기기
    let reportIcon = document.getElementById("reportIcon");
    let fullreportIcon = document.getElementById("fullreportIcon");

    reportIcon.style.display = "inline"; // 하얀 신고 아이콘 표시
    fullreportIcon.style.display = "none"; // 빨간 신고 아이콘 숨기기

}

//신고 등록 함수
function insertReport() {
    // 1) 모달 내의 입력 필드에서 값을 가져와서
    let reporter = $('#reporter').val();
    let btypeNo = $('#btypeNo').val();
    let boardNo = $('#boardNo').val();
    let writer = $('#writer').val();
    let reportReason = $('#reportReason').val();

    // 입력창이 비어있을 경우 신고접수 X - 유효성 검사
    if (reporter != '' && writer != '' && reportReason != '') {
        $.ajax({
            url: "/report/insertReport",
            data: {
                "reporter": reporter, // 신고자
                "writer": writer, // 게시글 작성자
                "boardNo": boardNo, // 게시글 번호
                "btypeNo": btypeNo, // 게시판 타입 번호
                "reportReason": reportReason, // 신고 사유
                "category": "강의 추천" // 게시판 구분 이름
            },
            type: "get",
            contentType: "application/json",
            dataType: "text",
            success: function(data) {
                console.log('응답 데이터:', data);
            },
        });
        
        $('#myModal').modal('hide'); // 신고 완료시 모달 숨기기
        reportIcon.style.display = "none"; // 하얀 신고 아이콘 숨기기
        fullreportIcon.style.display = "inline"; // 빨간 신고 아이콘 표시
        
        alert("등록되었습니다.");
        
    } else {
        alert("신고 접수가 안되었습니다. 다시 신고 부탁드립니다."); // 입력값이 부적절한 경우 메시지 표시
    }
}

//---------------------------------------------------------------------

// 신고 취소 (모달 닫기)
function closeReport() {
    // 이미지 표시 변경 (신고 완료 아이콘을 신고 아이콘으로 전환)
    let reportIcon = document.getElementById("reportIcon");
    let fullreportIcon = document.getElementById("fullreportIcon");
    reportIcon.style.display = "inline"; // 기존 신고 아이콘을 표시
    fullreportIcon.style.display = "none"; // 신고 완료 아이콘을 숨김

    // 모달 닫기
    $('#myModal').modal('hide');
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
							<!-- <h3>강의 추천 게시글 상세 페이지</h3> -->
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
							<label for="lecWriter" class="form-label">작성자
								${lecBoard.lecWriter }</label>
						</div>

						<div class="mb-3 mt-3">
							<label for="lecPostDate" class="form-label">작성일자
								${lecBoard.lecPostDate }</label>
						</div>

						<div class="mb-3 mt-3">
							<label for="lecReadCount" class="form-label">조회수
								${lecBoard.lecReadCount }</label>
						</div>

						<div class="mb-3 mt-3">
							<label for="scrap" class="form-label">스크랩수
								${lecBoard.scrap }</label>
						</div>

						<div class="mb-3 mt-3">
							<label for="lecTitle" class="form-label">제목 :
								${lecBoard.lecTitle }</label>
						</div>

						<div class="mb-3 mt-3">
							<label for="lecLink" class="form-label">강의 링크</label>
							<ul id="bookmarkList" class="bookmark-list"></ul>
						</div>

						<div class="mb-3 mt-3">
							<label for="lecReview" class="form-label">후기</label>
							<div class="content">${lecBoard.lecReview }</div>
						</div>

						<!-- 강의 후기 별점 -->
						<!-- input type="hidden"을 사용해서 유저가 안보이도록 정보를 보낸다. -->
						<div class="starRating">
							<label for="lecScore" class="form-label">별점</label>
							<label class="star"><input type="hidden" value="1"></label>
							<label class="star"><input type="hidden" value="2"></label>
							<label class="star"><input type="hidden" value="3"></label>
							<label class="star"><input type="hidden" value="4"></label>
							<label class="star"><input type="hidden" value="5"></label>
						</div>

						<!-- 별점 값을 숨기는 input type -->
						<input type="hidden" id="lecScore" name="lecScore"
							value="${lecBoard.lecScore}">
					</div>

					<!-- 좋아요 - likeInfo / 신고 - reportInfo -->
					<div class="btn-group">
						<div class="btns">
							<div class="btns heart">
								<!-- 하트 이미지 -->
								<img id="heartIcon" src="/resources/images/lecture/redHeart.png"
									alt="하트 이미지" onclick="clickHeart();">
								<img id="fullHeartIcon" src="/resources/images/lecture/redFullHeart.png"
									alt="하트 이미지" style="display: none;" onclick="clickHeart();">
							</div>

							<div class="btns scrap">
								<!-- 스크랩 -->
								<img id="scrapIcon" src="/resources/images/lecture/scrap.png"
									alt="스크랩" onclick="clickScrap();">
								<img id="fullScrapIcon" src="/resources/images/lecture/fullScrap.png"
									alt="스크랩" style="display: none;" onclick="clickScrap();">
							</div>
						</div>
						
						<div class="btns report">
							<!-- 신고 -->
							<img id="reportIcon" src="/resources/images/lecture/siren.png"
								alt="신고" onclick="clickReport();"
								data-bs-toggle="modal" data-bs-target="#myModal">
							<img id="fullreportIcon" src="/resources/images/lecture/fullSiren.png"
								alt="신고" style="display: none;" onclick="clickReport();">
						</div>
					</div>

					<!-- 글 수정 & 글 삭제 로그인 한 유저만 가능 -->
					<div class="btns">
						<c:if
							test="${sessionScope.loginMember.userId == lecBoard.lecWriter}">
							<a href="/lecture/modifyLectureBoard?lecNo=${lecBoard.lecNo}"
								class="modifyBtn btn">글수정</a>
							<a href="/lecture/removeLectureBoard?lecNo=${lecBoard.lecNo}"
								class="removeBtn btn">글삭제</a>
						</c:if>
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



		<!--  -----------------------------------------------report board modal------------------------------------------------------------------ -->
		<!-- The Modal   -->
		<div class="modal" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">신고 사유를 작성해주세요.</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>

					<!-- Modal body -->
					<label for="title" class="form-label">신고할 게시판 제목 : </label>
					<input type="text" class="form-control" id="reportTitle" value="${lecBoard.lecTitle}"
						name="reportTitle" readonly="readonly" />
					<label for="title" class="form-label">게시글 작성자 : </label>
					<input type="text" class="form-control" id="writer" name="writer"
						readonly="readonly" />
					<label for="title" class="form-label">신고글 작성자 : </label>
					<input type="text" class="form-control" id="reporter" name="reporter"
						readonly="readonly" />


					<div class="mb-3 mt-3">
						<label for="title" class="form-label">신고사유 : </label>
						<input type="text" class="form-control" id="reportReason"
							name="reportReason" placeholder="신고 사유를 입력하세요..." >
					</div>


					<div>
						<input type="hidden" id="btypeNo" name="btypeNo" value="1">
						<input type="hidden" id="boardNo" name="boardNo">
					</div>


					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							onclick="insertReport();">등록</button>
						<button type="button" class="btn btn-secondary"
							onclick="closeReport();"  data-bs-dismiss="modal">Close</button>
					</div>

				</div>
			</div>
		</div>
		<!--  --------------------------------------------------report modal End-----------------------------------------------
		
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
