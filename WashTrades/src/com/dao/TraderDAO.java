package com.dao;


import java.util.List;


import com.pojos.Trader;

public interface TraderDAO {
	
	 int addTrader(Trader trader);
     Trader findByTraderID(int traderID);
     Trader findByTraderName(String name);
     Trader deleteByTraderID(int traderID);
     boolean deleteAllTraders();
     List<Trader> findAllTraders();

}
