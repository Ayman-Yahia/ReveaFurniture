<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style>
.divs {
	display: inline-block;
	margin-right: 100px;
	margin-top:0;
	
}
</style>
</head>
<body>
	<h1>Welcome user</h1>
	<div class="divs">
		<h3>Register!</h3>

		<p>
			<form:errors path="user.*" />
		</p>

		<form:form method="POST" action="/register" modelAttribute="user">
			<p>
				<form:label path="name" class="form-label">Name:</form:label>
				<form:input type="text" path="name" class="form-control" />
			</p>
		
			<p>
				<form:label path="email" class="form-label">Email:</form:label>
				<form:input type="email" path="email" class="form-control" />
			</p>
			
			<p>
				<form:label path="password" class="form-label">Password:</form:label>
				<form:password path="password" class="form-control" />
			</p>
			<p>
				<form:label path="passwordConfirmation" class="form-label">Password Confirmation:</form:label>
				<form:password path="passwordConfirmation" class="form-control" />
			</p>
			<input type="submit" value="Register!" />

		</form:form>
	</div>
	<div class="divs">
		<h3>Login</h3>
		<div>
			<p>
				<c:out value="${error}" />
			</p>
			<form method="post" action="/login">
				<div class="mb-3">
					<p>
						<label for="email" class="form-label">Email</label> <input
							type="text" id="email" name="email" class="form-control" />
					</p>
					<p>
						<label for="password" class="form-label">Password</label> <input
							type="password" id="password" name="password"
							class="form-control" />
					</p>
					<input type="submit" value="Login!" />
				</div>
			</form>
		</div>
</body>
</html>