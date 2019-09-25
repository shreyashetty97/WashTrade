package com.dao;

import java.util.List;

import com.pojos.Trade;

public interface WashMapDAO {
	
	int addWashTradeIDs(int washID,List<Integer> tradeIDs); //is invoked when addWash of WashDAO is called
	List<Integer> findTradeIDsByWashID(int washID);// is invoked when findAllTradesByWashID ic called from WashDAO

}
