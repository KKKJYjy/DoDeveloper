<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.7.1/kakao.min.js"
	integrity="sha384-kDljxUXHaJ9xAb2AzRd59KxjrFjzHa5TAoFQ6GbYTCAG0bjM55XohjjDT7tDDC01"
	crossorigin="anonymous"></script>
<script>
	Kakao.init('505dac3eb6f86064a1b842e6ddf54fec'); // 사용하려는 앱의 JavaScript 키 입력
</script>
<title>카카오톡 1대1 문의</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
Kakao.Channel.followChannel({
	  channelPublicId: '${CHANNEL_PUBLIC_ID}',
	})
	  .then(function(response) {
	    console.log(response)
	    // 채널 간편 추가 성공 처리
	  })
	  .catch(function(error) {
	    console.error(error)
	    // 채널 간편 추가 실패 처리
	  })


	Kakao.Channel.createChatButton({
		container : '#chat-channel-button',
		channelPublicId : '_ZeUTxl',
	});

	Kakao.Channel.addChannel({
		channelPublicId : '_ZeUTxl'
	});
	
	
	Kakao.API.request({
		  url: '/v1/api/talk/channels',
		})
		  .then(function(response) {
		    console.log(response);
		  })
		  .catch(function(error) {
		    console.log(error);
		  });

</script>


</head>

<body>
	<c:import url="./adminHeader.jsp"></c:import>



	

	<a id="chat-channel-button" href="javascript:chatChannel()"> <img
		src="/tool/resource/static/img/button/channel/consult/consult_small_yellow_pc.png"
		alt="카카오톡 채널 채팅하기 버튼" />
	</a>


	<c:import url="./adminFooter.jsp"></c:import>















</body>

</html>