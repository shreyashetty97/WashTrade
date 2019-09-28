package com.dao;

import java.util.List;

import com.pojos.Trade;

public interface AlgoDao {
	
//	int createViews(int traderID,int brokerID,int symbolID,String viewName);
//	List<Trade>getViews(String viewName);

	List<List<Trade>> createViews();

}
