<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<title>Study List - DoDeveloper</title>
<meta content="" name="description" />
<meta content="" name="keywords" />

<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>

<!-- 부트스트랩 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<!-- select2 css cdn -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.9/css/select2.min.css"
	rel="stylesheet" />

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

<!-- 스터디 listAll css 파일 -->
<link href="/resources/assets/css/study/listAll.css" rel="stylesheet" />

<script>
	$(function() {
		$('.studyLang').select2({
			placeholder : '스터디 언어'
		});

		let studyStackList = new Array();

		//스터디 언어 선택했을 때 필터링 
		$('.studyLang').on("select2:select", function() {
			console.log("select", $('.studyLang').val());

			//필터링할 스터디 1개 이상일때만 ajax호출
			if ($('.studyLang').val().length > 0) {
				searchStudy();
			}

		})

		//스터디 언어 삭제했을 때 필터링
		$(".studyLang").on("select2:unselect", function() {
			//alert("!");
			console.log("unselect" ,$('.studyLang').val());
			
			if($('.studyLang').val().length == 0){
				$(".studyList").css("display","block");
				$(".paging").css("display","block");
				$(".studyListBySearch").empty();
			}
			
			if ($('.studyLang').val().length > 0) {
				searchStudy();
			}
		});
		
		//모집중만 보기 클릭했을 때		
		let status = 0;
        $('#studyStatus').on('click',function(){
			 console.log(status);
            if(status==0){
            	//모집중 글만 보기
                $(this).attr('class','text-light bg-secondary text-center border border-secondary rounded-2 p-2');
               	status++;
            	   	
            }else if(status==1){
            	//모집중+모집마감 글 전체 보기
                $(this).attr('class','text-secondary text-center border border-secondary rounded-2 p-2');
                status--;
                
            }
        });
		

	});
	
	//스터디 언어로 필터링 (복수 선택 가능)
	function searchStudy(){
		console.log($('.studyLang').val());
		
		$.ajax({
			url : '/study/searchStudyByStack',
			type : 'post',
			data : JSON.stringify($('.studyLang').val()), //보내는 데이터를 제이슨 형식으로
			headers : { // 서버에 보내지는 데이터 형식
				"content-type" : "application/json"
			},
			dataType : "json",
			contentType : false, //default true : 데이터를 쿼리스트링 형태로 보내는지 아닌지
			async : false, //받아올 데이터가 있어야 파싱 가능.
			success : function(data) { //HttpStatus code가 200인 경우 이 코드 실행
				console.log(data);		
				$(".studyList").css("display","none");
				$(".paging").css("display","none");
				outputSearchStudy(data);
			},
			error : function(data){ //HttpStatus code가 200이 아닌경우 이 코드 실행
				console.log(data);
			}
		});
	}

	//ajax로 데이터를 가져왔으므로 js에서 데이터들을 출력한다.
	function outputSearchStudy(data) {
		
		let stuStackList = data.stuStackList;
		let studyList = data.studyList;
		
		let output = `<div class="row row-cols-md-4 ">`;
		output += `<div class="col mb-4">`;
		output += `<div class="card">`;
		output += `<div class="card-body p-4 text-center" style="height: 225px;">`;
		output += `<h5 class="text-danger" style="line-height: 180px; cursor: pointer;"onclick="location.href='/study/writeStudyBoard';">`;
		output += `<b>나도 스터디 만들기</b></h5></div></div></div>`;
	
		$.each(studyList, function(i, e) {
			output += `<div class="col mb-4" style="cursor: pointer;" id="studyList" onclick="location.href='/study/viewStudyBoard?stuNo=\${e.stuNo}';">`;
			output += `<div class="card">`;
			output += `<div class="card-body p-4" style="width: 100%;">`;
			output += `<div class="">`;
			output += `<p class="card-subtitle mb-2 text-body-secondary text-truncate" style="max-width: 100%;">📍\${e.stuLoc }</p>`;
			output += `</div>`;
			output += `<div class="mt-4"><h5 class="card-title text-truncate" style="max-width: 100%;"><b>\${e.stuTitle }</b></h5></div>`;
			output += `<div class="mt-4">`;
			output += `<p class="card-text">`;
			
			//스터디 언어는 여러개이므로 함수를 이용해 값을 비교해서 가져온다
			
			let stackName = [];
			stackName = getStudyStack(e.stuNo, stuStackList);
			console.log(stackName);
			for(let j=0; j < stackName.length; j++){
				//console.log(stackName[j])
				output += `<span class="badge text-bg-secondary me-1">\${stackName[j]}</span>`;			
			}
					
			output += `</p>`;
			output += `</div>`;
			
			output += `<div class="d-flex mt-4">`;
			output += `<div class="me-auto"><p class="card-text">\${e.stuWriter }</p></div>`;
			output += `<div class="me-2">`;
			output += `<p class="card-text text-body-secondary">`;
			output += `<i class="bi bi-eye"></i>\${e.readCount }`;
			output += `</p></div>`;
			output += `<div class=""><p class="card-text text-body-secondary"><i class="bi bi-bookmark"></i>\${e.scrape }</p>`;
			output += `</div></div></div></div></div>`;
			
		});
		
		
		$(".studyListBySearch").html(output);
	}
	
	function getStudyStack(stuNo, stuStackList){
		//console.log(stuNo, stuStackList);
		let result = [];
		//console.log(result);
		$.each(stuStackList, function(i, e){
			if(e.stuBoardNo == stuNo){
				//console.log(i, e);
				result.push(e.stackName);
			}
		});
		
		return result;
	}

	//검색 조건 유효성 체크
	function isValid() {
		let result = false;

		let searchType = $("#searchType").val();
		let searchValue = $("#searchValue").val();

		console.log(searchType, searchValue);

		//검색어에 있어서는 안되는 쿼리문 키워드 배열로 정의
		let keyWord = new Array("OR", "SELECT", "AND", "INSERT", "UPDATE",
				"DELETE", "DROP", "EXEC", "TRUNCATE", "CREATE", "ALTER");

		if (searchType != -1 && searchValue.length > 0) {
			//검색 방법과 검색 내용이 있을 때 쿼리문 키워드가 존재하는지 검사

			let regEx;
			for (let i = 0; i < keyWord.length; i++) {
				//keyWord 배열에 있는 문자열 패턴이 있는지 없는지 전역적으로 검사하는 객체 생성
				regEx = new RegExp(keyWord[i], "gi");

				if (regEx.test(searchValue)) {
					//유저가 입력한 검색어에 키워드가 존재하는지 검사
					alert('검색어가 올바르지 않습니다!');
					$('#searchValue').val('');
					$('#searchValue').focus();
					return false;
				}
			}

			result = true; //검색방법, 검색 내용 있을 때

		} else { //검색 방법과 검색 내용, 쿼리문 키워드 없을 때
			alert('검색 방법과 검색 내용을 입력하세요!');
		}

		return result;
	}

	
	
