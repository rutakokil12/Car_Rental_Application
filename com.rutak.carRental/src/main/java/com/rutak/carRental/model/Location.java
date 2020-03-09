package com.rutak.carRental.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Enum of available locations 
public enum Location {
	MALVERN,
	DOWNINGTOWN;

	static {
		collectListOfLocations();
	}

	public static  List<String> locationsList;

	public static void collectListOfLocations() {
		locationsList  = Stream.of(Location.values()).map(Location::toString).collect(Collectors.toList());
	}

	public static boolean isValidLocation(String location){
		return(locationsList.contains(location)) ;

	}
}
