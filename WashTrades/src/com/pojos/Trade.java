package com.pojos;

import java.sql.Date;

public class Trade {

	protected int tradeID;
	protected String tradeType; /// buy OR SELL
	protected Date timeStamp;
	protected int volume;
	protected int traderID;
	protected int brokerID;
	protected String securityType;
	protected float price;
	protected int symbolID;


	public Trade() {
		
	}
    
	public Trade(int tradeID, String tradeType, Date timeStamp, int volume, int traderID, int brokerID,
			String securityType, float price, int symbolID) {
		super();
		this.tradeID = tradeID;
		this.tradeType = tradeType;
		this.timeStamp = timeStamp;
		this.volume = volume;
		this.traderID = traderID;
		this.brokerID = brokerID;
		this.securityType = securityType;
		this.price = price;
		this.symbolID = symbolID;
	}

	public int getTradeID() {
		return tradeID;
	}


    public String getTradeType() {
		return tradeType;
	}


	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}


	public Date getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}


	public int getVolume() {
		return volume;
	}


	public void setVolume(int volume) {
		this.volume = volume;
	}


	public int getTraderID() {
		return traderID;
	}


	public void setTraderID(int traderID) {
		this.traderID = traderID;
	}


	public int getBrokerID() {
		return brokerID;
	}


	public void setBrokerID(int brokerID) {
		this.brokerID = brokerID;
	}


	public String getSecurityType() {
		return securityType;
	}


	public void setSecurityType(String securityType) {
		this.securityType = securityType;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public int getSymbolID() {
		return symbolID;
	}


	public void setSymbolID(int symbolID) {
		this.symbolID = symbolID;
	}


}