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
<script>
// 강의 후기 "직접 작성" 눌렀을 경우
function showInput() {
	var selectBox = document.getElementById("lecReviewSelect");
	var inputField = document.getElementById("lecReviewInput");

	if (selectBox.value === "") {
		inputField.style.display = "block";
	} else {
		inputField.style.display = "none";
	}
}

// 유저가 게시글 수정하려다가 취소버튼 눌렀을 때
function cancelModifyBoard() {
	let lecNo = '${param.lecNo}'; // 쿼리스트링에서 lecNo를 가져와라
	alert("게시글을 수정 안하시겠습니까?");

	$.ajax({
		url : '/lecture/cancelModify', // 수정된 부분
		type : 'post',
		data : {
			lecNo : lecNo
		}, // lecNo를 데이터로 전송
		dataType : 'text', // 수신받을 데이터의 타입
		success : function(data) { // data(json)
			// 통신 성공하면 실행할 내용들....
			console.log(data);
			if (data == 'success') {
				location.href = '/lecture/viewBoard?lecNo=' + lecNo;
				// location.href = '/lecture/viewBoard?lecNo=${lecBoard.lecNo}'; -> 지역변수를 사용안할 경우 이 코드 사용
			}
		}
	});
}

//---------------------------------------------------------------------

// 별점 설정하는 함수
function setStarRating() {
	$(document).ready(function() {
		// 별점을 클릭할 때 실행되는 함수
		$('.starRating .star').click(function() {
			// 모든 별에서 'on' 클래스를 삭제(클릭된 별 제외)
			// siblings() 함수를 사용함으로 인해서 star에서 on이 추가 된 별은 삭제한다.
			$(this).siblings().removeClass('on');
			// 선택이 안되어있던 별에 유저가 별을 누를 경우 별에 'on' 클래스를 추가
			// 유저가 star를 선택시 클래스에 on을 넣어주고 prevAll 함수를 사용하여 여러개를 선택할 수 있도록 활성화시켜준다.
			$(this).addClass('on').prevAll('.star').addClass('on');

			// 유저가 클릭한 별의 갯수(평점)를 얻어온다.
			let star = $(this).find('input').val();
			// input type="hidden"에다가 value에 평점을 넣어준다.
			$('#lecScore').val(star);
		});
	});
}

// 위 별점 설정한 함수 호출
setStarRating();

//유저가 누른 별점을 불러오는 함수
(function() {
	// 별점을 표시하는 함수 starSc
	let starSc = document.querySelectorAll('.starRating .star');
	// 유저가 누른 별점을 저장하는 함수score
	let score = 0;

	starSc.forEach(function(e, i) {
		e.addEventListener('click', function() { // 유저가 선택한 별점을 처리
			rating(i + 1); // 유저가 클릭한 별의 i에 1을 더해서 rating 함수에 전달
		});
	});

	// 유저가 클릭한 별접 DB에 업데이트
	function rating(score) {
		// 유저가 클릭한 별점(score)이 낮은 index(i)는 on클래스 추가해서 활성화
		// 유저가 클릭한 별점(score)이 낮은 index(i)는 on클래스 제거해서 비활성화
		starSc.forEach(function(e, i) {
			if (i < score) {
				e.classList.add('on');
			} else {
				e.classList.remove('on');
			}
		});
		score = score; // 유저가 선택한 score를 score에 저장
		document.getElementById('lecScore').value = score; // 선택한 별점을 hidden input에 저장
	}
})();

//---------------------------------------------------------------------

//유저가 글 작성시 작성 안한 곳이 없도록 - 유효성 검사
//강의 후기 같은 경우는 선택하거나 작성 두 경우가 있어서 else if 두 경우로 나눔
function modifyIsValid() {
    let result = false;
    // 강의 링크를 올릴때 링크가 아니면 올리지 못하도록 URL 검사
    const urlPattern = new RegExp('https?://[^\s/$.?#].[^\s]*', 'i');

    if ($("#lecTitle").val() == '' || $("#lecTitle").val() == null) { // 제목
        $("#lecTitle").focus();
        alert("제목을 입력해주세요.");
    } else if ($("#lecLink").val() == '' || $("#lecLink").val() == null) { // 링크
        $("#lecLink").focus();
        alert($("#lecWriter").val() + "님께서 들었던 강의 중 좋았던 강의 링크를 공유해주세요.");
    } else if (!urlPattern.test($("#lecLink").val())) { // URL을 맞도록 작성했는지 검사
        $("#lecLink").focus();
        alert("유효한 강의 링크를 입력해주세요.");
    } else if ($("#lecReviewSelect").val() == '-1') { // 후기 선택
        $("#lecReviewSelect").focus();
        alert("강의 후기를 선택하거나 작성해주세요.");
    } else if ($("#lecReviewSelect").val() == '' && ($("#lecReviewInput").val() == '' || $("#lecReviewInput").val() == null)) { // 후기 작성
        $("#lecReviewInput").focus();
        alert("강의 후기를 작성해주세요.");
    } else if ($("#lecScore").val() == '0') { // 별점
        alert("들으셨던 강의가 얼마나 좋으셨는지 별점을 남겨주세요.");
    } else {
        result = true;
    }
    
    return result;
}

