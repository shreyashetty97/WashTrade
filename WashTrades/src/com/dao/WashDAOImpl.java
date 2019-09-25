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
import com.pojos.Trade;
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
		// TODO Auto-generated method stub
		Trade trade = null;
		List<Wash> washList = new ArrayList<Wash>();
		String SQL_FIND_ALL = "select * from washtrades";
		try(Connection con=openConnection();){
			Statement st=con.createStatement();
			
			ResultSet resultSet=st.executeQuery(SQL_FIND_ALL);
			while(resultSet.next()) {
				int washID = resultSet.getInt("washID");
				int brokerID = resultSet.getInt("brokerID");
			    int traderID = resultSet.getInt("traderID");
//                String tradeType = resultSet.getString("trade type"); /// buy OR SELL
//				Date timeStamp = resultSet.getDate("timestamp");
//				int volume = resultSet.getInt("volume");;
//				String securityType = resultSet.getString("security type");;
//				float price = resultSet.getInt("price");
//				int symbolID = resultSet.getInt("symbolID");
			    
			    //call method with argument washid to fetch all tradeIDs
			    List<Trade> washTrades = func(washID);
			    
			    
                
				
				return washList;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;

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
		    ps.setInt(4, wash.getBrokerID());
		    ps.setInt(5, wash.getTraderID());
		    ps.setInt(6, wash.getBrokerID());

		    rows = ps.executeUpdate();

			
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return rows;

	}



	@Override
	public List<Trade> findTradesByWashID(int washID) {
		// TODO Auto-generated method stub
		return null;
	}

}
