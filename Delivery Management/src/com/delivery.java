package com;

import model.deliveryInfo;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/delivery")

public class delivery {
deliveryInfo api=new deliveryInfo();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readItems()
	 {
		return api.readDeliveryInfo();
	 
	 }
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDeliveryInfo (
		
			@FormParam("deliveName") String deliveName,
			@FormParam("deliveAddress") String deliveAddress,
			@FormParam("deliveContact") String delivecontact,
			@FormParam("deliveDate") String delivedate
			)
		
	{
				
	 String output =api.insertDeliveryInfo(deliveName,deliveAddress,delivecontact,delivedate);
	return output;
	}
	
	//Update API JSON
	
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateDelivery(String deliveryData)
		{
		
			//Convert the input string to a JSON object
		 JsonObject DeliveryObject = new JsonParser().parse(deliveryData).getAsJsonObject();
		
		 //Read the values from the JSON object
		 String deliveID =  DeliveryObject.get("delivID").getAsString();
		 String deliveName =  DeliveryObject.get("deliveName").getAsString();
		 String deliveAddress =  DeliveryObject.get("deliveAddress").getAsString();
		 int deliveContact = DeliveryObject.get("deliveContact").getAsInt();
		 String deliveDate = DeliveryObject.get("deliveDate").getAsString();
		
		
		 String output =  api.updateDeliveryInfo(deliveID, deliveName, deliveAddress, deliveContact, deliveDate);
		
		 return output;
		}
		

		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String deleteDeliveryInfo(String deliverydata)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse( deliverydata, "", Parser.xmlParser());

		//Read the value from the element 
		 String deliveryID = doc.select("delivID").text();
		 String output = api.deleteDeliveryInfo(deliveryID);
		return output;
		}
}