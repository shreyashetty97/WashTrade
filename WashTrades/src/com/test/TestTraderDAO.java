package com.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.dao.TraderDAOImpl;
import com.pojos.Trader;

class TestTraderDAO {

	@Test
	void testAddTrader() {
		TraderDAOImpl dao=new TraderDAOImpl();//create an object of the class to be tested
		Trader trader=new Trader(999,"name1");
		int r=dao.addTrader(trader);
		
		assertEquals(1, r); 
		}
	@Test
	public void testFindByTraderID() {  
		TraderDAOImpl dao=new TraderDAOImpl();
		Trader trader=dao.findByTraderID(0);
		assertEquals("name", trader.getName());
		
	}
	
	@Test
	public void testFindByTraderID_negative() {
		TraderDAOImpl dao=new TraderDAOImpl();
		Trader trader=dao.findByTraderID(2);
		assertEquals(null, trader);
		
	}
	@Test
	public void testFindByTraderName() {
		TraderDAOImpl dao=new TraderDAOImpl();
		Trader trader=dao.findByTraderName("name");
		assertEquals(0, trader.getTraderID());
		
	}
	
	@Test
	public void testFindByTraderName_negative() {
		TraderDAOImpl dao=new TraderDAOImpl();
		Trader trader=dao.findByTraderName("name2");
		assertEquals(0, trader.getTraderID());
		
	}
	@Test
	public void testDeleteByTraderID() {
		TraderDAOImpl dao=new TraderDAOImpl();
		assertEquals(0,dao.deleteByTraderID(0).getTraderID());
		assertEquals(null,dao.deleteByTraderID(0));
		
	}
	
	@Test
	public void testDeleteByTraderID_negative() {
		TraderDAOImpl dao=new TraderDAOImpl();
		assertEquals(null,dao.deleteByTraderID(9));
		
	}
	@Test
	public void testDeleteAllTraders() {
		
		TraderDAOImpl dao=new TraderDAOImpl();
		boolean del=dao.deleteAllTraders();
		System.out.println(del);
		
	}


}
