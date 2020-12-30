<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Event</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" 
integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-dark">
			<div class="navbar-brand">
			</div>
	</nav>
	<center><h1>Create Safety Event</h1></center>

	<div class="container col-md-5">
	<form action="eventcreate" method="post">
		<div class="form-group">

			<label for="latitude">Latitude</label>
			<input id="latitude" class="form-control" name="latitude" value="">
		</div>
		<div class="form-group">

			<label for="longitude">Longitude</label>
			<input id="longitude" class="form-control" name="longitude" value="">
		</div>
		<div class="form-group">

			<label for="reporttime">ReportTime (yyyy-mm-dd)</label>
			<input id="reporttime" class="form-control" name="reporttime" value="">
		</div>
		<div class="form-group">

			<label for="eventtype">EventType</label>
			<input id="eventtype" class="form-control" name="eventtype" value="">
		</div>
		<div class="form-group">

			<label for="MCPP">MCPP</label>
			<input id="MCPP" class="form-control" name="MCPP" value="">
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
		<a class="btn btn-primary" href="FindSafetyEvents.jsp">SafetyEvents</a>
		<a class="btn btn-primary" href="Homepage.jsp">Homepage</a>                 
                        
	</form>
	</div>
	<br/><br/>
	<center>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
	</center>
</body>
</html>
