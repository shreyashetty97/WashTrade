package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.pojos.PutOption;

public class PutOptionDAOImpl implements PutOptionDAO {
	
	
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
	public int addPutOptionInfo(PutOption putoption) {
		// TODO Auto-generated method stub
		int rows=0;
		try(Connection con=openConnection()){
			int tradeID=putoption.getTradeID();
			Date expiryDate=putoption.getExpiryDate();
			float Strike=putoption.getStrike();
			String INSERT="insert into equity values(?,?,?)";
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
	public int deletePutOptionInfo(int tradeID) {
		// TODO Auto-generated method stub
		 int rows = 0;
			
			
			try(Connection con=openConnection()){
				
				String DELETE="delete from putoption where tradeid=?";
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
	public boolean deleteAllPutOptions() {
		// TODO Auto-generated method stub
		try(Connection connection=openConnection()) {
			String DELETE_ALL="delete  from putoption";
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
