package currency;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseInitializer {
	public static Connection GetConnection() {
		
		  String url = "jdbc:mysql://localhost:3306/currency_converter";
		  String dbUsername = "root";
		  String dbPassword = "Manchai1";
		  
		  String driver = "com.mysql.cj.jdbc.Driver";   
		   		 
		  try {	
			  Class.forName(driver);
		  // 2. Create a connection
			  Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
			  return con;
		 
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			return null;
	}
}
