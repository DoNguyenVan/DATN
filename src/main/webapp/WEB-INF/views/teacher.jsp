<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@page session="true"%>

<html>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <title>Teacher</title>
 <style type="text/css">
	ul.nav.nav-tabs{
	 background-color: mintcream;
	 margin-top: 3%;
     padding: 3%;
	}
	div#profile,div#schedule,div#exam,div#schoolFee,div#map,div#notify,div#check{
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
	    background-color: #fefefe;
	    margin: auto;
	    padding: 20px;
	    border: 1px solid #888;
	    width: 80%;
	}
	.widget.style1.navy-bg{
	  height: 8em;
	  background-color: darkolivegreen;
	}
 </style> 
 <link rel="stylesheet" href="css/style.css">
 <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
 <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
 <%@include file="_link.jsp" %>
</head>
<body>
<div class="container-fruit">
<div class="col-xs-offset-1 col-sm-offset-1 col-md-offset-1  col-xs-10 col-sm-10 col-md-10">
  
  <ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#profile"><span class="glyphicon glyphicon-user"> Profile</span></a></li>
    <li><a data-toggle="tab" href="#check"><span class="glyphicon glyphicon-check"> CheckAttendance</span></a></li>
    <li><a data-toggle="tab" href="#schedule"><span class="glyphicon glyphicon-time"> Schedule</span></a></li>
    <li><a data-toggle="tab" href="#salary"><span class="glyphicon glyphicon-usd"> Salary</span></a></li>
    <li><a data-toggle="tab" href="#map"><span class="glyphicon glyphicon-map-marker"> Map/Direction</span></a></li>
    <li><a data-toggle="tab" href="#notify"><span class="glyphicon glyphicon-globe"> Notify</span></a></li>
  </ul>

 <div class="tab-content">
  <!-- Menu Profile -->
    <div id="profile" class="tab-pane fade in active">
        <div class="row">
         <div class="col-md-4"><h3>PROFILE</h3></div>
         <div class="col-md-8"><h4>Day la ngay thang nam hien tai</h4></div> 
        </div>    

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
     <div id="check" class="tab-pane fade">
        <div class="row">
         <div class="col-md-4"><h3>Check Attendance</h3></div>
         <div class="col-md-8"><h4>Day la ngay thang nam hien tai</h4></div> 
        </div>
         <div class="row">
           <div class="col-md-2"><label>Choose Course</label></div>
	       <div class="col-md-3"> 
	         <select  class="form-control">
			    <option>Mustard</option>
			    <option>Ketchup</option>
			    <option>Relish</option>
			 </select>
		  </div> 
        </div>
        
        <div class="row">
         <div class="col-xs-offset-1 col-sm-offset-1 col-md-offset-1 col-xs-10 col-sm-10 col-md-10">
          <a href="#myModal" id="myBtn">
             <% int x=0; while(x<5){%>
	          <div class="col-xs-4 col-sm-4 col-md-4"> 
				 <div class="widget style1 navy-bg">
					<h4>Class Name</h4>
						<ul class="list-unstyled m-t-md">
							<li class="pull-right">
								<span>000</span>
							</li>
						</ul>
				</div>	
			 </div>
			<%x++;} %> 
          </a>
		  </div>
        </div>
        
       <!-- The Modal -->
		<div id="myModal" class="modal">
		
		  <!-- Modal content -->
		  <div class="modal-content">
		    <span class="close">&times;</span>
		   		<table id="studentTable" class="table table-bordered" style="width: 100%;">
					<thead>
					  <tr>
					    <th>STT</th>
					    <th>Full Name</th>							    
					    <th>Birthday</th>
					    <th>Phone Number</th>
					    <th>Email</th>							    
					    <th>Action</th>
					   </tr>
					</thead>
					<tbody>
					  <tr>
					    <td>dads</td>
					    <td>dadsa</td>
					    <td>dadas</td>
					    <td>dadsa</td>
					    <td>dadas</td>
					    <td>dadgfd</td>
					  </tr>
					</tbody>
							
				</table>

		  </div>
		
		</div> 
        
        
        
     </div>
   <!-- Menu Schedule --> 
    <div id="schedule" class="tab-pane fade">
       <div class="row">
         <div class="col-md-4"><h3>My Class Schedule</h3></div>
         <div class="col-md-8"><h4>Day la ngay thang nam hien tai</h4></div> 
        </div>
      
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
    
    <div id="menu3" class="tab-pane fade">
      <h3>Menu 3</h3>
      <p>Eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.</p>
    </div>
  </div>

</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	
	$('#studentTable').DataTable( {
		"scrollX": true
	} );
	
	$('a#myBtn').click(function () {
		var modal = document.getElementById('myModal');
		modal.style.display = "block";
		$('span.close').click(function () {
			modal.style.display = "none";
		});
	});

} );

</script>
</body>
</html>