<%@page import="com.channel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<h1>CHANNELING DETAILS</h1>

<!--  To read the values from a form -->
<% if (request.getParameter("doctor_name") != null)
	{ 
	
	channel channelObj = new channel();  
	channelObj.connect();
	
	//visitObj.insertVisits(hospitalName, city, date, time, noPatients);
	
	String stsMsg = channelObj.insertchannels(request.getParameter("doctor_name"), 
									request.getParameter("hospital_name"), 
									request.getParameter("date"));
					
	session.setAttribute("stsMsg", stsMsg)	;						
	
	/* session.setAttribute("doctorName", request.getParameter("doctorName"));  
	session.setAttribute("hospitalName", request.getParameter("hospitalName"));  
	session.setAttribute("date", request.getParameter("date"));   */

	} 

	//Delete item
	if (request.getParameter("channeling_id") != null)  
	{ 
		channel channelObj = new channel();  
		String stsMsg = channelObj.deletechannels(request.getParameter("channeling_id"));  
		session.setAttribute("statusMsg", stsMsg); 
	}
%>
<form method="post" action="Channeling.jsp">
	<table>
		<tr><th>Doctor Name :</th><th> <input type="text" name="doctor_name"></th></tr>
		<tr><th>Hospital Name :</th><th> <input type="text" name="hospital_name"></th></tr>
		<tr><th>Date :</th><th> <input type="text" name="date"></th></tr>
	
		<tr><th></th><th><input name="btnSubmit" type="submit" value="Proceed"><input name="btnReset" type="reset" value="Reset"></th></tr>
	</table>
	<br>
</form>

<% out.print(session.getAttribute("statusMsg")); %>


<%-- <table border="1">
	<tr>
		<th>Doctor Name</th>
		<th>Hospital Name</th>
		<th>Date</th>
		<th>Update</th><th>Remove</th>
	</tr>
	<tr>
		<td>abc</td><td>xxx</td><td>a1</td><td>a2</td><td>10</td>
		<td><input name="btnUpdate" type="button" value="Update"> </td>
		<td><input name="btnRemove" type="button" value="Remove"> </td>
	</tr>	
	
	<tr> 
	<!-- display the data in the table  -->
		<td><%out.print(session.getAttribute("doctorName")); %></td> 
		<td><%out.print(session.getAttribute("hospitalName")); %></td> 
		<td><%out.print(session.getAttribute("date")); %></td> 
		
		<td><input name="btnUpdate" type="button" value="Update"></td> 
		<td><input name="btnRemove" type="button" value="Remove"></td> 
</tr>		
</table> --%>



<!--  Prepare the html table to be displayed -->
 <%  channel channelsObj = new channel(); 
 out.print(channelsObj.readchannels()); %> 

</body>
</html>