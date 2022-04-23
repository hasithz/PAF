package com;

import model.employeeInfo;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/employee")

public class employee {
employeeInfo api=new employeeInfo();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readItems()
	 {
		return api.readEmployeeInfo();
	 
	 }
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertemployeeInfo (
		
			@FormParam("empName") String empName,
			@FormParam("empEmail") String email,
			@FormParam("empAddress") String address,
			@FormParam("empContact") String contact
			)
		
	{
				
	 String output =api.insertEmployeeInfo(empName,email,address,contact);
	return output;
	}
	
	//Update API JSON
	
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateEmployee(String employeeData)
		{
		
			//Convert the input string to a JSON object
		 JsonObject employeeObject = new JsonParser().parse(employeeData).getAsJsonObject();
		
		 //Read the values from the JSON object
		 String empID =  employeeObject.get("empID").getAsString();
		 String empName =  employeeObject.get("empName").getAsString();
		 String empEmail =  employeeObject.get("empEmail").getAsString();
		 String empAddress = employeeObject.get("empAddress").getAsString();
		 int empContact = employeeObject.get("empContact").getAsInt();
		
		
		 String output =  api.updateEmployeeInfo(empID, empName, empEmail, empAddress, empContact);
		
		 return output;
		}
		

		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String deleteEmployeeInfo(String employeedata)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse( employeedata, "", Parser.xmlParser());

		//Read the value from the element 
		 String empID = doc.select("empID").text();
		 String output = api.deleteEmployeeInfo(empID);
		return output;
		}
}