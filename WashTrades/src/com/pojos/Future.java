package com.pojos;

import java.sql.Date;

public class Future extends Trade {
	
	private Date expiryDate;
	
	public Future() {
		super();
	}

	public Future(int tradeID, String tradeType, Date timeStamp, int volume, int traderID, int brokerID,
			String securityType, float price, int symbolID,Date expiryDate){
		
		super( tradeID,  tradeType,  timeStamp,  volume,  traderID,  brokerID,
				 securityType,  price,  symbolID);
		this.expiryDate = expiryDate;
	}
		
	public Date getExpiryDate() {
		return expiryDate;
	}
	
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
}
