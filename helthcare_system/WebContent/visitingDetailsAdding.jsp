<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Visiting details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-8">
				<h1 class="m-3">Doctor Visiting Details</h1>
				
				<form id="formVisit">
				
					<!-- NAME -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Hospital Name:
							</span>
						</div>
						<input type="text" id="hospital_name" name="hospital_name">
					</div>

					<!-- CITY -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Hospital City:
							</span>
						</div>
						<input type="text" id="hospital_city" name="hospital_city">
					</div>
					
					<!-- Date -->
					<div class="input-group input-group-sm mb-3">

						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Date: </span>
						</div>
						<input type="text" id="date" name="date">
					</div>


					<!-- TIME -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Visiting Time:
							</span>
						</div>
						<select id="time" name="time">
							<option value="0">--Select Time--</option>
							<option value="1">6.00 - 9.00 AM</option>
							<option value="2">9.00 - 12.00 AM</option>
							<option value="3">12.00 - 3.00 PM</option>
							<option value="4">3.00 - 6.00 PM</option>
						</select>
					</div>
					
					<!-- no. of patients -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">No. of Channels: </span>
						</div>
						<input type="text" id="noPatients" name="noPatients">
					</div>



					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>

					<input type="button" id="btnSave" value="Save"
						class="btn btn-primary">
				</form>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-12" id="colVisits"></div>
		</div>

	</div>
</body>
</html>