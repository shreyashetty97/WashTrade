package com.dao;

import com.pojos.CallOption;
import com.pojos.Future;

public interface CallOptionDAO {
	int addCallOptionInfo(CallOption calloption);
	int deleteCallOptionInfo(int tradeID);
	boolean deleteAllCallOptions();

}
