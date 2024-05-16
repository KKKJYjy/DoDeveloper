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
}
</style>
<script>
	$(function() {
		var chkObj = document.getElementsByName("rowCheck");
		var rowCnt = chkObj.length;

		$("input[name='allCheck']").click(function() {
			var chk_listArr = $("input[name='rowCheck']");
			for (var i = 0; i < chk_listArr.length; i++) {
				chk_listArr[i].checked = this.checked;
			}
		});
		$("input[name='rowCheck']").click(function() {
			if ($("input[name='rowCheck']:checked").length == rowCnt) {
				$("input[name='allCheck']")[0].checked = true;
			} else {
				$("input[name='allCheck']")[0].checked = false;
			}
		});
	});

	function checkCheckbox() {
		let url = "algDelete";
		let valueArr = new Array();
		let list = $("input[name='rowCheck']");
		for (let i = 0; i < list.length; i++) {
			if (list[i].checked) {
				valueArr.push(list[i].value);
			}
		}
		if (valueArr.length == 0) {
			alert("선택된 게시글이 없습니다");
		} else {
			let chk = confirm("정말 삭제하시겠습니까?");
			if (!chk) {
				location.replace("algorithmBoard")
			} else {
				$.ajax({
					url : url,
					type : "post",
					traditional : true,
					data : {
						valueArr : valueArr
					},
					success : function(data) {
						if (data = 1) {
							alert("삭제 성공");
							location.replace("algorithmBoard")
						} else {
							alert("삭제 실패");
						}
					}

				});
			}
		
		}
	}
</script>
</head>
<body>


	<c:import url="./adminHeader.jsp"></c:import>

	<c:import url="./adminSidebar.jsp"></c:import>

	<div class="page-wrapper">

		<c:import url="./adminMiniHeader.jsp"></c:import>

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

				<button id="openModalBtn" onclick="checkCheckbox()">게시글삭제</button>

				<div class="container mt-3">
					<table class="table table-light table-hover">



						<thead>
							<tr>
								<th><input id="allCheck" type="checkbox" name="allCheck" /></th>
								<th>글번호</th>
								<th>제목</th>
								<th>내용</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="board" items="${argBoardList }">
								<tr
									onclick="location.href = '/algorithm/algDetail?boardNo=${board.boardNo}';">
									<td><input type="checkbox" name="rowCheck"
										class="deleteCheckbox" id="myCheckbox"
										value="${board.boardNo }" /></td>
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