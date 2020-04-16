<%@page import="com.visits"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<h1>VISITING DETAILS</h1>

<!--  To read the values from a form -->
<% if (request.getParameter("hospital_name") != null)
	{ 
	
	visits visitObj = new visits();  
	visitObj.connect();
	
	//visitObj.insertVisits(hospitalName, city, date, time, noPatients);
	
	String stsMsg = visitObj.insertVisits(request.getParameter("hospital_name"), 
									request.getParameter("hospital_city"), 
									request.getParameter("date"),
									request.getParameter("time") ,
									request.getParameter("noPatients")); 
					
	session.setAttribute("stsMsg", stsMsg)	;						
	
	/* session.setAttribute("hospitalName", request.getParameter("hospitalName"));  
	session.setAttribute("hospitalCity", request.getParameter("hospitalCity"));  
	session.setAttribute("date", request.getParameter("date"));  
	session.setAttribute("time", request.getParameter("time"));  
	session.setAttribute("noPatients", request.getParameter("noPatients"));   */

	} 

	//Delete item
	if (request.getParameter("visiting_id") != null)  
	{ 
		visits visitObj = new visits();  
		String stsMsg = visitObj.deleteVisits(request.getParameter("visiting_id"));  
		session.setAttribute("statusMsg", stsMsg); 
	}
%>
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

<% out.print(session.getAttribute("statusMsg")); %>


<%-- <table border="1">
	<tr>
		<th>Hospital Name</th>
		<th>Hospital City</th>
		<th>Date</th><th>Time</th>
		<th>No. of patients</th>
		<th>Update</th><th>Remove</th>
	</tr>
	<tr>
		<td>abc</td><td>xxx</td><td>a1</td><td>a2</td><td>10</td>
		<td><input name="btnUpdate" type="button" value="Update"> </td>
		<td><input name="btnRemove" type="button" value="Remove"> </td>
	</tr>	
	
	<tr> 
	<!-- display the data in the table  -->
		<td><%out.print(session.getAttribute("hospitalName")); %></td> 
		<td><%out.print(session.getAttribute("hospitalCity")); %></td> 
		<td><%out.print(session.getAttribute("date")); %></td> 
		<td><%out.print(session.getAttribute("time")); %></td> 
		<td><%out.print(session.getAttribute("noPatients")); %></td> 
		
		<td><input name="btnUpdate" type="button" value="Update"></td> 
		<td><input name="btnRemove" type="button" value="Remove"></td> 
</tr>		
</table> --%>



<!--  Prepare the html table to be displayed -->
 <%  visits visitsObj = new visits(); 
 out.print(visitsObj.readVisits()); %> 

</body>
</html>