</script>

</head>
<style>
#studyStatus{cursor:pointer;}
.study{ transition: transform 250ms;}
.study:hover {
	transform: translateY(-10px);
}
</style>

<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="../header.jsp"%>

	<main id="main">
		<!-- Basic Section - Study Page -->
		<section id="study" class="studyBasic">

			<div class="container" style="width: 70%">
				<div class="container">
					<h3 class="center text-center text-light">
						<b>🔥 개발 스터디 모집</b>
					</h3>
				</div>

				<!-- 공지사항 넣을 부분 -->
				<div class="container mt-3">공지사항</div>

				<!-- 상단 필터 & 검색부분 -->
				<div class="container mt-3">

					<!-- 스터디할 언어 선택해서 select -->
					<div class="row">
						<div class="col-md-3">
							<select class="studyLang form-control" multiple="multiple"
								id="chooseStack" name="chooseStack" style="width: 100%;">
								<c:forEach var="stack" items="${stackList }">
									<option value="${stack.stackNo }" onclick='selectStack();'>${stack.stackName }</option>
								</c:forEach>
							</select>
						</div>

						<!-- 모집중 or 모집마감 bg-primary-subtle-->
						<div class="col-md-2">
						<!-- <form> -->
							<!-- <div id="studyStatus" class="text-secondary text-center border border-secondary rounded-2 p-2">
								모집중만 보기
							</div> -->
							<input id="studyStatus" type="submit" class="text-secondary text-center border border-secondary rounded-2 p-2" value="👀 모집중만 보기" />
						<!-- </form> -->
						
							<!-- <button id="studyStatus" class="btn btn-outline-secondary" style="width: 100%">
								모집중만 보기</button> -->
						</div>

						<div class="col-md-2"></div>

						<!-- 검색바 -->

						<div class="col-md-5">
							<form>
								<div class="row">
									<div class="col-md-4">
										<select class="form-select" id="searchType" name="searchType">
											<option value="-1">검색 방법</option>
											<option value="title">제목</option>
											<option value="writer">작성자</option>
											<option value="content">내용</option>
										</select>
									</div>
									<div class="col-md-5">
										<input type="text" class="form-control mb-4" id="searchValue"
											name="searchValue" placeholder="검색할 내용 입력" />
									</div>
									<div class="col-md-3">
										<input type="submit" class="btn btn-secondary" value="검색"
											style="width: 100%" onclick="return isValid();" />
									</div>
								</div>
							</form>
						</div>


					</div>
				</div>
				<!-- 스터디 언어로 검색시 나오는 리스트 -->
				<div class="container mt-3 studyListBySearch">

				</div>
				<!-- 첫 화면 : 스터디 모임글 리스트 -->
				<div class="container mt-3 studyList">

					<%-- 	${studyList }
				${stuStackList } --%>
					<div class="row row-cols-md-4 ">
						<!-- 모임글 추가하기 -->
						<div class="col-md mb-4 study">
							<div class="card">
								<div class="card-body p-4 text-center " style="height: 225px;">
									<h5 class="text-danger"
										style="line-height: 180px; cursor: pointer;"
										onclick="location.href='/study/writeStudyBoard';">
										<b>나도 스터디 만들기</b>
									</h5>
								</div>
							</div>
						</div>

						<c:forEach var="study" items="${studyList }">
							<!-- 모임글 1개 -->
							<c:choose>
								<c:when test="${study.status == '모집중' }">
									<div class="col-md mb-4 study" style="cursor: pointer;"
										onclick="location.href='/study/viewStudyBoard?stuNo=${study.stuNo}';">
										<div class="card">
											<div class="card-body p-4" style="width: 100%;">
												<div class="">
													<p class="card-subtitle mb-2 text-body-secondary text-truncate" style="max-width: 100%;">
													📍${study.stuLoc }</p>
												</div>
		
												<!-- 제목 -->
												<div class="mt-4">
													<h5 class="card-title text-truncate" style="max-width: 100%;">
														<b>${study.stuTitle }</b>
													</h5>
												</div>
		
												<!-- 스터디 언어 stuStack테이블에서 가져올 예정 -->
												<div class="mt-4">
													<p class="card-text">
														<c:forEach var="stack" items="${stuStackList }">
															<c:if test="${study.stuNo == stack.stuBoardNo }">
																<span class="badge text-bg-secondary">${stack.stackName }</span>
															</c:if>
														</c:forEach>
													</p>
												</div>
		
												<div class="d-flex mt-4">
													<div class="me-auto">
														<p class="card-text">${study.stuWriter }</p>
													</div>
													<div class="me-2">
														<p class="card-text text-body-secondary">
															<i class="bi bi-eye"></i> ${study.readCount }
														</p>
													</div>
													<div class="">
														<p class="card-text text-body-secondary">
															<i class="bi bi-bookmark"></i> ${study.scrape }
														</p>
													</div>
												</div>
		
											</div>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<div class="col-md mb-4 study" style="cursor: pointer;"
										onclick="location.href='/study/viewStudyBoard?stuNo=${study.stuNo}';">
										<div class="card position-relative">
											<span class="position-absolute top-50 start-50 translate-middle badge pill bg-black text-light"
												style="width:100%; height:100%; opacity:75%;">
											</span>
											<span class="position-absolute top-50 start-50 translate-middle badge text-light"
												style="font-size:17px;">
											    모집 마감
											</span>
											<div class="card-body p-4" style="width: 100%;">
												<div class="">
													<p class="card-subtitle mb-2 text-body-secondary text-truncate" style="max-width: 100%;">
													📍${study.stuLoc }</p>
												</div>
		
												<!-- 제목 -->
												<div class="mt-4">
													<h5 class="card-title text-truncate" style="max-width: 100%;">
														<b>${study.stuTitle }</b>
													</h5>
												</div>
		
												<!-- 스터디 언어 stuStack테이블에서 가져올 예정 -->
												<div class="mt-4">
													<p class="card-text">
														<c:forEach var="stack" items="${stuStackList }">
															<c:if test="${study.stuNo == stack.stuBoardNo }">
																<span class="badge text-bg-secondary">${stack.stackName }</span>
															</c:if>
														</c:forEach>
													</p>
												</div>
		
												<div class="d-flex mt-4">
													<div class="me-auto">
														<p class="card-text">${study.stuWriter }</p>
													</div>
													<div class="me-2">
														<p class="card-text text-body-secondary">
															<i class="bi bi-eye"></i> ${study.readCount }
														</p>
													</div>
													<div class="">
														<p class="card-text text-body-secondary">
															<i class="bi bi-bookmark"></i> ${study.scrape }
														</p>
													</div>
												</div>
		
											</div>
										</div>
									</div>
								</c:otherwise>
							</c:choose>


						</c:forEach>

					</div>

				</div>

				<!-- 페이징 -->
				<%-- ${pagingInfo } --%>
				<div class="row mt-3 paging">
					<div class="col">
						<ul class="pagination justify-content-center">
							<c:if test="${pagingInfo.pageNo > 1}">
								<li class="page-item"><a
									class="page-link text-light bg-danger" style="border: none"
									href="/study/listAll?pageNo=${param.pageNo -1 }&searchType=${param.searchType }&searchValue=${param.searchValue }"
									aria-label="Previous"> <span aria-hidden="true"><i
											class="bi bi-arrow-left-short"></i></span>
								</a></li>
							</c:if>

							<c:forEach var="i"
								begin="${pagingInfo.startNumOfCurrentPagingBlock }"
								end="${pagingInfo.endNumOfCurrentPagingBlock }" step="1">
								<li class="page-item" id="${i }"><a
									class="page-link text-black" style="border: none"
									href="/study/listAll?pageNo=${i }&searchType=${param.searchType }&searchValue=${param.searchValue }">${i }</a>
								</li>
							</c:forEach>

							<c:if test="${pagingInfo.pageNo < pagingInfo.totalPageCnt}">
								<li class="page-item"><a
									class="page-link text-light bg-danger" style="border: none"
									href="/study/listAll?pageNo=${param.pageNo +1 }&searchType=${param.searchType }&searchValue=${param.searchValue }"
									aria-label="Previous"> <span aria-hidden="true"><i
											class="bi bi-arrow-right-short"></i></span>
								</a></li>
							</c:if>

						</ul>
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
</body>
</html>
