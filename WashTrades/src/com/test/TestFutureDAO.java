package com.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.dao.EquityDAOImpl;
import com.dao.FutureDAOImpl;
import com.pojos.Broker;
import com.pojos.Equity;
import com.pojos.Future;
import com.pojos.Symbol;
import com.pojos.Trader;

public class TestFutureDAO {


	@Test
	void testAddFuture() {
		Trader trader=new Trader(1,"one");
		Broker broker=new Broker(2,"two");
		Symbol symbol=new Symbol(3,"three");
		Date date=new Date(2000,4,20);
		
		FutureDAOImpl dao=new FutureDAOImpl();//create an object of the class to be tested
		Future future=new Future(1, "h", date, 0, trader, broker, "fuure",0, symbol);
		int r=dao.addFutureInfo(future);
		
		assertEquals(1, r); 
		}
	
	@Test
	public void testDeleteFuture() {
		FutureDAOImpl dao=new FutureDAOImpl();
		assertEquals(0,dao.deleteFutureInfo(1));
	
		
	}

}
