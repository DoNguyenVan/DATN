<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@page session="true"%>
<%@page import="com.nguyenvando.Utils.MyAppUtil" %>
<html>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Student</title>
 <%@include file="_link.jsp" %>
 <style type="text/css">
	ul.nav.nav-tabs{
	 background-color: mintcream;
	 margin-top: 3%;
     padding: 3%;
	}
	div#profile,div#schedule,div#exam,div#schoolFee,div#map,div#notify,div#register {
	    background-color: ghostwhite;
	    min-height: 400px;
	}
	body{
	 background-color: lightslategray;
	}
	h3{
	  padding:2%;
	}
	.row label{
	  margin-left: 15%;
	  padding-bottom: 3%;
	}
	label{
	  color: black;
	  cursor: pointer;
	}
	
	label:hover{
	  text-decoration: underline;
	}
	
	#file_input_id{
	  display:none;
	}
	img {
    border: 1px solid #ddd;
    border-radius: 4px;
    padding: 5px;
    width: 150px;
	}
	
	img:hover {
	    box-shadow: 0 0 2px 1px rgba(0, 140, 186, 0.5);
	}
	#table_schedule th{
	  background-color: black;
	  color: white;
	  text-align: center;
	}
	#table_Score th{
	  background-color: darkviolet;
	  color: white;
	  text-align: center;
	}
	td{
	 text-align: center;
	}
	body {
      background:url(../img/background.jpg);
	}
	div#RE_CLASS {
     background-color: coral;
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
	  
	}
	#close {
     margin-right: 25px;
     margin-top: 1px;
	}
	h4{
	  font-size: 40px;
	}
	div#L_CLASS {
      background-color: darkslategrey;    
	}
	input#moneyValue,select#moneyValue {
	    width: 100%;
	    height: 10%;
	    border-bottom-left-radius: 5%;
	    border-radius: 5%;
    }
   input#submit_btn {
	    margin-top: 15%;
	    margin-bottom: 3%;	   
	    width: 150px;
	    margin-left: 30%;
	}
	input#btn-Submit_profile {
	    width: 220px;
	    margin-left: 29%;
	    margin-top: 4%;
	    margin-bottom: 4%; 
	}
	div#tittle-msg{
	  margin-left: 5%;
	  margin-bottom: 2%;
	}
	input#btnSubmit-UploadProfile,label#setImgProfile {
	  width: 60%;
	  margin-left: 34%;
	}
 </style> 
 <link rel="stylesheet" href="css/selectBox.css">
  <link rel="stylesheet" href="css/style.css">
 <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
 <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
