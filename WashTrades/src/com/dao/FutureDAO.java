package com.dao;

import com.pojos.Equity;
import com.pojos.Future;

public interface FutureDAO {
	
	int addFutureInfo(Future future);
	int deleteFutureInfo(int tradeID);
	boolean deleteAllFutures();

}
