<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib  prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<head>
<title>Add Teacher</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">

<style type="text/css">
 label.error {
			 display:inline-block;
			 color:red;
			 width: 200px;
			 margin-left:30px;
			 font-style: italic;
			 font-size: 12px;
	  	 }  
 	
 

	.row.border-bottom.white-bg.dashboard-header {
	 background-color: lightyellow;
     overflow: auto;
     border: 1px solid #CCCCCC;
     margin: 1em 0;
	}
	.row{
	    padding-bottom: 2%;
	}
	h2{
	   padding-bottom: 2%;
	}
	#Skill_Form{
	 display: none;
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
					    <h2>ADD NEW TEACHER</h2> 
					    <span id="message">${message}</span>
					   <div class="col-xs-offset-1 col-sm-offset-1 col-md-offset-1 Form_Teacher">                       
					      <form:form  action ="${pageContext.request.contextPath}/doAddTeacher" enctype="multipart/form-data"
					        method="post" commandName="TeacherForm" id="addTeacherForm"> 
					        
					         <div class="row">
					           <div class="col-xs-1 col-sm-1 col-md-2"><label>Full Name*</label></div>
					           <div class="col-xs-3 col-sm-3 col-md-3">
					             <form:input path="fullName" class="form-control" placeholder="Nguyen Van A"/>
					           </div>
					           
					           <div class="col-xs-offset-1 col-sm-offset-1 col-md-offset-1 col-xs-1 col-sm-1 col-md-1"><label>City</label></div>
					           <div class=" col-xs-3 col-sm-3 col-md-3">
					             <form:select path="city" class="form-control">
					               <form:options items="${cityMap}"/>
					             </form:select>
					           </div>					           
					         </div>
					         
					         <div class="row">
					           <div class="col-xs-1 col-sm-1 col-md-2"><label>Birthday*</label></div>
					           <div class="col-xs-3 col-sm-3 col-md-3">
					             <form:input path="dateOfBirth" class="form-control" type="date"/>
					           </div>
					           
					           <div class="col-xs-offset-1 col-sm-offset-1 col-md-offset-1 col-xs-1 col-sm-1 col-md-1"><label>District</label></div>
					           <div class=" col-xs-3 col-sm-3 col-md-3">
					             <form:select path="district" class="form-control" id="district">
					               <form:options items="${districtMap}"/>
					             </form:select>
					           </div>					           
					         </div>
					         
					         <div class="row">
					           <div class="col-xs-1 col-sm-1 col-md-2"><label>Phone*</label></div>
					           <div class="col-xs-3 col-sm-3 col-md-3">
					             <form:input path="phoneNumber" class="form-control"  type="number" placeholder="number"  
					             onfocus="Focus(this)" onblur="Blur1(this)" />
					           </div>
					           
					           <div class="col-xs-offset-1 col-sm-offset-1 col-md-offset-1 col-xs-1 col-sm-1 col-md-1"><label>UserName*</label></div>
					           <div class=" col-xs-3 col-sm-3 col-md-3">
					             <form:input path="userName" class="form-control"/>
					           </div>					           
					         </div>
					         
					         <div class="row">
					           <div class="col-xs-1 col-sm-1 col-md-2"><label>Email*</label></div>
					           <div class="col-xs-3 col-sm-3 col-md-3">
					             <form:input path="email" class="form-control"  type="email" placeholder="email@email.com"  />
					           </div>
					           
					           <div class="col-xs-offset-1 col-sm-offset-1 col-md-offset-1 col-xs-1 col-sm-1 col-md-1"><label>password*</label></div>
					           <div class=" col-xs-3 col-sm-3 col-md-3">
					             <form:input path="password" class="form-control" type="password"/>
					           </div>					           
					         </div>
					        <button class="btn btn-link" type="button">-----------Click to Add Teacher Skill----------</button>
					        <!-- Skill Form Add -->
					        <div id="Skill_Form">
					          <div class="row">
					           <div class="col-xs-1 col-sm-1 col-md-2"><label>SkillName</label></div>
					           <div class="col-xs-3 col-sm-3 col-md-3">
					             <form:input path="skillName" class="form-control"  type="text" placeholder="text"  />
					           </div>
					           
					           <div class="col-xs-offset-1 col-sm-offset-1 col-md-offset-1 col-xs-1 col-sm-1 col-md-1"><label>Experiment</label></div>
					           <div class=" col-xs-3 col-sm-3 col-md-3">
					             <form:input path="experiment" class="form-control" type="text"/>
					           </div>					           
					         </div>
					          <div class="row">
					           <div class="col-xs-1 col-sm-1 col-md-2"><label>UrlCertificate</label></div>
					           <div class="col-xs-8 col-sm-8 col-md-8">
					             <form:input path="file" class="form-control"  type="file" name="file" />
					           </div>					           
					         </div>
					          <div class="row">
					           <div class="col-xs-1 col-sm-1 col-md-2"><label>Note</label></div>
					           <div class="col-xs-8 col-sm-8 col-md-8">
					             <form:textarea path="note" class="form-control"  type="text"/>
					           </div>					           
					         </div>

					        </div>
					          <table class ="table">						            
					            <tr>
						            <td colspan="4" >                                                  
									    <div class="form-inline">  
											<input type="submit" class="btn btn-info" value="Submit"> 
											<a href="${pageContext.request.contextPath}/listTeacher" class="btn btn-default" role="button">Cancel</a> 
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
		
	 $("#addTeacherForm").validate({ //check validate
			rules:{
				fullName :  "required",
				dateOfBirth :  "required",
				phoneNumber : "required",
				email: "required",
				userName: "required",
				password : "required"
			},
			messages:{				
				fullName: "Enter Teacher Name ",
				dateOfBirth:  "Enter your's birthday",
				phoneNumber: "Enter Phone Number",
				email : "Enter Student email ",				
				password: "Enter your's password"
			}
			
		});
	 $("select#city").change(function () {
			var data = $('select#city').val();
			var myContextPath = "${pageContext.request.contextPath}";
			$.ajax({
				type : "GET",
				contentType : 'application/json; charset=utf-8',
				dataType : 'json',
				url : myContextPath + "/ChangeDistrictList?data=" + data,
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

	 $("button.btn.btn-link").click(function () {
		 var x = document.getElementById('Skill_Form');
		    if (x.style.display === 'none') {
		        x.style.display = 'block';
		    } else {
		        x.style.display = 'none';
		    }
	 });
	 
	 $('#message').css({"color":"#009966","font-size":"13px"}).fadeOut(6000);
	 $('.errorMessage').css({"color":"red","font-size":"20px"}).fadeOut(6000);
	 $('.msgerror').css({"color":"red","font-size":"12px"}).fadeOut(6000);
	 
	 $("input#email").change(function() {
		 $('input#userName').val($('input#email').val());
		});
	});
 </script> 
 <!-- ------------------------------------------------------------- -->
<script type="text/javascript">
        function Focus(object) {
            object.value = "";
        }
 
        function Blur1(object) {
         var num = $('input#phoneNumber').val();
         var n = num.toString();
         if(n.startsWith("0")==false || (n.length<10 || n.length>12) ){ 
             object.value ="";
         }else{
               object.value = num ;
              }           
        }
        
</script>
<!-- ----------------------------------------------------------------------- -->
</body>
</html>