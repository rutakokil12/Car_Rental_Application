package com.rutak.carRental.model;

import javax.ws.rs.QueryParam;

// BeanParam for user request when user requests to see all available cars for the requested location and dates

public class CarRequestForm {
	@QueryParam("location")
	private String location;

	@QueryParam("fromDate")
	private String fromDate;

	@QueryParam("fromTime")
	private String fromTime;

	@QueryParam("toDate")
	private String toDate;

	@QueryParam("toTime")
	private String toTime;

	private String fromDateTime;
	private String toDateTime;

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public String getFromDateTime() {
		return fromDate+ " "+fromTime;
	}
	public String getToDateTime() {
		return toDate +" "+ toTime;
	}

}
