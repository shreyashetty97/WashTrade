package com.simulation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.dao.TradeDAO;
import com.dao.TradeDAOImpl;
import com.dao.WashDAOImpl;
import com.dao.WashMapDAOImpl;
import com.helper.OpenConnection;

public class TransferDataJDBC {

 
	private boolean copyFromTrade() {	  
		  System.out.println("inside copyFromTrades");
		 boolean flag=false;
		 try(Connection connection=new OpenConnection().newConnection()) {
	     
		 String SET_UNIQUE_INDEX="CREATE UNIQUE INDEX hr.tradebook_pk ON hr.tradebook (tradeid ASC )";	 
	     
		 String SET_PK_CONSTRAINT="ALTER TABLE hr.tradebook ADD CONSTRAINT tradebook_pk PRIMARY KEY ( tradeid ) USING INDEX hr.tradebook_pk";
	     
	     String COPY_ALL_TRADE="insert into trade (select * from tradebook)";
         
	     PreparedStatement ps= connection.prepareStatement(SET_UNIQUE_INDEX);
         ps.execute();
         ps= connection.prepareStatement( SET_PK_CONSTRAINT );
         ps.execute();
         ps= connection.prepareStatement( COPY_ALL_TRADE );
         ps.execute();
         flag=true;
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
         return flag;
	}
	
	public boolean simulate() {
		boolean flag =false;
		try {
		new TradeDAOImpl().deleteAll();
		new WashDAOImpl().deleteAllWash();
		new WashMapDAOImpl().deleteAllWashMap();
		new JavaRunBatFile().dataGeneration1();
		copyFromTrade();
		flag =true;
		System.out.println("data simulation successful");
		}
		
		catch(Exception e) {
			e.printStackTrace();
			flag=false;
		}
			return flag;
		}
	
	public static void main(String args[]) {
		
		//new TradeDAOImpl().deleteAll();
		new TransferDataJDBC().copyFromTrade();
		//new JavaRunBatFile().dataGeneration();
		
		
		
	}
	
}

