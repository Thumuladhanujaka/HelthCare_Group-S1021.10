<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.2.1.min.js"></script> 
<script src="Components/main.js"></script>   
</head>


<body>
	<div class="container">
		<div class="row">
			<div class="col-8">

				<h1 class="m-3">Visiting Details</h1>

				<form id="formStudent">
				
					<!-- hospital name -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Hospital Name: </span>
						</div>
						<input type="text" id="txtName" name="txtName">
					</div>
					
					<!-- hospital name -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Hospital City: </span>
						</div>
						<input type="text" id="txtName" name="txtName">
					</div>
					
					<!-- date -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Date: </span>
						</div>
						<input type="text" id="txtName" name="txtName">
					</div>
					
					<!-- no of patients -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">No. of Patients: </span>
						</div>
						<input type="text" id="txtName" name="txtName">
					</div>
					

					<!-- GENDER -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Gender : </span>
						</div>
						&nbsp;&nbsp;Male <input type="radio" id="rdoGenderMale"
							name="rdoGender" value="Male"> &nbsp;&nbsp;Female <input
							type="radio" id="rdoGenderFemale" name="rdoGender" value="Female">
					</div>

					<!-- YEAR -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Visiting Time : </span>
						</div>
						<select id="ddlYear" name="ddlYear">
							<option value="0">--Time Period--</option>
							<option value="1">6.00 - 9.00 AM</option>
							<option value="2">9.00 - 12.00 AM</option>
							<option value="3">12.00 - 3.00 PM</option>
							<option value="4">3.00 - 6.00 PM</option>
						</select>
					</div>

					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>

					<input type="button" id="btnSave" value="Submit "
						class="btn btn-primary">
				</form>
			</div>
		</div>
		<br>


		<div class="row">
			<div class="col-12" id="colStudents"></div>
			
		</div>

	</div>
</body>


</html>