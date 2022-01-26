<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create/Update User</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>	
	<nav class="navbar navbar-expand-md navbar-light bg-success">
		<h3 class="text-white">Teacher's Management Application</h3>
		<ul class="navbar-nav">
			<li><a href="<%=request.getContextPath()%>/list" class="nav-link">Users</a></li>
		</ul>
	</nav>
	<div class="container">
		<div class="shadow p-3 mb-5 bg-body rounded">
			<div class="bg-success p-2 text-dark bg-opacity-10">
				<div class="d-grid justify-content-md-center">
					<h1>List of All Users</h1>
				</div>
				<div class="d-grid justify-content-md-end">
					<a href = "<%=request.getContextPath()%>/new" class="btn btn-success btn-lg" type="button" > Create User</a>		
				</div>
				<br>
				<div class="container">
				<div class="row">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>ID</th>
								<th>Name</th>
								<th>Email</th>
								<th>Country</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<!--   for (Todo todo: todos) {  -->
							<c:forEach var="teacher" items="${listUser}">
		
								<tr>
									<td><c:out value="${teacher.id}" /></td>
									<td><c:out value="${teacher.name}" /></td>
									<td><c:out value="${teacher.email}" /></td>
									<td><c:out value="${teacher.country}" /></td>
									<td><a href="edit?id=<c:out value='${teacher.id}' />">Edit</a>
										&nbsp;&nbsp;&nbsp;&nbsp; 
										<a href="delete?id=<c:out value='${teacher.id}' />">Delete</a></td>
								</tr>
							</c:forEach>
							<!-- } -->
						</tbody>
					</table>
				</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>