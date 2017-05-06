<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<head>
<title>List Class</title>
<meta  charset="UTF-8">
<%@include file="_link.jsp" %>
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
</style>

</head>
<body >

    <div id="main">

		<div id="wrapper">
		      <!-- Side bar -->  
			    <%@include file="_siderbar.jsp" %>
			  <!--  end side bar --> 
			<div id="page-wrapper" class="white-bg" style="min-height:880px;">
			  
			   <!-- Header page -->
			   <%@include file="_header.jsp" %>
				<!-- end header page -->

				<!-- content -->   
				 	<div class="row  border-bottom white-bg dashboard-header">
						<div class="col-xs-12 col-sm-12 col-md-12">
							<div class="row">
							  <div class="col-xs-10 col-sm-10 col-md-10"><h2>Full List Of Class in the System </h2></div>
							  <div class="btn-add"><a href="${pageContext.request.contextPath}/addClass"><input type="button" value="Add Class" class="btn btn-warning"/></a></div>		
							 </div>	 
								<span id="message">${message}</span>
						</div>						
						<hr>
					</div>
		
					<div class="col-xs-12 col-sm-12 col-md-12">	
						<table id="classtable" class="table table-bordered" style="width: 100%;">
							<thead>
								<tr>
									<th>STT</th>
									<th>Class Name</th>  
									<th>Students</th>									
									<th>Start Date</th>
									<th>Max Seats </th>
									<th>Level</th>
									<th>Fee</th>
									<th>Action</th>      									 						                    
								</tr>
							</thead>
							<tbody>
							<%int x = 0; %>
							<c:forEach items="${listClass}" var="list">
							 	<tr>
							 	  <td><%=++x %></td>
							 	  <td>Class ${list.className}</td>
							 	  <td>0</td>							 	 
							 	  <td>${list.startDate}</td>
							 	   <td>${list.numberOfSeats}</td>
							 	  <td>${list.classLevel}</td>
							 	  <td>${list.fee} VND</td>
							 	  <td>
									<a href="${pageContext.request.contextPath}/SetSchedule?classid=${list.classId}" role="button" class="btn"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span> </a> 
									<a href="#delBoxConfirm${list.classId}" role="button" class="btn" data-toggle="modal"><span class="glyphicon glyphicon-erase" aria-hidden="true"></span> </a>
									 <!-- Start Confirm Delete Box --> 
										 <confirm-delete-box>
											<div class="modal fade" id="delBoxConfirm${list.classId}" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
												<div class="modal-dialog">
													<div class="modal-content">
														<div class="modal-header">									
														<h4 class="modal-title" id="myModalLabel">
															<h4>Confirm Delete Class Box</h4>
														</h4>
														</div>
														<div class="modal-body">
															<h2>Are You Sure ? </h2>
														</div>
														<div class="modal-footer"> 
														<form class="delete-form" action="${pageContext.request.contextPath}/doDeleteClass" method="get">
														   <input type="hidden" name="classid" value="${list.classId}" />
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
		
		 $('#message').css({"color":"#ff3300","font-size":"14px"}).fadeOut(5000);
	
	} );

</script>
</body>
</html>