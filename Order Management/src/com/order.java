package com;

import model.orderInfo;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/order")

public class order {
orderInfo api=new orderInfo();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readItems()
	 {
		return api.readOrderInfo();
	 
	 }
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertOrderInfo (
		
			@FormParam("orderName") String orderName,
			@FormParam("orderDate") String date,
			@FormParam("orderQuantity") int quantity,
			@FormParam("orderUnit") int unit
			)
		
	{
				
	 String output =api.insertOrderInfo(orderName,date,quantity,unit);
	return output;
	}
	
	//Update API JSON
	
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateOrder(String orderData)
		{
		
			//Convert the input string to a JSON object
		 JsonObject orderObject = new JsonParser().parse(orderData).getAsJsonObject();
		
		 //Read the values from the JSON object
		 String orderID =  orderObject.get("orderID").getAsString();
		 String orderName =  orderObject.get("orderName").getAsString();
		 String orderDate =  orderObject.get("orderDate").getAsString();
		 int orderQuantity = orderObject.get("orderQuantity").getAsInt();
		 int orderUnit = orderObject.get("orderUnit").getAsInt();
		
		
		 String output =  api.updateOrderInfo(orderID, orderName, orderDate, orderQuantity, orderUnit);
		
		 return output;
		}
		

		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String deleteOrderInfo(String orderData)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse( orderData, "", Parser.xmlParser());

		//Read the value from the element 
		 String orderID = doc.select("orderID").text();
		 String output = api.deleteOrderInfo(orderID);
		return output;
		}
}