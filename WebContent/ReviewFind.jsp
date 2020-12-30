<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Reviews On Event</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
	
	
	<nav class="navbar navbar-light bg-light">
		<form class="form-inline" action="findreviews" method="get">
			<span class="navbar-brand mb-0 h1">Search Reviews On Events By UserName</span>
			<div class="form-group">
				<div class="input-group-prepend">
        			<span class="input-group-text" id="userName">UserName</span>
      			</div>
				<input class="form-control mr-sm-2" id="userName" name="userName" value="${fn:escapeXml(param.userName)}">
				<button type="submit" class="btn btn-outline-success my-2 my-sm-0">Submit</button>
				<a class="btn btn-outline-success my-2 my-sm-0" href="Homepage.jsp">Homepage</a>  
			</div>
				<!-- <input type="submit"> -->
				
		</form>
	</nav>
	
	<br/>
	<div id="createreview"><a class="btn btn-primary" href="createreview">Create Review on SafetyEvent</a></div>
	<br/>
	
	<br/>
		<span id="successMessage"><b>${messages.success}</b></span>
	<br/>
	<h1>Matching Reviews On Safety Events</h1>
        <table class="table w-auto text-center table-bordered">
        	<thead class="thead-dark">
            <tr>
            	<th>ReviewID</th>
                <th>Content</th>
                <th>CreatedTime</th>
                <th>UserName</th>
                <th>EventType</th>
                <th>MCPP</th>
                <th>Update Review</th>
                <th>Delete Review</th>
            </tr>
            </thead>
            <c:forEach items="${reviews}" var="review" >
                <tr>
                	<td><c:out value="${review.getReviewId()}" /></td>
                    <td><c:out value="${review.getContent()}" /></td>
                    <td><fmt:formatDate value="${review.getCreatedTime()}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${review.getUser().getUserName()}" /></td>
                    <td><c:out value="${review.getEvent().getEventType().name()}" /></td>
                    <td><c:out value="${review.getEvent().getMCPP()}" /></td>
                    
                    <td><a href="updatereview?id=<c:out value="${review.getReviewId()}"/>">Update Review</a></td>
                    <td><a href="deletreview?id=<c:out value="${review.getReviewId()}"/>">Delete Review</a></td>
                   
                </tr>
            </c:forEach>
       </table>
	
</body>
</html>