<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib  prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<head>
<title>Edit Student</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/selectBox.css">
<style type="text/css">
 label.error {
			 display:inline-block;
			 color:red;
			 width: 200px;
			 margin-left:30px;
			 font-style: italic;
			 font-size: 12px;
	  	 } 
  .row.col-xs-5.col-sm-5.col-md-5{
   margin-top: 2%;
   background-color: darkkhaki;
   margin-left: 5%;
  }  
  .row.col-xs-6.col-sm-6.col-md-6 {
    margin-top: 3%;
    padding: 2%;
    margin-left: 2%;
   }
   select#ClassBox {
    margin-top: 1%;
    margin-left: 15%;
   }
 
  //Register Class Css
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
	    height: 40%;
	     margin-top: 15%;
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
	
</style>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body onload="JavaScript:AutoRefresh(5000);">
 
    <div id="main">

		<div id="wrapper">
		      <!-- Side bar -->  
			    <%@include file="_siderbar.jsp" %>
			  <!--  end side bar --> 
			<div id="page-wrapper" class="white-bg" style="min-height: 760px">
			  
			   <!-- Header page -->
			   <%@include file="_header.jsp" %>
				<!-- end header page -->

				<!-- content -->  
				<span>${message}</span> 	
			<form:form commandName="StudentForm" method="post" id="studentFormEdit"
			action="${pageContext.request.contextPath}/admin/updateStudent">
				<div clas="row col-md-offset-1 col-xs-10 col-sm-10 col-md-10">
					<div class="row col-xs-5 col-sm-5 col-md-5">
					  <div class="col-md-4">
					   <label class="pull-right">Full Name</label>
					  </div>
					  <div class="col-md-8">
					    <form:input type="hidden" class="form-control pull-left" path="studentId"/>
					    <form:input type="text" class="form-control pull-left" path="fullName"/>
					  </div>
					</div>
					<div class="row col-xs-5 col-sm-5 col-md-5">

					  <div class="col-md-4">
					   <label class="pull-right">Birthday</label>
					  </div>
					  <div class="col-md-8">
					    <form:input type="date" class="form-control pull-left" path="dateOfBirth"/>
					  </div>
				
					</div>			
				</div>
				<div clas="row col-md-offset-1 col-xs-10 col-sm-10 col-md-10">
					<div class="row col-xs-5 col-sm-5 col-md-5">
					  <div class="col-md-4">
					   <label class="pull-right">Gender</label>
					  </div>
					  <div class="col-md-8">
					    <form:input type="text" class="form-control pull-left" path="gender"/>
					  </div>
					</div>
					<div class="row col-xs-5 col-sm-5 col-md-5">

					  <div class="col-md-4">
					   <label class="pull-right">Phone</label>
					  </div>
					  <div class="col-md-8">
					    <form:input type="number" class="form-control pull-left" path="phoneNumber"/>
					  </div>
				
					</div>			
				</div>
				<div clas="row col-md-offset-1 col-xs-10 col-sm-10 col-md-10">
					<div class="row col-xs-5 col-sm-5 col-md-5">
					  <div class="col-md-4">
					   <label class="pull-right">Email</label>
					  </div>
					  <div class="col-md-8">
					    <form:input type="text" class="form-control pull-left" path="email"/>
					  </div>
					</div>
					<div class="row col-xs-5 col-sm-5 col-md-5">

					  <div class="col-md-4">
					   <label class="pull-right">City</label>
					  </div>
					  <div class="col-md-8">
					    <form:select  class="form-control pull-left" path="city">
					      <form:options items="${cityMap}"/>
					    </form:select>
					  </div>
				
					</div>			
				</div>
				<div clas="row col-md-offset-1 col-xs-10 col-sm-10 col-md-10">
					<div class="row col-xs-5 col-sm-5 col-md-5">
					  <div class="col-md-4">
					   <label class="pull-right">username</label>
					  </div>
					  <div class="col-md-8">
					    <form:input type="text" class="form-control pull-left" path="userName"/>
					  </div>
					</div>
					<div class="row col-xs-5 col-sm-5 col-md-5">

					  <div class="col-md-4">
					   <label class="pull-right">district</label>
					  </div>
					  <div class="col-md-8">
					    <form:select class="form-control pull-left" path="district">
					      <form:options items="${districtMap}"/> 
					    </form:select>
					  </div>
				
					</div>			
				</div>
				<div clas="row col-md-offset-1 col-xs-10 col-sm-10 col-md-10">
					<div class="row col-xs-5 col-sm-5 col-md-5">
					  <div class="col-md-4">
					   <label class="pull-right">School</label>
					  </div>
					  <div class="col-md-8">
					    <form:select  class="form-control pull-left" path="school" >
					      <form:options items="${schoolMap}"/>
					    </form:select>
					  </div>
					</div>
					<div class="row col-xs-5 col-sm-5 col-md-5">
			 				
					</div>			
				</div>						
		
				<div clas="row col-md-offset-1 col-xs-10 col-sm-10 col-md-10">
					<div class="row col-xs-6 col-sm-6 col-md-6">
					  <div class="col-xs-12 col-sm-12 col-md-12">		
					   <input type="submit" value="Update" class="btn btn-info btn-lg" />
					   <a href="${pageContext.request.contextPath}/admin/listStudent" ><input type="button" value="Cancel" class="btn btn-warning btn-lg" /></a>				  					   					
					   <button type="button" class="btn btn-info btn-lg" data-toggle="collapse" data-target="#register">Register Class</button>
					  </div>					  
					</div>											
				</div>	
				<!-- Start Register Class -->		
				<div id="register" clas="row col-md-offset-1 col-xs-10 col-sm-10 col-md-10 collapse">
				  <div class="row col-xs-4 col-sm-4 col-md-4">
				    <form:select class="form-control" id="ClassBox" path="classId">				  
				      <form:options items="${ClassMap}"/>
				    </form:select>
				  </div>
				  <div class="row col-xs-6 col-sm-6 col-md-6" id="classList">
				  <c:forEach items="${stclassList}" var="list">
				  	<div class="col-xs-4 col-sm-4 col-md-4"> 
							<div class="widget style1 navy-bg">
								<h4>${list.className}</h4>							
							</div>	
						</div>
				 </c:forEach>
				  </div>
				</div>
				<!-- End Register Class -->										
			  </form:form>	
			  
			  <!-- Start Modal Register -->
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
		                 <a href="javascript:location.reload(true)">
		                  <button type="button" id="registerBtn" class="btn btn-info"><span class="glyphicon glyphicon-upload"></span>Register</button>						                
		                 </a>
		                 </div>
		               </div>
				  </div>
				</div>	
			  
			  <!-- End Modal Register -->
				<!-- end content-->  
	 
				<!-- footer page -->				
				<%@include file="_footer.jsp" %>
				<!-- end footer-->
			
			</div> 
		  
		</div>                    

    </div>
