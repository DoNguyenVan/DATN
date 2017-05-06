<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@page session="true"%>

<html>
<head>
<title>Finance Management</title>
<meta  charset="UTF-8">
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<style type="text/css">
	div#Input_form {
	    padding: 2%;
	    margin-left: 3%;
	}
	div#form1 {
     background-color: darkorange;
     padding-bottom: 3%;
	}
	div#form2 {
      background-color: floralwhite;
      overflow: scroll;
      height: 350px;
	}
	div#Search_Form {
     padding: 2%;
     margin-left: 3%;
	}
	div#Seach_Display_Month,div#Seach_Display_Year {
     padding: 2%;
     margin-left: 15%;
     display: none;
	}
	#table {
     width: 70%;
     padding: 1%; 
     margin-left: 5%;
	}
</style>

</head>
<body >

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
				 <div class="row  border-bottom white-bg dashboard-header" id="form1">
					<form:form commandName="FinanceForm">
					  <div class="row" id="Input_form">
					    <div class="col-xs-2 col-sm-2 col-md-2"><label>statistics</label></div>
					    <div class="col-xs-3 col-sm-3 col-md-3">
					     <form:select path="month" class="form-control">
					       <form:options items="${mapMonths}"/>
					     </form:select>
					    </div>
					     <div class="col-xs-3 col-sm-3 col-md-3">
					     <form:select path="month" class="form-control">
					       <form:options items="${mapMonths}"/>
					     </form:select>
					    </div>
					    <div class="col-xs-2 col-sm-2 col-md-2"><input type="Submit" class="btn btn-info" value="Submit"/></div>					    
					  </div>
				
				   <div class="row" id="Search_Form">
				      <div class="col-xs-2 col-sm-2 col-md-2"><label>Search By: </label></div>
				      <div class="col-xs-2 col-sm-2 col-md-2">
				       <input type="radio" name="searchValue" value="month" id="radio_month"><label>Month</label><br>
  					   <input type="radio" name="searchValue" value="year" id="radio_year"><label>Year</label> 
				      </div>
				      <!-- Search From month to month -->
				      <div class="col-xs-11 col-sm-11 col-md-11" id="Seach_Display_Month">
				       <div class="col-xs-1 col-sm-1 col-md-1"><label>From:</label></div> 
				         <div class="col-xs-3 col-sm-3 col-md-3">
				           <form:select path="month1" class="form-control">
				           <form:options items="${mapMonths}"/>
				          </form:select>
				         </div>
				         <div class="col-xs-1 col-sm-1 col-md-1"><label>To:</label></div> 
				         <div class="col-xs-3 col-sm-3 col-md-3">				         
				           <form:select path="month2" class="form-control">
				           <form:options items="${mapMonths}"/>
				          </form:select>
				         </div>
				         <div class="col-xs-1 col-sm-1 col-md-1"><label>Year:</label></div> 
				         <div class="col-xs-2 col-sm-2 col-md-2">				           
				           <form:select path="month" class="form-control">
				           <form:options items="${mapMonths}"/>
				          </form:select>
				         </div>			         
				      </div>
				       <!-- Search From Year To Year -->
				      <div class="col-xs-11 col-sm-11 col-md-11" id="Seach_Display_Year">
				       <div class="col-xs-1 col-sm-1 col-md-1"><label>From:</label></div> 
				         <div class="col-xs-3 col-sm-3 col-md-3">
				           <form:select path="month1" class="form-control">
				           <form:options items="${mapMonths}"/>
				          </form:select>
				         </div>
				         <div class="col-xs-1 col-sm-1 col-md-1"><label>To:</label></div> 
				         <div class="col-xs-3 col-sm-3 col-md-3">				         
				           <form:select path="month2" class="form-control">
				           <form:options items="${mapMonths}"/>
				          </form:select>
				         </div>		         
				      </div>
				       <!-- End form -->
				   </div>
				    </form:form>  
				</div>
				 <div class="row  border-bottom white-bg dashboard-header" id="form2">
				   <div id="table">
				     <table class="table table-condensed"> 
				       <thead><tr>
				         <th>Hoat dong</th>
				         <th>Thu Tien</th>
				         <th>Chi tien</th>
				         <th>Lai suat</th>
				       </tr></thead>
				       <tbody>
				         <%int i=0; while(i<10){ %>
				          <tr> 
				            <td>thu chi</td>
				            <td> 4000$</td>
				            <td>2000$</td>
				            <td>200$</td>
				          </tr>
				          <%i++;} %>
				       </tbody>
				     </table>
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
	$("input#radio_month").click(function () {			
		 var x = document.getElementById('Seach_Display_Month');
		 var y = document.getElementById('Seach_Display_Year');
		 x.style.display ='block';
		 y.style.display ='none';
	});
	$("input#radio_year").click(function () {			
		 var x = document.getElementById('Seach_Display_Year');
		 var y = document.getElementById('Seach_Display_Month');
		 x.style.display ='block';
		 y.style.display ='none';
	});	


} );

</script>
</body>
</html>