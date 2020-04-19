<%@page import="health.service.*"%>
<%@page import="health.model.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Health Care System</title>
	
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/croppie.js"></script>
    <link rel="stylesheet" href="assets/css/croppie.css" />

    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <script src="assets/js/sweetalert.min.js"></script>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.js"></script>
	<style type="text/css">
        .my-error-class {
            color:red;
        }
        .my-valid-class {
            color:green;
        }
    </style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
    	<div class="container">
	        <a class="navbar-brand" href="profileServlet">Health Care System</a>
	        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	            <span class="navbar-toggler-icon"></span>
	        </button>
	
	        <div class="collapse navbar-collapse" id="navbarSupportedContent">
	            <ul class="navbar-nav ml-auto">
	            <% if(session.getAttribute("userEmail")==null){ %>
	                <li class="nav-item">
	                    <a class="nav-link" href="login.jsp">Login</a>
	                </li>
	                <li class="nav-item">
	                    <a class="nav-link" href="register.jsp">Register</a>
	                </li>
	                <%}else{ %>
	                <li class="nav-item">
	                    <a class="nav-link" href="logoutServlet">Logout</a>
	                </li>
	                <%} %>
	            </ul>
	
	        </div>
        </div>
	</nav>
    <div class="container">
		
<br>
<p></p>
		<main class="login-form">
	        <div class="row justify-content-center">
	            <div class="col-md-10">
	                <div class="card">
	                    <div class="card-header">System Users</div>
	                    <div class="p-1" id="usersDiv">
	                    	<table class="table">
			                    <thead>
					                <tr>
					                    <th class="tableTh">User ID</th>
					                    <th class="tableTh">First Name</th>
					                    <th class="tableTh">Last Name</th>
					                    <th class="tableTh">Email</th>
					                    <th class="tableTh">Phone</th>
					                    <th class="tableTh">Patient</th>
					                    <th class="tableTh">Doctor</th>
					                    <th class="tableTh">Admin</th>
					                    <th class="tableTh">Remove User</th>
					                </tr>
				                </thead>
			                <tbody>
	                	<%
			    			registerService users = new registerService();
							ArrayList<User> arrayList = users.getUser((String)session.getAttribute("userEmail"));
							
							for (User user : arrayList) {
								
								String buyer = "",admin="",seller="";
								
								if(user.getAdmin().equals("0")){
									buyer = "";
								}else{
									buyer = "<p id='"+user.getId()+"' style='cursor: pointer' onclick='clickUser(this);'>X</p>";
								}
								
								if(user.getAdmin().equals("1")){
									admin = "";
								}else{
									admin = "<p id='"+user.getId()+"' style='cursor: pointer' onclick='clickAdmin(this);'>X</p>";
								}
								
								if(user.getAdmin().equals("2")){
									seller = "";
								}else{
									seller = "<p id='"+user.getId()+"' style='cursor: pointer' onclick='clickSeller(this);'>X</p>";
								}
								
						%><tr>
							<td class="tableTh"><%=user.getId() %></td>
							<td class="tableTh"><%=user.getFname() %></td>
							<td class="tableTh"><%=user.getLname() %></td>
							<td class="tableTh"><%=user.getEmail() %></td>
							<td class="tableTh"><%=user.getPhone() %></td>
							<td class="tableTh"><%=buyer %></td>
							<td class="tableTh"><%=seller %></td>
							<td class="tableTh"><%=admin %></td>
							<td class="tableTh"><p id="<%=user.getId() %>" style="cursor: pointer" onclick="clickDelete(this);">X</p></td>
						  </tr>
						<%
							}
						%>
						</tbody>
	            </table>
	            </div>
	                </div>
	            </div>
	        </div>
		</main>
	</div>

</body>
</html>
<<script>

	function clickUser(id){
		
		var r = confirm("If you want this user type change to Patient ?");
		
		if (r == true) {
		  
			var user = id.id;
			
			var jsonfile = JSON.stringify({
				"id" : user
			});
			
			var ans = $.ajax({
				type : 'POST',
				url : '<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() %>/rest/user/createUser',
				dataType : 'json',
				contentType : 'application/json',
				data : jsonfile
			});
			
			ans.done(function(data){
				if(data['success']=="1"){
					alert("Edit Successfull!");
					$('#usersDiv').empty();
					$("#usersDiv").load(location.href + " #usersDiv");
				}else if(data['success']=="0"){
					alert("Edit Unuccessfull!");
				}
			});
			ans.fail(function(data){
				alert("Connection Error !");
			});
			
		}
		
	}
	
	function clickAdmin(id){
		
		var r = confirm("If you want this user type change to Admin ?");
		
		if (r == true) {
		  
			var user = id.id;
			
			var jsonfile = JSON.stringify({
				"id" : user
			});
			
			var ans = $.ajax({
				type : 'POST',
				url : '<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() %>/rest/user/createAdmin',
				dataType : 'json',
				contentType : 'application/json',
				data : jsonfile
			});
			
			ans.done(function(data){
				if(data['success']=="1"){
					alert("Edit Successfull!");
					$('#usersDiv').empty();
					$("#usersDiv").load(location.href + " #usersDiv");
				}else if(data['success']=="0"){
					alert("Edit Unuccessfull!");
				}
			});
			ans.fail(function(data){
				alert("Connection Error !");
			});
			
		}
		
	}
	
	function clickSeller(id){
		
		var r = confirm("If you want this user type change to Doctor ?");
		
		if (r == true) {
		  
			var user = id.id;
			
			var jsonfile = JSON.stringify({
				"id" : user
			});
			
			var ans = $.ajax({
				type : 'POST',
				url : '<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() %>/rest/user/createSeller',
				dataType : 'json',
				contentType : 'application/json',
				data : jsonfile
			});
			
			ans.done(function(data){
				if(data['success']=="1"){
					alert("Edit Successfull!");
					$('#usersDiv').empty();
					$("#usersDiv").load(location.href + " #usersDiv");
				}else if(data['success']=="0"){
					alert("Edit Unuccessfull!");
				}
			});
			ans.fail(function(data){
				alert("Connection Error !");
			});
			
		}
		
	}
	
	function clickDelete(id){
		
		var r = confirm("If you want remove this user ?");
		
		if (r == true) {
		  
			var user = id.id;
			
			var jsonfile = JSON.stringify({
				"id" : user
			});
			
			var ans = $.ajax({
				type : 'POST',
				url : '<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() %>/rest/user/removeUser',
				dataType : 'json',
				contentType : 'application/json',
				data : jsonfile
			});
			
			ans.done(function(data){
				if(data['success']=="1"){
					alert("Remove Successfull!");
					$('#usersDiv').empty();
					$("#usersDiv").load(location.href + " #usersDiv");
				}else if(data['success']=="0"){
					alert("Remove Unuccessfull!");
				}
			});
			ans.fail(function(data){
				alert("Connection Error !");
			});
			
		}
		
	}

</script>



