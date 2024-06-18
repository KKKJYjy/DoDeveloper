<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<title>My Page - DoDeveloper</title>
<meta content="" name="description" />
<meta content="" name="keywords" />

<!-- Favicons -->
<link href="/resources/assets/img/favicon.png" rel="icon" />
<link href="/resources/assets/img/apple-touch-icon.png"
	rel="apple-touch-icon" />

<!-- Fonts -->
<link href="https://fonts.googleapis.com" rel="preconnect" />
<link href="https://fonts.gstatic.com" rel="preconnect" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;1,300;1,400;1,500;1,600;1,700;1,800&family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap"
	rel="stylesheet" />

<!-- Vendor CSS Files -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link href="/resources/assets/vendor/glightbox/css/glightbox.min.css"
	rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"
	rel="stylesheet" />
<link href="/resources/assets/vendor/aos/aos.css" rel="stylesheet" />

<!-- Template Main CSS File -->
<link href="/resources/assets/css/main.css" rel="stylesheet" />
<link href="/resources/assets/css/mypage/myProfile.css" rel="stylesheet" />

<!-- =======================================================
  * Template Name: Append
  * Template URL: https://bootstrapmade.com/append-bootstrap-website-template/
  * Updated: Mar 17 2024 with Bootstrap v5.3.3
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="/resources/assets/js/emailVerification/emailVerification.js"></script>
<script>
  $(function () {
  var staticBackdrop = document.getElementById("staticBackdrop");
  staticBackdrop.addEventListener("show.bs.modal", function (event) {
    console.log("staticBackdrop open");

    $("#currentUserPwd").val("");
    $("#newUserPwd").val("");
    $("#newUserPwdConfirm").val("");
  });
  
  var staticBackdrop = document.getElementById("sb_dropMember");
  staticBackdrop.addEventListener("show.bs.modal", function (event) {
    console.log("sb_dropMember open");

    $("#etcReason").val("");
    $('input[name="dropReason"]:checked').prop("checked", false);
  });
  
  var staticBackdrop = document.getElementById("sb-change-email");
  staticBackdrop.addEventListener("show.bs.modal", function (event) {
    console.log("sb-change-email open");

	$("#new-email").val("");
	$("#email-code").val("");
	blockEmailCodeInput(true);
	blockSubmitEmailBtn(true);
  });

  $("#fileUploader").change(handleImgInput);

  $("#newUserPwd, #newUserPwdConfirm").on("keyup", function (e) {
    checkValidPassword();
  });

  getProfileImage();
  
  getAllScrap();
  
  $("#new-email").on("keyup", function(e){
	  isValidEmail = false;
	  blockEmailCodeInput(true);
	  blockSubmitEmailBtn(true);
  });
  
  $("#request-verify-email-btn").on("click", function(e){
	  inputNewEmail();
  });
  
  $("#check-email-code-btn").on("click", function(e){
	  checkEmailCode();
  });
  
});

