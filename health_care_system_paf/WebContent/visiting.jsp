<%@page import="com.visits"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Health Care </title>
</head>
<body>


<h1>VISITING DETAILS</h1>


<form method="post" action="visiting.jsp">
	<table>
		<tr><th>Hospital Name :</th><th> <input type="text" name="hospital_name"></th></tr>
		<tr><th>Hospital City :</th><th> <input type="text" name="hospital_city"></th></tr>
		<tr><th>Date :</th><th> <input type="text" name="date"></th></tr>
		<tr><th>Time :</th><th> <input type="text" name="time"></th></tr>
		<tr><th>No. of patients : </th><th><input type="text" name="noPatients"></th></tr>
	
		<tr><th></th><th><input name="btnSubmit" type="submit" value="Save"><input name="btnReset" type="reset" value="Reset"></th></tr>
	</table>
	<br>
</form>


</body>
</html>