// 폼 제출 이벤트에 유효성 검사 함수 연결
document.getElementById('yourFormId').addEventListener('submit', function(event) {
    if (!isValid()) {
        event.preventDefault(); // 폼 제출 방지
    }
});
</script>
<style>
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
	width: 25px;
	height: 25px;
	margin-right: 10px;
	display: inline-block;
	/* 채워진 별점 이미지의 경로 */
	background-image: url('/resources/images/lecture/fullStar.png');
	background-size: 100%;
}
</style>
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
							<!-- <h3>강의 추천 게시글 작성 페이지</h3> -->
						</div>
					</div>

					<!-- 글 작성은 로그인 한 유저만 가능하니 유저 id가 바로 뜰수 있도록 -->
					<!-- 별점은 ☆로 5개로 하되 반개는 안되고 한개씩만 가능 -->
					<!-- 후기는 select박스로 결정하는 것도 있고 "내가 작성"을 누를 경우 input박스가 생기도록 -->
					<!-- 단, 유저가 select박스 중 다른 것을 눌렀을 경우 input박스는 사라져야 한다. -->
					<!-- 링크를 올렸을 때 바로 북마크가 생기도록 -->
					<div class="lecBoard">
						<form action="/lecture/modifyPost" method="post">
							<input type="hidden" name="lecNo"
								value="${result.lecBoard.lecNo }">
							<div class="mb-3 mt-3">
								<label for="lecWriter" class="form-label"></label> <input
									type="text" class="form-control" id="lecWriter"
									name="lecWriter" value="${sessionScope.loginMember.userId}" />
								님께서 시청하신 강의 중 좋았던 강의 링크를 공유해주시고 후기를 남겨주세요.
							</div>

							<div class="mb-3 mt-3">
								<label for="lecTitle" class="form-label">제목</label> <input
									type="text" class="form-control" id="lecTitle" name="lecTitle"
									value="${result.lecBoard.lecTitle}" />
							</div>

							<div class="mb-3 mt-3">
								<label for="lecLink" class="form-label">강의 링크</label>
								<textarea id="lecLink" name="lecLink" rows="5" cols="500"
									class="form-control">${result.lecBoard.lecLink}</textarea>
							</div>

							<div class="mb-3 mt-3" class="form-label">
								<label for="lecReview" class="lecReview">강의 후기</label> <select
									id="lecReviewSelect" name="lecReview" onchange="showInput()">
									<option value="">-- 강의 후기 선택 --</option>
									<option>이번 강의는 정말 유익했습니다. 교수님께서 어려운 개념을 쉽게 설명해 주셔서 이해가
										잘 되었습니다. 실습 시간도 충분해서 배운 내용을 바로 적용할 수 있었습니다.</option>
									<option>강사님께서 주제에 대해 깊은 이해를 가지고 계셨고, 실제 사례를 들어 설명해 주셔서
										실무에 어떻게 적용되는지 잘 알 수 있었습니다. 덕분에 이론과 실무를 모두 배울 수 있어 좋았습니다.</option>
									<option>수업 자료가 체계적으로 준비되어 있었고, 강의 슬라이드와 참고 자료가 매우 도움이
										되었습니다. 특히, 수업 후 제공된 추가 자료들이 학습에 큰 도움이 되었습니다.</option>
									<option>강의 내용이 매우 유익했지만, 다소 빠르게 진행된 느낌이 있었습니다. 중요한
										부분에서는 조금 더 시간을 할애해 주시면 좋을 것 같습니다.</option>
									<option>강의 시간이 너무 길어서 집중력이 떨어질 때가 있었습니다. 중간에 짧은 휴식 시간을
										주시면 좋겠습니다.</option>
									<option>수업 자료가 조금 더 일찍 제공되면 예습하는 데 도움이 될 것 같습니다. 또한,
										수업 중 다루는 예제 코드도 함께 배포해 주시면 좋겠습니다.</option>
									<option value="">강의 후기 직접 입력</option>
								</select>
								<textarea class="form-control" id="lecReviewInput" name="lecReview" placeholder="강의 후기 작성해주세요."
									style="display: none;"></textarea>
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
							<input type="hidden" id="lecScore" name="lecScore" value="0">

							<!-- 글 수정 & 글 삭제 로그인 한 유저만 가능 -->
							<div class="btns">
								<input type="submit" class="btn btn-success" value="글 저장" onclick="return modifyIsValid();"/> <input
									type="button" class="btn btn-danger" value="취소"
									onclick="cancelModifyBoard();" />
								<div class="btn-group">
									<button type="button" class="btn"
										onclick="location.href='/lecture/listAll';">목록으로</button>
								</div>
							</div>
						</form>
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
