<%@page import="com.ward_details"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<h1>WARD DETAILS</h1>

<!--  To read the values from a form -->
<% if (request.getParameter("ward_name") != null)
	{ 
	
	ward_details wardObj = new ward_details();  
	wardObj.connect();
	
	//wardObj.insertWard_details(ward_name, no_of_wards ,active_time,facilities);
	
	String stsMsg = wardObj.insertWard_Details(
									request.getParameter("ward_name"), 
									request.getParameter("no_of_wards"),
									request.getParameter("active_time"),
									request.getParameter("facilities")	); 
					
	session.setAttribute("stsMsg", stsMsg)	;						

	} 

	//Delete item
	if (request.getParameter("ward_Id") != null)  
	{ 
		ward_details wardObj = new ward_details();  
		String stsMsg = wardObj.deleteward_Details(request.getParameter("ward_Id"));  
		session.setAttribute("statusMsg", stsMsg); 
	}
%>
<form method="post" action="ward.jsp">
	<table>
		<tr><th>Ward Name :</th><th> <input type="text" name="ward_name"></th></tr>
		<tr><th>no of wards :</th><th> <input type="text" name="no_of_wards"></th></tr>
		<tr><th>Active Time :</th><th> <input type="text" name="active_time"></th></tr>
		<tr><th>facilities :</th><th> <input type="text" name="facilities"></th></tr>
	
		<tr><th></th><th><input name="btnSubmit" type="submit" value="Save"><input name="btnReset" type="reset" value="Reset"></th></tr>
	</table>
	<br>
</form>

<% out.print(session.getAttribute("statusMsg")); %>

<!--  Prepare the html table to be displayed -->
 <%  ward_details wardObj = new ward_details(); 
 out.print(wardObj.readWard_Details()); %> 

<H1>WARD</H1>
</body>
</html>