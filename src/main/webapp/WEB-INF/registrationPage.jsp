<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration Login Page</title>
</head>
<body>
    <div align="center" class="container" style="display:flex;justify-content:space-between;width:1000px;margin-top:40px;margin-left:200px">
    	<div class="left">
    		<form:form action="/registration" method="post" modelAttribute="user">
            <table border="0">
                <tr>
                    <td colspan="2" align="center"><h2> Registration</h2></td>
                    <td><form:errors path="user.*"/></td>
                </tr>
		<tr>
                    <td>First Name:</td>
                    <td><form:input path="firstName" /></td>
                </tr>
                <tr>
                    <td>Last Name:</td>
                    <td><form:input path="lastName" /></td>
                </tr>
                <tr>
                    <td>E-mail:</td>
                    <td><form:input path="username" /></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><form:password path="password" /></td>
                </tr>
                <tr>
                    <td>Pw Con:</td>
                    <td><form:password path="passwordConfirmation" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Register" /></td>
                </tr>
            </table>
        </form:form>
    	</div>
    	<div class="rigth">
    		<form method="post" action="/login">
            <table border="0">
                <tr>
                    <td colspan="2" align="center"><h2>Login</h2></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="text" id="email" name="username"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" id="password" name="password"/></td>
                </tr>
                <tr>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                
                    <td colspan="2" align="center"><input type="submit" value="Login!" /></td>
                </tr>
            </table>
        </form>
    	</div>
        
    </div>
</body>
</html>