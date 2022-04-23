package model;

import java.sql.*;
import dbconnection.DBConnection;

public class deliveryInfo {
			public String insertDeliveryInfo( String deliveName, String deliveAddress, String deliveContact, String deliveDate)
			{
				String output = "";
				
				try
				 {
				 
					DBConnection dbconnection = new DBConnection();
					Connection con = dbconnection.connect();
					
					if (con == null)
					{return "Error while connecting to the database for inserting."; } 
				
					// create a prepared statement
					String query = " insert into delivery (`delivID`,`deliveName`,`deliveAddress`,`deliveContact`,`deliveDate`)"
							+ " values (?, ?, ?, ?, ?)";
				
					PreparedStatement preparedStmt = con.prepareStatement(query);
			
					// binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2,deliveName);
					 preparedStmt.setString(3,deliveAddress);
					 preparedStmt.setString(4,deliveContact);
					 preparedStmt.setString(5,deliveDate);	
					
					 
					 
					// execute the statement
					 preparedStmt.execute();
					 con.close(); 
					 
					 output = "Inserted successfully";
				  
					 
				 } 
					 catch (Exception e)
					 {
					
						 output = "Error while inserting the delivery information.";
						 System.err.println(e.getMessage());
					 }
					 	return output;
					
					 	
					 	
					 	
				 } 
			public String readDeliveryInfo() 	 
			{		 
				
				String output = ""; 	 
					 
				try
				 {
					DBConnection dbconnection = new DBConnection();
					Connection con = dbconnection.connect();
				
				 if (con == null)
				 {return "Error while connecting to the database for reading."; }  
					 
					 
				// Prepare the html table to be displayed 
				 
				 output ="<body class=\"bg-image\"\n" + 
					 		"style=\"background-image: url('https://www.wisesecurity.net/wp-content/uploads/2019/11/160-1600491_autodesk-wallpaper-website-background.jpg');\">" 
							+ "<h1 style='text-align:center;margin-top:50px; margin-bottom:50px'>Delivery Management</h1>"
					 		+ "<table style='margin-left:25%;font-size:24' border=\"1\"><tr><th>Delivery ID</th><th>Delivery Name</th><th>Delivery Address</th><th>Contact</th><th>Date</th><th>Update</th><th>Remove</th></tr>"; 
				 
				 
				 
				 String query = "select * from delivery";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query); 
				 
				 
				// iterate through the rows in the result set
				 
				 while (rs.next()) 
				 {

					 String deliveID = Integer.toString(rs.getInt("delivID")); 
					 String name		=	rs.getString("deliveName"); 
					 String address		= 	rs.getString("deliveAddress");
					 String contact		= 	rs.getString("deliveContact");
					 String date    =	rs.getString("deliveDate");
		
				 
					// Add into the html table

					 
					 output += "<tr><td>" + deliveID + "</td>"; 
					 output += "<td>" + name + "</td>"; 
					 output += "<td>" + address + "</td>"; 
					 output += "<td>" + contact+ "</td>"; 
					 output += "<td>" +date  + "</td>"; 					
					 
					 
			
						output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
								+ "<td><form method=\"post\" action=\"\">"
								+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
								+ "<input name=\"RegId\" type=\"hidden\" value=\"" + deliveID  + "\">" + "</form></td></tr>";
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
			 
			public String updateDeliveryInfo(String ID ,String deliveName, String deliveAddress, int contact, String date)
			{ 
			String output = "";  
				 
				 
			try
			 {
				DBConnection dbconnection = new DBConnection();
				Connection con = dbconnection.connect();
					
					if (con == null)
					{return "Error while connecting to the database for updating."; }
			
					// create a prepared statement
			 String query = "UPDATE delivery SET deliveName=?,deliveAddress=?,deliveContact=?,deliveDate=? WHERE delivID=?";
				 
				 
			 PreparedStatement preparedStmt = con.prepareStatement(query);	 
				 
				 
			// binding values
			 preparedStmt.setString(1, deliveName); 
			 preparedStmt.setString(2, deliveAddress);  
			 preparedStmt.setInt(3,  contact); 	
			 preparedStmt.setString(4, date);   
			 preparedStmt.setInt(5, Integer.parseInt(ID));

			 
			// execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Updated successfully";
			 
			 } 
			
			catch (Exception e)
			 {
			 
				output = "Error while updating the delivery information.";
				System.err.println(e.getMessage());
			 } 
			
			
			return output;
			} 

			public String deleteDeliveryInfo(String delivID)
			{
				String output = ""; 
				
				try
				 {
				
					DBConnection dbconnection = new DBConnection();
					Connection con = dbconnection.connect();
				 
				 if (con == null)
				 {return "Error while connecting to the database for deleting."; }
				
				// create a prepared statement
				 String query="delete from delivery  where delivID=?";
				 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				// binding values
				 preparedStmt.setInt(1, Integer.parseInt(delivID)); 
				 
				// execute the statement
				 preparedStmt.execute();
				 con.close();
				 
				 output = "Deleted successfully";
				
				 }
				 
			
			
			catch (Exception e)
			 {
					output = "Error while deleting the delivery.";
					System.err.println(e.getMessage());
			 }
			 
				return output;
			 } 
			
			
	}
