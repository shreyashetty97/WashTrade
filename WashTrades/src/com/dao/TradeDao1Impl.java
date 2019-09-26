package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.pojos.Broker;
import com.pojos.Symbol;
import com.pojos.Trade;
import com.pojos.Trader;

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
		int tradeID= resultSet.getInt("trade_id");
		Date timestamp= resultSet.getDate("timestamp");
		String tradeType= resultSet.getString("trade_tp");
		int symbolID = resultSet.getInt("symbol_id");
		String securityType= resultSet.getString("security_tp");
		int volume = resultSet.getInt("volume");
		float price = resultSet.getFloat("price");
		int traderID = resultSet.getInt("trader_id");
		int brokerID = resultSet.getInt("broker_id");
		
		Broker broker= (new BrokerDAOImpl()).findBrokerByID(brokerID);
		Trader trader= (new TraderDAOImpl()).findByTraderID(traderID);
		Symbol symbol= (new SymbolDAOImpl()).getBySymbolID(symbolID);
		
		Trade trade= new Trade(tradeID,tradeType,timestamp,volume,trader,broker,securityType,price,symbol);
		return trade;
		
	}
	
	@Override
	public Trade findByTradeID(int tradeID)
	{
		Trade trade=null;
         try (Connection con=openConnection()){
			
			String FIND_TRADEID="select * from tradebook where trade_id = ?";
			PreparedStatement ps=con.prepareStatement(FIND_TRADEID);
			
			ps.setInt(1, tradeID);
			ResultSet resultSet=ps.executeQuery();
			while(resultSet.next()) {
				 trade=ResultSetToTrade(resultSet);
			}
					
         		
		
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	
	return trade;
}




	@Override
	public List<Trade> findAllTradesByTraderID(int traderID) {
		// TODO Auto-generated method stub
		List<Trade> trade=new ArrayList<Trade>();
		 Trade trade1=null;
			try(Connection con=openConnection();){
				Statement st=con.createStatement();
				String SQL_FIND_ALL="select * from tradebook where trader_id =?";
				PreparedStatement ps=con.prepareStatement(SQL_FIND_ALL);
				ps.setInt(1, traderID);
				ResultSet resultSet=st.executeQuery(SQL_FIND_ALL);
				while(resultSet.next())
				{ trade1=ResultSetToTrade(resultSet);
				  trade.add(trade1);
				}
				
         		
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return trade;
			
	}




	@Override
	public int addTrade(Trade trade) {
		// TODO Auto-generated method stub
		int rows=0;
		 try(Connection con=openConnection()){
			 
					String INSERT_TRADE="INSERT INTO TRADEBOOK VALUES(?,?,?,?,?,?,?,?,?)";
					PreparedStatement ps= con.prepareStatement(INSERT_TRADE);
					
					ps.setInt(1, trade.getTradeID());
					ps.setDate(2, trade.getTimeStamp());
					ps.setString(3,trade.getTradeType());
					ps.setInt(4,trade.getSymbol().getsymbolID());
					ps.setString(5,trade.getSecurityType());
					ps.setInt(6,trade.getVolume());
					ps.setFloat(7,trade.getPrice());
					ps.setInt(8,trade.getTrader().getTraderID());
					ps.setInt(9,trade.getBroker().getBrokerID());
					
					rows= ps.executeUpdate();
		 }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		 return rows;
	}




	@Override
	public Trade deleteTrade(int tradeID) {
		// TODO Auto-generated method stub
      Trade trade = findByTradeID(tradeID);
		
		try(Connection con= openConnection()) {
			String REMOVE = "delete from tradebook where trade_id = ?";
			PreparedStatement ps=con.prepareStatement(REMOVE);
			
			ps.setInt(1, tradeID);
			ResultSet resultSet=ps.executeQuery();
			
			return trade;	
		
	}catch (Exception e) {
		return null;}
	}




	@Override
	public int updateByTraderID(Trade trade) {
		// TODO Auto-generated method stub
		
		
		
		int rows=0;
		 try(Connection con=openConnection()){
			 
					String INSERT_TRADE="update trade set tradeid=?,tradetype=?, timestamp=? ,volume=?, traderid=?, brokerid=?, securitytype=?, price=?, symbolid=? where traderid=?";
					PreparedStatement ps= con.prepareStatement(INSERT_TRADE);
					
					ps.setInt(1, trade.getTradeID());
					ps.setDate(2, trade.getTimeStamp());
					ps.setString(3,trade.getTradeType());
					ps.setInt(4,trade.getSymbol().getsymbolID());
					ps.setString(5,trade.getSecurityType());
					ps.setInt(6,trade.getVolume());
					ps.setFloat(7,trade.getPrice());
					ps.setInt(8,trade.getTrader().getTraderID());
					ps.setInt(9,trade.getBroker().getBrokerID());
					
					rows= ps.executeUpdate();
		 }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		 return rows;
	}







	@Override
	public List<Trade> findALLTrades() {
		// TODO Auto-generated method stub
		List<Trade> trade=new ArrayList<Trade>();
		 Trade trade1=null;
		String SQL_FIND_ALL="select * from trade";
		
		try(Connection con=openConnection()){
			Statement st=con.createStatement();
			ResultSet resultSet=st.executeQuery(SQL_FIND_ALL);
			
			while(resultSet.next()) {
				trade1=ResultSetToTrade(resultSet);
				  trade.add(trade1);
				}
				
       		
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			return trade;
			
		
		
		
	}




	@Override
	public boolean deleteAll() {
		// TODO Auto-generated method stub
		String SQL_QUERY_DELETE_TRADE = "delete from trade";
		try {
			Connection con = openConnection();{
				
			    Statement s = con.createStatement();
				s.executeUpdate(SQL_QUERY_DELETE_TRADE);
				return true;
		   
			}
					}catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
		
		return false;
	}

		}





