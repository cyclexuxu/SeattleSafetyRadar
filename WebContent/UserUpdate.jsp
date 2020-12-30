<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update User</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
	<p class="h1">Update User Information</p>
	<div class="container col-md-5">
	<form action="userupdate" method="post">
		<div class="form-group">
			<label for="username">UserName</label>
			<input id="username" class="form-control" name="username" value="${fn:escapeXml(param.username)}">
		</div>
		<div class="form-group">
			<label for="password">New Password</label>
			<input id="password" class="form-control" name="password" value="">
		</div>
		<div class="form-group">
			<label for="firstname">New FirstName</label>
			<input id="firstname" class="form-control" name="firstname" value="">
		</div>
		<div class="form-group">
			<label for="lastname">New LastName</label>
			<input id="lastname" class="form-control" name="lastname" value="">
		</div>
		<div class="form-group">
			<label for="email">New Email</label>
			<input id="email" class="form-control" name="email" value="">
		</div>
		<div class="form-group">
			<label for="street1">Street1</label>
			<input id="street1" class="form-control" name="street1" value="">
		</div>
		<div class="form-group">
			<label for="street2">Street2</label>
			<input id="street2" class="form-control" name="street2" value="">
		</div>
		<div class="form-group">
			<label for="city">City</label>
			<input id="city" class="form-control" name="city" value="">
		</div>
		<div class="form-group">
			<label for="zip">Zip</label>
			<input id="zip" class="form-control" name="zip" value="">
		</div>
		<div class="form-group">
			<label for="state">State</label>
			<input id="state" class="form-control" name="state" value="">
		</div>
		<div class="form-group">
			<label for="country">Country</label>
			<input id="country" class="form-control" name="country" value="">
		</div>
		<div class="form-group">
			<label for="latitude">Latitude</label>
			<input id="latitude" class="form-control" name="latitude" value="">
		</div>
		<div class="form-group">
			<label for="longitude">Longitude</label>
			<input id="longitude" class="form-control" name="longitude" value="">
		</div>
		<div class="form-check">
			  <input class="form-check-input" type="radio" id="regular" name="usertype" value="REGULAR">
			  <label class="form-check-label" for="regular">Regular</label><br>
			  <input class="form-check-input" type="radio" id="military" name="usertype" value="MILITARY">
			  <label class="form-check-label" for="military">Military</label><br>
			  <input class="form-check-input" type="radio" id="official" name="usertype" value="OFFICIAL">
			  <label class="form-check-label" for="official">Official</label>
		</div>
		<div>
			<button type="submit" class="btn btn-primary">Submit</button>
			<a class="btn btn-primary" href="UserFind.jsp">User Find</a>
			<a class="btn btn-primary" href="Homepage.jsp">Homepage</a>
		</div>
	</form>
	</div>
	<br/><br/>
	<center>
	<p>
		<c:forEach var="msg" items="${messages.success}">
         	<b>${msg}</b><br/>
    	</c:forEach>
	</p>
	</center>

</body>
</html>