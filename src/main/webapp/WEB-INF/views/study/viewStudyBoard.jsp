<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<title>View Study - DoDeveloper</title>
<meta content="" name="description" />
<meta content="" name="keywords" />

<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>

<!-- 부트스트랩 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<!-- select2 css cdn -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.9/css/select2.min.css"
	rel="stylesheet" />

<!-- include summernote css/js -->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">

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



<!-- 스터디 writeStudyBoard css 파일 -->
<link href="/resources/assets/css/study/writeStudyBoard.css"
	rel="stylesheet" />

<script>

	let replies = "";
	
	$(function() {
		
		//카카오 공유하기
		Kakao.init('8b1554294fb8c555a2e57d63f00a7589'); // 사용하려는 앱의 JavaScript 키 입력
		
		Kakao.Share.createDefaultButton({
			container: '#kakaotalk-sharing-btn',
			objectType: 'feed',
			content: {
				title: '${studyList.stuTitle }',
				description: '${studyList.stuWriter }님의 스터디 모집글',
			    link: {
			    	// [내 애플리케이션] > [플랫폼] 에서 등록한 사이트 도메인과 일치해야 함
			    	mobileWebUrl: 'http://localhost:8081',
			    	webUrl: 'http://localhost:8081',
			    	},
			},
			social: {
				likeCount: 286,
			    commentCount: 45,
			    sharedCount: 845,
			},
			buttons: [
				{
					title: '웹으로 보기',
			        link: {
			        	mobileWebUrl: 'http://localhost:8081',
			        	webUrl: 'http://localhost:8081',
			        },
			    },
			    {
			    	title: '앱으로 보기',
			        link: {
			        	mobileWebUrl: 'http://localhost:8081',
			        	webUrl: 'http://localhost:8081',
			        },
			    },
			],
		});
		
		
		// ====== 댓글 관련 시작 =====
		//댓글 리스트 가져오는 함수 호출
		getAllReplies();
		
		//댓글 작성 버튼 눌렀을 때
		$(".saveReply").on('click',function(){
			
			let replyContent = $(".replyContent").val();
			console.log(replyContent);
						
			
			if(replyContent == ''){
				
				alert('댓글을 남겨주세요');
				$(".replyContent").focus();
				
			}else{
				
				let replyer = preAuth(); //댓글 작성자
				
				if(replyer != ''){
				//댓글 내용이 있다면, 로그인한 유저인지 먼저 체크해준다.					
				let bNo = ${studyList.stuNo}; //게시글 번호를 저장
					
				let newReply = {
					"bNo" : bNo + "",
					"replyer" : replyer,
					"replyContent" : replyContent,
				}; //새로 추가할 댓글을 객체로 선언
				
					$.ajax({
						url : "/studyReply/saveReply/" + bNo,
						type : "post",
						async : false, //받아올 데이터가 있어야 파싱 가능.
						data : JSON.stringify(newReply), // 서버에 넘겨주는 데이터
						headers : { // 서버에 보내지는 데이터 형식
							"content-type" : "application/json"
						},
						dataType : "text",
						success : function(data) {
							console.log(data);
							if(data == 'insertSuccess'){
								$(".replyList").empty();
								$(".replyContent").val('');
								getAllReplies();
							}
						},
					});
				}
				
			}
			
		});
		
		// ====== 댓글 관련 끝 =====
		// ====== kakao map 관련 시작 =====
		
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new kakao.maps.LatLng(${studyList.stuY }, ${studyList.stuX }), // 지도의 중심좌표
			level : 3
		// 지도의 확대 레벨
		};

		var map = new kakao.maps.Map(mapContainer, mapOption);

		// 마커가 표시될 위치입니다 
		var markerPosition = new kakao.maps.LatLng(${studyList.stuY }, ${studyList.stuX });

		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
			position : markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);

		// 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
		var iwContent = `<div style="padding:10px; width:100%; "><p class="mb-1"><b>${studyList.stuLoc}</b></p>`;
		iwContent += `<p class="pb-2"><a href="https://map.kakao.com/link/map/${studyList.stuLoc},${studyList.stuY }, ${studyList.stuX }" target="_blank"><span class="badge text-bg-secondary me-2">큰지도보기</span></a>`;
		iwContent += `<a href="https://map.kakao.com/link/to/${studyList.stuLoc},${studyList.stuY }, ${studyList.stuX }" target="_blank"><span class="badge text-bg-secondary">길찾기</span></a></p>`;
		iwContent += `</div>`; 
		
		iwPosition = new kakao.maps.LatLng(${studyList.stuY }, ${studyList.stuX }); //인포윈도우 표시 위치입니다

		// 인포윈도우를 생성합니다
		var infowindow = new kakao.maps.InfoWindow({
			position : iwPosition,
			content : iwContent
		});

		// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
		infowindow.open(map, marker);
		
		// 로드뷰 이미지				
		var roadviewContainer = document.getElementById('roadview'); //로드뷰를 표시할 div
		var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
		var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

		var position = new kakao.maps.LatLng(${studyList.stuY }, ${studyList.stuX });

		// 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
		roadviewClient.getNearestPanoId(position, 50, function(panoId) {
		    roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
		});
		
		// ====== kakao map 관련 끝 =====
		
		//북마크 버튼을 눌렀을 때
		let book = 0;
        $('.bookMark').on('click',function(){
            if(book==0){
                $(this).attr('class','bi-bookmark-fill');
               	book++;
            }else if(book==1){
                $(this).attr('class','bi-bookmark');
                book--;
            }

        });
        
        
        //url 쿼리스트링 값 가져와서 스터디 신청완료했을때 알럿창 표시
        let url = new URL(window.location.href);
        let urlParams = url.searchParams;
        
        console.log(urlParams);
        
        if(urlParams.get('status') == 'success'){
        	alert("참여신청 완료했습니다.");	
        }
        
        
	});
	
	// ====== 댓글 관련 시작 =====
	//DB에서 모든 댓글 리스트를 가져오는 함수
	function getAllReplies(){
		//댓글을 조회할 게시글 번호
		let stuNo = ${studyList.stuNo};
		console.log(stuNo + "번째 게시글 댓글 조회");

		//get방식이라 쿼리스트링 붙여서 보낼 수 있음.. boardNo는 변수로 들어간다
		$.ajax({
			//내부에서 작동하는거고 페이지이동 없어서(url 변경 안된다) interceptor에 걸리지 않는다. 로그인할때는 Form으로 보냈었음..
			url : "/studyReply/replyAll/" + stuNo,
			type : "get",
			dataType : "json",
			async : false, //받아올 데이터가 있어야 파싱 가능.
			success : function(data) {
				console.log(data);
				//replies = data;
				if(data.length > 0){					
					showReplies(data);
				}else if(data.length == 0){
					showReplieEmpty();
				}
			},
		});
	}
	
	//댓글이 하나도 없을때 호출하는 함수
	function showReplieEmpty(){
		let output = `<ul class="list-group mt-4">`;
		output += `<li class="list-group-item">`;
		output += `아직 댓글이 없습니다.`;
		output += `</li>`;
		output += `</ul>`;
		$(".replyList").html(output);
	}
	
	//댓글이 하나라도 있다면, 모든 댓글 리스트 호출하는 함수
	function showReplies(data){
	
		let output = `<ul class="list-group mt-4">`;
		
		$.each(data, function(i, e) {
			
			output += `<li class="list-group-item">`;
			output += `<div class="row" id="reply_\${e.replyNo}">`;
			output += `<div class="col-md-1"><b>\${e.replyer}</b></div>`;
			output += `<div class="col-md-8">\${e.replyContent}</div>`;
			
			//게시글 작성일 계산하는 함수 호출해서 변수에 넣기
			let diff = dateSum(e.writtenDate);
			output += `<div class="col-md-3" style="text-align:right">\${diff}`;
						
			//댓글 단 사람과 로그인한 사람이 같을 경우에만 수정 삭제 버튼이 보이도록 한다
			if(e.replyer == `${loginMember.userId}`){
				output += `<span style="cursor:pointer" class="badge text-bg-secondary ms-2 modifyReplyArea" onclick="modifyReplyArea(\${e.replyNo}, '\${e.replyContent}');">수정</span>`;
				output += `<span style="cursor:pointer" class="badge text-bg-secondary ms-2 removeReplyAlert" onclick="removeReplyAlert(\${e.replyNo});">삭제</span></div>`;					
			}
			
			output += `</div>`;
			output += `</li>`;
			
		});
		
		output += `</ul>`;
		
		$(".replyList").html(output); 
	}
	
	//댓글 수정 버튼 눌렀을 때 호출되는 함수
	function modifyReplyArea(replyNo, replyContent){
		
		//댓글 수정 창이 보이게 한다. <b>${loginMember.userId}</b>
		let modifyReply = `<div class="row" id="cancle_\${replyNo}">`;
		modifyReply += `<div class="col-md-1"></div>`;
		modifyReply += `<div class="col-md-8"><input class="modiftReplyContent" type="text" value="\${replyContent}" style="width:100%;" /></div>`;
		modifyReply += `<div class="col-md-3" style="text-align:left">`;
		modifyReply += `<span style="cursor:pointer" class="badge text-bg-secondary me-2" onclick="modifyReply(\${replyNo})">수정</span>`;
		modifyReply += `<span style="cursor:pointer" class="badge text-bg-secondary" onclick="cancleModifyReply(\${replyNo});">취소</span></div>`;
		modifyReply += `</div>`;
		
		$(modifyReply).insertAfter($(`#reply_\${replyNo}`));
		
	}
	
	//댓글 수정 창에서 수정버튼을 눌렀을 떄 호출되는 함수
	function modifyReply(replyNo){
		
		//수정할 댓글 내용과 댓글 번호를 ajax로 보내자
		let replyContent = $(".modiftReplyContent").val();
		console.log(replyContent);
		let bNo = `${studyList.stuNo}`;
		
		let modifyReply = {
			"bNo" : bNo,
			"replyNo" : replyNo,
			"replyer" : `${loginMember.userId}`,
			"replyContent" : replyContent,
		}; //수정할 댓글 객체로 선언
		
		console.log(JSON.stringify(modifyReply));
		
		$.ajax({
			url : "/studyReply/modifyReply/" + replyNo + "/" + bNo,
			type : "put",
			data : JSON.stringify(modifyReply),
			contentType : 'application/json; charset=utf-8',
			dataType : "text",
			async : false, //받아올 데이터가 있어야 파싱 가능.
			success : function(data) {
				console.log(data);
				if(data == 'updateSuccess'){
					$(`#cancle_\${replyNo}`).remove();
					getAllReplies();
				}
			},
		});
	}
	
	//댓글 수정 창에서 취소 버튼을 눌렀을 때 호출되는 함수
	function cancleModifyReply(replyNo){
		$(`#cancle_\${replyNo}`).empty();
	}
	
	//댓글 삭제 버튼 눌렀을 때 호출되는 함수
	function removeReplyAlert(replyNo){
		if(window.confirm("댓글을 삭제하시겠습니까?")){
			//예 - true값이 넘어오면 db에서 삭제하자
			$.ajax({
				url : "/studyReply/deleteReply/" + replyNo,
				type : "delete",
				header : {
					//PUT, DELETE, PATCH등의 HTTP method가 동작하지 않는 과거의 웹 브라우저라면 post방식으로 작동되도록 한다 
					"X-HTTP-Method-Override" : "POST"
				},
				dataType : "text",
				async : 'false', //받아올 데이터가 있어야 파싱 가능.
				success : function(data) {
					console.log(data);
					if(data == 'deleteSuccess'){
						getAllReplies();
					}
				},
			});
		}	
	}
	
	//댓글 쓴지 얼마나 지났는지 계산하는 함수
	function dateSum(date){
		let postDate = new Date(date); 
		let now = new Date();
		
		let dateDiff = (now - postDate) / 1000; // 시간차 초단위
		
		let times = [
			{name: "일", time: 60 * 60 * 24},
			{name: "시간", time: 60 * 60},
			{name: "분", time: 60}
		];
		
		for(let val of times){
			let betweenTime = Math.floor(dateDiff / val.time);
			//console.log(dateDiff, betweenTime);
			
			//일, 시간, 분 단위로 하나씩 나눠봐서 0보다 큰 숫자가 나올때, 그 값을 return
			if(betweenTime >0){
				return betweenTime + val.name + "전"; //시간
			}
		}
		
		return "방금전";
	}
	
	//로그인한 유저인지 아닌지 체크해주는 함수
	function preAuth(){
		//ajax는 내부에서 작동하는거고, 페이지 이동이 없어서 url 변경이 안된다.
		//즉, 인터셉터가 동작하지 않는다는 뜻이다. 그래서 로그인 했는지 안했는지 검사는 자바스크립트에서 검사해야한다.
		let writer = '${sessionScope.loginMember.userId}';
		
		if(writer == ''){ //로그인 안했다면
			location.href='/member/login?redirectUrl=viewStudyBoard&stuNo=${studyList.stuNo}';
			writer = '${sessionScope.loginMember.userId}';
		}
		
		return writer; //로그인을 했다면 writer 반환해준다
	}
	
	// ====== 댓글 관련 끝 =====
	
	//참여신청팝업창에서 참여신청버튼을 눌렀을 때 유효성검사
	function isVaild(){
		let result = false;
		
		if($("#reason").val() == null || $("#reason").val() == ''){
			alert("참여 신청 이유를 입력해주세요");
		}else if($("#reason").val().length < 10){
			alert("참여 신청 이유는 10자 이상 입력해주세요");
		}else{
			result = true;			
		}
		
		return result;
	}
	
	
