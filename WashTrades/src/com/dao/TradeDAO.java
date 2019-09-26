package com.dao;

import java.util.List;

import com.pojos.Trade;

public interface TradeDAO {
	
	//find by trade ID
	List<Trade> findByTradeIDs(List<Integer> tradeID);
	//find by traderID
	List<Trade> findAllTradesByTraderID(int traderID, String securityType);
	
	//find all trades
	List<Trade> findALLTrades();
	
	//add trade
	int addTrade(Trade trade) throws ClassCastException;
	
	
	// delete trade by trade
	int deleteTrade(int tradeID);
	
	//new trade with the old tradeID
	int updateByTradeID(Trade trade, int tradeID);
	
	//delete all records
	boolean deleteAll();
	

}
