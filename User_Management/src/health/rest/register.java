package health.rest;

import java.sql.SQLException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.json.simple.*;
import org.json.simple.parser.*;

import health.model.*;
import health.service.*;

@Path("/user")
public class register {
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public String register(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		if(jobj.get("fname").toString()!=""||jobj.get("lname").toString()!=""||jobj.get("email").toString()!=""||jobj.get("phone").toString()!="") {
			// create object user
			User user =  new User();
	
			user.setFname(jobj.get("fname").toString());
			user.setLname(jobj.get("lname").toString());
			user.setEmail(jobj.get("email").toString());
			user.setPhone(jobj.get("phone").toString());
			user.setPassword(jobj.get("pass1").toString());
			
			registerService register = new registerService();
			register.register(user);
			
			JSONObject json = new JSONObject();
			json.put("success", Integer.toString(register.getSuccess()));
			
			return json.toString();
		
		}else {
			JSONObject json = new JSONObject();
			json.put("success", 0);
			
			return json.toString();
		}
		
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public String login(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		// create object user
		User user =  new User();
		
		//insert post value 
		user.setEmail(jobj.get("email").toString());
		user.setPassword(jobj.get("password").toString());
		
		registerService register = new registerService();
		register.login(user);
		
		JSONObject json = new JSONObject();
		json.put("success", Integer.toString(register.getSuccess()));
		
		return json.toString();
		
	}
	
	@POST
	@Path("/createUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public String createUser(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		// create object user
		User user =  new User();
		
		//insert post value 
		user.setId(Integer.parseInt(jobj.get("id").toString()));
		user.setAdmin("0");
		
		registerService register = new registerService();
		register.createUser(user);
		
		JSONObject json = new JSONObject();
		json.put("success", Integer.toString(register.getSuccess()));
		
		return json.toString();
		
	}
	
	@POST
	@Path("/createAdmin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public String createAdmin(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		// create object user
		User user =  new User();
		
		//insert post value 
		user.setId(Integer.parseInt(jobj.get("id").toString()));
		user.setAdmin("1");
		
		registerService register = new registerService();
		register.createUser(user);
		
		JSONObject json = new JSONObject();
		json.put("success", Integer.toString(register.getSuccess()));
		
		return json.toString();
		
	}
	
	@POST
	@Path("/createPatient")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public String createPatient(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		// create object user
		User user =  new User();
		
		//insert post value 
		user.setId(Integer.parseInt(jobj.get("id").toString()));
		user.setAdmin("2");
		
		registerService register = new registerService();
		register.createUser(user);
		
		JSONObject json = new JSONObject();
		json.put("success", Integer.toString(register.getSuccess()));
		
		return json.toString();
		
	}
	
	@POST
	@Path("/removeUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public String removeUser(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		// create object user
		User user =  new User();
		
		//insert post value 
		user.setId(Integer.parseInt(jobj.get("id").toString()));
		
		registerService register = new registerService();
		register.removeUser(user);
		
		JSONObject json = new JSONObject();
		json.put("success", Integer.toString(register.getSuccess()));
		
		return json.toString();
		
	}

}
