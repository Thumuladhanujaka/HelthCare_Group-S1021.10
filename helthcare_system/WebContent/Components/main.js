
$(document).ready(function() {
	/*if ($("#alertSuccess").text().trim() == "")  
	{  
		$("#alertSuccess").hide();  
	}  */
	$("#alertSuccess").hide(); 
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
			$("#alertSuccess").text("Saved successfully.");
			
			// If valid----------------------
			$("#formVisit").submit();
		/*
		 * var student = getStudentCard($("#txtName").val().trim(), $(
		 * 'input[name="rdoGender"]:checked').val(), $("#ddlYear") .val());
		 * $("#colStudents").append(student); $("#alertSuccess").text("Saved
		 * successfully."); $("#alertSuccess").show();
		 * $("#formStudent")[0].reset();
		 */
		});

// UPDATE==========================================
$(document).on("click",".btnUpdate",function(event) 
		{
			$("#hidVisitIDSave").val($(this).closest("tr").find('#hidVisitIDUpdate').val());
			$("#hospital_name").val($(this).closest("tr").find('td:eq(0)').text());
			$("#hospital_city").val($(this).closest("tr").find('td:eq(1)').text());
			$("#date").val($(this).closest("tr").find('td:eq(2)').text());
			$("#time").val($(this).closest("tr").find('td:eq(3)').text());
			$("#noPatients").val($(this).closest("tr").find('td:eq(4)').text());
			$("#alertSuccess").text("Up successfully.");
		});


//REMOVE========================================== 
$(document).on("click", ".btnRemove", function(event) 
{ 
	$(this).closest(".visit").btnRemove();  
	$("#alertSuccess").text("Removed successfully.");
	$("#alertSuccess").show();
}); 


// CLIENT-MODEL=================================================================
function validateVisitForm() 
{
	if ($("#hospital_name").val().trim() == "") 
	{
		return "Insert Hospital Name.";
	}
	
	if ($("#hospital_city").val().trim() == "") 
	{
		return "Insert Hospital City.";
	}
	if ($("#date").val().trim() == "") 
	{
		return "Insert Date.";
	}
	if ($("#time").val().trim() == "") 
	{
		return "Insert Visiting Time.";
	}
	if ($("#noPatients").val().trim() == "") 
	{
		return "Insert No. of Channels.";
	}

	return true;
}
