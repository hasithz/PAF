package model;

import com.google.gson.*;
import java.sql.*;
import java.util.Date;
import dbconnection.DBConnection;

public class orderInfo {
			public String insertOrderInfo( String orderName, String orderDate, int orderQuantity, int orderUnit)
			{
				String output = "";
				
				try
				 {
				 
					DBConnection dbconnection = new DBConnection();
					Connection con = dbconnection.connect();
					
					if (con == null)
					{return "Error while connecting to the database for inserting."; } 
				
					// create a prepared statement
					String query = " insert into orders (`orderID`,`orderName`,`orderDate`,`orderQuantity`,`orderUnit`)"
							+ " values (?, ?, ?, ?, ?)";
				
					PreparedStatement preparedStmt = con.prepareStatement(query);
			
					// binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2,orderName);
					 preparedStmt.setString(3,orderDate);
					 preparedStmt.setInt(4,orderQuantity);
					 preparedStmt.setInt(5,orderUnit);	
					
					 
					 
					// execute the statement
					 preparedStmt.execute();
					 con.close(); 
					 
					 output = "Inserted successfully";
				  
					 
				 } 
					 catch (Exception e)
					 {
					
						 output = "Error while inserting the order information.";
						 System.err.println(e.getMessage());
					 }
					 	return output;
					
					 	
					 	
					 	
				 } 
			public String readOrderInfo() 	 
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
				 		"style=\"background-image: url('https://motionarray.imgix.net/preview-41462fuhzQiVTjG_0015.jpg');\">" 
						+ "<h1 style='text-align:center;margin-top:50px; margin-bottom:50px'>Order Management</h1>"
				 		+ "<table style='margin-left:25%;font-size:24' border=\"1\"><tr><th>Order ID</th><th>Order Name</th><th>Order Date</th><th>Quantity</th><th>Unit</th><th>Update</th><th>Remove</th></tr>"; 
				 
				 
				 
				 String query = "select * from orders";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query); 
				 
				 
				// iterate through the rows in the result set
				 
				 while (rs.next()) 
				 {

					 String orderID = Integer.toString(rs.getInt("orderID")); 
					 String ordername		=	rs.getString("orderName"); 
					 String orderDate		= 	rs.getString("orderDate");
					 String orderQuantity		= 	rs.getString("orderQuantity");
					 String orderUnit    =	rs.getString("orderUnit");
		
				 
					// Add into the html table

					 
					 output += "<tr><td>" + orderID + "</td>"; 
					 output += "<td>" + ordername + "</td>"; 
					 output += "<td>" + orderDate + "</td>"; 
					 output += "<td>" + orderQuantity+ "</td>"; 
					 output += "<td>" +orderUnit  + "</td>"; 					
					 
					 
			
						output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
								+ "<td><form method=\"post\" action=\"\">"
								+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
								+ "<input name=\"RegId\" type=\"hidden\" value=\"" + orderID  + "\">" + "</form></td></tr>";
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
			 
			public String updateOrderInfo(String ID ,String orderName, String orderDate, int orderQuantity, int orderUnit)
			{ 
			String output = "";  
				 
				 
			try
			 {
				DBConnection dbconnection = new DBConnection();
				Connection con = dbconnection.connect();
					
					if (con == null)
					{return "Error while connecting to the database for updating."; }
			
					// create a prepared statement
			 String query = "UPDATE orders SET orderName=?,orderDate=?,orderQuantity=?,orderUnit=? WHERE orderID=?";
				 
				 
			 PreparedStatement preparedStmt = con.prepareStatement(query);	 
				 
				 
			// binding values
			 preparedStmt.setString(1, orderName); 
			 preparedStmt.setString(2, orderDate);  
			 preparedStmt.setInt(3,  orderQuantity); 	
			 preparedStmt.setInt(4, orderUnit);   
			 preparedStmt.setInt(5, Integer.parseInt(ID));

			 
			// execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Updated successfully";
			 
			 } 
			
			catch (Exception e)
			 {
			 
				output = "Error while updating the order information.";
				System.err.println(e.getMessage());
			 } 
			
			
			return output;
			} 

			public String deleteOrderInfo(String orderID)
			{
				String output = ""; 
				
				try
				 {
				
					DBConnection dbconnection = new DBConnection();
					Connection con = dbconnection.connect();
				 
				 if (con == null)
				 {return "Error while connecting to the database for deleting."; }
				
				// create a prepared statement
				 String query="delete from orders  where orderID=?";
				 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				// binding values
				 preparedStmt.setInt(1, Integer.parseInt(orderID)); 
				 
				// execute the statement
				 preparedStmt.execute();
				 con.close();
				 
				 output = "Deleted successfully";
				
				 }
				 
			
			
			catch (Exception e)
			 {
					output = "Error while deleting the order.";
					System.err.println(e.getMessage());
			 }
			 
				return output;
			 } 
			
			
	}
