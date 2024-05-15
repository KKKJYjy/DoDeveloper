<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style>
	body {
    font-family: Arial, sans-serif;
}

.modal {
    display: none; /* 숨김 상태로 시작 */
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgb(0, 0, 0);
    background-color: rgba(0, 0, 0, 0.4); /* 투명한 검은 배경 */
}

.modal-content {
    background-color: #fefefe;
    margin: 15% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
    max-width: 400px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
    animation: slideIn 0.4s;
}

@keyframes slideIn {
    from { top: -300px; opacity: 0; }
    to { top: 0; opacity: 1; }
}

.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: #000;
    text-decoration: none;
    cursor: pointer;
}

.deBtn {
	width: 70px;
	position: relative;
	left: 285px;
	
}
</style>
<script>
function deleteBoard() {
	$('.modal').show();
	
}



document.addEventListener("DOMContentLoaded", function() {
    let modal = document.getElementById("myModal");
    let openModalBtn = document.getElementById("openModalBtn");
    let closeModalSpan = document.querySelector(".close");

    // 모달 열기
    openModalBtn.addEventListener("click", function() {
        modal.style.display = "block";
    });

    // 모달 닫기 (x 버튼 클릭 시)
    closeModalSpan.addEventListener("click", function() {
        modal.style.display = "none";
    });
    
 // 모달 닫기 (모달 외부 클릭 시)
    window.addEventListener("click", function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    });

 
});

</script>
</head>
<body>


	<c:import url="./adminHeader.jsp"></c:import>

	<div class="page-wrapper">

		<div class="container-fluid">

			<div class="container mt-3">
				<p class="text-center">알고리즘게시판</p>
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
				
				<button id="openModalBtn">게시글삭제</button>

				<div class="container mt-3">
					<table class="table table-light table-hover">



						<thead>
							<tr>
							    <th></th>
								<th>글번호</th>
								<th>제목</th>
								<th>내용</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="board" items="${argBoardList }">
								<tr
									onclick="location.href = '/algorithm/algDetail?boardNo=${board.boardNo}';">
									<td><input type="checkbox" /></td>
									<td>${board.boardNo }</td>
									<td>${board.title }</td>
									<td>${board.comment }</td>
								</tr>


							</c:forEach>
						</tbody>
					</table>
				</div>

				<ul class="pagination">
					<c:if test="${param.pageNo > 1 }">
						<li class="page-item"><a class="page-link"
							href="/admin/algorithmBoard?pageNo=${param.pageNo -1 }">Previous</a></li>

					</c:if>
					<c:forEach var="i"
						begin="${pagingInfo.startNumOfCurrentPagingBlock }"
						end="${pagingInfo.endNumOfCurrentPagingBlock }" step="1">
						<li class="page-item" id="${i }"><a class="page-link"
							href="/admin/algorithmBoard?pageNo=${i }">${i }</a></li>
					</c:forEach>

					<c:if test="${param.pageNo < pagingInfo.totalPageCnt }">
						<li class="page-item"><a class="page-link"
							href="/admin/algorithmBoard?pageNo=${param.pageNo +1 }">Next</a></li>
					</c:if>
				</ul>
			</div>
		</div>

		<div id="myModal" class="modal">
			<div class="modal-content">
				<span class="close">&times;</span>
				<p>게시글을 삭제하시겠습니까?</p>
				<button type="button" class="btn btn-secondary deBtn">삭제</button>
			</div>
		</div>


		<c:import url="./adminFooter.jsp"></c:import>
	</div>

</body>
</html>