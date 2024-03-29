package com.dao;

import java.util.List;

import com.pojos.Trade;

public interface WashMapDAO {
	
	//is invoked when addWash of WashDAO is called
	int addWashTradeIDs(int washID,List<Integer> tradeIDs); 
	
	
	// is invoked when findAllTradesByWashID ic called from WashDAO
	List<Integer> findTradeIDsByWashID(int washID);
	
	//delete all tables
	boolean deleteAllWashMap();

}
