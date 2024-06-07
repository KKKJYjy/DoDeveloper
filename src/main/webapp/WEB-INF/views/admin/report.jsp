<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
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

	let selectedReportIds = [];

	function checkCheckbox() {
		selectedReportIds = [];
		let list = $("input[name='rowCheck']");
		for (let i = 0; i < list.length; i++) {
			if (list[i].checked) {
				selectedReportIds.push(list[i].value);
			}
		}
		if (selectedReportIds.length == 0) {
			alert("선택된 게시글이 없습니다");
		} else {

		}
	}
	
	function deleteBoard(btypeNo, boardNo) {
		let url = "reportDelete";
		let deleteReason = $('#reportInput').val();
		if (deleteReason.trim() === "") {
			
			$('#myModal').show();
			return;
		}

	    $.ajax({
	    	url : url,
			type : "post",
	        data: {
	            btypeNo: btypeNo,
	            boardNo: boardNo,
	            deleteReason: deleteReason
	        },
	        success : function(data) {
				if (data == 1) {

					alert("삭제 성공");
					location.replace("report")
				} else {
					alert("삭제 실패");
				}
			},
	       
	    });
	}


	

	//	function modal() {
	//let modalText = document.getElementById("modalText");
	//	$('#myModal').show();
	//let valueToPass = `${report.reportNo }`; // 전달할 변수 값
	//modalText.textContent = valueToPass; // 변수 값을 모달 텍스트로 설정

	//	}

	/* function modal() {

		window.location.href = '/admin/viewReport';

	}
	 */
	let modalText = document.getElementById("modalText");

	document.addEventListener("DOMContentLoaded", function() {
		let modal = document.getElementById("myModal");
		let closeModalSpan = document.querySelector(".close");

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
	max-width: 600px;
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
	animation: slideIn 0.4s;
}

@
keyframes slideIn {from { top:-300px;
	opacity: 0;
}

to {
	top: 0;
	opacity: 1;
}

}
.close {
	color: #aaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}

#deleteButton {
	position: relative;
	width: 70px;
	margin: 10px;
}

#reportInput {
	height: 150px;
}

#openModalBtn {
	margin-bottom: 15px;
	background-color: #cf4d3d;
	color: white;
	border-radius: 25px;
}

#deleteReportBtn {
	margin-bottom: 15px;
	color: white;
	float: right;
}

h2 {
	text-align: center;
}

.viewBoard {
	border-radius: 25px;
}
</style>

<body>
	<c:import url="./adminHeader.jsp"></c:import>


	<c:import url="./adminSidebar.jsp"></c:import>

	<div class="page-wrapper">

		<c:import url="./adminMiniHeader.jsp"></c:import>



		<div class="container-fluid">



			<div class="row">
				<div class="col-sm-12">
					<div class="card">
						<div class="card-body">
							<h2>신고 내역 조회</h2>
							<c:choose>
								<c:when test="${sessionScope.loginMember.isAdmin == 'Y' }">
									<button type="button" class="btn btn-secondary"
										id="deleteReportBtn" onclick="checkCheckbox()">신고내역삭제</button>
								</c:when>
								<c:otherwise>
									<button type="button" class="btn btn-secondary"
										id="deleteReportBtn" disabled>신고내역삭제</button>
								</c:otherwise>
							</c:choose>

							<table class="table table-light table-hover">

								<thead>
									<tr>
										<th><input id="allCheck" type="checkbox" name="allCheck" /></th>
										<th>신고 일자</th>
										<th>게시판 구분</th>
										<th>신고 사유</th>
										<th>작성자</th>
										<th>신고자</th>
										<th>글 보기</th>
										<th>삭제</th>
									</tr>
								</thead>
								<tbody>

									<c:forEach var="board" items="${reportList }">

										<tr>
											<td onclick="event.cancelBubble=true"><input
												type="checkbox" name="rowCheck" class="deleteCheckbox"
												id="myCheckbox" value="${board.reportNo }" /></td>
											<td><fmt:formatDate value="${board.reportDate}"
													pattern="yyyy-MM-dd" /></td>
											<td>${board.category }</td>
											<td>${board.reportReason }</td>
											<td>${board.writer }</td>
											<td>${board.reporter }</td>
											<td>
												<form id="moveToDetailViewForm" action="/viewBoardDetail"
													method="GET">
													<input type="hidden" name="btypeNo"
														value="${board.btypeNo}" /> <input type="hidden"
														name="boardNo" value="${board.boardNo}" />
													<button type="submit"
														class="btn btn-light btn-sm viewBoard">글 이동</button>
												</form>
											</td>
											<td><c:choose>
													<c:when test="${sessionScope.loginMember.isAdmin == 'Y' }">
														<button type="button" class="btn btn-danger btn-sm"
															id="openModalBtn" onclick="deleteReport()"
															value="${board.reportNo }">삭제</button>
													</c:when>
													<c:otherwise>
														<button type="button" class="btn btn-danger btn-sm"
															id="openModalBtn" disabled>삭제</button>
													</c:otherwise>
												</c:choose></td>
										</tr>
									</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>



			<div id="myModal" class="modal">
				<div class="modal-content">
					<span class="close">&times;</span>
					<p id="modalText"></p>
					<textarea id="reportInput" placeholder="삭제 사유를 입력하세요."></textarea>
					<button type="button" class="btn btn-secondary deBtn"
						id="deleteButton">삭제</button>
				</div>
			</div>

		</div>

		<c:import url="./adminFooter.jsp"></c:import>
	</div>
</body>
</html>