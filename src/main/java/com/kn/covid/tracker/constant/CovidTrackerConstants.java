package com.kn.covid.tracker.constant;

public class CovidTrackerConstants {
	public static final String BASE_URI = "https://covid-api.mmediagroup.fr/v1";
	public static final String HISTORY_URI = "/history";
	public static final String CASES_URI = "/cases";
	public static final String VACCINES_URI = "/vaccines";
	public static final String CONFIRMED = "Confirmed";
	public static final String COUNTRY = "country";
	public static final String STATUS = "status";
	public static final String RELATIME_API_ERROR_MSG = "Could not fetch real time data, remote api call failed : " ;
	public static final String HISTORY_API_ERROR_MSG = "Could not fetch historical data, remote api call failed : ";
	public static final String VACCINE_API_ERROR_MSG = "Could not fetch vaccination data, remote api call failed : ";
	public static final String RELATIME_API_INCORRECT_RESPONSE = "Incorrect response recieved from the API ! Please try again later!";
	public static final String VACCINE_API_INCORRECT_RESPONSE = "Incorrect response recieved from vaccination API, showing default values!";
	
}
