package com.pojos;

public class Trader {

	private int traderID;
	private String name;

	public Trader() {

	}

	public Trader(String name) {
		this.name = name;
	}
	

	public int getTraderID() {
		return traderID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
