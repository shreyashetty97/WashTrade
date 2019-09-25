package com.pojos;
import java.util.HashMap;
import java.util.List;

public class Wash {

	private int  washID;
	private float priceMargin;
	private float volumeMargin;
	private Broker broker;
	private Trader trader;
	private HashMap<Integer, List<Integer>> washMap; //List of all TradeID's of trades associated with this washid
	
	
	
}
