package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pojos.Broker;
import com.pojos.Symbol;
import com.pojos.Trader;

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
			ps.setInt(1, symbol.getsymbolID());
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
	public Symbol getBySymbolID(int symbolID) {
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
        Symbol symbol=getBySymbolID(symbolID);
		
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





	@Override
	public Symbol findBySymbolName(String name) {
		// TODO Auto-generated method stub
		Symbol symbol=null;
		try(Connection con=openConnection()){
			
			String SELECT="select symbolID from symbol where name=? ";
			PreparedStatement st=con.prepareStatement(SELECT);
			st.setString(1,name);
		    ResultSet rs=st.executeQuery();
		    rs.next();
		    symbol=new Symbol(rs.getInt("symbolID"), name);
		    return symbol;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return symbol;
	}


	@Override
	public boolean deleteAllSymbols() {
		// TODO Auto-generated method stub
		try(Connection connection=openConnection()) {
			String DELETE_ALL="delete  from symbol";
			Statement st=connection.createStatement();
			st.executeUpdate(DELETE_ALL);
			
			
			return true;
			}
			catch (Exception e) {
				// TODO: handle exception
				return false;
			}
	}


	@Override
	public List<Symbol> findAllSymbols() {
		// TODO Auto-generated method stub
		List<Symbol>symbols=new ArrayList<Symbol>();
		String SQL_FIND_ALL="select * from symbol order by symbolID";
		try (Connection con=openConnection()){
			
			Statement st=con.createStatement();
			ResultSet resultSet=st.executeQuery(SQL_FIND_ALL);
			while(resultSet.next()) {
				
				int symbolID=resultSet.getInt("symbolID");
				String name=resultSet.getString("name");
				
				Symbol symbol=new Symbol(symbolID,name);
				symbols.add(symbol);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return symbols;
	}


	
}
