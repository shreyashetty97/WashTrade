package com.dao;

import com.pojos.Equity;

public interface EquityDAO {
	int addEquityInfo(Equity equity);
	int deleteEquityInfo(int tradeID);
	boolean deleteAllEquities();
}
