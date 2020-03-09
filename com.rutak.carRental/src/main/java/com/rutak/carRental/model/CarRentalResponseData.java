package com.rutak.carRental.model;

import java.util.List;

import javax.inject.Named;

// This is the response object with filtered list of cars sent as a response of available cars for a location and dates
@Named
public class CarRentalResponseData {

	private String location;
	private List<CarInfo> listOfCars;
	private List<String> errorList;
	
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<CarInfo> getListOfCars() {
		return listOfCars;
	}
	public void setListOfCars(List<CarInfo> listOfCars) {
		this.listOfCars = listOfCars;
	}
	public List<String> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}
	
}
