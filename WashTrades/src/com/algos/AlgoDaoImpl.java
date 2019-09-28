package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pojos.Broker;
import com.pojos.Symbol;
import com.pojos.Trade;
import com.pojos.Trader;

public class AlgoDaoImpl implements AlgoDao{

	private Connection openConnection() {
		Connection connection=null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}


	public List<List<Trade>> createViews() {
		// TODO Auto-generated method stub


		List<List<Trade>> allTrades_list=new ArrayList<List<Trade>>();
		List<Trade> trades_list=new ArrayList<Trade>();
		Trade trade;
		try(Connection con=openConnection()){

			for(int i=1;i<=3;i++) {

				for(int j=1;j<=3;j++) {

					for(int k=1;k<=3;k++) {
						String SQL_QUERY=" select * from tradebook where traderid= "+ j +  " and symbolid=" + i + " and brokerid= " + k + " order by securityType" ;
						//			System.out.println(SQL_QUERY);
						PreparedStatement ps=con.prepareStatement(SQL_QUERY);

						ResultSet resultset=ps.executeQuery();
						while(resultset.next()) {

							int tradeID = resultset.getInt("tradeID");
							String tradeType=resultset.getString("tradeType");
							int traderID = resultset.getInt("traderId");
							int brokerID = resultset.getInt("brokerId");
							String securityType=resultset.getString("securityType");
							float price=resultset.getFloat("price");
							int symbolID = resultset.getInt("symbolid");
							int volume;
							if(tradeType.equals("buy"))
								volume=resultset.getInt("volume");
							else
								volume=-resultset.getInt("volume");


							TraderDAO traderDao = new TraderDAOImpl();
							Trader trader = traderDao.findByTraderID(traderID);

							BrokerDAO brokerDao = new BrokerDAOImpl();
							Broker broker = brokerDao.findBrokerByID(brokerID);

							SymbolDAO symbolDao = new SymbolDAOImpl();
							Symbol symbol = symbolDao.getBySymbolID(symbolID);




							trade = new Trade(tradeID, tradeType, trader, broker, securityType, price, symbol, volume);
							trades_list.add(trade);

						}
						allTrades_list.add(trades_list);
					}
				}
			}
		}

		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return allTrades_list;
	}
}


	//	public int createViews(int traderID,int brokerID,int symbolID,String viewName) {
	//		// TODO Auto-generated method stub
	//
	//	
	//		int rows=0;
	//		try(Connection con=openConnection()){
	//			String SQL_QUERY="create or replace view " +viewName + " as select tradeid,tradetype,volume,traderid,brokerid,securitytype,price from tradebook where traderid= "+ traderID  +  " and symbolid=" + symbolID + " and brokerid= " + brokerID + " order by securityType" ;
	////			System.out.println(SQL_QUERY);
	//			PreparedStatement ps=con.prepareStatement(SQL_QUERY);
	//			
	//			rows=ps.executeUpdate();
	//			
	//		}
	//		catch (SQLException e) {
	//			// TODO: handle exception
	//		e.printStackTrace();
	//		}
	//		
	//		return rows;
	//	}
	//
	//	public List<Trade> getViews(String viewName) {
	//		// TODO Auto-generated method stub
	//
	//		Trade trade ;
	//		List<Trade> trades_list=new ArrayList<Trade>();
	//		try(Connection con=openConnection()){
	//			
	//
	//			
	//			String SQL_QUERY="select * from "+viewName;
	//			//System.out.println(SQL_QUERY);
	//			PreparedStatement ps=con.prepareStatement(SQL_QUERY);
	//			
	//	
	//			ResultSet resultset=ps.executeQuery();
	//			while(resultset.next()) {
	//				int tradeID = resultset.getInt("tradeID");
	//			    String tradeType=resultset.getString("tradeType");
	//			    int traderID = resultset.getInt("traderId");
	//			    int brokerID = resultset.getInt("brokerId");
	//			    String securityType=resultset.getString("securityType");
	//				float price=resultset.getFloat("price");
	//				int symbolID = resultset.getInt("symbolid");
	//				int volume;
	//				if(tradeType.equals("buy"))
	//					volume=resultset.getInt("volume");
	//				else
	//					volume=-resultset.getInt("volume");
	//				
	//				
	//				TraderDAO traderDao = new TraderDAOImpl();
	//				Trader trader = traderDao.findByTraderID(traderID);
	//				
	//				BrokerDAO brokerDao = new BrokerDAOImpl();
	//				Broker broker = brokerDao.findBrokerByID(brokerID);
	//				
	//				SymbolDAO symbolDao = new SymbolDAOImpl();
	//				Symbol symbol = symbolDao.getBySymbolID(symbolID);
	//				
	//				
	//				
	//				
	//			trade = new Trade(tradeID, tradeType, trader, broker, securityType, price, symbol, volume);
	//			trades_list.add(trade);
	//				
	//			}
	//			
	//			
	//		}
	//		catch (SQLException e) {
	//			// TODO: handle exception
	//		e.printStackTrace();
	//		}
	//		
	//		return trades_list;
	//	}
	//
	//
	//}
//}
