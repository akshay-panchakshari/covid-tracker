package com.kn.covid.tracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RealTimeData {
	@JsonProperty("confirmed")
	private Integer confirmed;
	@JsonProperty("recovered")
	private Integer recovered;
	@JsonProperty("deaths")
	private Integer deaths;
	@JsonProperty("country")
	private String country;
	@JsonProperty("population")
	private Integer population;
	@JsonProperty("confirmed")
	public Integer getConfirmed() {
	return confirmed;
	}

	@JsonProperty("confirmed")
	public void setConfirmed(Integer confirmed) {
	this.confirmed = confirmed;
	}

	@JsonProperty("recovered")
	public Integer getRecovered() {
	return recovered;
	}

	@JsonProperty("recovered")
	public void setRecovered(Integer recovered) {
	this.recovered = recovered;
	}

	@JsonProperty("deaths")
	public Integer getDeaths() {
	return deaths;
	}

	@JsonProperty("deaths")
	public void setDeaths(Integer deaths) {
	this.deaths = deaths;
	}

	@JsonProperty("country")
	public String getCountry() {
	return country;
	}

	@JsonProperty("country")
	public void setCountry(String country) {
	this.country = country;
	}

	@JsonProperty("population")
	public Integer getPopulation() {
	return population;
	}

	@JsonProperty("population")
	public void setPopulation(Integer population) {
	this.population = population;
	}

	@Override
	public String toString() {
		return "RealTimeData [confirmed=" + confirmed + ", recovered=" + recovered + ", deaths=" + deaths + ", country="
				+ country + ", population=" + population + "]";
	}
	
}
