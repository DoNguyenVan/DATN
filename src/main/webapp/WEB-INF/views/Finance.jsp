<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  prefix="form"   uri="http://www.springframework.org/tags/form"  %>
<%@page session="true"%>

<html>
<head>
<title>Finance Management</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/selectBox.css">
<style type="text/css">
	div#Input_form {
	    padding: 2%;
	    margin-left: 3%;
	}
	div#form1 {
     background-color: beige;
     padding-bottom: 3%;
	}
	div#detail {	 
      background-color: floralwhite;
      overflow: scroll;
      height: 350px;
	}
	div#table_Detail{
	 display: none;
	}
	div#Search_Form {
     padding: 0.5%;
     margin-left: 1%;
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
	select#month1,select#month2,select#year,select#month3,select#month4 {
     width: 90%;
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
			      <form:form commandName="FinanceForm" method="post" action="${pageContext.request.contextPath}/admin/statistics">				
				   <div class="row" id="Search_Form">
				      <div class="col-xs-2 col-sm-2 col-md-2"><label>Search By: </label></div>
				      <div class="col-xs-2 col-sm-2 col-md-2">
				      	<c:choose>
							<c:when test="${searchValue =='month'}">
								<input type="radio" name="searchValue" value="month" id="radio_month" checked="checked"><label>Month</label><br>
				   				<input type="radio" name="searchValue" value="year" id="radio_year" ><label>Year</label> 
							</c:when>
							<c:otherwise>
								<input type="radio" name="searchValue" value="month" id="radio_month"><label>Month</label><br>
  					  			 <input type="radio" name="searchValue" value="year" id="radio_year" checked="checked"><label>Year</label> 
							</c:otherwise>
						</c:choose>

				      </div>
				      <div class="col-xs-2 col-sm-2 col-md-2"><input type="Submit" class="btn btn-info" value="Submit"/></div>	
				      <!-- Search From month to month -->
				      <div class="col-xs-12 col-sm-12 col-md-12" id="Seach_Display_Month">
				       <div class="col-xs-1 col-sm-1 col-md-1"><label>From:</label></div> 
				         <div class="col-xs-2 col-sm-2 col-md-2">
				           <form:select path="month1" class="dropdown">
				           <form:options items="${mapMonths}"/>
				          </form:select>
				         </div>
				         <div class="col-xs-1 col-sm-1 col-md-1"><label>To:</label></div> 
				         <div class="col-xs-2 col-sm-2 col-md-2">				         
				           <form:select path="month2" class="dropdown">
				           <form:options items="${mapMonths}"/>
				          </form:select>
				         </div>
				         <div class="col-xs-1 col-sm-1 col-md-1"><label>Year:</label></div> 
				         <div class="col-xs-3 col-sm-3 col-md-3">				           
				           <form:select path="year" class="dropdown">
				           <form:options items="${mapYear}"/>
				          </form:select>
				         </div>			         
				      </div>
				       <!-- Search From Year To Year -->
				      <div class="col-xs-11 col-sm-11 col-md-11" id="Seach_Display_Year">
				       <div class="col-xs-1 col-sm-1 col-md-1"><label>From:</label></div> 
				         <div class="col-xs-3 col-sm-3 col-md-3">
				           <form:select path="month3" class="dropdown">
				           <form:options items="${mapMonths}"/>
				          </form:select>
				         </div>
				         <div class="col-xs-1 col-sm-1 col-md-1"><label>To:</label></div> 
				         <div class="col-xs-3 col-sm-3 col-md-3">				         
				           <form:select path="month4" class="dropdown">
				           <form:options items="${mapMonths}"/>
				          </form:select>
				         </div>		         
				      </div>
				       <!-- End form -->
				   </div>
				    </form:form>  
				</div>
				<span id="error">${error}</span>
				<span id="errors">${errors}</span>
				
			<div class="row  border-bottom white-bg dashboard-header" id="detail">
			 <button class="glyphicon glyphicon-eye-open btn-lg" id="view_detail_btn" type="button"></button>
			  <div id="table_Detail">	 
				   <div id="table">
				     <table class="table table-condensed"> 
				       <thead><tr>
				         <th>STT</th>				         
				         <th>Thời Gian</th>
				         <th>Chi Tiết</th>
				         <th>Tiền Thu</th>
				         <th>Tiền Chi</th>				        
				       </tr></thead>
				       <tbody>
				         <%int i=0; %>
				         <c:forEach items="${statistic}" var="list">
				          <tr> 
				            <td><%=++i %></td>
				            <td>${list.date}</td>
				            <td>${list.activity}</td>	
				            <c:if test="${list.proceeds==0 || list.payouts ==0}"></c:if>
				            <c:if test="${list.proceeds!=0 && list.payouts ==0}">
				           	 <td>${list.proceeds}</td>
				           	 <td></td>
				            </c:if>	
				             <c:if test="${list.proceeds==0 && list.payouts !=0}">	
				               <td></td>
				               <td>${list.payouts}</td>
				             </c:if>		           
				          </tr>
				         </c:forEach> 
				       </tbody>
				     </table>
				   </div>
				</div>
				
				<div id="TK">
					<div class="row col-xs-offset-1 col-sm-offset-1 col-md-offset-1">	   
						<div class="col-xs-3 col-sm-3 col-md-3" > 
							<div class="widget lazur-bg p-xl">
								<h4>TỔNG THU</h4>
								<ul class="list-unstyled m-t-md">
									<li class="pull-left">
										${total1}  
									</li>
								</ul>
							</div>	
						</div>
						
						<div class="col-xs-3 col-sm-3 col-md-3" > 
							<div class="widget lazur-bg p-xl">
								<h4>TỔNG CHI</h4>
								<ul class="list-unstyled m-t-md">
									<li class="pull-left">
										${total2}
									</li>
								</ul>
							</div>	
						</div>
						
						<div class="col-xs-3 col-sm-3 col-md-3" > 
							<div class="widget lazur-bg p-xl">
								<h4>TIỀN LÃI</h4>
								<ul class="list-unstyled m-t-md">
									<li class="pull-left">
										${total3}
									</li>
								</ul>
							</div>	
						</div>
						
					</div>	   
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
   
	if($('input#radio_year').prop("checked") == true){
		 var x = document.getElementById('Seach_Display_Year');
		 var y = document.getElementById('Seach_Display_Month');
		 x.style.display ='block';
		 y.style.display ='none';
    }
	if($('input#radio_month').prop("checked") == true){
		 var x = document.getElementById('Seach_Display_Month');
		 var y = document.getElementById('Seach_Display_Year');
		 x.style.display ='block';
		 y.style.display ='none';
   }

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
	
	 $('#error').css({"color":"#009966","font-size":"13px"}).fadeOut(6000);
	 $('#errors').css({"color":"#009966","font-size":"13px"}).fadeOut(6000);
	 
	 $('button#view_detail_btn').click(function(){	
		 var x = document.getElementById('table_Detail');	
		 var y = document.getElementById('TK');
		 if (x.style.display === 'none') {
		        x.style.display = 'block';	
		        y.style.display = 'none';	
		  } else {
		        x.style.display = 'none';	
		        y.style.display = 'block';	
		    }			 
		 
	 });

} );

</script>
</body>
</html>