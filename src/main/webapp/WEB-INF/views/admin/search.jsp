<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</head>
<body>
	<form class="searchBar" style="margin: 15px; text-align: center;">
			<select id="searchType" name="searchType">
				<option value="-1">--- 검색 조건을 선택하세요 ---</option>
				<option value="title">제목</option>
				<option value="writer">작성자</option>
			</select> <input type="text" id="searchValue" name="searchValue"
				placeholder="검색어를 입력하세요">
			<button type="submit" onclick="return isValid();">검색</button>
		</form>
</body>
</html>