</head>
<body >
<div class="container-fruit">
<div class="col-xs-offset-1 col-sm-offset-1 col-md-offset-1  col-xs-10 col-sm-10 col-md-10">
  
  <ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#profile"><span class="glyphicon glyphicon-user"> Profile</span></a></li>
    <li><a data-toggle="tab" href="#register"><span class="glyphicon glyphicon-registration-mark"> Register</span></a></li>
    <li><a data-toggle="tab" href="#schedule"><span class="glyphicon glyphicon-time"> Schedule</span></a></li>
    <li><a data-toggle="tab" href="#exam"><span class="glyphicon glyphicon-book"> Score/Exam</span></a></li>
    <li><a data-toggle="tab" href="#schoolFee" id="menuFee"><span class="glyphicon glyphicon-usd"> ShoolFee</span></a></li>
    <li><a data-toggle="tab" href="#notify"><span class="glyphicon glyphicon-globe"> Notify</span></a></li>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
    <li><a href="javascript:formSubmit()"><span class="glyphicon glyphicon-log-out"> Logout</span></a></li>
	</c:if>
  </ul>
	<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
  
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
 <div class="tab-content">
  <!-- Menu Profile -->
    <div id="profile" class="tab-pane fade in active">
     <div><h3><span class="glyphicon glyphicon-user"> PROFILE</span></h3></div>     
     <div class="row">
     <div id="tittle-msg">${message}</div>
    
     <form action="${pageContext.request.contextPath}/student/updateProfile" method="post">
       <div class="col-xs-8 col-sm-8 col-md-8">
			<form:form commandName="StudentForm" method="post" id="studentFormEdit"
			action="${pageContext.request.contextPath}/student/updateProfile">
				<div clas="row col-xs-12 col-sm-12 col-md-12">
					<div class="row col-xs-6 col-sm-6 col-md-6">
					  <div class="col-xs-4 col-sm-4 col-md-4">
					   <label class="pull-right">Full Name</label>
					  </div>
					  <div class="col-xs-8 col-sm-8 col-md-8">
					    <form:input type="hidden" class="form-control pull-left" path="studentId"/>
					    <form:input type="text" class="form-control pull-left" path="fullName"/>
					  </div>
					</div>
					<div class="row col-xs-6 col-sm-6 col-md-6">

					  <div class="col-xs-4 col-sm-4 col-md-4">
					   <label class="pull-right">Birthday</label>
					  </div>
					  <div class="col-xs-8 col-sm-8 col-md-8">
					    <form:input type="date" class="form-control pull-left" path="dateOfBirth"/>
					  </div>
				
					</div>			
				</div>
				<div clas="row col-xs-12 col-sm-12 col-md-12">
					<div class="row col-xs-6 col-sm-6 col-md-6">
					  <div class="col-xs-4 col-sm-4 col-md-4">
					   <label class="pull-right">Gender</label>
					  </div>
					  <div class="col-xs-8 col-sm-8 col-md-8">
					    <form:input type="text" class="form-control pull-left" path="gender"/>
					  </div>
					</div>
					<div class="row col-xs-6 col-sm-6 col-md-6">

					  <div class="col-xs-4 col-sm-4 col-md-4">
					   <label class="pull-right">Phone</label>
					  </div>
					  <div class="col-xs-8 col-sm-8 col-md-8">
					    <form:input type="number" class="form-control pull-left" path="phoneNumber"/>
					  </div>
				
					</div>			
				</div>
				<div clas="row col-xs-12 col-sm-12 col-md-12">
					<div class="row col-xs-6 col-sm-6 col-md-6">
					  <div class="col-xs-4 col-sm-4 col-md-4">
					   <label class="pull-right">Email</label>
					  </div>
					  <div class="col-xs-8 col-sm-8 col-md-8">
					    <form:input type="text" class="form-control pull-left" path="email"/>
					  </div>
					</div>
					<div class="row col-xs-6 col-sm-6 col-md-6">

					  <div class="col-xs-4 col-sm-4 col-md-4">
					   <label class="pull-right">City</label>
					  </div>
					  <div class="col-xs-8 col-sm-8 col-md-8">
					    <form:select  class="form-control pull-left" path="city">
					      <form:options items="${cityMap}"/>
					    </form:select>
					  </div>
				
					</div>			
				</div>
				<div clas="row col-xs-12 col-sm-12 col-md-12">
					<div class="row col-xs-6 col-sm-6 col-md-6">
					  <div class="col-xs-4 col-sm-4 col-md-4">
					   <label class="pull-right">username</label>
					  </div>
					  <div class="col-xs-8 col-sm-8 col-md-8">
					    <form:input type="text" class="form-control pull-left" path="userName" />
					  </div>
					</div>
					<div class="row col-xs-6 col-sm-6 col-md-6">

					  <div class="col-xs-4 col-sm-4 col-md-4">
					   <label class="pull-right">district</label>
					  </div>
					  <div class="col-xs-8 col-sm-8 col-md-8">
					    <form:select class="form-control pull-left" path="district">
					      <form:options items="${districtMap}"/> 
					    </form:select>
					  </div>
				
					</div>			
				</div>
				<div clas="row  col-xs-12 col-sm-12 col-md-12">
					<div class="row col-xs-6 col-sm-6 col-md-6">
					  <div class="col-xs-4 col-sm-4 col-md-4">
					   <label class="pull-right">School</label>
					  </div>
					  <div class="col-xs-8 col-sm-8 col-md-8">
					    <form:select  class="form-control pull-left" path="school" >
					      <form:options items="${schoolMap}"/>
					    </form:select>
					  </div>
					</div>
					<div class="row col-xs-6 col-sm-6 col-md-6">
			 				
					</div>			
				</div>						
		
				<div clas="row  col-xs-12 col-sm-12 col-md-12">
					 <input type="submit" value="Update" class="btn btn-info btn-lg" id="btn-Submit_profile"/>							
				</div>									
			  </form:form>	 
			  
			<form:form action="${pageContext.request.contextPath}/student/ChangePassword" method="post">
				<div clas="row  col-xs-12 col-sm-12 col-md-12">
				  <input name="username" class="form-control" type="hidden" id="userUpdate">
					<div class="row col-xs-5 col-sm-5 col-md-5">
					  <div class="col-xs-4 col-sm-4 col-md-4">
					   <label class="pull-right">OdlPass</label>
					  </div>
					  <div class="col-xs-8 col-sm-8 col-md-8">
					    <input name="oldPassword" class="form-control" type="password">
					  </div>
					</div>
					<div class="row col-xs-5 col-sm-5 col-md-5">
					  <div class="col-xs-4 col-sm-4 col-md-4">
					   <label class="pull-right">NewPass</label>
					  </div>
					  <div class="col-xs-8 col-sm-8 col-md-8">
					    <input name="newPassword" class="form-control" type="password">
					  </div>			 				
					</div>
					<div class="row col-xs-2 col-sm-2 col-md-2">	
					<input type="submit" value="Change Password" class="btn btn-info btn-sm" id="btn-Submit_Password"/>	
					</div>			
				</div>						
			
			</form:form>   
       </div>
     </form>
       <div class="col-xs-2 col-sm-2 col-md-2">
          <div class="row col-md-offset-1 col-xs-offset-1 col-sm-offset-1">
           <img src="${Student.stAccount.profileImgUrl}" style="width:150px">
          </div>
          <div class="row">
           <form id="login_frm" enctype="multipart/form-data" action="${pageContext.request.contextPath}/student/setProfileImg" method="post">
            <label for="file_input_id" id="setImgProfile" >
			<input type="file" id="file_input_id" name="file" accept="image/*">
				Profile Image
			</label>
			<input type="submit" value="Upload" width="70px" id="btnSubmit-UploadProfile"/>
           </form>
          </div>
          
       </div>
               
     </div>          
    </div>
   <!-- Menu Register -->
     <div id="register" class="tab-pane fade">
         <div><h3><span class="glyphicon glyphicon-registration-mark"> Register Class</span></h3></div>
         <div><h5 id="msgRegister">${msgRegister}</h5></div>
        <form:form commandName="RegisterForm" >
         <div class="row">
           <div class="col-xs-2 col-sm-2 col-md-2"><label>Choose Course</label></div>
	       <div class="col-xs-2 col-sm-2 col-md-2"> 
	         <form:select  class="dropdown" path="couseId">
	           <form:options items="${mapCourse}" />
			 </form:select>
		  </div>
		  <div class="col-xs-8 col-sm-8 col-md-8">
		    <div><label>Class Level</label></div>
		    <label>Beginner </label><input name="clevel" id="clevelB" type="radio" value="Beginner"/>
		    <label>Intermediate </label><input name="clevel"id="clevelI" type="radio" value="Intermediate"/>
		    <label>Advance </label><input name="clevel" id="clevelA"type="radio" value="Advance"/>
		  </div>	 
        </div>
        </form:form> 
        <div class="row">
         <div class="col-xs-offset-1 col-sm-offset-1 col-md-offset-1 col-xs-10 col-sm-10 col-md-10" id="CLASS_LIST">

					<!-- 			 
								<a href="#myModal" data-toggle="modal">
								  <div class="col-xs-4 col-sm-4 col-md-4">
									<div class="widget style1 navy-bg" id="C_CLASS">
									  <h4>className</h4>
										<ul class="list-unstyled m-t-md">
											<li class="pull-right">
											 <span>45</span>
											</li>
										</ul>
									</div>
								 </div>
								</a>
								<div class="modal" id="myModal" aria-hidden="true">
								  <div class="modal-content">
									   <div class="row">								  								    
									       <span class="glyphicon glyphicon-import">Register Class Form</span>
									       <span class="close"><label class="glyphicon glyphicon-remove" id="close"></label></span>
									    </div>

									    <div id="content"> 

									    </div>					
						               <div class="footer">
						                 <div class="row">
						                  <button type="button" class="btn btn-info"><span class="glyphicon glyphicon-upload"></span>Register</button>						                
						                 </div>
						               </div>
								  </div>
								</div>	
                       -->
		 </div>

        </div>
     </div>
   <!-- Menu Schedule --> 
    <div id="schedule" class="tab-pane fade">
      <div><h3>My Class Schedule</h3></div>
      <div class="row">
      <div class="col-xs-offset-1 col-sm-offset-1 col-md-offset-1 col-xs-10 col-sm-10 col-md-10">
	       <table class="table table-stripped" id="table_schedule">
	         <thead>
	           <tr>
	             <th colspan="2">Time</th>
	             <th>Monday</th>
	             <th>Tuesday</th>
	             <th>Wednesday</th>
	             <th>Thursday</th>
	             <th>Friday</th>
	             <th>Saturday</th>
	             <th>Sunday</th>
	           </tr>
	         </thead>
	         <tbody>
	        <c:forEach items="${ST_Schedule}" var="list">
	           <tr>
	             <td>${list.startTime} - ${list.endtTime}</td>
	             <td></td>
	             <c:choose>
				    <c:when test="${list.dayOfWeek =='Mon'}">
				       	 <td class="success">${list.className}</td>
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>		       
				    </c:when>  
				    <c:when test="${list.dayOfWeek =='Tue'}">
				       	 <td class="danger">FreeTime</td>
			             <td class="success">${list.className}</td>
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>	
				    </c:when>   
				    <c:when test="${list.dayOfWeek =='Wed'}">
				     	 <td class="danger">FreeTime</td>			           
			             <td class="danger">FreeTime</td>
			             <td class="success">${list.className}</td>
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>  
				    </c:when>  
				    <c:when test="${list.dayOfWeek =='Thu'}">
				      	 <td class="danger">FreeTime</td>			           
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>
			             <td class="success">${list.className}</td>
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>  
				    </c:when>   
				    <c:when test="${list.dayOfWeek =='Fri'}">
				      	 <td class="danger">FreeTime</td>			           
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>
			             <td class="success">${list.className}</td>
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td> 				       
				    </c:when>  
				    <c:when test="${list.dayOfWeek =='Sat'}">
				       	 <td class="danger">FreeTime</td>			           
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>
			             <td class="success">${list.className}</td>
			             <td class="danger">FreeTime</td>
				    </c:when>   
				    <c:otherwise>
				       	 <td class="danger">FreeTime</td>			           
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>
			             <td class="danger">FreeTime</td>	
			             <td class="danger">FreeTime</td>				        
				    </c:otherwise>
				</c:choose>
	           </tr>
	          </c:forEach> 	         
	         </tbody>      
	       </table>
      </div>
	</div>
    </div>
    
   <!-- Menu Exam --> 
    <div id="exam" class="tab-pane fade">
      <div><h3>My Class Schedule</h3></div>
      <div class="row">
      <div class="col-md-offset-1 col-md-10">
	       <table class="table table-bordered" id="table_Score">
	         <thead>
	           <tr>
	             <th rowspan="2">Mon Thi</th>
	             <th colspan="2" >Thi</th>          
	           </tr>
	           <tr>
	             <th> Lan thi</th>	
	             <th> Diem thi</th>	            
	           </tr>
	         </thead>
	         <tbody>
	         <% int i=0; while(i<10){ %>
	           <tr>
	             <td>8:00 AM</td>
	             <td>Class1</td>
	             <td>Class1</td>
	  
	           </tr>
	         <%i++;} %>
	        
	         </tbody>      
	       </table>
      </div>
	</div>
    </div>
    
     <!-- Menu Pay School Fee --> 
     <div id="schoolFee" class="tab-pane fade">
      <div><h3><span class="glyphicon glyphicon-bitcoin"> Choose Method to pay</span></h3></div>    
      <c:forEach items="${class_StList}" var="list">     
      	<div class="col-xs-3 col-sm-3 col-md-3">
			<div class="widget style1 navy-bg" id="L_CLASS">
				<h4>${list.className}</h4>
				<h5>Fee:&nbsp; ${list.fee} VND</h5>
				<h5 >Remain: &nbsp;${list.feeRemain} VND </h5> 				
					<ul class="list-unstyled m-t-md">
						<li class="pull-right">							
						</li>
					</ul>
			</div>
        </div>
      </c:forEach>
      
       <div class="btn-group btn-group-justified">
	    <a class="btn btn-primary"  id="btnMoney">InputMoney</a>
	    <a class="btn btn-primary"  id="btnCard">VisaCard</a>
	  </div>
	  
		<div class="modal" id="payMoney" aria-hidden="true">
		  <div class="modal-content">
			   <div class="row">								  								    
			       <span class="glyphicon glyphicon-import">Pay School Fee Form</span>
			       <span class="close" id="close"><label class="glyphicon glyphicon-remove"></label></span>
			    </div>
	
			    <div id="content">
			    <form >
			      <div class="row">
			        <div class="col-xs-4 col-sm-4 col-md-4">
			         	        
			          <select class="dropdown" name="classid" id="select_class">
			             <c:forEach items="${class_StList}" var="list">		
			              	 <option value="${list.classId}">${list.className}</option>
			             </c:forEach>
			          </select>
			           
			        </div>
			        <div class="col-xs-offset-1 col-sm-offset-1 col-md-offset-1"></div>
			        <div class="col-xs-4 col-sm-4 col-md-4"><label id="moneyHadPaid"></label></div>			        
			      </div>
			      <br><br>
			      <div class="row">			       
			        <div class="col-xs-5 col-sm-5 col-md-5">
			        	<label>Select payment times</label>
			        	<div id="selectBoxFee">			        	
			        	</div>
			      	<!-- <input type="number" id="moneyValue" class="col-md-4 form-control" /> -->	
			      		<input type="hidden" id="txtStudentId" value="${StudentForm.studentId}"/>
			        </div> 
			      </div>
				
	              <div class="footer">
	                <div class="row">
	                 <button type="button" id="sendMoneybtn" class="btn btn-info"><span class="glyphicon glyphicon-send"></span> SEND</button>						                
	                </div>
	              </div>
	           </form>   
		  </div>
		</div>	
		</div>
		<div class="modal" id="payCard" aria-hidden="true">
		  <div class="modal-content">
			   <div class="row">								  								    
			       <span class="glyphicon glyphicon-import">Pay School Fee Form</span>
			       <span class="close" id="close"><label class="glyphicon glyphicon-remove"></label></span>
			    </div>
	
			    <div id="content"> 
	
			    </div>					
	              <div class="footer">
	                <div class="row">
	                 <button type="button" class="btn btn-info"><span class="glyphicon glyphicon-send"></span> SEND</button>						                
	                </div>
	              </div>
		  </div>
		</div>	
	   
	     
     </div>
    <!-- Ending page --> 
