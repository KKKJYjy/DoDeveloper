<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
</style>
<meta content="" name="description" />
<meta content="" name="keywords" />
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		// Google Charts 라이브러리 로드
		google.charts.load('current', {
			'packages' : [ 'corechart' ]
		});
		google.charts.load('current', {
			'packages' : [ 'table' ]
		});
		google.charts.setOnLoadCallback(drawChart);

		// 현재 월 가져오기
		let nowMonth = new Date().getMonth() + 1; // 월은 0부터 시작하므로 +1
		$('#month').val(nowMonth); // 현재 월을 기본 선택값으로 설정

	});

	function drawChart() {

		// 페이지가 로드될 때 초기 데이터를 가져와서 차트 그리기
		$.ajax({
			url : "/admin/getUri", // 초기 데이터 가져오는 URL
			type : "GET",
			dataType : "json",
			async : true,
			success : function(data) {
				drawPie(data);
				drawTable(data);
				console.log(data);
			},

		});

		$('.monthSelector').change(function() {
			let month = $(this).val();

			// AJAX 요청
			$.ajax({
				url : "/admin/getLog", // 서버에서 데이터 가져오는 URL
				type : "GET",
				data : {
					month : month
				}, // 데이터 전달 수정
				dataType : "json", // 서버로부터 수신받을 데이터의 타입
				async : true,
				success : function(data) {
					console.log(data); // 데이터를 콘솔에 출력하여 확인
					drawLine(data);
				},

			});
		});
		$('.monthSelector').trigger('change');
	}

	function drawLine(data) {
		if (data != null) {
			// ConnectLog 데이터
			var connectLogData = new google.visualization.DataTable();
			connectLogData.addColumn('string', '날짜');
			connectLogData.addColumn('number', '접속수');
			data.connectLog.forEach(function(row) {
				connectLogData.addRow([ row.date, row.count ]);
			});

			let month = $('.monthSelector').val();
			var connectLogOptions = {
				title : month + '월 일일접속자',
				fontSize : 15,
				width : 650,
				height : 400,
				hAxis : {
					title : '일자'
				},
				vAxis : {
					title : '접속수'
				},
				explorer : {
					axis : 'horizontal',
					actions : [ 'dragToPan', 'rightClickToReset' ]
				}
			};

			var connectLogChart = new google.visualization.LineChart(document
					.getElementById('connect_log_chart'));
			connectLogChart.draw(connectLogData, connectLogOptions);
		} else {
			console.log('데이터 없음');
		}
	}

	function drawPie(data) {
		if (data != null) {
			// URI Count 데이터를 Google 차트 데이터 형식으로 변환
			var uriCountData = new google.visualization.DataTable();
			uriCountData.addColumn('string', '페이지');
			uriCountData.addColumn('number', '접속자 수');

			// 데이터베이스에서 가져온 사용자 데이터를 반복하여 차트 데이터에 추가
			data.uriCount.forEach(function(row) {
				uriCountData.addRow([ row.uriName, row.accessCount ]);
			});

			// 차트 옵션 설정
			var uriCountOptions = {
				title : '페이지별 접속수',
				is3D : true,
				width : 465,
				height : 400
			};

			// 원형 차트 생성
			var uriCountChart = new google.visualization.PieChart(document
					.getElementById('uri_count_chart'));
			uriCountChart.draw(uriCountData, uriCountOptions);
			console.log("차트 생성 성공");
		} else {
			console.log('데이터 없음');
		}
	}

	function drawTable(data) {
		var uriCountData = new google.visualization.DataTable();
		uriCountData.addColumn('string', '페이지');
		uriCountData.addColumn('number', '접속자 수');
		;
		data.uriCount.forEach(function(row) {
			uriCountData.addRow([ row.uriName, row.accessCount ]);
		});

		var table = new google.visualization.Table(document
				.getElementById('table_div'));

		table.draw(uriCountData, {
			showRowNumber : false,
			width : '100%',
			height : '50px'
		});
	}
</script>

