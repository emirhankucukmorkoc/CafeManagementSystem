package dao;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionProvider {
	
	public static Connection getCon() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms?useSSL=false","root","Kucuk.258");
			return con; 
		} catch (Exception e) {
			return null; 
		}
	}

}
