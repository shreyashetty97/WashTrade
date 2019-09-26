package com.pojos;
import java.util.List;

public class Wash {

	private int  washID;
	private float priceMargin;
	private float volumeMargin;
	private Symbol symbol;
	private Broker broker;
	private Trader trader;
	private List<Trade> trades; //List of all trades associated with this washid
	public Wash(int washID, float priceMargin, float volumeMargin, Symbol symbol, Broker broker, Trader trader,
			List<Trade> trades) 
	{
		this.washID = washID;
		this.priceMargin = priceMargin;
		this.volumeMargin = volumeMargin;
		this.symbol = symbol;
		this.broker = broker;
		this.trader = trader;
		this.trades = trades;
	}
	public int getWashID() {
		return washID;
	}
	public void setWashID(int washID) {
		this.washID = washID;
	}
	public float getPriceMargin() {
		return priceMargin;
	}
	public void setPriceMargin(float priceMargin) {
		this.priceMargin = priceMargin;
	}
	public float getVolumeMargin() {
		return volumeMargin;
	}
	public void setVolumeMargin(float volumeMargin) {
		this.volumeMargin = volumeMargin;
	}
	public Symbol getSymbol() {
		return symbol;
	}
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}
	public Broker getBroker() {
		return broker;
	}
	public void setBroker(Broker broker) {
		this.broker = broker;
	}
	public Trader getTrader() {
		return trader;
	}
	public void setTrader(Trader trader) {
		this.trader = trader;
	}
	public List<Trade> getTrades() {
		return trades;
	}
	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}
	@Override
	public String toString() {
		return "Wash [washID=" + washID + ", priceMargin=" + priceMargin + ", volumeMargin=" + volumeMargin
				+ ", symbol=" + symbol + ", broker=" + broker + ", trader=" + trader + ", trades=" + trades + "]";
	}
	
	
	
	
	
}
