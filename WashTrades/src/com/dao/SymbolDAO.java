package com.dao;


import com.pojos.Symbol;

public interface SymbolDAO {
	
	int addSymbol(Symbol symbol);
    Symbol findBySymbolID(int symbolID);
    Symbol findBySymbolName(String name);
    Symbol deleteBySymbolID(int symbolID);

}
