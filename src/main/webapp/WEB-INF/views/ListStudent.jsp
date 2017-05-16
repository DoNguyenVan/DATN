<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Student</title>
<%@include file="_link.jsp" %>
<style type="text/css">
   .dataTables_wrapper{
	  margin-top: 2%;
	} 
	
	table thead tr{
	 background:  #ffcc00;
	}
	span.glyphicon.glyphicon-edit {
      letter-spacing: 1px;
    }
    span {
     font-size: 12;
     font-family: initial;
	}
 // import button css
 	#file{
	  display: none ;
	}
	label.glyphicon.glyphicon-plus {
      font-family: cursive;
	}
	.modal {
     display: none; /* Hidden by default */
     position: fixed; /* Stay in place */
     z-index: 1; /* Sit on top */
     padding-top: 100px; /* Location of the box */
     left: 0;
     top: 0;
     width: 100%; /* Full width */
     height: 100%; /* Full height */
     overflow: auto; /* Enable scroll if needed */
     background-color: rgb(0,0,0); /* Fallback color */
     background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
    }
    /* Modal Content */
	.modal-content {
	    background-color: cornsilk;
	    margin: auto;
	    padding: 20px;
	    border: 1px solid #888;
	    width: 50%;
	    height: 70%;
	}
	#content{
	  margin-top: 5%;
	  margin-bottom: 1%;
	  overflow: scroll;
	  height: 200px;
	}
	#close {
     margin-right: 25px;
     margin-top: 1px;
	}
	h4{
	  font-size: 40px;
	}
	
	div#mydeleteModal {
     width: 40em;
     background-color: ghostwhite;
	}
	
</style>

</head>
<body>

    <div id="main">

		<div id="wrapper">
		      <!-- Side bar -->  
			    <%@include file="_siderbar.jsp" %>
			  <!--  end side bar --> 
			<div id="page-wrapper" class="white-bg" style="min-height:920px;">
			  
			   <!-- Header page -->
			   <%@include file="_header.jsp" %>
				<!-- end header page -->

				<!-- content -->   
				 	<div class="row  border-bottom white-bg dashboard-header">
						<div class="col-xs-12 col-sm-12 col-md-12">
							<div class="row">
							  <div class="col-xs-10 col-sm-10 col-md-10"><h2>Full List Of Student in the System </h2></div>
							  <div class="btn-add"><a href="${pageContext.request.contextPath}/admin/addStudent"><input type="button" value="Add Student" class="btn btn-warning"/></a></div>									 						
							 </div>	
							<div class="row col-xs-2 col-sm-2 col-md-2">												 	   					 
	   					      <a href="#myModal">
	   					      <button class="btn btn-info btn-lg" type="button" >	        					
	        					 <label class="glyphicon glyphicon-plus">Import File	</label>				   
	   					      </button>	 
	   					      </a> 
	   				            <!-- The Modal -->
								<div id="myModal" class="modal">
								
								  <!-- Modal content -->
								  <div class="modal-content">
								   <form  id="import_Form" action="${pageContext.request.contextPath}/importClass" method="post"
								     enctype="multipart/form-data">
									   <div class="row">								  								    
									       <span class="glyphicon glyphicon-import">Import Data Form File</span>
									       <span class="close"><label class="glyphicon glyphicon-remove" id="close"></label></span>
									    </div>
									    <div class="row">
									       <button class="glyphicon glyphicon-plus btn-lg" id="btn-add-file" type="button"></button>
									    </div>
									    <div id="content"> 
									     <div class="row"><input name="file" type="file" class="form-control" /></div>	
									    </div>					
						               <div class="footer">
						                 <div class="row">
						                  <button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-import"></span>Submit</button>
						                  <button type="button" class="btn btn-primary"><label>Cancel<span class="glyphicon glyphicon-remove"></span></label></button>
						                 </div>
						               </div>
					               </form>
								  </div>
								
								</div> 	      
	   					  <!-- End model -->      	   					       					      							 
							</div>  
							<div class="col-xs-4 col-sm-4 col-md-4"> <span id="message">${message}</span></div>								
						</div>						
						<hr>
					</div>
		
					<div class="col-xs-12 col-sm-12 col-md-12">	
						<table id="studentTable" class="table table-bordered" style="width: 100%;">
							<thead>
							  <tr>
							    <th>STT</th>
							    <th>Full Name</th>							    
							    <th>Birthday</th>
							     <th>Gender</th>
							    <th>Phone</th>
							    <th>Email</th>
							    <th>Address</th>	
							    <th>School</th>						    
							    <th>Action</th>
							   </tr>
							</thead>
							<tbody>
							   <%int x=0; %>
								<c:forEach items="${listStudent}" var="list">
								 <tr>
									<td><%=++x %></td>
									<td>${list.fullName}</td>
									<td>${list.dateOfBirth}</td>
									<td>${list.gender}</td>
									<td>${list.phoneNumber}</td>
									<td>${list.email}</td>
									<td>${list.stAddress.city.cityName}</td>
									<td>${list.school.schoolName}</td>
									<td> 
									    <a href="#" role="button" class="btn"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> </a> 
										<a href="#delBoxConfirm${list.studentId}" role="button" class="btn" data-toggle="modal">
										 <span class="glyphicon glyphicon-erase" aria-hidden="true"></span></a>
									  <!-- Start Confirm Delete Box --> 
										 <confirm-delete-box>
											<div class="modal fade" id="delBoxConfirm${list.studentId}" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
												<div class="modal-dialog">
													<div class="modal-content" id="mydeleteModal">
														<div class="modal-header">									
														<h4 class="modal-title" id="myModalLabel">
															<h4>Confirm Delete Student Box</h4>
														</h4>
														</div>
														<div class="modal-body">
															<h2>Are You Sure ? </h2>
														</div>
														<div class="modal-footer"> 
														<form class="delete-form" action="${pageContext.request.contextPath}/admin/doDeleteStudent" method="post">
														   <input type="hidden" value="${list.studentId}"  name ="studentid" id ="studentid">														  
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
		
		$('#studentTable').DataTable( {
			"scrollX": true
		} );
		
	//	 $('#message').css({"color":"#009966","font-size":"13px"}).fadeOut(6000);
		 
		 $('button.btn.btn-info.btn-lg').click(function () {
				var modal = document.getElementById('myModal');
				modal.style.display = "block";
				$('span.close').click(function () {
					modal.style.display = "none";
				});
				$('button.btn.btn-primary').click(function () {
					modal.style.display = "none";
				});
				
				$('button#btn-add-file').click(function () {
					$('#content').append(' <div class="row"><input name="file" type="file" class="form-control" />');
				});							
		});
	
	} );

</script>
</body>
</html>