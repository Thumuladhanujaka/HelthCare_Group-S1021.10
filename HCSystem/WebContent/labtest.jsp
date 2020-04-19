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
	                    <div class="card-header">Lab Test</div>
	                    <div class="card-body">
	                        <form id="payment">
	                            <div class="form-group row">
	                                <label for="email_address" class="col-md-4 col-form-label text-md-right">Patient Name</label>
	                                <div class="col-md-6">
	                                    <input type="text" id="name" class="form-control" name="name">
	                                </div>
	                            </div>
	                            
	                            <div class="form-group row">
	                                <label for="email_address" class="col-md-4 col-form-label text-md-right">Date</label>
	                                <div class="col-md-6">
	                                    <input type="date" id="date" class="form-control" name="date">
	                                </div>
	                            </div>
	                            
	                            <div class="form-group row">
	                                <label for="email_address" class="col-md-4 col-form-label text-md-right">Description</label>
	                                <div class="col-md-6">
	                                    <input type="text" id="description" class="form-control" name="description">
	                                </div>
	                            </div>
	                            
	                            <div class="form-group row">
	                                <label for="email_address" class="col-md-4 col-form-label text-md-right">Test Name</label>
	                                <div class="col-md-6">
	                                    <input type="text" id="test" class="form-control" name="test">
	                                </div>
	                            </div>

								<div class="col-md-6 offset-md-4">
	                                <button type="submit" class="btn btn-primary">
	                                    Add
	                                </button>
	                                <a href="testList.jsp" class="btn btn-primary">
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
        	date: "required",
        	description: "required",
        	test: "required"
        },
        messages: {
        	name: "Patient Name Required!",
        	date: "Date Required!",
        	description: "Description Required!",
            test: "Test Name Required!"
        },
        submitHandler: function () {
            var datas = JSON.stringify({
                "name" : $('#name').val(),
                "date" : $('#date').val(),
                "description" : $('#description').val(),
                "test" : $('#test').val()
            });

            $.ajax({
                type: "POST",
                url: '<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() %>/rest/lab/add',
                dataType : 'json',
				contentType : 'application/json',
				data: datas,
                success: function(data){
                	if(data['success']=="1"){
						swal("Success!", "Added Successfull!", "success");
						$('#name').val("");
						$('#date').val("");
						$('#description').val("");
						$('#test').val("");
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

</script>

