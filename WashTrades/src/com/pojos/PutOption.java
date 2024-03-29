package com.pojos;

import java.sql.Date;

import com.helper.TradeDate;

public class PutOption extends Trade {

	
	private final static Date expiryDate=(new TradeDate()).expDate();
	private final static float strike=105;

	public PutOption() {
		super();
	}

	public PutOption(int tradeID, String tradeType, Date timeStamp, int volume, Trader trader, Broker broker,
			String securityType, float price, Symbol symbol) {
		super(tradeID,  tradeType,  timeStamp,  volume,  trader,  broker,
				 securityType,  price,  symbol);
	}
	
	
	public PutOption(Trade trade){
		  
		  super(trade.getTradeID(),trade.getTradeType(), trade.getTimeStamp(),trade.getVolume(), trade.getTrader(), trade.getBroker(), trade.getSecurityType(), trade.getPrice(),trade.getSymbol());
		 
		  
	  }

	public Date getExpiryDate() {
		return expiryDate;
	}

	public float getStrike() {
		return strike;
	}
	
	@Override
	public String toString() {
		return "PutOption [expiryDate=" + expiryDate + ", strike=" + strike + ", tradeID=" + tradeID + ", tradeType="
				+ tradeType + ", timeStamp=" + timeStamp + ", volume=" + volume + ", trader=" + trader + ", broker="
				+ broker + ", securityType=" + securityType + ", price=" + price + ", symbol=" + symbol + "]";
	}

}
