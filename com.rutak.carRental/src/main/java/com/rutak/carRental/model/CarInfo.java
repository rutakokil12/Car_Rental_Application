package com.rutak.carRental.model;

import java.util.Date;

import javax.inject.Named;

//This object is for car Details with its reservation dates
@Named
public class CarInfo {
	private String carName;
	private String category;
	private Date fromDateTime;
	private Date toDateTime;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public Date getFromDateTime() {
		return fromDateTime;
	}

	public void setFromDateTime(Date fromDateTime) {
		this.fromDateTime = fromDateTime;
	}

	public Date getToDateTime() {
		return toDateTime;
	}

	public void setToDateTime(Date toDateTime) {
		this.toDateTime = toDateTime;
	}

	public CarInfo() {

	}

}

