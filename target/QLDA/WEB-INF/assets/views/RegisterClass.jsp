<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib  prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<head>
<title>Register Class</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">

</head>
<body>
    <div id="main">

		<div id="wrapper">
		      <!-- Side bar -->  
			    <%@include file="_siderbar.jsp" %>
			  <!--  end side bar --> 
			<div id="page-wrapper" class="white-bg" style="min-height: 760px;min-width: 500px">
			  
			   <!-- Header page -->
			   <%@include file="_header.jsp" %>
				<!-- end header page -->
				<!-- start content -->   	
				<!-- end content-->  
	 
				<!-- footer page -->				
				<%@include file="_footer.jsp" %>
				<!-- end footer-->
			
			</div> 
		  
		</div>                    

    </div>
				
</body>
</html>