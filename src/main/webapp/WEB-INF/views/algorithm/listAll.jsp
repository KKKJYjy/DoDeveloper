<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" />
<link href="/resources/assets/vendor/glightbox/css/glightbox.min.css"
	rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"
	rel="stylesheet" />
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</head>

<style>
body {
	padding: 1.5em;
	background: #f5f5f5
}

table {
	border: 1px #a39485 solid;
	font-size: .9em;
	box-shadow: 0 2px 5px rgba(0, 0, 0, .25);
	width: 100%;
	border-collapse: collapse;
	border-radius: 5px;
	overflow: hidden;
}

th {
	text-align: left;
}

thead {
	font-weight: bold;
	color: #fff;
	background: #73685d;
}

td, th {
	padding: 1em .5em;
	vertical-align: middle;
}

td {
	border-bottom: 1px solid rgba(0, 0, 0, .1);
	background: #fff;
}

a {
	color: #73685d;
}

@media all and (max-width: 768px) {
	table, thead, tbody, th, td, tr {
		display: block;
	}
	th {
		text-align: right;
	}
	table {
		position: relative;
		padding-bottom: 0;
		border: none;
		box-shadow: 0 0 10px rgba(0, 0, 0, .2);
	}
	thead {
		float: left;
		white-space: nowrap;
	}
	tbody {
		overflow-x: auto;
		overflow-y: hidden;
		position: relative;
		white-space: nowrap;
	}
	tr {
		display: inline-block;
		vertical-align: top;
	}
	th {
		border-bottom: 1px solid #a39485;
	}
	td {
		border-bottom: 1px solid #e5e5e5;
	}
}


<!-- -->

.side-menu {
            top: 50px;
            width: 45px;
            z-index: 10;
            background: #ff5858;
            border-right: 1px solid rgba(0, 0, 0, 0.07);
            bottom: 50px;
            height: 100%;
            margin-bottom: -70px;
            margin-top: 0px;
            padding-bottom: 70px;
            position: fixed;
            box-shadow: 0 0px 24px 0 rgb(0 0 0 / 6%), 0 1px 0px 0 rgb(0 0 0 / 2%);
        }

        .sidebar-inner {
            height: 100%;
            padding-top: 30px;
        }

        #sidebar-menu,
        #sidebar-menu ul,
        #sidebar-menu li,
        #sidebar-menu a {
            border: 0;
            font-weight: normal;
            line-height: 1;
            list-style: none;
            margin: 0;
            padding: 0;
            position: relative;
            text-decoration: none;
        }

        #sidebar-menu>ul>li a {
            color: #fff;
            font-size: 20px;
            display: block;
            padding: 14px 0px;
            margin: 0px 0px 0px 8px;
            border-top: 1px solid rgba(0, 0, 0, 0.1);
            border-bottom: 1px solid rgba(255, 255, 255, 0.05);
            width: 28px;
            cursor: pointer;
        }

        #sidebar-menu .fas {
            padding-left: 6px;
        }

        /* 사이드 메뉴 */
        input[type="search"] {
            width: 180px;
            margin: 0 auto;
            margin-left: 9px;
            border: 2px solid #797979;
            font-size: 14px;
            margin-top: 10px;
            padding: 4px 0 4px 14px;
            border-radius: 50px;
        }

        .left_sub_menu {
            position: fixed;
            top: 50px;
            width: 200px;
            z-index: 10;
            left: 45px;
            background: white;
            border-right: 1px solid rgba(0, 0, 0, 0.07);
            bottom: 50px;
            height: 100%;
            margin-bottom: -70px;
            margin-top: 0px;
            padding-bottom: 0px;
            box-shadow: 0 0px 24px 0 rgb(0 0 0 / 6%), 0 1px 0px 0 rgb(0 0 0 / 2%);
            color: black;
            margin-top: 40px;
        }

        .sub_menu {
            margin-top: 50px;
        }

        .left_sub_menu>.sub_menu li:hover {
            color: ff5858;
            background-color: #e1e1e1;
        }

        .left_sub_menu>.sub_menu li {
            color: #333;
            font-size: 17px;
            font-weight: 600;
            padding: 20px 0px 8px 14px;
            border-bottom: 1px solid #e1e1e1;
        }

        .sub_menu>h2 {
            padding-bottom: 4px;
            border-bottom: 3px solid #797979;
            margin-top: 30px;
            font-size: 21px;
            font-weight: 600;
            color: #333;
            margin-left: 10px;
            margin-right: 10px;
            font-family: 'NotoKrB';
            line-height: 35px;
        }

        .sub_menu .fas {
            color: #ff5858;
            font-size: 20px;
            line-height: 20px;
            float: right;
            margin-right: 20px;
        }

        .sub_menu>.big_menu>.small_menu li {
            color: #333;
            font-size: 14px;
            font-weight: 600;
            border-bottom: 0px solid #e1e1e1;
            margin-left: 14px;
            padding-top: 8px;
        }

        .big_menu {
            cursor: pointer;
        }

        ul {
            padding-inline-start: 0px;
        }

        a {
            color: #797979;
            text-decoration: none;
            background-color: transparent;
        }

        ul {
            list-style: none;
        }

        ol,
        ul {
            margin-top: 0;
            margin-bottom: 10px;
        }

        .has_sub {
            width: 100%;
        }

        .overlay {
            position: fixed;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.7);
        }

        .hide_sidemenu {
            display: none;
        }




</style>

