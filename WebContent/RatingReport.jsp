<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rating Reports</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" 
integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	
</head>
<body>
	<!--  
	<nav class="navbar navbar-expand-md navbar-dark">
			<div class="navbar-brand">
			</div>
	</nav>
	<center><h1>Display Ratings by Date and MCPP</h1></center>
	-->
	<div class="navbar navbar-light bg-light">
		<form class="form-inline" action="ratingreports" method="post">
			<span class="navbar-brand mb-0 h1">Display Ratings by Date and MCPP</span>
			<div class="form-group">
				<div class="input-group-prepend">
        			<span class="input-group-text" id="event_type">Date</span>
        			<input class="form-control mr-sm-2" type = "text" name = "date" value="${fn:escapeXml(param.date)}" >
        			<span class="input-group-text" id="event_type">MCPP</span>
        			<input class="form-control mr-sm-2" type = "text" name = "MCPP" value="${fn:escapeXml(param.MCPP)}" >
      			</div>
				<button type="submit" class="btn btn-outline-success my-2 my-sm-0">Submit</button>
				<a class="btn btn-outline-success my-2 my-sm-0" href="Homepage.jsp">Homepage</a>  
			</div>
      </form>
      </div>
      
	
	<h1>${messages.title}</h1>
        <table class="table w-auto text-center table-bordered" >
        	<thead class="thead-dark">
        	<tr>
                <th>RecordHistoryID</th>
                <th>CurrentRating</th>
                <th>SafetyRankingUp</th>
                <th>CreatedDate</th>
                <th>MCPP</th>
                <th>ReportID</th>
            </tr>
             </thead>
             
            <c:forEach items="${ratingReports}" var="ratingReport" >
            	<tr>
                    <td><c:out value="${ratingReport.getRecordHistoryID()}" /></td>
                    <td><c:out value="${ratingReport.getCurrentRating()}" /></td>
                    <td><c:out value="${ratingReport.getSafetyRankingUP()}" /></td>
                    <td><fmt:formatDate value="${ratingReport.getCurrentDate()}" pattern="MM-dd-yyyy hh:mm:sa"/></td>
                    
                    
                    <td><c:out value="${ratingReport.getNeighborhoods().getMCPP()}"/></td>
                    <td><c:out value="${ratingReport.getRatingReports().getReportId()}" /></td>
                </tr>
            </c:forEach>
       </table>
       
       
 

</body>
</html>