package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pojos.Broker;
import com.pojos.Trader;

public class BrokerDAOImpl implements BrokerDAO {
	
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
	public int addBroker(Broker broker) {
		// TODO Auto-generated method stub

		int rows=0;
		try(Connection con=openConnection()){
			
			String INSERT_BROKER="insert into broker values(?,?)";
			PreparedStatement ps=con.prepareStatement(INSERT_BROKER);
			ps.setInt(1, broker.getBrokerID());
			ps.setString(2, broker.getName());
			
			rows=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return rows;
		
		
		
	}

	@Override
	public Broker getBrokerID(int brokerID) {
		// TODO Auto-generated method stub
		Broker broker=null;
		try(Connection con=openConnection()){
			
			String SELECT="select name from broker where brokerID=? ";
			PreparedStatement st=con.prepareStatement(SELECT);
			st.setInt(1,brokerID);
		    ResultSet rs=st.executeQuery();
		    rs.next();
		    broker=new Broker(brokerID, rs.getString("name"));
		    return broker;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return broker;
		
	}

	@Override
	public Broker deleteByBrokerID(int brokerID) {
		// TODO Auto-generated method stub
        Broker broker=getBrokerID(brokerID);
		
		if (broker!=null){
		try(Connection con=openConnection()){
			
		String DELETE="delete from broker where brokerID=?";
		PreparedStatement ps= con.prepareStatement(DELETE);
		ps.setInt(1, brokerID);
		int rows=ps.executeUpdate();
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
		return broker;
	}

}