<script>

	function classCodeFilter(val) {
		alert("!!??!!");
		console.log(val);
		
		$.ajax({
            url: "/algorithm/getClassification", // 실제 Ajax 요청을 처리할 URL
            type: "GET",
            contentType: "application/json", // JSON 형식으로 데이터 전송
            data : {val : val},
            dataType: 'text',
            success: function(data) {
                // Ajax 요청 성공 시 처리
                console.log("code", val); // 받은 응답을 콘솔에 출력
                console.log(data);
                
                
               
                
                searchTable(data);  // db에서 받아온 데이터의 배열의 길이만큼의 tr태그를 가진 빈 값의 tbody를 html 화면에 출력 
                insertTable(data);  // db에서 받아온 데이터를 tbody 값에 각각 입력하는 함수
                
            },
            error: function(xhr, status, error) {
                // Ajax 요청 실패 시 처리
                console.error("Ajax request failed:", status, error);
            }
        });
	}
	
	function searchTable(data) {
		console.log(data);
		
		let arr = data.split('"boardNo":');
		
		
		let html = ``;
		for(let i=1;i<arr.length;i++){
			let no = arr[i].split(",")[0];
			//console.log(no);
			var myVariable = "/algorithm/algDetail?boardNo="+no;
			console.log(myVariable);
		html += `<tr>`
		html += `<td style="color: black;" id="title" class="title"><a id="myLink" class="myLink" href="#">no</a></td>`;
		html += `<td>Matman</td>`;
		html += `<td>(713) 123-8965</td>`;
		html += `<td><a id="myLink">jmatman@stewart.com</a></td>`;
		html += `<td>01/13/1979</td>`;
		html += `</tr>`;
		}
		
		$(".tbody").empty();
		$(".tbody").append(html);
		
		
	}
	
	function insertTable(data) {
		let arr = data.split('"boardNo":');
		
		let arr2 = data.split('"title":');
		
		for(let i=1;i<arr.length;i++){
			let no = arr[i].split(",")[0];
			let title = arr2[i].split(",")[0];
			title = title.replace(/\"/gi, "");
			console.log(title);
			document.getElementsByClassName("myLink")[i-1].href = "/algorithm/algDetail?boardNo="+no;
			document.getElementsByClassName("myLink")[i-1].innerHTML = title;
		}
		
		
	}

</script>

<body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
	<%@ include file="../header.jsp"%>

	<main id="main">
		<!-- Basic Section - Algorithm Page -->
		<section id="algorithm" class="basic">
			<div class="container">
				<h1>알고리즘</h1>
				<h1>alg</h1>



				<div class="container mt-3">
					<h2>알고리즘 목록</h2>
					<p>The disabled class adds a lighter text color to the disabled
						item. And if used on links, it will remove the default hover
						effect.</p>
					<div class="list-group">
						<c:forEach var="alg" items="${algBoardList}">
							<a href="/algorithm/algDetail?boardNo=${alg.boardNo}"
								class="list-group-item">${alg.title }</a>
							<div>/////////////////////////////</div>
						</c:forEach>
					</div>

					<div class="btns">

						<button type="button" class="btn btn-info"
							onclick="location.href='/algorithm/writePOST';">글쓰기</button>
						<button type="button" class="btn btn-info"
							onclick="location.href='/algorithm/modifyAlg';">글수정</button>
					</div>
				</div>

				

							<div>${algBoardList}</div>
							<div>${algClassification}</div>
							<div>${algBoardByCode}</div>
                        


			<table>
				<thead>
					<tr>
						<th>title</th>
						<th>Last Name</th>
						<th>Phone</th>
						<th>Email</th>
						<th>Date of Birth</th>

					</tr>
				</thead>
				<tbody class="tbody">
						<c:forEach var="alg" items="${algBoardList}">
							<tr>
								
								<td style="color: black;" id="title"><a href="/algorithm/algDetail?boardNo=${alg.boardNo}">${alg.title}</a></td>
								
								<td>Matman</td>
								<td>(713) 123-8965</td>
								<td><a href="mailto:jmatman@stewart.com">jmatman@stewart.com</a></td>
								<td>01/13/1979</td>
							</tr>

						</c:forEach>
					</tbody>
			</table>

<!--     ===================side bar================================                     -->
		<div id="wrapper">
        <div class="topbar" style="position: absolute; top:0;">
            <!-- 왼쪽 메뉴
            <div class="left side-menu">
                <div class="sidebar-inner">
                    <div id="sidebar-menu">
                        <ul>
                            <li class="has_sub"><a href="javascript:void(0);" class="waves-effect">
                                <i class="fas fa-bars"></i>
                            </a></li>
                        </ul>
                    </div>
                </div>
            </div>
             -->
            <!-- 왼쪽 서브 메뉴 -->
            <div class="left_sub_menu">
                <div class="sub_menu">
                    <input type="search" name="SEARCH" placeholder="SEARCH">
                    <h2>TITLE</h2>
                    <ul class="big_menu">
                        <li>MENU 1 <i class="arrow fas fa-angle-right"></i></li>
                        <c:forEach var="algClass" items="${algClassification}">
                        <ul class="small_menu">
                        	<!--  사이드바의 메뉴에서 항목을 클릭하면 listAll에서 항목에 해당하는 종류의 알고리즘만 출력되도록 -->
                            <li onclick="classCodeFilter(this.value)" value="${algClass.code}">${algClass.algClassification}</li>
                            
                        </ul>
                        </c:forEach>
                    </ul>
                    <ul class="big_menu">
                        <li>MENU 2 <i class="arrow fas fa-angle-right"></i></li>
                        <ul class="small_menu">
                            <li><a href="#">소메뉴2-1</a></li>
                            <li><a href="#"></a>소메뉴2-2</a></li>
                        </ul>
                    </ul>
                    <ul class="big_menu">
                        <li>MYPAGE</li>
                    </ul>
                </div>
            </div>
            <!--  
            <div class="overlay"></div>
             -->
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