function checkValidPassword() {
  // 비밀번호 : 4자 이상 12자 이하 필수 (비밀 번호 확인과 동일해야 함)

  let result = false;

  let pwd1 = $("#newUserPwd").val();
  let pwd2 = $("#newUserPwdConfirm").val();

  if (pwd1 == "" || pwd1.length < 1) {
    $("#errorMsgNewUserPwd").html("비밀번호는 필수 항목 입니다");
    $("#errorMsgNewUserPwdConfirm").html("");
    $("#errorMsgNewUserPwd").removeClass("text-danger");
    $("#errorMsgNewUserPwd").removeClass("text-success");
    $("#errorMsgNewUserPwd").addClass("text-primary");
  } else if (pwd1.length < 4 || pwd1.length > 13) {
    $("#errorMsgNewUserPwd").html("비밀번호는 4~12 글자로 입력하세요");
    if (pwd2 != "" && pwd1 != pwd2) {
      $("#errorMsgNewUserPwdConfirm").html("비밀번호가 서로 맞지 않습니다!");
      $("#errorMsgNewUserPwdConfirm").removeClass("text-primary");
      $("#errorMsgNewUserPwdConfirm").removeClass("text-success");
      $("#errorMsgNewUserPwdConfirm").addClass("text-danger");
      result = false;
    }
  } else if (pwd1.length >= 4 && pwd1.length <= 13) {
    if (pwd2 != "" && pwd1 != pwd2) {
      $("#errorMsgNewUserPwd").html("비밀번호가 서로 맞지 않습니다!");
      $("#errorMsgNewUserPwdConfirm").html("비밀번호가 서로 맞지 않습니다!");
      $("#errorMsgNewUserPwd, #errorMsgNewUserPwdConfirm").removeClass(
        "text-primary"
      );
      $("#errorMsgNewUserPwd, #errorMsgNewUserPwdConfirm").removeClass(
        "text-success"
      );
      $("#errorMsgNewUserPwd, #errorMsgNewUserPwdConfirm").addClass("text-danger");

      result = false;
    } else if (pwd1 == pwd2) {
      $("#errorMsgNewUserPwd").html("비밀번호가 확인되었습니다.");
      $("#errorMsgNewUserPwdConfirm").html("비밀번호가 확인되었습니다.");
      $("#errorMsgNewUserPwd, #errorMsgNewUserPwdConfirm").removeClass(
        "text-primary"
      );
      $("#errorMsgNewUserPwd, #errorMsgNewUserPwdConfirm").removeClass("text-danger");
      $("#errorMsgNewUserPwd, #errorMsgNewUserPwdConfirm").addClass("text-success");
      result = true;
    } else {
      if (pwd2 == "" && document.activeElement.id != "newUserPwd") {
        $("#errorMsgNewUserPwd").html("");
        $("#errorMsgNewUserPwdConfirm").html("비밀번호를 확인해주세요.");

        $("#errorMsgNewUserPwdConfirm").removeClass("text-danger");
        $("#errorMsgNewUserPwdConfirm").removeClass("text-success");
        $("#errorMsgNewUserPwdConfirm").addClass("text-primary");
      } else {
        $("#errorMsgNewUserPwd").html("");
        $("#errorMsgNewUserPwdConfirm").html("");
      }
      result = false;
    }
  }
  console.log("result", result);
  return result;
}

const resizeImage = (settings) => {
  const file = settings.file;
  const maxSize = settings.maxSize;
  const reader = new FileReader();
  const image = new Image();
  const canvas = document.createElement("canvas");

  const dataURItoBlob = (dataURI) => {
    const bytes =
      dataURI.split(",")[0].indexOf("base64") >= 0
        ? atob(dataURI.split(",")[1])
        : unescape(dataURI.split(",")[1]);
    const mime = dataURI.split(",")[0].split(":")[1].split(";")[0];
    //console.log("mime", mime);
    const max = bytes.length;
    const ia = new Uint8Array(max);
    for (let i = 0; i < max; i++) ia[i] = bytes.charCodeAt(i);
    return new Blob([ia], { type: mime });
  };

  const resize = () => {
    let width = image.width;
    let height = image.height;
    if (width > height) {
      if (width > maxSize) {
        height *= maxSize / width;
        width = maxSize;
      }
    } else {
      if (height > maxSize) {
        width *= maxSize / height;
        height = maxSize;
      }
    }
    canvas.width = width;
    canvas.height = height;
    canvas.getContext("2d").drawImage(image, 0, 0, width, height);
    // console.log("file.type", file.type);
    const dataUrl = canvas.toDataURL(file.type);

    // 서버 저장
    // console.log("dataUrl", dataUrl.length);

    let prefix = dataUrl.split(",").shift();
    let base64 = dataUrl.substring(prefix.length + 1);
    // console.log("prefix", prefix);
    // console.log("base64", base64);
    setProfileImage(prefix, base64);

    return dataURItoBlob(dataUrl);
  };

  return new Promise((ok, no) => {
    if (!file) {
      return;
    }
    if (!file.type.match(/image.*/)) {
      no(new Error("Not an image"));
      return;
    }
    reader.onload = (readerEvent) => {
      image.onload = () => {
        return ok(resize());
      };
      image.src = readerEvent.target.result;
      // console.log("original base64", readerEvent.target.result);
    };
    reader.readAsDataURL(file);
  });
};

const handleImgInput = (e) => {
  const config = {
    file: e.target.files[0],
    maxSize: 350,
  };
  resizeImage(config)
    .then((resizedImage) => {
      // console.log("resizedImage", resizedImage);
      const url = window.URL.createObjectURL(resizedImage);
      //console.log("url", url);
      $("#profileImage").attr("src", url);

      /* blobToBase64(resizedImage).then(function(result) {
          console.log("resize blob to base64", result.length);
      }); */
    })
    .catch((err) => {
      console.log(err);
    });
};

