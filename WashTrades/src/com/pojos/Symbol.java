package com.pojos;

public class Symbol {
	
	private int symbolID;
	private String name;
	
	public Symbol() {
		
	}

	public Symbol(int symbolID,String name) {
		this.name = name;
		this.symbolID=symbolID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSymbolID() {
		return symbolID;
	}
	
}
