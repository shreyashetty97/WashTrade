package com.dao;

import com.pojos.Trade;

public interface TradeDAO1 {
	Trade findByTradeID(int tradeID,int traderID);

}
