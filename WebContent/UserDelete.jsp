<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete User</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

</head>
<body>
	<p class="h1">${messages.title}</p>
	<div class="container col-md-5">
		<form action="userdelete" method="post">
			<p>
				<div class="form-group" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
					<label for="username">UserName</label>
					<input id="username" class="form-control" name="username" value="${fn:escapeXml(param.username)}">
				</div>
			</p>
			<p>
				<button type="submit" class="btn btn-primary" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>Submit</button>
				<a class="btn btn-primary" href="UserFind.jsp">User Find</a>
			    <a class="btn btn-primary" href="Homepage.jsp">Homepage</a>
			</p>
		</form>
	</div>
	<br/><br/>

</body>
</html>