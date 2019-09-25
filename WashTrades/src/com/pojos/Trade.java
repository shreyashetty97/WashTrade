 package com.pojos;

import java.sql.Date;

public class Trade {

	protected int tradeID;
	protected String tradeType; /// buy OR SELL
	protected Date timeStamp;
	protected int volume;
	protected Trader trader;
	protected Broker broker;
	protected String securityType;
	protected float price;
	protected Symbol symbol ;
	
	public Trade() {
		
	}
	
	public Trade(int tradeID, String tradeType, Date timeStamp, int volume, Trader trader, Broker broker,
			String securityType, float price, Symbol symbol) {
		super();
		this.tradeID = tradeID;
		this.tradeType = tradeType;
		this.timeStamp = timeStamp;
		this.volume = volume;
		this.trader = trader;
		this.broker = broker;
		this.securityType = securityType;
		this.price = price;
		this.symbol = symbol;
	}
	public int getTradeID() {
		return tradeID;
	}
	public void setTradeID(int tradeID) {
		this.tradeID = tradeID;
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
	public Symbol getSymbol() {
		return symbol;
	}
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}
	@Override
	public String toString() {
		return "Trade [tradeID=" + tradeID + ", tradeType=" + tradeType + ", timeStamp=" + timeStamp + ", volume="
				+ volume + ", trader=" + trader + ", broker=" + broker + ", securityType=" + securityType + ", price="
				+ price + ", symbol=" + symbol + "]";
	}
	
	

}