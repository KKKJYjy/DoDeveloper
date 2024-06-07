<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>1대1 문의</title>
<style>
#openModalBtn {
	margin-bottom: 15px;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
	$(function() {
		let chkObj = document.getElementsByName("rowCheck");
		let rowCnt = chkObj.length;

		$("input[name='allCheck']").click(function() {
			let chk_listArr = $("input[name='rowCheck']");
			for (let i = 0; i < chk_listArr.length; i++) {
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
		let url = "qnaDelete";
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
				location.replace("inquiry")
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
							location.replace("inquiry")
						} else {
							alert("삭제 실패");
						}
					}

				});
			}

		}
	}
	
	
	$(function() {

		let pageNo = '${param.pageNo}';
		if (pageNo == '') {
			pageNo = 1;
		}

		$(`#\${pageNo}`).addClass('active')

	})
	
	
</script>


</head>

<body>
	<c:import url="./adminHeader.jsp"></c:import>

	<c:import url="./adminSidebar.jsp"></c:import>

	<div class="page-wrapper">

		<c:import url="./adminMiniHeader.jsp"></c:import>

		<div class="container-fluid">

			<div class="container">

				<c:if test="${sessionScope.loginMember.isAdmin == 'Y' }">
					<button id="openModalBtn" onclick="checkCheckbox()">게시글삭제</button>
				</c:if>

				<div class="row">
					<!-- column -->
					<div class="col-sm-12">
						<div class="card">
							<div class="card-body">

								<table class="table table-Default table-hover">



									<thead>
										<tr>
											<th><input id="allCheck" type="checkbox" name="allCheck" /></th>
											<th>글번호</th>
											<th>작성자</th>
											<th>제목</th>
											<th>작성 일자</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="board" items="${qnaList }">

											<tr id="table"
												onclick="location.href = '/qna/viewBoard?no=${board.no}';">
												<td onclick="event.cancelBubble=true"><input
													type="checkbox" name="rowCheck" class="deleteCheckbox"
													id="myCheckbox" value="${board.no }" /></td>
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


				<ul class="pagination">
					<c:if test="${param.pageNo > 1 }">
						<li class="page-item"><a class="page-link"
							href="/admin/inquiry?pageNo=${param.pageNo -1 }&searchType=${param.searchType}&searchValue=${param.searchValue}">Previous</a></li>

					</c:if>
					<c:forEach var="i"
						begin="${pagingInfo.startNumOfCurrentPagingBlock }"
						end="${pagingInfo.endNumOfCurrentPagingBlock }" step="1">
						<li class="page-item" id="${i }"><a class="page-link"
							href="/admin/inquiry?pageNo=${i }&searchType=${param.searchType}&searchValue=${param.searchValue}">${i }</a></li>
					</c:forEach>

					<c:if test="${param.pageNo < pagingInfo.totalPageCnt }">
						<li class="page-item"><a class="page-link"
							href="/admin/inquiry?pageNo=${param.pageNo +1 }&searchType=${param.searchType}&searchValue=${param.searchValue}">Next</a></li>
					</c:if>
				</ul>


			</div>

		</div>


		<c:import url="./adminFooter.jsp"></c:import>

	</div>



</body>

</html>