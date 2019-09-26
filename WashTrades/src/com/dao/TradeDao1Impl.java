package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pojos.Trade;

public class TradeDao1Impl implements TradeDAO1 {
	
	
	
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

	
	
	
	private Trade ResultSetToTrade(ResultSet resultSet) throws SQLException
	{
		int TradeID= resultSet.getInt("trade_id");
		Date timestamp= resultSet.getDate("timestamp");
		String tradeType= resultSet.getString("trade_tp");
		int SymbolID = resultSet.getInt("symbol_id");
		String securityType= resultSet.getString("security_tp");
		int Volume = resultSet.getInt("volume");
		float Price = resultSet.getFloat("price");
		int TraderID = resultSet.getInt("trader_id");
		int BrokerID = resultSet.getInt("broker_id");
		
		
		
	}
	
	
	@Override
	public Trade findByTradeID(int tradeID, int traderID)
	{
		
		return null;
	}

}
