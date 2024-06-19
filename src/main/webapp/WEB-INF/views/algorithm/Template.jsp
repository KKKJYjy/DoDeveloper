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
  </head>
  
  <style>
  
  body {
  padding:1.5em;
  background: #f5f5f5
}

table {
  border: 1px #a39485 solid;
  font-size: .9em;
  box-shadow: 0 2px 5px rgba(0,0,0,.25);
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
  border-bottom: 1px solid rgba(0,0,0,.1);
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
    box-shadow: 0 0 10px rgba(0,0,0,.2);
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
  
  </style>

  <body class="index-page" data-bs-spy="scroll" data-bs-target="#navmenu">
    <%@ include file="../header.jsp"%>

    <main id="main">
      <!-- Basic Section - Algorithm Page -->
      <section id="algorithm" class="basic">
        <div class="container">
        	
          
        	
          <h1>alg</h1>

   <table>
    <thead>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Phone</th>
        <th>Email</th>
        <th>Date of Birth</th>

    </tr>
    </thead>
    <tbody>
    <tr>
        <td>James</td>
        <td>Matman</td>
        <td>(713) 123-8965</td>
        <td><a href="mailto:jmatman@stewart.com">jmatman@stewart.com</a></td>
        <td>01/13/1979</td>
    </tr>
    <tr>
        <td>Johnny</td>
        <td>Smith</td>
        <td>(713) 584-9614</td>
        <td><a href="mailto:jsmith@stewart.com">jsmith@stewart.com</a></td>
        <td>06/09/1971</td>
    </tr>
    <tr>
        <td>Susan</td>
        <td>Johnson</td>
        <td>(713) 847-1124</td>
        <td><a href="mailto:sjohnson@stewart.com">sjohnson@stewart.com</a></td>
        <td>08/25/1965</td>
    </tr>
    <tr>
        <td>Tracy</td>
        <td>Richardson</td>
        <td>(713) 245-4821</td>
        <td><a href="mailto:trichard@stewart.com">trichard@stewart.com</a></td>
        <td>03/13/1980</td>
    </tr>
    </tbody>
</table>
         
        </div>
      </section>
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
