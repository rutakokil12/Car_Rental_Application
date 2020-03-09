package com.rutak.carRental.resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.QueryParam;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rutak.carRental.service.CarRentalService;
import com.rutak.carRental.model.CarRentalResponseData;
import com.rutak.carRental.model.CarRequestForm;
import com.rutak.carRental.model.Location;
import com.rutak.carRental.model.ReservationRequestData;
import com.rutak.carRental.model.StoreRequestDate;
//Resource class with all Rest Api 

@RestController 
public class RentalAppRESTController {

	@Inject
	private CarRentalService carRentalService;

	@Inject
	private CarRentalResponseData carRentalResponseData;

	@Inject
	private StoreRequestDate storeRequestDate;

	String dateFormat = "dd-MM-yyyy hh:mm a";
	// URL:
	// http://localhost:8080/rentalCars


	@RequestMapping(value = "/rentalCars", method = RequestMethod.GET, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public CarRentalResponseData getAvailableCars(@BeanParam CarRequestForm carRequestForm) {
		List<String> errorMessages  = new ArrayList<String>();
		try {
			Date currentDateTime = carRentalService.convertToDateTimeFormat(new SimpleDateFormat(dateFormat).format(new Date()), dateFormat);
			inputValidation(carRequestForm, currentDateTime ,errorMessages);
			if(errorMessages.isEmpty()) {
				carRentalResponseData = carRentalService.getAvailableInfo(carRequestForm,errorMessages);
			}else {
				carRentalResponseData.setErrorList(errorMessages);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return carRentalResponseData;
	}

	// URL:
	// http://localhost:8080/updateReservation

	@RequestMapping(value = "/updateReservation", method = RequestMethod.POST, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public String updateReservation(@RequestBody ReservationRequestData reservationRequestData) {
		boolean isSuccessful = false;
		String message =" ";
		try {
			isSuccessful = carRentalService.updateCarReservation(reservationRequestData);
			message = "Car is reserved successfully for Requested from date - " + storeRequestDate.getRequestFromDate().toString() + "to date - " +storeRequestDate.getRequestToDate().toString();
			System.out.println(message);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return message;
	}
	private void inputValidation(CarRequestForm carRequestForm, Date currentDateTime ,List<String> errorMessages) {
		validateDateTime(carRequestForm.getFromDateTime(),carRequestForm.getToDateTime(),currentDateTime,errorMessages);
		validateLocationSelected(carRequestForm.getLocation(),errorMessages);
	}

	private void validateLocationSelected(String location, List<String> errorMessages) {
		if(!Location.isValidLocation(location)) {
			errorMessages.add("We do not provide services to the location " +location);

		}
	}

	private void validateDateTime(String requestFromDate, String requestToDate, Date currentDateTime, List<String> errorMessages) {
		//add 2 weeks
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.WEEK_OF_YEAR, 2);
		try {
			Date dateAfterTwoWeeks = carRentalService.convertToDateTimeFormat(new SimpleDateFormat(dateFormat).format(calendar.getTime()),dateFormat);
			Date fromDate = carRentalService.convertToDateTimeFormat(requestFromDate,dateFormat);
			Date toDate = carRentalService.convertToDateTimeFormat(requestToDate,dateFormat);
			// condition - user's request date must not be the past date
			if(fromDate.compareTo(currentDateTime) < 0 || toDate.compareTo(currentDateTime) < 0) {
				errorMessages.add("Dates must not be from the past");
				System.out.println("Dates must not be from the past");
			}
			// condition - user can book upto 2 weeks in advance
			if(fromDate.compareTo(toDate) < 0 && fromDate.compareTo(dateAfterTwoWeeks)>0) {
				errorMessages.add("from date must not be after toDate and from date cannot be after two weeks from now");
				System.out.println("from date must not be after toDate and from date cannot be after two weeks from now");
			}

		}catch(ParseException e) {
			errorMessages.add("Invalid date and time format");
			System.out.println("Invalid date and time format " + e.getMessage());
		}
	}

	// URL:
	// http://localhost:8080/locations

	@RequestMapping(value = "/locations", method = RequestMethod.GET, 
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public List<String> getAllLocations() {
		return Location.locationsList;
	}
}
