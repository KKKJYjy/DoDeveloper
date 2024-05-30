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
<script>
      let isValidName = false;
      let isValidMobile = false;
      let isValidEmail = false;

      $(function () {
        // 아이디를 입력할 때, 중복인지 아닌지 검사하는 메서드
        $("#userId").keyup(function (e) {
          checkValidId();
        });

        $("#userPwd, #userPwdConfirm").on("keyup", function (e) {
          checkValidPassword();
        });

        $("#email").keyup(function (e) {
          
        });
        
        $("#request-confirm-email-btn").on("click",function(e){
        	let emailAddress = $("#email").val();
        	/*
        	if(emailAddress != '^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$'){
        		return;
        	}
        	*/
        	console.log(emailAddress);
        	requestEmailConfirm(emailAddress);
        	$("#email-validation-check").show();
        });
        
        $("#confirm-email-code-btn").on("click", function(e){
        	let emailValidCode = $("#email-code").val();
        	isValidEmail = confirmEmail(emailValidCode);
        });

        $("#userName").keyup(function (e) {
          checkValidName();
        });
      });

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

      function requestEmailConfirm(emailAddress){
    	  let urlInput = "./emailConfirmRequest";
    	  result = false;
    	  
    	  $.ajax({
              url: urlInput,
              type: "post",
              dataType: "json",
              data: {
                  emailAddress: emailAddress,
                },
              success: function (data) {
            	  result = data;
              },
            });
    	  
		  return result;
      }
      
      function confirmEmail(code){
    	  let urlInput = "./emailCode";
    	  result = false;
    	  
    	  $.ajax({
              url: urlInput,
              type: "post",
              dataType: "json",
              async: false,
              data: {
                  code: code,
                },
              success: function (data) {
                result = data;
              },
            });
    	  
    	  return result;
      }
      
      function checkValidEmail() {
    	  return true;
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
										<div class="mb-3 mt-2">
											<div class="label-group">
												<label for="email" class="form-label">이메일</label><span
													id="errorMsgEmail" class="errMessage"></span>
											</div>
											<input type="text" class="form-control" id="email"
												name="email" />
											<button type="button"
												class="btn btn-block btn-outline-secondary mt-2"
												id="request-confirm-email-btn">인증받기</button> 아직은 이거 안해도 됩니다.
											<div id="email-validation-check" style="display: none">
												<input type="text" class="form-control" id="email-code"
													style="margin: 5px 0;" />
												<button type="button" id="confirm-email-code-btn"
													class="btn btn-outline-success btn-sm">인증확인</button>
											</div>
											<div class="d-grid">
												<button type="submit"
													class="btn btn-block btn-register mt-2"
													onclick="return validate()">가입하기</button>
											</div>
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
