package com.pojos;

import java.sql.Date;

public class Future extends Trade {
	
	private Date expiryDate;
	
	public Future() {
		super();
	}

	public Future(int tradeID, String tradeType, Date timeStamp, int volume,Trader trader,Broker broker,
			String securityType, float price,Symbol symbol ,Date expiryDate){
		
		super( tradeID,  tradeType,  timeStamp,  volume,  trader,  broker,
				 securityType,  price,  symbol);
		this.expiryDate = expiryDate;
	}
		
	public Date getExpiryDate() {
		return expiryDate;
	}
	
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "Future [expiryDate=" + expiryDate + ", tradeID=" + tradeID + ", tradeType=" + tradeType + ", timeStamp="
				+ timeStamp + ", volume=" + volume + ", trader=" + trader + ", broker=" + broker + ", securityType="
				+ securityType + ", price=" + price + ", symbol=" + symbol + "]";
	}
	
	
}
