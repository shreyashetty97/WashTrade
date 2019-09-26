package com.dao;
import java.sql.Date;
//
import java.util.List;

import com.pojos.*;
public interface tradeDao {

	// finding and searching
	Trade findByTradeID(int tradeID,int traderID);
	List<Trade> findByTime(Date timestamp,int traderID);
	List<Trade> findByTradeType(String tradeType,int traderID);
	List<Trade> findBySecurityType(String securityType,int traderID);
	List<Trade> findBySymbolID(int symbolID,int traderID);
	List<Trade> findByTraderID(int traderID);
	List<Trade> findByBrokerID(int brokerID,int traderID);
	List<Trade> findAllTradesByTraderID(int traderID );
	
	//adding
	int addTrade(Trade trade);
	
	
	
	//deleting
	Trade deleteTrade(int tradeID,int traderID);
	boolean deleteAll();
	
	
	//updating
	boolean updatePrice(float price,int traderID);
	boolean updateVolume(int volume,int traderID);
	boolean updateTradeType(String tradeType, int traderID);
	boolean updateSecurityType(String secTyp, int traderID);
	boolean updateSymbolID(String symbol, int traderID);
//	boolean updateTraderID(int traderID);
//	boolean updateBrokerID(int brokerID);
	
	
	
	
	
}
