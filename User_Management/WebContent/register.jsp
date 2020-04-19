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
	                    <div class="card-header">Register</div>
	                    <div class="card-body">
	                        <form id="register">
	                            <div class="form-group row">
	                                <label for="email_address" class="col-md-4 col-form-label text-md-right">First Name</label>
	                                <div class="col-md-6">
	                                    <input type="text" id="fname" class="form-control" name="fname">
	                                </div>
	                            </div>
	                            
	                            <div class="form-group row">
	                                <label for="email_address" class="col-md-4 col-form-label text-md-right">Last Name</label>
	                                <div class="col-md-6">
	                                    <input type="text" id="lname" class="form-control" name="lname">
	                                </div>
	                            </div>
	
	                            <div class="form-group row">
	                                <label for="email_address" class="col-md-4 col-form-label text-md-right">Phone Number</label>
	                                <div class="col-md-6">
	                                    <input type="text" id="phone" class="form-control" name="phone">
	                                </div>
	                            </div>
	                            
	                            <div class="form-group row">
	                                <label for="email_address" class="col-md-4 col-form-label text-md-right">Email</label>
	                                <div class="col-md-6">
	                                    <input type="text" id="email" class="form-control" name="email">
	                                </div>
	                            </div>
	                            
	                            <div class="form-group row">
	                                <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
	                                <div class="col-md-6">
	                                    <input type="password" id="password" class="form-control" name="password">
	                                </div>
	                            </div>
	                            
	                            <div class="form-group row">
	                                <label for="password" class="col-md-4 col-form-label text-md-right">Confirm Password</label>
	                                <div class="col-md-6">
	                                    <input type="password" id="cpassword" class="form-control" name="cpassword">
	                                </div>
	                            </div>
	
	                            <div class="col-md-6 offset-md-4">
	                                <button type="submit" class="btn btn-primary">
	                                    Register
	                                </button>
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

    $("#register").validate({
        errorClass: "my-error-class",
        validClass: "my-valid-class",
        rules: {
        	fname: "required",
        	lname: "required",
        	phone: {
        		minlength: 10,
        		maxlength: 10,
                number: true,
                required: true
            },
        	email: {
                email: true,
                required: true
            },
        	password: "required",
        	cpassword: {
        		required: true,
        		equalTo: "#password"
        	}
        },
        messages: {
        	fname: "First Name Required!",
        	lname: "Last Name Required!",
        	phone: {
        		minlength: "incorrect phone number!",
        		maxlength: "incorrect phone number!",
                number: "only numbers!",
                required: "Phone Number Required!"
            },
            email: {
                email: "Invalid email!",
                required: "Email Required!"
            },
            password: "Password Required!",
        	cpassword: {
        		required: "Confirm Password Required!",
        		equalTo: "Password & Confirm Password not equal!"
        	}
        },
        submitHandler: function () {
            var datas = JSON.stringify({
                "fname" : $('#fname').val(),
                "lname" : $('#lname').val(),
                "phone" : $('#phone').val(),
                "email" : $('#email').val(),
                "pass1" : $('#password').val(),
            });

            $.ajax({
                type: "POST",
                url: '<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() %>/rest/user/register',
                dataType : 'json',
				contentType : 'application/json',
				data: datas,
                success: function(data){
                	if(data['success']=="1"){
						swal("Success!", "Your Account Has Been Registered. You Can Login Now!", "success");
						$('#fname').val("");
						$('#lname').val("");
						$('#email').val("");
						$('#phone').val("");
						$('#password').val("");
						$('#cpassword').val("");
					}else if(data['success']=="0"){
						swal({
				            title: "Error",
				            text: "This Email is Already Exists!",
				            icon: "warning",
				            dangerMode: true,
				        });
						$('#email').val("");
					}else if(data['success']=="2"){
						swal({
				            title: "Error",
				            text: "This Phone is Already Exists!",
				            icon: "warning",
				            dangerMode: true,
				        });
						$('#phone').val("");
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

    $("#register").submit(function(e) {
        e.preventDefault();
    });

});

</script>
