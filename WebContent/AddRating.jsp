<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a rating</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
	<p class="h1">Add a rating</p>
	<div class="container col-md-5">
	<form action="addrating" method="post">
		<div class="form-group">
			<label for="rating">Rating</label>
			<input id="rating" class="form-control" name="rating" value="">
		</div>
		<div class="form-group">
			<label for="comment">Comment</label>
			<input id="comment" class="form-control" name="comment" value="">
		</div>
		<div class="form-group">
			<label for="username">UserName</label>
			<input id="username" class="form-control" name="username" value="">
		</div>
		<div class="form-group">
			<label for="mcpp">MCPP</label>
			<input id="mcpp" class="form-control" name="mcpp" value="${fn:escapeXml(param.mcpp)}">
		</div>
	
		<div>
			<button type="submit" class="btn btn-primary">Submit</button>
			<a class="btn btn-primary" href="Homepage.jsp">Homepage</a>
		</div>
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