<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib  prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<head>
<title>Finance Management</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<style type="text/css">
	.well{
	  background-color: gold;
	  height: 100px;
	} 
	select#month{
	 width: auto;
	}
	label {
     font-size: larger;
     font-family: cursive;
	}
	div#table_Income {
     background-color: honeydew;
     height: 400px;
     overflow: scroll; 
	}
	#table{
	 width: 80%;
	}
	#table th {
	    background-color: chartreuse;
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
				<!-- start content -->   
				  <div class="row  border-bottom white-bg ">
				   <form:form commandName="FinanceForm">
				      <div class="well" >
				       <div class="row col-xs-12 col-sm-12 col-md-12">
				          <div class="col-xs-1 col-sm-1 col-md-1"><label>statistics</label></div>
					       <div class="col-md-offset-1    col-xs-12 col-sm-12 col-md-2">
						        <form:select class="form-control" name="monthSelect" path="month">
						          <form:options items="${mapMonths}"/>
						        </form:select>
					        </div>
					         <div class="col-md-offset-1  col-xs-2 col-sm-2 col-md-2">
						        <form:select class="form-control" name="monthSelect" path="month">
						          <form:options items="${mapMonths}"/>
						        </form:select>
					        </div>
					         <div class="col-md-offset-1 col-xs-2 col-sm-2 col-md-2">
					          <input type="submit" class="btn btn-info" value="Submit"> 
					         </div>
				       </div>
				      </div>
				   </form:form>
				   
				 <div id="table_Income" class="jumbotron">
				      <table class="col-md-offset-1 table table-bordered" id="table">
				       <thead>
				         <tr>
				           <th>Hoat dong</th>
				           <th>Thu</th>
				           <th>Chi</th>
				           <th>Lai Suat</th>
				        </tr>
				       </thead>
				       <tbody>
				         <%int i=0; while(i<10){%>
					        <tr>
					          <td>Thu hoc phi</td>
					          <td>5000$</td>
					          <td>300$</td>
					          <td>4600$</td>					        
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
				
</body>
</html>