function registerProfileImage() {
  $("#fileUploader").click();
}

function removeProfileImage() {
  let userId = "${loginMember.userId}";
  let data = {
    userId: userId,
  };

  $.ajax({
    url: "/mypage/removeProfileImage",
    type: "post",
    contentType: "application/json;charset=utf-8;",
    data: JSON.stringify(data),
    dataType: "json",
    success: function (data) {
      console.log(data);
      if (data.message == "Success") {
        $("#uploadBtn").show();
        $("#changeNDeleteBtns").removeClass("d-flex").addClass("d-none");
        $("#profileImage").attr(
          "src",
          "http://bootdey.com/img/Content/avatar/avatar1.png"
        );
      }
    },
  });
}

function setProfileImage(prefix, base64Encode) {
  //console.log("setProfileImage");

  let userId = "${loginMember.userId}";
  let data = {
    userId: userId,
    prefix: prefix,
    base64Encode: base64Encode,
  };

  $.ajax({
    url: "/mypage/setProfileImage",
    type: "post",
    contentType: "application/json;charset=utf-8;",
    data: JSON.stringify(data),
    dataType: "json",
    success: function (data) {
      // console.log(data);

      if (data.message == "Success") {
        $("#uploadBtn").hide();
        $("#changeNDeleteBtns").removeClass("d-none").addClass("d-flex");
      }
    },
  });
}

function getProfileImage() {
  // console.log("getProfileImage");

  let userId = "${loginMember.userId}";
  let data = {
    userId: userId,
  };

  $.ajax({
    url: "/mypage/getProfileImage",
    type: "post",
    contentType: "application/json;charset=utf-8;",
    data: JSON.stringify(data),
    dataType: "json",
    success: function (data) {
      //console.log("getProfileImage", data);

      if (data.message == "Success") {
        console.log("업로드 버튼 구성 변경 [사진 업로드 -> 변경/삭제]");
        // 업로드 버튼 구성 변경 [사진 업로드 -> 변경/삭제]
        $("#uploadBtn").hide();
        $("#changeNDeleteBtns").removeClass("d-none").addClass("d-flex");

        let base64Image = data.prefix + "," + data.base64Encode;
        //console.log("base64Image", base64Image);
        $("#profileImage").attr("src", base64Image);

        /* const max = data.base64Encode.length;
                const ia = new Uint8Array(max);
                for (let i = 0; i < max; i++) ia[i] = bytes.charCodeAt(i);
                const url = window.URL.createObjectURL(new Blob([ia], { type: mime }));
                //console.log("url", url);
                $("#profileImage").attr("src", url); */
      } else {
        console.log("업로드 버튼 show / 변경,삭제 버튼 hide");
        //
        $("#profileImage").attr(
          "src",
          "http://bootdey.com/img/Content/avatar/avatar1.png"
        );
        $("#uploadBtn").show();
        $("#changeNDeleteBtns").removeClass("d-flex").addClass("d-none");
      }
    },
  });
}

function changePwd() {
	let result = checkValidPassword();
	if (result) {
		let userId = "${loginMember.userId}";
		let data = {
		  userId : userId,
		  currentUserPwd : $("#currentUserPwd").val(),
		  newUserPwd : $("#newUserPwd").val()
		};
		  
		$.ajax({
		    url: "/mypage/changePwd",
		    type: "post",
		    contentType: "application/json;charset=utf-8;",
		    data: JSON.stringify(data),
		    dataType: "json",
		    success: function (data) {
		      if (data.message == "Success") {
		        console.log("비밀번호 변경 성공");
		        $("#staticBackdrop").hide();
		        setTimeout(function() { 
		        	alert('비밀번호가 변경되어, 로그아웃되었습니다.')
			        location.href = "/member/logout";
		        }, 200);
		      } else {
		        console.log("비밀번호 변경 실패");
		      }
		    },
		});
	}
}


//------------------- 이메일 변경 시작 -----------------------
let emailTimer;
let isValidEmail = false;

function inputNewEmail(){
	let newEmailAddress = $("#new-email").val();
	if(!checkEmailFormat(newEmailAddress)){
	 $("#email-error-msg").html("이메일을 다시 한 번 확인해주세요.");
	 $("#email-error-msg").css("color", "red");
	}
	sendRequestToVerifyEmail(newEmailAddress);
	
	blockEmailCodeInput(false);
	startEmailTimer(1000 * 60 * 5);
}

