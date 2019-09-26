package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.pojos.Trader;

public class TraderDAOImpl implements TraderDAO {
	
	

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
	public int addTrader(Trader trader) {
		// TODO Auto-generated method stub
		
		int rows=0;
		try(Connection con=openConnection()){
			
			String INSERT_TRADER="insert into trader values(?,?)";
			PreparedStatement ps=con.prepareStatement(INSERT_TRADER);
			ps.setInt(1, trader.getTraderID());
			ps.setString(2, trader.getName());
			
			rows=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return rows;
		
	}

	@Override
	public Trader findByTraderID(int traderID) {
		// TODO Auto-generated method stub
		Trader trader=null;
		try(Connection con=openConnection()){
			
			String SELECT="select name from trader where traderID=? ";
			PreparedStatement st=con.prepareStatement(SELECT);
			st.setInt(1,traderID);
		    ResultSet rs=st.executeQuery();
		    rs.next();
		    trader=new Trader(traderID, rs.getString("name"));
		    return trader;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return trader;
		
		
	}

	@Override
	public Trader deleteByIsbn(int traderID) {
		// TODO Auto-generated method stub
	Trader trader=findByTraderID(traderID);
		
		if (trader!=null){
		try(Connection con=openConnection()){
			
		String DELETE="delete from trader where traderID=?";
		PreparedStatement ps= con.prepareStatement(DELETE);
		ps.setInt(1, traderID);
		int rows=ps.executeUpdate();
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
		return trader;
	}

	@Override
	public Trader findByTraderName(String name) {
		// TODO Auto-generated method stub
		Trader trader=null;
		try(Connection con=openConnection()){
			
			String SELECT="select traderID from trader where name=? ";
			PreparedStatement st=con.prepareStatement(SELECT);
			st.setString(1,name);
		    ResultSet rs=st.executeQuery();
		    rs.next();
		    trader=new Trader(rs.getInt("traderID"), name);
		    return trader;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return trader;
		
	}

}
