package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pojos.Broker;
import com.pojos.Symbol;

public class SymbolDAOImpl implements SymbolDAO {
	
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
	public int addSymbol(Symbol symbol) {
		// TODO Auto-generated method stub
		int rows=0;
		try(Connection con=openConnection()){
			
			String INSERT_SYMBOL="insert into symbol values(?,?)";
			PreparedStatement ps=con.prepareStatement(INSERT_SYMBOL);
			ps.setInt(1, symbol.getSymbolId());
			ps.setString(2, symbol.getName());
			
			rows=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return rows;
		
	}

	@Override
	public Symbol getSymbolID(int symbolID) {
		// TODO Auto-generated method stub
		Symbol symbol=null;
		try(Connection con=openConnection()){
			
			String SELECT="select name from symbol where symbolID=? ";
			PreparedStatement st=con.prepareStatement(SELECT);
			st.setInt(1,symbolID);
		    ResultSet rs=st.executeQuery();
		    rs.next();
		    symbol=new Symbol(symbolID, rs.getString("name"));
		    return symbol;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return symbol;
		
	}

	@Override
	public Symbol deleteBySymbolID(int symbolID) {
		// TODO Auto-generated method stub
        Symbol symbol=getSymbolID(symbolID);
		
		if (symbol!=null){
		try(Connection con=openConnection()){
			
		String DELETE="delete from symbol where symbolID=?";
		PreparedStatement ps= con.prepareStatement(DELETE);
		ps.setInt(1, symbolID);
		int rows=ps.executeUpdate();
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
		return symbol;
	}

}