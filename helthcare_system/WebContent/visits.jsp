<%@page import="model.Visit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Visiting Details Adding</h1>

	<form id="formVisit" name="formItem" method="post" action="visits.jsp">
		 Hospital Name: <input id="hospital_name" name="hospital_name" type="text"	class="form-control form-control-sm"> <br>
		 Hospital City: <input id="hospital_city" name="hospital_city" type="text" class="form-control form-control-sm"> <br> 
		 Date: <input id="date" name="date" type="text" class="form-control form-control-sm"> <br> 
		 Time: <input id="time" name="time" type="text" class="form-control form-control-sm"> <br> 
		 No.of Channells: <input id="noPatients" name="noPatients" type="text" class="form-control form-control-sm"> <br> 
		 
		<!-- <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> -->
				<tr><th></th><th><input name="btnSubmit" type="submit" value="Save"><input name="btnReset" type="reset" value="Reset"></th></tr>
		
		 <input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
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