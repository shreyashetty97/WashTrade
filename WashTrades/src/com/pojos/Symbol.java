package com.pojos;

public class Symbol {
	
	private String symbolId;
	private String name;
	
	public Symbol() {
		
	}

	public Symbol(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbolId() {
		return symbolId;
	}
	
}
