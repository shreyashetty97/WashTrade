package com.pojos;

import java.sql.Date;

import com.helper.TradeDate;

public class Future extends Trade {
	
	protected final static Date expiryDate= (new TradeDate()).expDate();
	
	public Future() {
		super();
	}

	public Future(int tradeID, String tradeType, Date timeStamp, int volume,Trader trader,Broker broker,
			String securityType, float price,Symbol symbol){
		
		super( tradeID,  tradeType,  timeStamp,  volume,  trader,  broker,
				 securityType,  price,  symbol);
	}
	
	
		public Future(Trade trade){
			  
			  super(trade.getTradeID(),trade.getTradeType(), trade.getTimeStamp(),trade.getVolume(), trade.getTrader(), trade.getBroker(), trade.getSecurityType(), trade.getPrice(),trade.getSymbol());
			 
			  
		  }
	
		
	public Date getExpiryDate() {
		return expiryDate;
	}
	

	@Override
	public String toString() {
		return "Future [expiryDate=" + expiryDate + ", tradeID=" + tradeID + ", tradeType=" + tradeType + ", timeStamp="
				+ timeStamp + ", volume=" + volume + ", trader=" + trader + ", broker=" + broker + ", securityType="
				+ securityType + ", price=" + price + ", symbol=" + symbol + "]";
	}
	
	public static void main(String args[]) {
		Trade tr=new Trade();
		Future fr= (Future) tr;
	
	}
	
}
