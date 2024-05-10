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
<script>
	function isValid() {
		let result = false;

		let searchType = $('#searchType').val();
		let searchValue = $('#searchValue').val();

		console.log(searchType, searchValue);

		// 검색어에 있어서는 안되는 쿼리문 키워드를 배열로 정의
		let sqlKeyWord = new Array("OR", "SELECT", "AND", "INSERT", "UPDATE",
				"DELETE", "DROP", "EXEC", "TRUNCATE", "CREATE", "ALTER");

		if (searchType != "-1" && searchValue.length > 0) { // 검색조건과 검색어가 있는지 확인

			// 검색어에 쿼리문 키워드가 존재하는지 확인
			let regEx;
			for (let i = 0; i < sqlKeyWord.length; i++) {
				regEx = new RegExp(sqlKeyWord[i], "gi") // sqlKeyWord 배열에 있는 문자열 패턴이 있는지 없는지 전역적으로 검사하는 객체 생성

				if (regEx.test(searchValue)) { // 유저가 입력한 검색어에 sql키워드가 존재한다
					alert('검색어가 올바르지 않습니다!');
					$('#searchValue').val('');
					$('#searchValue').focus();
					return false;
				}
			}

			result = true;

		} else {
			alert('검색 조건과 검색어를 입력하시고 검색 버튼을 누르세요!');
		}

		return result;
	}
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
	<c:import url="./adminHeader.jsp"></c:import>








	<p class="text-center">스터디게시판</p>
	<ul class="nav justify-content-center box">
		<li class="nav-item"><a class="nav-link"
			href="/admin/selectBoard">스터디게시판</a></li>
		<li class="nav-item"><a class="nav-link"
			href="/admin/lectureBoard">강의추천게시판</a></li>
		<li class="nav-item"><a class="nav-link"
			href="/admin/algorithmBoard">알고리즘게시판</a></li>
		<li class="nav-item"><a class="nav-link"
			href="/admin/reviewBoard">제직자리뷰게시판</a></li>
	</ul>




	<div class="container">
		<h4>스터디게시글 전체 조회 페이지</h4>

		<c:import url="./search.jsp"></c:import>


		<div class="container mt-3">
			<table class="table table-hover">



				<thead>
					<tr>
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
							onclick="location.href = '/adminView/viewDetails?stuNo=${board.stuNo}';">
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
	</div>






	<c:import url="./adminFooter.jsp"></c:import>
</body>
</html>