function checkEmailCode(){
	let code = $("#email-code").val();
	
	if(!sendEmailVerificationCode(code)){
		 $("#email-error-msg").html("인증이 실패했습니다.");
		 $("#email-error-msg").css("color", "red");
	}else{
		 $("#email-error-msg").html("인증 성공!");
		 $("#email-error-msg").css("color", "green");

		 blockEmailCodeInput(true);
		 blockSubmitEmailBtn(false);
		 endEmailTimer();
		 
		 isValidEmail = true;
	}
}

function blockEmailCodeInput(wannaBlock){
	$("#email-code").prop("disabled", wannaBlock);
	$("#check-email-code-btn").prop("disabled", wannaBlock);
}

function blockSubmitEmailBtn(wannaBlock){
	$("#submit-email-btn").prop("disabled", wannaBlock);
}

function startEmailTimer(timeLimit){
	  let now = new Date();
	  emailCodeExpireDate = new Date( now.getTime() + timeLimit );
	  
	  emailTimer = setInterval(() => {
	         showRemainingTime(emailCodeExpireDate);
	}, 100);
}

function endEmailTimer(){
	  clearInterval(emailTimer);
	  $("#email-timelimit").html("");
}

function showRemainingTime(expireDate){
	  const now = new Date();
	  const remainingTime = expireDate.getTime() - now.getTime();
	  
	  let output = "";
	  
	  if(remainingTime <= 0){
		  output = "0:00";
  	  	  $("#email-timelimit").html(output);
  	      blockEmailCodeInput(true);
     	  return;
	  }
  
	  let remainingMinute = Math.trunc(remainingTime / (1000 * 60));
	  let remainingSecond = Math.trunc(remainingTime % (1000 * 60) / 1000);

	  output += remainingMinute + ":";
	  
	  if(remainingSecond < 10){
		  output += "0" + remainingSecond;
	  }else{
		  output += remainingSecond + "";
	  }
  
	  $("#email-timelimit").html(output);
}

function changeEmail() {
	if(!isValidEmail){
		return;
	}
	
	let emailInfo = new Object();
	emailInfo.userId = "${sessionScope.loginMember.userId}";
	emailInfo.email = $("#new-email").val();
		
	$.ajax({
	    url: "/mypage/email",
	    type: "put",
	    contentType: "application/json;charset=utf-8;",
	    data: JSON.stringify(emailInfo),
	    dataType: "json",
	    success: function (data) {
	      if (data.isSuccess == "true") {
	    	alert("변경 성공");
	    	location.href = "/mypage/myProfile";
	      }else{
	    	alert(data.reason);
	    	$('#sb-change-email').modal('toggle');
	      }
	    },
	});
	
}
//------------------- 이메일 변경 끝 -----------------------

function modifyProfile() {
	$("#modifyProfileBtn").hide();
	$("#updateProfileBtn").show();
	
//	$('#email').attr("readonly", false);
	
	/* $("#careerRadio1").removeAttr("onclick");
	$("#careerRadio2").removeAttr("onclick"); */
	
	$("input[name=careerRadio]").removeAttr("onclick");
}

function updateProfile() {
	$("#modifyProfileBtn").show();
	$("#updateProfileBtn").hide();
	
//	$('#email').attr("readonly", true);
	
	/* $("#careerRadio1").attr("onclick", 'return false');
	$("#careerRadio2").attr("onclick", 'return false'); */
	
	$("input[name=careerRadio]").attr("onclick", 'return false');
	
	console.log($("input[name=careerRadio]:checked").val());
	
	let userId = "${loginMember.userId}";
	let data = {
	  userId : userId,
	  email : $("#email").val(),
	  careers : $("input[name=careerRadio]:checked").val()
	};
	
	$.ajax({
	    url: "/mypage/changeProfile",
	    type: "post",
	    contentType: "application/json;charset=utf-8;",
	    data: JSON.stringify(data),
	    dataType: "json",
	    success: function (data) {
	      if (data.message == "Success") {
	        console.log("개인정보 변경 성공");
	        $("#staticBackdrop").hide();
	        setTimeout(function() { 
	        	alert('개인정보가 변경되었습니다.')
	        }, 200);
	      } else {
	        console.log("개인정보 변경 실패");
	      }
	    },
	});
}