<script type="text/javascript">
 $(document).ready(function(){

	 $('select#ClassBox').change(function () {		
		var mymodal = document.getElementById('myModal');
		mymodal.style.display = 'block';
		$('span.close').click(function () {
			mymodal.style.display = "none";
		});				
	 });
	 
	$('button#registerBtn').click(function () {
		var stidValue = $('input#studentId').val();		
		var xValue = $('select#ClassBox').val(); 	
		var data = [stidValue, xValue];
		var myContextPath = "${pageContext.request.contextPath}";
		$.ajax({
			type : "GET",
			contentType : 'application/json; charset=UTF-8',
			dataType : 'json',
			url : myContextPath + "/myAPI/registerClassFormAddmin?data=" + data,
			success : function(data) {
				//alert(JSON.stringify(data));
				var mymodal = document.getElementById('myModal');
				// $(table_content).empty();
				
				alert("You has been registerd new Class");
				mymodal.style.display = "none";
				
			},
			 error: function() {
				 var mymodal = document.getElementById('myModal');
				 mymodal.style.display = "none";
		         alert("There was an error. Try again please!");
		      },
			done : function(e) {
				console.log("DONE");
			}
		});
	});	 
	
	 $('#message').css({"color":"#009966","font-size":"13px"}).fadeOut(6000);
	 $('.errorMessage').css({"color":"red","font-size":"20px"}).fadeOut(6000);
	 $('.msgerror').css({"color":"red","font-size":"12px"}).fadeOut(6000);
	 
	 $("select#city").change(function () {
			var data = $('select#city').val();
			var myContextPath = "${pageContext.request.contextPath}";
			$.ajax({
				type : "GET",
				contentType : 'application/json; charset=UTF-8',
				dataType : 'json',
				url : myContextPath + "/admin/ChangeDistrictList?data=" + data,
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
<!-- ----------------------------------------------------------------------- -->
</body>
</html>