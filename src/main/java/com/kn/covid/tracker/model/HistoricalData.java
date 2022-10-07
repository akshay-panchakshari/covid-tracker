package com.kn.covid.tracker.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoricalData {
	
	@JsonProperty("country")
	private String country;
	@JsonProperty("population")
	private Integer population;
	@JsonProperty("dates")
	private  Map<String, Long> dates;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}
	public Map<String, Long> getDates() {
		return dates;
	}

	public void setDates( Map<String, Long> dates) {
		this.dates = dates;
	}

	@Override
	public String toString() {
		return "HistoricalData [country=" + country + ", population=" + population + ", dates=" + dates + "]";
	}
		
	
}
