<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Check Attendance</title>
<style type="text/css">
   .dataTables_wrapper{
	  margin-top: 2%;
	} 
	
	table thead tr{
	 background: #e6e600;
	}
	#classtable{
	 width: 100%;
	}
	label#tittle{
	 background-color: yellow;
	}
</style>
<%@include file="_link.jsp" %>
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
				 <div class="col-xs-12 col-sm-12 col-md-12">
				 	 <h4><label id="tittle">CLASS : ${ClassName}</label></h4>
				 </div>
				 <span id="message">${message}</span>
				</div>
					<div class="col-xs-12 col-sm-12 col-md-12">	
						<table id="classtable" class="table table-bordered" style="width: 100%;">
							<thead>
								<tr>
									<th>STT</th>
									<th>FullName</th>  							
									<th>Gender</th>
									<th>Birthday</th>									
									<th>Action</th>      									 						                    
								</tr>
							</thead>
							<tbody id="table_content">
							<%int x = 0; %>
							<c:forEach items="${StOfClass}" var="list">
							 	<tr>
							 	  <td><%=++x %></td>
							 	  <td>${list.fullName}</td>						 	 
							 	  <td>${list.gender}</td>
							 	  <td>${list.dateOfBirth}</td>
							 	  <td>
									<a href="#" role="button" class="btn"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> </a> 
									<a href="#delBoxConfirm${list.studentId}" role="button" class="btn" data-toggle="modal"><span class="glyphicon glyphicon-erase" aria-hidden="true"></span> </a> 
									 <!-- Start Confirm Delete Box --> 
										 <confirm-delete-box>
											<div class="modal fade" id="delBoxConfirm${list.studentId}" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
												<div class="modal-dialog">
													<div class="modal-content" id="deleteFormBox">
														<div class="modal-header">									
														<h4 class="modal-title" id="myModalLabel">
															<h4>Delete Student Form Class</h4>
														</h4>
														</div>
														<div class="modal-body">
															<h2>Click Yes to delete Student from this Class? </h2>
														</div>
														<div class="modal-footer"> 
														<form class="delete-form" action="${pageContext.request.contextPath}/admin/doDeleteSTFormClass" method="get">
														   <input type="hidden" name="studentid" value="${list.studentId}" />
															<button type="submit" class="btn btn-danger">
																Yes
															</button>
															<button type="button" class="btn btn-default" data-dismiss="modal">
																No
															</button> 
				
														</form>	
														</div>
												    </div>															
											    </div>	
											</div>	
									</confirm-delete-box>							 
							 	  </td>
							 	</tr>
							 </c:forEach>	
							</tbody>
							
						</table>
					</div>				
												
				<!-- end content-->  
	 
				<!-- footer page -->
				<%@include file="_footer.jsp" %>
				<!-- end footer-->
			
			</div> 
		  
		</div>                    

    </div>
<script type="text/javascript">
$(document).ready(function() {
	
	$('#classtable').DataTable( {
		"scrollX": true
	} );
	
	$('#message').css({"color":"#009966","font-size":"14px","margin-left":"3%"}).fadeOut(6000);
});	
</script>      
</body>
</html>