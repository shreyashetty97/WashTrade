package com.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CountTrades {
	
	private Connection openConnection() {
		// TODO Auto-generated method stub
		 Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			System.out.println("connection opened");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;

	}

	
	int countTrades() {
		
		int count= 0;
		System.out.println("waiting....");
		try(Connection con= openConnection() ){
		System.out.println("entered");	
		String COUNT_TRADES="select count(*) as count from trade";
		Statement ps=con.createStatement();
		ResultSet rs= ps.executeQuery(COUNT_TRADES);
		rs.next();
		count=rs.getInt("count");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return count;
	}
	
     public static void main(String[] args) {
    	 CountTrades c= new CountTrades();
    	
    	 System.out.println(c.countTrades());
     }
}
