<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Review an Event</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" 
integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

</head>

<body>

	<nav class="navbar navbar-expand-md navbar-dark">
			<div class="navbar-brand">
				Review A Safety Event
			</div>
	</nav>
	
	<center>
	<h1>Review An Event</h1>
	</center>
	
	<center>
	<div class="container col-md-5">
	<form action="createreview" method="post">
	
		<div class="form-group">
			<label for=eventId><h3>Event ID</h3></label>
			<input id="eventId" class="form-control" name="eventId" value="${fn:escapeXml(param.id)}">
		</div>
		<div class="form-group">
			<label for="userName"><h3>UserName</h3></label>
			<input id="userName" class="form-control" name="userName" value="">
		</div>
		<div class="form-group">
			<label for="createdTime"><h3>CreateTime</h3></label>
			<input id="createdTime" class="form-control" name="createdTime" value="">
		</div>
		<div class="form-group">
			<label for="content"><h3>Review Content</h3></label>
			<input id="content" class="form-control" name="content" value="">
		</div>
		
		<button type="submit" class="btn btn-primary">Submit</button>
		<a class="btn btn-primary" href="ReviewFind.jsp">Reviews</a>
		<a class="btn btn-primary" href="Homepage.jsp">Homepage</a>   
	</form>
	
	</div>
	</center>
	
	<br/><br/>
	
	<div class="form-group">
		<center>
		<span id="successMessage"><b>${messages.success}</b></span>
		</center>
	</div>
	
</body>
</html>