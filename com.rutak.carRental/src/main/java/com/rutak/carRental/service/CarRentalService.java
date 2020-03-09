package com.rutak.carRental.service;

import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.rutak.carRental.model.CarInfo;
import com.rutak.carRental.model.CarRentalResponseData;
import com.rutak.carRental.model.CarRequestForm;
import com.rutak.carRental.model.Location;
import com.rutak.carRental.model.ReservationRequestData;
import com.rutak.carRental.model.StoreRequestDate;

@Service
public class CarRentalService {

	@Inject
	CarInfo carInfo;

	@Inject
	CarRentalResponseData carRentalResponseData;

	@Inject
	StoreRequestDate storeRequestDate;

	String dateFormat = "dd-MM-yyyy hh:mm a";


	private static final Map<String, List<CarInfo>> carRentalMap = new HashMap<String, List<CarInfo>>();

	static {
		init();
	}


	//Hashmap is initialized as a storage for car details 
	private static void init() {
		List<CarInfo> malvernCarsList = new ArrayList<CarInfo>();
		CarInfo malvernCarInfo1  = new CarInfo();
		malvernCarInfo1.setCarName("civic");
		malvernCarInfo1.setCategory("economy");
		malvernCarsList.add(malvernCarInfo1);

		CarInfo malvernCarInfo2  = new CarInfo();
		malvernCarInfo2.setCarName("tesla");
		malvernCarInfo2.setCategory("luxury");
		malvernCarsList.add(malvernCarInfo2);


		List<CarInfo> downingtownCarsList = new ArrayList<CarInfo>();
		CarInfo downingtownCarInfo1  = new CarInfo();
		downingtownCarInfo1.setCarName("accord");
		downingtownCarInfo1.setCategory("economy");
		downingtownCarsList.add(downingtownCarInfo1);

		CarInfo downingtownCarInfo2  = new CarInfo();
		downingtownCarInfo2.setCarName("benz");
		downingtownCarInfo2.setCategory("luxury");
		downingtownCarsList.add(downingtownCarInfo2);


		carRentalMap.put(Location.MALVERN.toString(),malvernCarsList);
		carRentalMap.put(Location.DOWNINGTOWN.toString(),downingtownCarsList);


	}
	// retrieves available cars list for selected location and valid dates
	public CarRentalResponseData getAvailableInfo(CarRequestForm carRequestForm, List<String> errorMessages) throws ParseException { 
		Date requestFromDate = convertToDateTimeFormat(carRequestForm.getFromDateTime(),dateFormat);
		Date requestToDate = convertToDateTimeFormat(carRequestForm.getToDateTime(),dateFormat);
		String locationKey = carRequestForm.getLocation();
		List<CarInfo> listOfCars = new ArrayList<CarInfo>();
		carRentalMap.get(locationKey).forEach(car ->{
			// if car is not reserved before, dates will be null in initial stage. Once the car is bokked , its details will have past from dates and todates to comapre further 
			if(null == car.getFromDateTime() && null == car.getToDateTime()) {
				listOfCars.add(car);
				storeRequestedDates(requestFromDate,requestToDate);
			}else if(isCarAvailableForRequestedDates(car ,requestFromDate)) {   //check to see if car is available for user requested dates
				listOfCars.add(car);
				storeRequestedDates(requestFromDate,requestToDate);
			}
		});
		if(listOfCars.isEmpty()) {
			errorMessages.add("No any car available for the location and requested dates. Try with other combination of location and dates");

		}
		carRentalResponseData.setListOfCars(listOfCars);
		carRentalResponseData.setLocation(carRequestForm.getLocation());
		carRentalResponseData.setErrorList(errorMessages);
		return carRentalResponseData;
	}

	private void storeRequestedDates(Date requestFromDate, Date requestToDate) {
		storeRequestDate.setRequestFromDate(requestFromDate);
		storeRequestDate.setRequestToDate(requestToDate);
	}

	private boolean isCarAvailableForRequestedDates(CarInfo car ,Date requestFromDate) {
		if((requestFromDate.compareTo(car.getToDateTime()) <= 0) && (requestFromDate.compareTo(car.getFromDateTime())>=0)){
			return false;
		}

		return true;

	}

	// To update car reservation dates for selected car and location
	public boolean updateCarReservation(ReservationRequestData reservationRequestData) throws ParseException { 

		String locationKey = reservationRequestData.getLocation();
		try {
			if(carRentalMap.containsKey(locationKey)) {
				carRentalMap.get(locationKey).forEach(car ->{
					if(car.getCarName().equalsIgnoreCase(reservationRequestData.getCarName())) {
						car.setFromDateTime(storeRequestDate.getRequestFromDate());
						car.setToDateTime(storeRequestDate.getRequestToDate());
					}
				});
			}
		}catch(Exception e) {
			System.out.println("Exception while reserving car" +e.getMessage());
			return false;
		}


		return true;

	}


	public Date convertToDateTimeFormat(String dateTime ,String pattern) throws ParseException{
		SimpleDateFormat dateTimeFormat =new SimpleDateFormat(pattern);
		Date requestDate = dateTimeFormat.parse(dateTime);
		return requestDate;

	}

}
