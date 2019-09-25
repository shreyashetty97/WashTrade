package com.pojos;

import java.sql.Date;

public class PutOption extends Security {

	private Date dateOfSettlement;
	private float marketPrice; // for calculation
	private float strike; // will be equal to "price" in "Security"

	public PutOption() {
		super();
	}

	public PutOption(float price, Symbol symbol, Date expiryDate, Date dateOfSettlement, float premium,
			float strike) {
		super(price, symbol);
		this.dateOfSettlement = dateOfSettlement;
		this.marketPrice = premium;
		this.strike = strike;
	}

	public Date getDateOfSettlement() {
		return dateOfSettlement;
	}

	public void setDateOfSettlement(Date dateOfSettlement) {
		this.dateOfSettlement = dateOfSettlement;
	}

	public float getPremium() {
		return marketPrice;
	}

	public void setPremium(float premium) {
		this.marketPrice = premium;
	}

	public float getStrike() {
		return strike;
	}

	public void setStrike(float strike) {
		this.strike = strike;
	}
}
