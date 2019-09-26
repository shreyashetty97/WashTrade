package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.pojos.Trade;

public class WashMapDAOImpl implements WashMapDAO {
	
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
	public int addWashTradeIDs(int washID, List<Integer> tradeIDs) {
		// TODO Auto-generated method stub
		int washAdded=0;
		try(Connection con=openConnection()){
			
			for(int i=0;i<tradeIDs.size();i++) {
			
			String INSERT_WASHMAP =
		    		"insert into washmap values(?,?)";
		    PreparedStatement ps=con.prepareStatement(INSERT_WASHMAP);
		    
		   
		    
		    ps.setInt(1, washID);
		    ps.setInt(2, tradeIDs.get(i));
		    
		    washAdded += ps.executeUpdate();
			}
			
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

		return washAdded;
	}

	
	@Override
	public List<Integer> findTradeIDsByWashID(int washID) {
		// TODO Auto-generated method stub
		List<Integer> tradeIDs = new ArrayList<Integer>();
		String SQL_FIND_TRADE_IDS = "select tradeid from washmap where washid=? order by tradeid";
		try {
			Connection con = openConnection();{
				
				PreparedStatement ps = con.prepareStatement(SQL_FIND_TRADE_IDS);
				ps.setInt(1, washID);
				ResultSet rs = ps.executeQuery();
				//System.out.println(rs.getFetchSize());
				while(rs.next()) {
//					System.out.println("inside while");
					int trade = rs.getInt("tradeid");
//					System.out.println(trade);
				    tradeIDs.add(trade);					
				}
	//			System.out.println("while exited");
		   
			}
					}catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
		
		return tradeIDs;
	}


	@Override
	public boolean deleteAllWashMap() {
		// TODO Auto-generated method stub
		String SQL_QUERY_DELETE_WASHMAP = "delete from washmap";
		try {
			Connection con = openConnection();{
				
			    Statement s = con.createStatement();
				s.executeUpdate(SQL_QUERY_DELETE_WASHMAP);
				return true;
		   
			}
					}catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
		

		return false;
	}

	

}
