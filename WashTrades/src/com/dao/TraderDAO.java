package com.dao;


import com.pojos.Trader;

public interface TraderDAO {
	
	 int addTrader(Trader trader);
     Trader findByTraderID(int traderID);
     Trader findByTraderName(String name);
     Trader deleteByTraderID(int traderID);
     boolean deleteAllTraders();

}
