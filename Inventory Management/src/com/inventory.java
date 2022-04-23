package com;

import model.inventoryInfo;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/inventory")

public class inventory {
inventoryInfo api=new inventoryInfo();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readItems()
	 {
		return api.readInventoryInfo();
	 
	 }
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertInventoryInfo (
		
			@FormParam("inName") String inventoryName,
			@FormParam("inType") String type,
			@FormParam("inUnit") String unit,
			@FormParam("inQuantity") String quantity
			)
		
	{
				
	 String output =api.insertInventoryInfo(inventoryName,type,unit,quantity);
	return output;
	}
	
	//Update API JSON
	
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateInventory(String inventoryData)
		{
		
			//Convert the input string to a JSON object
		 JsonObject inventoryObject = new JsonParser().parse(inventoryData).getAsJsonObject();
		
		 //Read the values from the JSON object
		 String inventoryID =  inventoryObject.get("inventoryID").getAsString();
		 String inName =  inventoryObject.get("inName").getAsString();
		 String inType =  inventoryObject.get("inType").getAsString();
		 int inUnit = inventoryObject.get("inUnit").getAsInt();
		 int inQuantity = inventoryObject.get("inQuantity").getAsInt();
		
		
		 String output =  api.updateInventoryInfo(inventoryID, inName, inType, inUnit, inQuantity);
		
		 return output;
		}
		

		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String deleteInventoryInfo(String inventorydata)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse( inventorydata, "", Parser.xmlParser());

		//Read the value from the element 
		 String inventoryID = doc.select("inventoryID").text();
		 String output = api.deleteInventoryInfo(inventoryID);
		return output;
		}
}