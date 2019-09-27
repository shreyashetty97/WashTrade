package com.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.dao.CallOptionDAOImpl;
import com.dao.PutOptionDAOImpl;
import com.pojos.Broker;
import com.pojos.CallOption;
import com.pojos.PutOption;
import com.pojos.Symbol;
import com.pojos.Trader;

class TestCallOption {

	@Test
	void testAddPutOption() {
		Trader trader=new Trader(1,"one");
		Broker broker=new Broker(2,"two");
		Symbol symbol=new Symbol(3,"three");
		Date date=new Date(2000,4,20);
		
		CallOptionDAOImpl dao=new CallOptionDAOImpl();//create an object of the class to be tested
		CallOption calloption=new CallOption(1, "p", date, 0, trader, broker, "p",0, symbol);
		int r=dao.addCallOptionInfo(calloption);
		
		assertEquals(1, r); 
		}
	
	@Test
	public void testDeletePutOption() {
		CallOptionDAOImpl dao=new CallOptionDAOImpl();
		assertEquals(0,dao.deleteCallOptionInfo(1));
	
		
	}

}
