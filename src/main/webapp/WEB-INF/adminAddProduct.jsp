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
<title>Add Product Page</title>
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
        <div class="container" >
        <h1 class="jumbotron-heading">Add Product</h1>
        	<form:form method="POST" action="/admin/add" modelAttribute="product">
			
					<div class="row">
						<label> <span>Name</span> <form:input type="text" class="form-control"
							path="name" />
					</label>
					</div>
					<div class="row">
						<label> <span>Description</span> <form:textarea class="form-control" type="text"
							path="description" />
					</label>
					</div>
					<div class="row">
						<label> <span>Price</span> <form:input type="number" class="form-control"   path="price" step="0.01" />
					</label> 
					</div>
					
					<div class="row">
						<label> <span>Available Quantity</span> <form:input class="form-control" type="number" min="0" 
							path="availableQuantity" />
					</label> 
					</div>
						<div class="row">
						<label> <span>Image URL</span> <form:input class="form-control"
							type="text" path="image" />
					</label>
					</div>
					<div class="row"style="margin-left:1px" >
						<label>Select Category</label>
						<select class="form-select" aria-label="Default select example" name="cat">
							<c:forEach items="${categories}" var="category">
								<option value="${category.id}">${category.name}</option>
							</c:forEach>
						</select>
					</div><br>

			
			        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					
					<button type="submit" class="btn btn-primary"/>Add Product</button>
		</form:form>
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