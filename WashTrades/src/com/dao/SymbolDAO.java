package com.dao;


import com.pojos.Symbol;

public interface SymbolDAO {
	
	int addSymbol(Symbol symbol);
    Symbol getBySymbolID(int symbolID);
    Symbol findBySymbolName(String name);
    Symbol deleteBySymbolID(int symbolID);
    boolean deleteAllSymbols();

}
