<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DoDeveloper</title>
<meta content="" name="description" />
<meta content="" name="keywords" />
</head>
<body>
	<c:set var="contextPath" value="<%=request.getContextPath()%>" />
	<c:import url="./adminHeader.jsp"></c:import>


	<c:import url="./adminSidebar.jsp"></c:import>



	<div class="page-wrapper">

		<c:import url="./adminMiniHeader.jsp"></c:import>


		<div class="container-fluid">
		
		<h4>통계</h4>
		<div class="row">
                    <!-- Column -->
                    <div class="col-lg-8">
                        <div class="card">
                            <div class="card-body">
                                <div class="row">
                                  
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="card">
                            <div class="card-body">
                                
                            </div>
                            <div>
                                <hr class="mt-0 mb-0">
                            </div>
                           
                        </div>
                    </div>
                </div>
		
		
		</div>

		<c:import url="./adminFooter.jsp"></c:import>
	</div>
</body>
</html>