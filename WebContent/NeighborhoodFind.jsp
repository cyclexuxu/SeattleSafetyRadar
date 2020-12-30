<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Neighborhoods</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-light bg-light">
		<form class="form-inline" action="findneighborhood" method="post">
			<span class="navbar-brand mb-0 h1">Search for a Neighborhood by MCPP</span>
			<div class="form-group">
				<div class="input-group-prepend">
        			<span class="input-group-text" id="mcpp">MCPP</span>
      			</div>
				<input class="form-control mr-sm-2" id="mcpp" name="mcpp" value="${fn:escapeXml(param.mcpp)}">
				<button type="submit" class="btn btn-outline-success my-2 my-sm-0">Submit</button>
				<a class="btn btn-outline-success my-2 my-sm-0" href="Homepage.jsp">Homepage</a>
				<a class="btn btn-outline-success my-2 my-sm-0" href="Ranking.jsp">Show Ranking</a>
			</div>
		</form>
	</nav>
	
	<h1>Matching Neighborhoods</h1>
        <table class="table w-auto text-center table-bordered">
            <thead class="thead-dark">
            <tr>
                <th>MCPP</th>
                <th>Area</th>
                <th>Police Station</th>
                <th>Police Station Address</th>
                <th>Distance to police station</th>
                <th>Fire Station</th>
                <th>Fire Station Address</th>
                <th>Distance to fire station</th>
                <th>Most Frequent Event Type</th>
                <th>Least Frequent Event Type</th>
                <th>Add Rating</th>
            </tr>
            </thead>
            
                <tr>
                    <td><c:out value="${neighborhood.getMCPP()}" /></td>
                    <td><c:out value="${neighborhood.getArea()}" /></td>
                    <td><c:out value="${neighborhood.getPoliceStation()}" /></td>
                    <td><c:out value="${neighborhood.getPoliceStationAddress()}" /></td>
                    <td><c:out value="${neighborhood.getDistanceToPoliceStation()}" /></td>
                    <td><c:out value="${neighborhood.getFireStatin()}" /></td>
                    <td><c:out value="${neighborhood.getFireStationAddress()}" /></td>
                    <td><c:out value="${neighborhood.getDistanceToFireStation()}" /></td>
                    <td><c:out value="${neighborhoodEventReports.getMostFrequentEventType()}" /></td>
                    <td><c:out value="${neighborhoodEventReports.getLeastFrequentEventType()}" /></td>
                    <td><a href="addrating?mcpp=<c:out value="${neighborhood.getMCPP()}"/>">Add Rating</a></td>
                </tr>
           
       </table>
</body>
</html>