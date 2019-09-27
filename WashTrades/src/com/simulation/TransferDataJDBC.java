package com.simulation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.helper.OpenConnection;

public class TransferDataJDBC {
  OpenConnection con=new OpenConnection();
 
  
	public int deleteFromTrade() throws SQLException {	  
		  
		int rows=0;
          Connection connection= con.newConnection();
          String DELETE_ALL_TRADE="delete from trade";
          PreparedStatement ps= connection.prepareStatement(DELETE_ALL_TRADE);
         rows=ps.executeUpdate();
         return rows;
	}
  
	public int deleteFromWash() throws SQLException {	  
		  
		int rows=0;
          Connection connection= con.newConnection();
          String DELETE_ALL_TRADE="delete from wash";
          PreparedStatement ps= connection.prepareStatement(DELETE_ALL_TRADE);
         rows=ps.executeUpdate();
         return rows;
	}
	
	public int deleteFromWashMap() throws SQLException {	  
		  
		int rows=0;
          Connection connection= con.newConnection();
          String DELETE_ALL_TRADE="delete from washmap";
          PreparedStatement ps= connection.prepareStatement(DELETE_ALL_TRADE);
         rows=ps.executeUpdate();
         return rows;
	}
	public int deleteFromEquity() throws SQLException {	  
		  
		int rows=0;
          Connection connection= con.newConnection();
          String DELETE_ALL_TRADE="delete from equity";
          PreparedStatement ps= connection.prepareStatement(DELETE_ALL_TRADE);
         rows=ps.executeUpdate();
         return rows;
	}
		  
	public int deleteFromFuture() throws SQLException {	  
		  
		int rows=0;
          Connection connection= con.newConnection();
          String DELETE_ALL_TRADE="delete from future";
          PreparedStatement ps= connection.prepareStatement(DELETE_ALL_TRADE);
         rows=ps.executeUpdate();
         return rows;
	}
	public int deleteFromCallOption() throws SQLException {	  
		  
		int rows=0;
          Connection connection= con.newConnection();
          String DELETE_ALL_TRADE="delete from calloption";
          PreparedStatement ps= connection.prepareStatement(DELETE_ALL_TRADE);
         rows=ps.executeUpdate();
         return rows;
	}
	public int deleteFromPutOption() throws SQLException {	  
		  
		int rows=0;
          Connection connection= con.newConnection();
          String DELETE_ALL_TRADE="delete from putoption";
          PreparedStatement ps= connection.prepareStatement(DELETE_ALL_TRADE);
         rows=ps.executeUpdate();
         return rows;
	}
	
	public boolean copyFromTrade() throws SQLException {	  
		  
		boolean r=false;
          Connection connection= con.newConnection();
          String COPY_ALL_TRADE="insert into trade select * from tradebook";
          PreparedStatement ps= connection.prepareStatement(COPY_ALL_TRADE);
           r=ps.execute();
         return r;
	}
		
//	public static void main(String[] args) {
//		 boolean 
//		
		
	


		
		
	}

