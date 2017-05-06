<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
    <link rel="stylesheet" href="css/login.css">
    <style> 		
		 label.error {
			 display:inline-block;
			 color:red;
			 width: 200px;
			 margin-left:30px;
			 font-style: italic;
			 font-size: 14px;
	  	 }  
	  	 .errorInvalid {				
			 display:inline-block;
			 color:red;
			 width: 200px;
			 margin-left:30px;
			 font-style: italic;
			 font-size: 12px;
			}
		.login-form .content .input {
		  width: 300px;
		}
    </style>
  <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
  <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
    
</head>

<body onload='document.loginForm.username.focus();'>

		<form name='loginForm' class="login-form" action="<c:url value='/login' />" method='POST' id="LoginForm">
			<div class="header">
				<h1>Welcome to this Site</h1>
				<span>Please, fill this form</span>
			<c:if test="${not empty error}">
			<div class="errorInvalid">${error}</div>
		</c:if>
				
			</div>

		<div class="content">
			<input name="username" type="text" class="input username" placeholder="Username"/>
			<div class="user-icon"></div>

			<input name="password" type="password" class="input password" placeholder="Password" />
			<div class="pass-icon"></div>	
				
		</div>

		<div class="footer">
			<input type="submit" class="button" value= "Login" />
		</div>

		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

		</form>
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
		
	});
 </script> 

</body>
</html>