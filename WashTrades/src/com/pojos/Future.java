package com.pojos;

import java.sql.Date;

public class Future extends Security {
	
	private Date expiryDate;
	
	public Future() {
		super();
	}

	public Future(float price, Symbol symbol, Date expiryDate){
		super(price, symbol);
		this.expiryDate = expiryDate;
	}
		
	public Date getExpiryDate() {
		return expiryDate;
	}
	
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
}