function dropMember() {
	var dropReason = "";
	var radioId = $('input[name="dropReason"]:checked').attr("id");
	if (radioId == "dropReason_7") {
		dropReason = $("#etcReason").val();
	} else {
		dropReason = $('label[for="'+radioId+'"]').text();
	}
	console.log("dropReason", dropReason);
	
	let userId = "${loginMember.userId}";
	let data = {
	  userId : userId,
	  dropReason : dropReason,
	  status : "탈퇴회원"
	};
		
	$.ajax({
	    url: "/member/dropMember",
	    type: "post",
	    contentType: "application/json;charset=utf-8;",
	    data: JSON.stringify(data),
	    dataType: "json",
	    success: function (data) {
	      if (data.message == "Success") {
	        console.log("회원탈퇴 성공");
	        $("#sb_dropMember").hide();
	        setTimeout(function() { 
	        	alert('회원 탈퇴가 완료되었습니다.')
	        }, 200);
	        
	        location.href = "/member/logout";
	      } else {
	        console.log("회원탈퇴 실패");
	      }
	    },
	});
}

	//----------------------------------------- 스크랩 작업 ----------------------------------------// 
	
	function getAllScrap() {
		let scrapId = '${loginMember.userId}';
		
		$.ajax({
		    url: "/scrap/all/" + scrapId,
		    type: "get",
		    dataType: "json",
		    async: 'false',
		    success: function(data) {
		    	console.log(data);
		    	
		    	outputAllScrap(data);
		    },
		});
	}
	
	function outputAllScrap(data) { // 기업리뷰글 스크랩(join)
		let output = `<div class="list-group">`;
		$.each(data, function(i, ScrapRevJoinVO) {
			output += `<a href="#" class="list-group-item list-group-item-action">`;
			
			output += `<div><img src="\${ScrapRevJoinVO.companyInfoImgLogo}" /></div>`;
			output += `<div>\${ScrapRevJoinVO.companyInfoName}</div>`;
			output += `<div>\${ScrapRevJoinVO.revTitle}</div>`;
			
			// 스크랩 한 날짜 시간 처리 
			let diff = processPostDate(ScrapRevJoinVO.scrapDate);
			output += `<div>\${diff}</div>`;
			
			output += `</a>`;
		});
		
		$('.scrapList').html(output);
		
	}
	
	function processPostDate(scrapRevDate) { // 스크랩한 날짜 시간 처리
		let postDate = new Date(scrapRevDate);
		let now = new Date();
		
		let diff = (now - postDate) / 1000; 
		
		let times = [
			{name: "일", time: 60 * 60 * 24},
			{name: "시간", time: 60 * 60},
			{name: "분", time: 60}
		];
		
		for (let val of times) {
			
			let betweenTime = Math.floor(diff / val.time);
			console.log(diff, betweenTime);
			
			if(betweenTime > 0) {
				return betweenTime + val.name + "전";
			}
		}
		
		return "방금전";
	}
	
</script>
</head>

