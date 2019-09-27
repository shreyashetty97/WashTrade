package com.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.dao.TradeDAOImpl;
import com.dao.WashMapDAO;
import com.dao.WashMapDAOImpl;
import com.pojos.Trade;

class TestTradeDao1Impl {
	@Test
	void testFindByTradeID() {
		TradeDAOImpl tradeDaoImpl = new TradeDAOImpl();
		Trade t=tradeDaoImpl.findByTradeID(100);
		System.out.println(t);
		assertEquals(expected, actual);
	}
	
	@Test
	void testFindByTradeIDnegative() {
		TradeDAOImpl tradeDaoImpl = new TradeDAOImpl();
		Trade t=tradeDaoImpl.findByTradeID(100);
		System.out.println(t);
		assertEquals(expected, actual);
	}
	
	@Test
	void testFindAllTradesByTraderID() {
		TradeDAOImpl tradeDaoImpl = new TradeDAOImpl();
        List<Trade> r = tradeDaoImpl.findAllTradesByTraderID(traderID);
        System.out.println(r);
        assertEquals(3, r.size());
	}
	
	@Test
	void testFindAllTradesByTraderIDnegative() {
		TradeDAOImpl tradeDaoImpl = new TradeDAOImpl();
        List<Trade> r = tradeDaoImpl.findAllTradesByTraderID(traderID);
        System.out.println(r);
        assertEquals(3, r.size());
	}
	
	@Test
	void testAddTrade() {
		TradeDAOImpl tradeDaoImpl = new TradeDAOImpl();
		Trade trade=new Trade();
		int r= tradeDaoImpl.addTrade(trade);
		assertEquals(r,1);
	}
	
	@Test
	void testAddTradeNegative() {
		TradeDAOImpl tradeDaoImpl = new TradeDAOImpl();
		Trade trade=new Trade();
		int r= tradeDaoImpl.addTrade(trade);
		assertEquals(r,0);
	}
	
	@Test
	void testDeleteTrade() {
		TradeDAOImpl tradeDaoImpl = new TradeDAOImpl();
		
		List<Trade> t= new ArrayList<Trade>();
		t= tradeDaoImpl.findALLTrades();
		
		Trade trade= tradeDaoImpl.deleteTrade(tradeID);
		List<Trade> t1= new ArrayList<Trade>();
		assertEquals(t.size()-1,t1.size());
		
	}
	
	@Test
	void testUpdateByTraderId() {
		 
		TradeDAOImpl tradeDaoImpl = new TradeDAOImpl();
		Trade trade=new Trade();
		int r= tradeDaoImpl.updateByTraderID(trade);
		assertEquals(r,1);
	}
	
	
	@Test
	void testUpdateByTraderIdnegative() {
		 
		TradeDAOImpl tradeDaoImpl = new TradeDAOImpl();
		Trade trade=new Trade();
		int r= tradeDaoImpl.updateByTraderID(trade);
		assertEquals(r,0);
	}
	
	@Test
	void testFindAll() {
        TradeDAOImpl tradeDaoImpl = new TradeDAOImpl();
		
		List<Trade> t= new ArrayList<Trade>();
		t= tradeDaoImpl.findALLTrades();
		 System.out.println(t);
	        assertEquals(3, t.size());
	}
	
	@Test
	void testDeleteAll() {
		
        TradeDAOImpl tradeDaoImpl = new TradeDAOImpl();
		
		boolean t;
		t= tradeDaoImpl.deleteAll();
		
	        assertEquals(true, t);
	}
	@Test
	void testDeleteAllNegative() {
		
        TradeDAOImpl tradeDaoImpl = new TradeDAOImpl();
		
		boolean t;
		t= tradeDaoImpl.deleteAll();
		
	        assertEquals(false, t);
	}


}
