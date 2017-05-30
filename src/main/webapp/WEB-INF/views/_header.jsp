<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AdminHome</title>
<link rel="stylesheet" href="css/header.css">
</head>
<body>
	<!-- Header page -->
	<div class="row border-bottom">

		<div class="navbar navbar-static-top" id="header">
			<h2 class="col-sm-8 col-md-8">THE GALAXY LANGUAGE SCHOOL</h2>
			<div class="pull-right" id="btn-group">
				<div class="row">
					<div class="current-username">
						<label>${pageContext.request.userPrincipal.name}</label>
					</div>
					<c:url value="/logout" var="logoutUrl" />
					<form action="${logoutUrl}" method="post" id="logoutForm">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
					<script>
						function formSubmit() {
							document.getElementById("logoutForm").submit();
						}
					</script>

					<div class="col-md-offset-1 col-sm-4 col-md-4 ">
						<c:if test="${pageContext.request.userPrincipal.name != null}">
							<a href="javascript:formSubmit()"><input type="button"
								value="Logout" id="btn-logout" class="btn btn-default" /></a>
						</c:if>
					</div>
				</div>
			</div>

		</div>

	</div>
	<!-- End header page -->
</body>
</html>