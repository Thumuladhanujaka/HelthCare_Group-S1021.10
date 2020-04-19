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
	 
    <style>

        .elementz {
            border:none;
            background-image:none;
            background-color:transparent;
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
            outline: none;
            -webkit-appearance: none;
            color: #000;
        }

        /* The Modal (background) */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            padding-top: 100px; /* Location of the box */
            left: 0;
            top: 0;
            width: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0, 0, 0); /* Fallback color */
            background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
        }

        /* Modal Content */
        .modal-content1 {
            background-color: #fefefe;
            margin: auto;
            width: 40%;
            height: 80%;
            overflow-y: scroll;
            padding: 20px;
            border: 1px solid #888;
        }

        /* The Close Button */
        .close1 {
            color: #aaaaaa;
            float: right;
            margin-left: 95%;
            font-size: 28px;
            font-weight: bold;
        }

        .close1:hover,
        .close1:focus {
            color: #000;
            text-decoration: none;
            cursor: pointer;
        }

        .btn-file {
            position: relative;
            overflow: hidden;
        }
        .btn-file input[type=file] {
            position: absolute;
            top: 0;
            right: 0;
            min-width: 100%;
            min-height: 100%;
            font-size: 100px;
            text-align: right;
            filter: alpha(opacity=0);
            opacity: 0;
            outline: none;
            background: white;
            cursor: inherit;
            display: block;
        }

        #img-upload{
            width: 100%;
        }

        .my-error-class {
            color: red;
        }

        .my-valid-class {
            color: green;
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
	                    <div class="card-header">Visit List</div>
	                    <div class="card-body">
	                        <div class="p-1" id="usersDiv">
	                    	<table class="table">
			                    <thead>
					                <tr>
					                    <th class="tableTh">ID</th>
					                    <th class="tableTh">Name</th>
					                    <th class="tableTh">Age</th>
					                    <th class="tableTh">Gender</th>
					                    <th class="tableTh">Doctor Name</th>
					                    <th class="tableTh">Date</th>
					                    <th class="tableTh">Time</th>
					                    <th class="tableTh">Hospital</th>
					                    <th class="tableTh">Package/Price</th>
					                    <th class="tableTh">Action</th>
					                </tr>
				                </thead>
			                <tbody>
	                	<%
	                		appointmentService appointments = new appointmentService();
							ArrayList<Appointment> arrayList = appointments.getAppointment();
							
							for (Appointment appointment : arrayList) {
								
								String button = "<button type='button' onclick='edit("+appointment.getId()+")' class='btn btn-success'>Edit</button><button type='button' onclick='deletes("+appointment.getId()+")' class='btn btn-danger'>Delete</button>";
								
						%><tr>
							<td class="tableTh"><%=appointment.getId() %></td>
							<td class="tableTh"><%=appointment.getName() %></td>
							<td class="tableTh"><%=appointment.getAge() %></td>
							<td class="tableTh"><%=appointment.getGender() %></td>
							<td class="tableTh"><%=appointment.getDname() %></td>
							<td class="tableTh"><%=appointment.getDate() %></td>
							<td class="tableTh"><%=appointment.getTime() %></td>
							<td class="tableTh"><%=appointment.getHname() %></td>
							<td class="tableTh"><%=appointment.getPack() %></td>
							<td class="tableTh"><%=button %></td>
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
	        </div>
		</main>
	</div>

</body>
</html>
<div id="popupEdit" class="modal">
    <div class="modal-content1" style="height: 70%">
        <span class="close">&times;</span>
        <br>
        <div class="col-md-12">
            <form  id="editForm">
                <input type="hidden" id="edit_id">
                <div class="form-group">
                    <label for="Email">Patient Name</label>
                    <input type="text" id="e_name" class="form-control" name="e_name">
                </div>
                <div class="form-group">
                    <label for="Email">Patient Age</label>
                    <input type="number" id="age" class="form-control" name="age">
                </div>
                <div class="form-group">
                    <label for="Email">Patient Gender</label>
                    <select name="gender" class="form-control" id="gender">
                   		<option value="">Select</option>
                   		<option value="Male">Male</option>
                   		<option value="Female">Female</option>
                   	</select>
                </div>
                <div class="form-group">
                    <label for="Email">Doctor Name</label>
                    <select name="visitId" class="form-control" onchange="details()" id="visitId">
                       		<option value="">Select</option>
                           		<%
	                		visitService visits = new visitService();
							ArrayList<Visit> arrayList1 = visits.getVisit();
							
							for (Visit visit : arrayList1) {
								
						%>
							<option value="<%=visit.getId() %>"><%=visit.getName() %></option>
						<%
							}
						%>
                    </select>
                </div>
                <div class="form-group">
                    <label for="Email">Date</label>
                    <input type="date" id="date" class="form-control" name="date" readonly>
                </div>
                <div class="form-group">
                    <label for="Email">Time</label>
                    <input type="time" id="time" class="form-control" name="time" readonly>
                </div>
                <div class="form-group">
                    <label for="Email">Hospital</label>
                    <input type="text" id="hospital" class="form-control" name="hospital" readonly>
                </div>
                <div class="form-group">
                    <label for="Email">Packages / Price</label>
                    <input type="text" id="packages" class="form-control" name="packages" readonly>
                </div>
                <input type="submit" value="Submit" class="btn btn-primary">
            </form>
        </div>
    </div>
</div>

<script>

    var popupEdit = document.getElementById("popupEdit");

    var span = document.getElementsByClassName("close")[0];

    span.onclick = function () {
        popupEdit.style.display = "none";
    }

    function edit(id) {

    	$.ajax({
            type: "POST",
            url: "<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() %>/rest/appointment/getOne",
            data: JSON.stringify({ 'id' : id}),
            dataType: "json",
			contentType : 'application/json',
            success: function(data){
                $(e_name).val(data['name']),
                $(gender).val(data['gender']),
                $(age).val(data['age']),
                $(visitId).val(data['visitId']),
                $(hospital).val(data['hospital']),
                $(time).val(data['time']),
                $(date).val(data['date']),
                $(edit_id).val(data['id']),
                $(packages).val(data['pack']),
                popupEdit.style.display = "block";
            },
            failure: function(errMsg) {
                alert('Error');
            }
        });

        
    }
    
    function deletes(id) {

    	$.ajax({
            type: "POST",
            url: "<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() %>/rest/appointment/deletes",
            data: JSON.stringify({ 'id' : id}),
            dataType: "json",
			contentType : 'application/json',
            success: function(data){
            	if(data['success']=="1"){
					swal("Success!", "Delete Successfull!", "success");
					$("#usersDiv").load(location.href + " #usersDiv");
				}else if(data['success']=="0"){
					swal({
			            title: "Error",
			            text: "Delete Unsuccessful!",
			            icon: "warning",
			            dangerMode: true,
			        });
				}
            },
            failure: function(errMsg) {
                alert('Error');
            }
        });

        
    }

    $(document).ready(function () {

        $("#editForm").validate({
            errorClass: "my-error-class",
            validClass: "my-valid-class",
            rules: {
            	e_name: "required",
            	visitId: "required",
                gender: "required",
                age: {
                    number: true,
                    required: true
                }
            },
            messages: {
            	e_name: "Patient Name Required!",
            	visitId: "Select Doctor Required!",
            	gender: "Gender Required!",
            	age: {
                    number: "only numbers!",
                    required: "Age Required!"
                }
            },
            submitHandler: function () {
                var datas = JSON.stringify({
                    "id" : $('#edit_id').val(),
                    "name" : $('#e_name').val(),
                    "visitId" : $('#visitId').val(),
                    "gender" : $('#gender').val(),
                    "age" : $('#age').val()
                });

                $.ajax({
                    type: "POST",
                    url: '<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() %>/rest/appointment/edit',
                    dataType : 'json',
    				contentType : 'application/json',
    				data: datas,
                    success: function(data){
                    	if(data['success']=="1"){
    						swal("Success!", "Edit Successfull!", "success");
    						$(e_name).val("");
    		                $(gender).val("");
    		                $(age).val("");
    		                $(visitId).val("");
    		                $(hospital).val("");
    		                $(time).val("");
    		                $(date).val("");
    		                $(edit_id).val("");
    		                $(packages).val("");
    						popupEdit.style.display = "none";
    						$("#usersDiv").load(location.href + " #usersDiv");
    					}else if(data['success']=="0"){
    						swal({
    				            title: "Error",
    				            text: "This Hospital is Already Exists!",
    				            icon: "warning",
    				            dangerMode: true,
    				        });
    						$('#name').val("");
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

        $("#editForm").submit(function(e) {
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
                $(packages).val(data['pack']),
                popupEdit.style.display = "block";
            },
            failure: function(errMsg) {
                alert('Error');
            }
        });
	}
</script>