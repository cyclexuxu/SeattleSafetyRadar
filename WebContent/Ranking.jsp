<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Neighborhoods Safety Ranking</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-light bg-light">
		<form class="form-inline" action="neighborhoodRanking" method="post">
			<span class="navbar-brand mb-0 h1">Neighborhood Safety Ranking</span>
			<div class="form-group">
				<div class="input-group-prepend">
        			<span class="input-group-text" id="order">Order(ASC or DESC)</span>
      			</div>
				<input class="form-control mr-sm-2" id="order" name="order" value="${fn:escapeXml(param.order)}">
				<div class="input-group-prepend">
        			<span class="input-group-text" id="limit">LIMIT</span>
      			</div>
				<input class="form-control mr-sm-2" id="limit" name="limit" value="${fn:escapeXml(param.limit)}">
				<button type="submit" class="btn btn-outline-success my-2 my-sm-0">Submit</button>
				<a class="btn btn-outline-success my-2 my-sm-0" href="Homepage.jsp">Homepage</a>
			</div>
		</form>
	</nav>
	
	<h1>Ranking</h1>
		<table class="table w-auto text-center table-bordered">
			<thead class="thead-dark">
			<tr>
				<th>Ranking</th>
				<th>Average Rating</th>
				<th>MCPP</th>
			</tr>
			</thead>
			<c:forEach items="${rankings}" var="ranking" >
			<tr>
				<td><c:out value="${ranking.getRanking() }" /></td>
				<td><c:out value="${ranking.getRating() }" /></td>
				<td><c:out value="${ranking.getMcpp() }" /></td>
			</tr>
			</c:forEach>
		</table>

</body>
</html>