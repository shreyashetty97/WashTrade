package com.pojos;

public class Symbol {
	
	private int symbolId;
	private String name;
	
	public Symbol() {
		
	}

	public Symbol(int symbolId,String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSymbolId() {
		return symbolId;
	}
	
}
