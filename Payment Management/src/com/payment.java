package com;

import model.paymentInfo;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/payment")

public class payment {
paymentInfo api=new paymentInfo();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readItems()
	 {
		return api.readPaymentInfo();
	 
	 }
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPaymentInfo (
		
			@FormParam("payName") String payName,
			@FormParam("payDate") String date,
			@FormParam("payType") String type,
			@FormParam("payAmmount") String ammount
			)
		
	{
				
	 String output =api.insertPaymentInfo(payName,date,type,ammount);
	return output;
	}
	
	//Update API JSON
	
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatePayment(String PaymentData)
		{
		
			//Convert the input string to a JSON object
		 JsonObject paymentObject = new JsonParser().parse(PaymentData).getAsJsonObject();
		
		 //Read the values from the JSON object
		 String payID =  paymentObject.get("payID").getAsString();
		 String payName =  paymentObject.get("payName").getAsString();
		 String payDate =  paymentObject.get("payDate").getAsString();
		 String payType = paymentObject.get("payType").getAsString();
		 int payAmmount = paymentObject.get("payAmmount").getAsInt();
		
		
		 String output =  api.updatePaymentInfo(payID, payName, payDate, payType, payAmmount);
		
		 return output;
		}
		

		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String deletePaymentInfo(String paymentdata)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse( paymentdata, "", Parser.xmlParser());

		//Read the value from the element 
		 String paymentID = doc.select("payID").text();
		 String output = api.deletePaymentInfo(paymentID);
		return output;
		}
}