package com.pojos;

public class Broker {

	private int brokerID;
	private String name;

	public Broker() {

	}

	public Broker(String name) {
		this.name = name;
	}
	
	public int getBrokerID() {
		return brokerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