</script>
<style>
i {
	cursor: pointer;
}
</style>
</head>

<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="../header.jsp"%>

	<main id="main">
		<%-- ${studyList } --%>
		<!-- Basic Section - Study Page -->
		<section id="study" class="studyBasic">
			<div class="container" style="width: 70%">
				<div class="container">

					<div class="d-flex">
						<div class="me-auto">
							<h3 class="text-light">
								<b>${studyList.stuTitle }</b>
							</h3>
						</div>
						<!-- 로그인한 유저와 작성자가 같을 때에만 수정 삭제 버튼이 보이도록 처리 -->
						<c:if test="${loginMember.userId == studyList.stuWriter }">
							<!-- 수정 버튼 -->

							<div class="icon-link icon-link-hover"
								style="-bs-icon-link-transform: translate3d(0, -.125rem, 0);"
								onclick="location.href='/study/modifyStudyBoard?stuNo=${studyList.stuNo}';">
								<i class="bi bi-pencil fs-5 me-2" style="color: #ffffff;"></i>
							</div>
							<!-- 삭제 버튼 -->
							<div class="studyBoardDelete icon-link icon-link-hover"
								style="-bs-icon-link-transform: translate3d(0, -.125rem, 0);"
								data-bs-toggle="modal" data-bs-target="#deleteModal">
								<i class="bi bi-trash3 fs-5 me-2" style="color: #ffffff;"></i>
							</div>
						</c:if>

						<!-- 카카오 공유 버튼 -->
						<a id="kakaotalk-sharing-btn" href="javascript:;"
							class="icon-link icon-link-hover"
							style="-bs-icon-link-transform: translate3d(0, -.125rem, 0);">
							<i class="bi bi-share fs-5 me-2" style="color: #ffffff;"></i>
						</a>

					</div>

					<p class="text-light mt-3">
						<b>${studyList.stuWriter }</b>
					</p>
				</div>


				<!-- 모집글 삭제 확인용 모달창 -->
				<div class="modal fade" id="deleteModal">
					<div class="modal-dialog">
						<div class="modal-content">

							<!-- Modal Header -->
							<div class="modal-header">
								<h4 class="modal-title">스터디 모집글 삭제</h4>
								<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
							</div>

							<!-- Modal body -->
							<div class="modal-body">해당 글을 삭제하시겠습니까?</div>

							<!-- Modal footer -->
							<div class="modal-footer">
								<button type="button" class="btn btn-outline-danger"
									data-bs-dismiss="modal">취소</button>
								<button type="button" class="btn btn-danger"
									onclick="location.href='/study/deleteStudy?stuNo=${studyList.stuNo }';">삭제</button>
							</div>

						</div>
					</div>
				</div>



				<div class="container pt-4">


					<div class="card">
						<div class="card-body">

							<div class="row mb-2">
								<!-- 모집인원 -->
								<div class="col-md-6 ">
									<div class="mb-1 ">
										<b>모집 인원</b> <span class="">${studyList.stuPers }명</span>
									</div>

								</div>

								<!-- 마감일 -->
								<div class="col-md-6">
									<div class="mb-1 ">
										<b>모집 마감일</b> <span class=""> <fmt:formatDate
												pattern="yyyy-MM-dd" value="${studyList.endDate }" />

										</span>
									</div>
								</div>
							</div>


							<div class="row mb-2">
								<!-- 연락 방법 -->
								<div class="col-md-6">
									<div class="mb-1 ">
										<b>연락 방법</b> <span class=""><a
											href="${studyList.contactLink }">${studyList.contactLink }</a></span>
									</div>
								</div>

								<!-- 진행 기간 -->
								<div class="col-md-6">
									<div class="mb-1">
										<b>진행 기간</b> <span class="">${studyList.stuDate }</span>
									</div>
								</div>
							</div>


							<!-- 스터디 언어 선택 -->
							<div class="row">
								<div class="col-md-6">
									<div class="">
										<b>스터디 언어</b>
										<c:forEach var="stack" items="${stuStackList }">
											<span class="badge text-bg-secondary">${stack.stackName }</span>
										</c:forEach>
									</div>
								</div>
								<div class="col-md-6">
									<div class="">
										<b>모집 상태</b> <span class="">${studyList.status }</span>
									</div>
								</div>
							</div>

						</div>
					</div>


					<!-- 내용 -->
					<div class="card mt-3">
						<div class="card-body">
							<div class="mb-2">
								<b>스터디 소개</b>
							</div>
							<div class="">${studyList.stuContent }</div>
						</div>
					</div>


					<!-- 카카오 지도 입력 부분 -->
					<div class="card mt-3">
						<div class="card-body">
							<div class="mb-2">
								<b>스터디 예정 장소</b>
							</div>
							<div class="row">
								<!-- 카카오 지도 출력 부분 -->
								<div class="col-md-6">
									<div id="map" style="width: 100%; height: 400px;"></div>
								</div>
								<!-- 카카오 로드뷰 부분 -->
								<div class="col-md-6">
									<div id="roadview" style="width: 100%; height: 400px;"></div>
								</div>
							</div>
						</div>
					</div>


					<!-- 로그인한 유저와 작성자가 다를 때에만 참여신청 버튼이 보이도록 처리 -->
					<!-- 북마크, 참여신청 버튼 -->
					<c:if test="${loginMember.userId != studyList.stuWriter }">
						<div class="row mt-4">
							<div class="col-md-1">
								<button type="button" class="btn btn-danger bookBtn"
									style="width: 100%">
									<i class="bi bi-bookmark bookMark"></i>
								</button>
							</div>

							<div class="col-md-11">
								<input type="button" class="btn btn-secondary" value="참여신청"
									style="width: 100%" data-bs-toggle="modal"
									data-bs-target="#exampleModal" />
							</div>
						</div>
					</c:if>

					<!-- 댓글 리스트 -->
					<div class="mb-3 replyList"></div>

					<!-- 댓글 작성창 -->
					<div class="form-floating mt-4">
						<div class="d-flex">
							<c:choose>
								<c:when test="${loginMember != null }">
									<div class="text-light" style="width: 100px;">
										<p>${loginMember.userId}</p>
									</div>
									<div class="flex-grow-1">
										<textarea class="form-control replyContent"
											placeholder="댓글을 작성하세요" id="floatingTextarea"></textarea>
									</div>
									<div class="">
										<button type="button"
											class="btn btn-secondary ms-3 p-3 saveReply">댓글 저장</button>
									</div>
								</c:when>
								<c:otherwise>
									<div class="text-light" style="width: 100px;">
										<p>비회원</p>
									</div>
									<div class="flex-grow-1">
										<textarea class="form-control replyContent"
											placeholder="로그인 후 댓글을 달 수 있습니다" id="floatingTextarea"></textarea>
									</div>
									<div class="">
										<button type="button"
											class="btn btn-secondary ms-3 p-3 saveReply">댓글 저장</button>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>


					<!-- 참여신청 버튼 눌렀을 때 뜨는 모달창 -->
					<div class="modal fade" id="exampleModal" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog  modal-dialog-centered">
							<div class="modal-content">
								<div class="modal-header">
									<h1 class="modal-title fs-5" id="exampleModalLabel">${studyList.stuWriter }님의
										스터디에 참여 신청</h1>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<form action="/studyApply/insertApply" method="post">
									<div class="modal-body">
										<input type="text" id="applyId" name="applyId"
											value="${loginMember.userId }" hidden="true" /> <input
											type="text" id="stuNo" name="stuNo"
											value="${studyList.stuNo}" hidden="true" />
										<div class="mb-3">
											<label for="reason" class="col-form-label">참여 신청하는
												이유를 간단하게 입력해주세요.</label>
											<textarea class="form-control" id="reason" name="reason"
												placeholder="10자 이상 입력하세요"></textarea>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">취소</button>
										<input type="submit" class="btn btn-danger"
											onclick="return isVaild();" value="참여신청" />
									</div>
								</form>


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

	<!-- select2 javascript cdn -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.9/js/select2.min.js"></script>
	<!-- summernote -->
	<script
		src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	<!-- kakao map api -->
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=60e5e9d6a47834eb03c485008a9e14a3&libraries=services"></script>
	<!-- 카카오톡 공유하기 -->
	<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.7.2/kakao.min.js"
		integrity="sha384-TiCUE00h649CAMonG018J2ujOgDKW/kVWlChEuu4jK2vxfAAD0eZxzCKakxg55G4"
		crossorigin="anonymous"></script>
</body>
</html>
