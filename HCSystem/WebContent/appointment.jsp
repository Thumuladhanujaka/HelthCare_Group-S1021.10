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
	            <div class="col-md-8">
	                <div class="card">
	                    <div class="card-header">Appointments</div>
	                    <div class="card-body">
	                        <form id="payment">
	                            <div class="form-group row">
	                                <label for="email_address" class="col-md-4 col-form-label text-md-right">Patient Name</label>
	                                <div class="col-md-6">
	                                    <input type="text" id="name" class="form-control" name="name">
	                                </div>
	                            </div>
	                            
	                            <div class="form-group row">
	                                <label for="email_address" class="col-md-4 col-form-label text-md-right">Patient age</label>
	                                <div class="col-md-6">
	                                    <input type="number" id="age" class="form-control" name="age">
	                                </div>
	                            </div>
	                            
	                            <div class="form-group row">
	                                <label for="email_address" class="col-md-4 col-form-label text-md-right">Patient Gender</label>
	                                <div class="col-md-6">
	                                    <select name="gender" class="form-control" id="gender">
	                                		<option value="">Select</option>
	                                		<option value="Male">Male</option>
	                                		<option value="Female">Female</option>
	                                	</select>
	                                </div>
	                            </div>
	                            
	                            <div class="form-group row">
	                                <label for="email_address" class="col-md-4 col-form-label text-md-right">Doctor Name</label>
	                                <div class="col-md-6">
	                                	<select name="visitId" class="form-control" onchange="details()" id="visitId">
	                                		<option value="">Select</option>
	                                		<%
						                		visitService visits = new visitService();
												ArrayList<Visit> arrayList = visits.getVisit();
												
												for (Visit visit : arrayList) {
													
											%>
												<option value="<%=visit.getId() %>"><%=visit.getName() %></option>
											<%
												}
											%>
	                                	</select>
	                                </div>
	                            </div>
	                            
	                            <div class="form-group row">
	                                <label for="email_address" class="col-md-4 col-form-label text-md-right">Date</label>
	                                <div class="col-md-6">
	                                    <input type="text" id="date" class="form-control" readonly>
	                                </div>
	                            </div>
	                            
	                            <div class="form-group row">
	                                <label for="email_address" class="col-md-4 col-form-label text-md-right">Time</label>
	                                <div class="col-md-6">
	                                    <input type="text" id="time" class="form-control" readonly>
	                                </div>
	                            </div>
	                            
	                            <div class="form-group row">
	                                <label for="email_address" class="col-md-4 col-form-label text-md-right">Hospital</label>
	                                <div class="col-md-6">
	                                    <input type="text" id="hospital" class="form-control" readonly>
	                                </div>
	                            </div>
	                            
	                            <div class="col-md-6 offset-md-4">
	                                <button type="submit" class="btn btn-primary">
	                                    Add
	                                </button>
	                                <a href="appointmentList.jsp" class="btn btn-primary">
	                                    List
	                                </a>
	                            </div>
	                    	</form>
	                    </div>
	                </div>
	            </div>
	        </div>
		</main>
	</div>

</body>
</html>
<script>

$(document).ready(function () {

    $("#payment").validate({
        errorClass: "my-error-class",
        validClass: "my-valid-class",
        rules: {
        	name: "required",
        	visitId: "required",
            gender: "required",
            age: {
                number: true,
                required: true
            }
        },
        messages: {
        	name: "Patient Name Required!",
        	visitId: "Select Doctor Required!",
        	gender: "Gender Required!",
        	age: {
                number: "only numbers!",
                required: "Age Required!"
            }
        },
        submitHandler: function () {
            var datas = JSON.stringify({
                "name" : $('#name').val(),
                "visitId" : $('#visitId').val(),
                "gender" : $('#gender').val(),
                "age" : $('#age').val()
            });

            $.ajax({
                type: "POST",
                url: '<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() %>/rest/appointment/add',
                dataType : 'json',
				contentType : 'application/json',
				data: datas,
                success: function(data){
                	if(data['success']=="1"){
						swal("Success!", "Added Successfull!", "success");
						$('#name').val("");
						$('#visitId').val("");
						$('#gender').val("");
						$('#age').val("");
						$(hospital).val("");
		                $(time).val("");
		                $(date).val("");
					}
                },
                failure: function(errMsg) {
                	swal({
			            title: "Error",
			            text: "Connection Error!",
			            icon: "warning",
			            dangerMode: true,
			        });
                }
            });
        }
    });

    $("#payment").submit(function(e) {
        e.preventDefault();
    });

});


	function details(){
		var id = $("#visitId").val();
		$.ajax({
            type: "POST",
            url: "<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() %>/rest/appointment/getDetails",
            data: JSON.stringify({ 'id' : id }),
            dataType: "json",
			contentType : 'application/json',
            success: function(data){
                $(hospital).val(data['hospital']),
                $(time).val(data['time']),
                $(date).val(data['date']),
                popupEdit.style.display = "block";
            },
            failure: function(errMsg) {
                alert('Error');
            }
        });
	}

</script>