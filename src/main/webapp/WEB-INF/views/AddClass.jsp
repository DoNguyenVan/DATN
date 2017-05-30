<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@page session="true"%>

<html>
<head>
<title>Add Class</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<style type="text/css">
 .error {
		 display:inline-block;
		 color:red;
		 width: 400px;
		
		 font-style: italic;
		 font-size: 12px;			
	  	 }  
	  .msgerror{		    
	  }	 
	  input#startdate,select#classLevel,input#className,input#numberOfSeats,
	  input#fee,select#fee,select#idCourse,select#teacherId{
		  width: 300px;
		  height: 40px;
	  }
	 #Groupbtn{
	  background-color: #ffffff;
	 }
 	.row.border-bottom.white-bg.dashboard-header {
    overflow: auto;
    border: 1px solid #CCCCCC;
    margin: 1em 0;
	}
 //Add course CSS
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
	    width: 60%;
	    height: 70%;
	}
	#content{
	  margin-top: 5%;
	  margin-bottom: 1%;	
	  height: 300px;
	}
	#close {
     margin-right: 25px;
     margin-top: 1px;
	}

  #content table td{
   padding: 2%;

 }

</style>
</head>
<body>
 
 <div id="main">

		<div id="wrapper">
		      <!-- Side bar -->  
			    <%@include file="_siderbar.jsp" %>
			  <!--  end side bar --> 
			<div id="page-wrapper" class="white-bg" style="min-height:720px;">
			  
			   <!-- Header page -->
			   <%@include file="_header.jsp" %>
				<!-- end header page -->
				<!-- content -->   
				<div class="col-xs-10 col-sm-10 col-md-10">   
			        <h2>ADD CLASS</h2>
			        <label class="errorMessage">${errorMessage}</label>	
			     </div>		    
			 	   <div class="col-xs-10 col-sm-10 col-md-10">   
			          <form:form action="${pageContext.request.contextPath}/admin/doAddClass" 
			                     method="post" commandName="classForm" id="addClassForm">
			              <table class="table table-striped" style="width: 60%;">			                
			                
			                 <tr class="Info">
			                    <td>
			                    <label>Please Choose One Course*</label>
			                   <a href="#courseModal"><input type="button" value="addCourse" class="btn btn-success btn-medium" id="btn-AddCourse"/></a>
			                    </td>			                   
			                     <td>
			                     	<div class="form-inline">
			                     	
			                              <div class="form-group">    			                                
				                               <form:select path="idCourse" class="form-control">
				                                  <form:options items="${Courses}" />
				                               </form:select>
			                              </div>
			                         </div>			                      			                     
			                    </td>
			                 </tr>
			                
			                 <tr class="active">
			                    <td><label>Class Name*</label></td>
			                    <td><label>Class Level</label></td>
			                 </tr>
			                  <tr>  
			                      <td>
			                      	<div class="form-inline">			                      	     
			                              <div class="form-group">			                                  
			                                  <form:input path="className" class="form-control" type="text" placeholder="classname"/>			                                 
			                                  <label class="msgerror">${validname}</label>
			                              </div>
			                          </div>			                      			                          
			                      </td>			                     
			                      <td>
			                      	<div class="form-inline">
			                              <div class="form-group">      
				                               <form:select path="classLevel" class="form-control">
				                                  <form:option value="a">--------- Select Class Level -------</form:option>
				                                  <form:options items="${classLevelList}" />
				                               </form:select>
			                              </div>
			                         </div>			                      
			                      </td>

			                  </tr>
			                  
			                  <tr class="active">
			                 	 <td> <label>Start Date*</label></td>
			                 	 <td><label>Class Fee</label></td>
			                  </tr>
			                  <tr>
			                     <td>
			                         <div class="form-inline">
			                              <div class="form-group">      
						                    <form:input path="startDate" class="form-control" placeholder="mm/dd/yyyy" type="date"/>
						                    <label class="msgerror">${timeover}</label>
			                              </div>
			                          </div>			                      
			                      </td>
			                      <td>
			                      	<div class="form-inline">
			                              <div class="form-group">      
				                              <form:input path="fee" class="form-control" type="text"/>
						                      <label class="msgerror">${feemsg}</label>
			                              </div>
			                         </div>			                      
			                      </td>
			                      
			                  </tr>
			                  
			                  <tr class="active">
			                 	 <td><label> Max Number Of Student*</label></td>
			                 	  <td><label>Teacher Of Class</label></td>
			                  </tr>
			                  <tr>
			                      <td>
			                      	  <div class="form-inline">
			                              <div class="form-group">      
						                    <form:input path="numberOfSeats" class="form-control" type="number" placeholder="number"  />
						                    <label class="msgerror">${maxNum}</label>
			                              </div>
			                          </div>			                      
			                      </td>
			                      <td>
			                      	<div class="form-inline">
			                              <div class="form-group">      
				                             <form:select path="teacherId" class="form-control">
				                                 <form:options items="${Teachers}" />
				                             </form:select>
			                              </div>
			                         </div>			                      
			                      </td>			                      
			                  </tr>		                 	                  			           		                  
			                  
			                  <tr id="Groupbtn">
			                      <td colspan="2">
			                          <div class="form-inline">
			                              <input type="submit" class="btn btn-info" value="Create">
			                              <a href="listClass" class="btn btn-default">Cancel</a>
			                          </div>
			                      </td>
			                  </tr>
			              </table>
			          </form:form>			        
			      </div>
			     
			   <!-- Start Form Add Course -->			  
					<div id="courseModal" class="modal">					
					  <!-- Modal content -->
					  <div class="modal-content">
					   <form  id="addCourseForm" action="${pageContext.request.contextPath}/admin/addCourse" method="post">
						   <div class="row">								  								    
						       <span class="glyphicon glyphicon-plus">Add new Course</span>
						       <span class="close"><label class="glyphicon glyphicon-remove" id="close"></label></span>
						    </div>
						   
						    <div id="content"> 
						    <table>
						      <tr class="active">
						        <td>Course Name*</td>
						        <td><input type="text" class="form-control" name="coursename" width="40px" /></td>
						      </tr>
						      <tr>
						        <td>timeLine*</td>
						        <td>
						        <select class="form-control" width="40px" name="timeline" >
						         <option value="3 months">3 Months</option>
						         <option value="6 months">6 Months</option>
						        </select>
						        </td>
						      </tr>
						      <tr>
						        <td>Note</td>
						        <td>
						        <textarea name="note" rows="5" cols="4" class="form-control">
						        </textarea>
						        </td>
						      </tr>
						    </table>
						    </div>					
			               <div class="footer">
			                 <div class="row">
			                  <button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-import"></span>Submit</button>			                  
			                 </div>
			               </div>
		               </form>
					  </div>								
					</div> 	      			     
  				<!--  End Form Add Course-->	
  				<!-- end content-->  
				<!-- footer page -->
				<%@include file="_footer.jsp" %>
				<!-- end footer-->
			
			</div> 
		  
		</div>                    

 </div>
