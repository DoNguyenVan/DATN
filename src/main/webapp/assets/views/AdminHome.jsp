<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Home Page</title>
<style type="text/css">
  .gray-bg, .bg-muted{
    background-color: red; 
  }
  #scrollSpace {  
    background-color: #e6ffff;
    width: 400px;
    height: 500px;
    overflow: scroll;
}
#scrollSpace h1{
 color: ;
}
#dashboard{
    background-color: #00ffcc;
    width: 500px;
    height: 400px;
    margin-bottom:1%;
}
</style>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
 <div id="main">

		<div id="wrapper">
		      <!-- Side bar -->  
			    <%@include file="_siderbar.jsp" %>
			  <!--  end side bar --> 
			<div id="page-wrapper" class="white-bg">
			  
			   <!-- Header page -->
			   <%@include file="_header.jsp" %>
				<!-- end header page -->
				<!-- content -->   
				<div class="row  border-bottom white-bg dashboard-header">
					
					<div class="col-xs-6 col-sm-6 col-md-6" id="dashboard">						
						 <h1 class="col-xs-12 col-md-12 col-sm-12">Dashboards</h1>						   
						   <a href="${pageContext.request.contextPath}/listClass">
								<div class="col-xs-6 col-sm-6 col-md-6" > 
									<div class="widget lazur-bg p-xl">
										<h2>Class</h2>
										<ul class="list-unstyled m-t-md">
											<li class="pull-right">
												Total ${classtotal}
											</li>
										</ul>
									</div>	
								</div>
							</a>
						 
						  <a href="${pageContext.request.contextPath}/listStudent">
							<div class="col-xs-6 col-sm-6 col-md-6 "> 
								<div class="widget lazur-bg p-xl">								
									<h2>Student</h2>
									<ul class="list-unstyled m-t-md">
										<li class="pull-right">
											Total ${stTotal}  
										</li>
									</ul> 
								</div>	
							 </div>	
						 </a>
					    <a href="${pageContext.request.contextPath}/listTeacher">
								<div class="col-xs-6 col-sm-6 col-md-6" > 
									<div class="widget lazur-bg p-xl">
										<h2>Teacher</h2>
										<ul class="list-unstyled m-t-md">
											<li class="pull-right">
												Total ${tcTotal}
											</li>
										</ul>
									</div>	
								</div>
							</a>
					    <a href="${pageContext.request.contextPath}/Finance">
								<div class="col-xs-6 col-sm-6 col-md-6" > 
									<div class="widget lazur-bg p-xl">
										<h2>Finance</h2>
										<ul class="list-unstyled m-t-md">
											<li class="pull-right">
												Total ${classtotal}
											</li>
										</ul>
									</div>	
								</div>
							</a>
						 	
					</div> 							 
								
						<div class="col-xs-offset-1 col-xs-6 col-sm-offset-2 col-sm-6 col-md-offset-1 col-md-6" id="scrollSpace">	
							<h1 class="col-xs-12 col-sm-12 col-md-12">Student Of class</h1>
							<c:forEach items="${classList}" var="list">							
							  <a href="${pageContext.request.contextPath}/CheckAttendace">
								<div class="col-xs-6 col-sm-6 col-md-6"> 
									<div class="widget style1 navy-bg">
										<h4>
											Class ${list.className}
										</h4>
										<ul class="list-unstyled m-t-md">
											<li class="pull-right">
												<span>${list.numberSTofCLASS }</span>
											</li>
										</ul>
									</div>	
								</div>
								</a>
							</c:forEach>								 
						</div>			
				 </div>  
				<!-- end content-->  
	 
				<!-- footer page -->
				<%@include file="_footer.jsp" %>
				<!-- end footer-->
			
			</div> 
		  
		</div>                    

    </div>
</body>
</html>