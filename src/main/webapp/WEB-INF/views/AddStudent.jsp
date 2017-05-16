<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib  prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<head>
<title>Add Student</title>
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
	 .col-xs-offset-1.col-sm-offset-1.col-md-offset-1.col-xs-10.col-sm-10.col-md-10 {
	    background-color: lightyellow;
	   }	
	.row.border-bottom.white-bg.dashboard-header {
	    background-color: lightgoldenrodyellow;
	}  	
	input#fullName,input#dateOfBirth,input#phoneNumber,input#email,select#district,select#city,
	select#classList,input#userName,input#password,select#school{
	 width: 200px;
	 height:35px; 
	} 
	div#Group-Level {
    font-size: 0.6em;
    font-family: initial;
    font-style: inherit;
	}
	div#Group-Level .radiobtn{
	 display: inline-block;
     width: 1em;
	 height: 1em;
	 margin-right: 0.05em;
	 margin-left: 0.75em;
	 transition: 0.5s ease all; 
	}
	.row.border-bottom.white-bg.dashboard-header {
    overflow: auto;
    border: 1px solid #CCCCCC;
    margin: 1em 0;
	}
</style>
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

				<!-- content -->   	
					<div class="row  border-bottom white-bg dashboard-header">
					  <div class="row"> 
					   <div class="col-xs-4 col-sm-4 col-md-4"><h2>ADD NEW STUDENT</h2></div> 
					   <div class="col-xs-offset-3 col-sm-offset-3 col-md-offset-3 col-md-2"><label>Search by Course:</label></div>
					   <div class="col-xs-5 col-sm-5 col-md-5 dropdown">
					     <form:form commandName="search_course">
							<form:select path="couseId" class="dropdown-select" >
							   <form:options items="${listCourse}" />
							</form:select>								
						 </form:form>

					   </div>
					   </div>
					   <span id="message">${message}</span>

			    
					    <div class="col-xs-offset-1 col-sm-offset-1 col-md-offset-1 col-xs-10 col-sm-10 col-md-10">
					                                 
					     <form:form  action ="${pageContext.request.contextPath}/admin/doAddStudent" 
					        method="post" commandName="StudentForm" id="addStudentForm"> 
					          <table class ="table">
					             <tr>   			                   
				                    <td>
				                       <div class="form-inline">
				                          <div class="form-group">  
				                             <label>Full Name*</label>
				                          </div>
										</div>
									 </td>  									      
				                    <td> 
								      <div class="form-inline" >
			                            <div class="form-group">
			                                <form:input path="fullName" class="form-control" type="text" placeholder="Nguyễn Văn A" />			                                
			                            </div>
									  </div>
									</td> 
									<td>
										<div class="form-inline">                                   
											<div class="form-group">                              
												<label>Gender</label>
											</div>
										</div>  
									</td>
										<td>
											<div class="form-inline">                                   
												<div class="form-group" id="Group-Level">                              
													<form:radiobutton path="gender" value="Female" label="Nữ" class="radiobtn"/>
													<form:radiobutton path="gender" value="Male" label="Nam" class="radiobtn"/>
												</div>
											</div>  
										</td>                                                                   										                                                                      
					               </tr>
									<tr>
										<td>
											<div class="form-inline">
											  <div class="form-group">                               
												  <label >DateOfBirth*</label>
											  </div>
											</div>
										</td>
									    <td>                 
										 <div class="form-inline">
										    <div class ="form-group">
												<div class="form-inline" >
													<form:input path="dateOfBirth" class="form-control" placeholder="mm/dd/yyyy" type="date" />
													<label class="msgerror">${timefuture}</label>
												 </div>
											 </div>
										 </div> 
									   </td>	
										<td>
										   <div class="form-inline">                                   
											  <div class="form-group">                              
												<label>Student Level</label>
											  </div>
										   </div>  
										</td>
										<td>
											<div class="form-inline">                                   
												<div class="form-group" id="Group-Level">                              
													<form:radiobutton path="stLevel" value="Beginner" label="Beginner" class="radiobtn"/>
													<form:radiobutton path="stLevel" value="Intermediate" label="Intermediate" class="radiobtn"/>
													<form:radiobutton path="stLevel" value="Advance" label="Advance" class="radiobtn"/>
												</div>
											</div>  
										</td> 									   
									</tr>
									<tr>
										<td>
										    <div class="form-inline">                                   
												<div class="form-group">                              
												<label>Phone Number*</label>
												</div>
											</div>                                 
										</td>
										<td>
											<div class="form-inline">                                   
												<div class="form-group">                              
													<form:input path="phoneNumber" class="form-control"  type="number" placeholder="number" 
													onfocus="Focus(this)" onblur="Blur1(this)" />
													 <label class="msgerror">${phoneformat}</label>
												</div>
											</div>                             
										 </td>																											
									   <td>
										  <div class="form-inline">                                   
											<div class="form-group">                              
												<label>Class* </label>
											</div>
										  </div>
										</td>	
										<td>                                                       
										   <div class="form-inline">
									         <div class="form-group">  
										         <form:select path="classOfST" class="form-control" id="classList">
										         	<form:options items="${ClassMap}"/>									         
										         </form:select>
										     </div>
											</div>                                   
										</td>
									</tr>
									<!--  Address Table -->
									<tr>									   
									   <td>
											<div class="form-inline">                                   
												<div class="form-group">                              
													<label>Email* </label>
												</div>
											</div>
										</td>
										<td>
											<div class="form-inline">                                   
												<div class="form-group">                              
													<form:input  path="email" class="form-control"  type="email" placeholder="email@gmail.com" />
												    <label class="msgerror">${emailformat}</label>
												</div>
											</div>                            
									   </td>
										 <td>
											<div class="form-inline">                                   
												<div class="form-group">                              
													<label>Account* </label>
												</div>
											</div>
										</td>
										<td>
											<div class="form-inline">                                   
												<div class="form-group">                              
													<form:input  path="userName" class="form-control"  type="text" placeholder="account" />																					    
												</div>
											</div>                            
									   </td>										   
									</tr>
					
									<tr>
									   <td>
											<div class="form-inline">                                   
												<div class="form-group">                              
													<label>City</label>
												</div>
											</div>
									   </td>
									   <td>
											<div class="form-inline">                                   
												<div class="form-group">    
												   <form:select path="city" class="form-control">
												     <form:options items="${cityMap}"/>
												   </form:select>                          													
												</div>
											</div>                                                                
									   </td>
										 <td>
											<div class="form-inline">                                   
												<div class="form-group">                              
													<label>Password* </label>
												</div>
											</div>
										</td>
										<td>
											<div class="form-inline">                                   
												<div class="form-group">                              
													<form:input  path="password" class="form-control"  type="password" placeholder="*******" />												    
												</div>
											</div>                            
									   </td>									   
									</tr>
					
									<tr>
									   <td>
											<div class="form-inline">                                   
												<div class="form-group">                              
													<label>District*</label>
												</div>
											</div>
									   </td>
									   <td>
											<div class="form-inline">                                   
												<div class="form-group">                              
													<form:select path="district" class="form-control" id="district">
													 <form:options items="${districtMap}"/>
													</form:select>
												</div>
											</div>                                                                
									   </td>
									   <td>
											<div class="form-inline">                                   
												<div class="form-group">                              
													<label>School</label>
												</div>
											</div>
									   </td>
									   <td>
											<div class="form-inline">                                   
												<div class="form-group">    
												   <form:select path="school" class="form-control">
												     <form:options items="${schoolMap}"/>
												   </form:select>                          													
												</div>
											</div>                                                                
									   </td>									   
									</tr>														                          
					               <!--  End Address Table -->      
					            
					            <tr><td colspan="4" >                                                  
							    <div class="form-inline">  
									<input type="submit" class="btn btn-info" value="Submit"> 
									<a href="${pageContext.request.contextPath}/admin/listStudent" class="btn btn-default" role="button">Cancel</a> 
								</div>  
								</td>
								</tr>
							</table>	 
					        </form:form>  
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
 $(document).ready(function(){
		
	 $("#addStudentForm").validate({ //check validate
			rules:{
				fullName :  "required",
				dateOfBirth :  "required",
				phoneNumber : "required",
				email: "required",
				gender: "required",
				password : "required"
			},
			messages:{				
				fullName: "Enter Student Name ",
				dateOfBirth:  "Enter your's birthday",
				phoneNumber: "Enter Phone Number",
				email : "Enter Student email ",
				gender : "Choose one ",
				password: "Enter your's password"
			}
			
		});
	 
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
	 
	$("input#stLevel1").click(function () {
		var data = $('input#stLevel1').val();
		var data2 = $('select#couseId').val();
		var data3 = [data, data2];
		var myContextPath = "${pageContext.request.contextPath}";
		$.ajax({
			type : "GET",
			contentType : 'application/json; charset=UTF-8',
			dataType : 'json',
			url : myContextPath + "/myAPI/changeClassList?data=" + data3,
			success : function(data) {
				var classList = document.getElementById('classList');
				 $(classList).empty();
				for (i in data) {
			   	 $(classList).append('<option  value=' +i+ '>' + data[i] + '</option>');
			    }
				
			},
			done : function(e) {
				console.log("DONE");
			}
		});
		
	});
	
	$("input#stLevel2").click(function () {
		var data = $('input#stLevel2').val();
		var myContextPath = "${pageContext.request.contextPath}";
		$.ajax({
			type : "GET",
			contentType : 'application/json; charset=UTF-8',
			dataType : 'json',
			url : myContextPath + "/myAPI/changeClassList?data=" + data,
			success : function(data) {
				var classList = document.getElementById('classList');
				 $(classList).empty();
				for (i in data) {
			   	 $(classList).append('<option  value=' +i+ '>' + data[i] + '</option>');
			    }
				
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	});
	
	$("input#stLevel3").click(function () {
		var data = $('input#stLevel3').val();
		var myContextPath = "${pageContext.request.contextPath}";
		$.ajax({
			type : "GET",
			contentType : 'application/json; charset=UTF-8',
			dataType : 'json',
			url : myContextPath + "/myAPI/changeClassList?data=" + data,
			success : function(data) {
				var classList = document.getElementById('classList');
				 $(classList).empty();
				for (i in data) {
			   	 $(classList).append('<option  value=' +i+ '>' + data[i] + '</option>');
			    }
				
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	});
	 	
	
	 $('#message').css({"color":"#009966","font-size":"13px"}).fadeOut(6000);
	 $('.errorMessage').css({"color":"red","font-size":"20px"}).fadeOut(6000);
	 $('.msgerror').css({"color":"red","font-size":"12px"}).fadeOut(6000);
	 
	 $("input#email").change(function() {
		 $('input#userName').val($('input#email').val());
		});
	 
		// seach by Course
	 $("select#couseId").change(function () {
			var data = $('select#couseId').val();
			var myContextPath = "${pageContext.request.contextPath}";
				$.ajax({
					type : "GET",
					contentType : 'application/json; charset=UTF-8',
					dataType : 'json',						
					url : myContextPath + "/myAPI/ChangeClassTable?data="+data,
					success : function(data) {
					//	alert(JSON.stringify(data));
						var classList = document.getElementById('classList');
						$(classList).empty();						 
						for (i in data) {
						   	 $(classList).append('<option  value=' +data[i].classId+ '>' + data[i].className + '</option>');
						    }
						$("input#stLevel1").click(function () {
							$(classList).empty();						 
							for (i in data) {
								if(data[i].classLevel=='Beginner')
							   	 $(classList).append('<option  value=' +data[i].classId+ '>' + data[i].className + '</option>');
							  }														
						});
						$("input#stLevel2").click(function () {
							$(classList).empty();						 
							for (i in data) {
								if(data[i].classLevel=='Intermediate')
							   	 $(classList).append('<option  value=' +data[i].classId+ '>' + data[i].className + '</option>');
							  }														
						});
						$("input#stLevel3").click(function () {
							$(classList).empty();						 
							for (i in data) {
								if(data[i].classLevel=='Advance')
							   	 $(classList).append('<option  value=' +data[i].classId+ '>' + data[i].className + '</option>');
							  }														
						});

						
					},
					done : function(e) {
						console.log("DONE");
					}				  			
		});

	}); 
	 
 });
 </script> 
 <!-- ------------------------------------------------------------- -->
<script type="text/javascript">
        function Focus(object) {
            object.value = "";
        }
 
        function Blur1(object) {
         var num = document.getElementById("phonenumber").value;
         var n = num.toString();
         if(n.startsWith("0")==false || (n.length<10 || n.length>12) ){ 
             object.value ="";
         }else{
               object.value = num ;
              }           
        }
        
        function Blur2(object) {
            var num2 = document.getElementById("phoneofparents").value;
            var str = num2.toString();
            if(str.startsWith("0")==false || (str.length<10 || str.length>12) ){ 
                object.value ="";
            }else{
                  object.value = num2 ;
                 }  
        }
</script>
<!-- ----------------------------------------------------------------------- -->
</body>
</html>