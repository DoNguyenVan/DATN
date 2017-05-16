<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@page session="true"%>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <title>Login</title>
 <%@include file="_link.jsp" %>
 <style type="text/css">
 	#Login_Form label.error {
		display:inline-block;
		color: yellow;
		width: 200px;			
		font-style: italic;
		font-size: 14px;
	}
 	label.error {
		display:inline-block;
		color: red;
		width: 200px;			
		font-style: italic;
		font-size: 14px;
    }
	.errorInvalid {				
		display:inline-block;
		color:yellow;
		margin-left:2%;
		width: 200px;
	   font-style: italic;
	   font-size: 12px;
	}	
	 div#Login_Form {	   
	  padding: 1% 2% 3%;
	  background-color: cornflowerblue;
	}
	div#CreateStudent_Form {
     background-color: azure;
	}
	div#STUDENT{
	  margin-top: 1%;
	  height: 80%;
	  margin-bottom: 1%;
	}
	div#row0,div#row1,div#row2,div#row3,div#row4,div#row5,div#row6{
	  margin-bottom: 2%;
	}
	div#row6{
	 margin-top: 5%;
	}
 </style> 
 <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
 <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
  
</head>

<body onload='document.loginForm.username.focus();'>
 <div class="container-fruit">
   <div class="row" id="Login_Form">
		<form name='loginForm' class="login-form" action="<c:url value='/login' />" method='POST' id="LoginForm">
		   <div class="col-xs-offset-5 col-sm-offset-5 col-md-offset-5 col-xs-7 col-sm-7 col-md-7 pull-right">
		      <div class="row">
		         <div class="col-xs-5 col-sm-5 col-md-5"><label>UserName</label></div>
		         <div class="col-xs-5 col-sm-5 col-md-5"><label>Password</label></div>
		      </div>
		  	  <div class="row">
		  	    <div class="col-xs-5 col-sm-5 col-md-5"><input name="username" type="text" class="form-control" placeholder="UserName"/></div>	  	   
		  	    <div class="col-xs-5 col-sm-5 col-md-5"><input name="password" type="password" class="form-control" placeholder="Password"/></div>
		  	    <div class="col-xs-2 col-sm-2 col-md-2"><input type="submit" class="btn btn-info" value="Login"/></div>
		  	  </div>
		  	  <div class="row">
		  	  
				 <div class="errorInvalid">${error}</div>
				
		  	  </div>		
	 		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		 	</div>	
		</form>
   </div>

   <div class="row" id="CreateStudent_Form">
      <div class="col-xs-offset-5 col-sm-offset-5 col-md-offset-5 col-xs-7 col-sm-7 col-md-7 pull-right">
          <div class="row">
            <div class="col-xs-10 col-sm-10 col-md-10"><h3>CREATE NEW STUDENT/PUPIL</h3></div> 
          </div>  
         <form:form id="addStudentForm" action ="${pageContext.request.contextPath}/do" method="post" commandName="STF" >                 
          <div id="STUDENT">
	          <div class="row">
	            <div class="col-xs-10 col-sm-10 col-md-10">
	              <h6>Choose Student or Pupil before create new account</h6>
				</div> 
	          </div>
          	  <div id="row0">
		          <div class="row">
		            <div class="col-xs-5 col-sm-5 col-md-5">
		              <input type="radio" name="st_type" value="pupil"><label>Pupil</label>
		              <input type="radio" name="st_type" value="student" checked="checked"><label>Student</label>
		            </div>
		          </div>	          
	         </div> 
           
	         <div id="row1">
		          <div class="row">
		            <div class="col-xs-5 col-sm-5 col-md-5"><label>Full Name</label></div>
		            <div class="col-xs-5 col-sm-5 col-md-5"><label>City</label></div>
		          </div>
		          <div class="row">
		            <div class="col-xs-5 col-sm-5 col-md-5">
		              <form:input path="fullName" class="form-control" placeholder="Full Name"/>
		            </div>
		            <div class="col-xs-5 col-sm-5 col-md-5">
		              <form:select path="city" class="form-control">
		               <form:options items="${mapCity}"/>
		              </form:select>
					</div>
	          	</div>	          
	         </div> 
	         <div id="row2">
		          <div class="row">
		            <div class="col-xs-5 col-sm-5 col-md-5"><label>Birthday</label></div>
		            <div class="col-xs-5 col-sm-5 col-md-5"><label>District</label></div>
		          </div>
		          <div class="row">
		            <div class="col-xs-5 col-sm-5 col-md-5">
		               <form:input path="dateOfBirth"  type="date" class="form-control" placeholder="mm/dd/yyyy"/>
		            </div>
		            <div class="col-xs-5 col-sm-5 col-md-5">
		              <form:select path="district" class="form-control">
		               <form:options items="${mapDistrict}"/>
		              </form:select>
					</div>
	              </div>
	         </div> 
	         <div id="row3">
		          <div class="row">
		            <div class="col-xs-5 col-sm-5 col-md-5"><label>Phone</label></div>
		            <div class="col-xs-5 col-sm-5 col-md-5"><label>Email</label></div>
		          </div>
		          <div class="row">
		            <div class="col-xs-5 col-sm-5 col-md-5">
		               <form:input path="phoneNumber"  type="number" class="form-control" placeholder="0978987876"/>
		            </div>
		            <div class="col-xs-5 col-sm-5 col-md-5">
		               <form:input path="email"  type="email" class="form-control" placeholder="email@email.com"/>
					</div>
	              </div>
	         </div> 
	        <div id="row4">
		          <div class="row">
		            <div class="col-xs-5 col-sm-5 col-md-5"><label>Gender</label></div>
		            <div class="col-xs-5 col-sm-5 col-md-5"><label>School</label></div>
		          </div>
		          <div class="row">
		            <div class="col-xs-5 col-sm-5 col-md-5">
		              <form:radiobuttons path="gender" items="${mapGender}"/>
		            </div>
		            <div class="col-xs-5 col-sm-5 col-md-5">
		             <form:select path="school" class="form-control">
		              <form:options items="${schoolMap}"/>
		             </form:select>
					</div>
	              </div>
	         </div> 
	         
	         <div id="row5">
		          <div class="row">
		            <div class="col-xs-5 col-sm-5 col-md-5"><label>UserName</label></div>
		            <div class="col-xs-5 col-sm-5 col-md-5"><label>Password</label></div>
		          </div>
		          <div class="row">
		            <div class="col-xs-5 col-sm-5 col-md-5">
		               <form:input path="userName"  type="text" class="form-control" placeholder="email@email.com"/>
		            </div>
		            <div class="col-xs-5 col-sm-5 col-md-5">
		               <form:input path="password" type="password" class="form-control" placeholder="***********"/>
					</div>
	              </div>
	         </div> 
	        <div id="row6"> 
	          <div class="row">
		         <div class="col-xs-5 col-sm-5 col-md-5">
		            <input type="submit" class="btn btn-success" value="CREATE NEW STUDENT" />
		         </div>
	           </div>
	        </div>  	          
          </div>   
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />    	 
        </form:form>
      </div>
   </div>

 </div>	
 <script type="text/javascript">
 $(document).ready(function(){
		$("#LoginForm").validate({
			rules:{
				username : "required",
				password :  "required"
			},
			messages:{				
				username: "enter username",
				password: "enter password"						
			}
			
		});
		
		$('.errorInvalid').fadeOut(5000);
		
		 $("#addStudentForm").validate({ //check validate
				rules:{
					fullName :  "required",
					dateOfBirth :  "required",
					phoneNumber : "required",
					email: "required",
					userName: "required",
					password : "required"
				},
				messages:{				
					fullName: "Enter Student Name ",
					dateOfBirth:  "Enter your's birthday",
					phoneNumber: "Enter Phone Number",
					email : "Enter Student email ",
					password: "Enter your's password"
				}
				
			});

		
		
	});
 </script> 

</body>
</html>