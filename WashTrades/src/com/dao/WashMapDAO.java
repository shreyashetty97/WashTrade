package com.dao;

import java.util.List;

import com.pojos.Trade;

public interface WashMapDAO {
	
	int addWashTrades(List<Integer> tradeID);
	List<Trade> findTradeIDsByWashID(int washID);

}
