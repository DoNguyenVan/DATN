<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/siderbar.css">
</head>
<body>

<!-- side bar -->
	<div class="navbar-default navbar-static-side">

	    <div class="sidebar-collapse">
			<ul class="nav metismenu" id="side-menu">
				<li class="nav-header">
					<div class="dropdown profile-element">
					    <span>
							<img alt="image" class="img-circle" src ="img/vando.jpg">
						</span>                       						            
					</div>
				</li>
				
				<li>
					<div class="menu-home"><a  href="${pageContext.request.contextPath}/admin/AdminManagement">Home Management</a></div>
				</li>                  
		  
				<li>
					<div class="menu-checked"><a  href="${pageContext.request.contextPath}/CheckAttendace">Check Attendance</a></div>
				</li>
		  
				<li>
					<div class="menu-student"><a  href="${pageContext.request.contextPath}/admin/listStudent">Student Management</a></div>
				</li>
   				
				<li>
				    <div class="menu-teacher"><a  href="${pageContext.request.contextPath}/admin/listTeacher">Teacher Management</a></div>                    
				</li>
				
				<li>
				    <div class="menu-class"><a  href="${pageContext.request.contextPath}/admin/listClass">Class Management</a></div>                    
				</li>				
				
				<li>
				    <div class="menu-finance"><a  href="${pageContext.request.contextPath}/admin/Finance">Finance Management</a></div>                    
				</li>
				
				<li>
				    <div class="menu-finance"><a  href="${pageContext.request.contextPath}/test/test1">TEST</a></div>                    
				</li>
				
		    
			</ul>
		</div>
					   
	</div>        
<!--  End side bar --> 
</body>
</html>