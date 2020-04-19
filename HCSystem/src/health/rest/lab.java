package health.rest;

import java.sql.SQLException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.json.simple.*;
import org.json.simple.parser.*;

import health.model.*;
import health.service.*;

@Path("/lab")
public class lab {
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public String myMethod(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		if(jobj.get("name").toString()!=""||jobj.get("date").toString()!=""||jobj.get("description").toString()!=""||jobj.get("test").toString()!="") {
			
			// create object Lab
			Lab lab =  new Lab();
			
			lab.setName(jobj.get("name").toString());
			lab.setDate(jobj.get("date").toString());
			lab.setDescription(jobj.get("description").toString());
			lab.setTest(jobj.get("test").toString());
			
			labService labs = new labService();
			labs.addTest(lab);
			
			JSONObject json = new JSONObject();
			json.put("success", Integer.toString(labs.getSuccess()));
			
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
		
		Lab lab =  new Lab();
		
		lab.setId(Integer.parseInt(jobj.get("id").toString()));
		lab.setName(jobj.get("name").toString());
		lab.setDate(jobj.get("date").toString());
		lab.setDescription(jobj.get("description").toString());
		lab.setTest(jobj.get("test").toString());
		
		labService labs = new labService();
		labs.editLabTest(lab);
		
		JSONObject json = new JSONObject();
		json.put("success", Integer.toString(labs.getSuccess()));
		
		return json.toString();
		
	}
	
	@POST
	@Path("/getOne")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getHospital(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		labService get = new labService();
		Lab lab = get.getLabTest(Integer.parseInt(jobj.get("id").toString()));
		
		JSONObject json = new JSONObject();
		json.put("id", lab.getId());
		json.put("name", lab.getName());
		json.put("date", lab.getDate());
		json.put("description", lab.getDescription());
		json.put("test", lab.getTest());
		
		return json.toString();
		
	}
	
	
	@POST
	@Path("/deletes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteProduct(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		labService delete = new labService();
		delete.deleteTest(Integer.parseInt(jobj.get("id").toString()));
		
		JSONObject json = new JSONObject();
		json.put("success", Integer.toString(delete.getSuccess()));
		
		return json.toString();
		
	}

}
