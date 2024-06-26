<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <title>Algorithm List - DoDeveloper</title>
    <meta content="" name="description" />
    <meta content="" name="keywords" />

    <!-- Favicons -->
    <link href="/resources/assets/img/favicon.png" rel="icon" />
    <link
      href="/resources/assets/img/apple-touch-icon.png"
      rel="apple-touch-icon"
    />

    <!-- Fonts -->
    <link href="https://fonts.googleapis.com" rel="preconnect" />
    <link href="https://fonts.gstatic.com" rel="preconnect" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;1,300;1,400;1,500;1,600;1,700;1,800&family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Lato:ital,wght@0,100;0,300;0,400;0,700;0,900;1,100;1,300;1,400;1,700;1,900&display=swap"
      rel="stylesheet"
    />

    <!-- Vendor CSS Files -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"
    />
    <link
      href="/resources/assets/vendor/glightbox/css/glightbox.min.css"
      rel="stylesheet"
    />
    <link
      href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"
      rel="stylesheet"
    />
    <link href="/resources/assets/vendor/aos/aos.css" rel="stylesheet" />

    <!-- Template Main CSS File -->
    <link href="/resources/assets/css/main.css" rel="stylesheet" />

    <!-- =======================================================
  * Template Name: Append
  * Template URL: https://bootstrapmade.com/append-bootstrap-website-template/
  * Updated: Mar 17 2024 with Bootstrap v5.3.3
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--  pyscript -->
<link rel="stylesheet" href="https://pyscript.net/alpha/pyscript.css" />
<script defer src="https://pyscript.net/alpha/pyscript.js"></script>
<script>


$(function () {
	
	$('.py').hide();
	
	
	
		let text = document.querySelector('.content').textContent;
		console.log(text);
		var enter = text.replace(/(\n|\r\n)/g, '<br>');
		console.log(enter);
		var tab = enter.replaceAll('    ', '&emsp;');
		console.log(tab);
		
		const html = document.getElementById('content');
		html.innerHTML = tab;
	
	
	
	
	
});

function button1_click(no) {
	var boardNo = no;
	console.log(boardNo)	
	$('.'+boardNo).show()
}

function button2_click(no) {
	var boardNo = no;
	
	$('.'+boardNo).hide()
}


</script>    
<style>
#button {
	color: black;
	font-size: 12px;
	border: 1px solid black;
	padding: 5px;
	border-radius: 6px;
}
p {			
	border: 1px solid white;
	padding: 10px;
	margin: 20px;	
}
.btn {
	margin-bottom: 30px;
}
</style>    
    
  </head>
  
 

  <body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
    <%@ include file="../header.jsp"%>

    <main id="main">
      <!-- Basic Section - Algorithm Page -->
      <section id="algorithm" class="basic">
        <div class="container">
        	
         
        	
          <h1>alg</h1>

          
          
          
          
          
          <div class="container mt-3">
					
						<h2>${algDetail.algDetailTitle}</h2></a>
						<div>${algDetail.algDetailNo}</div>
						<div class="mt-4 p-5 bg-primary text-white rounded">
							<h1>code</h1>
							<div class='content' id='content'>${algDetailList.algDetailContent}</div>
							<div>
							<h1>result</h1>
								<py-script class="py ${algDetailList.algDetailNo}">
								${algDetailList.algDetailContent} </py-script>
							</div>
							<div class="btn">
							<input type="button" id="button"
								onclick="button1_click(${algDetailList.algDetailNo})" value="RUN" />
							<input type="button" id="button"
								onclick="button2_click(${algDetailList.algDetailNo})" value="HIDE" />
							</div>
							<p>${algDetailList.algDetailComment}</p>
						</div>
					</div>
          
          
          
        </div>
      </section>
        <div class="btns">
				
				<button type="button" class="btn btn-info"
					onclick="location.href='/algorithm/modifyAlgDetail';">글수정</button>
				<button type="button" class="btn btn-info"
					onclick="location.href='/algorithm/removeAlgDetail?algDetailNo=${algDetailList.algDetailNo}&algBoardNo=${algDetailList.algBoardNo}';">글삭제</button>
			</div>
      <!-- End Basic Section -->
    </main>

    <%@ include file="../footer.jsp"%>

    <!-- Scroll Top Button -->
    <a
      href="#"
      id="scroll-top"
      class="scroll-top d-flex align-items-center justify-content-center"
      ><i class="bi bi-arrow-up-short"></i
    ></a>

    <!-- Preloader -->
    <div id="preloader">
      <div></div>
      <div></div>
      <div></div>
      <div></div>
    </div>

    <!-- Vendor JS Files -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="/resources/assets/vendor/glightbox/js/glightbox.min.js"></script>
    <script src="/resources/assets/vendor/purecounter/purecounter_vanilla.js"></script>
    <script src="/resources/assets/vendor/imagesloaded/imagesloaded.pkgd.min.js"></script>
    <script src="/resources/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <script src="/resources/assets/vendor/aos/aos.js"></script>
    <script src="/resources/assets/vendor/php-email-form/validate.js"></script>

    <!-- Template Main JS File -->
    <script src="/resources/assets/js/main.js"></script>
  </body>
</html>