<script type="text/javascript">
 $(document).ready(function(){
	
	 $("#addClassForm").validate({ //check validate
			rules:{
				nameOfClass : "required",
				startDate :  "required",
				numberOfSeats :  "required"
			},
			messages:{				
				nameOfClass: "Enter Name Of Class",
				startDate: "Enter Start Date ",
				numberOfSeats:  "Enter Max Seats Of class"
			}
			
		});
	 
	 $('#addCourseForm').validate({ //check validate
			rules:{
				coursename : "required",
				timeline :  "required"
			},
			messages:{				
				coursename: "Enter Course Name",
				timeline: "Enter Time Line "
			}					 
	 });
	 $('input#btn-AddCourse').click(function () {
			var modal = document.getElementById('courseModal');
			modal.style.display = "block";
			$('span.close').click(function () {
				modal.style.display = "none";
			});
	 });
	 
	 $('.errorMessage').css({"color":"red","font-size":"20px"}).fadeOut(6000);
	 $('.msgerror').css({"color":"red","font-size":"12px"}).fadeOut(6000);
	
	 $( "select#classLevel" ).change(function() {
		  var level =  $('select#classLevel').val();
		  var fees = [0,3000000, 4000000, 5000000];
          if("Beginner" == level){
			  $('input#fee').val(fees[1]);
		  }else if("Intermediate" == level){
			  $('input#fee').val(fees[2]);
		  }else if("Advance" == level){
			  $('input#fee').val(fees[3]);
		  }else{
			  $('input#fee').val(fees[0]);
		  }
		});
	});
 </script> 
 
</body>
</html>