// 함수명 : getParameter
// 매개변수(paramName) : 값을 찾고자 하는 매개변수의 이름
// 반환값 : 매개변수의 값을 찾았다면 해당 매개변수의 값, 못 찾았다면 false반환

function getParameter(paramName) {
  // 쿼리스트링에서 넘겨받은 paramName의 값을 찾아 그 파라메터의 값을 return한다
  // 만약, 쿼리스트링에 넘겨받은 paramName의 값이 없다면 false를 return한다
  
  let returnVal = false; // return할 변수
  let queryString = location.search; // 웹브라우저의 url주소창의 쿼리스트링값
  let queryStringAr = queryString.split("&");
  for (let queryStr of queryStringAr) {
    if (queryStr.indexOf(paramName) != -1) {
      // 원하는 paramName을 찾았다
      returnVal = queryStr.split("=")[1];
      break;
    }
  }
  return returnVal;
}