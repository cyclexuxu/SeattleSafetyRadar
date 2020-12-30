<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- added boostrap css file -->
        <link rel="stylesheet" type="text/css" href="style.css"></linke>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <!-- added javascript file -->
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="row">
            <div class="col-md-12">
            <!-- add navigation bar  -->
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <a class="navbar-brand h1" href="Homepage.jsp">Seattle Safety Radar</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#menu" aria-controls="menu" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="menu">
                    <div class="navbar-nav">
                        <a class="nav-item nav-link" href="FindSafetyEvents.jsp">SafetyEvents</a>
                        <a class="nav-item nav-link" href="NeighborhoodFind.jsp">Neighborhoods</a>
                        <a class="nav-item nav-link" href="UserFind.jsp">Users</a>
                        <a class="nav-item nav-link" href="ReviewFind.jsp">Reviews</a>
                        <a class="nav-item nav-link" href="RatingReport.jsp">Rating Report</a>
                    </div>
                </div>
            </nav>
            <!-- add background video  
            <video autoplay muted loop id="myVideo">
                <source src="video.mp4" type="video/mp4">
            </video>
			-->
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<img id="homepage-banner" src="https://images.unsplash.com/photo-1502175353174-a7a70e73b362?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1570&q=80">
            	<!--
            	<div class="centered-heading">Seattle Safety Radar</div>
            	<div class="centered">A Safety Guardian In Seattle Community</div>
            	<a id="continue" class="btn btn-lg btn-primary" href="UserFind.jsp">Continue</a>
            	-->
			</div>
        </div>
        

    </body>
</html>