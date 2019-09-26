package com.dao;

import java.util.List;

import com.pojos.Trade;

public interface TradeDAO1 {
	
	//find by trade ID
	Trade findByTradeID(int tradeID);
	//find by traderID
	List<Trade> findAllTradesByTraderID(int traderID );
	
	//find all trades
	List<Trade> findALLTrades();
	
	//add trade
	int addTrade(Trade trade);
	
	
	// delete trade by trader ID
	Trade deleteTrade(int tradeID);
	
	//delete all records
	boolean deleteAll();
	
	
	
	// update Trade
	int updateByTraderID (Trade trade);

}
