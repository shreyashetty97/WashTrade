package com.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.dao.FutureDAOImpl;
import com.dao.PutOptionDAOImpl;
import com.pojos.Broker;
import com.pojos.Future;
import com.pojos.PutOption;
import com.pojos.Symbol;
import com.pojos.Trader;

class TestPutOption {

	@Test
	void testAddPutOption() {
		Trader trader=new Trader(1,"one");
		Broker broker=new Broker(2,"two");
		Symbol symbol=new Symbol(3,"three");
		Date date=new Date(2000,4,20);
		
		PutOptionDAOImpl dao=new PutOptionDAOImpl();//create an object of the class to be tested
		PutOption putoption=new PutOption(1, "p", date, 0, trader, broker, "p",0, symbol, date, 0, 0);
		int r=dao.addPutOptionInfo(putoption);
		
		assertEquals(1, r); 
		}
	
	@Test
	public void testDeletePutOption() {
		PutOptionDAOImpl dao=new PutOptionDAOImpl();
		assertEquals(0,dao.deletePutOptionInfo(1));
	
		
	}

}
