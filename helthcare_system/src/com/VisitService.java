package com;

import model.Visit;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON 
import com.google.gson.*;

//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Visits")
public class VisitService {
	Visit visitObj = new Visit();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readVisits() {
		return visitObj.readVisits();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertVisit(@FormParam("hospital_name")String hospital_name, 
			@FormParam("hospital_city") String hospital_city,
			@FormParam("date") String date,
			@FormParam("time") String time,
			@FormParam("noPatients") String noPatients) 
	{
		String output = visitObj.insertVisits(hospital_name,hospital_city , date, time, noPatients);
		return output;
	}
}
