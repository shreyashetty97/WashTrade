package com.pojos;

import java.sql.Date;;

public class Equity extends Trade {
	 
	  public Equity() {
		 super();
	 }
	  
	  public Equity(int tradeID, String tradeType, Date timeStamp, int volume, Trader trader, Broker broker,
				String securityType, float price, Symbol symbol) {
		  super( tradeID,  tradeType,  timeStamp,  volume,  trader,  broker,
			 securityType,  price,  symbol);
	  }

	@Override
	public String toString() {
		return "Equity [tradeID=" + tradeID + ", tradeType=" + tradeType + ", timeStamp=" + timeStamp + ", volume="
				+ volume + ", trader=" + trader + ", broker=" + broker + ", securityType=" + securityType + ", price="
				+ price + ", symbol=" + symbol + "]";
	}
	  
	  
	 
	
}
