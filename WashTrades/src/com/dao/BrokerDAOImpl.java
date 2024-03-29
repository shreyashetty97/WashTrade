package com.dao;
import com.helper.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.pojos.Broker;
import com.pojos.Trader;

public class BrokerDAOImpl implements BrokerDAO {
	
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
	public int addBroker(Broker broker) {
		// TODO Auto-generated method stub

		int rows=0;
		try(Connection con= openConnection()){
			
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
	public Broker findBrokerByID(int brokerID) {
		// TODO Auto-generated method stub
		Broker broker=null;
		try(Connection con= openConnection()){
			
			String SELECT="select name from broker where brokerID=? ";
			PreparedStatement st=con.prepareStatement(SELECT);
			st.setInt(1,brokerID);
		    ResultSet rs=st.executeQuery();
		    
		    while(rs.next()) {
		    broker=new Broker(brokerID, rs.getString("name"));
		    }
			
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
        Broker broker=findBrokerByID(brokerID);
		
		if (broker!=null){
		try(Connection con= openConnection()){
			
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

	@Override
	public Broker findBrokerByName(String name) {
		// TODO Auto-generated method stub
		Broker broker=null;
		try(Connection con= openConnection()){
			
			String SELECT="select brokerID from broker where name=? ";
			PreparedStatement st=con.prepareStatement(SELECT);
			st.setString(1,name);
		    ResultSet rs=st.executeQuery();
		    while(rs.next()) {
		    broker=new Broker(rs.getInt("brokerID"), name);
		    }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return broker;
		
	}

	@Override
	public boolean deleteAllBrokers() {
		// TODO Auto-generated method stub
		try (Connection con= openConnection()){
			
			String DELETE_ALL="delete  from broker";
			Statement st=con.createStatement();
			st.executeUpdate(DELETE_ALL);
			
			
			return true;
			}
			catch (Exception e) {
				// TODO: handle exception
				return false;
			}
	}

	@Override
	public List<Broker> findAllBrokers() {
		// TODO Auto-generated method stub
		List<Broker>brokers=new ArrayList<Broker>();
		String SQL_FIND_ALL="select * from broker order by brokerID";
		try(Connection con= openConnection()) {
			
			Statement st=con.createStatement();
			ResultSet resultSet=st.executeQuery(SQL_FIND_ALL);
			while(resultSet.next()) {
				System.out.println("inside while");
				int brokerID=resultSet.getInt("brokerID");
				String name=resultSet.getString("name");
				
				Broker broker=new Broker(brokerID,name);
				brokers.add(broker);
				
				
			}System.out.println("outside while");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(brokers);		
		return brokers;
	}
	
//	public static void main(String[] args) {
//		
//	BrokerDAOImpl dao=new BrokerDAOImpl();
//	System.out.println(dao.findBrokerByName("one"));
//	dao.closeConnection();
//	
//	dao=new BrokerDAOImpl();
//	System.out.println(dao.findAllBrokers());
//	dao.closeConnection();
//		
//	}
//	
	}


