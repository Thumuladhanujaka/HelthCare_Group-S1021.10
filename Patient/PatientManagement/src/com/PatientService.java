package com;

import model.Patient;
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
public class PatientService {
	Patient pat = new Patient(); 
	
	@GET  
	@Path("/")  
	@Produces(MediaType.TEXT_HTML)  
	public String readPatients() {
		return pat.readPatients();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPatient(@FormParam("patientCode") String patientCode, @FormParam("patientName") String patientName,
	@FormParam("patientAge") String patientAge, @FormParam("patientGender") String patientGender,
	@FormParam("patientAddress") String patientAddress, @FormParam("patientMobile") String patientMobile) {
		String output = pat.insertPatient(patientCode, patientName, patientAge, patientGender, patientAddress, patientMobile);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatient(String patientData) {
		//Convert the input string to a JSON object
		JsonObject patientObject = new JsonParser().parse(patientData).getAsJsonObject();
		//Read the values from the JSON object
		String patientId = patientObject.get("patientId").getAsString();
		String patientCode = patientObject.get("patientCode").getAsString();
		String patientName = patientObject.get("patientName").getAsString();
		String patientAge = patientObject.get("patientAge").getAsString();
		String patientGender = patientObject.get("patientGender").getAsString();
		String patientAddress = patientObject.get("patientAddress").getAsString();
		String patientMobile = patientObject.get("patientMobile").getAsString();
		String output = pat.updatePatient(patientId, patientCode, patientName, patientAge, patientGender, patientAddress, patientMobile);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatient(String patientData) {
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(patientData, "", Parser.xmlParser());

		//Read the value from the element <patientID>
		String patientID = doc.select("patientID").text();
		String output = pat.deletePatient(patientID);
		return output;
	}
	
} 
 