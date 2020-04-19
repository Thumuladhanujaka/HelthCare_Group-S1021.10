package health.rest;

import java.sql.SQLException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.json.simple.*;
import org.json.simple.parser.*;

import health.model.*;
import health.service.*;

@Path("/payment")
public class payment {
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public String myMethod(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		if(jobj.get("name").toString()!=""||jobj.get("hopital").toString()!=""||jobj.get("type").toString()!=""||jobj.get("price").toString()!="") {
			
			// create object Payment
			Payment payment =  new Payment();
			
			payment.setName(jobj.get("name").toString());
			payment.setHospital(Integer.parseInt(jobj.get("hopital").toString()));
			payment.setType(jobj.get("type").toString());
			payment.setPrice(Double.parseDouble(jobj.get("price").toString()));
			
			paymentService packages = new paymentService();
			packages.addPayments(payment);
			
			JSONObject json = new JSONObject();
			json.put("success", Integer.toString(packages.getSuccess()));
			
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
		
		// create object payment
		Payment payment =  new Payment();
		
		payment.setId(Integer.parseInt(jobj.get("id").toString()));
		payment.setName(jobj.get("name").toString());
		payment.setHospital(Integer.parseInt(jobj.get("hospital").toString()));
		payment.setType(jobj.get("typez").toString());
		payment.setPrice(Double.parseDouble(jobj.get("price").toString()));
		
		paymentService packages = new paymentService();
		packages.editPayments(payment);
		
		JSONObject json = new JSONObject();
		json.put("success", Integer.toString(packages.getSuccess()));
		
		return json.toString();
		
	}
	
	@POST
	@Path("/getOne")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getHospital(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		paymentService get = new paymentService();
		Payment payment = get.getPayment(Integer.parseInt(jobj.get("id").toString()));
		
		JSONObject json = new JSONObject();
		json.put("id", payment.getId());
		json.put("name", payment.getName());
		json.put("hospital", payment.getHospital());
		json.put("type", payment.getType());
		json.put("price", payment.getPrice());
		
		return json.toString();
		
	}
	
	
	@POST
	@Path("/deletes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteProduct(String re) throws ClassNotFoundException,SQLException,ParseException {
		
		JSONParser jp = new JSONParser();
		JSONObject jobj = (JSONObject) jp.parse(re);
		
		paymentService delete = new paymentService();
		delete.deletePayment(Integer.parseInt(jobj.get("id").toString()));
		
		JSONObject json = new JSONObject();
		json.put("success", Integer.toString(delete.getSuccess()));
		
		return json.toString();
		
	}

}
