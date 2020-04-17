package com;

import model.Laboratory;
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON 
import com.google.gson.*; 
//For XML 
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
 
@Path("/Patients") 
public class LaboratoryService {
	Laboratory lab = new Laboratory(); 
	
	@GET  
	@Path("/")  
	@Produces(MediaType.TEXT_HTML)  
	public String readPatients() {
		return lab.readPatients();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertlaboratory(@FormParam("labCode") String labCode, @FormParam("labName") String labName,
	@FormParam("labHospital") String labHospital, @FormParam("labTests") String labTests,
	@FormParam("labInCharge") String labInCharge, @FormParam("labInChargeID") String labInChargeID) {
		String output = lab.insertlaboratory(labCode, labName, labHospital, labTests, labInCharge, labInChargeID);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatelaboratory(String laboratoryData) {
		//Convert the input string to a JSON object
		JsonObject laboratoryObject = new JsonParser().parse(laboratoryData).getAsJsonObject();
		//Read the values from the JSON object
		String labId = laboratoryObject.get("labId").getAsString();
		String labCode = laboratoryObject.get("labCode").getAsString();
		String labName = laboratoryObject.get("labName").getAsString();
		String labHospital = laboratoryObject.get("labHospital").getAsString();
		String labTests = laboratoryObject.get("labTests").getAsString();
		String labInCharge = laboratoryObject.get("labInCharge").getAsString();
		String labInChargeID = laboratoryObject.get("labInChargeID").getAsString();
		String output = lab.updatelaboratory(labId, labCode, labName, labHospital, labTests, labInCharge, labInChargeID);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletelaboratory(String laboratoryData) {
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(laboratoryData, "", Parser.xmlParser());

		//Read the value from the element <patientID>
		String labID = doc.select("labID").text();
		String output = lab.deletelaboratory(labID);
		return output;
	}
	
} 
 