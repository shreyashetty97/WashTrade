package com.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CountTrades {
	
	public int countTrades() {
		
		int count= 0;
		System.out.println("waiting....");
		try(Connection con= new OpenConnection().newConnection() ){
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
	
}
