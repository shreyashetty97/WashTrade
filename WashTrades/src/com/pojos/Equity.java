package com.pojos;

import java.sql.Date;;

public class Equity extends Trade {
	 
	  public Equity() {
		 super();
	 }
	  
	  public Equity(int tradeID, String tradeType, Date timeStamp, int volume, int traderID, int brokerID,
				String securityType, float price, int symbolID) {
		  super( tradeID,  tradeType,  timeStamp,  volume,  traderID,  brokerID,
			 securityType,  price,  symbolID);
	  }
	  
	  
	 
	
}
