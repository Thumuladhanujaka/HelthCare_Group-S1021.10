package health.rest;

import java.sql.SQLException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.json.simple.*;
import org.json.simple.parser.*;

import health.model.*;
import health.service.*;

@Path("/visit")
public class visit {
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public String myMethod(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		if(jobj.get("name").toString()!=""||jobj.get("date").toString()!=""||jobj.get("time").toString()!=""||jobj.get("hospital").toString()!="") {
			
			// create object Visit
			Visit visit =  new Visit();
			
			visit.setName(jobj.get("name").toString());
			visit.setDate(jobj.get("date").toString());
			visit.setTime(jobj.get("time").toString());
			visit.setHospital(Integer.parseInt(jobj.get("hospital").toString()));
			
			visitService visits = new visitService();
			visits.addVisits(visit);
			
			JSONObject json = new JSONObject();
			json.put("success", Integer.toString(visits.getSuccess()));
			
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
		
		Visit visit =  new Visit();
		
		visit.setId(Integer.parseInt(jobj.get("id").toString()));
		visit.setName(jobj.get("name").toString());
		visit.setDate(jobj.get("date").toString());
		visit.setTime(jobj.get("time").toString());
		visit.setHospital(Integer.parseInt(jobj.get("hospital").toString()));
		
		visitService visits = new visitService();
		visits.editVisits(visit);
		
		JSONObject json = new JSONObject();
		json.put("success", Integer.toString(visits.getSuccess()));
		
		return json.toString();
		
	}
	
	@POST
	@Path("/getOne")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getHospital(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		visitService get = new visitService();
		Visit visit = get.getVisit(Integer.parseInt(jobj.get("id").toString()));
		
		JSONObject json = new JSONObject();
		json.put("id", visit.getId());
		json.put("name", visit.getName());
		json.put("date", visit.getDate());
		json.put("time", visit.getTime());
		json.put("hospital", visit.getHospital());
		
		return json.toString();
		
	}
	
	
	@POST
	@Path("/deletes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteProduct(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		visitService delete = new visitService();
		delete.deleteVisit(Integer.parseInt(jobj.get("id").toString()));
		
		JSONObject json = new JSONObject();
		json.put("success", Integer.toString(delete.getSuccess()));
		
		return json.toString();
		
	}

}
