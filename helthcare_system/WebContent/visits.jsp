<%@page import="model.Visit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<% 
	//session.setAttribute("stsMsg", stsMsg)	;
	System.out.println("\nTryingto process...");

	//save
	if (request.getParameter("hospital_name") != null)
	{ 
	Visit visitObj = new Visit();  
	//visitObj.connect();
	String stsMsg ="";		
	
	
	//Insert--------------------------
	if (request.getParameter("hidVisitIDSave") == "") {
		stsMsg =visitObj.insertVisits(request.getParameter("hospital_name"), 
									request.getParameter("hospital_city"), 
									request.getParameter("date"),
									request.getParameter("time") ,
									request.getParameter("noPatients")); 
	} else//Update----------------------
	{
		stsMsg = visitObj.updateVisits(request.getParameter("hidVisitIDSave"),
		request.getParameter("hospital_name"), request.getParameter("hospital_city"), request.getParameter("date"), request.getParameter("time"), request.getParameter("noPatients"));
	}
	session.setAttribute("statusMsg", stsMsg);
		
} 

	//Delete item
	if (request.getParameter("visiting_id") != null)  
	{ 
		Visit visitObj = new Visit();  
		String stsMsg = visitObj.deleteVisits(request.getParameter("visiting_id"));  
		
	}
	if (request.getParameter("hidVisitIDDelete") != null) {
		Visit visitObj = new Visit();  
		String stsMsg = visitObj.deleteVisits(request.getParameter("hidVisitIDDelete"));
		session.setAttribute("statusMsg", stsMsg);
	}
%>	
	
	
	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visiting Details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script> 
</head>
<body>
<h1>Visiting Details Adding</h1>

	<form id="formVisit" name="formVisit" method="post" action="visits.jsp">
	
		 <!-- NAME -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Hospital Name:
							</span>
						</div>
						<input type="text" id="hospital_name" name="hospital_name">
					</div>
					
		 <!-- Hospital City: <input id="hospital_city" name="hospital_city" type="text" class="form-control form-control-sm"> <br> -->
		 <!-- CITY -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Hospital City:
							</span>
						</div>
						<input type="text" id="hospital_city" name="hospital_city">
					</div>
					
		 <!--  Date: <input id="date" name="date" type="text" class="form-control form-control-sm"> <br> -->
		 <!-- Date -->
					<div class="input-group input-group-sm mb-3">

						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Date: </span>
						</div>
						<input type="text" id="date" name="date">
					</div>
		 
		 <!--  Time: <input id="time" name="time" type="text" class="form-control form-control-sm"> <br> -->
		 <!-- Date -->
					<div class="input-group input-group-sm mb-3">

						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Time: </span>
						</div>
						<input type="text" id="time" name="time">
					</div>
		 
		 
		 <!--No.of Channells: <input id="noPatients" name="noPatients" type="text" class="form-control form-control-sm"> <br> -->
		 <div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">No. of Channels: </span>
						</div>
						<input type="text" id="noPatients" name="noPatients">
					</div>
		 
		 
		 
		<!-- <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> -->

		
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<input name="btnReset" type="reset" value="Reset" class="btn btn-primary"></th></tr>
					<input	id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary">
					<input type="hidden"id="hidVisitIDSave" name="hidVisitIDSave" value="">
	</form>
		<%
			out.print(session.getAttribute("statusMsg"));
		%>
	</div>
	
	<div id=alertError" class="alert alert-danger">
	
	<br>
	<%
		Visit visitObj = new Visit();
		out.print(visitObj.readVisits());
	%>
	
</body>
</html>