package com.test;


import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.dao.EquityDAOImpl;
import com.dao.TraderDAOImpl;
import com.pojos.Broker;
import com.pojos.Equity;
import com.pojos.Symbol;
import com.pojos.Trader;

class TestEquityDAO {

	@Test
	void testAddEquity() {
		Trader trader=new Trader(1,"one");
		Broker broker=new Broker(2,"two");
		Symbol symbol=new Symbol(3,"three");
		Date date=new Date(2000,4,20);
		
		EquityDAOImpl dao=new EquityDAOImpl();//create an object of the class to be tested
		Equity equity=new Equity(1,"r", date, 9, trader, broker, "equity", 0, symbol);
		int r=dao.addEquityInfo(equity);
		
		assertEquals(1, r); 
		}
	
	@Test
	public void testDeleteEquity() {
		EquityDAOImpl dao=new EquityDAOImpl();
		assertEquals(0,dao.deleteEquityInfo(1));
	
		
	}

}