<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="../header.jsp"%>

	<main id="main">
		<!-- Basic Section - My Page -->
		<section id="myPage" class="contact">
			<!-- <div class="container">
        </div> -->
			<!--  Section Title -->

			<div class="container section-title mt-5 pt-5 text-light" data-aos="fade-up">
				<h2 class="text-light">마이 페이지</h2>
				<p>${sessionScope.loginMember.userId}님의 개인정보 및 프로필 사진변경 등 저장된 글 들을 볼 수 있는 공간</p>
			</div>
			<!-- End Section Title -->

			<div class="container" data-aos="fade-up" data-aos-delay="100">
				<div class="tab-pane fade show active" id="profile" role="tabpanel"
					aria-labelledby="profile-tab">
					<div class="row justify-content-center">
						<div class="col-xl-4">
							<div class="card mb-4 mb-xl-0">
								<div class="card-header">프로필 사진</div>
								<div class="card-body text-center overflow-auto">
									<img id="profileImage"
										class="img-account-profile rounded-circle mb-2" src=""
										alt="profileImage" width="300px" height="300px" />
									<div class="small font-italic text-muted mb-4">JPG 또는 PNG
										로 5MB 이하만 업로드 가능</div>
									<input type="file" id="fileUploader" accept="image/*"
										style="display: none;" />
									<button id="uploadBtn" type="button" class="btn btn-primary"
										onclick="registerProfileImage();" style="display: none;">사진
										업로드</button>
									<div id="changeNDeleteBtns"
										class="d-none justify-content-center gap-3">
										<button type="button" class="btn btn-secondary"
											onclick="registerProfileImage();">변경</button>
										<button type="button" class="btn btn-danger"
											onclick="removeProfileImage()">삭제</button>
									</div>
								</div>
							</div>
						</div>

						<div class="col-xl-4">
							<div class="card mb-4 mb-xl-0">
								<div class="card-header">개인 정보</div>
								<div class="card-body">
									<form>
										<div class="row gx-3">
											<div class="col">
												<label class="small mb-1" for="userId">아이디</label> <input
													class="form-control" id="userId" type="text"
													value="${loginMember.userId }" readonly />
											</div>
										</div>
										<div class="row gx-3 mt-3">
											<div class="col">
												<label class="small mb-1" for="userName">이름</label> <input
													class="form-control" id="userName" type="text"
													value="${loginMember.userName }" readonly />
											</div>
										</div>
										<div class="row gx-3 mt-3">
											<div class="col">
												<label class="small mb-1" for="email">이메일</label> <input
													class="form-control" id="email" type="text"
													value="${loginMember.email }" readonly />
											</div>
										</div>
										${loginMember.careers}
										<div class="row gx-3 mt-3">
											<div class="row">
												<label class="small mb-1" for="career">커리어</label>
												<div class="d-flex gap-3">
													<div class="form-check">
														<input class="form-check-input" type="radio" value="신입"
															name="careerRadio" id="careerRadio1"
															${loginMember.careers == "신입" ? 'checked' : ''}
															onclick="return false"> <label
															class="form-check-label" for="careerRadio1">신입</label>
													</div>
													<div class="form-check">
														<input class="form-check-input" type="radio" value="경력"
															name="careerRadio" id="careerRadio2"
															${loginMember.careers == "경력" ? 'checked' : ''}
															onclick="return false"> <label
															class="form-check-label" for="careerRadio2">경력</label>
													</div>
												</div>
											</div>
										</div>

										<div class="mt-3">
											<button id="modifyProfileBtn"
												class="btn btn-primary float-end" type="button"
												onclick="modifyProfile();">커리어 수정</button>
											<button id="updateProfileBtn"
												class="btn btn-success float-end" type="button"
												onclick="updateProfile();" style="display: none;">커리어
												저장</button>
										</div>

									</form>
								</div>
							</div>
						</div>
					</div>

					<div class="row justify-content-center mt-3">
						<div class="col-xl-8">
							<div class="card mb-4 mb-xl-0">
								<div class="card-header">스터디 모임</div>
								<div class="card-body">
									<div class="btn-group">

										<a href="/mypage/myStudyList"
											class="btn btn-outline-secondary" aria-current="page">
											${sessionScope.loginMember.userId}님이 작성한 스터디 모임글</a> <a
											href="/mypage/myApplyList" class="btn btn-outline-secondary">
											${sessionScope.loginMember.userId}님이 참여 신청한 스터디 모임글</a> <a
											href="/mypage/myJoinedStudyList"
											class="btn btn-outline-secondary">
											${sessionScope.loginMember.userId}님이 참여중인 스터디 모임글</a>

									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="row justify-content-center mt-3">
						<div class="col-xl-8">
							<div class="card mb-4 mb-xl-0">
								<div class="card-header">강의 추천</div>
								<div class="card-body">
									<div class="btn-group">
										<a href="/mypage/myLectureList"
											class="btn btn-outline-secondary" aria-current="page">
											${sessionScope.loginMember.userId}님이 작성한 게시글</a> <a
											href="/mypage/myReplyLectureList"
											class="btn btn-outline-secondary">
											${sessionScope.loginMember.userId}님이 내가 작성한 댓글</a>
									</div>
									<div class="btn-group">
										<a href="/mypage/myScrapLectureList"
											class="btn btn-outline-secondary">
											${sessionScope.loginMember.userId}님이 스크랩 한 게시글</a> <a
											href="/mypage/myLikeLectureList"
											class="btn btn-outline-secondary">
											${sessionScope.loginMember.userId}님이 좋아요 누른 게시글</a>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="row justify-content-center mt-3">
						<div class="col-xl-8">
							<div class="card mb-4 mb-xl-0">
								<div class="card-header">기업 리뷰</div>
								<div class="card-body">
									<div class="btn-group">
										<a href="/mypage/" class="btn btn-outline-secondary"
											aria-current="page"> ${sessionScope.loginMember.userId}님이
											작성한 게시글</a> <a href="/mypage/" class="btn btn-outline-secondary">
											${sessionScope.loginMember.userId}님이 스크랩 한 게시글</a> <a
											href="/mypage/" class="btn btn-outline-secondary">
											${sessionScope.loginMember.userId}님이 내가 작성한 댓글</a>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="row justify-content-center mt-3">
						<div class="col-xl-8">
							<div class="card mb-4 mb-xl-0">
								<div class="card-header">알고리즘</div>
									<div class="card-body">
										<div class="btn-group">
											<a href="/mypage/myAlgList" class="btn btn-outline-secondary" aria-current="page">
												${sessionScope.loginMember.userId}님이 작성한 게시글</a>
											<a href="/mypage/" class="btn btn-outline-secondary">
												${sessionScope.loginMember.userId}님이 스크랩 한 게시글</a>
											<a href="/mypage/" class="btn btn-outline-secondary">
												${sessionScope.loginMember.userId}님이 내가 작성한 댓글</a>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="row justify-content-center mt-3">
						<div class="col-xl-8">
							<div class="card mb-4 mb-xl-0">
								<div class="card-header">신고</div>
									<div class="card-body">
										<div class="btn-group">
											<a href="/mypage/myReportList" class="btn btn-outline-secondary" aria-current="page">
												${sessionScope.loginMember.userId}님이 신고한 게시글</a>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="row justify-content-center mt-3">
						<div class="col-xl-8">
							<div class="card mb-4 mb-xl-0">
								<div class="card-header">문의</div>
									<div class="card-body">
										<div class="btn-group">
											<a href="/mypage/myQnAList" class="btn btn-outline-secondary" aria-current="page">
												${sessionScope.loginMember.userId}님이 작성한 문의</a>
									</div>
								</div>
							</div>
						</div>
					</div>


					<div class="row justify-content-center mt-3">
						<div class="col-xl-8">
							<div class="card mb-4 mb-xl-0">
								<div class="card-header">회원 정보 변경</div>
								<div class="card-body">
									<button class="btn btn-primary" type="button"
										data-bs-toggle="modal" data-bs-target="#staticBackdrop">비밀번호
										변경</button>
									<button class="btn btn-info" type="button"
										data-bs-toggle="modal" data-bs-target="#sb-change-email" style="color:white;">이메일
										변경</button>
									<button class="btn btn-success" type="button"
										onclick="location.href='/member/logout'">로그아웃</button>

									<button class="btn btn-danger float-end" type="button"
										data-bs-toggle="modal" data-bs-target="#sb_dropMember">회원탈퇴</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- End Basic Section -->
	</main>

	<!-- 비밀번호 변경 Modal -->
	<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="staticBackdropLabel">비밀번호 변경</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="form-header text-center mb-4">
						<i class="bi bi-shield-lock"></i>
						<div class="text-center">
							<span class="fw-light fs-3 text-secondary">비밀번호 변경</span>
						</div>
						<small class="text-muted">주기적인 비밀번호 변경을 통해 개인정보를 안전하게
							보호하세요.</small>
					</div>

					<div class="form_chagePwd d-flex flex-column">
						<input type="hidden" id="userId" name="userId"
							value="${loginMember.userId }" />
						<div class="mb-3 mt-2">
							<div class="label-group">
								<label for="currentUserPwd" class="form-label">현재 비밀번호
									입력</label>
							</div>
							<input type="password" class="form-control" id="currentUserPwd" />
						</div>
						<div class="mb-3 mt-2">
							<div class="label-group">
								<label for="newUserPwd" class="form-label">새 비밀번호 입력</label><span
									id="errorMsgNewUserPwd" class="errMessage"></span>
							</div>
							<input type="password" class="form-control" id="newUserPwd" />
						</div>
						<div class="mb-3 mt-2">
							<div class="label-group">
								<label for="newUserPwdConfirm" class="form-label">새 비밀번호
									확인</label><span id="errorMsgNewUserPwdConfirm" class="errMessage"></span>
							</div>
							<input type="password" class="form-control"
								id="newUserPwdConfirm" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger" onclick="changePwd();">변경</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 이메일 변경 Modal -->
	<div class="modal fade" id="sb-change-email" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="staticBackdropLabel">이메일 변경</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="form-header text-center mb-4">
						<i class="bi bi-shield-lock"></i>
						<div class="text-center mb-3">
							<span class="fw-light fs-4 text-secondary">새로운 이메일을
								입력해주세요.</span>
						</div>
					</div>

					<div class="mb-3 mt-2" id="email-input-div">
						<div class="label-group">
							<label for="new-email" class="form-label">새 이메일</label><span
								id="email-error-msg" class="errMessage"></span>
						</div>
						<input type="text" class="form-control" id="new-email"
							name="email" />

						<div>
							<button type="button"
								class="btn btn-block btn-outline-secondary mt-2"
								id="request-verify-email-btn">인증받기</button>
							<span id="email-timelimit" style="color: red;"></span>
						</div>

						<div id="email-validation-check">
							<input type="text" class="form-control" id="email-code"
								style="margin: 5px 0;" disabled />
							<button type="button" id="check-email-code-btn"
								class="btn btn-outline-success btn-sm" disabled>인증확인</button>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-primary" id="submit-email-btn"
						onclick="changeEmail();" disabled>확인</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 회원탈퇴 Modal -->
	<div class="modal fade" id="sb_dropMember" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="staticBackdropLabel">회원 탈퇴</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="form-header text-center mb-4">
						<i class="bi bi-shield-lock"></i>
						<div class="text-center mb-3">
							<span class="fw-light fs-4 text-secondary">${loginMember.userName }
								님, 회원 탈퇴 시 아래와 같이 처리됩니다.</span>
						</div>
						<div class="card mb-3">
							<div class="card-body">회원 탈퇴일로부터 계정과 닉네임을 포함한 계정
								정보(아이디/이름/이메일)는 개인정보 보호정책에 따라 60일간 보관(잠김)되며, 60일 경과된 후에는 모든 개인
								정보는 완전히 삭제되며 더 이상 복구할 수 없게 됩니다. 작성된 게시물은 삭제되지 않으며, 익명처리 후 DDev로
								소유권이 귀속됩니다. 게시물 삭제가 필요한 경우에는 관리자 메일로 문의해 주시기 바랍니다.</div>
						</div>
						<div class="text-start mb-3">
							<div class="mb-3">계정을 삭제하시려는 이유를 말씀해주세요. 사이트 개선에 중요한 자료로
								활용하겠습니다.</div>
							<div class="vstack gap-1">
								<div class="form-check">
									<input class="form-check-input" type="radio" name="dropReason"
										id="dropReason_1"> <label class="form-check-label"
										for="dropReason_1">기록 삭제 목적</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="dropReason"
										id="dropReason_2"> <label class="form-check-label"
										for="dropReason_2">이용이 불편하고 장애가 많아서</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="dropReason"
										id="dropReason_3"> <label class="form-check-label"
										for="dropReason_3">다른 사이트가 더 좋아서</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="dropReason"
										id="dropReason_4"> <label class="form-check-label"
										for="dropReason_4">삭제하고 싶은 내용이 있어서</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="dropReason"
										id="dropReason_5"> <label class="form-check-label"
										for="dropReason_5">사용빈도가 낮아서</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="dropReason"
										id="dropReason_6"> <label class="form-check-label"
										for="dropReason_6">콘텐츠 불만</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="dropReason"
										id="dropReason_7"> <label class="form-check-label"
										for="dropReason_7">기타</label> <input class="form-control"
										type="text" id="etcReason" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger"
						onclick="dropMember();">탈퇴</button>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="../footer.jsp"%>

	<!-- Scroll Top Button -->
	<a href="#" id="scroll-top"
		class="scroll-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Preloader -->
	<div id="preloader">
		<div></div>
		<div></div>
		<div></div>
		<div></div>
	</div>

	<!-- Vendor JS Files -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script src="/resources/assets/vendor/glightbox/js/glightbox.min.js"></script>
	<script
		src="/resources/assets/vendor/purecounter/purecounter_vanilla.js"></script>
	<script
		src="/resources/assets/vendor/imagesloaded/imagesloaded.pkgd.min.js"></script>
	<script
		src="/resources/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
	<script src="/resources/assets/vendor/aos/aos.js"></script>
	<script src="/resources/assets/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="/resources/assets/js/main.js"></script>
</body>
</html>
