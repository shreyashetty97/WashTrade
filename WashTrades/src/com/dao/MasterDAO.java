package com.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.helper.OpenConnection;

public class MasterDAO {

	Connection connection=null;
	
	public MasterDAO() {
		this.connection= new OpenConnection().newConnection();
	}
	
	 public MasterDAO(Connection existing) {
		 
		 this.connection=existing;
		 
	 };
	 
	 public Connection getConnection() {
		 return this.connection; 
		  
	 }
	 
	 public boolean closeConnection() {
		 boolean close=false;
		 try {
			if(this.connection.isClosed()==false)
			 
			{
				this.connection.close();
			}
			
			else {
				System.out.println(connection +" is already closed");
				}
		  
			close=true;
		}
		 
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return close;
	 }
}
