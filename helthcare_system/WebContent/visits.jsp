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
<title>Insert title here</title>
<!-- <link rel="stylesheet" href="Views/bootstrap.min.css"-->
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script> 
</head>
<body>
<h1>Visiting Details Adding</h1>

	<form id="formVisit" name="formVisit" method="post" action="visits.jsp">
		 Hospital Name: <input id="hospital_name" name="hospital_name" type="text"	class="form-control form-control-sm"> <br>
		 Hospital City: <input id="hospital_city" name="hospital_city" type="text" class="form-control form-control-sm"> <br> 
		 Date: <input id="date" name="date" type="text" class="form-control form-control-sm"> <br> 
		 Time: <input id="time" name="time" type="text" class="form-control form-control-sm"> <br> 
		 No.of Channells: <input id="noPatients" name="noPatients" type="text" class="form-control form-control-sm"> <br> 
		 
		<!-- <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> -->
		<!-- <input id="btnSave" name="btnSave" type="submit" value="Save"  class="btn btn-primary">
		<input name="btnReset" type="reset" value="Reset"></th></tr>
		<input type="hidden" id="hidVisitIDSave" name="hidVisitIDSave" value=""> -->
		
		<input	id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary">
		<input type="hidden"id="hidVisitIDSave" name="hidVisitIDSave" value="">
	</form>
	
	<div id=alertSuccess" class="alert alert-success">
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