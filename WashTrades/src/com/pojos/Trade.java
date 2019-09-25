package com.pojos;

public class Trade {

	private int tradeID;
	private String tradeType; /// buy OR SELL
	private String timestamp;
	private int volume;
	private Trader trader;
	private Broker broker;
	private Security security;

	public Trade() {
		
	}
	
	public Trade(int tradeID, String tradeType, String timestamp, int volume, Trader trader, Broker broker,
			Security security) {
		super();
		this.tradeID = tradeID;
		this.tradeType = tradeType;
		this.timestamp = timestamp;
		this.volume = volume;
		this.trader = trader;
		this.broker = broker;
		this.security = security;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
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

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public Trader getTrader() {
		return trader;
	}

	public void setTrader(Trader trader) {
		this.trader = trader;
	}

	public Broker getBroker() {
		return broker;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
	}
//
//	public Security getSecurity() {
//		return security;
//	}
//
//	public void setSecurity(Security security) {
//		this.security = security;
//	}
}
