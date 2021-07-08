<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"><link rel="stylesheet" type="text/css" href="/css/admin.css">
<link rel="stylesheet" type="text/css" href="/css/admin.css">
<title>Users</title>
</head>
<body>
    <div id="wrapper" class="toggled">

        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="/admin">
                        Admin  <c:out value="${currentUser.firstName}"></c:out>
                    </a>
                </li>
                <li>
                    <a href="/admin/users">Users</a>
                </li>
                <li>
                    <a href="/admin/add">Add Product</a>
                </li>
                <li>
                    <a href="/admin/addcategory">Add Category</a>
                </li>
                <li>
                    <a href="/admin/charts">Charts</a>
                </li><br>
                <li>
                    <a href="#"><form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" class="btn btn-danger" />
    </form></a>
                </li>
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <h1>Users</h1>
   

<table class="table">
  <thead class="thead-light">
    <tr>
      <th scope="col">First Name</th>
      <th scope="col">Last Name</th>
      <th scope="col">Email</th>
    </tr>
  </thead>
  <tbody>
    <tr>
    <c:forEach items="${users}" var="user">
      <td><c:out value="${user.firstName}"/></td>
      <td><c:out value="${user.lastName}"/></td>
      <td><c:out value="${user.username}"/></td>
    </tr>
    </c:forEach>
  </tbody>
</table>
             
            </div>
        </div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- Menu Toggle Script -->
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    </script>


</body>
</html>