package com.pojos;

import java.util.List;

public class Wash {

	private int washID;
	private double priceMargin;
	private List<Trade> trades;
	
	public Wash() {
	
	}

	public Wash(int washID, double priceMargin, int stockID, List<Trade> trades) {
		this.washID = washID;
		this.priceMargin = priceMargin;
		this.trades = trades;
	}

	public int getWashID() {
		return washID;
	}


	public double getPriceMargin() {
		return priceMargin;
	}

	public void setPriceMargin(double priceMargin) {
		this.priceMargin = priceMargin;
	}

	public List<Trade> getTrades() {
		return trades;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}
}
