<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>How to Design Login & Registration Form Transition</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script type="text/javascript" src="/js/app.js"></script>
<link
	href="https://fonts.googleapis.com/css?family=Nunito:400,600,700,800&display=swap"
	rel="stylesheet">
<script>
$(document).ready(function(){
    $('.img-btn').click(function(){
        $('.cont').toggleClass( "s-signup" );
        $('.sm-in').remove();
}) 
});
    </script>
</head>
<body>
	<div class="cont">
		<form method="POST" action="/login">

			<div class="form sign-in">
				<h2>Sign In</h2>
				<label> <span>Email Address</span> 
				<input type="email" name="username">
				</label> 
				<label> <span>Password</span> 
				<input type="password" name="password">
				</label> 
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

				<button class="submit" type="submit">Sign In</button>
				<p class="forgot-pass">Forgot Password ?</p>

			</div>
		</form>
		
		<div>
		<p>
			<form:errors path="user.*" />
		</p>
		<form:form method="POST" action="/registration" modelAttribute="user">
		

			<div class="sub-cont">
				<div class="img">
					<div class="img-text m-up">
						<h2>New here?</h2>
						<p>Sign up and discover great amount of new opportunities!</p>
					</div>
					<div class="img-text m-in">
						<h2>One of us?</h2>
						<p>If you already has an account, just sign in. We've missed
							you!</p>
					</div>
					<div class="img-btn">
						<span class="img-btn sm-in">Sign Up</span> <span class="m-in">Sign
							In</span>
					</div>
				</div>
				<div class="form sign-up">
					<h2>Sign Up</h2>
					<label> <span>First Name</span> <form:input type="text"
							path="firstName" />
					</label> <label> <span>last Name</span> <form:input type="text"
							path="lastName" />
					</label> <label> <span>Email</span> <form:input type="email"
							path="username" />
					</label> <label> <span>Password</span> <form:input type="password"
							path="password" />
					</label> <label> <span>Confirm Password</span> <form:input
							type="password" path="passwordConfirmation" />
					</label>
					<button type="submit" class="submit">Sign Up Now</button>
				</div>
			</div>
		</form:form>
		</div>


	</div>
</body>
</html>