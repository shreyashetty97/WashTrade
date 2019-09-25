package com.dao;


import com.pojos.Symbol;

public interface SymbolDAO {
	
	int addSymbol(Symbol symbol);
    Symbol getSymbolID(int symbolID);
    Symbol deleteBySymbolID(int symbolID);

}
