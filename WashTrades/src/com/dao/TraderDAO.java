package com.dao;


import com.pojos.Trader;

public interface TraderDAO {
	
	 int addTrader(Trader trader);
     Trader getTraderID(int traderID);
     Trader deleteByIsbn(int traderID);

}
