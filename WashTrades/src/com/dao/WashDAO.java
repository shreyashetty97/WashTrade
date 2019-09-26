package com.dao;

import java.util.List;

import com.pojos.Trade;
import com.pojos.Wash;


public interface WashDAO {
	
	//has to invoke the addWashTradeIDs method of WashMAPDAO.
	int AddWash(Wash wash); 
	
	//has to take the help of findTradeIdsByWashID() to pull out trades from tradeDAO.
	List<Trade> findTradesByWashID(int washID);
	
	//has to find all the washID's from the Wash table and construct a Wash object out of it with the help of findTradesByWashID 
	List<Wash> findAllWash();
	boolean deleteAllWash(); 
	   }
