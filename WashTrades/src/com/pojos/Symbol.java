package com.pojos;

public class Symbol {
	
	private int symbolID;
	private String name;
	
	public Symbol() {
		
	}

	public Symbol(int symbolID,String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getsymbolID() {
		return symbolID;
	}

	@Override
	public String toString() {
		return "Symbol [symbolID=" + symbolID + ", name=" + name + "]";
	}
	
}
