package model;

import java.sql.*;
import dbconnection.DBConnection;

public class employeeInfo {
			public String insertEmployeeInfo( String empName, String empEmail, String empAddress, String empContact)
			{
				String output = "";
				
				try
				 {
				 
					DBConnection dbconnection = new DBConnection();
					Connection con = dbconnection.connect();
					
					if (con == null)
					{return "Error while connecting to the database for inserting."; } 
				
					// create a prepared statement
					String query = " insert into employee (`empID`,`empName`,`empEmail`,`empAddress`,`empContact`)"
							+ " values (?, ?, ?, ?, ?)";
				
					PreparedStatement preparedStmt = con.prepareStatement(query);
			
					// binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2,empName);
					 preparedStmt.setString(3,empEmail);
					 preparedStmt.setString(4,empAddress);
					 preparedStmt.setString(5,empContact);	
					
					 
					 
					// execute the statement
					 preparedStmt.execute();
					 con.close(); 
					 
					 output = "Inserted successfully";
				  
					 
				 } 
					 catch (Exception e)
					 {
					
						 output = "Error while inserting the employee information.";
						 System.err.println(e.getMessage());
					 }
					 	return output;
					
					 	
					 	
					 	
				 } 
			public String readEmployeeInfo() 	 
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
				 		"style=\"background-image: url('https://img1.goodfon.com/wallpaper/nbig/a/9d/biznes-rukopozhatie-sdelka-6068.jpg');color:white\">" 
						+ "<h1 style='text-align:center;margin-top:50px; margin-bottom:50px'>Employee Management</h1>"
				 		+ "<table style='margin-left:25%;font-size:24' border=\"1\"><tr><th>Employee ID</th><th>Name</th><th>Email</th><th>Address</th><th>Contact</th><th>Update</th><th>Remove</th></tr>"; 
				 
				 
				 
				 String query = "select * from employee";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query); 
				 
				 
				// iterate through the rows in the result set
				 
				 while (rs.next()) 
				 {

					 String empID = Integer.toString(rs.getInt("empID")); 
					 String name		=	rs.getString("empName"); 
					 String email		= 	rs.getString("empEmail");
					 String address		= 	rs.getString("empAddress");
					 String contact    =	rs.getString("empContact");
		
				 
					// Add into the html table

					 
					 output += "<tr><td>" + empID + "</td>"; 
					 output += "<td>" + name + "</td>"; 
					 output += "<td>" + email + "</td>"; 
					 output += "<td>" + address+ "</td>"; 
					 output += "<td>" +contact  + "</td>"; 					
					 
					 
			
						output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
								+ "<td><form method=\"post\" action=\"\">"
								+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
								+ "<input name=\"RegId\" type=\"hidden\" value=\"" + empID  + "\">" + "</form></td></tr>";
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
			 
			public String updateEmployeeInfo(String ID ,String empName, String empEmail, String empAddress, int empContact)
			{ 
			String output = "";  
				 
				 
			try
			 {
				DBConnection dbconnection = new DBConnection();
				Connection con = dbconnection.connect();
					
					if (con == null)
					{return "Error while connecting to the database for updating."; }
			
					// create a prepared statement
			 String query = "UPDATE employee SET empName=?,empEmail=?,empAddress=?,empContact=? WHERE empID=?";
				 
				 
			 PreparedStatement preparedStmt = con.prepareStatement(query);	 
				 
				 
			// binding values
			 preparedStmt.setString(1, empName); 
			 preparedStmt.setString(2, empEmail);  
			 preparedStmt.setString(3, empAddress); 	
			 preparedStmt.setInt(4, empContact);   
			 preparedStmt.setInt(5, Integer.parseInt(ID));

			 
			// execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Updated successfully";
			 
			 } 
			
			catch (Exception e)
			 {
			 
				output = "Error while updating the employee information.";
				System.err.println(e.getMessage());
			 } 
			
			
			return output;
			} 

			public String deleteEmployeeInfo(String empID)
			{
				String output = ""; 
				
				try
				 {
				
					DBConnection dbconnection = new DBConnection();
					Connection con = dbconnection.connect();
				 
				 if (con == null)
				 {return "Error while connecting to the database for deleting."; }
				
				// create a prepared statement
				 String query="delete from employee  where empID=?";
				 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				// binding values
				 preparedStmt.setInt(1, Integer.parseInt(empID)); 
				 
				// execute the statement
				 preparedStmt.execute();
				 con.close();
				 
				 output = "Deleted successfully";
				
				 }
				 
			
			
			catch (Exception e)
			 {
					output = "Error while deleting the employee.";
					System.err.println(e.getMessage());
			 }
			 
				return output;
			 } 
			
			
	}
