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
import java.util.stream.Collector;
import java.util.stream.Collectors;
import com.dao.TradeDAOImpl;
import com.pojos.Broker;
import com.pojos.Symbol;
import com.pojos.Trade;
import com.pojos.Trader;
import com.pojos.Wash;

public class WashDAOImpl implements WashDAO {
	
	private Connection openConnection() {
		Connection connection = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection =
			DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
		    
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
	public List<Wash> findAllWash() {
		ResultSet rs=null;
		List<Wash> washes=new ArrayList<Wash>(); 
		try(Connection con=openConnection()){
			
			String SELECT = "select * from wash order by washid asc";
		    PreparedStatement ps=con.prepareStatement(SELECT);
		    rs = ps.executeQuery();
		    while(rs.next()) {
				int washID=rs.getInt("washid");
			    Symbol symbol=(new SymbolDAOImpl()).getBySymbolID(rs.getInt("symbolid"));
			    Trader trader=(new TraderDAOImpl()).findByTraderID(rs.getInt("traderid"));
			    Broker broker=(new BrokerDAOImpl()).findBrokerByID(rs.getInt("brokerid"));
			    float priceMargin=rs.getFloat("pricemargin");
				float volumeMargin=rs.getFloat("volumemargin");
				List<Trade> trades= findTradesByWashID(washID);
				Wash wash= new Wash(washID, priceMargin, volumeMargin, symbol, broker, trader, trades);
				washes.add(wash);
			}
        }
		
	catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	
		return washes;
		

	}



	@Override
	public int AddWash(Wash wash) {
		// TODO Auto-generated method stub
		int washAdded=0;
		try(Connection con=openConnection()){
			
			String INSERT_BOOK =
		    		"insert into washtrades values(?,?,?,?,?)";
		    PreparedStatement ps=con.prepareStatement(INSERT_BOOK);
		    
		    
		    ps.setInt(1, wash.getWashID());
		    ps.setFloat(2, wash.getPriceMargin());
		    ps.setFloat(3, wash.getVolumeMargin());
		    ps.setInt(4, wash.getBroker().getBrokerID());
		    ps.setInt(5, wash.getTrader().getTraderID());
		    ps.setInt(6, wash.getSymbol().getsymbolID());

		    washAdded = ps.executeUpdate();
        }
		
		
		
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		List<Trade> tradeList = wash.getTrades();
		List<Integer> tradeIds= tradeList.stream().map((t)->{return t.getTradeID();}).collect(Collectors.toList());
		WashMapDAO washMapDAO= new WashMapDAOImpl();
		washMapDAO.addWashTradeIDs(wash.getWashID(), tradeIds);
		
		return washAdded;

	}



	@Override
	public List<Trade> findTradesByWashID(int washID) {
		List<Integer> tradeIDs= (new WashMapDAOImpl()).findTradeIDsByWashID(washID);
		List<Trade> washTrades= (new TradeDAOImpl().findByTradeIDs(tradeIDs));
		return washTrades;
	}



	@Override
	public boolean deleteAllWash() {
		// TODO Auto-generated method stub
		String SQL_QUERY_DELETE_WASH = "delete from wash";
		try {
			Connection con = openConnection();{
				
			    Statement s = con.createStatement();
				s.executeUpdate(SQL_QUERY_DELETE_WASH);
				return true;
		   
			}
					}catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
		
		return false;
	}
	}
	
	


