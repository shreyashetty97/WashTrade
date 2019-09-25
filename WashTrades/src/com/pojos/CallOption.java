package com.pojos;

import java.sql.Date;

public class CallOption extends Trade {

	private Date expiryDate;
	private float expiryPrice;
	private float strike;

	public CallOption() {
		super();
	}

	public CallOption(int tradeID, String tradeType, Date timeStamp, int volume, Trader trader, Broker broker,
			String securityType, float price, Symbol symbol, Date expiryDate,float expiryPrice, float strike
			) {
		super(tradeID,  tradeType,  timeStamp,  volume,  trader,  broker,
				 securityType,  price,  symbol);
		this.expiryDate = expiryDate;
		this.expiryPrice = expiryPrice;
		this.strike = strike;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public float getExpiryPrice() {
		return expiryPrice;
	}

	public void setExpiryPrice(float expiryPrice) {
		this.expiryPrice = expiryPrice;
	}

	public float getStrike() {
		return strike;
	}

	public void setStrike(float strike) {
		this.strike = strike;
	}

	@Override
	public String toString() {
		return "CallOption [expiryDate=" + expiryDate + ", expiryPrice=" + expiryPrice + ", strike=" + strike
				+ ", tradeID=" + tradeID + ", tradeType=" + tradeType + ", timeStamp=" + timeStamp + ", volume="
				+ volume + ", trader=" + trader + ", broker=" + broker + ", securityType=" + securityType + ", price="
				+ price + ", symbol=" + symbol + "]";
	}
	
	

	
}
