package com.pojos;
import java.util.HashMap;
import java.util.List;

public class Wash {

	private int  washID;
	private float priceMargin;
	private float volumeMargin;
	private int brokerID;
	private int traderID;
	private HashMap<Integer, List<Integer>> washMap; //List of all TradeID's of trades associated with this washid
	
	public Wash(int washID, float priceMargin, float volumeMargin, int brokerID, int traderID,
			HashMap<Integer, List<Integer>> washMap) {
		super();
		this.washID = washID;
		this.priceMargin = priceMargin;
		this.volumeMargin = volumeMargin;
		this.brokerID = brokerID;
		this.traderID = traderID;
		this.washMap = washMap;
	}

	public Wash() {
	
	}

	public int getWashID() {
		return washID;
	}

	public void setWashID(int washID) {
		this.washID = washID;
	}

	public float getPriceMargin() {
		return priceMargin;
	}

	public void setPriceMargin(float priceMargin) {
		this.priceMargin = priceMargin;
	}

	public float getVolumeMargin() {
		return volumeMargin;
	}

	public void setVolumeMargin(float volumeMargin) {
		this.volumeMargin = volumeMargin;
	}

	public int getBrokerID() {
		return brokerID;
	}

	public void setBrokerID(int brokerID) {
		this.brokerID = brokerID;
	}

	public int getTraderID() {
		return traderID;
	}

	public void setTraderID(int traderID) {
		this.traderID = traderID;
	}

	public HashMap<Integer, List<Integer>> getWashMap() {
		return washMap;
	}

	public void setWashMap(HashMap<Integer, List<Integer>> washMap) {
		this.washMap = washMap;
	}
	
}
