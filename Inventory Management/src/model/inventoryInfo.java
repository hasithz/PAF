package model;

import java.sql.*;
import dbconnection.DBConnection;

public class inventoryInfo {
			public String insertInventoryInfo( String inName, String inType, String inUnit, String inQuantity)
			{
				String output = "";
				
				try
				 {
				 
					DBConnection dbconnection = new DBConnection();
					Connection con = dbconnection.connect();
					
					if (con == null)
					{return "Error while connecting to the database for inserting."; } 
				
					// create a prepared statement
					String query = " insert into inventory (`inventoryID`,`inName`,`inType`,`inUnit`,`inQuantity`)"
							+ " values (?, ?, ?, ?, ?)";
				
					PreparedStatement preparedStmt = con.prepareStatement(query);
			
					// binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2,inName);
					 preparedStmt.setString(3,inType);
					 preparedStmt.setString(4,inUnit);
					 preparedStmt.setString(5,inQuantity);	
					
					 
					 
					// execute the statement
					 preparedStmt.execute();
					 con.close(); 
					 
					 output = "Inserted successfully";
				  
					 
				 } 
					 catch (Exception e)
					 {
					
						 output = "Error while inserting the inventory information.";
						 System.err.println(e.getMessage());
					 }
					 	return output;
					
					 	
					 	
					 	
				 } 
			public String readInventoryInfo() 	 
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
				 		"style=\"background-image: url('https://wallpapercave.com/wp/wp3129753.jpg');\">" 
						+ "<h1 style='text-align:center;margin-top:50px; margin-bottom:50px'>Inventory Management</h1>"
				 		+ "<table style='margin-left:25%;font-size:24' border=\"1\"><tr><th>Inventory ID</th><th>Inventory Name</th><th>Inventory Type</th><th>Unit</th><th>Quantity</th><th>Update</th><th>Remove</th></tr>"; 
				 
				 
				 
				 String query = "select * from inventory";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query); 
				 
				 
				// iterate through the rows in the result set
				 
				 while (rs.next()) 
				 {

					 String inventoryID = Integer.toString(rs.getInt("inventoryID")); 
					 String name		=	rs.getString("inName"); 
					 String type		= 	rs.getString("inType");
					 String unit		= 	rs.getString("inUnit");
					 String quantity    =	rs.getString("inQuantity");
		
				 
					// Add into the html table

					 
					 output += "<tr><td>" + inventoryID + "</td>"; 
					 output += "<td>" + name + "</td>"; 
					 output += "<td>" + type + "</td>"; 
					 output += "<td>" + unit+ "</td>"; 
					 output += "<td>" +quantity  + "</td>"; 					
					 
					 
			
						output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
								+ "<td><form method=\"post\" action=\"\">"
								+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
								+ "<input name=\"RegId\" type=\"hidden\" value=\"" + inventoryID  + "\">" + "</form></td></tr>";
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
			 
			public String updateInventoryInfo(String ID ,String inventoryName, String inventoryType, int inventoryUnit, int inventoryQuantity)
			{ 
			String output = "";  
				 
				 
			try
			 {
				DBConnection dbconnection = new DBConnection();
				Connection con = dbconnection.connect();
					
					if (con == null)
					{return "Error while connecting to the database for updating."; }
			
					// create a prepared statement
			 String query = "UPDATE inventory SET inName=?,inType=?,inUnit=?,inQuantity=? WHERE inventoryID=?";
				 
				 
			 PreparedStatement preparedStmt = con.prepareStatement(query);	 
				 
				 
			// binding values
			 preparedStmt.setString(1, inventoryName); 
			 preparedStmt.setString(2, inventoryType);  
			 preparedStmt.setInt(3,  inventoryUnit); 	
			 preparedStmt.setInt(4, inventoryQuantity);   
			 preparedStmt.setInt(5, Integer.parseInt(ID));

			 
			// execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Updated successfully";
			 
			 } 
			
			catch (Exception e)
			 {
			 
				output = "Error while updating the inventory information.";
				System.err.println(e.getMessage());
			 } 
			
			
			return output;
			} 

			public String deleteInventoryInfo(String inventoryID)
			{
				String output = ""; 
				
				try
				 {
				
					DBConnection dbconnection = new DBConnection();
					Connection con = dbconnection.connect();
				 
				 if (con == null)
				 {return "Error while connecting to the database for deleting."; }
				
				// create a prepared statement
				 String query="delete from inventory  where inventoryID=?";
				 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				// binding values
				 preparedStmt.setInt(1, Integer.parseInt(inventoryID)); 
				 
				// execute the statement
				 preparedStmt.execute();
				 con.close();
				 
				 output = "Deleted successfully";
				
				 }
				 
			
			
			catch (Exception e)
			 {
					output = "Error while deleting the inventory.";
					System.err.println(e.getMessage());
			 }
			 
				return output;
			 } 
			
			
	}
