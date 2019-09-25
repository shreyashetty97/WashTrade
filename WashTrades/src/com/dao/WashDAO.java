package com.dao;

import java.util.List;

import com.pojos.Trade;
import com.pojos.Wash;


public interface WashDAO {
	
	int AddWash(Wash wash);
	List<Wash> findAllWash();
	List<Trade> findTradesByWashID(int washID);
	   

}
