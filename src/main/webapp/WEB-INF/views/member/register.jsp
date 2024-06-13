<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<title>Register - DoDeveloper</title>
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
<link href="/resources/assets/css/member/register.css" rel="stylesheet" />

<!-- =======================================================
  * Template Name: Append
  * Template URL: https://bootstrapmade.com/append-bootstrap-website-template/
  * Updated: Mar 17 2024 with Bootstrap v5.3.3
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="/resources/assets/js/emailVerification/emailVerification.js"></script>
<script>
      let isValidName = false;
      let isValidMobile = false;
      let isTryingSubmitEmail = false;
      let isValidEmail = false;
      let emailTimer;
      let emailCodeExpireDate = new Date();

      $(function () {
        // 아이디를 입력할 때, 중복인지 아닌지 검사하는 메서드
        $("#userId").keyup(function (e) {
          checkValidId();
        });

        $("#userPwd, #userPwdConfirm").on("keyup", function (e) {
          	checkValidPassword();
        });
        
        $("#email").keyup(function (e){
        	onChangeOfWrittenEmail();
        });
        
        $("#request-confirm-email-btn").on("click",function(e){
        	requestVerificateEmail();
        });
        
        $("#check-email-code-btn").on("click", function(e){
        	checkEmailVerificationCode();
        });
        
        $("#email-submit").on("click", function(e){
          	isTryingSubmitEmail = $(this).is(":checked");
        	showEmailInputDiv(isTryingSubmitEmail);
        });

        $("#userName").keyup(function (e) {
          checkValidName();
        });
       
        showEmailInputDiv(false);
      });
      
      function requestVerificateEmail(){
      	let emailAddress = $("#email").val();
    	let emailRegex = new RegExp('^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$');
      	
    	if(!emailRegex.test(emailAddress)){
    		$("#email-error-msg").html("이메일 주소를 다시 확인해주십시오.");
    		return;
    	}
    	
    	$("#email-code").prop("disabled", false);
    	sendRequestToVerificateEmail(emailAddress);
    	$("#email-validation-check").show();
    	startEmailValidationTimer(1000 * 60 * 5);
      }
      
      function onChangeOfWrittenEmail(){
    	$("#email-validation-check").hide();
    	$("#email-error-msg").html("");
    	isValidEmail = false;
    	$("#email-code").val("");
      }
      
      
      function startEmailValidationTimer(timeLimit){
    	  let now = new Date();
    	  emailCodeExpireDate = new Date( now.getTime() + timeLimit );
    	  
    	  emailTimer = setInterval(() => {
    	         showRemainingTime(emailCodeExpireDate);
    	}, 100);
      }
      
      function endEmailValidationTimer(){
    	  clearInterval(emailTimer);
    	  $("#email-timelimit").html("");
      }
      
      function showRemainingTime(expireDate){
    	  const now = new Date();
    	  const remainingTime = expireDate.getTime() - now.getTime();
    	  
    	  console.log(remainingTime);
    	  console.log(new Date(remainingTime));
    	  
    	  
    	  let output = "";
    	  
    	  if(remainingTime <= 0){
    		  output = "0:00";
        	  $("#email-timelimit").html(output);
        	  $("#email-code").prop("disabled", true);
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
      
      function checkEmailVerificationCode(){
      	let emailCode = $("#email-code").val();
    	isValidEmail = sendEmailVerificationCode(emailCode);
    	
    	if(isValidEmail){
    		$("#email-error-msg").html("확인됨!");
    		$("#email-error-msg").css("color", "green");    		
        	endEmailValidationTimer();
        	
    	}else{
    		$("#email-error-msg").html("다시 확인해주십시오.");
    		$("#email-error-msg").css("color", "red");
    	}
      }

      function showEmailInputDiv(data){
    	if(data == true){
    		$("#email-input-div").show();
    		$("#email").prop("disabled", false);
    	}else{
    		$("#email-input-div").hide();
    		$("#email").prop("disabled", true);
    	}
      }
      
      function duplicateUserId(userId) {
        let result = false;
        $.ajax({
          url: "./duplicateUserId",
          type: "post",
          dataType: "json",
          async: false,
          data: {
            userId: userId,
          },
          success: function (data) {
            console.log(data);

            if (data.isDuplicate == true) {
              $("#errorMsgUserId").html("사용 불가능한 아이디 입니다.");
              $("#errorMsgUserId").removeClass("text-primary");
              $("#errorMsgUserId").removeClass("text-success");
              $("#errorMsgUserId").addClass("text-danger");
              $("#checkUserId").val("false");
              result = false;
            } else {
              $("#errorMsgUserId").html("사용 가능한 아이디 입니다.");
              $("#errorMsgUserId").removeClass("text-primary");
              $("#errorMsgUserId").removeClass("text-danger");
              $("#errorMsgUserId").addClass("text-success");
              $("#checkUserId").val("true");
              result = true;
            }
          },
        });
        return result;
      }

      function checkValidId() {
        let result = false;
        let userId = $("#userId").val();

        if (userId.length > 2) {
          result = duplicateUserId(userId);
        } else if (userId.length == 0) {
          $("#errorMsgUserId").html("아이디는 2자리 이상 필수 항목 입니다");

          $("#errorMsgUserId").removeClass("text-danger");
          $("#errorMsgUserId").removeClass("text-success");
          $("#errorMsgUserId").addClass("text-primary");
          result = false;
        }
        return result;
      }

      function checkValidPassword() {
        // 비밀번호 : 4자 이상 12자 이하 필수 (비밀 번호 확인과 동일해야 함)

        let result = false;

        let pwd1 = $("#userPwd").val();
        let pwd2 = $("#userPwdConfirm").val();

        if (pwd1 == "" || pwd1.length < 1) {
          $("#errorMsgUserPwd").html("비밀번호는 필수 항목 입니다");
          $("#errorMsgUserPwdConfirm").html("");
          $("#errorMsgUserPwd").removeClass("text-danger");
          $("#errorMsgUserPwd").removeClass("text-success");
          $("#errorMsgUserPwd").addClass("text-primary");
        } else if (pwd1.length < 4 || pwd1.length > 13) {
          $("#errorMsgUserPwd").html("비밀번호는 4~12 글자로 입력하세요");
          if (pwd2 != "" && pwd1 != pwd2) {
            $("#errorMsgUserPwdConfirm").html("비밀번호가 서로 맞지 않습니다!");
            $("#errorMsgUserPwdConfirm").removeClass("text-primary");
            $("#errorMsgUserPwdConfirm").removeClass("text-success");
            $("#errorMsgUserPwdConfirm").addClass("text-danger");
            result = false;
          }
        } else if (pwd1.length >= 4 && pwd1.length <= 13) {
          if (pwd2 != "" && pwd1 != pwd2) {
            $("#errorMsgUserPwd").html("비밀번호가 서로 맞지 않습니다!");
            $("#errorMsgUserPwdConfirm").html("비밀번호가 서로 맞지 않습니다!");
            $("#errorMsgUserPwd, #errorMsgUserPwdConfirm").removeClass("text-primary");
            $("#errorMsgUserPwd, #errorMsgUserPwdConfirm").removeClass(
              "text-success"
            );
            $("#errorMsgUserPwd, #errorMsgUserPwdConfirm").addClass("text-danger");

            result = false;
          } else if (pwd1 == pwd2) {
            $("#errorMsgUserPwd").html("비밀번호가 확인되었습니다.");
            $("#errorMsgUserPwdConfirm").html("비밀번호가 확인되었습니다.");
            $("#errorMsgUserPwd, #errorMsgUserPwdConfirm").removeClass("text-primary");
            $("#errorMsgUserPwd, #errorMsgUserPwdConfirm").removeClass(
              "text-danger"
            );
            $("#errorMsgUserPwd, #errorMsgUserPwdConfirm").addClass("text-success");
            result = true;
          } else {
            if (pwd2 == "" && document.activeElement.id != "userPwd") {
              $("#errorMsgUserPwd").html("");
              $("#errorMsgUserPwdConfirm").html("비밀번호를 확인해주세요.");

              $("#errorMsgUserPwdConfirm").removeClass("text-danger");
              $("#errorMsgUserPwdConfirm").removeClass("text-success");
              $("#errorMsgUserPwdConfirm").addClass("text-primary");
            } else {
              $("#errorMsgUserPwd").html("");
              $("#errorMsgUserPwdConfirm").html("");
            }
            result = false;
          }
        }
        return result;
      }

      function checkValidName() {
        return $("#userId").val().length > 0 ? true : false;
      }
      
      function checkValidEmail() {
    	  if(isTryingSubmitEmail == false || isValidEmail == true){
    		  return true;
    	  }
    	  $("#email-error-msg").html("이메일을 확인해주세요.");
    	  $("#email-error-msg").css("color","red");
    	  return false;
      }

      function validate() {
        let result = false;
        let isValidId = $("#checkUserId").val();

        if (
          isValidId &&
          checkValidId() &&
          checkValidPassword() &&
          checkValidName() &&
          checkValidEmail()
        ) {
          result = true;
        }
        console.log("result", result);
        return result;
      }
    </script>
<style>
</style>
</head>

<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="../header.jsp"%>

	<main id="main">
		<!-- Basic Section - Login Page -->
		<section id="register" class="basic">
			<div class="container" data-aos="fade-up" data-aos-delay="200">
				<!-- Form-->
				<div class="d-flex justify-content-center">
					<div class="form-register form-control p-4"
						style="max-width: 330px">
						<div class="form-panel">
							<div class="form-header text-center">
								<i class="bi bi-person-fill-add"></i>
								<div class="text-center mb-4">
									<span class="fw-light fs-3 text-secondary">회원 가입</span>
								</div>
							</div>
							<div class="form-content">
								<form action="registerPost" method="post">
									<div class="d-flex flex-column">
										<div class="mb-3 mt-2">
											<div class="label-group">
												<label for="userId" class="form-label">아이디</label><span
													id="errorMsgUserId" class="errMessage"></span>
											</div>
											<input type="text" class="form-control" id="userId"
												name="userId" /> <input type="hidden" id="checkUserId"
												value="false" />
										</div>
										<div class="mb-3 mt-2">
											<div class="label-group">
												<label for="userPwd" class="form-label">비밀번호</label><span
													id="errorMsgUserPwd" class="errMessage"></span>
											</div>
											<input type="password" class="form-control" id="userPwd"
												name="userPwd" />
										</div>
										<div class="mb-3 mt-2">
											<div class="label-group">
												<label for="userPwdConfirm" class="form-label">비밀번호
													확인</label><span id="errorMsgUserPwdConfirm" class="errMessage"></span>
											</div>
											<input type="password" class="form-control"
												id="userPwdConfirm" />
										</div>
										<div class="mb-3 mt-2">
											<label for="userName" class="form-label">이름</label><input
												type="text" class="form-control" id="userName"
												name="userName" required="required" />
										</div>

										<div class="mb-3 mt-2" id="email-input-div"
											style="display: none;">
											<div class="label-group">
												<label for="email" class="form-label">이메일</label><span
													id="email-error-msg" class="errMessage"></span>
											</div>
											<input type="text" class="form-control" id="email"
												name="email" />

											<div>
												<button type="button"
													class="btn btn-block btn-outline-secondary mt-2"
													id="request-confirm-email-btn">인증받기</button>
												<span id="email-timelimit" style="color: red;"></span>
											</div>

											<div id="email-validation-check" style="display: none">
												<input type="text" class="form-control" id="email-code"
													style="margin: 5px 0;" disabled/>
												<button type="button" id="check-email-code-btn"
													class="btn btn-outline-success btn-sm">인증확인</button>
											</div>
										</div>
										<div>
											<input type="checkbox" id="email-submit" name="email-submit" />
											<label for="email-submit">이메일 제출</label>
										</div>
										<div class="d-grid">
											<button type="submit" class="btn btn-block btn-register mt-2"
												onclick="return validate()">가입하기</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- End Basic Section -->
	</main>

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
