<%@page import="com.visits"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Health Care System </title>
</head>
<body>


<h1>INCOME</h1>


<form method="post" action="income.jsp">
	<table>
		<tr><th>Apoiment Charges :</th><th> <input type="text" name="apoiment_charges"></th></tr>
	    <tr><th>Date :</th><th> <input type="text" name="date"></th></tr>
		<tr><th>Cash or Card :</th><th> <input type="text" name="cashorcard"></th></tr>
		<tr><th>Income ID : </th><th><input type="text" name="incometId "></th></tr>
		<tr><th>Total : </th><th><input type="text" name="total"></th></tr>

		<tr><th></th><th><input name="btnSubmit" type="submit" value="Save"><input name="btnReset" type="reset" value="Reset"></th></tr>
	</table>
	<br>
</form>


</body>
</html> 

