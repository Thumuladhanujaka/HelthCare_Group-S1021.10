package health.rest;

import java.sql.SQLException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.json.simple.*;
import org.json.simple.parser.*;

import health.model.*;
import health.service.*;

@Path("/hospital")
public class hospital {
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public String myMethod(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		if(jobj.get("name").toString()!=""||jobj.get("wards").toString()!=""||jobj.get("beds").toString()!=""||jobj.get("rooms").toString()!="") {
			
			// create object hospital
			Hospital hospital =  new Hospital();
			
			hospital.setName(jobj.get("name").toString());
			hospital.setBeds(Integer.parseInt(jobj.get("beds").toString()));
			hospital.setWards(Integer.parseInt(jobj.get("wards").toString()));
			hospital.setRooms(Integer.parseInt(jobj.get("rooms").toString()));
			
			hospitalService hospitals = new hospitalService();
			hospitals.addHospitals(hospital);
			
			JSONObject json = new JSONObject();
			json.put("success", Integer.toString(hospitals.getSuccess()));
			
			return json.toString();
			
		}else {
			JSONObject json = new JSONObject();
			json.put("success", 0);
			
			return json.toString();
		}
		
	}
	
	@POST
	@Path("/edit")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String editProduct(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		// create object hospital
		Hospital hospital =  new Hospital();
		
		hospital.setId(Integer.parseInt(jobj.get("id").toString()));
		hospital.setName(jobj.get("name").toString());
		hospital.setBeds(Integer.parseInt(jobj.get("beds").toString()));
		hospital.setWards(Integer.parseInt(jobj.get("wards").toString()));
		hospital.setRooms(Integer.parseInt(jobj.get("rooms").toString()));
		
		hospitalService hospitals = new hospitalService();
		hospitals.editHospitals(hospital);
		
		JSONObject json = new JSONObject();
		json.put("success", Integer.toString(hospitals.getSuccess()));
		
		return json.toString();
		
	}
	
	@POST
	@Path("/getOne")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getHospital(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		hospitalService get = new hospitalService();
		Hospital hospital = get.getHospital(Integer.parseInt(jobj.get("id").toString()));
		
		JSONObject json = new JSONObject();
		json.put("id", hospital.getId());
		json.put("name", hospital.getName());
		json.put("beds", hospital.getBeds());
		json.put("wards", hospital.getWards());
		json.put("rooms", hospital.getRooms());
		
		return json.toString();
		
	}
	
	
	@POST
	@Path("/deletes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteProduct(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		hospitalService delete = new hospitalService();
		delete.deleteHospital(Integer.parseInt(jobj.get("id").toString()));
		
		JSONObject json = new JSONObject();
		json.put("success", Integer.toString(delete.getSuccess()));
		
		return json.toString();
		
	}

}
