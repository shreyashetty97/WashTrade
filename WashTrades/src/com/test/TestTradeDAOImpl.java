package com.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.dao.TradeDAOImpl;
import com.dao.TradeDAOImpl;
import com.dao.WashMapDAO;
import com.dao.WashMapDAOImpl;
import com.helper.TradeDate;
import com.pojos.Broker;
import com.pojos.Symbol;
import com.pojos.Trade;
import com.pojos.Trader;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

class TestTradeDAOImpl {
	@Test
	void testFindByTradeID() {
		TradeDAOImpl TradeDAOImpl = new TradeDAOImpl();
		List<Trade> t=TradeDAOImpl.findByTradeIDs(Arrays.asList(1,2,1200,700));
		System.out.println(t);
		assertEquals(34,t.get(0).getVolume());
	}
	
	@Test
	void testFindByTradeIDnegative() {
		TradeDAOImpl TradeDAOImpl = new TradeDAOImpl();
		List<Trade> t=TradeDAOImpl.findByTradeIDs(Arrays.asList(4,5));
		System.out.println(t);
		assertEquals(0,t.size());
	}
	
	@Test
	void testFindAllTradesByTraderID() {
		TradeDAOImpl TradeDAOImpl = new TradeDAOImpl();
        List<Trade> r = TradeDAOImpl.findAllTradesByTraderID(1,"equity");
        System.out.println(r);
        assertEquals("buy",r.get(0).getTradeType());
	}
	
	@Test
	void testFindAllTradesByTraderIDnegative() {
		TradeDAOImpl TradeDAOImpl = new TradeDAOImpl();
        List<Trade> r = TradeDAOImpl.findAllTradesByTraderID(1,"future");
        System.out.println(r);
        assertEquals(null,r.get(0).getTradeType());
	}
	
	@Test
	void testAddTrade() {
		TradeDAOImpl TradeDAOImpl = new TradeDAOImpl();
		TradeDate date = new TradeDate();
		
		
		Trader t = new Trader(4, "T4");
		
		Symbol s = new Symbol(4, "SDHJSGF");
		
		Broker b = new Broker(4, "b4");

		Trade trade = new Trade(4, "future", date.expDate() , 4, t , b, "buy", 12.34f, s);
		int r= TradeDAOImpl.addTrade(trade);
		assertEquals(1,r);
	}
	
	@Test
	void testAddTradeNegative() {
		TradeDAOImpl TradeDAOImpl = new TradeDAOImpl();
		TradeDate date = new TradeDate();
		
		
		Trader t = new Trader(4, "T4");
		
		Symbol s = new Symbol(4, "SDHJSGF");
		
		Broker b = new Broker(4, "b4");

		Trade trade = new Trade(4, "future", date.expDate() , 4, t , b, "buy", 12.34f, s);
		int r= TradeDAOImpl.addTrade(trade);
		assertEquals(0,r);
	}
	
	@Test
	void testDeleteTrade() {
		TradeDAOImpl TradeDAOImpl = new TradeDAOImpl();
		
		int r = TradeDAOImpl.deleteTrade(1);
		assertEquals(1,r);
		
	}
	
	@Test
	void testDeleteTradeNegative() {
		TradeDAOImpl TradeDAOImpl = new TradeDAOImpl();
		
		int r = TradeDAOImpl.deleteTrade(7);
		assertEquals(0,r);
		
	}
	
	
	@Test
	void testUpdateByTradeId() {
		 
		TradeDAOImpl TradeDAOImpl = new TradeDAOImpl();
		TradeDate date = new TradeDate();
		
		
		Trader t = new Trader(4, "T4");
		
		Symbol s = new Symbol(4, "SDHJSGF");
		
		Broker b = new Broker(4, "b4");
		
		Trade trade = new Trade(2, "buy", date.expDate(), 30, t, b, "equity", 12.23f, s);
		int tID = trade.getTradeID();
		int r= TradeDAOImpl.updateByTradeID(trade,tID);
		assertEquals(1,r);
	}
	
	
	@Test
	void testUpdateByTraderIdnegative() {
		 
		TradeDAOImpl TradeDAOImpl = new TradeDAOImpl();
		TradeDate date = new TradeDate();
		
		
		Trader t = new Trader(7, "T4");
		
		Symbol s = new Symbol(7, "SDHJSGF");
		
		Broker b = new Broker(7, "b4");
		
		Trade trade = new Trade(9, "buy", date.expDate(), 30, t, b, "equity", 12.23f, s);
		int tID = trade.getTradeID();
		int r= TradeDAOImpl.updateByTradeID(trade,tID);
		assertEquals(0,r);
	}
	
	@Test
	void testFindAll() {
        TradeDAOImpl TradeDAOImpl = new TradeDAOImpl();
		
		List<Trade> t= new ArrayList<Trade>();
		t= TradeDAOImpl.findALLTrades();
		 System.out.println(t);
	        assertEquals(3, t.size());
	}
	
	
	@Test
	void testDeleteAll() {
		
        TradeDAOImpl TradeDAOImpl = new TradeDAOImpl();
		
		boolean t;
		t= TradeDAOImpl.deleteAll();
		
	        assertEquals(true, t);
	}
	@Test
	void testDeleteAllNegative() {
		
        TradeDAOImpl TradeDAOImpl = new TradeDAOImpl();
		
		boolean t;
		t= TradeDAOImpl.deleteAll();
		
	        assertEquals(false, t);
	}


}
