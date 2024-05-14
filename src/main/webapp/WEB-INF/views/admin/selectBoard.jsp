<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
	.butt {
	margin: 10px;
	}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>

	function deleteBoard() {
		$('.modal').show();
		
	}
	
	function btnClose() {
		$('.btnm').hide();
		
	}
	
	$(function() {

		let pageNo = '${param.pageNo}';
		if (pageNo == '') {
			pageNo = 1;
		}

		$(`#\${pageNo}`).addClass('active')

	})
</script>
<script>
	//이미지를 생성합니다.
	let img = document.createElement("img");
	img.src = "/resources/admin/images/delete.png"; // 이미지 파일 경로를 지정합니다.

	// 새로운 <td> 요소를 생성하고 이미지를 그 안에 추가합니다.
	let td = document.createElement("td");
	td.appendChild(img);

	// 새로운 <tr> 요소를 선택하고 새로운 <td> 요소를 추가합니다.
	let tr = document.getElementById("table");
	tr.appendChild(td);
</script>
</head>
<body>
<div class="page-wrapper">
	<c:import url="./adminHeader.jsp"></c:import>







	<div class="container mt-3">
		<p class="text-center">스터디게시판</p>
		<ul class="nav nav-tabs nav-justified">
			<li class="nav-item"><a class="nav-link"
				href="/admin/selectBoard">스터디게시판</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/admin/lectureBoard">강의추천게시판</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/admin/algorithmBoard">알고리즘게시판</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/admin/reviewBoard">제직자리뷰게시판</a></li>
		</ul>
	</div>




	<div class="container">


		<c:import url="./search.jsp"></c:import>
		
		<button type="button" class="btn btn-secondary butt" onclick="return deleteBoard();">게시글삭제</button>


		<div class="container mt-3">
			<table class="table table-light table-hover">



				<thead>
					<tr>
						<th></th>
						<th>글번호</th>
						<th>작성자</th>
						<th>제목</th>
						<th>장소</th>
						<th>기간</th>
						<th>모집상태</th>
						<th>종료일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="board" items="${stuBoardList }">
						<tr id="table"
							onclick="location.href = '/study/viewStudyBoard?stuNo=${board.stuNo}';">
							<td><input type="checkbox" /></td>
							<td>${board.stuNo }</td>
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


		<ul class="pagination">
			<c:if test="${param.pageNo > 1 }">
				<li class="page-item"><a class="page-link"
					href="/admin/selectBoard?pageNo=${param.pageNo -1 }">Previous</a></li>

			</c:if>
			<c:forEach var="i"
				begin="${pagingInfo.startNumOfCurrentPagingBlock }"
				end="${pagingInfo.endNumOfCurrentPagingBlock }" step="1">
				<li class="page-item" id="${i }"><a class="page-link"
					href="/admin/selectBoard?pageNo=${i }">${i }</a></li>
			</c:forEach>

			<c:if test="${param.pageNo < pagingInfo.totalPageCnt }">
				<li class="page-item"><a class="page-link"
					href="/admin/selectBoard?pageNo=${param.pageNo +1 }">Next</a></li>
			</c:if>
		</ul>




		<!-- The Modal -->
		<div class="modal btnm" id="myModal">
		  <div class="modal-dialog btnm">
		    <div class="modal-content btnm">

		      <!-- Modal Header -->
		      <div class="modal-header">
		        <h4 class="modal-title"></h4>
		        <button type="button" class="btn-close btnm" data-bs-dismiss="modal" onclick="return btnClose();"></button>
		      </div>

		      <!-- Modal body -->
		      <div class="modal-body btnm">
		        게시글을 삭제하시겠습니까?
		      </div>

		      <!-- Modal footer -->
		      <div class="modal-footer">
		        <button type="button" class="btn btn-danger btnm" data-bs-dismiss="modal" onclick="return btnClose();">Close</button>
		      </div>

		    </div>
		  </div>
		</div>
	</div>






	<c:import url="./adminFooter.jsp"></c:import>
	</div>
</body>
</html>