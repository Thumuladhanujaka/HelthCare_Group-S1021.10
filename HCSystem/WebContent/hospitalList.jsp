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
	            <div class="col-md-8">
	                <div class="card">
	                    <div class="card-header">Hospital List</div>
	                    <div class="card-body">
	                        <div class="p-1" id="usersDiv">
	                    	<table class="table">
			                    <thead>
					                <tr>
					                    <th class="tableTh">ID</th>
					                    <th class="tableTh">Name</th>
					                    <th class="tableTh">Wards</th>
					                    <th class="tableTh">Beds</th>
					                    <th class="tableTh">Rooms</th>
					                    <th class="tableTh">Action</th>
					                </tr>
				                </thead>
			                <tbody>
	                	<%
	                		hospitalService hospitals = new hospitalService();
							ArrayList<Hospital> arrayList = hospitals.getHospital();
							
							for (Hospital hospital : arrayList) {
								
								String button = "<button type='button' onclick='edit("+hospital.getId()+")' class='btn btn-success'>Edit</button><button type='button' onclick='deletes("+hospital.getId()+")' class='btn btn-danger'>Delete</button>";
								
						%><tr>
							<td class="tableTh"><%=hospital.getId() %></td>
							<td class="tableTh"><%=hospital.getName() %></td>
							<td class="tableTh"><%=hospital.getWards() %></td>
							<td class="tableTh"><%=hospital.getBeds() %></td>
							<td class="tableTh"><%=hospital.getRooms() %></td>
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
                    <label for="Email">Name</label>
                    <input type="text" id="e_name" class="form-control" name="e_name">
                </div>
                <div class="form-group">
                    <label for="Email">No Of Wards</label>
                    <input type="number" id="wards" class="form-control" name="wards">
                </div>
                <div class="form-group">
                    <label for="subject">No Of Beds</label>
                    <input type="number" id="beds" class="form-control" name="beds">
                </div>
                <div class="form-group">
                    <label for="message">No Of Channeling Rooms</label>
                    <input type="number" id="rooms" class="form-control" name="rooms">
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
            url: "<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() %>/rest/hospital/getOne",
            data: JSON.stringify({ 'id' : id}),
            dataType: "json",
			contentType : 'application/json',
            success: function(data){
                console.log(data['name']);
                $(e_name).val(data['name']),
                $(beds).val(data['beds']),
                $(wards).val(data['wards']),
                $(rooms).val(data['rooms']),
                $(edit_id).val(data['id']),
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
            url: "<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() %>/rest/hospital/deletes",
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
            	wards: {
                    number: true,
                    required: true
                },
            	beds: {
                    number: true,
                    required: true
                },
                rooms: {
                    number: true,
                    required: true
                }
            },
            messages: {
            	e_name: "Name Required!",
            	wards: {
                    number: "only numbers!",
                    required: "Wards Required!"
                },
                beds: {
                    number: "only numbers!",
                    required: "Beds Required!"
                },
                rooms: {
                    number: "only numbers!",
                    required: "Rooms Required!"
                }
            },
            submitHandler: function () {
                var datas = JSON.stringify({
                    "id" : $('#edit_id').val(),
                    "name" : $('#e_name').val(),
                    "wards" : $('#wards').val(),
                    "beds" : $('#beds').val(),
                    "rooms" : $('#rooms').val()
                });

                $.ajax({
                    type: "POST",
                    url: '<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() %>/rest/hospital/edit',
                    dataType : 'json',
    				contentType : 'application/json',
    				data: datas,
                    success: function(data){
                    	if(data['success']=="1"){
    						swal("Success!", "Edit Successfull!", "success");
    						$('#edit_id').val("");
    						$('#e_name').val("");
    						$('#wards').val("");
    						$('#beds').val("");
    						$('#rooms').val("");
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

    
</script>