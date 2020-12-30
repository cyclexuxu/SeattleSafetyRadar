<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete a Safety Event</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" 
integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>

<body>
	<center>
	<h1>${messages.title}</h1>
	</center>
	<form action="eventdelete" method="post">
	<center>
		<p>
			<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<h4>
				<label for="is">ID</label>
				<input id="id" name="id" value="${fn:escapeXml(param.id)}">
				</h4>
			</div>
		<p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<!-- <input type="submit"> -->
			<button type="submit" class="btn btn-primary">Submit</button>
			<a class="btn btn-primary" href="FindSafetyEvents.jsp">SafetyEvents</a>
			<a class="btn btn-primary" href="Homepage.jsp">Homepage</a>   

			</span>
		</p>
		</center>
	</form>
	<br/><br/>
	
</body>

</html>