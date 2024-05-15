<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
.butt {
	margin: 10px;
}

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

.deBtn {
	width: 70px;
	position: relative;
	left: 285px;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>

    // 삭제 버튼을 클릭했을 때 체크된 항목만 삭제
    document.addEventListener("DOMContentLoaded", function() {
    let deleteButton = document.getElementById("deleteButton");

    deleteButton.addEventListener("click", function() {
        let checkboxes = document.querySelectorAll(".deleteCheckbox:checked");

    checkboxes.forEach(checkbox => {
        let item = checkbox.parentElement;
        item.remove();
        });
    }); 
});



	
//	document.addEventListener("DOMContentLoaded", function() {
//	    let modal = document.getElementById("myModal");
	//    let openModalBtn = document.getElementById("openModalBtn");
	//    let closeModalSpan = document.querySelector(".close");

	    // 모달 열기
	//    openModalBtn.addEventListener("click", function() {
	//        modal.style.display = "block";
	//    });

	    // 모달 닫기 (x 버튼 클릭 시)
	//    closeModalSpan.addEventListener("click", function() {
	//        modal.style.display = "none";
	 //   });
	    
	 // 모달 닫기 (모달 외부 클릭 시)
	 //   window.addEventListener("click", function(event) {
	 //       if (event.target == modal) {
	  //          modal.style.display = "none";
	   //     }
	  //  });

	 
	//});
	
	$(function() {

		let pageNo = '${param.pageNo}';
		if (pageNo == '') {
			pageNo = 1;
		}

		$(`#\${pageNo}`).addClass('active')

	})
	
	
	function checkCheckbox() {
	   let url = "delete";
	   let valueArr = new Array();
	   let list= $("input[name='rowCheck']");
	   for (let i = 0; i < list.length; i++) {
		   if(list[i].checked) {
			   valueArr.push(list[i].value);
		   }
	   }
	   if (valueArr.length == 0) {
		   alert("선택된 게시글이 없습니다");
	   }
	   else{
		   let chk = confirm("정말 삭제하시겠습니까?");
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
						location.replace("selectBoard")
					} 
					else {
						alert("삭제 실패");
					}
			}
			
		}); 
	   }
	}
</script>

</head>
<body>

	<c:import url="./adminHeader.jsp"></c:import>





	<div class="page-wrapper">

		<div class="container-fluid">


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






			<c:import url="./search.jsp"></c:import>

			<button id="openModalBtn" onclick="checkCheckbox()">게시글삭제</button>




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
							<td><input type="checkbox" name="rowCheck" class="deleteCheckbox"
								id="myCheckbox"
								value="${board.stuNo }" /></td>
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






		<div id="myModal" class="modal">
			<div class="modal-content">
				<span class="close">&times;</span>
				<p>게시글을 삭제하시겠습니까?</p>
				<button type="button" class="btn btn-secondary deBtn"
					id="deleteButton"
					onclick="location.href='delete?stuNo=${studyBoard.stuNo}';">삭제</button>
			</div>
		</div>






		<c:import url="./adminFooter.jsp"></c:import>
	</div>

</body>
</html>