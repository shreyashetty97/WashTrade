package com.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.dao.TradeDao1Impl;
import com.dao.WashMapDAO;
import com.dao.WashMapDAOImpl;
import com.pojos.Trade;

class TestTradeDao1Impl {
	@Test
	void testFindByTradeID() {
		TradeDao1Impl tradeDaoImpl = new TradeDao1Impl();
		Trade t=tradeDaoImpl.findByTradeID(100);
		System.out.println(t);
		assertEquals(expected, actual);
	}
	
	@Test
	void testFindByTradeIDnegative() {
		TradeDao1Impl tradeDaoImpl = new TradeDao1Impl();
		Trade t=tradeDaoImpl.findByTradeID(100);
		System.out.println(t);
		assertEquals(expected, actual);
	}
	
	@Test
	void testFindAllTradesByTraderID() {
		TradeDao1Impl tradeDaoImpl = new TradeDao1Impl();
        List<Trade> r = tradeDaoImpl.findAllTradesByTraderID(traderID);
        System.out.println(r);
        assertEquals(3, r.size());
	}
	
	@Test
	void testFindAllTradesByTraderIDnegative() {
		TradeDao1Impl tradeDaoImpl = new TradeDao1Impl();
        List<Trade> r = tradeDaoImpl.findAllTradesByTraderID(traderID);
        System.out.println(r);
        assertEquals(3, r.size());
	}
	
	@Test
	void testAddTrade() {
		TradeDao1Impl tradeDaoImpl = new TradeDao1Impl();
		Trade trade=new Trade();
		int r= tradeDaoImpl.addTrade(trade);
		assertEquals(r,1);
	}
	
	@Test
	void testAddTradeNegative() {
		TradeDao1Impl tradeDaoImpl = new TradeDao1Impl();
		Trade trade=new Trade();
		int r= tradeDaoImpl.addTrade(trade);
		assertEquals(r,0);
	}
	
	@Test
	void testDeleteTrade() {
		TradeDao1Impl tradeDaoImpl = new TradeDao1Impl();
		
		List<Trade> t= new ArrayList<Trade>();
		t= tradeDaoImpl.findALLTrades();
		
		Trade trade= tradeDaoImpl.deleteTrade(tradeID);
		List<Trade> t1= new ArrayList<Trade>();
		assertEquals(t.size()-1,t1.size());
		
	}
	
	@Test
	void testUpdateByTraderId() {
		 
		TradeDao1Impl tradeDaoImpl = new TradeDao1Impl();
		Trade trade=new Trade();
		int r= tradeDaoImpl.updateByTraderID(trade);
		assertEquals(r,1);
	}
	
	
	@Test
	void testUpdateByTraderIdnegative() {
		 
		TradeDao1Impl tradeDaoImpl = new TradeDao1Impl();
		Trade trade=new Trade();
		int r= tradeDaoImpl.updateByTraderID(trade);
		assertEquals(r,0);
	}
	
	@Test
	void testFindAll() {
        TradeDao1Impl tradeDaoImpl = new TradeDao1Impl();
		
		List<Trade> t= new ArrayList<Trade>();
		t= tradeDaoImpl.findALLTrades();
		 System.out.println(t);
	        assertEquals(3, t.size());
	}
	
	@Test
	void testDeleteAll() {
		
        TradeDao1Impl tradeDaoImpl = new TradeDao1Impl();
		
		boolean t;
		t= tradeDaoImpl.deleteAll();
		
	        assertEquals(true, t);
	}
	@Test
	void testDeleteAllNegative() {
		
        TradeDao1Impl tradeDaoImpl = new TradeDao1Impl();
		
		boolean t;
		t= tradeDaoImpl.deleteAll();
		
	        assertEquals(false, t);
	}


}
