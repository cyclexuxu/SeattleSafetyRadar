<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Safety Event</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
	
	<nav class="navbar navbar-light bg-light">
		<form class="form-inline" action="findevents" method="post">
			<span class="navbar-brand mb-0 h1">Search safety events by type</span>
			<div class="form-group">
				<div class="input-group-prepend">
        			<span class="input-group-text" id="event_type">Event Type</span>
      			</div>
				<input class="form-control mr-sm-2" id="event_type" name="event_type" value="${fn:escapeXml(param.event_type)}">
				<button type="submit" class="btn btn-outline-success my-2 my-sm-0">Submit</button>

				<a class="btn btn-outline-success my-2 my-sm-0" href="Homepage.jsp">Homepage</a>  
			</div>
				<!-- <input type="submit"> -->
				
		</form>
	</nav>
	
	<br/>
	<div id="eventcreate"><a class="btn btn-primary" href="eventcreate">Create SafetyEvent</a></div>
	<br/>
	
	<br/>
		<span id="successMessage"><b>${messages.success}</b></span>
	<br/>
	<h1>Matching Safety Events</h1>
        <table class="table w-auto text-center table-bordered">
        	<thead class="thead-dark">
            <tr>
            	<th>ID</th>
                <th>Latitude</th>
                <th>Longitude</th>
                <th>Date</th>
                <th>EventType</th>
                <th>MCPP</th>
                <th>Update Event</th>
                <th>Delete Event</th>
                <th>Review Event</th>

            </tr>
            </thead>
            <c:forEach items="${events}" var="event" >
                <tr>
                	<td><c:out value="${event.getEventId()}" /></td>
                    <td><c:out value="${event.getLatitude()}" /></td>
                    <td><c:out value="${event.getLongitude()}" /></td>
                    <td><fmt:formatDate value="${event.getDateTime()}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${event.getEventType().name()}" /></td>
                    <td><c:out value="${event.getMCPP()}" /></td>
                    <td><a href="eventupdate?id=<c:out value="${event.getEventId()}"/>">Update Event</a></td>
                    <td><a href="eventdelete?id=<c:out value="${event.getEventId()}"/>">Delete Event</a></td>
                    <td><a href="createreview?id=<c:out value="${event.getEventId()}"/>">Review Event</a></td>

                   
                </tr>
            </c:forEach>
       </table>
	
</body>
</html>
