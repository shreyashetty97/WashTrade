package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;


import com.dao.tradeDao;

import com.pojos.Trade;
import com.pojos.Trader;
import com.pojos.Broker;

public class tradeDaoImpl implements tradeDao {

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
	    
	
	@Override
	public Trade findByTradeID(int tradeID,int traderID) {
		// TODO Auto-generated method stub
           Trade trade=null;
           Trader trader= null;	
           Broker broker=null; 
		
		try (Connection con=openConnection()){
			
			String FIND_TRADEID="select * from tradebook where trade_id = ? and trader_id=?";
			PreparedStatement ps=con.prepareStatement(FIND_TRADEID);
			
			ps.setInt(1, tradeID);
			ps.setInt(2, traderID);
			ResultSet resultSet=ps.executeQuery();
			
			while(resultSet.next()) {
				int TradeID= resultSet.getInt("trade_id");
				Date timestamp= resultSet.getDate("timestamp");
				String tradeType= resultSet.getString("trade_tp");
				int SymbolID = resultSet.getInt("symbol_id");
				String securityType= resultSet.getString("security_tp");
				int Volume = resultSet.getInt("volume");
				float Price = resultSet.getFloat("price");
				int TraderID = resultSet.getInt("trader_id");
				int BrokerID = resultSet.getInt("broker_id");
				String FIND_TRADERID="select * from traders where trader_id = ?";
				PreparedStatement ps1=con.prepareStatement(FIND_TRADERID);
				ps1.setInt(1, TraderID);
				ResultSet resultSet1=ps1.executeQuery();
				while(resultSet1.next()) {
					int Trader_ID=resultSet1.getInt("trader_id");
					String Name=resultSet1.getString("name");
				//	String Soeid=resultSet1.getString("soeid");
					trader= new Trader(Name);
					
				}
				String FIND_BROKERID="select * from brokers where broker_id = ?";
				PreparedStatement ps2=con.prepareStatement(FIND_BROKERID);
				ps1.setInt(1, TraderID);
				ResultSet resultSet2=ps2.executeQuery();
				while(resultSet2.next()) {
					int Broker_ID=resultSet2.getInt("broker_id");
					String Name=resultSet2.getString("name");
				//	String Soeid=resultSet2.getString("soeid");
					broker= new Broker(Name);
					
					
				}
				
				trade= new Trade(TradeID,tradeType,timestamp,Volume,trader,broker,securityType,Price,SymbolID);
				
			}
			
			//return object		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return trade;
	}

	@Override
	public List<Trade> findByTime(Date timestamp, int traderID) {
		// TODO Auto-generated method stub
		 List<Trade> trade= new ArrayList<Trade>();
		 Trade trade1=null;
         Trader trader= null;	
         Broker broker=null; 
			
			
			try (Connection con=openConnection()){
				
				String FIND_TRADEID="select * from tradebook where timestamp = ? and trader_id=?";
				PreparedStatement ps=con.prepareStatement(FIND_TRADEID);
				
				ps.setDate(1,timestamp );
				ps.setInt(2, traderID);
				ResultSet resultSet=ps.executeQuery();
				while(resultSet.next()) {
					int TradeID= resultSet.getInt("trade_id");
					Date timestamp1= resultSet.getDate("timestamp");
					String tradeType= resultSet.getString("trade_tp");
					int SymbolID = resultSet.getInt("symbol_id");
					String securityType= resultSet.getString("security_tp");
					int Volume = resultSet.getInt("volume");
					float Price = resultSet.getFloat("price");
					int TraderID = resultSet.getInt("trader_id");
					int BrokerID = resultSet.getInt("broker_id");
					String FIND_TRADERID="select * from traders where trader_id = ?";
					PreparedStatement ps1=con.prepareStatement(FIND_TRADERID);
					ps1.setInt(1, TraderID);
					ResultSet resultSet1=ps1.executeQuery();
					while(resultSet1.next()) {
						int Trader_ID=resultSet1.getInt("trader_id");
						String Name=resultSet1.getString("name");
					//	String Soeid=resultSet1.getString("soeid");
						trader= new Trader(Name);
						
					}
					String FIND_BROKERID="select * from brokers where broker_id = ?";
					PreparedStatement ps2=con.prepareStatement(FIND_BROKERID);
					ps1.setInt(1, TraderID);
					ResultSet resultSet2=ps2.executeQuery();
					while(resultSet2.next()) {
						int Broker_ID=resultSet2.getInt("broker_id");
						String Name=resultSet2.getString("name");
					//	String Soeid=resultSet2.getString("soeid");
						broker= new Broker(Name);
						
						
					}
					
					trade1= new Trade(TradeID,tradeType,timestamp,Volume,trader,broker,securityType,Price,SymbolID);
					trade.add(trade1);
					
				}
				
			
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
			return trade;
	}

	@Override
	public List<Trade> findByTradeType(String tradeType,int traderID ) {
		// TODO Auto-generated method stub
		List<Trade> trade= new ArrayList<Trade>();
		 Trade trade1=null;
        Trader trader= null;	
        Broker broker=null; 
			
			
			try (Connection con=openConnection()){
				
				String FIND_TRADETYPE="select * from tradebook where trade_tp = ? and trader_id=?";
				PreparedStatement ps=con.prepareStatement(FIND_TRADETYPE);
				
				ps.setString(1,tradeType );
				ps.setInt(2, traderID);
				ResultSet resultSet=ps.executeQuery();
				while(resultSet.next()) {
					int TradeID= resultSet.getInt("trade_id");
					Date timestamp= resultSet.getDate("timestamp");
					String tradeType1= resultSet.getString("trade_tp");
					int SymbolID = resultSet.getInt("symbol_id");
					String securityType= resultSet.getString("security_tp");
					int Volume = resultSet.getInt("volume");
					float Price = resultSet.getFloat("price");
					int TraderID = resultSet.getInt("trader_id");
					int BrokerID = resultSet.getInt("broker_id");
					String FIND_TRADERID="select * from traders where trader_id = ?";
					PreparedStatement ps1=con.prepareStatement(FIND_TRADERID);
					ps1.setInt(1, TraderID);
					ResultSet resultSet1=ps1.executeQuery();
					while(resultSet1.next()) {
						int Trader_ID=resultSet1.getInt("trader_id");
						String Name=resultSet1.getString("name");
					//	String Soeid=resultSet1.getString("soeid");
						trader= new Trader(Name);
						
					}
					String FIND_BROKERID="select * from brokers where broker_id = ?";
					PreparedStatement ps2=con.prepareStatement(FIND_BROKERID);
					ps1.setInt(1, TraderID);
					ResultSet resultSet2=ps2.executeQuery();
					while(resultSet2.next()) {
						int Broker_ID=resultSet2.getInt("broker_id");
						String Name=resultSet2.getString("name");
					//	String Soeid=resultSet2.getString("soeid");
						broker= new Broker(Name);
						
						
					}
					
					trade1= new Trade(TradeID,tradeType,timestamp,Volume,trader,broker,securityType,Price,SymbolID);;
					trade.add(trade1);
					
				}
				
			
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
			return trade;
	}

	@Override
	public List<Trade> findBySecurityType(String secTyp,int traderID) {
		// TODO Auto-generated method stub
		List<Trade> trade= new ArrayList<Trade>();
		 Trade trade1=null;
        Trader trader= null;	
        Broker broker=null; 
			
			
			try (Connection con=openConnection()){
				
				String FIND_SECTYPE="select * from tradebook where security_tp = ? and trader_id=?";
				PreparedStatement ps=con.prepareStatement(FIND_SECTYPE);
				
				ps.setString(1,secTyp );
				ps.setInt(2, traderID);
				ResultSet resultSet=ps.executeQuery();
				while(resultSet.next()) {
					int TradeID= resultSet.getInt("trade_id");
					Date timestamp= resultSet.getDate("timestamp");
					String tradeType= resultSet.getString("trade_tp");
					int SymbolID = resultSet.getInt("symbol_id");
					String securityType= resultSet.getString("security_tp");
					int Volume = resultSet.getInt("volume");
					float Price = resultSet.getFloat("price");
					int TraderID = resultSet.getInt("trader_id");
					int BrokerID = resultSet.getInt("broker_id");
					String FIND_TRADERID="select * from traders where trader_id = ?";
					PreparedStatement ps1=con.prepareStatement(FIND_TRADERID);
					ps1.setInt(1, TraderID);
					ResultSet resultSet1=ps1.executeQuery();
					while(resultSet1.next()) {
						int Trader_ID=resultSet1.getInt("trader_id");
						String Name=resultSet1.getString("name");
					//	String Soeid=resultSet1.getString("soeid");
						trader= new Trader(Name);
						
					}
					String FIND_BROKERID="select * from brokers where broker_id = ?";
					PreparedStatement ps2=con.prepareStatement(FIND_BROKERID);
					ps1.setInt(1, TraderID);
					ResultSet resultSet2=ps2.executeQuery();
					while(resultSet2.next()) {
						int Broker_ID=resultSet2.getInt("broker_id");
						String Name=resultSet2.getString("name");
					//	String Soeid=resultSet2.getString("soeid");
						broker= new Broker(Name);
						
						
					}
					
					trade1= new Trade(TradeID,tradeType,timestamp,Volume,trader,broker,securityType,Price,SymbolID);;
					trade.add(trade1);
					
				}
				
			
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
			return trade;
	}

	@Override
	public List<Trade> findBySymbolID(int Symbol,int traderID) {
		// TODO Auto-generated method stub
		List<Trade> trade= new ArrayList<Trade>();
		 Trade trade1=null;
        Trader trader= null;	
        Broker broker=null; 
			
			
			try (Connection con=openConnection()){
				
				String FIND_SYMBOLID="select * from tradebook where symbol_id = ? and trader_id = ?";
				PreparedStatement ps=con.prepareStatement(FIND_SYMBOLID);
				
				ps.setInt(1,Symbol);
				ps.setInt(2, traderID);
				ResultSet resultSet=ps.executeQuery();
				while(resultSet.next()) {
					int TradeID= resultSet.getInt("trade_id");
					Date timestamp= resultSet.getDate("timestamp");
					String tradeType= resultSet.getString("trade_tp");
					int SymbolID = resultSet.getInt("symbol_id");
					String securityType= resultSet.getString("security_tp");
					int Volume = resultSet.getInt("volume");
					float Price = resultSet.getFloat("price");
					int TraderID = resultSet.getInt("trader_id");
					int BrokerID = resultSet.getInt("broker_id");
					String FIND_TRADERID="select * from traders where trader_id = ?";
					PreparedStatement ps1=con.prepareStatement(FIND_TRADERID);
					ps1.setInt(1, TraderID);
					ResultSet resultSet1=ps1.executeQuery();
					while(resultSet1.next()) {
						int Trader_ID=resultSet1.getInt("trader_id");
						String Name=resultSet1.getString("name");
					//	String Soeid=resultSet1.getString("soeid");
						trader= new Trader(Name);
						
					}
					String FIND_BROKERID="select * from brokers where broker_id = ?";
					PreparedStatement ps2=con.prepareStatement(FIND_BROKERID);
					ps1.setInt(1, TraderID);
					ResultSet resultSet2=ps2.executeQuery();
					while(resultSet2.next()) {
						int Broker_ID=resultSet2.getInt("broker_id");
						String Name=resultSet2.getString("name");
					//	String Soeid=resultSet2.getString("soeid");
						broker= new Broker(Name);
						
						
					}
					
					trade1= new Trade(TradeID,tradeType,timestamp,Volume,trader,broker,securityType,Price,SymbolID);;
					trade.add(trade1);
					
				}
				
			
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
			return trade;
	}

	@Override
	public List<Trade> findByTraderID(int traderID) {
		// TODO Auto-generated method stub
		List<Trade> trade= new ArrayList<Trade>();
		 Trade trade1=null;
        Trader trader= null;	
        Broker broker=null; 
			
			
			try (Connection con=openConnection()){
				
				String FIND_TRADERID="select * from tradebook where trader_id = ?";
				PreparedStatement ps=con.prepareStatement(FIND_TRADERID);
				
				ps.setInt(1,traderID );
				
				ResultSet resultSet=ps.executeQuery();
				while(resultSet.next()) {
					int TradeID= resultSet.getInt("trade_id");
					Date timestamp= resultSet.getDate("timestamp");
					String tradeType= resultSet.getString("trade_tp");
					int SymbolID = resultSet.getInt("symbol_id");
					String securityType= resultSet.getString("security_tp");
					int Volume = resultSet.getInt("volume");
					float Price = resultSet.getFloat("price");
					int TraderID = resultSet.getInt("trader_id");
					int BrokerID = resultSet.getInt("broker_id");
					String FIND_TRADERID1="select * from traders where trader_id = ?";
					PreparedStatement ps1=con.prepareStatement(FIND_TRADERID1);
					ps1.setInt(1, TraderID);
					ResultSet resultSet1=ps1.executeQuery();
					while(resultSet1.next()) {
						int Trader_ID=resultSet1.getInt("trader_id");
						String Name=resultSet1.getString("name");
					//	String Soeid=resultSet1.getString("soeid");
						trader= new Trader(Name);
						
					}
					String FIND_BROKERID="select * from brokers where broker_id = ?";
					PreparedStatement ps2=con.prepareStatement(FIND_BROKERID);
					ps1.setInt(1, TraderID);
					ResultSet resultSet2=ps2.executeQuery();
					while(resultSet2.next()) {
						int Broker_ID=resultSet2.getInt("broker_id");
						String Name=resultSet2.getString("name");
					//	String Soeid=resultSet2.getString("soeid");
						broker= new Broker(Name);
						
						
					}
					
					trade1= new Trade(TradeID,tradeType,timestamp,Volume,trader,broker,securityType,Price,SymbolID);
					trade.add(trade1);
					
				}
				
			
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
			return trade;
	}

	@Override
	public List<Trade> findByBrokerID(int brokerID, int traderID) {
		// TODO Auto-generated method stub
		List<Trade> trade= new ArrayList<Trade>();
		 Trade trade1=null;
        Trader trader= null;	
        Broker broker=null; 
			
			
			try (Connection con=openConnection()){
				
				String FIND_BROKERID="select * from tradebook where broker_id = ?";
				PreparedStatement ps=con.prepareStatement(FIND_BROKERID);
				
				ps.setInt(1,brokerID );
				ps.setInt(2, traderID);
				ResultSet resultSet=ps.executeQuery();
				while(resultSet.next()) {
					int TradeID= resultSet.getInt("trade_id");
					Date timestamp= resultSet.getDate("timestamp");
					String tradeType= resultSet.getString("trade_tp");
					int SymbolID = resultSet.getInt("symbol_id");
					String securityType= resultSet.getString("security_tp");
					int Volume = resultSet.getInt("volume");
					float Price = resultSet.getFloat("price");
					int TraderID = resultSet.getInt("trader_id");
					int BrokerID = resultSet.getInt("broker_id");
					String FIND_TRADERID="select * from traders where trader_id = ?";
					PreparedStatement ps1=con.prepareStatement(FIND_TRADERID);
					ps1.setInt(1, TraderID);
					ResultSet resultSet1=ps1.executeQuery();
					while(resultSet1.next()) {
						int Trader_ID=resultSet1.getInt("trader_id");
						String Name=resultSet1.getString("name");
					//	String Soeid=resultSet1.getString("soeid");
						trader= new Trader(Name);
						
					}
					String FIND_BROKERID1="select * from brokers where broker_id = ?";
					PreparedStatement ps2=con.prepareStatement(FIND_BROKERID1);
					ps1.setInt(1, TraderID);
					ResultSet resultSet2=ps2.executeQuery();
					while(resultSet2.next()) {
						int Broker_ID=resultSet2.getInt("broker_id");
						String Name=resultSet2.getString("name");
					//	String Soeid=resultSet2.getString("soeid");
						broker= new Broker(Name);
						
						
					}
					
					trade1= new Trade(TradeID,tradeType,timestamp,Volume,trader,broker,securityType,Price,SymbolID);;
					trade.add(trade1);
					
				}
				
			
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
			return trade;
	}

	@Override
	public List<Trade> findAllTradesByTraderID(int traderID) {
		// TODO Auto-generated method stub
		
			// TODO Auto-generated method stub
			List<Trade> trade=new ArrayList<Trade>();
			 Trade trade1=null;
		     Trader trader= null;	
		     Broker broker=null; 
			
			
					try(Connection con=openConnection();){
						Statement st=con.createStatement();
						String SQL_FIND_ALL="select * from tradebook where trader_id =?";
						PreparedStatement ps=con.prepareStatement(SQL_FIND_ALL);
						ps.setInt(1, traderID);
						ResultSet resultSet=st.executeQuery(SQL_FIND_ALL);
						while(resultSet.next())
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
							String FIND_TRADERID="select * from traders where trader_id = ?";
							PreparedStatement ps1=con.prepareStatement(FIND_TRADERID);
							ps1.setInt(1, TraderID);
							ResultSet resultSet1=ps1.executeQuery();
							while(resultSet1.next()) {
								int Trader_ID=resultSet1.getInt("trader_id");
								String Name=resultSet1.getString("name");
							//	String Soeid=resultSet1.getString("soeid");
								trader= new Trader(Name);
								
							}
							String FIND_BROKERID1="select * from brokers where broker_id = ?";
							PreparedStatement ps2=con.prepareStatement(FIND_BROKERID1);
							ps1.setInt(1, TraderID);
							ResultSet resultSet2=ps2.executeQuery();
							while(resultSet2.next()) {
								int Broker_ID=resultSet2.getInt("broker_id");
								String Name=resultSet2.getString("name");
							//	String Soeid=resultSet2.getString("soeid");
								broker= new Broker(Name);
								
								
							}
							
							trade1= new Trade(TradeID,tradeType,timestamp,Volume,trader,broker,securityType,Price,SymbolID);;
							trade.add(trade1);
							
						}
						
					
						
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					
					
					return trade;
	}

	@Override
	public int addTrade(Trade trade ) {
		// TODO Auto-generated method stub
		
		int rows=0;
		 try(Connection con=openConnection()){
			 
					String INSERT_TRADE="INSERT INTO TRADEBOOK VALUES(?,?,?,?,?,?,?,?,?)";
					PreparedStatement ps= con.prepareStatement(INSERT_TRADE);
					
					ps.setInt(1, trade.getTradeID());
					ps.setDate(2, trade.getTimeStamp());
					ps.setString(3,trade.getTradeType());
					ps.setInt(4,trade.getSymbolID());
					ps.setString(5,trade.getSecurityType());
					ps.setInt(6,trade.getVolume());
					ps.setFloat(7,trade.getPrice());
//					ps.setTra(8,trade.getTrader());
//					ps.setnt(9,trade.getBroker());
					
					rows= ps.executeUpdate();
		 }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		 return rows;
		
	}

	@Override
	public Trade deleteTrade(int  tradeID, int traderID) {
		// TODO Auto-generated method stub
        Trade trade = findByTradeID(tradeID, traderID);
		
		try(Connection con= openConnection()) {
			String REMOVE = "delete from tradebook where trade_id = ? and trader_id = ? ";
			PreparedStatement ps=con.prepareStatement(REMOVE);
			
			ps.setInt(1, tradeID);
			ps.setInt(2, traderID);

			ResultSet resultSet=ps.executeQuery();
			
			return trade;	
		
	}catch (Exception e) {
		return null;}
		
	}

	@Override
	public boolean updatePrice(float price,int traderID) {
		// TODO Auto-generated method stub
		try(Connection con= openConnection()) {

			
			
			String UPDATE="update tradebook set price = ? where trader_id =?";
			PreparedStatement ps=con.prepareStatement(UPDATE);
			
			ps.setFloat(1, price);
			ps.setInt(2, traderID);
			
			ResultSet resultSet=ps.executeQuery();
			return true;
			
		//	}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;

	}
	


	@Override
	public boolean updateVolume(int volume,int traderID) {
		// TODO Auto-generated method stub
try(Connection con= openConnection()) {

			
			
			String UPDATE="update tradebook set volume = ? where trader_id =?";
			PreparedStatement ps=con.prepareStatement(UPDATE);
			
			ps.setInt(1, volume);
			ps.setInt(2, traderID);
			
			ResultSet resultSet=ps.executeQuery();
			return true;
			
		//	}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;

	}

	@Override
	public boolean updateTradeType(String tradeType,int traderID) {
		// TODO Auto-generated method stub
       try(Connection con= openConnection()) {

			
			
			String UPDATE="update tradebook set trade_tp = ? where trader_id =?";
			PreparedStatement ps=con.prepareStatement(UPDATE);
			
			ps.setString(1, tradeType);
			ps.setInt(2, traderID);
			
			ResultSet resultSet=ps.executeQuery();
			return true;
			
		//	}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;

	}

	@Override
	public boolean updateSecurityType(String secTyp,int traderID) {
		// TODO Auto-generated method stub
		 try(Connection con= openConnection()) {

				
				
				String UPDATE="update tradebook set security_tp = ? where trader_id =?";
				PreparedStatement ps=con.prepareStatement(UPDATE);
				
				ps.setString(1, secTyp);
				ps.setInt(2, traderID);
				
				ResultSet resultSet=ps.executeQuery();
				return true;
				
			//	}
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		return false;
	}

	@Override
	public boolean updateSymbolID(String symbol,int traderID) {
		// TODO Auto-generated method stub
		 try(Connection con= openConnection()) {

				
				
				String UPDATE="update tradebook set symbol_id = ? where trader_id =?";
				PreparedStatement ps=con.prepareStatement(UPDATE);
				
				ps.setString(1, symbol);
				ps.setInt(2, traderID);
				
				ResultSet resultSet=ps.executeQuery();
				return true;
				
			//	}
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		return false;
	}

//	@Override
//	public boolean updateTraderID(int traderID) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean updateBrokerID(int brokerID) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	
	
	
	
	
}