</head>
<body>
	<c:import url="./adminHeader.jsp"></c:import>


	<c:import url="./adminSidebar.jsp"></c:import>

	<div class="page-wrapper">
		<c:import url="./adminMiniHeader.jsp"></c:import>
		<div class="container-fluid">

			<main id="dashboard"></main>

			<div class="row">
				<div class="col-lg-7">
					<div class="card">
						<div class="card-body">
							<div class="form-group">
								<div>
									<h4>월별 일일접속자 통계</h4>
								</div>
								<div class="selector">
									<select id="month" class="form-control monthSelector">
										<option value="1">1월</option>
										<option value="2">2월</option>
										<option value="3">3월</option>
										<option value="4">4월</option>
										<option value="5">5월</option>
										<option value="6">6월</option>
										<option value="7">7월</option>
										<option value="8">8월</option>
										<option value="9">9월</option>
										<option value="10">10월</option>
										<option value="11">11월</option>
										<option value="12">12월</option>
									</select>
								</div>
							</div>
							<div id="connect_log_chart"></div>

						</div>
					</div>
				</div>
				<div class="col-lg-5">
					<div class="card">
						<div class="card-body">
							<div>
								<h4>페이지별 접속자 통계</h4>
							</div>
							<div id="uri_count_chart"></div>
							<div id="table_div"></div>
						</div>
						<div>
							<hr class="mt-0 mb-0">
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<!-- column -->
				<div class="col-sm-6">
					<div class="card">
						<div class="card-body tableN">
							<div>
								<h4>공지사항</h4>
							</div>
							<table class="table table-Default table-hover">



								<thead>
									<tr>

										<th>글번호</th>
										<th>작성자</th>
										<th>제목</th>
										<th>작성일</th>

									</tr>
								</thead>
								<tbody>
									<c:forEach var="board" items="${diffNotc }">

										<tr id="table"
											onclick="location.href = '/adminView/noticViewDetail?boardNo=${board.boardNo}';">

											<td>${board.boardNo }</td>
											<td>${board.writer}</td>
											<td>${board.title }</td>
											<td>${board.postDate }</td>

										</tr>


									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>

				<div class="col-lg-6">
					<div class="card">
						<div class="card-body tableQ">

							<div>
								<h4>문의사항</h4>
							</div>

							<table class="table table-Default table-hover">



								<thead>
									<tr>

										<th>글번호</th>
										<th>작성자</th>
										<th>제목</th>
										<th>작성 일자</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="board" items="${diffQna }">

										<tr id="table"
											onclick="location.href = '/qna/viewBoard?no=${board.no}';">

											<td>${board.no }</td>
											<td>${board.qnaWriter }</td>
											<td>${board.qnaTitle }</td>
											<td>${board.postDate}</td>
										</tr>


									</c:forEach>
								</tbody>
							</table>


						</div>
					</div>
				</div>

			</div>







			<div class="col-lg-7 col-xlg-5">
				<div class="card">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs profile-tab" role="tablist">
						<li class="nav-item"><a class="nav-link active"
							data-bs-toggle="tab" href="#home" role="tab">스터디모임</a></li>
						<li class="nav-item"><a class="nav-link" data-bs-toggle="tab"
							href="#profile" role="tab">강의추천</a></li>
						<li class="nav-item"><a class="nav-link" data-bs-toggle="tab"
							href="#settings" role="tab">알고리즘</a></li>
						<li class="nav-item"><a class="nav-link" data-bs-toggle="tab"
							href="#review" role="tab">기업리뷰</a></li>
					</ul>

					<div class="tab-content">
						<div class="tab-pane active" id="home" role="tabpanel">
							<div class="card-body">
								<div class="profiletimeline border-start-0">

									<div>
										<h4>스터디모임</h4>
									</div>
									<table class="table table-Default table-hover">



										<thead>
											<tr>

												
												<th>작성자</th>
												<th>제목</th>
												<th>장소</th>
												<th>기간</th>
												<th>모집상태</th>
												<th>종료일</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="board" items="${diffStu }">

												<tr id="table"
													onclick="location.href = '/study/viewStudyBoard?stuNo=${board.stuNo}';">

													
													<td>${board.stuWriter }</td>
													<td>${board.stuTitle }</td>
													<td>${board.stuLoc }</td>
													<td>${board.stuDate }</td>
													<td>${board.status }</td>
													<td>${board.endDate }</td>
												</tr>


											</c:forEach>
										</tbody>
									</table>


								</div>
							</div>
						</div>
						<!--second tab-->
						<div class="tab-pane" id="profile" role="tabpanel">
							<div class="card-body">
								<div class="profiletimeline border-start-0">


									<div>
										<h4>강의추천</h4>
									</div>

									<table class="table table-Default table-hover">



										<thead>
											<tr>

												
												<th>작성자</th>
												<th>제목</th>
												<th>작성일</th>

											</tr>
										</thead>
										<tbody>
											<c:forEach var="board" items="${diffLec }">
												<tr
													onclick="location.href = '/lecture/viewBoard?lecNo=${board.lecNo}';">

													
													<td>${board.lecWriter }</td>
													<td>${board.lecTitle }</td>
													<td>${board.lecPostDate }</td>

												</tr>


											</c:forEach>
										</tbody>
									</table>
								</div>

							</div>
						</div>
						<div class="tab-pane" id="settings" role="tabpanel">
							<div class="card-body">
								<div class="profiletimeline border-start-0">
								
								<div>
								<h4>알고리즘</h4>
							</div>
							<table class="table table-Default table-hover">



								<thead>
									<tr>

										<th>글번호</th>
										<th>제목</th>
										<th>내용</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="board" items="${diffAlg }">
										<tr
											onclick="location.href = '/algorithm/algDetail?boardNo=${board.boardNo}';">

											<td>${board.boardNo }</td>
											<td>${board.title }</td>
											<td>${board.comment }</td>
										</tr>


									</c:forEach>
								</tbody>
							</table>
								
								
								</div>
							</div>
						</div>
						
						
						
						<div class="tab-pane" id="review" role="tabpanel">
							<div class="card-body">
								<div class="profiletimeline border-start-0">
								
								
								<div>
								<h4>기업리뷰</h4>
							</div>

							<table class="table table-Default table-hover">



								<thead>
									<tr>

										
										<th>작성자</th>
										<th>제목</th>
										<th>부서</th>
										<th>작성일</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="board" items="${diffRev }">
										<tr
											onclick="location.href = '/companyInfo/revCompanyBoard?companyInfoNo=${board.companyInfoNo}';">

											
											<td>${board.revWriter }</td>
											<td>${board.revTitle }</td>
											<td>${board.revProfession }</td>
											<td>${board.revPostDate }</td>
										</tr>


									</c:forEach>
								</tbody>
							</table>
								
								</div>
							</div>
						</div>
						
						
						
						
					</div>



				</div>
			</div>





		</div>
		<c:import url="./adminFooter.jsp"></c:import>
	</div>
</body>
</html>