package com.rutak.carRental.model;

//This is request object when user tries to reserve car
public class ReservationRequestData {
	private String location;
	private String carName;

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}

}
