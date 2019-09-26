package com.dao;

import com.pojos.CallOption;
import com.pojos.PutOption;

public interface PutOptionDAO {
	
	int addPutOptionInfo(PutOption putoption);
	int deletePutOptionInfo(int tradeID);
	boolean deleteAllPutOptions();


}
