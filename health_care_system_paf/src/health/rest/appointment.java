package health.rest;

import java.sql.SQLException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.json.simple.*;
import org.json.simple.parser.*;

import health.model.*;
import health.service.*;

@Path("/appointment")
public class appointment {
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String myMethod(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		if(jobj.get("name").toString()!=""||jobj.get("age").toString()!=""||jobj.get("visitId").toString()!=""||jobj.get("gender").toString()!="") {
			
			// create object Appointment
			Appointment appointment =  new Appointment();
			
			appointment.setName(jobj.get("name").toString());
			appointment.setAge(Integer.parseInt(jobj.get("age").toString()));
			appointment.setGender(jobj.get("gender").toString());
			appointment.setVisitId(Integer.parseInt(jobj.get("visitId").toString()));
			
			appointmentService appointments = new appointmentService();
			appointments.addAppointment(appointment);
			
			JSONObject json = new JSONObject();
			json.put("success", Integer.toString(appointments.getSuccess()));
			
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
		
		Appointment appointment =  new Appointment();
		
		appointment.setId(Integer.parseInt(jobj.get("id").toString()));
		appointment.setName(jobj.get("name").toString());
		appointment.setAge(Integer.parseInt(jobj.get("age").toString()));
		appointment.setGender(jobj.get("gender").toString());
		appointment.setVisitId(Integer.parseInt(jobj.get("visitId").toString()));
		
		appointmentService edit = new appointmentService();
		edit.editAppointments(appointment);
		
		JSONObject json = new JSONObject();
		json.put("success", Integer.toString(edit.getSuccess()));
		
		return json.toString();
		
	}
	
	@POST
	@Path("/getOne")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getHospital(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		appointmentService get =  new appointmentService();
		Appointment appointment = get.getAppointments(Integer.parseInt(jobj.get("id").toString()));
		
		JSONObject json = new JSONObject();
		json.put("id", appointment.getId());
		json.put("name", appointment.getName());
		json.put("age", appointment.getAge());
		json.put("gender", appointment.getGender());
		json.put("date", appointment.getDate());
		json.put("time", appointment.getTime());
		json.put("hospital", appointment.getHname());
		json.put("visitId", appointment.getVisitId());
		json.put("pack", appointment.getPack());
		
		return json.toString();
		
	}
	
	@POST
	@Path("/getDetails")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getDetails(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		visitService get = new visitService();
		Visit visit = get.getVisit(Integer.parseInt(jobj.get("id").toString()));
		
		JSONObject json = new JSONObject();
		json.put("id", visit.getId());
		json.put("name", visit.getName());
		json.put("date", visit.getDate());
		json.put("time", visit.getTime());
		json.put("hospital", visit.getHname());
		json.put("pack", visit.getPack());
		
		return json.toString();
		
	}
	
	@POST
	@Path("/deletes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteProduct(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		appointmentService delete = new appointmentService();
		delete.deleteAppointment(Integer.parseInt(jobj.get("id").toString()));
		
		JSONObject json = new JSONObject();
		json.put("success", Integer.toString(delete.getSuccess()));
		
		return json.toString();
		
	}

}
