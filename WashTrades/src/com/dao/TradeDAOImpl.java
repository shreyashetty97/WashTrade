package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.helper.CountTrades;
import com.pojos.Broker;
import com.pojos.CallOption;
import com.pojos.Equity;
import com.pojos.Future;
import com.pojos.PutOption;
import com.pojos.Symbol;
import com.pojos.Trade;
import com.pojos.Trader;

public class TradeDAOImpl implements TradeDAO {
	
	
	static int recordCount=new CountTrades().countTrades();
	
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

	
	
	
	private Trade ResultSetToTrade(ResultSet resultSet) throws Exception
	{
		
		Trade trade=null;
		int tradeID= resultSet.getInt("tradeid");
		Date timestamp= resultSet.getDate("timestamp");
		String tradeType= resultSet.getString("tradetype");
		int symbolID = resultSet.getInt("symbolsid");
		String securityType= resultSet.getString("securitytype");
		int volume = resultSet.getInt("volume");
		float price = resultSet.getFloat("price");
		int traderID = resultSet.getInt("traderid");
		int brokerID = resultSet.getInt("brokerid");
		
		Broker broker= (new BrokerDAOImpl()).findBrokerByID(brokerID);
		Trader trader= (new TraderDAOImpl()).findByTraderID(traderID);
		Symbol symbol= (new SymbolDAOImpl()).getBySymbolID(symbolID);
		
		
		trade= new Trade(tradeID,tradeType,timestamp,volume,trader,broker,securityType,price,symbol);
		switch(securityType.toLowerCase()) {
		
		case "equity":
			trade= new Equity(trade);
			break;
		case "future":
			trade= new Future(trade);
			break;
		case "call":
			trade= new CallOption(trade);
			break;
		case "put":
			trade= new PutOption(trade);
			break;
		default:
			throw new Exception("Security type "+securityType+" doesnt exist in the switch case in TradeDAOImpl");
			
		}
		 
		
		return trade;
		
	}
	
	@Override
	public List<Trade> findByTradeIDs(List <Integer> tradeIDs)
	{
		List<Trade> trades=new ArrayList<Trade>();
		Trade trade=null;
         try (Connection con=openConnection()){
        	 for(int tradeID:tradeIDs) 
        	 {
			String FIND_TRADEID="select * from tradebook where tradeid = ?";
			PreparedStatement ps=con.prepareStatement(FIND_TRADEID);
			ps.setInt(1, tradeID);
			ResultSet resultSet=ps.executeQuery();
			resultSet.next();
		    trade=ResultSetToTrade(resultSet);
		    trades.add(trade);
			}
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
         
	
	return trades;
}




	@Override
	public List<Trade> findAllTradesByTraderID(int traderID,String securityType) {
		// TODO Auto-generated method stub
		List<Trade> trade=new ArrayList<Trade>();
		 Trade trade1=null;
			try(Connection con=openConnection();){
				String SQL_FIND_ALL="select * from tradebook where traderid =? and securityType=? order by tradeid asc";
				PreparedStatement ps=con.prepareStatement(SQL_FIND_ALL);
				ps.setInt(1, traderID);
				ps.setString(2, securityType);
				ResultSet resultSet=ps.executeQuery();
				
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
	public int addTrade(Trade trade) throws ClassCastException{
		// TODO Auto-generated method stub
		int rows=0;
		 try(Connection con=openConnection()){
			 
					String INSERT_TRADE="INSERT INTO TRADEBOOK VALUES(?,?,?,?,?,?,?,?,?)";
					PreparedStatement ps= con.prepareStatement(INSERT_TRADE);
					
					if (trade.getTradeID()==this.recordCount+1) {
					ps.setInt(1,++this.recordCount);}
					
					else {
						ps.setInt(1,trade.getTradeID());
						
					}
					
					ps.setString(2,trade.getTradeType());
					ps.setDate(3, trade.getTimeStamp());
					ps.setInt(4,trade.getVolume());
					ps.setInt(5,trade.getTrader().getTraderID());
					ps.setInt(6,trade.getBroker().getBrokerID());
					ps.setString(7,trade.getSecurityType());
					ps.setFloat(8,trade.getPrice());
					ps.setInt(9,trade.getSymbol().getsymbolID());			
					rows= ps.executeUpdate();
					
					
		 }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		         
		 switch(trade.getSecurityType().toLowerCase()) {
			
			case "equity":
				(new EquityDAOImpl()).addEquityInfo(new Equity(trade));
				break;
			case "future":
				(new FutureDAOImpl()).addFutureInfo(new Future(trade));
				break;
			case "calloption":
				(new CallOptionDAOImpl()).addCallOptionInfo(new CallOption(trade));
				break;
			case "putoption":
				(new PutOptionDAOImpl()).addPutOptionInfo(new PutOption(trade));
			default:
				System.out.println("Security type "+trade.getSecurityType()+" doesnt exist in the switch case in TradeDAOImpl");
				
			}
			
		 
		 
			
		 return rows;
	}




	@Override
	public int deleteTrade(int tradeID) {
		// TODO Auto-generated method stub
		int rows=0;
      Trade trade= findByTradeIDs(Arrays.asList(tradeID)).get(0);
	  
	  switch(trade.getSecurityType().toLowerCase()) {
		
		case "equity":
			(new EquityDAOImpl()).deleteEquityInfo(tradeID);
			break;
		case "future":
			(new FutureDAOImpl()).deleteFutureInfo(tradeID);
			break;
		case "call":
			(new CallOptionDAOImpl()).deleteCallOptionInfo(tradeID);
			break;
		case "put":
			(new PutOptionDAOImpl()).deletePutOptionInfo(tradeID);
		default:
			System.out.println("Security type "+trade.getSecurityType()+" doesnt exist in the switch case in TradeDAOImpl");
			
		}
		
		try(Connection con= openConnection()) {
			String REMOVE = "delete from tradebook where trade_id = ?";
			PreparedStatement ps=con.prepareStatement(REMOVE);
			ps.setInt(1,tradeID);
			rows=ps.executeUpdate();	
		
	}catch (Exception e) {
		e.printStackTrace();
	}   
		
		
		return rows;
	}


	@Override
	public List<Trade> findALLTrades() {
		// TODO Auto-generated method stub
		List<Trade> trade=new ArrayList<Trade>();
		 Trade trade1=null;
		String SQL_FIND_ALL="select * from trade order by tradeid asc";
		
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
	public int updateByTradeID(Trade trade, int tradeID) {
		int rows=0;
		try {
			
		deleteTrade(tradeID);
		addTrade(trade);
		rows=1;
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return rows;
		
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


