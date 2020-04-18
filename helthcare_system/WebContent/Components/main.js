$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "")  
	{  
		$("#alertSuccess").hide();  
	}  
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click","#btnSave",function(event) 
		{
			// Clear status msges-------------
			$("#alertSuccess").text("");
			$("#alertSuccess").hide();
			$("#alertError").text("");
			$("#alertError").hide();

			// Form validation----------------
			var status = validateVisitForm();

			// If not valid-------------------
			if (status != true) {
				$("#alertError").text(status);
				$("#alertError").show();
				return;
			}

			// If valid----------------------
			$("#formVisit").submit();
		/*	var student = getStudentCard($("#txtName").val().trim(), $(
					'input[name="rdoGender"]:checked').val(), $("#ddlYear")
					.val());
			$("#colStudents").append(student);
			$("#alertSuccess").text("Saved successfully.");
			$("#alertSuccess").show();
			$("#formStudent")[0].reset();*/
		});
//UPDATE==========================================
$(document).on("click",".btnUpdate",function(event) 
		{
			$("#hidVisitIDSave").val($(this).closest("tr").find('#hidVisitIDUpdate').val());
			$("#hospital_name").val($(this).closest("tr").find('td:eq(0)').text());
			$("#hospital_city").val($(this).closest("tr").find('td:eq(1)').text());
			$("#date").val($(this).closest("tr").find('td:eq(2)').text());
			$("#time").val($(this).closest("tr").find('td:eq(3)').text());
			$("#noPatients").val($(this).closest("tr").find('td:eq(4)').text());

		});
// REMOVE==========================================
$(document).on("click", ".remove", function(event) {
	$(this).closest(".student").remove();
	$("#alertSuccess").text("Removed successfully.");
	$("#alertSuccess").show();
});

// CLIENT-MODEL=================================================================
function validateVisitForm() {
	// NAME
	/*if ($("#txtName").val().trim() == "") {
		return "Insert student name.";
	}

	// GENDER
	if ($('input[name="rdoGender"]:checked').length === 0) {
		return "Select gender.";
	}

	// YEAR
	if ($("#ddlYear").val() == "0") {
		return "Select year.";
	}*/
if ($("#hospital_name").val().trim() == "") {
		return "Insert Hospital Name.";
	}
	
	if ($("#hospital_city").val().trim() == "") {
		return "Insert Hospital City.";
	}
	if ($("#date").val().trim() == "") {
		return "Insert Date.";
	}
	if ($("#time").val().trim() == "") {
		return "Insert Visiting Time.";
	}
	if ($("#noPatients").val().trim() == "") {
		return "Insert No. of Channels.";
	}

	return true;
}

/*function getStudentCard(name, gender, year) {
	var title = (gender == "Male") ? "Mr." : "Ms.";
	var yearNumber = "";

	switch (year) {
	case "1":
		yearNumber = "1st";
		break;
	case "2":
		yearNumber = "2nd";
		break;
	case "3":
		yearNumber = "3rd";
		break;
	case "4":
		yearNumber = "4th";
		break;
	}

	var student = "";
	student += "<div class=\"student card bg-light m-2\"       style=\"max-width: 10rem; float: left;\">";
	student += "<div class=\"card-body\">";
	student += title + " " + name + ",";
	student += "<br>";
	student += yearNumber + " year";
	student += "</div>";
	student += "<input type=\"button\" value=\"Remove\"         class=\"btn btn-danger remove\">";
	student += "</div>";

	return student;
}*/
