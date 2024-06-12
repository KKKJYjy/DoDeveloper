<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
Date l = new Date(System.currentTimeMillis());
SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<title>CompanyInfo List - DoDeveloper</title>
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

<!-- =======================================================
  * Template Name: Append
  * Template URL: https://bootstrapmade.com/append-bootstrap-website-template/
  * Updated: Mar 17 2024 with Bootstrap v5.3.3
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script
	src="/resources/assets/js/company/common.js?v=<%=sdf.format(l)%>"></script>
	
<!--  리스트 길이 구하는 함수 포함 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>	

<script>
//	if (getParameter('status').indexOf('Fail') != -1) {
//		alert('작업에 실패하셨습니다!');
//		}




function selectNo(no) {
	// 셀렉트 태그로 게시판을 선택하면 1.신고자의 아이디를 SessionScope에서 받아와 #reoprter태그에 삽입
	// 2.셀렉트태그로 받아온 번호를 이용해 해당하는 번호를 작성한 작성자를 #writer태그에 삽입
	console.log(no);
	let user = '${sessionScope.loginMember.userId}';
	let boardNumber = no.split('&')[0]
	let indexNo = parseInt(no.split('&')[1]);
	console.log(boardNumber);
	console.log(indexNo);
	let list = `${revList}`;
	
	console.log(list);
	
	let writer = list.split('revWriter=')[1+indexNo].split(', revTitle')[0];
	console.log(writer);
	$('#reporter').val(user);
	$('#writer').val(writer);
	$('#boardNo').val(boardNumber);
	console.log(user);
	
	return boardNumber;
}

function insertReport() {
	// 모달창에서 받은 변수들을 컨트롤러 단의 report/insertReport 에서 Map으로 묶어서 전송됨
	reporter = $('#reporter').val();
	btypeNo = $('#btypeNo').val();
	boardNo = $('#boardNo').val();
	writer = $('#writer').val();
	reportReason = $('#reportReason').val();
	
	if(reporter != ''){
		$.ajax({
			url : "/report/insertReport",
			data : {
				
				
				"reporter": reporter,
				"writer" : writer,
				"boardNo" : boardNo,
				"btypeNo" : btypeNo,
				"reportReason" : reportReason,
				"category" : "기업 리뷰",
				
				
			},
			type : "get",
			dataType : "text", // 수신받을 데이터의 타입
			success : function(data) {
			console.log(data);
			
			
			},
		});
		
		alert("등록되었습니다.")
	} else {
		alert("부적절한 입력값");
	}
}



</script>

<style>
.companyInfo {
	display: flex;
	justify-content: space-around;
	align-items: center;
}

.float {
	position: fixed;
	width: 60px;
	height: 60px;
	bottom: 40px;
	right: 40px;
	background-color: #0C9;
	color: #FFF;
	border-radius: 50px;
	text-align: center;
	box-shadow: 2px 2px 3px #999;
}

.my-float {
	margin-top: 22px;
}
</style>
</head>

<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<c:import url="../header.jsp" />

	<main id="main">
		<!-- Basic Section - CompanyInfo Page -->
		<section id="companyInfo" class="basic">
			<!-- <h1>기업리뷰 상세 페이지</h1> -->
			<div class="container mt-3">

				<!-- 해당 기업 리뷰 클릭하면 기업후기 대신 기업명 표기 구현 예정 
					 작성자는 익명 처리 할 예정 -->
				<p>기업후기</p>

				<ul class="list-group">

					<c:forEach var="rev" items="${revList}">
						<table class="table table-striped">
							<tr>
								<th>작성자</th>
								<td>${rev.revWriter}</td>
							</tr>
							<tr>
								<th>제목</th>
								<td>${rev.revTitle}</td>
							</tr>
							<tr>
								<th>파트</th>
								<td>${rev.revProfession}</td>
							</tr>
							<tr>
								<th>내용</th>
								<td>${rev.revContent}</td>
							</tr>
							<tr>
								<th>장점</th>
								<td>${rev.revGood}</td>
							</tr>
							<tr>
								<th>단점</th>
								<td>${rev.revBed}</td>
							</tr>
						</table>
						<input type="button" class="btn btn-info" value="수정"
								onclick="location.href='/companyInfo/editWrittenBoard?companyInfoNo=${param.companyInfoNo}&revNo=${rev.revNo}';" />	
						<input type="button" class="btn btn-danger" value="삭제"
								onclick="location.href='/companyInfo/deleteWrittenBoard?companyInfoNo=${param.companyInfoNo}&revNo=${rev.revNo}';" />				
						<input type="button" class="btn btn-primary" value="목록으로"
								onclick="location.href='/companyInfo/entire?companyInfoNo=${param.companyInfoNo}&revNo=${rev.revNo}';" />
						<input type="button" class="btn btn-success" value="스크랩"
								onclick="location.href='/companyInfo/insertScrap?companyInfoNo=${param.companyInfoNo}&scrapBoard=${rev.revNo}';" />
								<!--  신고 버튼 추가함 (전일) -->
						<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">신고</button>								
					</c:forEach>
				</ul>
			</div>
			<a href="/companyInfo/writtenBoard?companyInfoNo=${param.companyInfoNo}"
				class="float"><i class="fa fa-plus my-float"></i></a>

		</section>
		
		
		
		
		
		
		
		
		<!--  -----------------------------------------------report board modal------------------------------------------------------------------ -->
		<!-- The Modal   -->
		<div class="modal" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">신고할 게시글 선택</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>

					<!-- Modal body -->
					
					
					<select class="form-select" id="select" name="select"
						onchange="selectNo(this.value);">
						<option value="-1">신고할 게시판을 선택</option>
						<c:forEach items="${revList}" var="revList" begin="0" end="${fn:length(revList)}" varStatus="status">
							
							<option value="${revList.revNo}&${status.index}">${revList.revTitle} ${revList.revNo}</option>
						</c:forEach>
					</select>

					
						
					
						<label for="title" class="form-label">게시글 작성자 : </label> <input
									type="text" class="form-control" id="writer" placeholder="게시글 작성자를 입력하세요..."
									name="writer" />
							
						<label for="title" class="form-label">신고글 작성자 : </label> <input
									type="text" class="form-control" id="reporter" placeholder="신고글 작성자를 입력하세요..."
									name="reporter" />




					<div class="modal-body">Modal body..</div>
					<div class="mb-3 mt-3">
						<label for="title" class="form-label">신고사유 : </label> <input
							type="text" class="form-control" id="reportReason"
							placeholder="신고 사유를 입력하세요..." name="reportReason" />
					</div>

					
					<div>
						<input type="hidden" id="btypeNo" name="btypeNo" value="3">
					</div>
					<div>
						<input type="hidden" id="boardNo" name="boardNo">
					</div>
					

					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-warning"
							onclick="insertReport()">등록</button>
						<button type="button" class="btn btn-danger"
							data-bs-dismiss="modal">Close</button>
					</div>

				</div>
			</div>
		</div>
		<!--  ----------------------------------------------------------------------------------------------------------------------
		
		
		
		
		
		<!-- End Basic Section -->
	</main>

	<c:import url="../footer.jsp" />

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
	<script src="https://kit.fontawesome.com/ffcac42df4.js"
		crossorigin="anonymous"></script>
</body>
</html>