</div>
</div>
</div>
<script type="text/javascript">
  $(document).ready(function(){ 
	

	  //select class for payment  VND
	  $('select#select_class').change(function () {
		  var data = $('select#select_class').val();
		  var myContextPath = "${pageContext.request.contextPath}";
			$.ajax({
				type : "GET",
				contentType : 'application/json; charset=UTF-8',
				dataType : 'json',
				url : myContextPath + "/myAPI/getClassFee?data=" + data,
				success : function(data) {
					
					if(data.classFeeRemain == data.classfee){
						var selectBoxfee = document.getElementById('selectBoxFee');
						 $(selectBoxfee).empty();
						 $(selectBoxfee).append(
							' <select class="col-md-4 form-control" id="moneyValue">'+
								'<option value="1">1st payment 30%</option>'+
								' <option value="2">2nd payment 30%</option>'+
								'<option value="3">3rd payment 40%</option>	'+
							'</select>'
						 );

					}
					if(data.classFeeRemain == data.classfee/100*70){
						var selectBoxfee = document.getElementById('selectBoxFee');
						 $(selectBoxfee).empty();
						 $(selectBoxfee).append(
							' <select class="col-md-4 form-control" id="moneyValue">'+								
								' <option value="2">2nd payment 30%</option>'+
								'<option value="3">3rd payment 40%</option>	'+
							'</select>'
						 );
					}
					if(data.classFeeRemain == data.classfee/100*30){
						var selectBoxfee = document.getElementById('selectBoxFee');
						 $(selectBoxfee).empty();
						 $(selectBoxfee).append(
							' <select class="col-md-4 form-control" id="moneyValue">'+								
								' <option value="2">2nd payment 30%</option>'+								
							'</select>'
						 );
					}
					if(data.classFeeRemain == data.classfee/100*60){
						var selectBoxfee = document.getElementById('selectBoxFee');
						 $(selectBoxfee).empty();
						 $(selectBoxfee).append(
							' <select class="col-md-4 form-control" id="moneyValue">'+	
								'<option value="1">1st payment 30%</option>'+
								' <option value="2">2nd payment 30%</option>'+								
							'</select>'
						 );
					}

					if(data.classFeeRemain == data.classfee/100*40){
						var selectBoxfee = document.getElementById('selectBoxFee');
						 $(selectBoxfee).empty();
						 $(selectBoxfee).append(
							' <select class="col-md-4 form-control" id="moneyValue">'+																
								'<option value="3">3rd payment 40%</option>	'+
							'</select>'
						 );
					}
					if(data.classFeeRemain == 0){
						var selectBoxfee = document.getElementById('selectBoxFee');
						 $(selectBoxfee).empty();
						 $(selectBoxfee).append('<h4>You already finished payment</h4>');
					}
					
				},
				done : function(e) {
					console.log("DONE");
				}
			});	    	  
	  });
	  
	  //Show erorrs message
	  $('#msgRegister').css({"color":"#009966","font-size":"13px"}).fadeOut(6000);	  
	  
	  $('button#sendMoneybtn').click(function () { // select box form pay fee by money
		
		  var data1 = $('select#select_class').val();
	   //   var data2 = $('input#moneyValue').val();	
	      var data2 = $('select#moneyValue').val();
	       alert(data2);
	      var data3 = $('input#txtStudentId').val();
	      if(data2 < 0 || null==data1 || null==data3){
	    	  alert("Bạn chưa chọn lớp cần đóng học phí");
	    	 if(data2 < 0) alert("Số tiền đóng học phí phải lớn hơn 10% tổng số tiền phải đóng")
	      }else{
	    	  var data4 = [data1,data2,data3];
				var myContextPath = "${pageContext.request.contextPath}";
				$.ajax({
					type : "GET",
					contentType : 'application/json; charset=UTF-8',
					dataType : 'json',
					url : myContextPath + "/myAPI/paidFeeByMoney?data=" + data4,
					success : function(data) {
						//alert(JSON.stringify(data));
						alert(data.msg);
					},
					done : function(e) {
						console.log("DONE");
					}
				});	    	  
	      }	      				  
	  });
	  
	  $('a#btnMoney').click(function () {
			var modal1 = document.getElementById('payMoney');
			var modal2 = document.getElementById('payCard');
			modal1.style.display = "block";
			modal2.style.display = "none";
			$('span#close').click(function () {
				modal1.style.display = "none";
			});
	  });
	  $('a#btnCard').click(function () {
			var modal1 = document.getElementById('payMoney');
			var modal2 = document.getElementById('payCard');
			modal1.style.display = "none";
			modal2.style.display = "block";
			$('span#close').click(function () {
				modal2.style.display = "none";
			});
	  });	  
	
	  // after get class of Soruce
		// filter via class Level
			  $("input#clevelI").click(function () {
					var data = $('input#clevelI').val();
					var data2 = $('select#couseId').val();
					var data3 = [data, data2];
					var myContextPath = "${pageContext.request.contextPath}";
					$.ajax({
						type : "GET",
						contentType : 'application/json; charset=UTF-8',
						dataType : 'json',
						url : myContextPath + "/myAPI/filterClass?data=" + data3,
						success : function(data) {
							//alert(JSON.stringify(data));
							var table_content = document.getElementById('CLASS_LIST');
							 $(table_content).empty();
							for (i in data) {
								if(data[i].numberSTofCLASS < 30){
								   	 $(table_content).append(
												'<a href="#myModal'+data[i].classId+'" data-toggle="modal">'+
												  '<div class="col-xs-4 col-sm-4 col-md-4">'+
													'<div class="widget style1 navy-bg" id="C_CLASS">'+
													  '<h4>'+data[i].className+'</h4>'+
														'<ul class="list-unstyled m-t-md">'+
															'<li class="pull-right">'+
															 '<span>'+data[i].numberSTofCLASS+'</span>'+
															'</li>'+
														'</ul>'+
													'</div>'+
												 '</div>'+
												'</a>'+
												'<div class="modal" name="form-modal" id="myModal'+data[i].classId+'" aria-hidden="true">'+
												  '<div class="modal-content">'+
													   '<div class="row">'+								  								    
													       '<span class="glyphicon glyphicon-import">Register Class Form</span>'+
													       '<a href="${pageContext.request.contextPath}/student/home"><span class="close"><label class="glyphicon glyphicon-remove" id="close"></label></span></a>'+
													    '</div>'+
													    '<form  action="${pageContext.request.contextPath}/student/register" method="post">'+
													    '<div id="content">'+ 
													   	  '<input type="hidden" name="classid" value="'+data[i].classId+'" />'+
													    '</div>'+					
										               '<div class="footer">'+
										                 '<div class="row">'+
										                  '<button type="submit" id="registerBTN" class="btn btn-info"><span class="glyphicon glyphicon-upload"></span>Register</button>'+						                
										                 '</div>'+
										               '</div>'+
										               '</form>'+
												 '</div>'+
												'</div>'										   			 
									  );
								}
								else{
								   	 $(table_content).append(
							      	    	  '<div class="col-xs-4 col-sm-4 col-md-4">'+ 
											     '<div class="widget style1 navy-bg" id="RE_CLASS">'+
												    '<h4>'+data[i].className+'</h4>'+
												      '<ul class="list-unstyled m-t-md">'+
												     	'<li class="pull-right">'+
														 '<span>'+data[i].numberSTofCLASS+'</span>'+
													    '</li>'+
												     '</ul>'+
											    '</div>'+
										     '</div>'		     	    
									  );										
								}
						    }
							
						},
						done : function(e) {
							console.log("DONE");
						}
					});
				});
			  $("input#clevelA").click(function () {
					var data = $('input#clevelA').val();
					var data2 = $('select#couseId').val();
					var data3 = [data, data2];
					var myContextPath = "${pageContext.request.contextPath}";
					$.ajax({
						type : "GET",
						contentType : 'application/json; charset=UTF-8',
						dataType : 'json',
						url : myContextPath + "/myAPI/filterClass?data=" + data3,
						success : function(data) {
							//alert(JSON.stringify(data));
							var table_content = document.getElementById('CLASS_LIST');
							 $(table_content).empty();
							for (i in data) {
								if(data[i].numberSTofCLASS < 30){
								   	 $(table_content).append(
												'<a href="#myModal'+data[i].classId+'" data-toggle="modal">'+
												  '<div class="col-xs-4 col-sm-4 col-md-4">'+
													'<div class="widget style1 navy-bg" id="C_CLASS">'+
													  '<h4>'+data[i].className+'</h4>'+
														'<ul class="list-unstyled m-t-md">'+
															'<li class="pull-right">'+
															 '<span>'+data[i].numberSTofCLASS+'</span>'+
															'</li>'+
														'</ul>'+
													'</div>'+
												 '</div>'+
												'</a>'+
												'<div class="modal" name="form-modal" id="myModal'+data[i].classId+'" aria-hidden="true">'+
												  '<div class="modal-content">'+
													   '<div class="row">'+								  								    
													       '<span class="glyphicon glyphicon-import">Register Class Form</span>'+
													       '<a href="${pageContext.request.contextPath}/student/home"><span class="close"><label class="glyphicon glyphicon-remove" id="close"></label></span></a>'+
													    '</div>'+
													    '<form  action="${pageContext.request.contextPath}/student/register" method="post">'+
													    '<div id="content">'+ 
													   	  '<input type="hidden" name="classid" value="'+data[i].classId+'" />'+
													    '</div>'+					
										               '<div class="footer">'+
										                 '<div class="row">'+
										                  '<button type="submit" id="registerBTN" class="btn btn-info"><span class="glyphicon glyphicon-upload"></span>Register</button>'+						                
										                 '</div>'+
										               '</div>'+
										               '</form>'+
												 '</div>'+
												'</div>'										   			 
									  );
								}
								else{
								   	 $(table_content).append(
							      	    	  '<div class="col-xs-4 col-sm-4 col-md-4">'+ 
											     '<div class="widget style1 navy-bg" id="RE_CLASS">'+
												    '<h4>'+data[i].className+'</h4>'+
												      '<ul class="list-unstyled m-t-md">'+
												     	'<li class="pull-right">'+
														 '<span>'+data[i].numberSTofCLASS+'</span>'+
													    '</li>'+
												     '</ul>'+
											    '</div>'+
										     '</div>'		     	    
									  );										
								}
						    }
							
						},
						done : function(e) {
							console.log("DONE");
						}
					});
				});
			  $("input#clevelB").click(function () {
					var data = $('input#clevelB').val();
					var data2 = $('select#couseId').val();
					var data3 = [data, data2];
					var myContextPath = "${pageContext.request.contextPath}";
					$.ajax({
						type : "GET",
						contentType : 'application/json; charset=UTF-8',
						dataType : 'json',
						url : myContextPath + "/myAPI/filterClass?data=" + data3,
						success : function(data) {
						//	alert(JSON.stringify(data));
							var table_content = document.getElementById('CLASS_LIST');
							 $(table_content).empty();
							for (i in data) {
								if(data[i].numberSTofCLASS < 30){
								   	 $(table_content).append(
												'<a href="#myModal'+data[i].classId+'" data-toggle="modal">'+
												  '<div class="col-xs-4 col-sm-4 col-md-4">'+
													'<div class="widget style1 navy-bg" id="C_CLASS">'+
													  '<h4>'+data[i].className+'</h4>'+
														'<ul class="list-unstyled m-t-md">'+
															'<li class="pull-right">'+
															 '<span>'+data[i].numberSTofCLASS+'</span>'+
															'</li>'+
														'</ul>'+
													'</div>'+
												 '</div>'+
												'</a>'+
												'<div class="modal" name="form-modal" id="myModal'+data[i].classId+'" aria-hidden="true">'+
												  '<div class="modal-content">'+
													   '<div class="row">'+								  								    
													       '<span class="glyphicon glyphicon-import">Register Class Form</span>'+
													       '<a href="${pageContext.request.contextPath}/student/home"><span class="close"><label class="glyphicon glyphicon-remove" id="close"></label></span></a>'+
													    '</div>'+
													    '<form  action="${pageContext.request.contextPath}/student/register" method="post">'+
													    '<div id="content">'+ 
													   	  '<input type="hidden" name="classid" value="'+data[i].classId+'" />'+
													    '</div>'+					
										               '<div class="footer">'+
										                 '<div class="row">'+
										                  '<button type="submit" id="registerBTN" class="btn btn-info"><span class="glyphicon glyphicon-upload"></span>Register</button>'+						                
										                 '</div>'+
										               '</div>'+
										               '</form>'+
												 '</div>'+
												'</div>'										   			 
									  );
								}
								else{
								   	 $(table_content).append(
							      	    	  '<div class="col-xs-4 col-sm-4 col-md-4">'+ 
											     '<div class="widget style1 navy-bg" id="RE_CLASS">'+
												    '<h4>'+data[i].className+'</h4>'+
												      '<ul class="list-unstyled m-t-md">'+
												     	'<li class="pull-right">'+
														 '<span>'+data[i].numberSTofCLASS+'</span>'+
													    '</li>'+
												     '</ul>'+
											    '</div>'+
										     '</div>'		     	    
									  );										
								}
						    }
							
						},
						done : function(e) {
							console.log("DONE");
						}
					});
				});

	  
		// seach by Course
		 $('select#couseId').change(function () {
				var data = $('select#couseId').val();				
				var myContextPath = "${pageContext.request.contextPath}";
					$.ajax({
						type : "GET",
						contentType : 'application/json; charset=UTF-8',
						dataType : 'json',						
						url : myContextPath + "/myAPI/student_Register_ClassList?data="+data,
						success : function(data) {
							//alert(JSON.stringify(data));
							var table_content = document.getElementById('CLASS_LIST');
							 $(table_content).empty();							 
							for (i in data) {
									if(data[i].numberSTofCLASS < 30){
									   	 $(table_content).append(	
													'<a href="#myModal'+data[i].classId+'" data-toggle="modal">'+
													  '<div class="col-xs-4 col-sm-4 col-md-4">'+
														'<div class="widget style1 navy-bg" id="C_CLASS">'+
														  '<h4>'+data[i].className+'</h4>'+
															'<ul class="list-unstyled m-t-md">'+
																'<li class="pull-right">'+
																 '<span>'+data[i].numberSTofCLASS+'</span>'+
																'</li>'+
															'</ul>'+
														'</div>'+
													 '</div>'+
													'</a>'+
													'<div class="modal" name="form-modal" id="myModal'+data[i].classId+'" aria-hidden="true">'+
													  '<div class="modal-content">'+
														   '<div class="row">'+								  								    
														       '<span class="glyphicon glyphicon-import">Register Class Form</span>'+
														       '<a href="${pageContext.request.contextPath}/student/home"><span class="close"><label class="glyphicon glyphicon-remove" id="close"></label></span></a>'+
														    '</div>'+
														    '<form  action="${pageContext.request.contextPath}/student/register" method="post">'+
														    '<div id="content">'+ 
														   	  '<input type="hidden" name="classid" value="'+data[i].classId+'" />'+
														    '</div>'+					
											               '<div class="footer">'+
											                 '<div class="row">'+
											                  '<button type="submit" id="registerBTN" class="btn btn-info"><span class="glyphicon glyphicon-upload"></span>Register</button>'+						                
											                 '</div>'+
											               '</div>'+
											               '</form>'+
													 '</div>'+
													'</div>'										   			 
										  );
												
									}
									else{
									   	 $(table_content).append(
								      	    	  '<div class="col-xs-4 col-sm-4 col-md-4">'+ 
												     '<div class="widget style1 navy-bg" id="RE_CLASS">'+
													    '<h4>'+data[i].className+'</h4>'+
													      '<ul class="list-unstyled m-t-md">'+
													     	'<li class="pull-right">'+
															 '<span>'+data[i].numberSTofCLASS+'</span>'+
														    '</li>'+
													     '</ul>'+
												    '</div>'+
											     '</div>'		     	    
										  );										
									}								
							}
						},
						done : function(e) {
							console.log("DONE");
						}				  	 			
			});

		});
		
	//update Student	
	$('input#btn-Submit_Password').click(function () {
		$('input#userUpdate').val($('input#userName').val());	
	});
	 $('#tittle-msg').css({"color":"#009966","font-size":"13px"}).fadeOut(6000);	
	 
	 $("select#city").change(function () {
			var data = $('select#city').val();
			var myContextPath = "${pageContext.request.contextPath}";
			$.ajax({
				type : "GET",
				contentType : 'application/json; charset=UTF-8',
				dataType : 'json',
				url : myContextPath + "/myAPI/ChangeDistrictList?data=" + data,
				success : function(data) {
					var district = document.getElementById('district');
					 $(district).empty();
					for (i in data) {
				   	 $(district).append('<option  value=' +i+ '>' + data[i] + '</option>');
				    }
					
				},
				done : function(e) {
					console.log("DONE");
				}
			});

	 }); 
					
  });

</script>
</body>
</html>