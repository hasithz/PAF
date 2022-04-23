package model;

import com.google.gson.*;
import java.sql.*;
import java.util.Date;
import dbconnection.DBConnection;

public class paymentInfo {
			public String insertPaymentInfo( String payName, String payDate, String payType, String payAmmount)
			{
				String output = "";
				
				try
				 {
				 
					DBConnection dbconnection = new DBConnection();
					Connection con = dbconnection.connect();
					
					if (con == null)
					{return "Error while connecting to the database for inserting."; } 
				
					// create a prepared statement
					String query = " insert into Payment (`payID`,`payName`,`payDate`,`payType`,`payAmmount`)"
							+ " values (?, ?, ?, ?, ?)";
				
					PreparedStatement preparedStmt = con.prepareStatement(query);
			
					// binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2,payName);
					 preparedStmt.setString(3,payDate);
					 preparedStmt.setString(4,payType);
					 preparedStmt.setString(5,payAmmount);	
					
					 
					 
					// execute the statement
					 preparedStmt.execute();
					 con.close(); 
					 
					 output = "Inserted successfully";
				  
					 
				 } 
					 catch (Exception e)
					 {
					
						 output = "Error while inserting the payment information.";
						 System.err.println(e.getMessage());
					 }
					 	return output;
					
					 	
					 	
					 	
				 } 
			public String readPaymentInfo() 	 
			{		 
				
				String output = ""; 	 
					 
				try
				 {
					DBConnection dbconnection = new DBConnection();
					Connection con = dbconnection.connect();
				
				 if (con == null)
				 {return "Error while connecting to the database for reading."; }  
					 
					 
				// Prepare the html table to be displayed 
				 
				 output = "<body class=\"bg-image\"\n" + 
				 		"style=\"background-image: url('https://previews.123rf.com/images/samarttiw/samarttiw1603/samarttiw160300020/55867934-medical-abstract-science-background-illustrations.jpg');\">" 
						+ "<h1 style='text-align:center;margin-top:50px; margin-bottom:50px'>Payment Management</h1>"
				 		+ "<table style='margin-left:25%;font-size:24' border=\"1\"><tr><th>Payment ID</th><th>Payment Name</th><th>Payment Date</th><th>Type</th><th>Ammount</th><th>Update</th><th>Remove</th></tr>"; 

				 
				 
				 
				 String query = "select * from payment";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query); 
				 
				 
				// iterate through the rows in the result set
				 
				 while (rs.next()) 
				 {

					 String payID = Integer.toString(rs.getInt("payID")); 
					 String payname		=	rs.getString("payName"); 
					 String payDate		= 	rs.getString("payDate");
					 String payType		= 	rs.getString("payType");
					 String payAmmount    =	rs.getString("payAmmount");
		
				 
					// Add into the html table

					 
					 output += "<tr><td>" + payID + "</td>"; 
					 output += "<td>" + payname + "</td>"; 
					 output += "<td>" + payDate + "</td>"; 
					 output += "<td>" + payType+ "</td>"; 
					 output += "<td>" +payAmmount  + "</td>"; 					
					 
					 
			
						output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
								+ "<td><form method=\"post\" action=\"\">"
								+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
								+ "<input name=\"RegId\" type=\"hidden\" value=\"" + payID  + "\">" + "</form></td></tr>";
				 }
					 con.close();
					 
					 // Complete the html table
					 output += "</table>";
					 }
			
				 catch (Exception e)
				 {
					 output = "Error while reading the information.";
					 System.err.println(e.getMessage());
				 }
				 	
				 return output;
				 	
			
				 	 
			 } 		
			 
			public String updatePaymentInfo(String ID ,String payName, String payDate, String payType, int payAmmount)
			{ 
			String output = "";  
				 
				 
			try
			 {
				DBConnection dbconnection = new DBConnection();
				Connection con = dbconnection.connect();
					
					if (con == null)
					{return "Error while connecting to the database for updating."; }
			
					// create a prepared statement
			 String query = "UPDATE payment SET payName=?,payDate=?,payType=?,payAmmount=? WHERE payID=?";
				 
				 
			 PreparedStatement preparedStmt = con.prepareStatement(query);	 
				 
				 
			// binding values
			 preparedStmt.setString(1, payName); 
			 preparedStmt.setString(2, payDate);  
			 preparedStmt.setString(3,  payType); 	
			 preparedStmt.setInt(4, payAmmount);   
			 preparedStmt.setInt(5, Integer.parseInt(ID));

			 
			// execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Updated successfully";
			 
			 } 
			
			catch (Exception e)
			 {
			 
				output = "Error while updating the payment information.";
				System.err.println(e.getMessage());
			 } 
			
			
			return output;
			} 

			public String deletePaymentInfo(String paymentID)
			{
				String output = ""; 
				
				try
				 {
				
					DBConnection dbconnection = new DBConnection();
					Connection con = dbconnection.connect();
				 
				 if (con == null)
				 {return "Error while connecting to the database for deleting."; }
				
				// create a prepared statement
				 String query="delete from payment  where payID=?";
				 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				// binding values
				 preparedStmt.setInt(1, Integer.parseInt(paymentID)); 
				 
				// execute the statement
				 preparedStmt.execute();
				 con.close();
				 
				 output = "Deleted successfully";
				
				 }
				 
			
			
			catch (Exception e)
			 {
					output = "Error while deleting the payment.";
					System.err.println(e.getMessage());
			 }
			 
				return output;
			 } 
			
			
	}
