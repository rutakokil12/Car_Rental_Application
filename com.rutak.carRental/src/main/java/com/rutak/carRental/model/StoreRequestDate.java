package com.rutak.carRental.model;

import java.util.Date;

import javax.inject.Named;

// this is to store requested from and To dates user had provided
@Named
public class StoreRequestDate {

	private Date requestFromDate;
	private Date requestToDate;

	public Date getRequestFromDate() {
		return requestFromDate;
	}
	public void setRequestFromDate(Date requestFromDate) {
		this.requestFromDate = requestFromDate;
	}
	public Date getRequestToDate() {
		return requestToDate;
	}
	public void setRequestToDate(Date requestToDate) {
		this.requestToDate = requestToDate;
	}


}
