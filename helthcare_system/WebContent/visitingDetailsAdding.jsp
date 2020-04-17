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

				<h1 class="m-3">Student details</h1>

				<form id="formStudent">

					<!-- NAME -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Name: </span>
						</div>
						<input type="text" id="txtName" name="txtName">
					</div>
					<!-- GENDER -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Gender: </span>
						</div>
						&nbsp;&nbsp;Male <input type="radio" id="rdoGenderMale"
							name="rdoGender" value="Male"> &nbsp;&nbsp;Female <input
							type="radio" id="rdoGenderFemale" name="rdoGender" value="Female">
					</div>
					<!-- YEAR -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Year: </span>
						</div>
						<select id="ddlYear" name="ddlYear">
							<option value="0">--Select year--</option>
							<option value="1">1st year</option>
							<option value="2">2nd year</option>
							<option value="3">3rd year</option>
							<option value="4">4th year</option>
						</select>
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
			<div class="col-12" id="colStudents"></div>
		</div>
	</div>
</body>


</html>