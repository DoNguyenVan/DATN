<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@page session="true"%>

<html>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
 </style> 
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
    <li><a data-toggle="tab" href="#schoolFee"><span class="glyphicon glyphicon-usd"> ShoolFee</span></a></li>
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
     <div><h3>PROFILE</h3></div>
     <div class="row">
     
       <div class="col-xs-6 col-sm-6 col-md-6">
	       <div class="row">
	         <div class="col-xs-3 col-sm-3 col-md-3"><label>Full Name:</label></div>
	         <div class="col-xs-8 col-sm-8 col-md-8 pull-left"><label>Nguyen Van AD n</label></div>
	       </div>
	       <div class="row">
	         <div class="col-xs-3 col-sm-3 col-md-3"><label>Gender:</label></div>
	         <div class="col-xs-8 col-sm-8 col-md-8 pull-left"><label>Name</label></div>
	       </div>
	       <div class="row">
	         <div class="col-xs-3 col-sm-3 col-md-3"><label>Birthday:</label></div>
	         <div class="col-xs-8 col-sm-8 col-md-8 pull-left"><label>09/03/1990</label></div>
	       </div>
	        <div class="row">
	         <div class="col-xs-3 col-sm-3 col-md-3"><label>Phone:</label></div>
	         <div class="col-xs-8 col-sm-8 col-md-8 pull-left"><label>0903199090</label></div>
	       </div>
	        <div class="row">
	         <div class="col-xs-3 col-sm-3 col-md-3"><label>Email:</label></div>
	         <div class="col-xs-8 col-sm-8 col-md-8 pull-left"><label>email@email.com</label></div>
	       </div>
	       <div class="row">
	         <div class="col-xs-3 col-sm-3 col-md-3"><label>City:</label></div>
	         <div class="col-xs-8 col-sm-8 col-md-8 pull-left"><label>Da Nang</label></div>
	       </div>
	       <div class="row">
	         <div class="col-xs-3 col-sm-3 col-md-3"><label>District:</label></div>
	         <div class="col-xs-8 col-sm-8 col-md-8 pull-left"><label>Hai Chau</label></div>
	       </div>
	       <div class="row">
	         <div class="col-xs-3 col-sm-3 col-md-3"><label>School:</label></div>
	         <div class="col-xs-8 col-sm-8 col-md-8 pull-left"><label>DH Bach KHoa</label></div>
	       </div>           
       </div>
     
       <div class="col-xs-6 col-sm-6 col-md-6">
          <div class="row col-md-offset-1 col-xs-offset-1 col-sm-offset-1">
           <img src="img_forest.jpg" alt="Forest" style="width:150px">
          </div>
          <div class="row">
           
            <label for="file_input_id">Set Profile Image</label>
			<input type="file" id="file_input_id">
           
          </div>
          
       </div>
               
     </div>          
    </div>
   <!-- Menu Register -->
     <div id="register" class="tab-pane fade">
         <div><h3>Register Class</h3></div>
         <div class="row">
           <div class="col-md-2"><label>Choose Course</label></div>
	       <div class="col-md-2"> 
	         <select  class="form-control">
			    <option>Mustard</option>
			    <option>Ketchup</option>
			    <option>Relish</option>
			 </select>
		  </div>
		  <div class="col-xs-8 col-sm-8 col-md-8">
		    <div class="col-xs-2 col-sm-2 col-md-2"><label>Level</label></div>
		    <div class="col-xs-2 col-sm-2 col-md-2"><input name="stlevel" type="radio" value="Beginner"><label>Be</label></div>
		    <div class="col-xs-2 col-sm-2 col-md-2"><input name="stlevel" type="radio" value="Intermediate"><label>In</label></div>
		    <div class="col-xs-2 col-sm-2 col-md-2"><input name="stlevel" type="radio" value="Advance"><label>Ad</label></div>
		  </div>	 
        </div>
        
        <div class="row">
         <div class="col-xs-offset-1 col-sm-offset-1 col-md-offset-1 col-xs-10 col-sm-10 col-md-10">
          <%int x =0;while(x<5){ %>
     		<div class="col-xs-4 col-sm-4 col-md-4"> 
				<div class="widget style1 navy-bg">
					<h4>
						Class
					</h4>
					<ul class="list-unstyled m-t-md">
						<li class="pull-right">
							<span>000</span>
						</li>
					</ul>
				</div>	
			</div>		 	
		 <%x++;} %>	
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
	         <%int i=0; while(i<5){ %>
	           <tr>
	             <td>8:00 AM</td>
	             <td></td>
	             <td>Class1</td>
	             <td>Class1</td>
	             <td>Class1</td>
	             <td>Class1</td>
	             <td>Class1</td>
	             <td>Class1</td>
	             <td>Class1</td>
	           </tr>
	         <%i++;} %>
	         </tbody>      
	       </table>
      </div>
	</div>
    </div>
    
   <!-- Menu Schedule --> 
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
	         <% while(i<10){ %>
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

    <div id="menu3" class="tab-pane fade">
      <h3>Menu 3</h3>
      <p>Eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.</p>
    </div>
  </div>

</div>
</div>
</body>
</html>