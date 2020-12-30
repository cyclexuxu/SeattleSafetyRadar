<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Users</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-light bg-light">
		<form class="form-inline" action="findusers" method="post">
			<span class="navbar-brand mb-0 h1">Search for a User by First Name</span>
			<div class="form-group">
				<div class="input-group-prepend">
        			<span class="input-group-text" id="firstname">FirstName</span>
      			</div>
				<input class="form-control mr-sm-2" id="firstname" name="firstname" value="${fn:escapeXml(param.firstname)}">
				<button type="submit" class="btn btn-outline-success my-2 my-sm-0">Submit</button>
				<a class="btn btn-outline-success my-2 my-sm-0" href="Homepage.jsp">Homepage</a>
			</div>
		</form>
	</nav>
	<br/>
	<div id="userCreate"><a class="btn btn-primary" href="usercreate">Create App User</a></div>
	<br/>
	
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	<h1>Matching Users</h1>
        <table class="table w-auto text-center table-bordered">
            <thead class="thead-dark">
            <tr>
                <th>UserName</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Email</th>
                <th>Street1</th>
                <th>Street2</th>
                <th>City</th>
                <th>Zip</th>
                <th>State</th>
                <th>Country</th>
                <th>Latitude</th>
                <th>Longitude</th>
                <th>UserType</th>
                <th>Delete User</th>
                <th>Update User</th>
            </tr>
            </thead>

            <c:forEach items="${users}" var="user" >
                <tr>
                    <td><c:out value="${user.getUserName()}" /></td>
                    <td><c:out value="${user.getFirstName()}" /></td>
                    <td><c:out value="${user.getLastName()}" /></td>
                    <td><c:out value="${user.getEmail()}" /></td>
                    <td><c:out value="${user.getStreet1()}" /></td>
                    <td><c:out value="${user.getStreet2()}" /></td>
                    <td><c:out value="${user.getCity()}" /></td>
                    <td><c:out value="${user.getZip()}" /></td>
                    <td><c:out value="${user.getState()}" /></td>
                    <td><c:out value="${user.getCountry()}" /></td>
                    <td><c:out value="${user.getLatitude()}" /></td>
                    <td><c:out value="${user.getLongitude()}" /></td>
                    <td><c:out value="${user.getUserType().name()}" /></td>
                    <td><a href="userdelete?username=<c:out value="${user.getUserName()}"/>">Delete</a></td>
                    <td><a href="userupdate?username=<c:out value="${user.getUserName()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>