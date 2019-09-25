package com.dao;

import java.util.List;

import com.pojos.Trade;
import com.pojos.Wash;


public interface WashDAO {
	
	int AddWash(Wash wash); //has to invoke the addWashTradeIDs method of WashMAPDAO.
	List<Trade> findTradesByWashID(int washID);//has to take the help of findTradeIdsByWashID() to pull out trades from tradeDAO.
	List<Wash> findAllWash();//has to find all the washID's from the Wash table and construct a Wash object out of it with the help of findTradesByWashID 
	
	 
	   

}
