<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@page session="true"%>

<html>
<head>
<meta  charset="UTF-8">
<title>Set Schedule</title>
<%@include file="_link.jsp" %>
<style type="text/css">
  	  .successmsg{		    
	  }	 
  
</style>
</head>
<body>

    <div id="main">

		<div id="wrapper">
		      <!-- Side bar -->  
			    <%@include file="_siderbar.jsp" %>
			  <!--  end side bar --> 
			<div id="page-wrapper" class="gray-bg dashbard-1" style="min-height:800px;">
			  
			   <!-- Header page -->
			   <%@include file="_header.jsp" %>
				<!-- end header page -->

				<!-- content -->   
				<div class="row  border-bottom white-bg dashboard-header"> 
					    <div class="form-left">
							<div class="col-md-9">
								<h2>SET SCHEDULE FOR CLASS</h2>
								<label class="successmsg">${success}</label>
								<hr>
							</div>
								
							<div class="col-md-12"> 			  
								<div class="row">
									<div class="col-xs-2 col-sm-2 col-md-2">  
										<label for="focusedInput">Class Name*</label>
									</div>
									<div class ="col-xs-2 col-sm-2 col-md-2">
										<label style="background:yellow;">${className}</label>
									</div>					  	   
								</div>
								<br>
							</div>
				
							<form class="form-inline" method="get" action="doAddTime">							
								<div class="row col-xs-12 col-sm-12 col-md-12">                  
									<div class="col-xs-2 col-sm-2 col-md-1">
									  <label >Schedule* </label> 
									</div>
									<div class="col-xs-3 col-sm-3 col-md-3">    
										<select class="form-control" id="selectTime" name="selectTime">
										  <option>Mon|7PM-9PM</option>
										  <option>Mon|7:30AM-9:30AM</option>
										  <option>Tue|7PM-9PM</option>
										  <option>Tue|7:30AM-9:30AM</option>
										  <option>Wed|7PM-9PM</option>
										  <option>Wed|7:30AM-9:30AM</option>
										  <option>Thu|7PM-9PM</option>
										  <option>Thu|7:30AM-9:30AM</option>
										  <option>Fri|7PM-9PM</option>
										  <option>Fri|7:30AM-9:30AM</option>
										  <option>Sat|7PM-9PM</option>
										  <option>Sat|7:30AM-9:30AM</option>
										</select>
									</div>
									<div class="col-xs-4 col-sm-4 col-md-4">
										<input type="submit" class="btn btn-default" value="Add Time"> 
									</div>
								</div>
							</form>
							
						<div class="col-xs-10 col-sm-10 col-md-10">  
							<table class="table table-hover" style="width:40%;">
								<thead>
									<tr><th>Time</th></tr>       
								</thead>
								
								<tbody>
								  <c:forEach items="${schedule}" var="list">	
									<tr>
										<td>${list.dateOfWeek }</td>    
										<td>${list.startTime } - ${list.endTime}</td>          
										<td> <a data-toggle="modal" data-target="#deleteForm${list.timeId}">delete</a></td>  
										 <!-- Modal -->
										  <div class="modal fade" id="deleteForm${list.timeId}" role="dialog">
										    <div class="modal-dialog">
										    
										      <!-- Modal content-->
										      <div class="modal-content">
										        <div class="modal-header">
										          <button type="button" class="close" data-dismiss="modal">&times;</button>
										          <h4 class="modal-title">Delete Time Confirm</h4>
										        </div>
										        <div class="modal-body">
										          <p>Do you want to delete this Time?</p>
										        </div>
										        <div class="modal-footer">
										          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
											      <a href="doDeleteTime?timeid=${list.timeId}"><button type="button" class="btn btn-danger">Delete</button></a>
										        </div>
										      </div>
										      
										    </div>
										  </div>
										<!-- End Modal -->
										                                          
									</tr>
								</c:forEach>
								</tbody>
							  </table> 
							<a href="listClass" class="btn btn-info">OK</a> 
							</div>	
					    </div>
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
		 $('.successmsg').css({"color":"#009933","font-size":"14px"}).fadeOut(6000);
	} );
	
</script> 
</body>
</html>