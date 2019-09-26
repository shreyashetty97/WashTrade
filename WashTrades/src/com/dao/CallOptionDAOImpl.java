package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.pojos.CallOption;

public class CallOptionDAOImpl implements CallOptionDAO {
	
	public Connection openConnection() {
		Connection connection=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //loading class
			connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			System.out.println(connection);
			return connection;
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
	public int addCallOptionInfo(CallOption calloption) {
		// TODO Auto-generated method stub
		
		int rows=0;
		try(Connection con=openConnection()){
			int tradeID=calloption.getTradeID();
			Date expiryDate=calloption.getExpiryDate();
			float expiryPrice=calloption.getExpiryPrice();
			float Strike=calloption.getStrike();
			String INSERT="insert into equity values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(INSERT);
			ps.setInt(1, tradeID);
			ps.setDate(2, expiryDate);
			ps.setFloat(3, Strike);
			ps.setFloat(4, Strike);
			
			
			rows=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return rows;
		
	}

	@Override
	public int deleteCallOptionInfo(int tradeID) {
		// TODO Auto-generated method stub
		 int rows = 0;
			
			
			try(Connection con=openConnection()){
				
				String DELETE="delete from calloption where tradeid=?";
			    PreparedStatement ps= con.prepareStatement(DELETE);
			    ps.setInt(1, tradeID);
		        rows=ps.executeUpdate();
		        return rows;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			return rows;
	}

	@Override
	public boolean deleteAllCallOptions() {
		// TODO Auto-generated method stub
		try(Connection connection=openConnection()) {
			String DELETE_ALL="delete  from calloption";
			Statement st=connection.createStatement();
			st.executeUpdate(DELETE_ALL);
			
			
			return true;
			}
			catch (Exception e) {
				// TODO: handle exception
				return false;
			}
	}
	
	

}
