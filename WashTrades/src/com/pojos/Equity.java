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
	  
	  public Equity(Trade trade){
		  
		  super(trade.getTradeID(),trade.getTradeType(), trade.getTimeStamp(),trade.getVolume(), trade.getTrader(), trade.getBroker(), trade.getSecurityType(), trade.getPrice(),trade.getSymbol());
		 
		  
	  }

	@Override
	public String toString() {
		return "Equity [tradeID=" + tradeID + ", tradeType=" + tradeType + ", timeStamp=" + timeStamp + ", volume="
				+ volume + ", trader=" + trader + ", broker=" + broker + ", securityType=" + securityType + ", price="
				+ price + ", symbol=" + symbol + "]";
	}
	  
	  
	 
	
}
